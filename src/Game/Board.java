package Game;

import People.Person;
import Rooms.Event;
import Rooms.Room;
import Rooms.TrapRoom;
import Rooms.WinningRoom;

public class Board {
    private Room[][] rooms;
    public static boolean traproomClear = false;
    private boolean first = false;
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

    public void generateSpecial()
    {
        //Create a random winning room.
        int x = 0;
        int y = 0;
        while (x == 0 && y == 0) {
            x = (int) (Math.random() * rooms.length);
            y = (int) (Math.random() * rooms.length);
        }
        rooms[x][y] = new WinningRoom(x, y);
        rooms[x][y].special = true;
        //Create a random trap room that can't be the same room as the winning room.
        while (a == 0 && b == 0)
        {
            a =(int) (Math.random() * rooms.length);
            b =(int) (Math.random() * rooms.length);
            if(rooms[a][b].special)
            {
                a=0;
                b=0;
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
    }
    public String toString()
    {
        String str ="";
        for(int i=0;i<rooms.length;i++)
        {
            for(int j=0;j<rooms[i].length;j++)
            {
                str += " [ ] ";
            }
            str += "\n";
        }
        return str;
    }
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
                else
                    str += " [ ] ";
            }
            str += "\n";
        }
        if(p.amnesia && p.map == false)
        {
            str = "";
        }

        return str;
    }



}
