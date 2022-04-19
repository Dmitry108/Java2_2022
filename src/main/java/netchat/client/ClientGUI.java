package netchat.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClientGUI extends JFrame implements ActionListener, KeyListener, Thread.UncaughtExceptionHandler {
    private final ChatClient client = new ChatClient();

    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;

    private final JPanel panelTop = new JPanel(new GridLayout(2, 3));
    private final JTextField ipAddressTextField = new JTextField("127.0.0.1");
    private final JTextField portTextField = new JTextField("222");
    private final JCheckBox onTopCheckBox = new JCheckBox("Always on top");
    private final JTextField loginTextField = new JTextField();
    private final JPasswordField passwordField = new JPasswordField();
    private final JButton loginButton = new JButton("Start");
    private final JTextArea logTextArea = new JTextArea();
    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JButton logoutButton = new JButton("Logout");
    private final JTextField messageTextField = new JTextField();
    private final JButton sendButton = new JButton("Send");
    private final JList<String> usersList = new JList<>();

    public ClientGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        setTitle("Chat");


        logTextArea.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(logTextArea);
        usersList.setPreferredSize(new Dimension(100, 0));
        JScrollPane userListScrollPane = new JScrollPane(usersList);

        panelTop.add(ipAddressTextField);
        panelTop.add(portTextField);
        panelTop.add(onTopCheckBox);
        panelTop.add(loginTextField);
        panelTop.add(passwordField);
        panelTop.add(loginButton);
        panelBottom.add(logoutButton, BorderLayout.WEST);
        panelBottom.add(messageTextField, BorderLayout.CENTER);
        panelBottom.add(sendButton, BorderLayout.EAST);
        add(panelTop, BorderLayout.NORTH);
        add(logScrollPane, BorderLayout.CENTER);
        add(panelBottom, BorderLayout.SOUTH);
        add(userListScrollPane, BorderLayout.EAST);

        onTopCheckBox.addActionListener(this);
        sendButton.addActionListener(this);
        messageTextField.addKeyListener(this);

        setVisible(true);
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ClientGUI::new);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object source = actionEvent.getSource();
        if (source.equals(onTopCheckBox)) {
            setAlwaysOnTop(onTopCheckBox.isSelected());
        } else if (source.equals(sendButton)) {
            sendMessage();
        } else {
            throw new IllegalStateException("Unexpected event");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getSource().equals(messageTextField) && e.getKeyCode() == KeyEvent.VK_ENTER) {
            sendMessage();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void sendMessage() {
        String text = messageTextField.getText();
        messageTextField.setText("");
        messageTextField.requestFocus();
        logTextArea.append(text + '\n');
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        String message = String.format("Exception in thread %s %s: %s%n%s", thread.getName(),
                throwable.getClass().getCanonicalName(), throwable.getMessage(), throwable.getStackTrace()[0]);
        JOptionPane.showMessageDialog(null, message, "Exception", JOptionPane.ERROR_MESSAGE);
    }
}