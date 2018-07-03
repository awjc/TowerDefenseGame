package game;

import java.awt.*;

public class Bloon {
  Track track;
  double positionFraction;
  double speed;

  public Bloon(Track track, double positionFraction, double speed) {
    this.track = track;
    this.positionFraction = positionFraction;
    this.speed = speed;
  }

  public void update() {
    positionFraction += speed;
  }

  public void draw(Graphics g) {
    if (positionFraction < 0 || positionFraction > 1) {
      return;
    }

    g.setColor(Color.black);
    int width = 20;
    int height = 30;
    Point position = track.interpolate(positionFraction);
    int xpos = (int) ((position.x * track.getWidth()) - width / 2);
    int ypos = (int) ((position.y * track.getHeight()) - height / 2);
    g.fillOval(xpos - 1, ypos - 1, width + 2, height + 2);

    g.setColor(Color.green);
    g.fillOval(xpos, ypos, width, height);
  }
}
