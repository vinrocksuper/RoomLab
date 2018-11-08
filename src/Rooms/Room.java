package Rooms;

import Entities.Monster;
import Entities.Person;

import static Game.Runner.floor;
import static Game.Runner.gameOff;

public class Room extends Cell {

	public boolean cleared = false;
	public boolean special = false;
	public Monster m;
	public boolean Monsterbeaten = false;
	public Room(int x, int y) {
		super(x, y);
	}

	/**
	 * Method controls the results when a person enters this room.
	 *
	 * @param x the Person entering
	 */
	public void enterRoom(Person x) {
		if (x.poisoned) {
			System.out.println("The poison eats away at you. You take 5 damage.");
			x.hp -= 5;
			System.out.println("You now have " + x.hp + "health");
			if (x.hp <= 0) {

				gameOff();
			}
		}

			System.out.println("You enter a plain old room");
			occupant = x;
			cleared = true;
			x.setxLoc(this.xLoc);
			x.setyLoc(this.yLoc);
		}

	}


