package HRmanager;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.io.*;

/**
 * Add Employee form – professional dark UI.
 */
public class addemployee extends JFrame {

    private JTextField txteid, txtename, txtedepartment, txtedesignation;

    public addemployee() {
        UITheme.applyGlobalDefaults();
        initUI();
    }

    private void initUI() {
        setTitle("HR Portal — Add Employee");
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

        JLabel title = new JLabel("Add Employee");
        title.setFont(UITheme.FONT_TITLE);
        title.setForeground(UITheme.TEXT_PRIMARY);
        title.setAlignmentX(LEFT_ALIGNMENT);
        card.add(title);

        JLabel sub = new JLabel("Register a new employee record");
        sub.setFont(UITheme.FONT_SUBTITLE);
        sub.setForeground(UITheme.TEXT_SECONDARY);
        sub.setAlignmentX(LEFT_ALIGNMENT);
        card.add(sub);
        card.add(Box.createVerticalStrut(26));

        // Employee ID
        card.add(label("Employee ID  (numeric)"));
        card.add(Box.createVerticalStrut(6));
        txteid = UITheme.createTextField();
        txteid.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        txteid.setAlignmentX(LEFT_ALIGNMENT);
        card.add(txteid);
        card.add(Box.createVerticalStrut(14));

        // Name
        card.add(label("Full Name"));
        card.add(Box.createVerticalStrut(6));
        txtename = UITheme.createTextField();
        txtename.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        txtename.setAlignmentX(LEFT_ALIGNMENT);
        card.add(txtename);
        card.add(Box.createVerticalStrut(14));

        // Department
        card.add(label("Department"));
        card.add(Box.createVerticalStrut(6));
        txtedepartment = UITheme.createTextField();
        txtedepartment.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        txtedepartment.setAlignmentX(LEFT_ALIGNMENT);
        card.add(txtedepartment);
        card.add(Box.createVerticalStrut(14));

        // Designation
        card.add(label("Designation"));
        card.add(Box.createVerticalStrut(6));
        txtedesignation = UITheme.createTextField();
        txtedesignation.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        txtedesignation.setAlignmentX(LEFT_ALIGNMENT);
        card.add(txtedesignation);
        card.add(Box.createVerticalStrut(28));

        // Hint
        JLabel hint = new JLabel("* Department & Designation must already exist in the system.");
        hint.setFont(UITheme.FONT_SMALL);
        hint.setForeground(UITheme.TEXT_MUTED);
        hint.setAlignmentX(LEFT_ALIGNMENT);
        card.add(hint);
        card.add(Box.createVerticalStrut(16));

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
        String sid  = txteid.getText().trim();
        String name = txtename.getText().trim();
        String dept = txtedepartment.getText().trim();
        String desig = txtedesignation.getText().trim();

        if (sid.isEmpty() || name.isEmpty() || dept.isEmpty() || desig.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled.", "Validation Error",
                JOptionPane.ERROR_MESSAGE); return;
        }

        int id;
        try { id = Integer.parseInt(sid); }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Employee ID must be a number.", "Input Error",
                JOptionPane.ERROR_MESSAGE); return;
        }

        // Validate department
        if (!existsInFile("Department_details", dept)) {
            JOptionPane.showMessageDialog(this,
                "Department \"" + dept + "\" not found. Create it first.", "Not Found",
                JOptionPane.ERROR_MESSAGE); return;
        }
        // Validate designation
        if (!existsInFile("Designation_details", desig)) {
            JOptionPane.showMessageDialog(this,
                "Designation \"" + desig + "\" not found. Create it first.", "Not Found",
                JOptionPane.ERROR_MESSAGE); return;
        }

        try {
            Mainclass obj = new Mainclass(id, name, dept, null, desig, 0);
            obj.saveEmployeeDetails(obj);
            JOptionPane.showMessageDialog(this,
                "Employee \"" + name + "\" created successfully.", "Success",
                JOptionPane.INFORMATION_MESSAGE);
            Show msg = new Show(); msg.Message();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean existsInFile(String fileName, String term) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null)
                if (line.toLowerCase().contains(term.toLowerCase())) return true;
        } catch (Exception ignored) {}
        return false;
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
        SwingUtilities.invokeLater(() -> new addemployee().setVisible(true));
    }
}