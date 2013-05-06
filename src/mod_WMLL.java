import net.minecraft.client.Minecraft;


public class mod_WMLL extends BaseMod {

	protected WMLL wmll;
	
	public mod_WMLL () {
	}
	
	@Override
	public String getVersion() {
		return WMLL.wmllVersion()+"-"+WMLL.getMinecraftVersion();
	}

	@Override
	public void load() {
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
