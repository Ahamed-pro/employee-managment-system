package HRAssistant;

import HRmanager.Mainlogin;
import HRmanager.UITheme;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.io.*;

/**
 * HR Assistant login screen – professional dark UI.
 */
public class loginAssistant extends JFrame {

    private JTextField     txthrasname;
    private JPasswordField jPasswordhraspassword;
    private JCheckBox      chkShow;

    public loginAssistant() {
        UITheme.applyGlobalDefaults();
        initUI();
    }

    private void initUI() {
        setTitle("HR Portal — Assistant Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(460, 540);
        setResizable(false);

        JPanel root = UITheme.createBackground();
        root.setLayout(new GridBagLayout());
        setContentPane(root);

        JPanel card = new JPanel();
        card.setBackground(UITheme.BG_CARD);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(UITheme.BORDER_COLOR, 1, true),
            new EmptyBorder(40, 48, 40, 48)
        ));

        // Back
        JButton btnBack = new JButton("← Back");
        btnBack.setFont(UITheme.FONT_SMALL);
        btnBack.setForeground(UITheme.TEXT_SECONDARY);
        btnBack.setContentAreaFilled(false); btnBack.setBorderPainted(false);
        btnBack.setFocusPainted(false);
        btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBack.setAlignmentX(LEFT_ALIGNMENT);
        btnBack.addActionListener(e -> { new Mainlogin().setVisible(true); dispose(); });
        card.add(btnBack);
        card.add(Box.createVerticalStrut(16));

        JLabel title = new JLabel("HR Assistant Login");
        title.setFont(UITheme.FONT_TITLE);
        title.setForeground(UITheme.TEXT_PRIMARY);
        title.setAlignmentX(LEFT_ALIGNMENT);
        card.add(title);
        card.add(Box.createVerticalStrut(4));

        JLabel sub = new JLabel("Sign in to access the employee search portal");
        sub.setFont(UITheme.FONT_SUBTITLE);
        sub.setForeground(UITheme.TEXT_SECONDARY);
        sub.setAlignmentX(LEFT_ALIGNMENT);
        card.add(sub);
        card.add(Box.createVerticalStrut(30));

        // Username
        card.add(fieldLabel("Username"));
        card.add(Box.createVerticalStrut(6));
        txthrasname = UITheme.createTextField();
        txthrasname.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        txthrasname.setAlignmentX(LEFT_ALIGNMENT);
        card.add(txthrasname);
        card.add(Box.createVerticalStrut(16));

        // Password
        card.add(fieldLabel("Password"));
        card.add(Box.createVerticalStrut(6));
        jPasswordhraspassword = new JPasswordField();
        UITheme.stylePasswordField(jPasswordhraspassword);
        jPasswordhraspassword.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        jPasswordhraspassword.setAlignmentX(LEFT_ALIGNMENT);
        card.add(jPasswordhraspassword);
        card.add(Box.createVerticalStrut(10));

        chkShow = new JCheckBox();
        UITheme.styleCheckBox(chkShow, "Show password");
        chkShow.setAlignmentX(LEFT_ALIGNMENT);
        chkShow.addActionListener(e ->
            jPasswordhraspassword.setEchoChar(chkShow.isSelected() ? (char)0 : '•')
        );
        card.add(chkShow);
        card.add(Box.createVerticalStrut(28));

        JButton btnLogin = UITheme.createPrimaryButton("Sign In");
        btnLogin.setAlignmentX(LEFT_ALIGNMENT);
        btnLogin.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        btnLogin.addActionListener(e -> handleLogin());
        card.add(btnLogin);
        card.add(Box.createVerticalStrut(24));

        JSeparator sep = UITheme.createSeparator();
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        card.add(sep);
        card.add(Box.createVerticalStrut(18));

        JLabel badge = UITheme.createBadge("Logged in as: HR Assistant");
        badge.setAlignmentX(LEFT_ALIGNMENT);
        card.add(badge);

        root.add(card);
        UITheme.centerFrame(this);
    }

    private JLabel fieldLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(UITheme.FONT_LABEL);
        lbl.setForeground(UITheme.TEXT_SECONDARY);
        lbl.setAlignmentX(LEFT_ALIGNMENT);
        return lbl;
    }

    private void handleLogin() {
        String user = txthrasname.getText().trim();
        String pass = new String(jPasswordhraspassword.getPassword());

        if (user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean valid = false;
        try (BufferedReader reader = new BufferedReader(new FileReader("HR Assistant_details"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Username : " + user) && line.contains("Password : " + pass)) {
                    valid = true; break;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error reading credentials: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (valid) {
            JOptionPane.showMessageDialog(this, "Welcome, " + user + "!", "Login Successful",
                JOptionPane.INFORMATION_MESSAGE);
            new searchforAssistant().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Incorrect username or password.", "Login Failed",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new loginAssistant().setVisible(true));
    }
}