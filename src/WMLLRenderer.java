import java.awt.image.BufferedImage;
import java.util.Arrays;

import net.minecraft.client.Minecraft;

import org.lwjgl.opengl.GL11;

public class WMLLRenderer extends oj {

	public WMLLRenderer(Minecraft m, WMLL w) {
		this.m = m;
		this.w = w;
	}

	public void renderLightImage(int i) {
		m.p.b(m.p.b("/ipeer/wmll/imagesheet.png"));
		GL11.glColor3f(1, 1, 1);
		//(x, y, imageposx, imageposy, imagew, imageh);
		b(-4, -5, 0, 0, 10, 16);
	}

	private int[] getImageData(BufferedImage b) {
		int w = b.getWidth();
		int h = b.getHeight();
		int[] d = new int[w * h];
		b.getRGB(0, 0, w, h, d, 0 ,w);
		return d;		
	}

	public void renderNetherImages(boolean s) {
		if (s) {
			//b(2, 32, 160, 224, 16, 16); // Pigmen
			b(2, 14, 176, 240, 16, 16); // Ghast
			return;
		}
		b(2, 14, 192, 240, 16, 16); // Ghast
		//b(2, 32, 160, 240, 16, 16); // Pigmen
	}

	public void renderIndicatorImages(int i, int j, int k, boolean s, boolean c) {
		m.p.b(m.p.b("/ipeer/wmll/imagesheet.png"));
		GL11.glColor3f(1, 1, 1);
		//(x, y, imageposx, imageposy, imagew, imageh);
		// Hostile mobs
		if ((i < 8) && !WMLL.blockblist.contains(j) && k == 0 && j != 110)
			//b(2, 14, 3, 19, 10, 10);
			b(2, 14, 224, 224, 16, 16);
		else if (k == 1 && !WMLL.blockblist.contains(j)) // Endermen (End)
			b(2, 14, 208, 224, 16, 16);
		else if (k == 1 && WMLL.blockblist.contains(j)) // Endermen (End)
			b(2, 14, 208, 208, 16, 16);
		else if (i < 13 && k == -1 && !WMLL.blockblist.contains(j)) // Blaze (Nether)
			b(2, 50, 176, 224, 16, 16);
		else if ((i > 12 && k == -1) || (k == -1 && WMLL.blockblist.contains(j))) // Blaze (Nether)
			b(2, 50, 192, 224, 16, 16);
		else
			//b(2, 14, 19, 19, 10, 10);
			b(2, 14, 240, 224, 16, 16);
		if (k == -1) // Everything Else (Nether)
			renderNetherImages(!WMLL.blockblist.contains(j));
		// Slimes
		if (k == 0)
			if (WMLL.playerYBase < 41 && !WMLL.blockblist.contains(j) && s)
				b(20, 14, 32, 32, 16, 16);
			else 
				b(20, 14, 48, 32, 16, 16);
		else if (k == -1)
			if (!WMLL.blockblist.contains(j))
				b(20, 14, 64, 32, 16, 16); // Magma Cube			
			else
				b(20, 14, 80, 32, 16, 16); // Magma Cube
		else
			b(20, 14, 48, 32, 16, 16);
		// Trees
		if (i > 8 && Arrays.asList(2, 3).contains(j)) 
			b(38, 14, 0, 48, 16, 16);
		else
			b(38, 14, 16, 48, 16, 16);
		// Animals
		//if (k == 0)
		if (i > 8 && j == 2 && k == 0)
			b(2, 32, 224, 240, 16, 16);
		else if (i < 9 && c && j == 2 && k == 0)
			b(2, 32, 208, 240, 16, 16);
		else if (k == -1 && !WMLL.blockblist.contains(j)) // Pigmen
			b(2, 32, 160, 224, 16, 16);
		else if (k == -1 && WMLL.blockblist.contains(j)) // Pigmen
			b(2, 32, 160, 240, 16, 16);
		else
			b(2, 32, 240, 240, 16, 16);
		// Crops
		if (i > 8 && j == 60)
			b(20, 32, 32, 0, 16, 16);
		else
			b(20, 32, 48, 0, 16, 16);
		// Mushrooms
		if ((i < 13 || j == 110) && !WMLL.blockblist.contains(j))
			b(38, 32, 0, 64, 16, 16);
		else
			b(38, 32, 16, 64, 16, 16);

	}


	public Minecraft m;
	private WMLL w;

}

