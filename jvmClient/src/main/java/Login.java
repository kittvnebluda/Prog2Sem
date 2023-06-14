import com.prog2sem.client.MainKt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

public class Login extends JFrame {

    private JPanel mainPanel;
    private JTextField loginField;
    private JPasswordField passwordField;
    private JLabel passwordLabel;
    private JLabel loginLabel;
    private JButton register;
    private JButton login;
    private JLabel warning;

    private final int widthScreen = 400;
    private final int heightScreen = 720;
    private final int widthLogin = 300;
    private final int heightLogin = 30;
    private final int widthRegister = 300;
    private final int heightRegister = 30;
    private final int widthPasswordField = 300;
    private final int heightPasswordField = 50;
    private final int widthLoginField = 300;
    private final int heightLoginField = 50;

    private final String passwordLableName = "Password:", loginLabelName = "Login:", buttomLoginName = "Login", buttomRegisterName = "Register";

    public static String command = "";

    public Login(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        mainPanel.setPreferredSize(new Dimension(widthScreen, heightScreen));
        this.setLocation(new Point(screenSize.width / 2 - widthScreen / 2, screenSize.height / 2 - heightScreen / 2));

        //mainPanel.setBorder(new RoundRectangle2D());

        passwordLabel.setText(passwordLableName);
        passwordLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        passwordField.setPreferredSize(new Dimension(widthPasswordField, heightPasswordField));
        //passwordField.setBorder(new Info.RoundedBorder(21));

        loginLabel.setText(loginLabelName);
        loginLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        loginField.setPreferredSize(new Dimension(widthLoginField, heightLoginField));
        //loginField.setBorder(new Info.RoundedBorder(21));

        register.setText(buttomRegisterName);
        register.setPreferredSize(new Dimension(widthRegister, heightRegister));
        //register.setBorder(new Info.RoundedBorder(21));
        register.setBackground(Color.BLUE);
        register.setForeground(Color.WHITE);


        login.setText(buttomLoginName);
        login.setPreferredSize(new Dimension(widthLogin, heightLogin));
        //login.setBorder(new Info.RoundedBorder(21));
        login.setBackground(Color.BLUE);
        login.setForeground(Color.WHITE);

        showComp(false);

        warning.setVisible(false);

        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        setShape(new RoundRectangle2D.Double(0, 0, widthScreen, heightScreen, 21, 21));

        setVisible(true);

        this.pack();

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                command = "login";
                if (!loginField.isVisible()) showComp(true);
                else {
                    MainKt.setMsg(loginField.getText());
                    MainKt.setMsg(passwordField.getText());
                }
            }
        });

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                command = "signup";
                if (!loginField.isVisible()) {
                    showComp(true);
                    login.setVisible(false);
                    warning.setVisible(false);
                } else {
                    if (login.isVisible()) {
                        MainKt.setMsg("");
                        login.setVisible(false);
                        loginField.setText("");
                        passwordField.setText("");
                        warning.setVisible(false);
                    } else {
                        MainKt.setMsg(loginField.getText());
                        MainKt.setMsg(passwordField.getText());
                    }
                }
            }
        });

        installEscapeCloseOperation(this);

    }

    public void acsess() {
        this.setVisible(false);
        new MainMenu("MainMenu", MainKt.getLogin());
    }

    public void showWarning(String warn) {
        warning.setText(warn);
        warning.setVisible(true);
    }

    private void showComp(Boolean flag) {
        passwordField.setVisible(flag);
        loginField.setVisible(flag);
        passwordLabel.setVisible(flag);
        loginLabel.setVisible(flag);
    }

    private static final KeyStroke escapeStroke =
            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
    public static final String dispatchWindowClosingActionMapKey =
            "com.spodding.tackline.dispatch:WINDOW_CLOSING";

    public static void installEscapeCloseOperation(final JFrame dialog) {
        Action dispatchClosing = new AbstractAction() {
            public void actionPerformed(ActionEvent event) {
                dialog.dispatchEvent(new WindowEvent(
                        dialog, WindowEvent.WINDOW_CLOSING
                ));
            }
        };
        JRootPane root = dialog.getRootPane();
        root.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                escapeStroke, dispatchWindowClosingActionMapKey
        );
        root.getActionMap().put(dispatchWindowClosingActionMapKey, dispatchClosing
        );
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(9, 3, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.setBackground(new Color(-1));
        mainPanel.setEnabled(false);
        mainPanel.setForeground(new Color(-1));
        loginField = new JTextField();
        mainPanel.add(loginField, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        mainPanel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(8, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        passwordField = new JPasswordField();
        mainPanel.add(passwordField, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        loginLabel = new JLabel();
        loginLabel.setHorizontalAlignment(10);
        loginLabel.setText("Label");
        mainPanel.add(loginLabel, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        mainPanel.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        passwordLabel = new JLabel();
        passwordLabel.setText("Label");
        mainPanel.add(passwordLabel, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        register = new JButton();
        register.setText("Button");
        mainPanel.add(register, new com.intellij.uiDesigner.core.GridConstraints(7, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        login = new JButton();
        login.setText("Button");
        mainPanel.add(login, new com.intellij.uiDesigner.core.GridConstraints(6, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        warning = new JLabel();
        warning.setText("Label");
        mainPanel.add(warning, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        mainPanel.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(6, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        mainPanel.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
