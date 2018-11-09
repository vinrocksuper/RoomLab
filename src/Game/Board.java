package Game;

import Entities.Monster;
import Entities.Person;
import Rooms.*;

import static Game.Runner.floor;

public class Board {
    private Room[][] rooms;
    public static boolean traproomClear = false;
    private boolean first = false;
    int x = 0;
    int y = 0;
    private int a = 0;
    private int b = 0;


    public Board(Room[][] a)
    {
        this.rooms = a;
    }
    public Board(int x,int y)
    {
        rooms = new Room[x][y];
    }

    /**
     * Generates all the special rooms. More trap rooms spawn with each floor after the 2nd.
     *
     */
    public void generateSpecial()
    {
        //Create a random winning room.

        while (x == 0 && y == 0) {
            x = (int) (Math.random() * rooms.length);
            y = (int) (Math.random() * rooms.length);
        }
        rooms[x][y] = new WinningRoom(x, y);
        rooms[x][y].special = true;
        //Create a random trap room that can't be the same room as the winning room.
        while (a == 0 && b == 0)
        {
            if(floor > 2) {
                for (int i = 0; i < rooms.length / 2; i++) {
                    a = (int) (Math.random() * rooms.length);
                    b = (int) (Math.random() * rooms.length);
                    if (rooms[a][b].special) {
                        a = 0;
                        b = 0;
                    }

                rooms[a][b] = new TrapRoom(a, b);
                rooms[a][b].special = true;
                }
            }
            else {
                a = (int) (Math.random() * rooms.length);
                b = (int) (Math.random() * rooms.length);
                if (rooms[a][b].special) {
                    a = 0;
                    b = 0;
                }
            }
        }
        rooms[a][b] = new TrapRoom(a, b);
        rooms[a][b].special = true;
        //Generates multiple event rooms
        for(int i=0;i<4;i++) {
            int c = 0;
            int d = 0;
            while (c == 0 && d == 0) {
                c = (int) (Math.random() * rooms.length);
                d = (int) (Math.random() * rooms.length);
                if (rooms[c][d].special) {
                    c = 0;
                    d = 0;
                }
            }
            rooms[c][d] = new Event(c, d);
            rooms[c][d].special = true;
        }
        int i = 0;
        int j =0;
        while(i == 0 && j ==0)
        {
            i = (int) (Math.random() * rooms.length);
            j = (int) (Math.random() * rooms.length);
            if (rooms[i][j].special) {
                i = 0;
                j = 0;
            }
        }
        rooms[i][j] = new Shop(i,j);
        rooms[i][j].special = true;
        int t = 0;
        int y =0;
        while(t == 0 && y ==0)
        {
            t = (int) (Math.random() * rooms.length);
            y = (int) (Math.random() * rooms.length);
            if (rooms[t][y].special) {
                t = 0;
                y = 0;
            }
        }
        Monster m = null;
        if(floor <=2) {
            m = new Monster(20, 8, 0, true);
        }
        else{
            m = new Monster(20+4*floor,8+5*floor,2*floor);
        }
        rooms[t][y] = new MonsterRoom(t,y,m);
        rooms[t][y].special = true;

    }

    /**
     * Prints out the map
     * @param p tells where the player is
     * @return returns the map to be printed
     */
    public String toString(Person p)
    {
        String str ="";
        for(int i=0;i<rooms.length;i++)
        {
            for(int j=0;j<rooms[i].length;j++)
            {

                if(p.getyLoc()==j && p.getxLoc()==i)
                {
                    str += " [X] ";
                }
                else if(traproomClear && j==b && i==a || p.map && j==b && i==a)
                {
                    str += " [T] ";
                }

                else if(!rooms[i][j].cleared)
                {
                    str += " [?] ";
                }
                else if(Runner.floorClear && j==y && i==x || p.map && j==y && i==x)
                {
                    str += " [V] ";
                }
                else if(rooms[i][j].type.equalsIgnoreCase("MOnster"))
                {
                    str += " [M] ";
                }
                else{
                str += " [ ] ";
            }
            }
            str += "\n";
        }
        if(p.amnesia && !p.map)
        {
            str = "";
        }

        return str;
    }



}
