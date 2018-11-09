package Entities;

/**
 * Person represents the player as they move through the game.
 */
public class Person {

	int xLoc, yLoc;
	public Boolean alive = true;
	public int hp = 100;
	public boolean amnesia = false;
	public boolean map = false;
	public boolean poisoned = false;
	public int gold = 0;
	public boolean pill = false;
	public boolean defuser = false;
	public int dex = 10;
	public int str = 10;

	public int getxLoc() {
		return xLoc;
	}

	public void setxLoc(int xLoc) {
		this.xLoc = xLoc;
	}

	public int getyLoc() {
		return yLoc;
	}

	public void setyLoc(int yLoc) {
		this.yLoc = yLoc;
	}

	public Person (int xLoc, int yLoc,int hp)
	{
		this.xLoc = xLoc;
		this.yLoc = yLoc;
		this.hp =hp;
	}


}
