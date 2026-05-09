package HRmanager;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

/**
 * HR Manager dashboard – professional dark UI with card-based navigation.
 */
public class mainjframe extends JFrame {

    public mainjframe() {
        UITheme.applyGlobalDefaults();
        initUI();
    }

    private void initUI() {
        setTitle("HR Portal — Manager Dashboard");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(620, 560);
        setResizable(false);

        JPanel root = UITheme.createBackground();
        root.setLayout(new BorderLayout());
        setContentPane(root);

        // ── Top header bar ────────────────────────────────────────────────
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.setBorder(new EmptyBorder(28, 36, 0, 36));

        JPanel titleRow = new JPanel(new BorderLayout(0, 4));
        titleRow.setOpaque(false);

        JLabel title = new JLabel("Dashboard");
        title.setFont(UITheme.FONT_TITLE);
        title.setForeground(UITheme.TEXT_PRIMARY);

        JLabel sub = new JLabel("What would you like to do today?");
        sub.setFont(UITheme.FONT_SUBTITLE);
        sub.setForeground(UITheme.TEXT_SECONDARY);

        titleRow.add(title, BorderLayout.NORTH);
        titleRow.add(sub,   BorderLayout.SOUTH);

        JLabel badge = UITheme.createBadge("HR Manager");
        badge.setHorizontalAlignment(SwingConstants.RIGHT);

        header.add(titleRow, BorderLayout.WEST);
        header.add(badge,    BorderLayout.EAST);
        root.add(header, BorderLayout.NORTH);

        // ── Grid of nav cards ─────────────────────────────────────────────
        JPanel grid = new JPanel(new GridLayout(3, 2, 14, 14));
        grid.setOpaque(false);
        grid.setBorder(new EmptyBorder(24, 36, 16, 36));

        grid.add(navCard("New Department",
            "Create a department unit",
            "◻", e -> { new Newdepartment().setVisible(true); dispose(); }));

        grid.add(navCard("New Designation",
            "Define a job designation",
            "◈", e -> { new Designation().setVisible(true); dispose(); }));

        grid.add(navCard("Add Employee",
            "Register a new employee",
            "＋", e -> { new addemployee().setVisible(true); dispose(); }));

        grid.add(navCard("Search Employee",
            "Look up employee records",
            "⌕", e -> { new searchemployee().setVisible(true); dispose(); }));

        grid.add(navCard("HR Assistant",
            "Create an assistant account",
            "◎", e -> { new HRassistant().setVisible(true); dispose(); }));

        // Empty slot – could be future feature
        JPanel placeholder = new JPanel();
        placeholder.setOpaque(false);
        grid.add(placeholder);

        root.add(grid, BorderLayout.CENTER);

        // ── Footer ────────────────────────────────────────────────────────
        JPanel footer = new JPanel(new BorderLayout());
        footer.setOpaque(false);
        footer.setBorder(new EmptyBorder(0, 36, 20, 36));

        JSeparator sep = UITheme.createSeparator();
        footer.add(sep, BorderLayout.NORTH);

        JPanel footerRow = new JPanel(new BorderLayout());
        footerRow.setOpaque(false);
        footerRow.setBorder(new EmptyBorder(12, 0, 0, 0));

        JLabel sys = new JLabel("HR Management System  ·  v2.0");
        sys.setFont(UITheme.FONT_SMALL);
        sys.setForeground(UITheme.TEXT_MUTED);

        JButton btnBack = UITheme.createSecondaryButton("Sign Out");
        btnBack.setPreferredSize(new Dimension(100, 32));
        btnBack.addActionListener(e -> { new login().setVisible(true); dispose(); });

        footerRow.add(sys,     BorderLayout.WEST);
        footerRow.add(btnBack, BorderLayout.EAST);
        footer.add(footerRow, BorderLayout.CENTER);

        root.add(footer, BorderLayout.SOUTH);
        UITheme.centerFrame(this);
    }

    /** Individual navigation card */
    private JPanel navCard(String title, String desc, String symbol, ActionListener action) {
        JPanel card = new JPanel() {
            private boolean hovered = false;
            { 
                addMouseListener(new MouseAdapter() {
                    @Override public void mouseEntered(MouseEvent e) { hovered = true;  repaint(); }
                    @Override public void mouseExited (MouseEvent e) { hovered = false; repaint(); }
                    @Override public void mouseClicked(MouseEvent e) { action.actionPerformed(null); }
                });
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Color bg = hovered ? new Color(56, 139, 253, 15) : UITheme.BG_CARD;
                g2.setColor(bg);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                Color bc = hovered ? UITheme.ACCENT_BLUE : UITheme.BORDER_COLOR;
                g2.setColor(bc);
                g2.setStroke(new BasicStroke(1f));
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 12, 12);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        card.setOpaque(false);
        card.setLayout(new BorderLayout(0, 6));
        card.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Top row: symbol + arrow
        JPanel topRow = new JPanel(new BorderLayout());
        topRow.setOpaque(false);

        JLabel sym = new JLabel(symbol);
        sym.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        sym.setForeground(UITheme.ACCENT_BLUE);
        topRow.add(sym, BorderLayout.WEST);

        JLabel arrow = new JLabel("›");
        arrow.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        arrow.setForeground(UITheme.TEXT_MUTED);
        topRow.add(arrow, BorderLayout.EAST);
        card.add(topRow, BorderLayout.NORTH);

        // Bottom text
        JPanel text = new JPanel();
        text.setOpaque(false);
        text.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS));
        text.setBorder(new EmptyBorder(8, 0, 0, 0));

        JLabel lbl = new JLabel(title);
        lbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
        lbl.setForeground(UITheme.TEXT_PRIMARY);
        text.add(lbl);
        text.add(Box.createVerticalStrut(3));

        JLabel dsc = new JLabel(desc);
        dsc.setFont(UITheme.FONT_SMALL);
        dsc.setForeground(UITheme.TEXT_SECONDARY);
        text.add(dsc);

        card.add(text, BorderLayout.CENTER);
        return card;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new mainjframe().setVisible(true));
    }
}