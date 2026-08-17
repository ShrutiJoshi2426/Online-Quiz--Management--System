import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class QuizFrame extends JFrame {

    JLabel questionLabel, timerLabel;
    JRadioButton option1, option2, option3, option4;
    ButtonGroup optionsGroup;
    JButton nextButton;

    List<Question> questions;
    int currentQuestionIndex = 0;
    int score = 0; // total score

    Timer timer;
    int timePerQuestion = 10; // 10 seconds per question
    int timeLeft;

    public QuizFrame() {

        questions = QuestionBank.getQuestions();
      	//System.out.println("Loaded Questions = " + questions.size());

        setTitle("Java Quiz Application");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // -------- MAIN PANEL --------
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(mainPanel, BorderLayout.CENTER);

        // -------- QUESTION LABEL --------
        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        questionLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(questionLabel, BorderLayout.NORTH);

        // -------- TIMER LABEL BELOW QUESTION --------
        timerLabel = new JLabel("Time Left: " + timePerQuestion + "s");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        timerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        timerLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));
        mainPanel.add(timerLabel, BorderLayout.CENTER);

        // -------- OPTIONS PANEL --------
        option1 = new JRadioButton();
        option2 = new JRadioButton();
        option3 = new JRadioButton();
        option4 = new JRadioButton();

        optionsGroup = new ButtonGroup();
        optionsGroup.add(option1);
        optionsGroup.add(option2);
        optionsGroup.add(option3);
        optionsGroup.add(option4);

        JPanel optionsPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
        optionsPanel.add(option1);
        optionsPanel.add(option2);
        optionsPanel.add(option3);
        optionsPanel.add(option4);
        mainPanel.add(optionsPanel, BorderLayout.SOUTH);

        // -------- NEXT BUTTON --------
        nextButton = new JButton("Next");
        nextButton.setFont(new Font("Arial", Font.BOLD, 16));
        nextButton.setEnabled(false);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(nextButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // -------- LOAD FIRST QUESTION --------
        loadQuestion();

        // -------- ENABLE NEXT WHEN OPTION SELECTED --------
        ActionListener optionSelectedListener = e -> nextButton.setEnabled(true);
        option1.addActionListener(optionSelectedListener);
        option2.addActionListener(optionSelectedListener);
        option3.addActionListener(optionSelectedListener);
        option4.addActionListener(optionSelectedListener);

        // -------- NEXT BUTTON LOGIC --------
        nextButton.addActionListener(e -> {
            stopTimer();
            checkAnswer();
            currentQuestionIndex++;
            if (currentQuestionIndex < questions.size()) {
                loadQuestion();
            } else {
                showResult();
            }
        });

        setVisible(true);
    }

    // -------- LOAD QUESTION --------
    private void loadQuestion() {
        optionsGroup.clearSelection();
        nextButton.setEnabled(false);

        Question q = questions.get(currentQuestionIndex);
        questionLabel.setText("<html>Q" + (currentQuestionIndex + 1) + " / " + questions.size()
                + ": " + q.getQuestion() + "</html>");
        String[] options = q.getOptions();
        option1.setText(options[0]);
        option2.setText(options[1]);
        option3.setText(options[2]);
        option4.setText(options[3]);

        // START TIMER
        startTimer();
    }

    // -------- CHECK ANSWER --------
    private void checkAnswer() {
        Question q = questions.get(currentQuestionIndex);
        int selected = -1;

        if (option1.isSelected()) selected = 0;
        if (option2.isSelected()) selected = 1;
        if (option3.isSelected()) selected = 2;
        if (option4.isSelected()) selected = 3;

        if (selected == q.getCorrectAnswer()) score++;
    }

    // -------- TIMER METHODS --------
    private void startTimer() {
        timeLeft = timePerQuestion;
        timerLabel.setText("Time Left: " + timeLeft + "s");

        timer = new Timer(1000, e -> {
            timeLeft--;
            timerLabel.setText("Time Left: " + timeLeft + "s");

            if (timeLeft <= 0) {
                ((Timer) e.getSource()).stop();
                checkAnswer(); // auto-check
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.size()) {
                    loadQuestion();
                } else {
                    showResult();
                }
            }
        });
        timer.start();
    }

    private void stopTimer() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    }

    // -------- SHOW RESULT --------
    private void showResult() {
        JOptionPane.showMessageDialog(this,
                "Quiz Finished!\n\n" +
                        "Total Score: " + score + " / " + questions.size()
        );
        System.exit(0);
    }
}
