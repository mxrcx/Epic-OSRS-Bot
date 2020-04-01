import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class ChopTree extends Task {

    /**
     * If Inventory contains no Logs, chop down Tree
     */
    @Override
    public boolean validate() {
        // Players.getLocal().getHealthPercent() < 60
        return Inventory.contains("Logs") == false
                && Inventory.contains("Bronze axe")
                && Players.getLocal().getTarget() == null
                && SceneObjects.getNearest(nearest -> nearest.getName().equals("Tree") && nearest.isPositionInteractable()) != null;
    }

    /**
     * Chop down Tree
     */
    @Override
    public int execute() {
        Log.info("chop down tree");

        final SceneObject tree = SceneObjects.getNearest(nearest -> nearest.getName().equals("Tree") && nearest.isPositionInteractable());
        final int remainingSlots = Inventory.getFreeSlots();

        // Wait until Tree is chopped down
        if(tree != null) {
            tree.interact("Chop down");
            Time.sleepUntil(() -> Inventory.getFreeSlots() != remainingSlots, Random.mid(4000, 5000));
        }

        return Random.mid(800, 1300);
    }
}
