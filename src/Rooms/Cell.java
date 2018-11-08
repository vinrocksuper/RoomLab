package Rooms;

import Entities.Person;

public abstract class Cell {
    Person occupant;
    int xLoc;
    int yLoc;

    public Cell(int x, int y) {
        xLoc = x;
        yLoc = y;
    }
    /**
     * Removes the player from the room.
     * @param x
     */
    public void leaveRoom(Person x)
    {
        occupant = null;
    }


}
