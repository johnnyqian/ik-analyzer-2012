package net.johnnyqian;

import org.wltea.analyzer.cfg.Configuration;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;
import org.wltea.analyzer.dic.Dictionary;
import org.wltea.analyzer.dic.Hit;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Johnny Qian on 8/16/2016.
 */

public class AddWord {
    public static void main(String[] args) throws IOException {
        String s = "中文分词工具包";

        Configuration cfg = org.wltea.analyzer.cfg.DefaultConfig.getInstance(); //加载词库
        cfg.setUseSmart(true); //设置智能分词

        Dictionary.initial(cfg);
        Dictionary dictionary = Dictionary.getSingleton();

        List<String> words = new ArrayList<>();
        words.add("基础班");
        words.add("高级会计实务");

        dictionary.addWords(words); //自动添加自定义词

        System.out.println(cfg.getMainDictionary()); // 系统默认词库
        System.out.println(cfg.getQuantifierDicionary());

        Hit hit = dictionary.matchInMainDict("基础班".toCharArray());
        System.out.println(hit.isMatch());

        System.out.println(queryWords(s));
    }

    public static List<String> queryWords(String query) throws IOException {
        List<String> list = new ArrayList<>();
        StringReader input = new StringReader(query.trim());
        IKSegmenter ikSeg = new IKSegmenter(input, true); // true 用智能分词，false细粒度
        for (Lexeme lexeme = ikSeg.next(); lexeme != null; lexeme = ikSeg.next()) {
            list.add(lexeme.getLexemeText());
        }
        return list;
    }
}
