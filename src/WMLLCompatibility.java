import net.minecraft.client.Minecraft;

import org.lwjgl.opengl.GL11;


public class WMLLCompatibility {
	
	private int i;

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
	            RadarBro _tmp = OCR; // Not needed?
	            RadarBro.drawTriangle(0, 0, 0xffc0c0c0);
	            if(RadarBro.RadarAutoRotate)
	                GL11.glRotatef(-i.h.u, 0.0F, 0.0F, 1.0F);
	            OCR.drawIconRadar();
	            GL11.glPopMatrix();
	        }
			
	}

	
}
