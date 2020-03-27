import org.rspeer.runetek.adapter.Interactable;
import org.rspeer.runetek.adapter.scene.Pickable;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Pickables;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;

import static org.rspeer.runetek.api.commons.Time.sleep;

public class Pickup extends Task {

    /**
     * If there is a Pickable next to the Player, the Player is not ion a Combat,
     * and has free Inventory Slots, he/she can take the Pickable(Loot)
     */
    @Override
    public boolean validate() {

        return Pickables.getNearest(nearest -> nearest.isPositionInteractable() && (nearest.getName().equals("Cowhide") || nearest.getName().equals("Raw beef") || nearest.getName().equals("Bones"))) != null
                && Inventory.getFreeSlots() > 0
                && Players.getLocal().getTarget() == null;
    }

    /**
     * Take the Pickable(Loot) and store in Inventory
     */
    @Override
    public int execute() {
        Pickable item = Pickables.getNearest(nearest -> nearest.isPositionInteractable() && (nearest.getName().equals("Cowhide") || nearest.getName().equals("Raw beef") || nearest.getName().equals("Bones")));

        item.interact("Take");

        sleep(Random.mid(800, 1200));

        return 0;
    }
}
