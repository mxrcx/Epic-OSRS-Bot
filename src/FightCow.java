import org.rspeer.runetek.adapter.Interactable;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.input.Keyboard;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Pickables;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;

/**
 * Marco's task
 */
public class FightCow extends Task {

    /**
     * If a nearest attackable Monster exists,
     * the Player has enough Health and isn't already in a Fight or picking up Loot, he can attack
     */
    @Override
    public boolean validate() {

        return Npcs.getNearest(nearest -> nearest.isPositionInteractable() && nearest.getName().equals("Chicken") && nearest.getTarget() == null) != null
                && Players.getLocal().getTarget() == null
                && Players.getLocal().getHealthPercent() >= 60
                && Pickables.getNearest(nearest -> nearest.getName().equals("Raw chicken") && nearest.distance() < 50) == null; // check if a Pickable is available (fight has ended)
    }

    /**
     * Attack nearest Monster until it is defeated
     */
    @Override
    public int execute() {
        final Npc cow = Npcs.getNearest(nearest -> nearest.isPositionInteractable() && nearest.getName().equals("Chicken") && nearest.getTarget() == null);

        cow.interact("Attack");

        Keyboard.sendText("Neue attacke...");
        Keyboard.pressEnter();

        Time.sleepUntil(() -> cow.getHealthPercent() < 1, Random.mid(800, 1200));

        return 0;
    }
}
