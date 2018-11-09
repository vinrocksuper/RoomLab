package Entities;

/**
 * Person represents the player as they move through the game.
 */
public class Person {

	int xLoc, yLoc;
	public Boolean alive = true;
	public int hp ;
	public boolean amnesia = false;
	public boolean map = false;
	public boolean poisoned = false;
	public int gold = 0;
	public boolean pill = false;
	public boolean defuser = false;
	public int dex = 10;
	public int str = 10;
	public int exp = 0;
	public int level = 1;
	/**
	 * Gives the player's x Location
	 * @return the x Location
	 */
	public int getxLoc() {
		return xLoc;
	}

	/**
	 * Changes the player's x Location
	 * @param xLoc the new xLocation
	 */
	public void setxLoc(int xLoc) {
		this.xLoc = xLoc;
	}
	/**
	 * gets the player's y Location
	 * @return the y Location
	 */
	public int getyLoc() {
		return yLoc;
	}

	/**
	 * Changes the player's y Location
	 * @param yLoc the new yLocation
	 */
	public void setyLoc(int yLoc) {
		this.yLoc = yLoc;
	}

	public Person (int xLoc, int yLoc,int hp)
	{
		this.xLoc = xLoc;
		this.yLoc = yLoc;
		this.hp = hp;

	}


}
