import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.TaskScript;


@ScriptMeta(name = "Auto-Combat-Bot", developer = "Marco Schaarschmidt", desc = "This is a Auto-Combat-Bot. It fights the nearest Monster, collects Items and restores Health.", category = ScriptCategory.COMBAT)
public class Main extends TaskScript {

    /**
     * Create all opponents and the valid pickable items
     */
    final String[] opponents = new String[]{"Goblin", "Dwarf"};
    final String[] pickables = new String[]{"Bones", "Hammer", "Bronze pickaxe", "Bronze sq shield", "Chef's hat", "Water rune", "Earth rune", "Fire rune", "Body rune", "Coins", "Goblin mail"};

    /**
     * Start a Fight, Pickup, ChopTree, Cook and Restore Task
     */
    @Override
    public void onStart() {
        submit(new Fight(opponents, pickables), new Pickup(pickables), new ChopTree(), new Cook(), new Restore());
    }
}
