import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Collections;
import java.util.List;

public class JavaExam extends JFrame {
    private final JLabel labelQuestion;
    private final JRadioButton radioButtonA, radioButtonB, radioButtonC, radioButtonD;
    private final JCheckBox checkBoxA, checkBoxB, checkBoxC, checkBoxD;
    private final ButtonGroup buttonGroup;
    private final JButton buttonSubmit;
    private final JLabel labelSingleChoiceNum, labelMultipleChoiceNum, labelTrueOrFalseNum, labelScore, labelTime;
    private int singleChoiceCount = 0, multipleChoiceCount = 0, trueOrFalseCount = 0;
    private int singleChoiceCorrectCount = 0, multipleChoiceCorrectCount = 0, trueOrFalseCorrectCount = 0;
    private int score = 0, seconds = 0, questionIndex = 0;
    private boolean isSingleChoice, isMultipleChoice, isTrueOrFalse, submit = false;
    private final List<SingleChoice> singleChoices;
    private final List<MultipleChoice> multipleChoices;
    private final List<TrueOrFalse> trueOrFalse;
    private final Timer timer;
    private final JPanel panelQuestion = new JPanel();

    class GiveQuestion implements Runnable {

        @Override
        public void run() {
            Collections.shuffle(singleChoices);
            Collections.shuffle(multipleChoices);
            Collections.shuffle(trueOrFalse);
            while (questionIndex < 15) {
                submit = false;
                if (questionIndex % 3 == 0) {
                    isSingleChoice = true;
                    showSingleChoiceQuestion(singleChoices.get(singleChoiceCount));
                    singleChoiceCount++;
                } else if (questionIndex % 3 == 1) {
                    isMultipleChoice = true;
                    showMultipleChoiceQuestion(multipleChoices.get(multipleChoiceCount));
                    multipleChoiceCount++;
                } else {
                    isTrueOrFalse = true;
                    showTrueOrFalseQuestion(trueOrFalse.get(trueOrFalseCount));
                    trueOrFalseCount++;
                }
                timer.start();
                while (!submit) {
                }
                questionIndex++;
            }
            endExam();
        }
    }

    public JavaExam() {
        super("Java机考 By YeMaolin");
        singleChoices = new Questions().singleChoices;
        multipleChoices = new Questions().multipleChoices;
        trueOrFalse = new Questions().trueOrFalse;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        panelQuestion.setLayout(new GridLayout(5,1));
        panelQuestion.setBorder(new EmptyBorder(0,15,0,0)); // 设置组件的外边距
        labelQuestion = new JLabel("", SwingConstants.CENTER);
        radioButtonA = new JRadioButton("A");
        radioButtonB = new JRadioButton("B");
        radioButtonC = new JRadioButton("C");
        radioButtonD = new JRadioButton("D");
        checkBoxA = new JCheckBox("A");
        checkBoxB = new JCheckBox("B");
        checkBoxC = new JCheckBox("C");
        checkBoxD = new JCheckBox("D");
        buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButtonA);
        buttonGroup.add(radioButtonB);
        buttonGroup.add(radioButtonC);
        buttonGroup.add(radioButtonD);
        JPanel panelInfo = new JPanel();
        labelSingleChoiceNum = new JLabel("单选题：0/5/0", SwingConstants.CENTER);
        labelMultipleChoiceNum = new JLabel("多选题：0/5/0", SwingConstants.CENTER);
        labelTrueOrFalseNum = new JLabel("判断题：0/5/0", SwingConstants.CENTER);
        labelScore = new JLabel("成绩：0分", SwingConstants.CENTER);
        labelTime = new JLabel("用时：0秒", SwingConstants.CENTER);
        panelInfo.add(labelSingleChoiceNum);
        panelInfo.add(labelMultipleChoiceNum);
        panelInfo.add(labelTrueOrFalseNum);
        panelInfo.add(labelScore);
        panelInfo.add(labelTime);
        add(panelInfo,BorderLayout.NORTH);
        add(panelQuestion,BorderLayout.WEST);
        setVisible(true);
        buttonSubmit = new JButton("提交");
        add(buttonSubmit,BorderLayout.SOUTH);
        buttonSubmit.addActionListener(e -> submitAnswer());
        timer = new Timer(1000, e -> {
            seconds++;
            labelTime.setText("用时：" + seconds + "秒");
            if (seconds >= 20) {
                submitAnswer();
            }
        });
        Thread giveQuestion = new Thread(new GiveQuestion());
        giveQuestion.start();
    }

    private void endExam() {
        timer.stop();
        JOptionPane.showMessageDialog(this, "考试结束，您的成绩是" + score + "分！");
        System.exit(0);
    }

    private void showSingleChoiceQuestion(SingleChoice singleChoice) {
        panelQuestion.removeAll();
        labelQuestion.setText(questionIndex + 1 + "/15[单选题]" + singleChoice.question);
        radioButtonA.setText(singleChoice.optionA);
        radioButtonB.setText(singleChoice.optionB);
        radioButtonC.setText(singleChoice.optionC);
        radioButtonD.setText(singleChoice.optionD);
        panelQuestion.add(labelQuestion);
        panelQuestion.add(radioButtonA);
        panelQuestion.add(radioButtonB);
        panelQuestion.add(radioButtonC);
        panelQuestion.add(radioButtonD);
    }

    private void showMultipleChoiceQuestion(MultipleChoice multipleChoice) {
        panelQuestion.removeAll();
        labelQuestion.setText(questionIndex + 1 + "/15[多选题]" + multipleChoice.question);
        checkBoxA.setText(multipleChoice.optionA);
        checkBoxB.setText(multipleChoice.optionB);
        checkBoxC.setText(multipleChoice.optionC);
        checkBoxD.setText(multipleChoice.optionD);
        panelQuestion.add(labelQuestion);
        panelQuestion.add(checkBoxA);
        panelQuestion.add(checkBoxB);
        panelQuestion.add(checkBoxC);
        panelQuestion.add(checkBoxD);
    }

    private void showTrueOrFalseQuestion(TrueOrFalse trueOrFalse) {
        panelQuestion.removeAll();
        labelQuestion.setText(questionIndex + 1 + "/15[判断题]" + trueOrFalse.question);
        radioButtonA.setText("正确");
        radioButtonB.setText("错误");
        panelQuestion.add(labelQuestion);
        panelQuestion.add(radioButtonA);
        panelQuestion.add(radioButtonB);
    }

    private void submitAnswer() {
        timer.stop();
        String correctAnswer = "";
        boolean right = false;
        if (isSingleChoice) {
            String userAnswer = "";
            correctAnswer = singleChoices.get(singleChoiceCount - 1).correctAnswer;
            if (radioButtonA.isSelected()) {
                userAnswer = "A";
            } else if (radioButtonB.isSelected()) {
                userAnswer = "B";
            } else if (radioButtonC.isSelected()) {
                userAnswer = "C";
            } else if(radioButtonD.isSelected()) {
                userAnswer = "D";
            }
            if (userAnswer.equals(correctAnswer)) {
                singleChoiceCorrectCount++;
                score++;
                right = true;
            }
        }else if (isMultipleChoice) {
            correctAnswer = multipleChoices.get(multipleChoiceCount - 1).correctAnswer;
            String userAnswer = "";
            if (checkBoxA.isSelected()) {
                userAnswer += "A";
            }
            if (checkBoxB.isSelected()) {
                userAnswer += "B";
            }
            if (checkBoxC.isSelected()) {
                userAnswer += "C";
            }
            if (checkBoxD.isSelected()) {
                userAnswer += "D";
            }
            if (correctAnswer.equalsIgnoreCase(userAnswer)) {
                multipleChoiceCorrectCount++;
                score += 2;
                right = true;
            }
        }else if (isTrueOrFalse) {
            boolean correct = trueOrFalse.get(trueOrFalseCount - 1).correctAnswer;
            correctAnswer = String.valueOf(correct);
            if (radioButtonA.isSelected()&&correct||radioButtonB.isSelected()&&!correct) {
                trueOrFalseCorrectCount++;
                score++;
                right = true;
            }
        }
        if (right)
            JOptionPane.showMessageDialog(this, "回答正确，用时" + seconds + "秒");
        else JOptionPane.showMessageDialog(this, "回答错误，正确答案是 " + correctAnswer + "\n用时" + seconds + "秒");
        update();
        submit = true;
    }

    private void update() {
        labelScore.setText("成绩：" + score + "分");
        labelSingleChoiceNum.setText("单选题：" + singleChoiceCount + "/5/" + singleChoiceCorrectCount);
        labelMultipleChoiceNum.setText("多选题：" + multipleChoiceCount + "/5/" + multipleChoiceCorrectCount);
        labelTrueOrFalseNum.setText("判断题：" + trueOrFalseCount + "/5/" + trueOrFalseCorrectCount);
        buttonGroup.clearSelection();
        checkBoxA.setSelected(false);
        checkBoxB.setSelected(false);
        checkBoxC.setSelected(false);
        checkBoxD.setSelected(false);
        radioButtonA.setSelected(false);
        radioButtonB.setSelected(false);
        radioButtonC.setSelected(false);
        radioButtonD.setSelected(false);
        seconds = 0;
        isSingleChoice = isMultipleChoice = isTrueOrFalse = false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(JavaExam::new);
    }
}
