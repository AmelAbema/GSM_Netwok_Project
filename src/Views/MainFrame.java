package Views;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("SMS Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        LeftPanel leftPanel = new LeftPanel();
        MiddlePanel middlePanel = new MiddlePanel();
        RightPanel rightPanel = new RightPanel();

        add(leftPanel, BorderLayout.WEST);
        add(middlePanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);

        JSplitPane splitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, middlePanel);
        JSplitPane splitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitPane1, rightPanel);

        getContentPane().add(splitPane2);

        setSize(1500, 700);
        setMinimumSize(new Dimension(1050, 300));
        setVisible(true);
    }
}
