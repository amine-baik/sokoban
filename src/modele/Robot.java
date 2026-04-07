package modele;

public class Robot {
    private int col;
    private int ligne;
    private Direction direction;
    public Robot(int col, int ligne) {
        this.col = col;
        this.ligne = ligne;
        this.direction = Direction.BAS;
    }
    public int getCol()             { return col; }
    public int getLigne()           { return ligne; }
    public Direction getDirection() { return direction; }
    public void setPosition(int col, int ligne) {
        this.col   = col;
        this.ligne = ligne;
    }
    public void setDirection(Direction d) { this.direction = d; }
}