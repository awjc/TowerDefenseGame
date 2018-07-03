package game;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.tuple.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Track extends JPanel {
  public List<Point> points;
  private List<Segment> segments;
  private List<Bloon> bloons;

  public Track(List<Point> trackPoints) {
    Preconditions.checkArgument(trackPoints.size() >= 2, "Need at least 2 track points");
    this.points = trackPoints;
    this.segments = new ArrayList<>();
    for (int i = 0; i < trackPoints.size() - 1; i++) {
      segments.add(new Segment(points.get(i), points.get(i + 1)));
    }
    bloons = new ArrayList<>();

    setLayout(new BorderLayout());
  }

  public void addBloon(Bloon b) {
    bloons.add(b);
  }

  public void updateBloons() {
    for (Bloon b : bloons) {
      b.update();
    }
  }

  public Point interpolate(double fraction) {
    Preconditions.checkArgument(0 <= fraction && fraction <= 1, "fraction must be [0, 1]");

    double totalLength = segments.stream().mapToDouble(s -> s.length).sum();
    List<Pair<Double, Point>> pointDistances = new ArrayList<>();
    double cumLength = 0;
    for (Segment s : segments) {
      pointDistances.add(Pair.of(cumLength / totalLength, s.p1));
      cumLength += s.length;
    }
    pointDistances.add(Pair.of(1.0, segments.get(segments.size() - 1).p2));


    for (int i = 0; i < pointDistances.size() - 1; i++) {
      Pair<Double, Point> p1 = pointDistances.get(i);
      Pair<Double, Point> p2 = pointDistances.get(i + 1);
      if (p1.getLeft() <= fraction && fraction <= p2.getLeft()) {
        return interpolate(p1.getRight(), p2.getRight(),
            (fraction - p1.getLeft()) / (p2.getLeft() - p1.getLeft()));
      }
    }

    throw new RuntimeException("Should never get here");

//    int N = points.size() - 1;
//    int idx = (int) Math.floor(fraction * N);
//    Point p1 = points.get(idx);
//    Point p2 = points.get(Math.min(idx + 1, points.size() - 1));
//    return interpolate(p1, p2, N * fraction - idx);
  }

  private static Point interpolate(Point p1, Point p2, double fraction) {
    double x = (p2.x - p1.x) * fraction + p1.x;
    double y = (p2.y - p1.y) * fraction + p1.y;
    return Point.of(x, y);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    ((Graphics2D) g).setStroke(new BasicStroke(3));

    g.setColor(Color.red);
    for (Segment s : segments) {
      drawLine(g, getSize(), s.p1, s.p2);
    }

    for (Bloon b : bloons) {
      b.draw(g);
    }
  }

  private void drawLine(Graphics g, Dimension size, Point p1, Point p2) {
    g.drawLine((int)(size.width * p1.x), (int)(size.height * p1.y),
        (int)(size.width * p2.x), (int)(size.height * p2.y));
  }
}
