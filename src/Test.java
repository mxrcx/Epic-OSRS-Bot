import org.rspeer.script.Script;
import org.rspeer.script.ScriptMeta;

@ScriptMeta(name = "Test-Bot", developer = "Marco Schaarschmidt", desc = "This is a Test-Bot.")
public class Test extends Script {
    @Override
    public void onStart() {
        //testing phase
    }

    @Override
    public int loop() {
        return 0;
    }
}
