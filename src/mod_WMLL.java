import net.minecraft.client.Minecraft;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;


public class mod_WMLL extends BaseMod {
	
	public mod_WMLL() { }
	
	@Override
	public String getVersion() {
		return WMLL.wmllVersion();
	}

	@Override
	public void load() {
	}
	
	public void preInit(FMLPreInitializationEvent e) {
		e.getModMetadata().version = getVersion();
		e.getModMetadata().modId = "wmll_forge";
		e.getModMetadata().authorList.add("iPeer");
		e.getModMetadata().description = "Enables the player to tell the light level of the block they're stood on (and more!)";
	}
	
	public void modsLoaded() {
		ModLoader.setInGameHook(this, true, false);
		WMLL.i.useML = true;
	}
	
	public boolean onTickInGame(float f, Minecraft mc) {
		WMLL.i.updategui(mc, f);	
		return true;
	}


	
	
}
