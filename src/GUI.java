import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    private final JPanel sendingPanel;
    private int deviceCounter = 1;
    private final JPanel receivingDevicesPanel;

    public GUI() {
        setTitle("SMS Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        sendingPanel = new JPanel();
        sendingPanel.setLayout(new BorderLayout());

        JScrollPane sendingScrollPane = new JScrollPane(sendingPanel);
        sendingScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JButton addSendingDeviceButton = new JButton("Add");
        addSendingDeviceButton.addActionListener(e -> showAddDeviceDialog());

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(sendingScrollPane, BorderLayout.CENTER);
        leftPanel.add(addSendingDeviceButton, BorderLayout.SOUTH);


        JPanel receivingPanel = new JPanel();
        receivingPanel.setLayout(new BorderLayout());

        JScrollPane receivingScrollPane = new JScrollPane(receivingPanel);
        receivingScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        receivingDevicesPanel = new JPanel();
        receivingDevicesPanel.setLayout(new BoxLayout(receivingDevicesPanel, BoxLayout.Y_AXIS));
        receivingScrollPane.setViewportView(receivingDevicesPanel);

        JButton addReceivingDeviceButton = new JButton("Add");
        addReceivingDeviceButton.addActionListener(e -> createReceivingDevice());

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(receivingScrollPane, BorderLayout.CENTER);
        rightPanel.add(addReceivingDeviceButton, BorderLayout.SOUTH);

        add(rightPanel, BorderLayout.EAST);
        add(leftPanel, BorderLayout.WEST);

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

        // Add action listener to the stateComboBox
        stateComboBox.addActionListener(e -> {
            String selectedState = (String) stateComboBox.getSelectedItem();
            if (selectedState.equals("ACTIVE")) {
                // Start or resume the thread
                // Implement the logic to handle the thread's behavior
            } else if (selectedState.equals("WAITING")) {
                // Suspend or pause the thread
                // Implement the logic to handle the thread's behavior
            }
        });

        devicePanel.add(deviceNumberLabel);
        devicePanel.add(frequencySlider);
        devicePanel.add(terminateButton);
        devicePanel.add(deviceNumberField);
        devicePanel.add(stateComboBox);

        sendingPanel.add(devicePanel);
        sendingPanel.revalidate();

        deviceCounter++;
    }

    private void createReceivingDevice() {
        JPanel devicePanel = new JPanel();
        devicePanel.setLayout(new FlowLayout());

        JButton terminateButton = new JButton("Terminate");
        JLabel receivedMessagesLabel = new JLabel("Received Messages: 0");
        JCheckBox autoClearCheckBox = new JCheckBox("Auto Clear every 10 seconds");

        devicePanel.add(terminateButton);
        devicePanel.add(receivedMessagesLabel);
        devicePanel.add(autoClearCheckBox);

        receivingDevicesPanel.add(devicePanel);
        receivingDevicesPanel.revalidate();
    }
    // Rest of the code...

}
