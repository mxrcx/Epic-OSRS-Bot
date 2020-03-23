import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.TaskScript;

@ScriptMeta(name = "Test-Bot", developer = "Marco Schaarschmidt", desc = "This is a Test-Bot.")
public class Test extends TaskScript {
    @Override
    public void onStart() {
        submit();
    }
}
