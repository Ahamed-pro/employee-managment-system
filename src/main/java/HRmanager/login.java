package HRmanager;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

/**
 * HR Manager login screen – redesigned professional dark UI.
 */
public class login extends JFrame {

    private JTextField     txtuname;
    private JPasswordField Passwordp;
    private JCheckBox      chkShow;

    public login() {
        UITheme.applyGlobalDefaults();
        initUI();
    }

    private void initUI() {
        setTitle("HR Portal — Manager Login");
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

        // Back link
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

        // Title
        JLabel title = new JLabel("HR Manager Login");
        title.setFont(UITheme.FONT_TITLE);
        title.setForeground(UITheme.TEXT_PRIMARY);
        title.setAlignmentX(LEFT_ALIGNMENT);
        card.add(title);
        card.add(Box.createVerticalStrut(4));

        JLabel sub = new JLabel("Sign in to access the management portal");
        sub.setFont(UITheme.FONT_SUBTITLE);
        sub.setForeground(UITheme.TEXT_SECONDARY);
        sub.setAlignmentX(LEFT_ALIGNMENT);
        card.add(sub);
        card.add(Box.createVerticalStrut(30));

        // Username
        card.add(fieldLabel("Username"));
        card.add(Box.createVerticalStrut(6));
        txtuname = UITheme.createTextField();
        txtuname.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        txtuname.setAlignmentX(LEFT_ALIGNMENT);
        card.add(txtuname);
        card.add(Box.createVerticalStrut(16));

        // Password
        card.add(fieldLabel("Password"));
        card.add(Box.createVerticalStrut(6));
        Passwordp = new JPasswordField();
        UITheme.stylePasswordField(Passwordp);
        Passwordp.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        Passwordp.setAlignmentX(LEFT_ALIGNMENT);
        card.add(Passwordp);
        card.add(Box.createVerticalStrut(10));

        // Show password
        chkShow = new JCheckBox("Show password");
        UITheme.styleCheckBox(chkShow, "Show password");
        chkShow.setAlignmentX(LEFT_ALIGNMENT);
        chkShow.addActionListener(e ->
            Passwordp.setEchoChar(chkShow.isSelected() ? (char)0 : '•')
        );
        card.add(chkShow);
        card.add(Box.createVerticalStrut(28));

        // Login button
        JButton btnLogin = UITheme.createPrimaryButton("Sign In");
        btnLogin.setAlignmentX(LEFT_ALIGNMENT);
        btnLogin.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        btnLogin.addActionListener(e -> handleLogin());
        card.add(btnLogin);
        card.add(Box.createVerticalStrut(24));

        // Divider
        JSeparator sep = UITheme.createSeparator();
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        card.add(sep);
        card.add(Box.createVerticalStrut(18));

        // Badge
        JLabel badge = UITheme.createBadge("Logged in as: HR Manager");
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
        String uname    = txtuname.getText().trim();
        String password = new String(Passwordp.getPassword());

        if (uname.isEmpty() || password.isEmpty()) {
            showError("Please fill in all fields.");
            return;
        }
        if (uname.equalsIgnoreCase("Ahamed") && password.equals("Ahamed123")) {
            JOptionPane.showMessageDialog(this,
                "Welcome back, " + uname + "!", "Login Successful",
                JOptionPane.INFORMATION_MESSAGE);
            new mainjframe().setVisible(true);
            dispose();
        } else {
            showError("Incorrect username or password. Please try again.");
        }
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Login Failed", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new login().setVisible(true));
    }
}