
import net.acomputerdog.BlazeLoader.mod.Mod;

/**
 *
 * @author iPeer
 */
public class ModWMLL extends Mod {

    @Override
    public String getStringModVersion() { 
	return WMLL.wmllVersion(); 
    }
    
    @Override
    public String getModId() {
	return "ipeer.wmll";
    }
    
    @Override
    public int getIntModVersion() {
	try {
	    return Integer.valueOf(getStringModVersion().split("Stable|Test")[1].trim());
	} catch (StringIndexOutOfBoundsException e) { return 0; }
    }
    
    @Override
    public String getModDescription() {
	return "Light level stuff!";
    }
    
    @Override
    public void eventPostTick() {
	WMLL.i.onTickInGame();
    }
    
}
