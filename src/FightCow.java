import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;

/**
 * Marco's task
 */
public class FightCow extends Task {
    @Override
    public boolean validate() {
        return Npcs.getNearest(nearest -> nearest.isPositionInteractable() && nearest.getName().equals("Cow")) != null;
    }

    @Override
    public int execute() {
        Npc cow = Npcs.getNearest(nearest -> nearest.isPositionInteractable() && nearest.getName().equals("Cow"));

        while (cow.getHealthPercent() > 0) {
            cow.interact("Attack");
        }

        return 0;
    }
}
