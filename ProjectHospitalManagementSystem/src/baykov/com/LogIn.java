package baykov.com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogIn implements ActionListener {

    private final JFrame frame = new JFrame("Log In");
    private final JTextField usernameText = new JTextField();
    private final JPasswordField passwordField = new JPasswordField();
    private final JButton logInButton = new JButton("Log in");
    private final JButton closeButton = new JButton("Close");

    public LogIn() {

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.getContentPane().setBackground(new Color(160, 216, 241).brighter());
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JLabel label = new JLabel();
        label.setIcon(new ImageIcon("images/login.jpg"));
        label.setBounds(40, 60, 200, 200);

        JLabel username = new JLabel("Username");
        username.setBounds(300, 100, 100, 25);
        username.setFont(new Font("Ink Free", Font.BOLD, 15));

        usernameText.setBounds(420, 100, 300, 25);
        usernameText.setFont(new Font("Ink Free", Font.PLAIN, 15));

        JLabel password = new JLabel("Password");
        password.setBounds(300, 150, 100, 25);
        password.setFont(new Font("Ink Free", Font.BOLD, 15));

        passwordField.setBounds(420, 150, 300, 25);
        passwordField.setFont(new Font("Ink Free", Font.PLAIN, 15));

        closeButton.setBounds(600, 200, 120, 30);
        closeButton.setIcon(new ImageIcon("images/closeicon.png"));
        closeButton.setFont(new Font("Ink Free", Font.BOLD, 15));
        closeButton.setFocusable(false);
        closeButton.addActionListener(this);

        logInButton.setBounds(420, 200, 120, 30);
        logInButton.setIcon(new ImageIcon("images/loginicon.png"));
        logInButton.setFont(new Font("Ink Free", Font.BOLD, 15));
        logInButton.setFocusable(false);
        logInButton.addActionListener(this);

        frame.add(logInButton);
        frame.add(closeButton);
        frame.add(passwordField);
        frame.add(password);
        frame.add(usernameText);
        frame.add(username);
        frame.add(label);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == logInButton) {
            if (usernameText.getText().isEmpty() || passwordField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter Username and Password");
            } else if (usernameText.getText().equals("root") && passwordField.getText().equals("123")) {
                new MainFrame();
                this.frame.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Wrong Username or Password");
                usernameText.setText(null);
                passwordField.setText(null);
            }
        }

        if (e.getSource() == closeButton) {
            this.frame.dispose();
        }

    }
}
