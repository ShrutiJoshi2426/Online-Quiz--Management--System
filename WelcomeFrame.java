import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WelcomeFrame extends JFrame {

    public WelcomeFrame() {

        setTitle("Quiz Application");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel heading = new JLabel("WELCOME TO JAVA QUIZ", JLabel.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 24));

        JButton startButton = new JButton("Start Quiz");
        startButton.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);

        add(heading, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // -------- CHANGE HERE --------
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // OPEN LOGIN FRAME
                LoginFrame login = new LoginFrame();
                login.setVisible(true);

                // CLOSE WELCOME
                dispose();
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(WelcomeFrame::new);
    }
}
