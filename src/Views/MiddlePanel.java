package Views;

import javax.swing.*;
import java.awt.*;

public class MiddlePanel extends JPanel {
    private final JPanel stationsPanel;
    private int stationCounter = 1;

    public MiddlePanel() {
        setLayout(new BorderLayout());

        stationsPanel = new JPanel();
        stationsPanel.setLayout(new BoxLayout(stationsPanel, BoxLayout.Y_AXIS));

        JScrollPane stationsScrollPane = new JScrollPane(stationsPanel);
        stationsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JButton addStationButton = new JButton("Add");
        addStationButton.addActionListener(e -> createStationDevice());

        JButton removeStationButton = new JButton("Remove BSC");
        removeStationButton.addActionListener(e -> removeStationDevice());

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addStationButton);
        buttonPanel.add(removeStationButton);

        add(stationsScrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        setMinimumSize(new Dimension(200, 100));
    }

    private void createStationDevice() {
        JPanel stationPanel = new JPanel();
        stationPanel.setLayout(new FlowLayout());

        JLabel numberLabel = new JLabel("Station " + stationCounter);

        JButton terminateButton = new JButton("Terminate");

        terminateButton.addActionListener(e -> {
            stationsPanel.remove(stationPanel);
            stationsPanel.revalidate();
            stationsPanel.repaint();
        });

        stationPanel.add(terminateButton);
        stationPanel.add(numberLabel);

        stationsPanel.add(stationPanel);
        stationsPanel.revalidate();

        stationCounter++;
    }

    private void removeStationDevice() {
        // Implement the logic to remove a station device
    }
}
