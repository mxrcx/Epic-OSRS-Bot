import org.rspeer.runetek.adapter.scene.Pickable;
import org.rspeer.runetek.api.scene.Pickables;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;

public class Pickup extends Task {
    @Override
    public boolean validate() {

        return Pickables.getNearest(nearest -> nearest.isPositionInteractable() && nearest.getName().equals("Cowhide") || nearest.getName().equals("Raw beef") || nearest.getName().equals("Bones")) != null;
    }

    @Override
    public int execute() {
        Pickable item = Pickables.getNearest(nearest -> nearest.isPositionInteractable() && nearest.getName().equals("Cowhide") || nearest.getName().equals("Raw beef") || nearest.getName().equals("Bones"));

        item.interact("Pick up");

        return 0;
    }
}
