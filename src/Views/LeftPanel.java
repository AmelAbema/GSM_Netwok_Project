package Views;

import Controllers.VBDController;
import Models.VBD;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LeftPanel extends JPanel {
    static Set<String> generatedNumbers = new HashSet<>();
    private VBDController vbdController;
    private final JPanel sendingDevicesPanel;
    private int deviceCounter = 1;

    public LeftPanel() {
        setLayout(new BorderLayout());

        sendingDevicesPanel = new JPanel();
        sendingDevicesPanel.setLayout(new BoxLayout(sendingDevicesPanel, BoxLayout.Y_AXIS));

        JScrollPane sendingScrollPane = new JScrollPane(sendingDevicesPanel);
        sendingScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JButton addSendingDeviceButton = new JButton("Add");
        addSendingDeviceButton.addActionListener(e -> showAddDeviceDialog());

        add(sendingScrollPane, BorderLayout.CENTER);
        add(addSendingDeviceButton, BorderLayout.SOUTH);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setMinimumSize(new Dimension(550, 100));
    }

    private void showAddDeviceDialog() {
        String message = JOptionPane.showInputDialog(this, "Enter a short text message: ");

        if (message != null && !message.isEmpty()) {
            createSendingDevice(message);
        }
    }

    private void createSendingDevice(String message) {
        String num = generateRandomPhoneNumber();
        VBD vbdObject = new VBD(num, message);


        JPanel devicePanel = new JPanel();
        devicePanel.setLayout(new FlowLayout());

        JTextField messageLabel = new JTextField(message);
        messageLabel.setEditable(false);

        JSlider frequencySlider = new JSlider(JSlider.HORIZONTAL, 1, 10, 1);
        frequencySlider.setSnapToTicks(true);
        frequencySlider.createStandardLabels(1, 1);
        frequencySlider.setMajorTickSpacing(1);
        frequencySlider.setPaintTicks(true);
        frequencySlider.setPaintLabels(true);

        JButton terminateButton = new JButton("Terminate");
        JTextField deviceNumberField = new JTextField(num);
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

        terminateButton.addActionListener(e -> {
            sendingDevicesPanel.remove(devicePanel);
            sendingDevicesPanel.revalidate();
            sendingDevicesPanel.repaint();
            vbdObject.interrupt();
        });

        frequencySlider.addChangeListener(e -> {
            int frequency = frequencySlider.getValue();
            // Perform action based on the selected frequency
            // For example, update the frequency value for
            vbdObject.setFrequency(frequency);
        });

        devicePanel.add(deviceNumberField);
        devicePanel.add(frequencySlider);
        devicePanel.add(terminateButton);
        devicePanel.add(stateComboBox);
        devicePanel.add(messageLabel);

        sendingDevicesPanel.add(devicePanel);
        sendingDevicesPanel.revalidate();

        deviceCounter++;
        vbdObject.start();
    }

    public static String generateRandomPhoneNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        sb.append('+');

        for (int i = 0; i < random.nextInt(4) + 9; i++) {
            sb.append(random.nextInt(10));
        }

        generatedNumbers.add(sb.toString());

        if (!generatedNumbers.contains(sb.toString())) {
            return generateRandomPhoneNumber();
        }
        return sb.toString();
    }
}
