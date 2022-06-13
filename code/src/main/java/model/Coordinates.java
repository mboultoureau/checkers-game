package model;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates() {}

    public int getX() {
        return x;
    }

    public int getRow() {
        return this.getX();
    }

    public int getY() {
        return y;
    }

    public int getColumn() {
        return this.getY();
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setRow(int row) {
        this.x = row;
    }

    public void setColumn(int column) {
        this.y = column;
    }
}
