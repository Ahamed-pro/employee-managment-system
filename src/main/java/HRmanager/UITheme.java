package HRmanager;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
 * Central UI theme for the HR Management System.
 * All screens use this class for consistent, professional styling.
 */
public class UITheme {

    // ── Colour Palette ──────────────────────────────────────────────────────
    public static final Color BG_DARK        = new Color(13, 17, 23);       // near-black
    public static final Color BG_CARD        = new Color(22, 27, 34);       // card surface
    public static final Color BG_INPUT       = new Color(30, 36, 46);       // input fields
    public static final Color BORDER_COLOR   = new Color(48, 56, 70);       // subtle borders
    public static final Color ACCENT_BLUE    = new Color(56, 139, 253);     // primary action
    public static final Color ACCENT_HOVER   = new Color(79, 160, 255);     // hover state
    public static final Color ACCENT_SUCCESS = new Color(35, 197, 98);      // success
    public static final Color ACCENT_DANGER  = new Color(248, 81, 73);      // error / danger
    public static final Color TEXT_PRIMARY   = new Color(230, 237, 243);    // headings
    public static final Color TEXT_SECONDARY = new Color(139, 148, 158);    // labels
    public static final Color TEXT_MUTED     = new Color(88, 96, 105);      // placeholders

    // ── Typography ──────────────────────────────────────────────────────────
    public static final Font FONT_TITLE    = new Font("Segoe UI Semibold", Font.BOLD,   22);
    public static final Font FONT_SUBTITLE = new Font("Segoe UI",          Font.PLAIN,  13);
    public static final Font FONT_LABEL    = new Font("Segoe UI",          Font.PLAIN,  12);
    public static final Font FONT_INPUT    = new Font("Segoe UI",          Font.PLAIN,  13);
    public static final Font FONT_BUTTON   = new Font("Segoe UI Semibold", Font.BOLD,   12);
    public static final Font FONT_SMALL    = new Font("Segoe UI",          Font.PLAIN,  11);
    public static final Font FONT_BADGE    = new Font("Segoe UI Semibold", Font.BOLD,   10);

    // ── Bootstrap ───────────────────────────────────────────────────────────
    public static void applyGlobalDefaults() {
        UIManager.put("Panel.background",          BG_DARK);
        UIManager.put("OptionPane.background",     BG_CARD);
        UIManager.put("OptionPane.messageForeground", TEXT_PRIMARY);
        UIManager.put("Button.background",         BG_INPUT);
        UIManager.put("Button.foreground",         TEXT_PRIMARY);
        UIManager.put("Label.foreground",          TEXT_PRIMARY);
        UIManager.put("TextField.background",      BG_INPUT);
        UIManager.put("TextField.foreground",      TEXT_PRIMARY);
        UIManager.put("TextField.caretForeground", ACCENT_BLUE);
        UIManager.put("PasswordField.background",  BG_INPUT);
        UIManager.put("PasswordField.foreground",  TEXT_PRIMARY);
        UIManager.put("Table.background",          BG_INPUT);
        UIManager.put("Table.foreground",          TEXT_PRIMARY);
        UIManager.put("Table.gridColor",           BORDER_COLOR);
        UIManager.put("TableHeader.background",    BG_CARD);
        UIManager.put("TableHeader.foreground",    TEXT_SECONDARY);
        UIManager.put("ScrollPane.background",     BG_CARD);
        UIManager.put("Viewport.background",       BG_INPUT);
        UIManager.put("CheckBox.background",       BG_CARD);
        UIManager.put("CheckBox.foreground",       TEXT_PRIMARY);
        UIManager.put("RadioButton.background",    BG_CARD);
        UIManager.put("RadioButton.foreground",    TEXT_SECONDARY);
    }

    // ── Factory helpers ──────────────────────────────────────────────────────

    /** Dark card panel with rounded border */
    public static JPanel createCard() {
        JPanel card = new JPanel();
        card.setBackground(BG_CARD);
        card.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(BORDER_COLOR, 1, true),
            new EmptyBorder(32, 36, 32, 36)
        ));
        return card;
    }

    /** Styled text field */
    public static JTextField createTextField() {
        JTextField tf = new JTextField();
        styleTextField(tf);
        return tf;
    }

    public static void styleTextField(JTextField tf) {
        tf.setBackground(BG_INPUT);
        tf.setForeground(TEXT_PRIMARY);
        tf.setCaretColor(ACCENT_BLUE);
        tf.setFont(FONT_INPUT);
        tf.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(BORDER_COLOR, 1, true),
            new EmptyBorder(6, 10, 6, 10)
        ));
    }

    /** Styled password field */
    public static void stylePasswordField(JPasswordField pf) {
        pf.setBackground(BG_INPUT);
        pf.setForeground(TEXT_PRIMARY);
        pf.setCaretColor(ACCENT_BLUE);
        pf.setFont(FONT_INPUT);
        pf.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(BORDER_COLOR, 1, true),
            new EmptyBorder(6, 10, 6, 10)
        ));
    }

    /** Primary action button (blue) */
    public static JButton createPrimaryButton(String text) {
        JButton btn = new JButton(text) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Color bg = getModel().isRollover() ? ACCENT_HOVER : ACCENT_BLUE;
                g2.setColor(bg);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
                g2.dispose();
                super.paintComponent(g);
            }
            @Override protected void paintBorder(Graphics g) {}
        };
        btn.setForeground(Color.WHITE);
        btn.setFont(FONT_BUTTON);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(110, 34));
        return btn;
    }

    /** Secondary / ghost button */
    public static JButton createSecondaryButton(String text) {
        JButton btn = new JButton(text) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Color bg = getModel().isRollover() ? new Color(48, 56, 70) : BG_INPUT;
                g2.setColor(bg);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
                g2.dispose();
                super.paintComponent(g);
            }
            @Override protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(BORDER_COLOR);
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 8, 8);
                g2.dispose();
            }
        };
        btn.setForeground(TEXT_PRIMARY);
        btn.setFont(FONT_BUTTON);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(110, 34));
        return btn;
    }

    /** Styled label */
    public static JLabel createLabel(String text, boolean primary) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(FONT_LABEL);
        lbl.setForeground(primary ? TEXT_PRIMARY : TEXT_SECONDARY);
        return lbl;
    }

    /** Separator line */
    public static JSeparator createSeparator() {
        JSeparator sep = new JSeparator();
        sep.setForeground(BORDER_COLOR);
        sep.setBackground(BORDER_COLOR);
        return sep;
    }

    /** Role badge label */
    public static JLabel createBadge(String role) {
        JLabel badge = new JLabel("  " + role + "  ") {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(56, 139, 253, 30));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                g2.setColor(ACCENT_BLUE);
                g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 12, 12);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        badge.setForeground(ACCENT_BLUE);
        badge.setFont(FONT_BADGE);
        badge.setOpaque(false);
        badge.setPreferredSize(new Dimension(140, 22));
        return badge;
    }

    /** Style a JTable */
    public static void styleTable(JTable table) {
        table.setBackground(BG_INPUT);
        table.setForeground(TEXT_PRIMARY);
        table.setFont(FONT_LABEL);
        table.setGridColor(BORDER_COLOR);
        table.setRowHeight(32);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(false);
        table.setSelectionBackground(new Color(56, 139, 253, 50));
        table.setSelectionForeground(TEXT_PRIMARY);
        table.getTableHeader().setBackground(BG_CARD);
        table.getTableHeader().setForeground(TEXT_SECONDARY);
        table.getTableHeader().setFont(FONT_BADGE);
        table.getTableHeader().setBorder(new MatteBorder(0, 0, 1, 0, BORDER_COLOR));
        table.setIntercellSpacing(new Dimension(0, 0));
    }

    /** Style a JScrollPane */
    public static void styleScrollPane(JScrollPane sp) {
        sp.setBorder(new LineBorder(BORDER_COLOR, 1, true));
        sp.getViewport().setBackground(BG_INPUT);
        sp.setBackground(BG_CARD);
        styleScrollBar(sp.getVerticalScrollBar());
        styleScrollBar(sp.getHorizontalScrollBar());
    }

    private static void styleScrollBar(JScrollBar sb) {
        sb.setBackground(BG_CARD);
        sb.setUI(new BasicScrollBarUI() {
            @Override protected void configureScrollBarColors() {
                thumbColor = BORDER_COLOR;
                trackColor = BG_CARD;
            }
            @Override protected JButton createDecreaseButton(int o) { return zeroButton(); }
            @Override protected JButton createIncreaseButton(int o) { return zeroButton(); }
            private JButton zeroButton() {
                JButton b = new JButton(); b.setPreferredSize(new Dimension(0,0)); return b;
            }
        });
    }

    /** Full-window dark background panel */
    public static JPanel createBackground() {
        JPanel bg = new JPanel() {
            @Override protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                // subtle radial glow at top-left
                RadialGradientPaint rgp = new RadialGradientPaint(
                    new Point(0, 0),
                    Math.max(getWidth(), getHeight()),
                    new float[]{0f, 1f},
                    new Color[]{new Color(56, 139, 253, 18), BG_DARK}
                );
                g2.setPaint(rgp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        bg.setBackground(BG_DARK);
        return bg;
    }

    /** Styled checkbox */
    public static void styleCheckBox(JCheckBox cb, String label) {
        cb.setText(label);
        cb.setBackground(BG_CARD);
        cb.setForeground(TEXT_SECONDARY);
        cb.setFont(FONT_SMALL);
        cb.setFocusPainted(false);
        cb.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    /** Styled radio button */
    public static void styleRadioButton(JRadioButton rb, String label) {
        rb.setText(label);
        rb.setBackground(BG_CARD);
        rb.setForeground(TEXT_SECONDARY);
        rb.setFont(FONT_SMALL);
        rb.setFocusPainted(false);
        rb.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    /** Section divider with title */
    public static JLabel createSectionHeader(String text) {
        JLabel lbl = new JLabel(text.toUpperCase());
        lbl.setFont(FONT_BADGE);
        lbl.setForeground(TEXT_MUTED);
        lbl.setBorder(new EmptyBorder(0, 0, 6, 0));
        return lbl;
    }

    /** Center a frame on screen */
    public static void centerFrame(JFrame frame) {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(
            (screen.width  - frame.getWidth())  / 2,
            (screen.height - frame.getHeight()) / 2
        );
    }
}
