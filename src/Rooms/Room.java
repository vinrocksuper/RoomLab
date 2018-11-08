package Rooms;

import Entities.Person;

import static Game.Runner.gameOff;

public class Room {
	Person occupant;
	public boolean cleared = false;
	int xLoc;
	int yLoc;
	public boolean special = false;
	
	public Room(int x, int y)
	{
		xLoc = x;
		yLoc = y;
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
		System.out.println("You enter a plain old room");
		occupant = x;
		cleared = true;
		x.setxLoc(this.xLoc);
		x.setyLoc(this.yLoc);
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
