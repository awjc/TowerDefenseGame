package game;

import com.google.common.collect.ImmutableList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TowerDefenseGame {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      JFrame f = new JFrame("My Game");
      f.setSize(1080, 1080);
      f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      f.setLocation((screenSize.width - f.getWidth()) / 2, (screenSize.height - f.getHeight()) / 2);
      f.setVisible(true);

      new TowerDefenseGame(f).runGame();
    });
  }

  private JFrame frame;
  private JPanel gamePanel;

  private TowerDefenseGame(JFrame frame) {
    this.frame = frame;
    this.gamePanel = new JPanel();
    frame.add(gamePanel);
  }

  private void runGame() {
    Track t = new Track(makePoints());
    gamePanel.removeAll();
    gamePanel.setLayout(new GridLayout(0, 1));
    gamePanel.add(t);

    double speed = 0.004;
    for (double d = 0.0; d >= -0.2; d -= 0.02) {
      t.addBloon(new Bloon(t, d, speed));
    }

    Timer timer = new Timer(33, new ActionListener(){
      public void actionPerformed(ActionEvent e) {
        t.updateBloons();
        gamePanel.paintImmediately(0, 0, gamePanel.getWidth(), gamePanel.getHeight());
      }
    });
    timer.start();
  }

//  private List<Point> makePoints() {
//    return ImmutableList.of(
//        Point.of(0, 0),
//        Point.of(0, 0.5),
////        Point.of(0.22, 0.62),
////        Point.of(0.7, 0.3),
////        Point.of(0.1, 0.6),
//        Point.of(1, 0.5)
//    );
//  }

  private List<Point> makePoints() {
    return ImmutableList.of(
        Point.of(0, 0),
        Point.of(0.2, 0.6),
        Point.of(0.38, 0.7),
        Point.of(0.48, 0.3),
        Point.of(1, 0.5)
    );
  }
}