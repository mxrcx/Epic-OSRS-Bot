import org.rspeer.runetek.adapter.Interactable;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Pickables;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;

import static org.rspeer.runetek.api.commons.Time.sleep;

public class Restore extends Task {

    /**
     * If the Health of the Player is below 60%,
     * and there is a Bread in his/her Inventory, he/she can restore Health
     */
    @Override
    public boolean validate() {

        return Players.getLocal().getHealthPercent() < 60 && Inventory.contains("Bread");
    }

    /**
     * Restore Health of Player by eating the first Bread in the Inventory
     */
    @Override
    public int execute() {
        final Interactable bread = Inventory.getFirst("Bread");

        bread.interact("Eat");

        sleep(Random.mid(800, 1200));

        return 0;
    }
}
