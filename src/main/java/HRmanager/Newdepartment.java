package HRmanager;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

/**
 * New Department form – professional dark UI.
 */
public class Newdepartment extends JFrame {

    private JTextField txtdname;
    private JTextField txtdepartmentpurpose;

    public Newdepartment() {
        UITheme.applyGlobalDefaults();
        initUI();
    }

    private void initUI() {
        setTitle("HR Portal — New Department");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(520, 560);
        setResizable(false);

        JPanel root = UITheme.createBackground();
        root.setLayout(new GridBagLayout());
        setContentPane(root);

        JPanel card = buildCard();
        root.add(card);
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

        // Back
        JButton back = backLink(e -> { new mainjframe().setVisible(true); dispose(); });
        card.add(back);
        card.add(Box.createVerticalStrut(16));

        JLabel title = new JLabel("New Department");
        title.setFont(UITheme.FONT_TITLE);
        title.setForeground(UITheme.TEXT_PRIMARY);
        title.setAlignmentX(LEFT_ALIGNMENT);
        card.add(title);

        JLabel sub = new JLabel("Create a new organisational department");
        sub.setFont(UITheme.FONT_SUBTITLE);
        sub.setForeground(UITheme.TEXT_SECONDARY);
        sub.setAlignmentX(LEFT_ALIGNMENT);
        card.add(sub);
        card.add(Box.createVerticalStrut(28));

        // Department Name
        card.add(label("Department Name"));
        card.add(Box.createVerticalStrut(6));
        txtdname = UITheme.createTextField();
        txtdname.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        txtdname.setAlignmentX(LEFT_ALIGNMENT);
        card.add(txtdname);
        card.add(Box.createVerticalStrut(16));

        // Purpose
        card.add(label("Purpose / Description"));
        card.add(Box.createVerticalStrut(6));
        txtdepartmentpurpose = UITheme.createTextField();
        txtdepartmentpurpose.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        txtdepartmentpurpose.setAlignmentX(LEFT_ALIGNMENT);
        card.add(txtdepartmentpurpose);
        card.add(Box.createVerticalStrut(30));

        // Buttons
        JPanel btnRow = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        btnRow.setOpaque(false);
        btnRow.setAlignmentX(LEFT_ALIGNMENT);
        btnRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        JButton btnCancel = UITheme.createSecondaryButton("Cancel");
        btnCancel.addActionListener(e -> { new mainjframe().setVisible(true); dispose(); });

        JButton btnCreate = UITheme.createPrimaryButton("Create");
        btnCreate.addActionListener(e -> handleCreate());

        btnRow.add(btnCancel);
        btnRow.add(btnCreate);
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
        String dname   = txtdname.getText().trim();
        String purpose = txtdepartmentpurpose.getText().trim();
        if (dname.isEmpty() || purpose.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled.", "Validation Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            Mainclass obj = new Mainclass(0, null, dname, purpose, null, 0);
            obj.SavetoDepartment();
            JOptionPane.showMessageDialog(this, "Department \"" + dname + "\" created successfully.",
                "Success", JOptionPane.INFORMATION_MESSAGE);
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
        SwingUtilities.invokeLater(() -> new Newdepartment().setVisible(true));
    }
}