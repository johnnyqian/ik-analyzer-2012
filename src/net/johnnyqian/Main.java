package net.johnnyqian;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

public class Main {

    public static void main(String[] args) {
        String str = "马云和阿里巴巴都很牛。居然之家与欧特克之间有着战略合作。" +
                "长春市长春药店。乒乓球拍卖啦。薄熙来到重庆。周杰轮周杰伦，范伟骑范玮琪。";
        IKAnalysis(str);
    }

    public static String IKAnalysis(String str) {
        StringBuilder sb = new StringBuilder();
        try {
            Reader read = new StringReader(str);
            IKSegmenter iks = new IKSegmenter(read, false); // true 用智能分词，false细粒度
            Lexeme t;
            while ((t = iks.next()) != null) {
                sb.append(t.getLexemeText()).append(" | ");
            }
            sb.delete(sb.length() - 3, sb.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
}
