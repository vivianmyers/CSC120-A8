import java.util.ArrayList;

/**
 * Wizard implements to interface Contract
 */
public class Wizard implements Contract {

    public int magic;
    public ArrayList<String> inventory;
    public int xPos = 0;
    public int yPos = 0;
    public double size = 1;
    public ArrayList<String> history;

    /**
     * Constructor for a Wizard
     * 
     * @param magic amount of magic the Wizard has
     */
    public Wizard(int magic) {
        this.magic = magic;
    }

    /**
     * Adds an item to the Wizards inventory if its not already there
     * 
     * @param item item to add to inventory
     */
    public void grab(String item) {

        try {
            if (!inventory.contains(item)) {
                inventory.add(item);
                System.out.println("Picked up " + item);
            } else {
                throw new Exception("You already have " + item);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }

    /**
     * Removes an items from the inventory if it is there
     * 
     * @param item item to remove from inventory
     * @return item removed
     */
    public String drop(String item) {

        try {
            if (inventory.contains(item)) {
                inventory.remove(item);
                System.out.println("Dropped " + item);
            } else {
                throw new Exception("You dont have " + item);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return item;
    }

    /**
     * Examines the spell book if its in the inventory. There is a 70% chance the
     * wizard will learn a new spell
     * 
     * @param item item to examine
     */
    public void examine(String item) {

        try {
            if (inventory.contains("Spell Book") && item.equals("Spell Book")) {
                System.out.println("Examining spell book...");
                System.out.println("Learning spells...");
                double chance = Math.random() * 100 + 1;
                if (chance > 30) {
                    magic += 20;
                    System.out.println("New spell learned! You have gained 20 magic.");
                } else {
                    System.out.println("Failed to learn spells, try again later.");
                }

                inventory.remove("Ancient Scroll");

            } else {
                throw new Exception("You do not have a spell book.");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }

    /**
     * If a wand is in the inventory and the spell name is valid, it will subtract
     * magic if a spell is cast
     * 
     * @param item the spell name
     */
    public void use(String item) {

        if (!inventory.contains("Wand")) {
            throw new RuntimeException("Wand is not pulled out. Please use grab() before using.");
        }

        if (item.equals("Abracadabra") || item.equals("Hocus Pocus")) {
            magic -= 10;
            System.out.println(item);
        } else {
            System.out.println("That is not a proper spell!");
        }
    }

    /**
     * Changes x and y position of the wizard depending on the direction
     * 
     * @param direction determines what position variable to modify
     */
    public boolean walk(String direction) {

        if (direction.equals("North")) {
            yPos += 10;
        }
        if (direction.equals("South")) {
            yPos -= 10;
        }
        if (direction.equals("East")) {
            xPos += 10;
        }
        if (direction.equals("West")) {
            xPos -= 10;
        }

        System.out.println("You walked to " + "(" + xPos + ", " + yPos + ")");
        return true;

    }

    /**
     * Changes x and y position of the wizard if they have enough magic
     * 
     * @param x amount to change xPos by
     * @param y amount to change yPos by
     */
    public boolean fly(int x, int y) {

        if (magic > 20) {

            xPos += x;
            yPos += y;

            System.out.println("You flew to " + "(" + xPos + ", " + yPos + ")");
            return true;
        }

        System.out.println("Not enough magic to fly.");
        return false;
    }

    /**
     * A spell that the wizard can use only on themselves if they have enough magic,
     * decreases the size variable
     * 
     * @return how much they shrunk
     */
    public Number shrink() {
        if (size - .25 == 0 || size - .5 == 0) {
            System.out.println("You are too small! Grow first.");
            return 0;
        }

        if (magic > 50) {
            size -= .5;
            history.add("shrink");
            return .5;

        } else if (magic > 25) {
            size -= .25;
            history.add("shrink");
            return .25;

        } else {
            System.out.println("Not enough magic to shrink!");

        }
        return 0;
    }

    /**
     * A spell that the wizard can use only on themselves if they have enough magic,
     * increases the size variable
     * 
     * @return how much they grew
     */
    public Number grow() {

        if (magic > 50) {
            size += .5;
            history.add("grow");
            return .5;

        } else if (magic > 25) {
            size += .25;
            history.add("grow");
            return .25;

        } else {
            System.out.println("Not enough magic to grow!");

        }
        return 0;
    }

    /**
     * Increases magic
     */
    public void rest() {
        magic += 10;
        System.out.println("+10 magic.");
    }

    /**
     * A wizard can only undo growth and shrinking spells. This method returns the
     * wizard to their original size.
     */
    public void undo() {

        if (history.get(history.size() - 1).equals("grow")) {
            System.out.println("Shrinking back to original size...");
            size = 1;
            return;
        }
        if (history.get(history.size() - 1).equals("shrink")) {
            System.out.println("Growing back to original size...");
            size = 1;
            return;
        }

        System.out.println("You have not used grow() or shrink() yet. There is nothing to undo!");

    }

}
