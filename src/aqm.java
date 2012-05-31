// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst noconv 
// Source File Name:   SourceFile

import java.awt.Color;
import java.util.Random;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

public class aqm extends sk
{

    private static final yr c = new yr();
    private final Random d = new Random();
    private final Minecraft e;
    private final tj f;
    private int h;
    private String i;
    private int j;
    private boolean k;
    public float a;
    public float b;

    public aqm(Minecraft minecraft)
    {
        h = 0;
        i = "";
        j = 0;
        k = false;
        b = 1.0F;
        e = minecraft;
        f = new tj(minecraft);
    }

    public void a(float f1, boolean flag, int l, int i1)
    {
        and and1 = new and(e.z, e.d, e.e);
        int j1 = and1.a();
        int k1 = and1.b();
        rc rc1 = e.q;
        e.u.b();
        GL11.glEnable(3042);
        if(Minecraft.s())
        {
            a(e.h.a(f1), j1, k1);
        } else
        {
            GL11.glBlendFunc(770, 771);
        }
        agv agv1 = e.h.ar.f(3);
        if(e.z.L == 0 && agv1 != null && agv1.c == tg.ba.bX)
        {
            a(j1, k1);
        }
        if(!e.h.a(agj.k))
        {
            float f2 = e.h.aU + (e.h.aT - e.h.aU) * f1;
            if(f2 > 0.0F)
            {
                b(f2, j1, k1);
            }
        }
        if(!e.c.a())
        {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glBindTexture(3553, e.p.b("/gui/gui.png"));
            ags ags1 = e.h.ar;
            g = -90F;
            c(j1 / 2 - 91, k1 - 22, 0, 0, 182, 22);
            c((j1 / 2 - 91 - 1) + ags1.c * 20, k1 - 22 - 1, 0, 22, 24, 22);
            GL11.glBindTexture(3553, e.p.b("/gui/icons.png"));
            GL11.glEnable(3042);
            GL11.glBlendFunc(775, 769);
            c(j1 / 2 - 7, k1 / 2 - 7, 0, 0, 16, 16);
            GL11.glDisable(3042);
            boolean flag1 = (e.h.ac / 3) % 2 == 1;
            if(e.h.ac < 10)
            {
                flag1 = false;
            }
            int i2 = e.h.be();
            int k3 = e.h.bA;
            d.setSeed(h * 0x4c627);
            boolean flag3 = false;
            qu qu1 = e.h.aJ();
            int k5 = qu1.a();
            int j6 = qu1.b();
            d();
            if(e.c.b())
            {
                int i7 = j1 / 2 - 91;
                int k7 = j1 / 2 + 91;
                int j8 = e.h.aI();
                if(j8 > 0)
                {
                    char c1 = '\266';
                    int l9 = (int)(e.h.aY * (float)(c1 + 1));
                    int k10 = (k1 - 32) + 3;
                    c(i7, k10, 0, 64, c1, 5);
                    if(l9 > 0)
                    {
                        c(i7, k10, 0, 69, l9, 5);
                    }
                }
                int i9 = k1 - 39;
                int i10 = i9 - 10;
                int l10 = e.h.ay();
                int k11 = -1;
                if(e.h.a(agj.l))
                {
                    k11 = h % 25;
                }
                for(int l11 = 0; l11 < 10; l11++)
                {
                    if(l10 > 0)
                    {
                        int k12 = i7 + l11 * 8;
                        if(l11 * 2 + 1 < l10)
                        {
                            c(k12, i10, 34, 9, 9, 9);
                        }
                        if(l11 * 2 + 1 == l10)
                        {
                            c(k12, i10, 25, 9, 9, 9);
                        }
                        if(l11 * 2 + 1 > l10)
                        {
                            c(k12, i10, 16, 9, 9, 9);
                        }
                    }
                    int l12 = 16;
                    if(e.h.a(agj.u))
                    {
                        l12 += 36;
                    }
                    int k13 = 0;
                    if(flag1)
                    {
                        k13 = 1;
                    }
                    int j14 = i7 + l11 * 8;
                    int l14 = i9;
                    if(i2 <= 4)
                    {
                        l14 += d.nextInt(2);
                    }
                    if(l11 == k11)
                    {
                        l14 -= 2;
                    }
                    byte byte3 = 0;
                    if(e.f.y().s())
                    {
                        byte3 = 5;
                    }
                    c(j14, l14, 16 + k13 * 9, 9 * byte3, 9, 9);
                    if(flag1)
                    {
                        if(l11 * 2 + 1 < k3)
                        {
                            c(j14, l14, l12 + 54, 9 * byte3, 9, 9);
                        }
                        if(l11 * 2 + 1 == k3)
                        {
                            c(j14, l14, l12 + 63, 9 * byte3, 9, 9);
                        }
                    }
                    if(l11 * 2 + 1 < i2)
                    {
                        c(j14, l14, l12 + 36, 9 * byte3, 9, 9);
                    }
                    if(l11 * 2 + 1 == i2)
                    {
                        c(j14, l14, l12 + 45, 9 * byte3, 9, 9);
                    }
                }

                for(int i12 = 0; i12 < 10; i12++)
                {
                    int i13 = i9;
                    int l13 = 16;
                    byte byte2 = 0;
                    if(e.h.a(agj.s))
                    {
                        l13 += 36;
                        byte2 = 13;
                    }
                    if(e.h.aJ().d() <= 0.0F && h % (k5 * 3 + 1) == 0)
                    {
                        i13 += d.nextInt(3) - 1;
                    }
                    if(flag3)
                    {
                        byte2 = 1;
                    }
                    int i15 = k7 - i12 * 8 - 9;
                    c(i15, i13, 16 + byte2 * 9, 27, 9, 9);
                    if(flag3)
                    {
                        if(i12 * 2 + 1 < j6)
                        {
                            c(i15, i13, l13 + 54, 27, 9, 9);
                        }
                        if(i12 * 2 + 1 == j6)
                        {
                            c(i15, i13, l13 + 63, 27, 9, 9);
                        }
                    }
                    if(i12 * 2 + 1 < k5)
                    {
                        c(i15, i13, l13 + 36, 27, 9, 9);
                    }
                    if(i12 * 2 + 1 == k5)
                    {
                        c(i15, i13, l13 + 45, 27, 9, 9);
                    }
                }

                if(e.h.a(ajb.g))
                {
                    int j12 = e.h.ak();
                    int j13 = (int)Math.ceil(((double)(j12 - 2) * 10D) / 300D);
                    int i14 = (int)Math.ceil(((double)j12 * 10D) / 300D) - j13;
                    for(int k14 = 0; k14 < j13 + i14; k14++)
                    {
                        if(k14 < j13)
                        {
                            c(k7 - k14 * 8 - 9, i10, 16, 18, 9, 9);
                        } else
                        {
                            c(k7 - k14 * 8 - 9, i10, 25, 18, 9, 9);
                        }
                    }

                }
            }
            GL11.glDisable(3042);
            GL11.glEnable(32826);
            xy.c();
            for(int j7 = 0; j7 < 9; j7++)
            {
                int l7 = (j1 / 2 - 90) + j7 * 20 + 2;
                int k8 = k1 - 16 - 3;
                a(j7, l7, k8, f1);
            }

            xy.a();
            GL11.glDisable(32826);
        }
        if(e.h.aF() > 0)
        {
            GL11.glDisable(2929);
            GL11.glDisable(3008);
            int l1 = e.h.aF();
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
        if(e.c.f() && e.h.aW > 0)
        {
            boolean flag2 = false;
            int k2 = flag2 ? 0xffffff : 0x80ff20;
            String s1 = (new StringBuilder()).append("").append(e.h.aW).toString();
            int k4 = (j1 - rc1.a(s1)) / 2;
            int i5 = k1 - 31 - 4;
            rc1.b(s1, k4 + 1, i5, 0);
            rc1.b(s1, k4 - 1, i5, 0);
            rc1.b(s1, k4, i5 + 1, 0);
            rc1.b(s1, k4, i5 - 1, 0);
            rc1.b(s1, k4, i5, k2);
        }
        if(e.p())
        {
            String s = "";
            if(e.f.u() >= 0x1d6b4L)
            {
                s = ej.a("demo.demoExpired");
            } else
            {
                s = String.format(ej.a("demo.remainingTime"), new Object[] {
                    ain.a((int)(0x1d6b4L - e.f.u()))
                });
            }
            int i3 = rc1.a(s);
            rc1.a(s, j1 - i3 - 10, 5, 0xffffff);
        }
        WMLL.i.updategui(e);
        if(e.z.M)
        {
            GL11.glPushMatrix();
            rc1.a((new StringBuilder()).append("Minecraft 12w22a (").append(e.N).append(")").toString(), 2, 2, 0xffffff);
            rc1.a(e.l(), 2, 12, 0xffffff);
            rc1.a(e.m(), 2, 22, 0xffffff);
            rc1.a(e.o(), 2, 32, 0xffffff);
            rc1.a(e.n(), 2, 42, 0xffffff);
            long l2 = Runtime.getRuntime().maxMemory();
            long l3 = Runtime.getRuntime().totalMemory();
            long l5 = Runtime.getRuntime().freeMemory();
            long l6 = l3 - l5;
            String s2 = (new StringBuilder()).append("Used memory: ").append((l6 * 100L) / l2).append("% (").append(l6 / 1024L / 1024L).append("MB) of ").append(l2 / 1024L / 1024L).append("MB").toString();
            b(rc1, s2, j1 - rc1.a(s2) - 2, 2, 0xe0e0e0);
            s2 = (new StringBuilder()).append("Allocated memory: ").append((l3 * 100L) / l2).append("% (").append(l3 / 1024L / 1024L).append("MB)").toString();
            b(rc1, s2, j1 - rc1.a(s2) - 2, 12, 0xe0e0e0);
            b(rc1, String.format("x: %.5f", new Object[] {
                Double.valueOf(e.h.s)
            }), 2, 64, 0xe0e0e0);
            b(rc1, String.format("y: %.5f", new Object[] {
                Double.valueOf(e.h.t)
            }), 2, 72, 0xe0e0e0);
            b(rc1, String.format("z: %.5f", new Object[] {
                Double.valueOf(e.h.u)
            }), 2, 80, 0xe0e0e0);
            b(rc1, (new StringBuilder()).append("f: ").append(iv.c((double)((e.h.y * 4F) / 360F) + 0.5D) & 3).toString(), 2, 88, 0xe0e0e0);
            int j9 = iv.c(e.h.s);
            int j10 = iv.c(e.h.t);
            int i11 = iv.c(e.h.u);
            if(e.f != null && e.f.l(j9, j10, i11))
            {
                aiz aiz1 = e.f.d(j9, i11);
                b(rc1, (new StringBuilder()).append("lc: ").append(aiz1.g() + 15).append(" b: ").append(aiz1.a(j9 & 0xf, i11 & 0xf, e.f.i()).y).append(" bl: ").append(aiz1.a(aby.b, j9 & 0xf, j10, i11 & 0xf)).append(" sl: ").append(aiz1.a(aby.a, j9 & 0xf, j10, i11 & 0xf)).append(" rl: ").append(aiz1.c(j9 & 0xf, j10, i11 & 0xf, 0)).toString(), 2, 96, 0xe0e0e0);
            }
            b(rc1, String.format("ws: %.3f, fs: %.3f, g: %b", new Object[] {
                Float.valueOf(e.h.aV.b()), Float.valueOf(e.h.aV.a()), Boolean.valueOf(e.h.D)
            }), 2, 104, 0xe0e0e0);
            GL11.glPopMatrix();
        }
        if(j > 0)
        {
            float f4 = (float)j - f1;
            int j3 = (int)((f4 * 256F) / 20F);
            if(j3 > 255)
            {
                j3 = 255;
            }
            if(j3 > 0)
            {
                GL11.glPushMatrix();
                GL11.glTranslatef(j1 / 2, k1 - 48, 0.0F);
                GL11.glEnable(3042);
                GL11.glBlendFunc(770, 771);
                int i4 = 0xffffff;
                if(k)
                {
                    i4 = Color.HSBtoRGB(f4 / 50F, 0.7F, 0.6F) & 0xffffff;
                }
                rc1.b(i, -rc1.a(i) / 2, -4, i4 + (j3 << 24));
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
        if(e.z.E.e && !e.z())
        {
            akd akd1 = e.h.i;
            java.util.List list = akd1.c;
            int j4 = akd1.d;
            int l4 = j4;
            int j5 = 1;
            for(; l4 > 20; l4 = ((j4 + j5) - 1) / j5)
            {
                j5++;
            }

            int i6 = 300 / j5;
            if(i6 > 150)
            {
                i6 = 150;
            }
            int k6 = (j1 - j5 * i6) / 2;
            byte byte0 = 10;
            a(k6 - 1, byte0 - 1, k6 + i6 * j5, byte0 + 9 * l4, 0x80000000);
            for(int i8 = 0; i8 < j4; i8++)
            {
                int l8 = k6 + (i8 % j5) * i6;
                int k9 = byte0 + (i8 / j5) * 9;
                a(l8, k9, (l8 + i6) - 1, k9 + 8, 0x20ffffff);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glEnable(3008);
                if(i8 >= list.size())
                {
                    continue;
                }
                bc bc1 = (bc)list.get(i8);
                rc1.a(bc1.a, l8, k9, 0xffffff);
                e.p.b(e.p.b("/gui/icons.png"));
                int j11 = 0;
                byte byte1 = 0;
                if(bc1.b < 0)
                {
                    byte1 = 5;
                } else
                if(bc1.b < 150)
                {
                    byte1 = 0;
                } else
                if(bc1.b < 300)
                {
                    byte1 = 1;
                } else
                if(bc1.b < 600)
                {
                    byte1 = 2;
                } else
                if(bc1.b < 1000)
                {
                    byte1 = 3;
                } else
                {
                    byte1 = 4;
                }
                g += 100F;
                c((l8 + i6) - 12, k9, 0 + j11 * 10, 176 + byte1 * 8, 10, 8);
                g -= 100F;
            }

        }
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(2896);
        GL11.glEnable(3008);
    }

    private void d()
    {
        if(su.c == null)
        {
            return;
        }
        ss ss1 = su.c;
        su.c = null;
        rc rc1 = e.q;
        and and1 = new and(e.z, e.d, e.e);
        int l = and1.a();
        char c1 = '\266';
        int i1 = l / 2 - c1 / 2;
        int j1 = (int)(((float)ss1.z() / (float)ss1.d()) * (float)(c1 + 1));
        byte byte0 = 12;
        c(i1, byte0, 0, 74, c1, 5);
        c(i1, byte0, 0, 74, c1, 5);
        if(j1 > 0)
        {
            c(i1, byte0, 0, 79, j1, 5);
        }
        String s = "Boss health";
        rc1.a(s, l / 2 - rc1.a(s) / 2, byte0 - 10, 0xff00ff);
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
        akv akv1 = akv.a;
        akv1.b();
        akv1.a(0.0D, i1, -90D, 0.0D, 1.0D);
        akv1.a(l, i1, -90D, 1.0D, 1.0D);
        akv1.a(l, 0.0D, -90D, 1.0D, 0.0D);
        akv1.a(0.0D, 0.0D, -90D, 0.0D, 0.0D);
        akv1.a();
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
        akv akv1 = akv.a;
        akv1.b();
        akv1.a(0.0D, i1, -90D, 0.0D, 1.0D);
        akv1.a(l, i1, -90D, 1.0D, 1.0D);
        akv1.a(l, 0.0D, -90D, 1.0D, 0.0D);
        akv1.a(0.0D, 0.0D, -90D, 0.0D, 0.0D);
        akv1.a();
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
        float f2 = (float)(tg.be.bW % 16) / 16F;
        float f3 = (float)(tg.be.bW / 16) / 16F;
        float f4 = (float)(tg.be.bW % 16 + 1) / 16F;
        float f5 = (float)(tg.be.bW / 16 + 1) / 16F;
        akv akv1 = akv.a;
        akv1.b();
        akv1.a(0.0D, i1, -90D, f2, f5);
        akv1.a(l, i1, -90D, f4, f5);
        akv1.a(l, 0.0D, -90D, f4, f3);
        akv1.a(0.0D, 0.0D, -90D, f2, f3);
        akv1.a();
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
        GL11.glEnable(3008);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    private void a(int l, int i1, int j1, float f1)
    {
        agv agv1 = e.h.ar.a[l];
        if(agv1 == null)
        {
            return;
        }
        float f2 = (float)agv1.b - f1;
        if(f2 > 0.0F)
        {
            GL11.glPushMatrix();
            float f3 = 1.0F + f2 / 5F;
            GL11.glTranslatef(i1 + 8, j1 + 12, 0.0F);
            GL11.glScalef(1.0F / f3, (f3 + 1.0F) / 2.0F, 1.0F);
            GL11.glTranslatef(-(i1 + 8), -(j1 + 12), 0.0F);
        }
        c.a(e.q, e.p, agv1, i1, j1);
        if(f2 > 0.0F)
        {
            GL11.glPopMatrix();
        }
        c.b(e.q, e.p, agv1, i1, j1);
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

    public tj b()
    {
        return f;
    }

    public int c()
    {
        return h;
    }

}
