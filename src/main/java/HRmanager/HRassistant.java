package HRmanager;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

/**
 * Create HR Assistant form – professional dark UI.
 */
public class HRassistant extends JFrame {

    private JTextField     txtaid, txtaname, txtusername;
    private JPasswordField jPassword;
    private JCheckBox      chkShow;

    public HRassistant() {
        UITheme.applyGlobalDefaults();
        initUI();
    }

    private void initUI() {
        setTitle("HR Portal — New HR Assistant");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(520, 560);
        setResizable(false);

        JPanel root = UITheme.createBackground();
        root.setLayout(new GridBagLayout());
        setContentPane(root);
        root.add(buildCard());
        UITheme.centerFrame(this);
    }

    private JPanel buildCard() {
        JPanel card = new JPanel();
        card.setBackground(UITheme.BG_CARD);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(UITheme.BORDER_COLOR, 1, true),
            new EmptyBorder(36, 44, 36, 44)
        ));

        JButton back = backLink(e -> { new mainjframe().setVisible(true); dispose(); });
        card.add(back);
        card.add(Box.createVerticalStrut(16));

        JLabel title = new JLabel("New HR Assistant");
        title.setFont(UITheme.FONT_TITLE);
        title.setForeground(UITheme.TEXT_PRIMARY);
        title.setAlignmentX(LEFT_ALIGNMENT);
        card.add(title);

        JLabel sub = new JLabel("Create a new HR assistant account");
        sub.setFont(UITheme.FONT_SUBTITLE);
        sub.setForeground(UITheme.TEXT_SECONDARY);
        sub.setAlignmentX(LEFT_ALIGNMENT);
        card.add(sub);
        card.add(Box.createVerticalStrut(26));

        // Assistant ID
        card.add(label("Assistant ID  (numeric, 1 – 500 000)"));
        card.add(Box.createVerticalStrut(6));
        txtaid = UITheme.createTextField();
        txtaid.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        txtaid.setAlignmentX(LEFT_ALIGNMENT);
        card.add(txtaid);
        card.add(Box.createVerticalStrut(14));

        // Name
        card.add(label("Full Name"));
        card.add(Box.createVerticalStrut(6));
        txtaname = UITheme.createTextField();
        txtaname.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        txtaname.setAlignmentX(LEFT_ALIGNMENT);
        card.add(txtaname);
        card.add(Box.createVerticalStrut(14));

        // Username
        card.add(label("Username"));
        card.add(Box.createVerticalStrut(6));
        txtusername = UITheme.createTextField();
        txtusername.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        txtusername.setAlignmentX(LEFT_ALIGNMENT);
        card.add(txtusername);
        card.add(Box.createVerticalStrut(14));

        // Password
        card.add(label("Password"));
        card.add(Box.createVerticalStrut(6));
        jPassword = new JPasswordField();
        UITheme.stylePasswordField(jPassword);
        jPassword.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        jPassword.setAlignmentX(LEFT_ALIGNMENT);
        card.add(jPassword);
        card.add(Box.createVerticalStrut(8));

        chkShow = new JCheckBox();
        UITheme.styleCheckBox(chkShow, "Show password");
        chkShow.setAlignmentX(LEFT_ALIGNMENT);
        chkShow.addActionListener(e ->
            jPassword.setEchoChar(chkShow.isSelected() ? (char)0 : '•')
        );
        card.add(chkShow);
        card.add(Box.createVerticalStrut(26));

        JPanel btnRow = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        btnRow.setOpaque(false);
        btnRow.setAlignmentX(LEFT_ALIGNMENT);
        btnRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        JButton cancel = UITheme.createSecondaryButton("Cancel");
        cancel.addActionListener(e -> { new mainjframe().setVisible(true); dispose(); });
        JButton create = UITheme.createPrimaryButton("Create");
        create.addActionListener(e -> handleCreate());
        btnRow.add(cancel);
        btnRow.add(create);
        card.add(btnRow);
        card.add(Box.createVerticalStrut(20));

        JSeparator sep = UITheme.createSeparator();
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        card.add(sep);
        card.add(Box.createVerticalStrut(16));
        card.add(UITheme.createBadge("HR Manager"));

        return card;
    }

    private void handleCreate() {
        String sid  = txtaid.getText().trim();
        String name = txtaname.getText().trim();
        String user = txtusername.getText().trim();
        String pass = new String(jPassword.getPassword());

        if (sid.isEmpty() || name.isEmpty() || user.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled.", "Validation Error",
                JOptionPane.ERROR_MESSAGE); return;
        }
        int id;
        try { id = Integer.parseInt(sid); }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Assistant ID must be a number.", "Input Error",
                JOptionPane.ERROR_MESSAGE); return;
        }
        if (id < 1 || id > 500000) {
            JOptionPane.showMessageDialog(this, "ID must be between 1 and 500 000.", "Range Error",
                JOptionPane.ERROR_MESSAGE); return;
        }
        try {
            Mainclass obj = new Mainclass(id, name, user, pass);
            obj.SavetoHRassistant();
            JOptionPane.showMessageDialog(this,
                "HR Assistant \"" + name + "\" created successfully.", "Success",
                JOptionPane.INFORMATION_MESSAGE);
            Show msg = new Show(); msg.Message();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private JLabel label(String text) {
        JLabel l = new JLabel(text);
        l.setFont(UITheme.FONT_LABEL);
        l.setForeground(UITheme.TEXT_SECONDARY);
        l.setAlignmentX(LEFT_ALIGNMENT);
        return l;
    }

    private JButton backLink(java.awt.event.ActionListener al) {
        JButton b = new JButton("← Back");
        b.setFont(UITheme.FONT_SMALL);
        b.setForeground(UITheme.TEXT_SECONDARY);
        b.setContentAreaFilled(false); b.setBorderPainted(false); b.setFocusPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setAlignmentX(LEFT_ALIGNMENT);
        b.addActionListener(al);
        return b;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HRassistant().setVisible(true));
    }
}