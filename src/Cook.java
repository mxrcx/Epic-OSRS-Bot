import org.rspeer.runetek.adapter.Interactable;
import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

import static org.rspeer.runetek.api.commons.Time.sleep;

public class Cook extends Task {

    /**
     * If Inventory contains no eatable item,
     * and if Inventory contains a cookable item and logs and tinderbox, Player can cook
     */
    @Override
    public boolean validate() {
        // Players.getLocal().getHealthPercent() < 60
        return Players.getLocal().getTarget() == null
                && !Inventory.contains(item -> item.containsAction("Eat"))
                && Inventory.contains("Logs")
                && Inventory.contains("Tinderbox")
                && (Inventory.contains("Raw chicken") || Inventory.contains("Raw beef"));
    }

    /**
     * Cook meat and store eatable item in Inventory
     */
    @Override
    public int execute() {

        Log.info("cook: ");

        // Light up camp fire
        Inventory.getFirst("Tinderbox").interact("Use");
        Inventory.getFirst("Logs").interact("Use");

        // Wait until log fire is created
        Time.sleepUntil(() -> SceneObjects.getNearest("Fire") != null, Random.mid(10000, 20000));

        // Cook raw item
        Inventory.getFirst(item -> item.getName().equals("Raw chicken") || item.getName().equals("Raw beef")).interact("Use");
        SceneObjects.getNearest("Fire").interact("Use");

        // Wait until Inventory contains eatable (cooked) Item
        Time.sleepUntil(() -> Inventory.contains(item -> item.containsAction("Eat")), Random.mid(1000, 2000));

        return Random.mid(500, 1000);
    }
}
