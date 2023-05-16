import javax.swing.*;
import java.awt.*;
public class GUI extends JFrame {
    private final JPanel sendingPanel;
    private final JPanel receivingPanel;
    private int deviceCounter = 1;

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

        receivingPanel = new JPanel();
        receivingPanel.setLayout(new BorderLayout());

        JScrollPane receivingScrollPane = new JScrollPane(receivingPanel);
        receivingScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

//        JButton addReceivingDeviceButton = new JButton("Add");


        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(receivingScrollPane, BorderLayout.CENTER);



        this.add(rightPanel, BorderLayout.EAST);
        this.add(leftPanel, BorderLayout.WEST);

        setSize(800, 600);
        this.setVisible(true);
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

    // Rest of the code...

}
