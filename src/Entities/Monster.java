package Entities;

import Game.Runner;
import Rooms.Room;

import java.util.Scanner;

public class Monster extends Room implements Enemies{


    private int hp;
    private int attack;
    private int resist;
    private boolean sleep;
    private boolean aggro = false;
    private boolean alive = true;

    public Monster(int x, int y,int health,int att, int res)
    {
        super(x,y);
        hp = health;
        attack = att;
        resist = res;
    }
    public Monster(int x, int y,int health,int att, int res, boolean slp)
    {
        super(x,y);
        hp = health;
        attack = att;
        resist = res;
        sleep = slp;
    }

    @Override
    public void generate(int x, int y,Person p) {

    }

    /** Spawns a sleeping monster. It takes bonus damage on the 1st hit.
     *
     * @param x where monster spawns(X coord)
     * @param y where monster spawns(Y coord)
     * @param sleep Overloaded constructor. Monsters are always sleeping if they spawn on the 1st or 2nd floor.
     * @param p The player's character
     */
    public void generate(int x, int y, boolean sleep,Person p)
    {
        System.out.println("You find a sleeping monster. Do you attack it?");
        Scanner z  = new Scanner(System.in);
        String q = z.nextLine();
        if (q.equalsIgnoreCase("yes")) {
                hp -= p.str*2;
            System.out.println("You deal " + p.str + " damage.");
            aggro = true;
            if(hp<=0)
            {
                System.out.println("The monster dies on the spot. Who's the real monster here?");
                alive = false;
                int g = dropGold();
                System.out.println("You gain "+g + " Gold");
                p.gold += g;
            }
            while(alive && aggro) {
                if(hp<=0)
                {
                    alive = false;
                    int g = dropGold();
                    System.out.println("You gain "+g + " Gold");
                    p.gold += g;
                }
                int damage = dealDamage(p);
                System.out.println("The monster bites you. You take " + damage+" damage.");
                p.hp -= damage;
                if(p.hp<=0)
                {
                    System.out.println("You died. Serves you right for hitting a sleeping monster.");
                    Runner.gameOff();
                }
                System.out.println("You have " + p.hp+" health.");
                System.out.println("What do you do now? (Fight, Run)");
                q = z.nextLine();
                if(q.equalsIgnoreCase("run"))
                {
                    damage =(int) dealDamage(p)/p.dex;
                    System.out.println("The monster bites you. You take " + damage+" damage.");
                    p.hp -= damage;
                    aggro = false;
                }

            }
            }
            else{

        }

    }

    /**
     * Calculates the damage taken by the attack
     * @param x The player's character
     * @return returns the damage dealt to the player
     */
    @Override
    public int dealDamage(Person x) {
        return  (attack*2 -x.dex);

    }

    /**
     * randomly gives gold to the player after combat
     * @return the amount of gold that the player gets
     */
    @Override
    public int dropGold() {
        int gold = (int)(Math.random()*(hp+attack+resist)/3);
        return gold;
    }
    public String toString()
    {
        String str="";

        return str;
    }

}
