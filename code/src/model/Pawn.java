package model;

public class Pawn {
    public enum PAWN_COLOR { BLACK, WHITE };
    public enum PAWN_TYPE { PAWN, QUEEN };

    private PAWN_COLOR color;
    private PAWN_TYPE type;

    public Pawn(PAWN_COLOR color, PAWN_TYPE type) {
        this.color = color;
        this.type = type;
    }

    public PAWN_COLOR getColor() {
        return color;
    }

    public void setColor(PAWN_COLOR color) {
        this.color = color;
    }

    public PAWN_TYPE getType() {
        return type;
    }

    public void setType(PAWN_TYPE type) {
        this.type = type;
    }
}
