import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginFrame() {
        setTitle("Java Quiz Login");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 10, 10));

        // Username panel
        JPanel userPanel = new JPanel(new FlowLayout());
        userPanel.add(new JLabel("Username:"));
        usernameField = new JTextField(15);
        userPanel.add(usernameField);
        add(userPanel);

        // Password panel
        JPanel passPanel = new JPanel(new FlowLayout());
        passPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField(15);
        passPanel.add(passwordField);
        add(passPanel);

        // Login button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        loginButton = new JButton("Login");
        buttonPanel.add(loginButton);
        add(buttonPanel);

        // Empty panel for spacing
        add(new JPanel());

        // -------- LOGIN ACTION --------
        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Please enter both username and password!", 
                    "Login Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Check login in database
            boolean success = UserAuthJDBC.login(username, password);

            if (success) {
                JOptionPane.showMessageDialog(this, 
                    "Login Successful! Welcome " + username + "!", 
                    "Welcome", JOptionPane.INFORMATION_MESSAGE);

                dispose();          // close login
                new QuizFrame();    // open quiz
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Invalid username or password. Please try again!", 
                    "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginFrame::new);
    }
}
