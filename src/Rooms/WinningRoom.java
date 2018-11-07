package Rooms;

import Game.Board;
import Game.Runner;
import People.Person;

import static Game.Runner.gameOff;

public class WinningRoom extends Room
{

	public WinningRoom(int x, int y) {
		super(x, y);

	}

	/**
	 * Triggers the game ending conditions.
	 * @param x the Person entering
	 */
	@Override
	public  void enterRoom(Person x) {

		occupant = x;
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
		x.setxLoc(xLoc);
		x.setyLoc(yLoc);
		System.out.println("You found the stairs. Do you descend? (Y/N)?");
		Runner.floorClear = true;
		this.cleared = true;
	}
	

}
