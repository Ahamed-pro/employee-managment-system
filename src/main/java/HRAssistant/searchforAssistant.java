package HRAssistant;

import HRmanager.UITheme;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;

/**
 * HR Assistant – Search Employee screen, professional dark UI.
 */
public class searchforAssistant extends JFrame {

    private JTextField        txtsearch;
    private JTable            jTable1;
    private DefaultTableModel tableModel;
    private JLabel            lblCount;

    public searchforAssistant() {
        UITheme.applyGlobalDefaults();
        initUI();
    }

    private void initUI() {
        setTitle("HR Portal — Employee Search (Assistant)");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(680, 520);
        setResizable(false);

        JPanel root = UITheme.createBackground();
        root.setLayout(new BorderLayout());
        setContentPane(root);

        // ── Header ───────────────────────────────────────────────────────
        JPanel header = new JPanel();
        header.setOpaque(false);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBorder(new EmptyBorder(28, 36, 16, 36));

        JButton back = backLink(e -> { new loginAssistant().setVisible(true); dispose(); });
        back.setAlignmentX(LEFT_ALIGNMENT);
        header.add(back);
        header.add(Box.createVerticalStrut(14));

        JPanel titleRow = new JPanel(new BorderLayout());
        titleRow.setOpaque(false);

        JLabel title = new JLabel("Employee Search");
        title.setFont(UITheme.FONT_TITLE);
        title.setForeground(UITheme.TEXT_PRIMARY);
        titleRow.add(title, BorderLayout.WEST);
        titleRow.add(UITheme.createBadge("HR Assistant"), BorderLayout.EAST);
        header.add(titleRow);
        header.add(Box.createVerticalStrut(4));

        JLabel sub = new JLabel("Search by ID, name, department or designation");
        sub.setFont(UITheme.FONT_SUBTITLE);
        sub.setForeground(UITheme.TEXT_SECONDARY);
        sub.setAlignmentX(LEFT_ALIGNMENT);
        header.add(sub);
        header.add(Box.createVerticalStrut(18));

        // Search bar
        JPanel searchRow = new JPanel(new BorderLayout(10, 0));
        searchRow.setOpaque(false);
        searchRow.setAlignmentX(LEFT_ALIGNMENT);

        txtsearch = UITheme.createTextField();
        searchRow.add(txtsearch, BorderLayout.CENTER);

        JButton btnSearch = UITheme.createPrimaryButton("Search");
        btnSearch.setPreferredSize(new Dimension(90, 36));
        btnSearch.addActionListener(e -> handleSearch());
        searchRow.add(btnSearch, BorderLayout.EAST);
        header.add(searchRow);

        root.add(header, BorderLayout.NORTH);

        // ── Results ───────────────────────────────────────────────────────
        JPanel centre = new JPanel(new BorderLayout());
        centre.setOpaque(false);
        centre.setBorder(new EmptyBorder(0, 36, 0, 36));

        lblCount = new JLabel("  Enter a search term to find employees");
        lblCount.setFont(UITheme.FONT_SMALL);
        lblCount.setForeground(UITheme.TEXT_MUTED);
        lblCount.setBorder(new EmptyBorder(0, 0, 8, 0));
        centre.add(lblCount, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(
            new String[]{"Employee ID", "Full Name", "Department", "Designation"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        jTable1 = new JTable(tableModel);
        UITheme.styleTable(jTable1);

        JScrollPane scroll = new JScrollPane(jTable1);
        UITheme.styleScrollPane(scroll);
        centre.add(scroll, BorderLayout.CENTER);
        root.add(centre, BorderLayout.CENTER);

        // ── Footer ────────────────────────────────────────────────────────
        JPanel footer = new JPanel(new BorderLayout());
        footer.setOpaque(false);
        footer.setBorder(new EmptyBorder(12, 36, 20, 36));

        JSeparator sep = UITheme.createSeparator();
        footer.add(sep, BorderLayout.NORTH);

        JPanel footRow = new JPanel(new BorderLayout());
        footRow.setOpaque(false);
        footRow.setBorder(new EmptyBorder(12, 0, 0, 0));

        JLabel note = new JLabel("Read-only access");
        note.setFont(UITheme.FONT_SMALL);
        note.setForeground(UITheme.TEXT_MUTED);
        footRow.add(note, BorderLayout.WEST);

        JButton btnClear = UITheme.createSecondaryButton("Clear");
        btnClear.setPreferredSize(new Dimension(90, 32));
        btnClear.addActionListener(e -> {
            tableModel.setRowCount(0);
            txtsearch.setText("");
            lblCount.setText("  Enter a search term to find employees");
        });
        footRow.add(btnClear, BorderLayout.EAST);
        footer.add(footRow, BorderLayout.CENTER);
        root.add(footer, BorderLayout.SOUTH);

        UITheme.centerFrame(this);
    }

    private void handleSearch() {
        String term = txtsearch.getText().trim().toLowerCase();
        if (term.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a search term.", "Warning",
                JOptionPane.WARNING_MESSAGE); return;
        }
        tableModel.setRowCount(0);
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("Employee_details"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length >= 4) {
                    String eid   = parts[0].split(": ")[1];
                    String ename = parts[1].split(": ")[1];
                    String dept  = parts[2].split(": ")[1];
                    String desig = parts[3].split(": ")[1];
                    if (eid.toLowerCase().contains(term) || ename.toLowerCase().contains(term)
                        || dept.toLowerCase().contains(term) || desig.toLowerCase().contains(term)) {
                        tableModel.addRow(new Object[]{eid, ename, dept, desig});
                        count++;
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading data: " + e.getMessage(), "Error",
                JOptionPane.ERROR_MESSAGE);
        }
        if (count == 0)
           lblCount.setText("  No employees found matching \"" + txtsearch.getText().trim() + "\"");
        else
            lblCount.setText("  " + count + " result" + (count > 1 ? "s" : "") + " found");
    }

    private JButton backLink(java.awt.event.ActionListener al) {
        JButton b = new JButton("← Back");
        b.setFont(UITheme.FONT_SMALL);
        b.setForeground(UITheme.TEXT_SECONDARY);
        b.setContentAreaFilled(false); b.setBorderPainted(false); b.setFocusPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.addActionListener(al);
        return b;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new searchforAssistant().setVisible(true));
    }
}