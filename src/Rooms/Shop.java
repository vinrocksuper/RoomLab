package Rooms;

import Entities.Person;

import java.util.Scanner;

import static Game.Runner.gameOff;

public class Shop extends Room{
    public String[] items = {"Defuser","Antidote","Map","Health Potion","Mysterious pill"};
    public int[] cost = {25,15,10,25,50};
    public boolean occupied = false;

    public Shop(int x, int y)
    {
        super(x,y);
    }

    /**
     * Method controls the results when a person enters this room.
     * @param x the Person entering
     */
    public void enterRoom(Person x)
    {

        if(x.poisoned)
        {
            System.out.println("The poison eats away at you. You take 5 damage.");
            x.hp -= 5;
            System.out.println("You now have " + x.hp + "health");
            if(x.hp<=0)
            {

                gameOff();
            }
        }
        occupant = x;

        x.setxLoc(this.xLoc);
        x.setyLoc(this.yLoc);
        occupied = true;

            if (!cleared) {
                System.out.println("You find a shop inside this twisted dungeon. The shop keeper nods his head towards you, as if you've met before.(Type exit to leave)");
                System.out.println("You see there's a few items for sale. You have " + x.gold + " gold (Type exit to leave)");
                for (int i = 0; i < items.length; i++) {
                    if (items[i] != null){
                        System.out.println("There is a(n) " + items[i] + " for sale for " + cost[i]);
                }
                }
            }
            if (cleared) {
                System.out.println("Welcome back.");
                System.out.println("You have " +x.gold + " gold");
                for (int i = 0; i < items.length; i++) {
                    if(items[i] != null) {
                        System.out.println("There is a(n) " + items[i] + " for sale for " + cost[i]);
                    }
                }
            }

            Scanner q = new Scanner(System.in);
            String n = q.nextLine();
        while(occupied) {
            if(n.equalsIgnoreCase("exit"))
            {
                System.out.println("you leave the store");
                occupied = false;
            }

            if (n.equalsIgnoreCase(items[1])) {
                System.out.println("Are you sure you want to buy a(n)" + items[1] + "?");
                n = q.nextLine();
                if (n.equalsIgnoreCase("yes") || n.equalsIgnoreCase("y")) {

                    if (x.gold < cost[1]) {
                        System.out.println("you don't have enough money");
                        occupied = false;
                    }
                    else
                    {
                        x.gold -= cost[1];
                        System.out.println("You are no longer poisoned. "+ "You now have " + x.gold + " gold");
                        items[1] = null;
                        x.poisoned = false;
                        occupied = false;
                    }
                }
            }
            if (n.equalsIgnoreCase(items[2])) {
                System.out.println("Are you sure you want to buy " + items[2] + "?");
                n = q.nextLine();
                if (n.equalsIgnoreCase("yes") || n.equalsIgnoreCase("y")) {

                    if (x.gold < cost[2]) {
                        System.out.println("you don't have enough money");
                        occupied = false;
                    }
                    else
                    {
                        x.gold -= cost[2];
                        System.out.println("You acquired " + items[2]+ " You now have " + x.gold + " gold");
                        items[2] = null;
                        x.map = true;
                        occupied = false;
                    }
                }
            }
            if (n.equalsIgnoreCase(items[3])) {
                System.out.println("Are you sure you want to buy " + items[3] + "?");
                n = q.nextLine();
                if (n.equalsIgnoreCase("yes") || n.equalsIgnoreCase("y")) {

                    if (x.gold < cost[3]) {
                        System.out.println("you don't have enough money");
                        occupied = false;
                    }
                    else
                    {
                        x.gold -= cost[3];
                        System.out.println("You feel restored. You gain 50hp. "+ "You now have " + x.gold + " gold");

                        x.hp += 50;
                        System.out.println("You now have " + x.hp + "health");
                        items[3] = null;
                        occupied = false;
                    }
                }
            }
            if (n.equalsIgnoreCase(items[4])) {
                System.out.println("Are you sure you want to buy " + items[4] + "?");
                n = q.nextLine();
                if (n.equalsIgnoreCase("yes") || n.equalsIgnoreCase("y")) {

                    if (x.gold < cost[4]) {
                        System.out.println("you don't have enough money");
                        occupied = false;
                    }
                    else
                    {
                        x.gold -= cost[4];
                        System.out.println("You take the strange pill."+ "You now have " + x.gold + " gold");

                        if(x.amnesia)
                        {
                            System.out.println("Suddenly, your memories start flowing back to you. Your amnesia is cured.");

                        }
                        if(Math.random()>.7)
                        {
                            System.out.println("You feel stronger and faster.");
                            x.dex++;
                            x.str++;
                        }
                        x.pill = true;
                        x.amnesia = false;
                        occupied = false;
                        items[4] = null;
                    }
                }
            }
            if (n.equalsIgnoreCase(items[0])) {
                System.out.println("Are you sure you want to buy " + items[0] + "?");
                n = q.nextLine();
                if (n.equalsIgnoreCase("yes") || n.equalsIgnoreCase("y")) {

                    if (x.gold < cost[0]) {
                        System.out.println("you don't have enough money");
                        occupied = false;
                    }
                    else
                    {
                        x.gold -= cost[0];
                        System.out.println("You acquired a " + items[0]+ " You now have " + x.gold + " gold");
                        items[1] = null;
                        x.defuser = true;
                        occupied = false;
                    }
                }
            }
            if(n.equalsIgnoreCase("exit"))
            {
                System.out.println("you leave the store");
                occupied = false;
            }
        }
    }

    /**
     * Removes the player from the room.
     * @param x
     */
    public void leaveRoom(Person x)
    {
        cleared = true;
        occupant = null;
    }



}
