package Game;

import Rooms.Room;
import Rooms.TrapRoom;
import Rooms.WinningRoom;

public class Board {
    private Room[][] rooms;

    public Board(Room[][] a)
    {
        this.rooms = a;
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
        //Create a random trap room that can't be the same room as the winning room.
        int a = 0;
        int b = 0;
        while (a == 0 && b == 0)
        {
            a =(int) (Math.random() * rooms.length);
            b =(int) (Math.random() * rooms.length);
            if(a == x && b ==x)
            {
                a=0;
                b=0;
            }
        }
        rooms[a][b] = new TrapRoom(a, b);
    }
    public String toString()
    {
        String str ="";
        for(Room[] a : rooms)
        {
            for(Room x:a)
            {
                str += x;
            }
            str += "\n";
        }
        return str;
    }



}
