package model;

public class PlayerModel {
    private String name;
    private int total;

    public PlayerModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total += total;
    }

    public String toString() {
        return name + ", total=" + total;
    }
}
