package Rooms;

import Entities.Monster;
import Entities.Person;
import Game.Runner;

import java.util.Scanner;

import static Game.Runner.floor;
import static Game.Runner.gameOff;

public class MonsterRoom extends Room {
    private Monster m;

    public MonsterRoom(int x, int y, Monster m) {
        super(x, y);
        this.m = m;
        this.type = "Monster";

    }

    /**
     * Spawns a monster.
     * If the monster is awake (Floor 3 and over), it attacks you upon entering
     * If the monster is asleep (Floor 2 and under), the player has the option of fighting it. The first attack gets bonus damage.
     *
     * @param p The player's character
     */

    public void enterRoom(Person p) {
        if (p.poisoned) {
            System.out.println("The poison eats away at you. You take 5 damage.");
            p.hp -= 5;
            System.out.println("You now have " + p.hp + "health");
            if (p.hp <= 0) {

                gameOff();
            }
        }
        occupant = p;
        p.setxLoc(this.xLoc);
        p.setyLoc(this.yLoc);
        Scanner n = new Scanner(System.in);

        if (!m.sleep) {
            if (m.alive) {
                System.out.println("There's a monster in the room!");
            } else {
                System.out.println("The monster's remains lies before you.");
            }
            m.aggro = true;
            while (m.alive && m.aggro) {
                if (m.hp <= 0) {
                    m.alive = false;
                    int g = m.dropGold();
                    System.out.println("You gain " + g + " Gold");
                    p.gold += g;
                }
                int damage = m.counterAttack(p);
                System.out.println("The monster bites you. You take " + damage + " damage.");
                p.hp -= damage;
                if (p.hp <= 0) {
                    System.out.println("You died. No one knows you died.");
                    Runner.gameOff();
                }
                System.out.println("You have " + p.hp + " health.");
                System.out.println("What do you do now? (Fight, Run)");
                String re = n.nextLine();
                if (re.equalsIgnoreCase("run")) {
                    damage = (int) (m.counterAttack(p) / p.dex);
                    System.out.println("The monster bites you. You take " + damage + " damage.");
                    p.hp -= damage;
                    m.aggro = false;
                }
                if (re.equalsIgnoreCase("Fight")) {
                    double dam = (p.str + p.str * Math.random()) / (m.resist + 1);

                    m.hp -= dam;
                    System.out.println("You deal " + dam + "damage.");
                    if (m.hp <= 0) {
                        m.alive = false;
                        System.out.println("The monster howls as you deal a finishing blow.");
                        int g = m.dropGold();
                        System.out.println("You gain " + p.gold + " Gold");
                        p.gold += g;
                        m.threat = false;
                        p.exp += (m.attack + m.resist) * floor;
                        System.out.println("You gain " + (m.attack + m.resist) * floor + " EXP");
                        if (p.exp >= 100) {
                            p.exp -= 100;
                            p.level++;
                            System.out.println("You leveled up! You gain +1 Strength and +1 Dexterity");
                            p.str++;
                            p.dex++;
                        }
                    }
                }
            }
        }
        if (m.alive) {
            if (m.sleep) {
                System.out.println("You find a sleeping monster. Do you attack it? (Y/N)");

                String q = n.nextLine();
                if (q.equalsIgnoreCase("yes") || q.equalsIgnoreCase("y")) {

                    double dam = p.str * (1.5) + p.str * Math.random();
                    m.hp -= dam;
                    System.out.println("You deal " + dam + " damage.");
                    m.aggro = true;
                    if (m.hp <= 0) {
                        System.out.println("The monster dies on the spot. Who's the real monster here?");
                        m.alive = false;
                        int g = m.dropGold();
                        System.out.println("You gain " + g + " Gold");

                        p.gold += g;
                        System.out.println("You now have " + p.gold + " Gold");
                        p.exp += (m.attack + m.resist) * floor;
                        System.out.println("You gain " + (m.attack + m.resist) * floor + " EXP");
                        if (p.exp >= 100) {
                            p.exp -= 100;
                            p.level++;
                            System.out.println("You leveled up! You gain +1 Strength and +1 Dexterity");
                            p.str++;
                            p.dex++;
                        }
                    }
                    while (m.alive && m.aggro) {
                        if (m.hp <= 0) {
                            m.alive = false;
                            int g = m.dropGold();
                            System.out.println("You gain " + g + " Gold");
                            p.gold += g;
                            System.out.println("You now have " + p.gold + "Gold");
                        }
                        int damage = m.counterAttack(p);
                        System.out.println("The monster bites you. You take " + damage + " damage.");
                        p.hp -= damage;
                        if (p.hp <= 0) {
                            System.out.println("You died. Serves you right for hitting a sleeping monster.");
                            Runner.gameOff();
                        }
                        System.out.println("You have " + p.hp + " health.");
                        System.out.println("What do you do now? (Fight, Run)");
                        q = n.nextLine();
                        if (q.equalsIgnoreCase("run")) {
                            damage = (m.counterAttack(p) / p.dex);
                            System.out.println("The monster bites you. You take " + damage + " damage.");
                            p.hp -= damage;
                            m.aggro = false;
                        }
                        if (q.equalsIgnoreCase("Fight")) {
                            dam = (p.str + p.str * Math.random()) / (m.resist + 1);
                            m.hp -= dam;
                            System.out.println("You deal " + dam + "damage.");
                            if (m.hp <= 0) {
                                m.alive = false;
                                System.out.println("The monster howls as you deal a finishing blow.");
                                int g = m.dropGold();
                                System.out.println("You gain " + g + " Gold");
                                p.gold += g;
                                System.out.println("You now have " + p.gold + " Gold");
                                m.threat = false;
                                p.exp += (m.attack + m.resist) * floor;
                                System.out.println("You gain " + (m.attack + m.resist) * floor + " EXP");
                                if (p.exp >= 100) {
                                    p.exp -= 100;
                                    p.level++;
                                    System.out.println("You leveled up! You gain +1 Strength and +1 Dexterity");
                                    p.str++;
                                    p.dex++;
                                }
                            }
                        }

                    }
                } else {
                    System.out.println("You let the sleeping monster be.");
                    m.threat = false;
                    n.close();
                }

            }
        }
        else
        {
            System.out.println("The monster's remains are still here.");
        }
    }
}

