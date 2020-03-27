import org.rspeer.runetek.api.input.Keyboard;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.TaskScript;

import java.util.concurrent.Future;


@ScriptMeta(name = "Auto-Combat-Bot", developer = "Marco Schaarschmidt", desc = "This is a Auto-Combat-Bot. It fights the nearest Monster, collects Items and restores Health.", category = ScriptCategory.COMBAT)
public class Test extends TaskScript {

    boolean fightIsOver = false;

    /**
     * Start a Fight, Pickup or Restore Task depending on Player's condition
     */
    @Override
    public void onStart() {

        if(!fightIsOver) {
            if(Players.getLocal().getHealthPercent() >= 60) {
                Keyboard.sendText("New Fight!");
                Keyboard.pressEnter();

                submit(new FightCow());
                fightIsOver = true;
            }
            else {
                Keyboard.sendText("Restore Health!");
                Keyboard.pressEnter();

                submit(new Restore());
            }
        } else {
            Keyboard.sendText("Picking up Items!");
            Keyboard.pressEnter();

            submit(new Pickup());
            fightIsOver = false;
        }

        // submit(new FightCow(), new Pickup(), new Restore());
    }
}
