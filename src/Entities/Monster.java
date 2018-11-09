package Entities;

import Game.Runner;
import Rooms.Room;

import java.util.Scanner;

public class Monster implements Enemies
{


    public int hp;
    public int attack;
    public int resist;
    public boolean sleep = false;
    public boolean aggro = false;
    public boolean alive = true;
    public boolean threat = true;
    public Monster(int health,int att, int res)
    {

        hp = health;
        attack = att;
        resist = res;
    }
    public Monster(int health,int att, int res, boolean slp)
    {

        hp = health;
        attack = att;
        resist = res;
        sleep = slp;
    }




    /**
     * Calculates the damage taken by the attack
     * @param x The player's character
     * @return returns the damage dealt to the player
     */
    @Override
    public int counterAttack(Person x) {
        return  (attack*2 -x.dex);

    }

    /**
     * randomly gives gold to the player after combat
     * @return the amount of gold that the player gets
     */
    @Override
    public int dropGold() {
        int gold = (int)(Math.random()*(30+attack+resist)/3);
        return gold +5 ;
    }
    

}
