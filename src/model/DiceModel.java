package model;

public class DiceModel {
    private int value;
    private boolean fixed;
    private boolean canUse;



    public DiceModel(boolean fixed, boolean canUse) {
        this.fixed = fixed;
        this.canUse = canUse;
    }

    public boolean canUse() {
        return canUse;
    }

    public void setCanUse(boolean canUse) {
        this.canUse = canUse;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        if (this.fixed == true) {
            str.append("|" + this.value + "|");
        } else {
            str.append(this.value);
        }

        return str.toString();
    }
}
