import org.rspeer.runetek.adapter.scene.Pickable;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Pickables;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import static org.rspeer.runetek.api.commons.Time.sleep;

public class Pickup extends Task {
    final String[] pickables;

    /**
     * Constructor
     */
    public Pickup(String[] pickables){
       this.pickables = pickables;
    }

    /**
     * If there is a Pickable next to the Player, the Player is not ion a Combat,
     * and has free Inventory Slots, he/she can take the Pickable(Loot)
     */
    @Override
    public boolean validate() {

        return Pickables.getNearest(nearest -> nearest.isPositionInteractable() && nearest.distance() < 5 && checkPickableTypes(nearest)) != null
                && Inventory.getFreeSlots() > 0
                && Players.getLocal().getHealthPercent() >= 60
                && Players.getLocal().getTarget() == null;
    }

    /**
     * Check if the type of the nearest Pickable equals one of the valid types
     */
    private boolean checkPickableTypes(Pickable nearest){
        for (String type : pickables) {
            if (nearest.getName().equals(type)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Take the Pickable(Loot) and store in Inventory
     */
    @Override
    public int execute() {
        final Pickable item = Pickables.getNearest(nearest -> nearest.isPositionInteractable() && checkPickableTypes(nearest));
        final int remainingSlots = Inventory.getFreeSlots();

        Log.info("pickup: " + item);

        // Perform the click action in the game
        if(item != null) {
            item.interact("Take");
            Time.sleepUntil(() -> Inventory.getFreeSlots() != remainingSlots, Random.mid(4000, 5000));
        }

        return Random.mid(800, 1300);
    }
}
