
/* 
 * 12w40a/b notice:
 * 		This class is FULL of errors in the snapshot release and there is nothing I can do about it until the referenced mods are updated.
 */

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.ForgeHooks;

import org.lwjgl.opengl.GL11;


public class WMLLCompatibility extends ati {
	
	private int i;

	public static int forgeA(Minecraft d) {
		int a = d.g.aT();
		if (WMLL.i.useForge)
			try {
				return ForgeHooks.getTotalArmorValue(WMLL.i.entityPlayer()); // 12W40 & 1.4PRE ERROR
			}
		catch (IllegalAccessError e) { return a; }
		catch (NoClassDefFoundError e) { return a; }
		catch (NoSuchFieldError e) { return a; }
		else
			return a;
	}
	
	public void RadarBroRun(Minecraft i, WMLL w) {
		 if(RadarBro.RadarEnabled)
	        {
			 	this.i = w.getWindowSize().a();
	            RadarBro OCR = new RadarBro(i, null);
	            GL11.glDisable(2929);
	            GL11.glPushMatrix();
	            GL11.glTranslatef((this.i - 65) + (GuiRepositionRadar.xDisplacement + GuiRepositionRadar.xEndDisplacement), 65 + (GuiRepositionRadar.yDisplacement + GuiRepositionRadar.yEndDisplacement), 0.0F);
	            RadarBro.drawCircle(0, 0, 63D, 0x80000000, true);
	            RadarBro.drawCircle(0, 0, 63D, 0x80646562, false);
	            RadarBro.drawCircle(0, 0, 43D, 0x80646562, false);
	            RadarBro.drawCircle(0, 0, 22D, 0x80646562, false);
	            GL11.glLineWidth(2.0F);
	            GL11.glDisable(3553);
	            GL11.glDisable(2896);
	            GL11.glBegin(1);
	            GL11.glVertex2d(0.0D, -63D);
	            GL11.glVertex2d(0.0D, 63D);
	            GL11.glVertex2d(-63D, 0.0D);
	            GL11.glVertex2d(63D, 0.0D);
	            GL11.glVertex2d(-44.5D, -44.5D);
	            GL11.glVertex2d(44.5D, 44.5D);
	            GL11.glVertex2d(-44.5D, 44.5D);
	            GL11.glVertex2d(44.5D, -44.5D);
	            GL11.glEnd();
	            GL11.glDisable(3042);
	            @SuppressWarnings("unused")
				RadarBro _tmp = OCR; // Not needed?
	            RadarBro.drawTriangle(0, 0, 0xffc0c0c0);
	            if(RadarBro.RadarAutoRotate)
	                GL11.glRotatef((float) -i.h.y, 0.0F, 0.0F, 1.0F);
	            OCR.drawIconRadar();
	            GL11.glPopMatrix();
	        }
			
	}
	
	public void drawSaturationBar(Minecraft mc, WMLL wmll) {
		mc.I.c("saturation");
		int sat = (int)mc.g.bY().e(); // Cannot find the corresponding method in 1.4 pre...
		int var19 = wmll.getWindowSize().a() / 2 + 91;
		int var47 = wmll.getWindowSize().b() - 39;
		int var22 = var47 - 10;
		GL11.glBindTexture(3553, mc.o.b("/gui/icons.png"));
		 for(int var99 = 0; var99 < 10; var99++)
         {
             int var26 = var22;
             int var52 = 16;
             byte var53 = 0;
             int var29 = var19 - var99 * 8 - 9;
             b(var29, var26, 16 + var53 * 9, 27, 9, 9);
             if(var99 * 2 + 1 < sat)
                 b(var29, var26, var52 + 36 + 18, 27, 9, 9);
             if(var99 * 2 + 1 == sat)
                 b(var29, var26, var52 + 45 + 18, 27, 9, 9);
         }
	}

	
}
