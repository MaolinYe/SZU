import javax.swing.*;
import java.awt.*;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.baidu.translate.demo.TransApi;

public class TranslationDemo extends JFrame {
    private JLabel inputLabel, baiduLabel, youdaoLabel, commonLabel;
    private JTextField inputText, baiduTranslation, youdaoTranslation, commonTextArea;
    private JButton translateButton;

    public TranslationDemo() {
        setTitle("中译英 Demo");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(1));
        inputLabel = new JLabel("中文原文：");
        inputText = new JTextField(33);
        translateButton = new JButton("翻译");
        JPanel panel1 = new JPanel();
        panel1.add(inputLabel);
        panel1.add(inputText);
        panel1.add(translateButton);
        add(panel1);
        baiduLabel = new JLabel("百度翻译：");
        baiduTranslation = new JTextField(40);
        baiduTranslation.setEditable(false);
        JPanel panel2 = new JPanel();
        panel2.add(baiduLabel);
        panel2.add(baiduTranslation);
        add(panel2);
        youdaoLabel = new JLabel("有道翻译：");
        youdaoTranslation = new JTextField(40);
        youdaoTranslation.setEditable(false);
        JPanel panel3 = new JPanel();
        panel3.add(youdaoLabel);
        panel3.add(youdaoTranslation);
        add(panel3);
        commonLabel = new JLabel("相同部分：");
        commonTextArea = new JTextField(40);
        commonTextArea.setEditable(false);
        JPanel panel4 = new JPanel();
        panel4.add(commonLabel);
        panel4.add(commonTextArea);
        add(panel4);
        translateButton.addActionListener(e -> {
            String inputText = this.inputText.getText();
            String outputBaidu, outputYouDao, same;
            try {
                outputBaidu = translateUsingBaidu(inputText);
                outputYouDao = translateUsingYoudao(inputText);
            } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
                throw new RuntimeException(ex);
            }
            Pattern pattern = Pattern.compile("\"dst\":\"(.*?)\"");
            Matcher matcher = pattern.matcher(outputBaidu);
            if (matcher.find())
                outputBaidu = matcher.group(1);
            baiduTranslation.setText(outputBaidu);
            pattern = Pattern.compile( "\"translation\":\\[\"(.*?)\"\\]");
            matcher = pattern.matcher(outputYouDao);
            if (matcher.find())
                outputYouDao = matcher.group(1);
            youdaoTranslation.setText(outputYouDao);
            commonTextArea.setText(findCommonPart(outputBaidu, outputYouDao));
        });
    }

    private String translateUsingBaidu(String text) throws UnsupportedEncodingException {    // 调用百度翻译API进行翻译
        TransApi api = new TransApi("", "");
        return api.getTransResult(text, "zh", "en");
    }


    private String translateUsingYoudao(String text) throws NoSuchAlgorithmException {    // 调用有道翻译API进行翻译
        YouDaoAPI api = new YouDaoAPI("", "");
        return api.getTransResult(text, "zh", "en");
    }

    private String findCommonPart(String text1, String text2) {    // 比较两个翻译结果，找出相同部分
        String[] baidu = text1.split("[ ,.]");
        String[] youdao = text2.split("[ ,.]");
        StringBuilder common = new StringBuilder();
        for (String a : baidu) {
            for (String b : youdao) {
                if (Objects.equals(a, b)) {
                    common.append(a).append(" ");
                }
            }
        }
        return common.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TranslationDemo demo = new TranslationDemo();
            demo.setVisible(true);
        });
    }
}
