import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.TaskScript;

@ScriptMeta(name = "Test-Bot", developer = "Marco Schaarschmidt", desc = "This is a Test-Bot.", category = ScriptCategory.COMBAT)
public class Test extends TaskScript {
    @Override
    public void onStart() {
        submit(new FightCow(), new Pickup(), new Restore());
    }
}
