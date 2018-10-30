package Rooms;

import Game.Runner;
import People.Person;

import java.util.Scanner;

public class TrapRoom extends Room {
    public TrapRoom(int x,int y)
    {
        super(x,y);
    }

    @Override
    public void enterRoom(Person x) {
        occupant = x;
        x.setxLoc(xLoc);
        x.setyLoc(yLoc);
        int randomTrap = (int) ((Math.random())*4);
        System.out.println("You've entered a trap room! The doors slam shut behind you. The words on the walls read:");
        System.out.println(riddle(randomTrap));
        Scanner answer = new Scanner(System.in);
        String reply = answer.nextLine();
        if(randomTrap == 0 )
        {
            if(reply.equalsIgnoreCase("fire"))
            {
                getDeath(randomTrap);
                Runner.gameOff();
            }

        }
        if(randomTrap == 1 )
        {
            if(reply.equalsIgnoreCase("blue wire"))
            {
                getDeath(randomTrap);
                Runner.gameOff();
            }

        }
        if(randomTrap == 2 )
        {
            if(reply.equalsIgnoreCase("e"))
            {
                getDeath(randomTrap);
                Runner.gameOff();
            }

        }
        if(randomTrap == 3 )
        {
            if(reply.equalsIgnoreCase("gas"))
            {
                getDeath(randomTrap);
                Runner.gameOff();
            }

        }

    }
    public String getDeath(int x)
    {
        String[] deaths = {"You are incinerated by fire traps."," The room explodes. And so do you.","You fall into an endless void.","Poison gas fills the room as the doors slam shut."};
        String death = deaths[x];
        return death;
    }
    public String riddle(int z)
    {
        String[] riddles = {"What grows when you feed it, but dies you give it water?", "Red wire or Blue wire?", "I’m am everywhere and a part of everyone. I am at the end of space and time and existence itself. What am I?","What three letter word starts with gas?"};
        String str = riddles[z];
        return str;
    }
    public void leaveRoom(Person x)
    {
        occupant = null;
    }
}
