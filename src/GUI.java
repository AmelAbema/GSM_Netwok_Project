import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    private final JPanel sendingPanel;
    private int deviceCounter = 1;

    public GUI() {
        this.setTitle("SMS Application");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.sendingPanel = new JPanel();
        this.sendingPanel.setLayout(new BoxLayout(sendingPanel, BoxLayout.Y_AXIS));

        JScrollPane sendingScrollPane = new JScrollPane(sendingPanel);
        sendingScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JButton addSendingDeviceButton = new JButton("Add");
        addSendingDeviceButton.addActionListener(e -> showAddDeviceDialog());

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(sendingScrollPane, BorderLayout.CENTER);
        leftPanel.add(addSendingDeviceButton, BorderLayout.SOUTH);

        this.add(leftPanel, BorderLayout.WEST);

        setSize(800, 600);
        setVisible(true);
    }

    private void showAddDeviceDialog() {
        String message = JOptionPane.showInputDialog(this, "Enter a short text message:");

        if (message != null && !message.isEmpty()) {
            createSendingDevice(message);
        }
    }

    private void createSendingDevice(String message) {
        JPanel devicePanel = new JPanel();
        devicePanel.setLayout(new FlowLayout());

        JTextField messageLabel = new JTextField(message);
        messageLabel.setEditable(false);
        JLabel deviceNumberLabel = new JLabel("Device " + deviceCounter);
        JSlider frequencySlider = new JSlider(JSlider.HORIZONTAL, 1, 10, 5);
        JButton terminateButton = new JButton("Terminate");
        JTextField deviceNumberField = new JTextField("Device " + deviceCounter);
        deviceNumberField.setEditable(false);
        JComboBox<String> stateComboBox = new JComboBox<>(new String[]{"WAITING", "ACTIVE"});

        devicePanel.add(deviceNumberLabel);
        devicePanel.add(frequencySlider);
        devicePanel.add(terminateButton);
        devicePanel.add(deviceNumberField);
        devicePanel.add(stateComboBox);
        devicePanel.add(messageLabel);

        sendingPanel.add(devicePanel);
        sendingPanel.revalidate(); // Refresh the panel to reflect the changes

        deviceCounter++;
    }

    // Rest of the code...

}
