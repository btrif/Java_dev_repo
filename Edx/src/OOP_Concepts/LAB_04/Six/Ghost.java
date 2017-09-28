package OOP_Concepts.LAB_04.Six;

/**
 * Created by Bogdan Trif on 02-05-2017 , 8:18 AM.
 */
/**
 * This code allows you to represent a generic ghost to be used in a simple game.
 */

public class Ghost extends Enemy {
    public Ghost(String name, String description){
        super(name, description, CHAINS);
    }
}