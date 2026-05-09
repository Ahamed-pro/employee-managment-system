package HRmanager;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

/**
 * New Designation form – professional dark UI.
 */
public class Designation extends JFrame {

    private JTextField txtdesignationname;
    private JTextField txtdesignationid;

    public Designation() {
        UITheme.applyGlobalDefaults();
        initUI();
    }

    private void initUI() {
        setTitle("HR Portal — New Designation");
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

        JLabel title = new JLabel("New Designation");
        title.setFont(UITheme.FONT_TITLE);
        title.setForeground(UITheme.TEXT_PRIMARY);
        title.setAlignmentX(LEFT_ALIGNMENT);
        card.add(title);

        JLabel sub = new JLabel("Define a new job designation");
        sub.setFont(UITheme.FONT_SUBTITLE);
        sub.setForeground(UITheme.TEXT_SECONDARY);
        sub.setAlignmentX(LEFT_ALIGNMENT);
        card.add(sub);
        card.add(Box.createVerticalStrut(28));

        // Designation Name
        card.add(label("Designation Name"));
        card.add(Box.createVerticalStrut(6));
        txtdesignationname = UITheme.createTextField();
        txtdesignationname.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        txtdesignationname.setAlignmentX(LEFT_ALIGNMENT);
        card.add(txtdesignationname);
        card.add(Box.createVerticalStrut(16));

        // Designation ID
        card.add(label("Designation ID  (numeric, 1 – 500 000)"));
        card.add(Box.createVerticalStrut(6));
        txtdesignationid = UITheme.createTextField();
        txtdesignationid.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        txtdesignationid.setAlignmentX(LEFT_ALIGNMENT);
        card.add(txtdesignationid);
        card.add(Box.createVerticalStrut(30));

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
        String name = txtdesignationname.getText().trim();
        String ids  = txtdesignationid.getText().trim();
        if (name.isEmpty() || ids.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled.", "Validation Error",
                JOptionPane.ERROR_MESSAGE); return;
        }
        int id;
        try { id = Integer.parseInt(ids); }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Designation ID must be a number.", "Input Error",
                JOptionPane.ERROR_MESSAGE); return;
        }
        if (id < 1 || id > 500000) {
            JOptionPane.showMessageDialog(this, "ID must be between 1 and 500 000.", "Range Error",
                JOptionPane.ERROR_MESSAGE); return;
        }
        try {
            Mainclass obj = new Mainclass(0, null, null, null, name, id);
            obj.SavetoDesignation();
            JOptionPane.showMessageDialog(this, "Designation \"" + name + "\" created successfully.",
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
        SwingUtilities.invokeLater(() -> new Designation().setVisible(true));
    }
}