package Game;

import People.Person;
import Rooms.Room;
import Rooms.TrapRoom;
import Rooms.WinningRoom;

import javax.xml.bind.SchemaOutputResolver;
import java.util.Scanner;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;

public class Runner {
	

	private static boolean gameOn = true;
	public static boolean floorClear = false;
	public static void main(String[] args) {
		int a = 3;
		int b = 3;
		Room[][] building = new Room[3][3];
		Board dungeon = new Board(building);
		//Fill the building with normal rooms
		for (int x = 0; x < building.length; x++) {
			for (int y = 0; y < building[x].length; y++) {
				building[x][y] = new Room(x, y);

			}
		}
		dungeon.generateSpecial();
		//Setup player 1 and the input scanner
		Person player1 = new Person(0, 0, 100);
		building[0][0].enterRoom(player1);
		Scanner in = new Scanner(System.in);
		System.out.println(dungeon.toString(player1));

		while (gameOn && player1.hp>0) {
			if (floorClear) {
				System.out.println("Where would you like to move? (Choose W,A,S,D)");
				String move = in.nextLine();
				if (validMove(move, player1, building)) {
					System.out.println(dungeon.toString(player1));
				}

				if (move.equalsIgnoreCase("y") || move.equalsIgnoreCase("yes")) {
					a++;
					b++;
					building = new Room[a][b];
					dungeon = new Board(building);
					//Fill the building with normal rooms
					for (int x = 0; x < building.length; x++) {
						for (int y = 0; y < building[x].length; y++) {
							building[x][y] = new Room(x, y);

						}
					}
					dungeon.generateSpecial();
					floorClear =false;
					player1.map = false;
					if(player1.poisoned) {
						player1.poisoned = false;
						System.out.println("The effects of the poison wore off");
					}
					building[player1.getxLoc()][player1.getyLoc()].enterRoom(player1);
					System.out.println(dungeon.toString(player1));
				}
			}
			if (!floorClear) {
				{
					System.out.println("Where would you like to move? (Choose W,A,S,D)");
					String move = in.nextLine();
					if (validMove(move, player1, building)) {
						System.out.println(dungeon.toString(player1));
					}
				}
			}
			else{
					System.out.println("Please choose a valid move.");
				}
			}
			in.close();
		}


	/**
	 * Checks that the movement chosen is within the valid game map.
	 * @param move the move chosen
	 * @param p person moving
	 * @param map the 2D array of rooms
	 * @return
	 */
	public static boolean validMove(String move, Person p, Room[][] map)
	{
		move = move.toLowerCase().trim();
		switch (move) {
			case "w":
				if (p.getxLoc() > 0)
				{
					map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
					map[p.getxLoc()-1][p.getyLoc()].enterRoom(p);
					return true;
				}
				else
				{
					return false;
				}
			case "d":
				if (p.getyLoc()< map[p.getyLoc()].length -1)
				{
					map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
					map[p.getxLoc()][p.getyLoc() + 1].enterRoom(p);
					return true;
				}
				else
				{
					return false;
				}

			case "s":
				if (p.getxLoc() < map.length - 1)
				{
					map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
					map[p.getxLoc()+1][p.getyLoc()].enterRoom(p);
					return true;
				}
				else
				{
					return false;
				}

			case "a":
				if (p.getyLoc() > 0)
				{
					map[p.getxLoc()][p.getyLoc()].leaveRoom(p);
					map[p.getxLoc()][p.getyLoc()-1].enterRoom(p);
					return true;
				}
				else
				{
					return false;
				}
			default:
				break;
					
		}
		return true;
	}
	public static void gameOff()
	{
		gameOn = false;
	}
	


}
