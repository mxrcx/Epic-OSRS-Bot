import org.rspeer.runetek.adapter.Interactable;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;

/**
 * Marco's task
 */
public class FightCow extends Task {
    @Override
    public boolean validate() {
        return Npcs.getNearest(nearest -> nearest.isPositionInteractable() && nearest.getName().equals("Cow") && nearest.getTarget() == null) != null && Players.getLocal().getTarget() == null;
    }

    @Override
    public int execute() {
        final Interactable cow = Npcs.getNearest(nearest -> nearest.isPositionInteractable() && nearest.getName().equals("Cow"));

        cow.interact("Attack");

        Time.sleepUntil(() -> Players.getLocal().getTarget().getHealthPercent() > 0, 2000);

        return 0;
    }
}
