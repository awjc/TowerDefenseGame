package game;

import com.google.common.base.Preconditions;

public class Point {
  public final double x;
  public final double y;

  private Point(final double x, final double y) {
    Preconditions.checkArgument(0 <= x && x <= 1, "x must be [0, 1]");
    Preconditions.checkArgument(0 <= y && y <= 1, "y must be [0, 1]");
    this.x = x;
    this.y = y;
  }

  public static Point of(final double x, final double y) {
    return new Point(x, y);
  }

  @Override
  public String toString() {
    return String.format("[%.2f, %.2f]", x, y);
  }
}
