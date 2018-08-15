package game;

public class Segment {
  final Point p1;
  final Point p2;
  final double length;

  public Segment(final Point p1, final Point p2) {
    this.p1 = p1;
    this.p2 = p2;
    length = Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
  }

  @Override
  public String toString() {
    return String.format("{Segment: %s -> %s; length %.4f}", p1, p2, length);
  }
}
