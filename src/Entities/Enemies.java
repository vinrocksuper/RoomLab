package Entities;

public interface Enemies {
     /**
      * Calculates the damage the player takes from the monster
      * @param x the player
      * @return the damage dealt to the player
      */
     int counterAttack(Person x);

     /**
      * Calculate the amount of gold dropped by a slain monster
      * @return the amount of gold dropped
      */
     int dropGold();

}
