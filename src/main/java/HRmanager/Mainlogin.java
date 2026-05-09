package HRmanager;

import HRAssistant.loginAssistant;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Main login screen – choose HR Manager or HR Assistant.
 * Redesigned with a professional dark-corporate aesthetic.
 */
public class Mainlogin extends JFrame {

    public Mainlogin() {
        UITheme.applyGlobalDefaults();
        initUI();
    }

    private void initUI() {
        setTitle("HR Portal — Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(460, 520);
        setResizable(false);

        // ── Root background ──────────────────────────────────────────────
        JPanel root = UITheme.createBackground();
        root.setLayout(new GridBagLayout());
        setContentPane(root);

        // ── Centre card ──────────────────────────────────────────────────
        JPanel card = new JPanel();
        card.setBackground(UITheme.BG_CARD);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(UITheme.BORDER_COLOR, 1, true),
            new EmptyBorder(44, 48, 44, 48)
        ));

        // Logo / icon row
        JLabel icon = new JLabel("HR");
        icon.setFont(new Font("Segoe UI Semibold", Font.BOLD, 26));
        icon.setForeground(UITheme.ACCENT_BLUE);
        icon.setAlignmentX(CENTER_ALIGNMENT);
        JPanel iconCircle = new JPanel() {
            @Override protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(56, 139, 253, 25));
                g2.fillOval(0, 0, 64, 64);
                g2.setColor(UITheme.ACCENT_BLUE);
                g2.setStroke(new BasicStroke(1.5f));
                g2.drawOval(1, 1, 62, 62);
            }
        };
        iconCircle.setLayout(new GridBagLayout());
        iconCircle.setPreferredSize(new Dimension(64, 64));
        iconCircle.setMaximumSize(new Dimension(64, 64));
        iconCircle.setOpaque(false);
        iconCircle.add(icon);
        iconCircle.setAlignmentX(CENTER_ALIGNMENT);
        card.add(iconCircle);
        card.add(Box.createVerticalStrut(20));

        // Title
        JLabel title = new JLabel("Welcome Back");
        title.setFont(UITheme.FONT_TITLE);
        title.setForeground(UITheme.TEXT_PRIMARY);
        title.setAlignmentX(CENTER_ALIGNMENT);
        card.add(title);
        card.add(Box.createVerticalStrut(6));

        JLabel subtitle = new JLabel("Select your role to continue");
        subtitle.setFont(UITheme.FONT_SUBTITLE);
        subtitle.setForeground(UITheme.TEXT_SECONDARY);
        subtitle.setAlignmentX(CENTER_ALIGNMENT);
        card.add(subtitle);
        card.add(Box.createVerticalStrut(36));

        // Divider
        card.add(makeDivider("SIGN IN AS"));
        card.add(Box.createVerticalStrut(20));

        // HR Manager button
        JButton btnManager = buildRoleButton(
            "HR Manager",
            "Full system access · manage employees & staff",
            "⬡"
        );
        btnManager.addActionListener(e -> {
            new login().setVisible(true);
            dispose();
        });
        card.add(btnManager);
        card.add(Box.createVerticalStrut(12));

        // HR Assistant button
        JButton btnAssistant = buildRoleButton(
            "HR Assistant",
            "Read-only access · search employee records",
            "◈"
        );
        btnAssistant.addActionListener(e -> {
            new loginAssistant().setVisible(true);
            dispose();
        });
        card.add(btnAssistant);
        card.add(Box.createVerticalStrut(32));

        // Footer
        JLabel footer = new JLabel("HR Management System  ·  v2.0");
        footer.setFont(UITheme.FONT_SMALL);
        footer.setForeground(UITheme.TEXT_MUTED);
        footer.setAlignmentX(CENTER_ALIGNMENT);
        card.add(footer);

        root.add(card);
        UITheme.centerFrame(this);
    }

    /** A wide role-selection button with icon + description */
    private JButton buildRoleButton(String label, String desc, String symbol) {
        JButton btn = new JButton() {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Color bg = getModel().isRollover()
                    ? new Color(56, 139, 253, 22)
                    : UITheme.BG_INPUT;
                g2.setColor(bg);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                Color border = getModel().isRollover() ? UITheme.ACCENT_BLUE : UITheme.BORDER_COLOR;
                g2.setColor(border);
                g2.setStroke(new BasicStroke(1f));
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 10, 10);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setLayout(new BorderLayout(14, 0));
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 68));
        btn.setBorder(new EmptyBorder(14, 18, 14, 18));

        // Left symbol
        JLabel sym = new JLabel(symbol);
        sym.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        sym.setForeground(UITheme.ACCENT_BLUE);
        sym.setPreferredSize(new Dimension(34, 34));
        sym.setHorizontalAlignment(SwingConstants.CENTER);
        btn.add(sym, BorderLayout.WEST);

        // Text panel
        JPanel text = new JPanel();
        text.setOpaque(false);
        text.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS));
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI Semibold", Font.BOLD, 13));
        lbl.setForeground(UITheme.TEXT_PRIMARY);
        JLabel dsc = new JLabel(desc);
        dsc.setFont(UITheme.FONT_SMALL);
        dsc.setForeground(UITheme.TEXT_SECONDARY);
        text.add(lbl);
        text.add(Box.createVerticalStrut(2));
        text.add(dsc);
        btn.add(text, BorderLayout.CENTER);

        // Arrow
        JLabel arrow = new JLabel("›");
        arrow.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        arrow.setForeground(UITheme.TEXT_MUTED);
        btn.add(arrow, BorderLayout.EAST);

        return btn;
    }

    private JPanel makeDivider(String text) {
        JPanel row = new JPanel(new BorderLayout(10, 0));
        row.setOpaque(false);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
        JSeparator l = new JSeparator(); l.setForeground(UITheme.BORDER_COLOR);
        JSeparator r = new JSeparator(); r.setForeground(UITheme.BORDER_COLOR);
        JLabel lbl = new JLabel(text);
        lbl.setFont(UITheme.FONT_BADGE);
        lbl.setForeground(UITheme.TEXT_MUTED);
        row.add(l, BorderLayout.WEST);
        row.add(lbl, BorderLayout.CENTER);
        row.add(r, BorderLayout.EAST);
        return row;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Mainlogin().setVisible(true));
    }
}