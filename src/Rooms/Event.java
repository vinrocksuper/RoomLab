package Rooms;

import People.Person;

import java.util.Scanner;

public class Event extends Room
{
    private int randomEvent = (int)(Math.random()*5);


    public Event(int x, int y)
    {
        super(x, y);

    }
    public void enterRoom(Person x)
    {
        getEvent(x);
        occupant = x;
        x.setxLoc(this.xLoc);
        x.setyLoc(this.yLoc);

    }
    public void leaveRoom(Person x)
    {
        occupant = null;
    }
    public void getEvent(Person x)
    {
        String[] events = {"You found a map!","You accidentally stub your toe.","You find a treasure chest!","There's a potion on the ground.","You hit your head and now you have amnesia."};
        String str = events[randomEvent];
        System.out.println(str);
        if(randomEvent == 4)
        {
            x.amnesia = true;
        }
        if(randomEvent == 0)
        {
            x.map = true;
        }
        if(randomEvent == 1)
        {
            x.hp -= 50;
        }
        if(randomEvent ==2)
        {
            System.out.println("Do you want to open the chest?");
            Scanner a = new Scanner(System.in);
            String rep = a.nextLine();
            if(rep.equalsIgnoreCase("yes")||rep.equalsIgnoreCase("y"))
            {
                double n = Math.random();
                if(n>.5)
                {
                    System.out.println("You find a bag of gold!");
                    x.gold += 50;
                }
                else
                {
                    System.out.println("The chest was trapped! You took 50 damage!");
                    x.hp -= 50;
                }
            }
            else
            {
                System.out.println("You ignore the chest and move on.");
            }

        }
        if(randomEvent == 3)
        {
            System.out.println("Do you want to drink the potion?");
            Scanner a = new Scanner(System.in);
            String rep = a.nextLine();
            if(rep.equalsIgnoreCase("yes")||rep.equalsIgnoreCase("y"))
            {
                double n = Math.random();
                if(n>.5)
                {
                    System.out.println("You are poisoned!");
                    x.poisoned = true;
                }
                else
                {
                    System.out.println("You feel rejuvenated. You are restored to full hp.");
                    if(x.poisoned == true)
                    {
                        x.poisoned = false;
                        System.out.println("The potion also cured your poison status");
                    }
                    if(x.amnesia)
                    {
                        x.amnesia = true;
                        System.out.println("The potion cleared your amnesia");
                    }
                    x.hp =100;
                }
            }
            else
            {
                System.out.println("You ignore the potion and move on.");
            }

        }
        this.cleared = true;
    }

}
