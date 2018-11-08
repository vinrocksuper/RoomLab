package Entities;

public abstract interface Enemies {
    public void generate(int x, int y, Person p);
    public void generate(int x, int y, boolean sleep, Person p);
    public int dealDamage(Person x);
    public int dropGold();

}
