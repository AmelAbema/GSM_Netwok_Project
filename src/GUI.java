import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    private final JPanel sendingDevicesPanel;
    private final JPanel receivingDevicesPanel;

    private final JPanel stationsPanel;
    private int deviceCounter = 1;
    private int stationCounter = 1;

    public GUI() {
        setTitle("SMS Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        sendingDevicesPanel = new JPanel();
        sendingDevicesPanel.setLayout(new BoxLayout(sendingDevicesPanel, BoxLayout.Y_AXIS));

        JScrollPane sendingScrollPane = new JScrollPane(sendingDevicesPanel);
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
        receivingDevicesPanel.setLayout(new BoxLayout(receivingDevicesPanel, BoxLayout.Y_AXIS));


        JScrollPane receivingScrollPane = new JScrollPane(receivingDevicesPanel);
        receivingScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JButton addReceivingDeviceButton = new JButton("Add");
        addReceivingDeviceButton.addActionListener(e -> createReceivingDevice());

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(receivingScrollPane, BorderLayout.CENTER);
        rightPanel.add(addReceivingDeviceButton, BorderLayout.SOUTH);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        rightPanel.setMinimumSize(new Dimension(550, 100));


        stationsPanel = new JPanel();
        stationsPanel.setLayout(new BoxLayout(stationsPanel, BoxLayout.Y_AXIS));


        JScrollPane stationsScrollPane = new JScrollPane(stationsPanel);
        stationsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);



        JButton addStationButton = new JButton("Add");
        addStationButton.addActionListener(e -> createStationDevice());

        JButton removeStationButton = new JButton("Remove BSC");
        addStationButton.addActionListener(e -> removeStationDevice());

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addStationButton);
        buttonPanel.add(removeStationButton);

        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BorderLayout());
        middlePanel.add(stationsScrollPane, BorderLayout.CENTER);
        middlePanel.add(buttonPanel, BorderLayout.SOUTH);
        middlePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        middlePanel.setMinimumSize(new Dimension(200, 100));

        add(leftPanel, BorderLayout.WEST);
        add(middlePanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);

        JSplitPane splitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, middlePanel);
        JSplitPane splitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitPane1, rightPanel);


        this.getContentPane().add(splitPane2);


        setSize(1500, 700);
        setMinimumSize(new Dimension(1050, 300));
        setVisible(true);

    }

    private void removeStationDevice() {

    }

    private void createStationDevice() {
        JPanel stationPanel = new JPanel();
        stationPanel.setLayout(new FlowLayout());

        JLabel numberLabel = new JLabel("Station " + stationCounter);

        JButton terminateButton = new JButton("Terminate");

        terminateButton.addActionListener(e ->{
            stationPanel.getParent().remove(stationPanel);
        });

        stationPanel.add(terminateButton);
        stationPanel.add(numberLabel);

        stationsPanel.add(stationPanel);
        stationsPanel.revalidate();

        stationCounter++;

    }

    private void showAddDeviceDialog() {
        String message = JOptionPane.showInputDialog(this, "Enter a short text message: ");

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

        terminateButton.addActionListener(e ->{
            devicePanel.getParent().remove(devicePanel);
        });

        frequencySlider.addChangeListener(e -> {
            int frequency = frequencySlider.getValue();
            // Perform action based on the selected frequency
            // For example, update the frequency value for the device
        });

        devicePanel.add(deviceNumberField);
        devicePanel.add(frequencySlider);
        devicePanel.add(terminateButton);
        devicePanel.add(stateComboBox);
        devicePanel.add(messageLabel);

        sendingDevicesPanel.add(devicePanel);
        sendingDevicesPanel.revalidate();

        deviceCounter++;
    }

    private void createReceivingDevice() {
        JPanel devicePanel = new JPanel();
        devicePanel.setLayout(new FlowLayout());

        JButton terminateButton = new JButton("Terminate");
        JLabel receivedMessagesLabel = new JLabel("Received Messages: 0");
        JCheckBox autoClearCheckBox = new JCheckBox("Auto Clear every 10 seconds");

        autoClearCheckBox.addActionListener(e ->{
            if (autoClearCheckBox.isSelected()) {
                // Perform action when the checkbox is selected
                // For example, enable auto clear functionality
            } else {
                // Perform action when the checkbox is deselected
                // For example, disable auto clear functionality
            }
        });

        terminateButton.addActionListener(e -> {
            devicePanel.getParent().remove(devicePanel);
        });

        devicePanel.add(terminateButton);
        devicePanel.add(receivedMessagesLabel);
        devicePanel.add(autoClearCheckBox);

        receivingDevicesPanel.add(devicePanel);
        receivingDevicesPanel.revalidate();
    }
    // Rest of the code...

}
