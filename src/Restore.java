import org.rspeer.runetek.adapter.Interactable;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import static org.rspeer.runetek.api.commons.Time.sleep;

public class Restore extends Task {

    /**
     * If the Health of the Player is below 60%,
     * and there is an eatable item in his/her Inventory, he/she can restore Health
     */
    @Override
    public boolean validate() {

        return Players.getLocal().getHealthPercent() < 60 && Inventory.contains(item -> item.containsAction("Eat"));
    }

    /**
     * Restore Health of Player by eating the first Bread in the Inventory
     */
    @Override
    public int execute() {

        final Interactable eatableItem = Inventory.getFirst(item -> item.containsAction("Eat"));
        Log.info("restore: " + eatableItem);

        // Perform the click action in the game
        eatableItem.interact("Eat");

        sleep(Random.mid(1000, 2000));

        return Random.mid(500, 1000);
    }
}
