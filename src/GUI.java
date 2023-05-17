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
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        leftPanel.setMinimumSize(new Dimension(550, 100));


        receivingDevicesPanel = new JPanel();
        receivingDevicesPanel.setLayout(new BorderLayout());


        JScrollPane receivingScrollPane = new JScrollPane(receivingDevicesPanel);
        receivingScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        receivingScrollPane.setViewportView(receivingDevicesPanel);

        JButton addReceivingDeviceButton = new JButton("Add");
        addReceivingDeviceButton.addActionListener(e -> createReceivingDevice());

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(receivingScrollPane, BorderLayout.CENTER);
        rightPanel.add(addReceivingDeviceButton, BorderLayout.SOUTH);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        rightPanel.setMinimumSize(new Dimension(550, 100));

        add(rightPanel, BorderLayout.EAST);
        add(leftPanel, BorderLayout.WEST);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerLocation(300);

        add(splitPane);



        setSize(1700, 700);
        setMinimumSize(new Dimension(1500, 300));
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

        JSlider frequencySlider = new JSlider(JSlider.HORIZONTAL, 1, 10, 5);
        frequencySlider.setSnapToTicks(true);
        frequencySlider.createStandardLabels(1, 1);
        frequencySlider.setMajorTickSpacing(1);
        frequencySlider.setPaintTicks(true);
        frequencySlider.setPaintLabels(true);
        JButton terminateButton = new JButton("Terminate");
        JTextField deviceNumberField = new JTextField("Device " + deviceCounter);
        deviceNumberField.setEditable(false);

        JComboBox<String> stateComboBox = new JComboBox<>(new String[]{"WAITING", "ACTIVE"});

        // Add action listener to the stateComboBox
        stateComboBox.addActionListener(e -> {
            String selectedState = (String) stateComboBox.getSelectedItem();
            assert selectedState != null;
            if (selectedState.equals("ACTIVE")) {
                // Start or resume the thread
                // Implement the logic to handle the thread's behavior
            } else if (selectedState.equals("WAITING")) {
                // Suspend or pause the thread
                // Implement the logic to handle the thread's behavior
            }
        });

        devicePanel.add(deviceNumberField);
        devicePanel.add(frequencySlider);
        devicePanel.add(terminateButton);
        devicePanel.add(stateComboBox);
        devicePanel.add(messageLabel);

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
