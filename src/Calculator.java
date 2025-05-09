import java.awt.*;
import java.awt.event.*;
public class LayoutManagerExample extends Frame {
 public LayoutManagerExample() {
 // BorderLayout Example
 setLayout(new BorderLayout());
 add(new Button("North"), BorderLayout.NORTH);
 add(new Button("South"), BorderLayout.SOUTH);
 add(new Button("East"), BorderLayout.EAST);
 add(new Button("West"), BorderLayout.WEST);
 add(new Button("Center"), BorderLayout.CENTER);
 // GridLayout Example
 Panel gridPanel = new Panel(new GridLayout(2, 2));
 gridPanel.add(new Button("Button 1"));
 gridPanel.add(new Button("Button 2"));
 gridPanel.add(new Button("Button 3"));
 gridPanel.add(new Button("Button 4"));
 add(gridPanel, BorderLayout.NORTH);
 // FlowLayout Example
 Panel flowPanel = new Panel(new FlowLayout());
 flowPanel.add(new Button("Button A"));
 flowPanel.add(new Button("Button B"));
 flowPanel.add(new Button("Button C"));
 add(flowPanel, BorderLayout.CENTER);
 // CardLayout Example
 CardLayout cardLayout = new CardLayout();
 Panel cardPanel = new Panel(cardLayout);
 cardPanel.add(new Button("Card 1"), "Card 1");
 cardPanel.add(new Button("Card 2"), "Card 2");
 cardPanel.add(new Button("Card 3"), "Card 3");
 add(cardPanel, BorderLayout.SOUTH);
 // GridBagLayout Example
 Panel gridBagPanel = new Panel(new GridBagLayout());
 GridBagConstraints gbc = new GridBagConstraints();
 gbc.gridx = 0;
 gbc.gridy = 0;
 gbc.insets = new Insets(5, 5, 5, 5);
 gridBagPanel.add(new Button("Button X"), gbc);
 gbc.gridx = 1;
 gridBagPanel.add(new Button("Button Y"), gbc);
 add(gridBagPanel, BorderLayout.WEST);
 // Set frame properties
 setTitle("Layout Manager Example");
 setSize(500, 300);
 setVisible(true);
 }
 public static void main(String[] args) {
 new LayoutManagerExample();
 }
}
