import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    private final JPanel sendingPanel;
    private final JPanel receivingPanel;
    private final JPanel networkPanel;

    public GUI() {
        setTitle("SMS Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        sendingPanel = new JPanel();
        receivingPanel = new JPanel();
        networkPanel = new JPanel();

        networkPanel.setLayout(new BoxLayout(networkPanel, BoxLayout.X_AXIS));

        JScrollPane sendingScrollPane = new JScrollPane(sendingPanel);
        sendingScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JScrollPane receivingScrollPane = new JScrollPane(receivingPanel);
        receivingScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        networkPanel.add(sendingScrollPane);
        networkPanel.add(receivingScrollPane);

        JButton addBSCButton = new JButton("Add BSC Layer");
        addBSCButton.addActionListener(e -> addBSCStation());

        networkPanel.add(addBSCButton);

        add(networkPanel, BorderLayout.CENTER);

        setSize(800, 600);
        setVisible(true);
    }

    private void addBSCStation() {
        // Code to add a new BSC station
    }

    private void addSendingDevice() {
        // Code to add a new sending device
    }

    private void addReceivingDevice() {
        // Code to add a new receiving device
    }
}
