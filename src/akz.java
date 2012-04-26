import java.awt.Color;
import java.util.Random;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

public class akz extends pl
{

    private static final vd c = new vd();
    private final Random d = new Random();
    private final Minecraft e;
    private final qd f;
    private int h;
    private String i;
    private int j;
    private boolean k;
    public float a;
    public float b;

    public akz(Minecraft minecraft)
    {
        h = 0;
        i = "";
        j = 0;
        k = false;
        b = 1.0F;
        e = minecraft;
        f = new qd(minecraft);
    }

    public void a(float f1, boolean flag, int l, int i1)
    {
        aic aic1 = new aic(e.A, e.d, e.e);
        int j1 = aic1.a();
        int k1 = aic1.b();
        oh oh1 = e.q;
        e.u.b();
        GL11.glEnable(3042);
        if(Minecraft.t())
        {
            a(e.h.a(f1), j1, k1);
        } else
        {
            GL11.glBlendFunc(770, 771);
        }
        acf acf1 = e.h.aq.f(3);
        if(e.A.K == 0 && acf1 != null && acf1.c == qb.ba.bQ)
        {
            a(j1, k1);
        }
        if(!e.h.a(abv.k))
        {
            float f2 = e.h.aT + (e.h.aS - e.h.aT) * f1;
            if(f2 > 0.0F)
            {
                b(f2, j1, k1);
            }
        }
        if(!e.c.e())
        {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glBindTexture(3553, e.p.b("/gui/gui.png"));
            acc acc1 = e.h.aq;
            g = -90F;
            b(j1 / 2 - 91, k1 - 22, 0, 0, 182, 22);
            b((j1 / 2 - 91 - 1) + acc1.c * 20, k1 - 22 - 1, 0, 22, 24, 22);
            GL11.glBindTexture(3553, e.p.b("/gui/icons.png"));
            GL11.glEnable(3042);
            GL11.glBlendFunc(775, 769);
            b(j1 / 2 - 7, k1 / 2 - 7, 0, 0, 16, 16);
            GL11.glDisable(3042);
            boolean flag1 = (e.h.Y / 3) % 2 == 1;
            if(e.h.Y < 10)
            {
                flag1 = false;
            }
            int i2 = e.h.bd();
            int j3 = e.h.bz;
            d.setSeed(h * 0x4c627);
            boolean flag3 = false;
            nz nz1 = e.h.aF();
            int j5 = nz1.a();
            int i6 = nz1.b();
            d();
            if(e.c.d())
            {
                int k6 = j1 / 2 - 91;
                int j7 = j1 / 2 + 91;
                int i8 = e.h.aE();
                if(i8 > 0)
                {
                    char c1 = '\266';
                    int k9 = (int)(e.h.aX * (float)(c1 + 1));
                    int j10 = (k1 - 32) + 3;
                    b(k6, j10, 0, 64, c1, 5);
                    if(k9 > 0)
                    {
                        b(k6, j10, 0, 69, k9, 5);
                    }
                }
                int l8 = k1 - 39;
                int l9 = l8 - 10;
                int k10 = e.h.au();
                int j11 = -1;
                if(e.h.a(abv.l))
                {
                    j11 = h % 25;
                }
                for(int k11 = 0; k11 < 10; k11++)
                {
                    if(k10 > 0)
                    {
                        int j12 = k6 + k11 * 8;
                        if(k11 * 2 + 1 < k10)
                        {
                            b(j12, l9, 34, 9, 9, 9);
                        }
                        if(k11 * 2 + 1 == k10)
                        {
                            b(j12, l9, 25, 9, 9, 9);
                        }
                        if(k11 * 2 + 1 > k10)
                        {
                            b(j12, l9, 16, 9, 9, 9);
                        }
                    }
                    int k12 = 16;
                    if(e.h.a(abv.u))
                    {
                        k12 += 36;
                    }
                    int j13 = 0;
                    if(flag1)
                    {
                        j13 = 1;
                    }
                    int i14 = k6 + k11 * 8;
                    int k14 = l8;
                    if(i2 <= 4)
                    {
                        k14 += d.nextInt(2);
                    }
                    if(k11 == j11)
                    {
                        k14 -= 2;
                    }
                    byte byte3 = 0;
                    if(e.f.B().s())
                    {
                        byte3 = 5;
                    }
                    b(i14, k14, 16 + j13 * 9, 9 * byte3, 9, 9);
                    if(flag1)
                    {
                        if(k11 * 2 + 1 < j3)
                        {
                            b(i14, k14, k12 + 54, 9 * byte3, 9, 9);
                        }
                        if(k11 * 2 + 1 == j3)
                        {
                            b(i14, k14, k12 + 63, 9 * byte3, 9, 9);
                        }
                    }
                    if(k11 * 2 + 1 < i2)
                    {
                        b(i14, k14, k12 + 36, 9 * byte3, 9, 9);
                    }
                    if(k11 * 2 + 1 == i2)
                    {
                        b(i14, k14, k12 + 45, 9 * byte3, 9, 9);
                    }
                }

                for(int l11 = 0; l11 < 10; l11++)
                {
                    int l12 = l8;
                    int k13 = 16;
                    byte byte2 = 0;
                    if(e.h.a(abv.s))
                    {
                        k13 += 36;
                        byte2 = 13;
                    }
                    if(e.h.aF().d() <= 0.0F && h % (j5 * 3 + 1) == 0)
                    {
                        l12 += d.nextInt(3) - 1;
                    }
                    if(flag3)
                    {
                        byte2 = 1;
                    }
                    int l14 = j7 - l11 * 8 - 9;
                    b(l14, l12, 16 + byte2 * 9, 27, 9, 9);
                    if(flag3)
                    {
                        if(l11 * 2 + 1 < i6)
                        {
                            b(l14, l12, k13 + 54, 27, 9, 9);
                        }
                        if(l11 * 2 + 1 == i6)
                        {
                            b(l14, l12, k13 + 63, 27, 9, 9);
                        }
                    }
                    if(l11 * 2 + 1 < j5)
                    {
                        b(l14, l12, k13 + 36, 27, 9, 9);
                    }
                    if(l11 * 2 + 1 == j5)
                    {
                        b(l14, l12, k13 + 45, 27, 9, 9);
                    }
                }

                if(e.h.a(aei.g))
                {
                    int i12 = e.h.Y();
                    int i13 = (int)Math.ceil(((double)(i12 - 2) * 10D) / 300D);
                    int l13 = (int)Math.ceil(((double)i12 * 10D) / 300D) - i13;
                    for(int j14 = 0; j14 < i13 + l13; j14++)
                    {
                        if(j14 < i13)
                        {
                            b(j7 - j14 * 8 - 9, l9, 16, 18, 9, 9);
                        } else
                        {
                            b(j7 - j14 * 8 - 9, l9, 25, 18, 9, 9);
                        }
                    }

                }
            }
            GL11.glDisable(3042);
            GL11.glEnable(32826);
            ul.c();
            for(int i7 = 0; i7 < 9; i7++)
            {
                int k7 = (j1 / 2 - 90) + i7 * 20 + 2;
                int j8 = k1 - 16 - 3;
                a(i7, k7, j8, f1);
            }

            ul.a();
            GL11.glDisable(32826);
        }
        if(e.h.aB() > 0)
        {
            GL11.glDisable(2929);
            GL11.glDisable(3008);
            int l1 = e.h.aB();
            float f3 = (float)l1 / 100F;
            if(f3 > 1.0F)
            {
                f3 = 1.0F - (float)(l1 - 100) / 10F;
            }
            int j2 = (int)(220F * f3) << 24 | 0x101020;
            a(0, 0, j1, k1, j2);
            GL11.glEnable(3008);
            GL11.glEnable(2929);
        }
        if(e.c.f() && e.h.aV > 0)
        {
            boolean flag2 = false;
            int k2 = flag2 ? 0xffffff : 0x80ff20;
            String s = (new StringBuilder()).append("").append(e.h.aV).toString();
            int j4 = (j1 - oh1.a(s)) / 2;
            int l4 = k1 - 31 - 4;
            oh1.b(s, j4 + 1, l4, 0);
            oh1.b(s, j4 - 1, l4, 0);
            oh1.b(s, j4, l4 + 1, 0);
            oh1.b(s, j4, l4 - 1, 0);
            oh1.b(s, j4, l4, k2);
        }
        e.c.a(f1, flag, l, i1, j1, k1);
        WMLL.i.updategui(e);
        if(e.A.L)
        {
            GL11.glPushMatrix();
            if(Minecraft.J > 0L)
            {
                GL11.glTranslatef(0.0F, 32F, 0.0F);
            }
            oh1.a((new StringBuilder()).append("Minecraft 12w17a (").append(e.M).append(")").toString(), 2, 2, 0xffffff);
            oh1.a(e.m(), 2, 12, 0xffffff);
            oh1.a(e.n(), 2, 22, 0xffffff);
            oh1.a(e.p(), 2, 32, 0xffffff);
            oh1.a(e.o(), 2, 42, 0xffffff);
            long l2 = Runtime.getRuntime().maxMemory();
            long l3 = Runtime.getRuntime().totalMemory();
            long l5 = Runtime.getRuntime().freeMemory();
            long l6 = l3 - l5;
            String s1 = (new StringBuilder()).append("Used memory: ").append((l6 * 100L) / l2).append("% (").append(l6 / 1024L / 1024L).append("MB) of ").append(l2 / 1024L / 1024L).append("MB").toString();
            b(oh1, s1, j1 - oh1.a(s1) - 2, 2, 0xe0e0e0);
            s1 = (new StringBuilder()).append("Allocated memory: ").append((l3 * 100L) / l2).append("% (").append(l3 / 1024L / 1024L).append("MB)").toString();
            b(oh1, s1, j1 - oh1.a(s1) - 2, 12, 0xe0e0e0);
            b(oh1, (new StringBuilder()).append("x: ").append(e.h.o).toString(), 2, 64, 0xe0e0e0);
            b(oh1, (new StringBuilder()).append("y: ").append(e.h.p).toString(), 2, 72, 0xe0e0e0);
            b(oh1, (new StringBuilder()).append("z: ").append(e.h.q).toString(), 2, 80, 0xe0e0e0);
            b(oh1, (new StringBuilder()).append("f: ").append(gt.c((double)((e.h.u * 4F) / 360F) + 0.5D) & 3).toString(), 2, 88, 0xe0e0e0);
            int i9 = gt.c(e.h.o);
            int i10 = gt.c(e.h.p);
            int l10 = gt.c(e.h.q);
            if(e.f != null && e.f.j(i9, i10, l10))
            {
                aef aef1 = e.f.c(i9, l10);
                b(oh1, (new StringBuilder()).append("lc: ").append(aef1.g() + 15).append(" b: ").append(aef1.a(i9 & 0xf, l10 & 0xf, e.f.i()).y).append(" bl: ").append(aef1.a(xx.b, i9 & 0xf, i10, l10 & 0xf)).append(" sl: ").append(aef1.a(xx.a, i9 & 0xf, i10, l10 & 0xf)).append(" rl: ").append(aef1.c(i9 & 0xf, i10, l10 & 0xf, 0)).toString(), 2, 96, 0xe0e0e0);
            }
            if(!e.f.F)
            {
                b(oh1, (new StringBuilder()).append("Seed: ").append(e.f.v()).toString(), 2, 112, 0xe0e0e0);
            }
            GL11.glPopMatrix();
        }
        if(j > 0)
        {
            float f4 = (float)j - f1;
            int i3 = (int)((f4 * 256F) / 20F);
            if(i3 > 255)
            {
                i3 = 255;
            }
            if(i3 > 0)
            {
                GL11.glPushMatrix();
                GL11.glTranslatef(j1 / 2, k1 - 48, 0.0F);
                GL11.glEnable(3042);
                GL11.glBlendFunc(770, 771);
                int k3 = 0xffffff;
                if(k)
                {
                    k3 = Color.HSBtoRGB(f4 / 50F, 0.7F, 0.6F) & 0xffffff;
                }
                oh1.b(i, -oh1.a(i) / 2, -4, k3 + (i3 << 24));
                GL11.glDisable(3042);
                GL11.glPopMatrix();
            }
        }
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3008);
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, k1 - 48, 0.0F);
        f.a(h);
        GL11.glPopMatrix();
        if((e.h instanceof ajx) && e.A.D.e)
        {
            afg afg1 = ((ajx)e.h).cm;
            java.util.List list = afg1.c;
            int i4 = afg1.d;
            int k4 = i4;
            int i5 = 1;
            for(; k4 > 20; k4 = ((i4 + i5) - 1) / i5)
            {
                i5++;
            }

            int k5 = 300 / i5;
            if(k5 > 150)
            {
                k5 = 150;
            }
            int j6 = (j1 - i5 * k5) / 2;
            byte byte0 = 10;
            a(j6 - 1, byte0 - 1, j6 + k5 * i5, byte0 + 9 * k4, 0x80000000);
            for(int l7 = 0; l7 < i4; l7++)
            {
                int k8 = j6 + (l7 % i5) * k5;
                int j9 = byte0 + (l7 / i5) * 9;
                a(k8, j9, (k8 + k5) - 1, j9 + 8, 0x20ffffff);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glEnable(3008);
                if(l7 >= list.size())
                {
                    continue;
                }
                ai ai1 = (ai)list.get(l7);
                oh1.a(ai1.a, k8, j9, 0xffffff);
                e.p.b(e.p.b("/gui/icons.png"));
                int i11 = 0;
                byte byte1 = 0;
                if(ai1.b < 0)
                {
                    byte1 = 5;
                } else
                if(ai1.b < 150)
                {
                    byte1 = 0;
                } else
                if(ai1.b < 300)
                {
                    byte1 = 1;
                } else
                if(ai1.b < 600)
                {
                    byte1 = 2;
                } else
                if(ai1.b < 1000)
                {
                    byte1 = 3;
                } else
                {
                    byte1 = 4;
                }
                g += 100F;
                b((k8 + k5) - 12, j9, 0 + i11 * 10, 176 + byte1 * 8, 10, 8);
                g -= 100F;
            }

        }
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(2896);
        GL11.glEnable(3008);
    }

    private void d()
    {
        if(pq.c == null)
        {
            return;
        }
        pp pp1 = pq.c;
        pq.c = null;
        oh oh1 = e.q;
        aic aic1 = new aic(e.A, e.d, e.e);
        int l = aic1.a();
        char c1 = '\266';
        int i1 = l / 2 - c1 / 2;
        int j1 = (int)(((float)pp1.x() / (float)pp1.d()) * (float)(c1 + 1));
        byte byte0 = 12;
        b(i1, byte0, 0, 74, c1, 5);
        b(i1, byte0, 0, 74, c1, 5);
        if(j1 > 0)
        {
            b(i1, byte0, 0, 79, j1, 5);
        }
        String s = "Boss health";
        oh1.a(s, l / 2 - oh1.a(s) / 2, byte0 - 10, 0xff00ff);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glBindTexture(3553, e.p.b("/gui/icons.png"));
    }

    private void a(int l, int i1)
    {
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(3008);
        GL11.glBindTexture(3553, e.p.b("%blur%/misc/pumpkinblur.png"));
        afw afw1 = afw.a;
        afw1.b();
        afw1.a(0.0D, i1, -90D, 0.0D, 1.0D);
        afw1.a(l, i1, -90D, 1.0D, 1.0D);
        afw1.a(l, 0.0D, -90D, 1.0D, 0.0D);
        afw1.a(0.0D, 0.0D, -90D, 0.0D, 0.0D);
        afw1.a();
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
        GL11.glEnable(3008);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    private void a(float f1, int l, int i1)
    {
        f1 = 1.0F - f1;
        if(f1 < 0.0F)
        {
            f1 = 0.0F;
        }
        if(f1 > 1.0F)
        {
            f1 = 1.0F;
        }
        b += (double)(f1 - b) * 0.01D;
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(0, 769);
        GL11.glColor4f(b, b, b, 1.0F);
        GL11.glBindTexture(3553, e.p.b("%blur%/misc/vignette.png"));
        afw afw1 = afw.a;
        afw1.b();
        afw1.a(0.0D, i1, -90D, 0.0D, 1.0D);
        afw1.a(l, i1, -90D, 1.0D, 1.0D);
        afw1.a(l, 0.0D, -90D, 1.0D, 0.0D);
        afw1.a(0.0D, 0.0D, -90D, 0.0D, 0.0D);
        afw1.a();
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glBlendFunc(770, 771);
    }

    private void b(float f1, int l, int i1)
    {
        if(f1 < 1.0F)
        {
            f1 *= f1;
            f1 *= f1;
            f1 = f1 * 0.8F + 0.2F;
        }
        GL11.glDisable(3008);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, f1);
        GL11.glBindTexture(3553, e.p.b("/terrain.png"));
        float f2 = (float)(qb.be.bP % 16) / 16F;
        float f3 = (float)(qb.be.bP / 16) / 16F;
        float f4 = (float)(qb.be.bP % 16 + 1) / 16F;
        float f5 = (float)(qb.be.bP / 16 + 1) / 16F;
        afw afw1 = afw.a;
        afw1.b();
        afw1.a(0.0D, i1, -90D, f2, f5);
        afw1.a(l, i1, -90D, f4, f5);
        afw1.a(l, 0.0D, -90D, f4, f3);
        afw1.a(0.0D, 0.0D, -90D, f2, f3);
        afw1.a();
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
        GL11.glEnable(3008);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    private void a(int l, int i1, int j1, float f1)
    {
        acf acf1 = e.h.aq.a[l];
        if(acf1 == null)
        {
            return;
        }
        float f2 = (float)acf1.b - f1;
        if(f2 > 0.0F)
        {
            GL11.glPushMatrix();
            float f3 = 1.0F + f2 / 5F;
            GL11.glTranslatef(i1 + 8, j1 + 12, 0.0F);
            GL11.glScalef(1.0F / f3, (f3 + 1.0F) / 2.0F, 1.0F);
            GL11.glTranslatef(-(i1 + 8), -(j1 + 12), 0.0F);
        }
        c.a(e.q, e.p, acf1, i1, j1);
        if(f2 > 0.0F)
        {
            GL11.glPopMatrix();
        }
        c.b(e.q, e.p, acf1, i1, j1);
    }

    public void a()
    {
        if(j > 0)
        {
            j--;
        }
        h++;
    }

    public void a(String s)
    {
        i = (new StringBuilder()).append("Now playing: ").append(s).toString();
        j = 60;
        k = true;
    }

    public qd b()
    {
        return f;
    }

    public int c()
    {
        return h;
    }

}
