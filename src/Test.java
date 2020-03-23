import org.rspeer.runetek.adapter.Interactable;
import org.rspeer.runetek.adapter.Positionable;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Pickables;
import org.rspeer.script.Script;
import org.rspeer.script.ScriptMeta;

@ScriptMeta(name = "Test-Bot", developer = "Marco Schaarschmidt", desc = "This is a Test-Bot.")
public class Test extends Script {
    @Override
    public void onStart() {
        // soethign
        //testing phase
    }

    @Override
    public int loop() {
        final Interactable toPick = Pickables.getNearest(Positionable::isPositionInteractable);
        toPick.interact("Take");
        final int freeSpaces = Inventory.getFreeSlots();
        Time.sleepUntil(() -> Inventory.getFreeSlots() != freeSpaces, Random.mid(1000, 2000));
        return Random.mid(800, 1000);
    }
}
