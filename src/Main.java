import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.TaskScript;


@ScriptMeta(name = "Auto-Combat-Bot", developer = "Marco Schaarschmidt", desc = "This is a Auto-Combat-Bot. It fights the nearest Monster, collects Items and restores Health.", category = ScriptCategory.COMBAT)
public class Main extends TaskScript {

    /**
     * Create all opponents and their loot as valid pickable items
     */
    final Opponent chicken = new Opponent("Chicken", new String[]{"Raw chicken", "Bones", "Feather"});
    final Opponent cow = new Opponent("Cow", new String[]{"Raw beef", "Bones", "Cowhide"});
    final Opponent[] opponents = new Opponent[]{chicken, cow};

    /**
     * Start a Fight, Pickup, ChopTree, Cook and Restore Task
     */
    @Override
    public void onStart() {
        submit(new Fight(opponents), new Pickup(opponents), new ChopTree(), new Cook(), new Restore());
    }
}
