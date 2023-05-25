package Views;

import javax.swing.*;
import java.awt.*;

public class RightPanel extends JPanel {
    private final JPanel receivingDevicesPanel;

    public RightPanel() {
        setLayout(new BorderLayout());

        receivingDevicesPanel = new JPanel();
        receivingDevicesPanel.setLayout(new BoxLayout(receivingDevicesPanel, BoxLayout.Y_AXIS));

        JScrollPane receivingScrollPane = new JScrollPane(receivingDevicesPanel);
        receivingScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JButton addReceivingDeviceButton = new JButton("Add");
        addReceivingDeviceButton.addActionListener(e -> createReceivingDevice());

        add(receivingScrollPane, BorderLayout.CENTER);
        add(addReceivingDeviceButton, BorderLayout.SOUTH);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setMinimumSize(new Dimension(550, 100));
    }

    private void createReceivingDevice() {
        JPanel devicePanel = new JPanel();
        devicePanel.setLayout(new FlowLayout());

        JButton terminateButton = new JButton("Terminate");
        JLabel receivedMessagesLabel = new JLabel("Received Messages: 0");
        JCheckBox autoClearCheckBox = new JCheckBox("Auto Clear every 10 seconds");

        autoClearCheckBox.addActionListener(e -> {
            if (autoClearCheckBox.isSelected()) {
                // Perform action when the checkbox is selected
                // For example, enable auto clear functionality
            } else {
                // Perform action when the checkbox is deselected
                // For example, disable auto clear functionality
            }
        });

        terminateButton.addActionListener(e -> {
            receivingDevicesPanel.remove(devicePanel);
            receivingDevicesPanel.revalidate();
            receivingDevicesPanel.repaint();
        });

        devicePanel.add(terminateButton);
        devicePanel.add(receivedMessagesLabel);
        devicePanel.add(autoClearCheckBox);

        receivingDevicesPanel.add(devicePanel);
        receivingDevicesPanel.revalidate();
    }
}
