// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst noconv 
// Source File Name:   SourceFile

import java.awt.Color;
import java.util.Random;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

public class aqz extends st
{

    private static final zf b = new zf();
    private final Random c = new Random();
    private final Minecraft d;
    private final tt e;
    private int f;
    private String h;
    private int i;
    private boolean j;
    public float a;

    public aqz(Minecraft minecraft)
    {
        f = 0;
        h = "";
        i = 0;
        j = false;
        a = 1.0F;
        d = minecraft;
        e = new tt(minecraft);
    }

    public void a(float f1, boolean flag, int k, int l)
    {
        anq anq1 = new anq(d.z, d.d, d.e);
        int i1 = anq1.a();
        int j1 = anq1.b();
        rm rm1 = d.q;
        d.u.b();
        GL11.glEnable(3042);
        if(Minecraft.t())
        {
            a(d.h.a(f1), i1, j1);
        } else
        {
            GL11.glBlendFunc(770, 771);
        }
        ahj ahj1 = d.h.as.f(3);
        if(d.z.L == 0 && ahj1 != null && ahj1.c == tp.ba.ca)
        {
            a(i1, j1);
        }
        if(!d.h.a(agx.k))
        {
            float f2 = d.h.aV + (d.h.aU - d.h.aV) * f1;
            if(f2 > 0.0F)
            {
                b(f2, i1, j1);
            }
        }
        if(!d.c.a())
        {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glBindTexture(3553, d.p.b("/gui/gui.png"));
            ahf ahf1 = d.h.as;
            g = -90F;
            c(i1 / 2 - 91, j1 - 22, 0, 0, 182, 22);
            c((i1 / 2 - 91 - 1) + ahf1.c * 20, j1 - 22 - 1, 0, 22, 24, 22);
            GL11.glBindTexture(3553, d.p.b("/gui/icons.png"));
            GL11.glEnable(3042);
            GL11.glBlendFunc(775, 769);
            c(i1 / 2 - 7, j1 / 2 - 7, 0, 0, 16, 16);
            GL11.glDisable(3042);
            boolean flag1 = (d.h.ad / 3) % 2 == 1;
            if(d.h.ad < 10)
            {
                flag1 = false;
            }
            int i2 = d.h.bg();
            int j3 = d.h.bB;
            c.setSeed(f * 0x4c627);
            boolean flag3 = false;
            re re1 = d.h.aK();
            int j5 = re1.a();
            int i6 = re1.b();
            d.J.a("bossHealth");
            d();
            d.J.b();
            if(d.c.b())
            {
                int k6 = i1 / 2 - 91;
                int j7 = i1 / 2 + 91;
                d.J.a("expBar");
                int i8 = d.h.aJ();
                if(i8 > 0)
                {
                    char c1 = '\266';
                    int k9 = (int)(d.h.aZ * (float)(c1 + 1));
                    int j10 = (j1 - 32) + 3;
                    c(k6, j10, 0, 64, c1, 5);
                    if(k9 > 0)
                    {
                        c(k6, j10, 0, 69, k9, 5);
                    }
                }
                int l8 = j1 - 39;
                int l9 = l8 - 10;
                int k10 = d.h.az();
                int j11 = -1;
                if(d.h.a(agx.l))
                {
                    j11 = f % 25;
                }
                d.J.c("healthArmor");
                for(int k11 = 0; k11 < 10; k11++)
                {
                    if(k10 > 0)
                    {
                        int j12 = k6 + k11 * 8;
                        if(k11 * 2 + 1 < k10)
                        {
                            c(j12, l9, 34, 9, 9, 9);
                        }
                        if(k11 * 2 + 1 == k10)
                        {
                            c(j12, l9, 25, 9, 9, 9);
                        }
                        if(k11 * 2 + 1 > k10)
                        {
                            c(j12, l9, 16, 9, 9, 9);
                        }
                    }
                    int k12 = 16;
                    if(d.h.a(agx.u))
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
                        k14 += c.nextInt(2);
                    }
                    if(k11 == j11)
                    {
                        k14 -= 2;
                    }
                    byte byte3 = 0;
                    if(d.f.y().s())
                    {
                        byte3 = 5;
                    }
                    c(i14, k14, 16 + j13 * 9, 9 * byte3, 9, 9);
                    if(flag1)
                    {
                        if(k11 * 2 + 1 < j3)
                        {
                            c(i14, k14, k12 + 54, 9 * byte3, 9, 9);
                        }
                        if(k11 * 2 + 1 == j3)
                        {
                            c(i14, k14, k12 + 63, 9 * byte3, 9, 9);
                        }
                    }
                    if(k11 * 2 + 1 < i2)
                    {
                        c(i14, k14, k12 + 36, 9 * byte3, 9, 9);
                    }
                    if(k11 * 2 + 1 == i2)
                    {
                        c(i14, k14, k12 + 45, 9 * byte3, 9, 9);
                    }
                }

                d.J.c("food");
                for(int l11 = 0; l11 < 10; l11++)
                {
                    int l12 = l8;
                    int k13 = 16;
                    byte byte2 = 0;
                    if(d.h.a(agx.s))
                    {
                        k13 += 36;
                        byte2 = 13;
                    }
                    if(d.h.aK().d() <= 0.0F && f % (j5 * 3 + 1) == 0)
                    {
                        l12 += c.nextInt(3) - 1;
                    }
                    if(flag3)
                    {
                        byte2 = 1;
                    }
                    int l14 = j7 - l11 * 8 - 9;
                    c(l14, l12, 16 + byte2 * 9, 27, 9, 9);
                    if(flag3)
                    {
                        if(l11 * 2 + 1 < i6)
                        {
                            c(l14, l12, k13 + 54, 27, 9, 9);
                        }
                        if(l11 * 2 + 1 == i6)
                        {
                            c(l14, l12, k13 + 63, 27, 9, 9);
                        }
                    }
                    if(l11 * 2 + 1 < j5)
                    {
                        c(l14, l12, k13 + 36, 27, 9, 9);
                    }
                    if(l11 * 2 + 1 == j5)
                    {
                        c(l14, l12, k13 + 45, 27, 9, 9);
                    }
                }

                d.J.c("air");
                if(d.h.a(ajr.g))
                {
                    int i12 = d.h.al();
                    int i13 = (int)Math.ceil(((double)(i12 - 2) * 10D) / 300D);
                    int l13 = (int)Math.ceil(((double)i12 * 10D) / 300D) - i13;
                    for(int j14 = 0; j14 < i13 + l13; j14++)
                    {
                        if(j14 < i13)
                        {
                            c(j7 - j14 * 8 - 9, l9, 16, 18, 9, 9);
                        } else
                        {
                            c(j7 - j14 * 8 - 9, l9, 25, 18, 9, 9);
                        }
                    }

                }
                d.J.b();
            }
            GL11.glDisable(3042);
            d.J.a("actionBar");
            GL11.glEnable(32826);
            ym.c();
            for(int i7 = 0; i7 < 9; i7++)
            {
                int k7 = (i1 / 2 - 90) + i7 * 20 + 2;
                int j8 = j1 - 16 - 3;
                a(i7, k7, j8, f1);
            }

            ym.a();
            GL11.glDisable(32826);
            d.J.b();
        }
        if(d.h.aG() > 0)
        {
            d.J.a("sleep");
            GL11.glDisable(2929);
            GL11.glDisable(3008);
            int k1 = d.h.aG();
            float f3 = (float)k1 / 100F;
            if(f3 > 1.0F)
            {
                f3 = 1.0F - (float)(k1 - 100) / 10F;
            }
            int j2 = (int)(220F * f3) << 24 | 0x101020;
            a(0, 0, i1, j1, j2);
            GL11.glEnable(3008);
            GL11.glEnable(2929);
            d.J.b();
        }
        if(d.c.f() && d.h.aX > 0)
        {
            d.J.a("expLevel");
            boolean flag2 = false;
            int k2 = flag2 ? 0xffffff : 0x80ff20;
            String s1 = (new StringBuilder()).append("").append(d.h.aX).toString();
            int j4 = (i1 - rm1.a(s1)) / 2;
            int l4 = j1 - 31 - 4;
            rm1.b(s1, j4 + 1, l4, 0);
            rm1.b(s1, j4 - 1, l4, 0);
            rm1.b(s1, j4, l4 + 1, 0);
            rm1.b(s1, j4, l4 - 1, 0);
            rm1.b(s1, j4, l4, k2);
            d.J.b();
        }
        if(d.q())
        {
            d.J.a("demo");
            String s = "";
            if(d.f.u() >= 0x1d6b4L)
            {
                s = ek.a("demo.demoExpired");
            } else
            {
                s = String.format(ek.a("demo.remainingTime"), new Object[] {
                    ajd.a((int)(0x1d6b4L - d.f.u()))
                });
            }
            int l2 = rm1.a(s);
            rm1.a(s, i1 - l2 - 10, 5, 0xffffff);
            d.J.b();
        }
        WMLL.i.updategui(d);
        if(d.z.M)
        {
            d.J.a("debug");
            GL11.glPushMatrix();
            rm1.a((new StringBuilder()).append("Minecraft 12w27a (").append(d.L).append(")").toString(), 2, 2, 0xffffff);
            rm1.a(d.m(), 2, 12, 0xffffff);
            rm1.a(d.n(), 2, 22, 0xffffff);
            rm1.a(d.p(), 2, 32, 0xffffff);
            rm1.a(d.o(), 2, 42, 0xffffff);
            long l1 = Runtime.getRuntime().maxMemory();
            long l3 = Runtime.getRuntime().totalMemory();
            long l5 = Runtime.getRuntime().freeMemory();
            long l6 = l3 - l5;
            String s2 = (new StringBuilder()).append("Used memory: ").append((l6 * 100L) / l1).append("% (").append(l6 / 1024L / 1024L).append("MB) of ").append(l1 / 1024L / 1024L).append("MB").toString();
            b(rm1, s2, i1 - rm1.a(s2) - 2, 2, 0xe0e0e0);
            s2 = (new StringBuilder()).append("Allocated memory: ").append((l3 * 100L) / l1).append("% (").append(l3 / 1024L / 1024L).append("MB)").toString();
            b(rm1, s2, i1 - rm1.a(s2) - 2, 12, 0xe0e0e0);
            b(rm1, String.format("x: %.5f", new Object[] {
                Double.valueOf(d.h.t)
            }), 2, 64, 0xe0e0e0);
            b(rm1, String.format("y: %.5f", new Object[] {
                Double.valueOf(d.h.u)
            }), 2, 72, 0xe0e0e0);
            b(rm1, String.format("z: %.5f", new Object[] {
                Double.valueOf(d.h.v)
            }), 2, 80, 0xe0e0e0);
            b(rm1, (new StringBuilder()).append("f: ").append(ix.c((double)((d.h.z * 4F) / 360F) + 0.5D) & 3).toString(), 2, 88, 0xe0e0e0);
            int i9 = ix.c(d.h.t);
            int i10 = ix.c(d.h.u);
            int l10 = ix.c(d.h.v);
            if(d.f != null && d.f.m(i9, i10, l10))
            {
                ajp ajp1 = d.f.d(i9, l10);
                b(rm1, (new StringBuilder()).append("lc: ").append(ajp1.g() + 15).append(" b: ").append(ajp1.a(i9 & 0xf, l10 & 0xf, d.f.i()).y).append(" bl: ").append(ajp1.a(acm.b, i9 & 0xf, i10, l10 & 0xf)).append(" sl: ").append(ajp1.a(acm.a, i9 & 0xf, i10, l10 & 0xf)).append(" rl: ").append(ajp1.c(i9 & 0xf, i10, l10 & 0xf, 0)).toString(), 2, 96, 0xe0e0e0);
            }
            b(rm1, String.format("ws: %.3f, fs: %.3f, g: %b", new Object[] {
                Float.valueOf(d.h.aW.b()), Float.valueOf(d.h.aW.a()), Boolean.valueOf(d.h.E)
            }), 2, 104, 0xe0e0e0);
            GL11.glPopMatrix();
            d.J.b();
        }
        if(i > 0)
        {
            d.J.a("overlayMessage");
            float f4 = (float)i - f1;
            int i3 = (int)((f4 * 256F) / 20F);
            if(i3 > 255)
            {
                i3 = 255;
            }
            if(i3 > 0)
            {
                GL11.glPushMatrix();
                GL11.glTranslatef(i1 / 2, j1 - 48, 0.0F);
                GL11.glEnable(3042);
                GL11.glBlendFunc(770, 771);
                int k3 = 0xffffff;
                if(j)
                {
                    k3 = Color.HSBtoRGB(f4 / 50F, 0.7F, 0.6F) & 0xffffff;
                }
                rm1.b(h, -rm1.a(h) / 2, -4, k3 + (i3 << 24));
                GL11.glDisable(3042);
                GL11.glPopMatrix();
            }
            d.J.b();
        }
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3008);
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, j1 - 48, 0.0F);
        d.J.a("chat");
        e.a(f);
        d.J.b();
        GL11.glPopMatrix();
        if(d.z.E.e && (!d.A() || d.h.i.c.size() > 1))
        {
            d.J.a("playerList");
            aks aks1 = d.h.i;
            java.util.List list = aks1.c;
            int i4 = aks1.d;
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
            int j6 = (i1 - i5 * k5) / 2;
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
                az az1 = (az)list.get(l7);
                rm1.a(az1.a, k8, j9, 0xffffff);
                d.p.b(d.p.b("/gui/icons.png"));
                int i11 = 0;
                byte byte1 = 0;
                if(az1.b < 0)
                {
                    byte1 = 5;
                } else
                if(az1.b < 150)
                {
                    byte1 = 0;
                } else
                if(az1.b < 300)
                {
                    byte1 = 1;
                } else
                if(az1.b < 600)
                {
                    byte1 = 2;
                } else
                if(az1.b < 1000)
                {
                    byte1 = 3;
                } else
                {
                    byte1 = 4;
                }
                g += 100F;
                c((k8 + k5) - 12, j9, 0 + i11 * 10, 176 + byte1 * 8, 10, 8);
                g -= 100F;
            }

        }
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(2896);
        GL11.glEnable(3008);
    }

    private void d()
    {
        if(td.c == null)
        {
            return;
        }
        tb tb1 = td.c;
        td.c = null;
        rm rm1 = d.q;
        anq anq1 = new anq(d.z, d.d, d.e);
        int k = anq1.a();
        char c1 = '\266';
        int l = k / 2 - c1 / 2;
        int i1 = (int)(((float)tb1.z() / (float)tb1.d()) * (float)(c1 + 1));
        byte byte0 = 12;
        c(l, byte0, 0, 74, c1, 5);
        c(l, byte0, 0, 74, c1, 5);
        if(i1 > 0)
        {
            c(l, byte0, 0, 79, i1, 5);
        }
        String s = "Boss health";
        rm1.a(s, k / 2 - rm1.a(s) / 2, byte0 - 10, 0xff00ff);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glBindTexture(3553, d.p.b("/gui/icons.png"));
    }

    private void a(int k, int l)
    {
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(3008);
        GL11.glBindTexture(3553, d.p.b("%blur%/misc/pumpkinblur.png"));
        alk alk1 = alk.a;
        alk1.b();
        alk1.a(0.0D, l, -90D, 0.0D, 1.0D);
        alk1.a(k, l, -90D, 1.0D, 1.0D);
        alk1.a(k, 0.0D, -90D, 1.0D, 0.0D);
        alk1.a(0.0D, 0.0D, -90D, 0.0D, 0.0D);
        alk1.a();
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
        GL11.glEnable(3008);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    private void a(float f1, int k, int l)
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
        a += (double)(f1 - a) * 0.01D;
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(0, 769);
        GL11.glColor4f(a, a, a, 1.0F);
        GL11.glBindTexture(3553, d.p.b("%blur%/misc/vignette.png"));
        alk alk1 = alk.a;
        alk1.b();
        alk1.a(0.0D, l, -90D, 0.0D, 1.0D);
        alk1.a(k, l, -90D, 1.0D, 1.0D);
        alk1.a(k, 0.0D, -90D, 1.0D, 0.0D);
        alk1.a(0.0D, 0.0D, -90D, 0.0D, 0.0D);
        alk1.a();
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glBlendFunc(770, 771);
    }

    private void b(float f1, int k, int l)
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
        GL11.glBindTexture(3553, d.p.b("/terrain.png"));
        float f2 = (float)(tp.be.bZ % 16) / 16F;
        float f3 = (float)(tp.be.bZ / 16) / 16F;
        float f4 = (float)(tp.be.bZ % 16 + 1) / 16F;
        float f5 = (float)(tp.be.bZ / 16 + 1) / 16F;
        alk alk1 = alk.a;
        alk1.b();
        alk1.a(0.0D, l, -90D, f2, f5);
        alk1.a(k, l, -90D, f4, f5);
        alk1.a(k, 0.0D, -90D, f4, f3);
        alk1.a(0.0D, 0.0D, -90D, f2, f3);
        alk1.a();
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
        GL11.glEnable(3008);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    private void a(int k, int l, int i1, float f1)
    {
        ahj ahj1 = d.h.as.a[k];
        if(ahj1 == null)
        {
            return;
        }
        float f2 = (float)ahj1.b - f1;
        if(f2 > 0.0F)
        {
            GL11.glPushMatrix();
            float f3 = 1.0F + f2 / 5F;
            GL11.glTranslatef(l + 8, i1 + 12, 0.0F);
            GL11.glScalef(1.0F / f3, (f3 + 1.0F) / 2.0F, 1.0F);
            GL11.glTranslatef(-(l + 8), -(i1 + 12), 0.0F);
        }
        b.a(d.q, d.p, ahj1, l, i1);
        if(f2 > 0.0F)
        {
            GL11.glPopMatrix();
        }
        b.b(d.q, d.p, ahj1, l, i1);
    }

    public void a()
    {
        if(i > 0)
        {
            i--;
        }
        f++;
    }

    public void a(String s)
    {
        h = (new StringBuilder()).append("Now playing: ").append(s).toString();
        i = 60;
        j = true;
    }

    public tt b()
    {
        return e;
    }

    public int c()
    {
        return f;
    }

}
