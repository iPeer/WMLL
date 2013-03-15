
import java.util.Arrays;
import java.util.regex.Pattern;

import net.minecraft.client.Minecraft;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class WMLLRenderer extends awr {

	public String updateVersion = "0";
	public String updateMCVersion = "1.0";
	private int StringY = 30, notifyTick = 200, StringY1 = 50, notifyTick1 = 800;
	public Minecraft mc;
	private WMLL wmll;
	public boolean notifyUpdate;
	public boolean firstRun;

	public WMLLRenderer(Minecraft m, WMLL w) {
		this.mc = m;
		this.wmll = w;
	}

	public void tick() {
		if (notifyUpdate) {
			if (mc.r == null)
				notifyTick--;
			if (notifyTick < 10)
				StringY--;
			if (StringY <= -13)
				notifyUpdate = false;
			String a = "WMLL Stable \247c"+updateVersion+"\247f for Minecraft\247c "+updateMCVersion+"\247f is available!";
			String t = Pattern.compile("\247[0-9a-f,l-o,r]").matcher(a).replaceAll("");
			wmll.drawStringUsingPixels(a, (wmll.getWindowSize().a() - wmll.getFontRenderer().a(t)) / 2, StringY, 0xffffff);
		}
		if (firstRun) {
			String a = "";
			if (mc.s == null)
				notifyTick1--;
			if (notifyTick1 >= 500)
				a = "To cycle WMLL's outputs, press \247c"+Keyboard.getKeyName(WMLL.F4Key)+"\247e.";
			else if (notifyTick1 < 500 && notifyTick1 >= 200)
				a = "To configure, hit \247c"+Keyboard.getKeyName(Keyboard.KEY_ESCAPE)+"\247r and press \"WMLL Options...\" or press \247c"+Keyboard.getKeyName(29)+"\247r & \247c"+Keyboard.getKeyName(WMLL.F4Key)+"\247r.";
			else
				a = "Thanks for using WMLL and have a nice day! :)";
			if (notifyTick1 < 10) {
				StringY1 -= 2;
			}
			if (StringY1 <= -15) {
				firstRun = false;
				StringY1 = 50;
				notifyTick1 = 800;
				return;
			}
			if (StringY1 >= -14) {
				String t = Pattern.compile("\247[0-9a-f,l-o,r]").matcher(a).replaceAll("");
				wmll.drawStringUsingPixels(a, (wmll.getWindowSize().a() - wmll.getFontRenderer().a(t)) / 2, StringY1, 0xffffff);
			}
		}
	}

	public void renderLightImage(int i) {
		mc.p.b("/ipeer/wmll/imagesheet.png");
		GL11.glColor3f(1, 1, 1);
		//(x, y, imageposx, imageposy, imagew, imageh);
		int pos = WMLL.outputLocation;
		if (pos == 0) {
			drawImage(-5, -5, 0, 0, 16, 16);
			return;
		}
		else if (pos == 1) {
			drawImage(0, -5, 0, 0, 10, 16);
			return;
		}
		else if (pos == 2) {
			drawImage(-4, 1, 0, 0, 16, 16);
			return;
		}
		drawImage(-6, 1, 0, 0, 16, 16);
	}

	public void renderNetherImages(boolean s) {
		if (s) {
			//drawImage(2, 32, 160, 224, 16, 16); // Pigmen
			drawImage(2, 14, 176, 240, 16, 16); // Ghast
			return;
		}
		drawImage(2, 14, 192, 240, 16, 16); // Ghast
		//drawImage(2, 32, 160, 240, 16, 16); // Pigmen
	}

	public void renderIndicatorImages(int i, int j, int k, boolean s, boolean c) {
		mc.p.b("/ipeer/wmll/imagesheet.png");
		GL11.glColor3f(1, 1, 1);
		//(x, y, imageposx, imageposy, imagew, imageh);
		// Hostile mobs
		if ((i < 8) && !WMLL.blockBlackList.contains(j) && k == 0 && j != 110)
			//drawImage(2, 14, 3, 19, 10, 10);
			drawImage(2, 14, 224, 224, 16, 16);
		else if (k == 1 && !WMLL.blockBlackList.contains(j)) // Endermen (End)
			drawImage(2, 14, 208, 224, 16, 16);
		else if (k == 1 && WMLL.blockBlackList.contains(j)) // Endermen (End)
			drawImage(2, 14, 208, 208, 16, 16);
		else if (i < 13 && k == -1 && !WMLL.blockBlackList.contains(j)) // Blaze (Nether)
			drawImage(2, 50, 176, 224, 16, 16);
		else if ((i > 12 && k == -1) || (k == -1 && WMLL.blockBlackList.contains(j))) // Blaze (Nether)
			drawImage(2, 50, 192, 224, 16, 16);
		else
			//drawImage(2, 14, 19, 19, 10, 10);
			drawImage(2, 14, 240, 224, 16, 16);
		if (k == -1) // Everything Else (Nether)
			renderNetherImages(!WMLL.blockBlackList.contains(j));
		// Slimes
		if (k == 0)
			if ((wmll.getPlayerCoordinates()[2] - 1) < 41 && !WMLL.blockBlackList.contains(j) && s)
				drawImage(20, 14, 32, 32, 16, 16);
			else 
				drawImage(20, 14, 48, 32, 16, 16);
		else if (k == -1)
			if (!WMLL.blockBlackList.contains(j))
				drawImage(20, 14, 64, 32, 16, 16); // Magma Cube			
			else
				drawImage(20, 14, 80, 32, 16, 16); // Magma Cube
		else
			drawImage(20, 14, 48, 32, 16, 16);
		// Trees
		if (i > 8 && Arrays.asList(2, 3).contains(j)) 
			drawImage(38, 14, 0, 48, 16, 16);
		else
			drawImage(38, 14, 16, 48, 16, 16);
		// Animals
		//if (k == 0)
		if (i > 8 && j == 2 && k == 0)
			drawImage(2, 32, 224, 240, 16, 16);
		else if (i < 9 && c && j == 2 && k == 0)
			drawImage(2, 32, 208, 240, 16, 16);
		else if (k == -1 && !WMLL.blockBlackList.contains(j)) // Pigmen
			drawImage(2, 32, 160, 224, 16, 16);
		else if (k == -1 && WMLL.blockBlackList.contains(j)) // Pigmen
			drawImage(2, 32, 160, 240, 16, 16);
		else
			drawImage(2, 32, 240, 240, 16, 16);
		// Crops
		if (i > 8 && j == 60)
			drawImage(20, 32, 32, 0, 16, 16);
		else
			drawImage(20, 32, 48, 0, 16, 16);
		// Mushrooms
		if ((i < 13 || j == 110) && !WMLL.blockBlackList.contains(j))
			drawImage(38, 32, 0, 64, 16, 16);
		else
			drawImage(38, 32, 16, 64, 16, 16);

	}

	private void drawImage(int x, int y, int imageposx, int imageposy, int imagewidth, int imageheight) {
		int pos = WMLL.outputLocation;
		int width = wmll.getWindowSize().a();
		int height = wmll.getWindowSize().b();
		if (WMLL.WMLLI > 3)
			y-=12;
		if (pos == 1) {
			// drawtext.a(t, size.a() - (drawtext.a(t) + (i - 2)), textpos+(j*10), k);
			b(width - (imagewidth + x), y, imageposx, imageposy, imagewidth, imageheight);
			return;
		}
		else if (pos == 2) {
			b(x, height - (imageheight + y), imageposx, imageposy, imagewidth, imageheight);
			return;
		}
		else if (pos == 3) {
			b(width - (imagewidth + x), height - (imageheight + y), imageposx, imageposy, imagewidth, imageheight);
			return;
		}
		b(x, y, imageposx, imageposy, imagewidth, imageheight);
	}


}

