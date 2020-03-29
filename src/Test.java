import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.TaskScript;
import org.rspeer.ui.Log;


@ScriptMeta(name = "Auto-Combat-Bot", developer = "Marco Schaarschmidt", desc = "This is a Auto-Combat-Bot. It fights the nearest Monster, collects Items and restores Health.", category = ScriptCategory.COMBAT)
public class Test extends TaskScript {
    final String opponentType = "Chicken";
    final String[] validPickableTypes = new String[]{"Raw chicken", "Bones", "Feather"};

    /**
     * Start a Fight, Pickup and Restore Task
     */
    @Override
    public void onStart() {
        submit(new Fight(opponentType, validPickableTypes), new Pickup(validPickableTypes), new Restore());
    }
}
