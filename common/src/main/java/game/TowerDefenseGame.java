package game;

import javax.swing.*;
import java.awt.*;

public class TowerDefenseGame {
  public static void main(String[] args) {
    // System.out.println("Hello, World");
    JFrame f = new JFrame("My Game");
    f.setSize(1920, 1080);
    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    f.setLocation((screenSize.width - f.getWidth()) / 2, (screenSize.height - f.getHeight()) / 2);

    f.setVisible(true);
  }
}