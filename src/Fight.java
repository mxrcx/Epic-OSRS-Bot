import org.rspeer.runetek.adapter.Interactable;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.adapter.scene.Pickable;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.input.Keyboard;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Pickables;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

/**
 * Marco's task
 */
public class Fight extends Task {
    final String[] opponents, pickables;

    /**
     * Constructor
     */
    public Fight(String[] opponents, String[] pickables) {
        this.opponents = opponents;
        this.pickables = pickables;
    }

    /**
     * If a nearest attackable Monster exists,
     * the Player has enough Health and isn't already in a Fight or picking up Loot, he can attack
     */
    @Override
    public boolean validate() {

        return Npcs.getNearest(nearest -> nearest.isPositionInteractable() && checkOpponentTypes(nearest) && nearest.getTarget() == null) != null
                && Players.getLocal().getTarget() == null
                && Players.getLocal().getHealthPercent() >= 60
                && ((Inventory.getFreeSlots() < 1) || (Pickables.getNearest(nearest -> nearest.isPositionInteractable() && nearest.distance() < 5 && checkPickableTypes(nearest))) == null); // check if a Pickable is available (fight has ended)
    }

    /**
     * Check if the type of the nearest Opponent equals one of the valid types
     */
    private boolean checkOpponentTypes(Npc nearest){
        for (String type : opponents) {
            if (nearest.getName().equals(type)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Check if the type of the nearest Pickable equals one of the valid types
     */
    private boolean checkPickableTypes(Pickable nearest){
        for (String type : pickables) {
            if (nearest.getName().equals(type)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Attack nearest Monster until it is defeated
     */
    @Override
    public int execute() {

        final Npc opponent = Npcs.getNearest(nearest -> nearest.isPositionInteractable() && checkOpponentTypes(nearest) && nearest.getTarget() == null);
        Log.info("fight: " + opponent);

        // Perform the click action in the game
        opponent.interact("Attack");

        // Check if enemy is dead by checking his target
        Time.sleepUntil(() -> opponent.getTarget() == null, Random.mid(10000, 20000));

        return Random.mid(800, 1300);
    }
}
