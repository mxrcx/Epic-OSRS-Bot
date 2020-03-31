import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;

public class ChopTree extends Task {

    /**
     * If Inventory contains no logs, chop a tree
     */
    @Override
    public boolean validate() {
        // Players.getLocal().getHealthPercent() < 60
        return Inventory.contains("Logs") == false
                && Inventory.contains("Bronze axe")
                && Players.getLocal().getTarget() == null
                && SceneObjects.getNearest("Tree") != null;
    }

    @Override
    public int execute() {

        SceneObjects.getNearest("Tree").interact("Chop down");

        // Wait until Tree is chopped down
        Time.sleepUntil(() -> Inventory.contains("Logs"), Random.mid(1000, 2000));

        return Random.mid(500, 1000);
    }
}
