import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.TaskScript;
import org.rspeer.ui.Log;


@ScriptMeta(name = "Auto-Combat-Bot", developer = "Marco Schaarschmidt", desc = "This is a Auto-Combat-Bot. It fights the nearest Monster, collects Items and restores Health.", category = ScriptCategory.COMBAT)
public class Test extends TaskScript {
    final String opponentType = "Chicken";
    final String[] validPickableTypes = new String[]{"Raw chicken", "Bones", "Feather"};
    boolean fightIsOver = false;

    /**
     * Start a Fight, Pickup or Restore Task depending on Player's condition
     */
    @Override
    public void onStart() {

        if(!fightIsOver) {
            if(Players.getLocal().getHealthPercent() >= 60) {
                Log.info("fight");

                submit(new Fight(opponentType, validPickableTypes));
                fightIsOver = true;
            }
            else {
                Log.info("restore");

                submit(new Restore());
            }
        } else {
            Log.info("pickup");

            submit( new Pickup(validPickableTypes));
            fightIsOver = false;
        }

        // submit(new FightCow(), new Pickup(), new Restore());
    }
}
