package home.entertainment;

public class Person {

    private boolean alive;
    private String number;
    private int liveNeighbors = 0;
    private int deadNeighbors = 0;

    public void addLiveNeighbor(){
        this.liveNeighbors++;
    }
    public void addDeadNeighbor(){
        this.deadNeighbors++;
    }

    public int getLiveNeighbors() {
        return this.liveNeighbors;
    }

    public int getdDadNeighbors() {
        return this.deadNeighbors;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getAliveAsInt() {
        return alive == true ? 1 : 0;
    }

    public void suicide() {
        this.alive = false;
        this.liveNeighbors = 0;
        this.deadNeighbors = 0;
    }

    public void resurrect() {
        this.alive = true;
        this.liveNeighbors = 0;
        this.deadNeighbors = 0;
    }

    @Override
    public String toString() {
        return "Person{" +
                "alive=" + alive +
                ", number=" + number +
                ", liveNeighbors=" + liveNeighbors +
                ", deadNeighbors=" + deadNeighbors +
                '}';
    }
}
