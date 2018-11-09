package Rooms;

import Game.Board;
import Game.Runner;
import Entities.Person;

import java.util.Scanner;

import static Game.Runner.floor;

public class TrapRoom extends Room {
    boolean cleared = false;
    public TrapRoom(int x,int y)
    {
        super(x,y);
        this.type = "trap";
    }

    @Override
    /**
     * The player has to answer a riddle or pass a skill check in order to live.
     * x - refers to the player
     */
    public void enterRoom(Person x) {
        occupant = x;

        while(occupant.alive && !cleared) {
            x.setxLoc(xLoc);
            x.setyLoc(yLoc);
            if (Math.random() > .49) {
                SkillCheck(x);
            } else {
                int randomTrap = (int) ((Math.random()) * 3);
                System.out.println("You've entered a trap room! The doors slam shut behind you. The words on the walls read:");
                System.out.println(riddle(randomTrap));
                Scanner answer = new Scanner(System.in);
                String reply = answer.nextLine();
                if (randomTrap == 0) {
                    if (!reply.equalsIgnoreCase("fire")) {
                        System.out.println(getDeath(randomTrap));
                        Runner.gameOff();
                    }
                    cleared = true;
                    Board.traproomClear = true;


                }
                if (randomTrap == 1) {
                    if (x.defuser) {
                        System.out.println("You take out your trusty defuser and disarm the bomb without worries.");
                        cleared = true;
                        Board.traproomClear = true;
                    }
                    if (!x.defuser) {
                        double guess = Math.random();
                        if (guess > .5) {
                            if (!reply.equalsIgnoreCase("blue wire")) {
                                System.out.println(getDeath(randomTrap));
                                Runner.gameOff();
                            }
                        }
                        if (guess <= .5) {
                            if (!reply.equalsIgnoreCase("red wire")) {
                                System.out.println(getDeath(randomTrap));
                                Runner.gameOff();
                            }
                        }
                        cleared = true;
                        Board.traproomClear = true;
                    }
                }
                if (randomTrap == 2) {
                    if (!reply.equalsIgnoreCase("car")) {
                        System.out.println(getDeath(randomTrap));
                        Runner.gameOff();
                    }
                    cleared = true;
                    Board.traproomClear = true;

                }
            }
        }
    }

    /**
     * Returns a string indicating the player's death matching the trap room type
     * @param x there are parallel arrays here, int x just refers to the correct parallel
     * @return returns the string indicating the death type
     */
    public String getDeath(int x)
    {
        String[] deaths = {"You are incinerated by fire traps."," The room explodes. And so do you.","Poison gas quickly fills the room. You suffocate."};
        String death = deaths[x];
        return death;
    }

    /**
     * Asks the player a riddle.
     * @param z Tells which riddle to ask
     * @return asks the riddle
     */
    public String riddle(int z)
    {
        String[] riddles = {"What grows when you feed it, but dies you give it water?", "Red wire or Blue wire?", "What three letter word starts with gas?"};
        String str = riddles[z];
        return str;
    }

    public void SkillCheck(Person p) {
        System.out.println("The floor begins to crumble beneath you. You can either try to RUN away, or to HOLD onto something.");
        Scanner t = new Scanner(System.in);
        String z = t.nextLine();

            if (z.equalsIgnoreCase("RUN")) {
                if (floor > 2) {
                    if (p.dex / 2 > Math.random() * 20) {
                        System.out.println("You successfully run to a safe area.");
                        cleared = true;
                        Board.traproomClear = true;
                    } else {
                        System.out.println("You trip on a pebble and fall into an endless void.");
                        occupant.alive = false;
                        Runner.gameOff();

                    }


                    }
                else{
                    if (p.dex / 1.1 > Math.random() * 10) {
                        System.out.println("You successfully run to a safe area.");
                        cleared = true;
                        Board.traproomClear = true;
                    } else {
                        System.out.println("You trip on a pebble and fall into an endless void.");
                        occupant.alive = false;
                        Runner.gameOff();

                    }
                }
            }

        if (z.equalsIgnoreCase("HOLD")) {
            if (floor > 2) {
                if (p.dex / 2 > Math.random() * 20) {
                    System.out.println("You successfully hold onto a piece of furniture.");
                    cleared = true;
                    Board.traproomClear = true;
                } else {
                    System.out.println("Your grip gives out and you plummet to your death.");
                    occupant.alive = false;
                    Runner.gameOff();

                }


            }
            else{
                if (p.dex / 1.1 > Math.random() * 10) {
                    System.out.println("You successfully hold onto a piece of furniture.");
                    cleared = true;
                    Board.traproomClear = true;
                } else {
                    System.out.println("Your grip gives out and you plummet to your death.");
                    occupant.alive = false;
                    Runner.gameOff();
                }
            }
        }
    }


}

