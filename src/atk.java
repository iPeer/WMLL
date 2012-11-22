// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst noconv 
// Source File Name:   SourceFile

import java.awt.Color;
import java.util.Random;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

public class atk extends atl
{

    private static final bbt b = new bbt();
    private final Random c = new Random();
    private final Minecraft d;
    private final asu e;
    private int f;
    private String g;
    private int h;
    private boolean i;
    public float a;

    public atk(Minecraft minecraft)
    {
        f = 0;
        g = "";
        h = 0;
        i = false;
        a = 1.0F;
        d = minecraft;
        e = new asu(minecraft);
    }

    public void a(float f1, boolean flag, int j, int k)
    {
        auf auf1 = new auf(d.y, d.c, d.d);
        int l = auf1.a();
        int i1 = auf1.b();
        atj atj1 = d.p;
        d.t.c();
        GL11.glEnable(3042);
        if(Minecraft.t())
        {
            a(d.g.c(f1), l, i1);
        } else
        {
            GL11.glBlendFunc(770, 771);
        }
        um um1 = d.g.bI.f(3);
        if(d.y.T == 0 && um1 != null && um1.c == amj.bd.cm)
        {
            a(l, i1);
        }
        if(!d.g.a(ll.k))
        {
            float f2 = d.g.cj + (d.g.j - d.g.cj) * f1;
            if(f2 > 0.0F)
            {
                b(f2, l, i1);
            }
        }
        if(!d.b.a())
        {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glBindTexture(3553, d.o.b("/gui/gui.png"));
            qw qw1 = d.g.bI;
            this.j = -90F;
            b(l / 2 - 91, i1 - 22, 0, 0, 182, 22);
            b((l / 2 - 91 - 1) + qw1.c * 20, i1 - 22 - 1, 0, 22, 24, 22);
            GL11.glBindTexture(3553, d.o.b("/gui/icons.png"));
            GL11.glEnable(3042);
            GL11.glBlendFunc(775, 769);
            b(l / 2 - 7, i1 / 2 - 7, 0, 0, 16, 16);
            GL11.glDisable(3042);
            boolean flag1 = (d.g.ae / 3) % 2 == 1;
            if(d.g.ae < 10)
            {
                flag1 = false;
            }
            int k1 = d.g.aU();
            int i3 = d.g.aR;
            c.setSeed(f * 0x4c627);
            boolean flag3 = false;
            ro ro1 = d.g.cd();
            int j5 = ro1.a();
            int l5 = ro1.b();
            d.I.a("bossHealth");
            d();
            d.I.b();
            if(d.b.b())
            {
                int j6 = l / 2 - 91;
                int i7 = l / 2 + 91;
                d.I.a("expBar");
                int l7 = d.g.cc();
                if(l7 > 0)
                {
                    char c1 = '\266';
                    int j9 = (int)(d.g.cf * (float)(c1 + 1));
                    int i10 = (i1 - 32) + 3;
                    b(j6, i10, 0, 64, c1, 5);
                    if(j9 > 0)
                    {
                        b(j6, i10, 0, 69, j9, 5);
                    }
                }
                int k8 = i1 - 39;
                int k9 = k8 - 10;
                int j10 = WMLLCompatibility.forgeA(d);
                int i11 = -1;
                if(d.g.a(ll.l))
                {
                    i11 = f % 25;
                }
                d.I.c("healthArmor");
                for(int k11 = 0; k11 < 10; k11++)
                {
                    if(j10 > 0)
                    {
                        int j12 = j6 + k11 * 8;
                        if(k11 * 2 + 1 < j10)
                        {
                            b(j12, k9, 34, 9, 9, 9);
                        }
                        if(k11 * 2 + 1 == j10)
                        {
                            b(j12, k9, 25, 9, 9, 9);
                        }
                        if(k11 * 2 + 1 > j10)
                        {
                            b(j12, k9, 16, 9, 9, 9);
                        }
                    }
                    int k12 = 16;
                    if(d.g.a(ll.u))
                    {
                        k12 += 36;
                    } else
                    if(d.g.a(ll.v))
                    {
                        k12 += 72;
                    }
                    int j13 = 0;
                    if(flag1)
                    {
                        j13 = 1;
                    }
                    int i14 = j6 + k11 * 8;
                    int k14 = k8;
                    if(k1 <= 4)
                    {
                        k14 += c.nextInt(2);
                    }
                    if(k11 == i11)
                    {
                        k14 -= 2;
                    }
                    byte byte3 = 0;
                    if(d.e.K().t())
                    {
                        byte3 = 5;
                    }
                    b(i14, k14, 16 + j13 * 9, 9 * byte3, 9, 9);
                    if(flag1)
                    {
                        if(k11 * 2 + 1 < i3)
                        {
                            b(i14, k14, k12 + 54, 9 * byte3, 9, 9);
                        }
                        if(k11 * 2 + 1 == i3)
                        {
                            b(i14, k14, k12 + 63, 9 * byte3, 9, 9);
                        }
                    }
                    if(k11 * 2 + 1 < k1)
                    {
                        b(i14, k14, k12 + 36, 9 * byte3, 9, 9);
                    }
                    if(k11 * 2 + 1 == k1)
                    {
                        b(i14, k14, k12 + 45, 9 * byte3, 9, 9);
                    }
                }

                d.I.c("food");
                for(int l11 = 0; l11 < 10; l11++)
                {
                    int l12 = k8;
                    int k13 = 16;
                    byte byte2 = 0;
                    if(d.g.a(ll.s))
                    {
                        k13 += 36;
                        byte2 = 13;
                    }
                    if(d.g.cd().e() <= 0.0F && f % (j5 * 3 + 1) == 0)
                    {
                        l12 += c.nextInt(3) - 1;
                    }
                    if(flag3)
                    {
                        byte2 = 1;
                    }
                    int l14 = i7 - l11 * 8 - 9;
                    b(l14, l12, 16 + byte2 * 9, 27, 9, 9);
                    if(flag3)
                    {
                        if(l11 * 2 + 1 < l5)
                        {
                            b(l14, l12, k13 + 54, 27, 9, 9);
                        }
                        if(l11 * 2 + 1 == l5)
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

                d.I.c("air");
                if(d.g.a(agb.h))
                {
                    int i12 = d.g.al();
                    int i13 = ke.f(((double)(i12 - 2) * 10D) / 300D);
                    int l13 = ke.f(((double)i12 * 10D) / 300D) - i13;
                    for(int j14 = 0; j14 < i13 + l13; j14++)
                    {
                        if(j14 < i13)
                        {
                            b(i7 - j14 * 8 - 9, k9, 16, 18, 9, 9);
                        } else
                        {
                            b(i7 - j14 * 8 - 9, k9, 25, 18, 9, 9);
                        }
                    }

                }
                d.I.b();
            }
            GL11.glDisable(3042);
            d.I.a("actionBar");
            GL11.glEnable(32826);
            aro.c();
            for(int k6 = 0; k6 < 9; k6++)
            {
                int j7 = (l / 2 - 90) + k6 * 20 + 2;
                int i8 = i1 - 16 - 3;
                a(k6, j7, i8, f1);
            }

            aro.a();
            GL11.glDisable(32826);
            d.I.b();
        }
        if(d.g.bZ() > 0)
        {
            d.I.a("sleep");
            GL11.glDisable(2929);
            GL11.glDisable(3008);
            int j1 = d.g.bZ();
            float f3 = (float)j1 / 100F;
            if(f3 > 1.0F)
            {
                f3 = 1.0F - (float)(j1 - 100) / 10F;
            }
            int i2 = (int)(220F * f3) << 24 | 0x101020;
            a(0, 0, l, i1, i2);
            GL11.glEnable(3008);
            GL11.glEnable(2929);
            d.I.b();
        }
        if(d.b.f() && d.g.cd > 0)
        {
            d.I.a("expLevel");
            boolean flag2 = false;
            int j2 = flag2 ? 0xffffff : 0x80ff20;
            String s1 = (new StringBuilder()).append("").append(d.g.cd).toString();
            int i4 = (l - atj1.a(s1)) / 2;
            int k4 = i1 - 31 - 4;
            atj1.b(s1, i4 + 1, k4, 0);
            atj1.b(s1, i4 - 1, k4, 0);
            atj1.b(s1, i4, k4 + 1, 0);
            atj1.b(s1, i4, k4 - 1, 0);
            atj1.b(s1, i4, k4, j2);
            d.I.b();
        }
        if(d.q())
        {
            d.I.a("demo");
            String s = "";
            if(d.e.F() >= 0x1d6b4L)
            {
                s = bm.a("demo.demoExpired");
            } else
            {
                s = String.format(bm.a("demo.remainingTime"), new Object[] {
                    km.a((int)(0x1d6b4L - d.e.F()))
                });
            }
            int k2 = atj1.a(s);
            atj1.a(s, l - k2 - 10, 5, 0xffffff);
            d.I.b();
        }
        WMLL.i.updategui(d, this);
        if(d.y.U)
        {
            d.I.a("debug");
            GL11.glPushMatrix();
            atj1.a((new StringBuilder()).append("Minecraft 1.4.5 (").append(d.K).append(")").toString(), 2, 2, 0xffffff);
            atj1.a(d.m(), 2, 12, 0xffffff);
            atj1.a(d.n(), 2, 22, 0xffffff);
            atj1.a(d.p(), 2, 32, 0xffffff);
            atj1.a(d.o(), 2, 42, 0xffffff);
            long l1 = Runtime.getRuntime().maxMemory();
            long l3 = Runtime.getRuntime().totalMemory();
            long l4 = Runtime.getRuntime().freeMemory();
            long l6 = l3 - l4;
            String s2 = (new StringBuilder()).append("Used memory: ").append((l6 * 100L) / l1).append("% (").append(l6 / 1024L / 1024L).append("MB) of ").append(l1 / 1024L / 1024L).append("MB").toString();
            b(atj1, s2, l - atj1.a(s2) - 2, 2, 0xe0e0e0);
            s2 = (new StringBuilder()).append("Allocated memory: ").append((l3 * 100L) / l1).append("% (").append(l3 / 1024L / 1024L).append("MB)").toString();
            b(atj1, s2, l - atj1.a(s2) - 2, 12, 0xe0e0e0);
            int l8 = ke.c(d.g.t);
            int l9 = ke.c(d.g.u);
            int k10 = ke.c(d.g.v);
            b(atj1, String.format("x: %.5f (%d) // c: %d (%d)", new Object[] {
                Double.valueOf(d.g.t), Integer.valueOf(l8), Integer.valueOf(l8 >> 4), Integer.valueOf(l8 & 0xf)
            }), 2, 64, 0xe0e0e0);
            b(atj1, String.format("y: %.3f (feet pos, %.3f eyes pos)", new Object[] {
                Double.valueOf(d.g.D.b), Double.valueOf(d.g.u)
            }), 2, 72, 0xe0e0e0);
            b(atj1, String.format("z: %.5f (%d) // c: %d (%d)", new Object[] {
                Double.valueOf(d.g.v), Integer.valueOf(k10), Integer.valueOf(k10 >> 4), Integer.valueOf(k10 & 0xf)
            }), 2, 80, 0xe0e0e0);
            int j11 = ke.c((double)((d.g.z * 4F) / 360F) + 0.5D) & 3;
            b(atj1, (new StringBuilder()).append("f: ").append(j11).append(" (").append(q.c[j11]).append(") / ").append(ke.g(d.g.z)).toString(), 2, 88, 0xe0e0e0);
            if(d.e != null && d.e.f(l8, l9, k10))
            {
                zs zs1 = d.e.d(l8, k10);
                b(atj1, (new StringBuilder()).append("lc: ").append(zs1.h() + 15).append(" b: ").append(zs1.a(l8 & 0xf, k10 & 0xf, d.e.t()).y).append(" bl: ").append(zs1.a(yh.b, l8 & 0xf, l9, k10 & 0xf)).append(" sl: ").append(zs1.a(yh.a, l8 & 0xf, l9, k10 & 0xf)).append(" rl: ").append(zs1.c(l8 & 0xf, l9, k10 & 0xf, 0)).toString(), 2, 96, 0xe0e0e0);
            }
            b(atj1, String.format("ws: %.3f, fs: %.3f, g: %b, fl: %d", new Object[] {
                Float.valueOf(d.g.cc.b()), Float.valueOf(d.g.cc.a()), Boolean.valueOf(d.g.E), Integer.valueOf(d.e.f(l8, k10))
            }), 2, 104, 0xe0e0e0);
            GL11.glPopMatrix();
            d.I.b();
        }
        if(h > 0)
        {
            d.I.a("overlayMessage");
            float f4 = (float)h - f1;
            int l2 = (int)((f4 * 256F) / 20F);
            if(l2 > 255)
            {
                l2 = 255;
            }
            if(l2 > 0)
            {
                GL11.glPushMatrix();
                GL11.glTranslatef(l / 2, i1 - 48, 0.0F);
                GL11.glEnable(3042);
                GL11.glBlendFunc(770, 771);
                int j3 = 0xffffff;
                if(i)
                {
                    j3 = Color.HSBtoRGB(f4 / 50F, 0.7F, 0.6F) & 0xffffff;
                }
                atj1.b(g, -atj1.a(g) / 2, -4, j3 + (l2 << 24));
                GL11.glDisable(3042);
                GL11.glPopMatrix();
            }
            d.I.b();
        }
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3008);
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, i1 - 48, 0.0F);
        d.I.a("chat");
        e.a(f);
        d.I.b();
        GL11.glPopMatrix();
        if(d.y.M.e && (!d.A() || d.g.a.c.size() > 1))
        {
            d.I.a("playerList");
            axz axz1 = d.g.a;
            java.util.List list = axz1.c;
            int k3 = axz1.d;
            int j4 = k3;
            int i5 = 1;
            for(; j4 > 20; j4 = ((k3 + i5) - 1) / i5)
            {
                i5++;
            }

            int k5 = 300 / i5;
            if(k5 > 150)
            {
                k5 = 150;
            }
            int i6 = (l - i5 * k5) / 2;
            byte byte0 = 10;
            a(i6 - 1, byte0 - 1, i6 + k5 * i5, byte0 + 9 * j4, 0x80000000);
            for(int k7 = 0; k7 < k3; k7++)
            {
                int j8 = i6 + (k7 % i5) * k5;
                int i9 = byte0 + (k7 / i5) * 9;
                a(j8, i9, (j8 + k5) - 1, i9 + 8, 0x20ffffff);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glEnable(3008);
                if(k7 >= list.size())
                {
                    continue;
                }
                ayl ayl1 = (ayl)list.get(k7);
                atj1.a(ayl1.a, j8, i9, 0xffffff);
                d.o.b(d.o.b("/gui/icons.png"));
                int l10 = 0;
                byte byte1 = 0;
                if(ayl1.b < 0)
                {
                    byte1 = 5;
                } else
                if(ayl1.b < 150)
                {
                    byte1 = 0;
                } else
                if(ayl1.b < 300)
                {
                    byte1 = 1;
                } else
                if(ayl1.b < 600)
                {
                    byte1 = 2;
                } else
                if(ayl1.b < 1000)
                {
                    byte1 = 3;
                } else
                {
                    byte1 = 4;
                }
                this.j += 100F;
                b((j8 + k5) - 12, i9, 0 + l10 * 10, 176 + byte1 * 8, 10, 8);
                this.j -= 100F;
            }

        }
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(2896);
        GL11.glEnable(3008);
    }

    private void d()
    {
        if(azx.c == null || azx.b <= 0)
        {
            return;
        }
        azx.b--;
        atj atj1 = d.p;
        auf auf1 = new auf(d.y, d.c, d.d);
        int j = auf1.a();
        char c1 = '\266';
        int k = j / 2 - c1 / 2;
        int l = (int)(azx.a * (float)(c1 + 1));
        byte byte0 = 12;
        b(k, byte0, 0, 74, c1, 5);
        b(k, byte0, 0, 74, c1, 5);
        if(l > 0)
        {
            b(k, byte0, 0, 79, l, 5);
        }
        String s = azx.c;
        atj1.a(s, j / 2 - atj1.a(s) / 2, byte0 - 10, 0xffffff);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glBindTexture(3553, d.o.b("/gui/icons.png"));
    }

    private void a(int j, int k)
    {
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(3008);
        GL11.glBindTexture(3553, d.o.b("%blur%/misc/pumpkinblur.png"));
        bao bao1 = bao.a;
        bao1.b();
        bao1.a(0.0D, k, -90D, 0.0D, 1.0D);
        bao1.a(j, k, -90D, 1.0D, 1.0D);
        bao1.a(j, 0.0D, -90D, 1.0D, 0.0D);
        bao1.a(0.0D, 0.0D, -90D, 0.0D, 0.0D);
        bao1.a();
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
        GL11.glEnable(3008);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    private void a(float f1, int j, int k)
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
        GL11.glBindTexture(3553, d.o.b("%blur%/misc/vignette.png"));
        bao bao1 = bao.a;
        bao1.b();
        bao1.a(0.0D, k, -90D, 0.0D, 1.0D);
        bao1.a(j, k, -90D, 1.0D, 1.0D);
        bao1.a(j, 0.0D, -90D, 1.0D, 0.0D);
        bao1.a(0.0D, 0.0D, -90D, 0.0D, 0.0D);
        bao1.a();
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glBlendFunc(770, 771);
    }

    private void b(float f1, int j, int k)
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
        GL11.glBindTexture(3553, d.o.b("/terrain.png"));
        float f2 = (float)(amj.bh.cl % 16) / 16F;
        float f3 = (float)(amj.bh.cl / 16) / 16F;
        float f4 = (float)(amj.bh.cl % 16 + 1) / 16F;
        float f5 = (float)(amj.bh.cl / 16 + 1) / 16F;
        bao bao1 = bao.a;
        bao1.b();
        bao1.a(0.0D, k, -90D, f2, f5);
        bao1.a(j, k, -90D, f4, f5);
        bao1.a(j, 0.0D, -90D, f4, f3);
        bao1.a(0.0D, 0.0D, -90D, f2, f3);
        bao1.a();
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
        GL11.glEnable(3008);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    private void a(int j, int k, int l, float f1)
    {
        um um1 = d.g.bI.a[j];
        if(um1 == null)
        {
            return;
        }
        float f2 = (float)um1.b - f1;
        if(f2 > 0.0F)
        {
            GL11.glPushMatrix();
            float f3 = 1.0F + f2 / 5F;
            GL11.glTranslatef(k + 8, l + 12, 0.0F);
            GL11.glScalef(1.0F / f3, (f3 + 1.0F) / 2.0F, 1.0F);
            GL11.glTranslatef(-(k + 8), -(l + 12), 0.0F);
        }
        b.b(d.p, d.o, um1, k, l);
        if(f2 > 0.0F)
        {
            GL11.glPopMatrix();
        }
        b.c(d.p, d.o, um1, k, l);
    }

    public void a()
    {
        if(h > 0)
        {
            h--;
        }
        f++;
    }

    public void a(String s)
    {
        g = (new StringBuilder()).append("Now playing: ").append(s).toString();
        h = 60;
        i = true;
    }

    public asu b()
    {
        return e;
    }

    public int c()
    {
        return f;
    }

}
