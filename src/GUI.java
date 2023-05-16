import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    private JPanel sendingPanel;
    private JButton addSendingDeviceButton;
    private int deviceCounter = 1;

    public GUI() {
        this.setTitle("SMS Application");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.sendingPanel = new JPanel();
        this.sendingPanel.setLayout(new BoxLayout(sendingPanel, BoxLayout.Y_AXIS));

        JScrollPane sendingScrollPane = new JScrollPane(sendingPanel);
        sendingScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        this.addSendingDeviceButton = new JButton("Add");
        this.addSendingDeviceButton.addActionListener(e -> showAddDeviceDialog());

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(sendingScrollPane, BorderLayout.CENTER);
        leftPanel.add(addSendingDeviceButton, BorderLayout.SOUTH);

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

        sendingPanel.add(devicePanel);
        sendingPanel.revalidate(); // Refresh the panel to reflect the changes

        deviceCounter++;
    }

    // Rest of the code...

}
