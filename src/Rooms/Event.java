package Rooms;

import Entities.Person;

import java.util.Scanner;

import static Game.Runner.gameOff;

public class Event extends Room
{
    private int randomEvent = (int)(Math.random()*5);
    private boolean[] eventClear = {false,false,false,false,false};


    public Event(int x, int y)
    {
        super(x, y);

    }
    public void enterRoom(Person x)
    {

        if(x.poisoned)
        {
            System.out.println("The poison eats away at you. You take 5 damage.");
            x.hp -= 5;
            System.out.println("You now have " + x.hp + " health");
            if(x.hp<=0)
            {
                gameOff();
            }
        }
        getEvent(x);
        occupant = x;
        x.setxLoc(this.xLoc);
        x.setyLoc(this.yLoc);

    }
    public void leaveRoom(Person x)
    {
        occupant = null;
    }
    private void getEvent(Person x)
    {
        String[] events = {"You found a map!","You accidentally stub your toe.","You find a treasure chest!","There's a potion on the ground.","You hit your head and now you have amnesia.", "You find a small pouch of gold on the ground"};
        String str = events[randomEvent];
        System.out.println(str);
        if(randomEvent == 4)
        {
            if(!eventClear[4]) {
                x.amnesia = true;
                if (x.pill) {
                    System.out.println("How peculiar, even though your head hurts, you can recall everything just fine.");
                    x.amnesia = false;
                }

                eventClear[4] = true;
            }
        }
        if(randomEvent == 0)
        {
            x.map = true;
            eventClear[0]=true;
        }
        if(randomEvent == 1)
        {
            System.out.println("You take 10 damage.");
            x.hp -= 10;
            System.out.println("You now have " + x.hp + "health");
            eventClear[1]=true;
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
                    System.out.println("You now have " + x.gold + " gold");
                }
                else
                {
                    System.out.println("The chest was trapped! You took 50 damage!");
                    x.hp -= 50;
                }
                eventClear[2]=true;
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
                    if(x.poisoned)
                    {
                        x.poisoned = false;
                        System.out.println("The potion also cured your poison status");
                    }
                    if(x.amnesia)
                    {
                        x.amnesia = false;
                        System.out.println("The potion cleared your amnesia");
                    }
                    x.hp =100;
                }
                eventClear[3]=true;
            }
            else
            {
                System.out.println("You ignore the potion and move on.");
            }

        }
        if(randomEvent == 5)
        {
            x.gold += 10;
            System.out.println("You now have " + x.gold + "gold.");
        }
        this.cleared = true;
    }

}
