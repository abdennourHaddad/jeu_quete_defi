package src.mazegame.entity.player;

import src.mazegame.item.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Inventory of player who stock some things.
 */
public class Inventory {
    private final Player player;
    private List<Item> items;

    /**
     * Create a new empty inventory for a player
     * @param player A player
     */
    public Inventory(Player player){
        this.player = player;
        this.items = new ArrayList<>();
    }

    /**
     * Get all items in the inventory
     * @return All items in the inventory
     */
    public List<Item> getItems(){
        return this.items;
    }

    /**
     * Add an item to the inventory
     * @param item An item
     */
    public void addItem(Item item){
        this.items.add(item);
    }

    /**
     * Remove an item to the inventory
     * The item should be present in the Inventory
     * @param item An item
     */
    public void removeItem(Item item){
        this.items.remove(item);
    }

    /**
     * Return True if the given Item is in the Inventory.
     * @param item An item to test
     * @return True if present, else False
     */
    public boolean itemIsPresent(Item item) {
        return this.items.contains(item);
    }

    /**
     * Return True if the all the given Item are in the Inventory.
     * @param items A collection of Items
     * @return True if present, else False
     */
    public boolean itemsAreAllPresent(Collection<Item> items) {
        return this.items.containsAll(items);
    }
}
