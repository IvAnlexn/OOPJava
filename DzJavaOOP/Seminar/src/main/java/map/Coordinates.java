package map;

public class Coordinates {
  private int row;
  private int col;

  public Coordinates(int row, int col) {
    this.row = row;
    this.col = col;
  }

  public int[] toArray() {
    return new int[]{row, col};
  }

  public void setCoordinates(int row, int col) {
    this.row = row;
    this.col = col;
  }

  @Override
  public String toString() {
    return String.format("row: %d, col: %d", row, col);
  }

  public int getDistance(Coordinates targetPosition) {
    return (int) Math.sqrt(Math.pow(row - targetPosition.toArray()[0], 2) + Math.pow(col - targetPosition.toArray()[1], 2));
  }

  public Directions getDirection(Coordinates otherCoordinates) {
    int[] my = this.toArray();
    int[] other = otherCoordinates.toArray();
    if (Math.abs(my[0] - other[0]) > Math.abs(my[1] - other[1])) {
      if (my[0] > other[0]) {
        return Directions.NORTH;
      } else {
        return Directions.SOUTH;
      }
    } else {
      if (my[1] > other[1]) {
        return Directions.WEST;
      } else {
        return Directions.EAST;
      }
    }
  }

  public boolean isEqual(Coordinates coordinates) {
    return this.row == coordinates.row && this.col == coordinates.col;
  }
}
