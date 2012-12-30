// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst noconv 
// Source File Name:   SourceFile

import java.awt.Color;
import java.util.Random;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

public class atr extends ats
{

    private static final bce b = new bce();
    private final Random c = new Random();
    private final Minecraft d;
    private final atc e;
    private int f;
    private String g;
    private int h;
    private boolean i;
    public float a;
    private int k;
    private ur l;

    public atr(Minecraft minecraft)
    {
        f = 0;
        g = "";
        h = 0;
        i = false;
        a = 1.0F;
        d = minecraft;
        e = new atc(minecraft);
    }

    public void a(float f1, boolean flag, int j, int i1)
    {
        aum aum1 = new aum(d.y, d.c, d.d);
        int j1 = aum1.a();
        int k1 = aum1.b();
        atq atq1 = d.p;
        d.t.c();
        GL11.glEnable(3042);
        if(Minecraft.t())
        {
            a(d.g.c(f1), j1, k1);
        } else
        {
            GL11.glBlendFunc(770, 771);
        }
        ur ur1 = d.g.bJ.f(3);
        if(d.y.W == 0 && ur1 != null && ur1.c == amq.bd.cm)
        {
            a(j1, k1);
        }
        if(!d.g.a(ll.k))
        {
            float f2 = d.g.ck + (d.g.j - d.g.ck) * f1;
            if(f2 > 0.0F)
            {
                b(f2, j1, k1);
            }
        }
        if(!d.b.a())
        {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glBindTexture(3553, d.o.b("/gui/gui.png"));
            qw qw1 = d.g.bJ;
            this.j = -90F;
            b(j1 / 2 - 91, k1 - 22, 0, 0, 182, 22);
            b((j1 / 2 - 91 - 1) + qw1.c * 20, k1 - 22 - 1, 0, 22, 24, 22);
            GL11.glBindTexture(3553, d.o.b("/gui/icons.png"));
            GL11.glEnable(3042);
            GL11.glBlendFunc(775, 769);
            b(j1 / 2 - 7, k1 / 2 - 7, 0, 0, 16, 16);
            GL11.glDisable(3042);
            boolean flag1 = (d.g.ae / 3) % 2 == 1;
            if(d.g.ae < 10)
            {
                flag1 = false;
            }
            int i2 = d.g.aU();
            int l3 = d.g.aS;
            c.setSeed(f * 0x4c627);
            boolean flag3 = false;
            rp rp1 = d.g.cc();
            int j6 = rp1.a();
            int i7 = rp1.b();
            d.I.a("bossHealth");
            d();
            d.I.b();
            if(d.b.b())
            {
                int k7 = j1 / 2 - 91;
                int j8 = j1 / 2 + 91;
                d.I.a("expBar");
                int i9 = d.g.cb();
                if(i9 > 0)
                {
                    char c1 = '\266';
                    int k10 = (int)(d.g.cg * (float)(c1 + 1));
                    int j11 = (k1 - 32) + 3;
                    b(k7, j11, 0, 64, c1, 5);
                    if(k10 > 0)
                    {
                        b(k7, j11, 0, 69, k10, 5);
                    }
                }
                int l9 = k1 - 39;
                int l10 = l9 - 10;
                int k11 = d.g.aW();
                int j12 = -1;
                if(d.g.a(ll.l))
                {
                    j12 = f % 25;
                }
                d.I.c("healthArmor");
                for(int l12 = 0; l12 < 10; l12++)
                {
                    if(k11 > 0)
                    {
                        int k13 = k7 + l12 * 8;
                        if(l12 * 2 + 1 < k11)
                        {
                            b(k13, l10, 34, 9, 9, 9);
                        }
                        if(l12 * 2 + 1 == k11)
                        {
                            b(k13, l10, 25, 9, 9, 9);
                        }
                        if(l12 * 2 + 1 > k11)
                        {
                            b(k13, l10, 16, 9, 9, 9);
                        }
                    }
                    int l13 = 16;
                    if(d.g.a(ll.u))
                    {
                        l13 += 36;
                    } else
                    if(d.g.a(ll.v))
                    {
                        l13 += 72;
                    }
                    int k14 = 0;
                    if(flag1)
                    {
                        k14 = 1;
                    }
                    int j15 = k7 + l12 * 8;
                    int l15 = l9;
                    if(i2 <= 4)
                    {
                        l15 += c.nextInt(2);
                    }
                    if(l12 == j12)
                    {
                        l15 -= 2;
                    }
                    byte byte3 = 0;
                    if(d.e.K().t())
                    {
                        byte3 = 5;
                    }
                    b(j15, l15, 16 + k14 * 9, 9 * byte3, 9, 9);
                    if(flag1)
                    {
                        if(l12 * 2 + 1 < l3)
                        {
                            b(j15, l15, l13 + 54, 9 * byte3, 9, 9);
                        }
                        if(l12 * 2 + 1 == l3)
                        {
                            b(j15, l15, l13 + 63, 9 * byte3, 9, 9);
                        }
                    }
                    if(l12 * 2 + 1 < i2)
                    {
                        b(j15, l15, l13 + 36, 9 * byte3, 9, 9);
                    }
                    if(l12 * 2 + 1 == i2)
                    {
                        b(j15, l15, l13 + 45, 9 * byte3, 9, 9);
                    }
                }

                d.I.c("food");
                for(int i13 = 0; i13 < 10; i13++)
                {
                    int i14 = l9;
                    int l14 = 16;
                    byte byte2 = 0;
                    if(d.g.a(ll.s))
                    {
                        l14 += 36;
                        byte2 = 13;
                    }
                    if(d.g.cc().e() <= 0.0F && f % (j6 * 3 + 1) == 0)
                    {
                        i14 += c.nextInt(3) - 1;
                    }
                    if(flag3)
                    {
                        byte2 = 1;
                    }
                    int i16 = j8 - i13 * 8 - 9;
                    b(i16, i14, 16 + byte2 * 9, 27, 9, 9);
                    if(flag3)
                    {
                        if(i13 * 2 + 1 < i7)
                        {
                            b(i16, i14, l14 + 54, 27, 9, 9);
                        }
                        if(i13 * 2 + 1 == i7)
                        {
                            b(i16, i14, l14 + 63, 27, 9, 9);
                        }
                    }
                    if(i13 * 2 + 1 < j6)
                    {
                        b(i16, i14, l14 + 36, 27, 9, 9);
                    }
                    if(i13 * 2 + 1 == j6)
                    {
                        b(i16, i14, l14 + 45, 27, 9, 9);
                    }
                }

                d.I.c("air");
                if(d.g.a(agi.h))
                {
                    int j13 = d.g.al();
                    int j14 = ke.f(((double)(j13 - 2) * 10D) / 300D);
                    int i15 = ke.f(((double)j13 * 10D) / 300D) - j14;
                    for(int k15 = 0; k15 < j14 + i15; k15++)
                    {
                        if(k15 < j14)
                        {
                            b(j8 - k15 * 8 - 9, l10, 16, 18, 9, 9);
                        } else
                        {
                            b(j8 - k15 * 8 - 9, l10, 25, 18, 9, 9);
                        }
                    }

                }
                d.I.b();
            }
            GL11.glDisable(3042);
            d.I.a("actionBar");
            GL11.glEnable(32826);
            arw.c();
            for(int i8 = 0; i8 < 9; i8++)
            {
                int k8 = (j1 / 2 - 90) + i8 * 20 + 2;
                int j9 = k1 - 16 - 3;
                a(i8, k8, j9, f1);
            }

            arw.a();
            GL11.glDisable(32826);
            d.I.b();
        }
        if(d.g.bY() > 0)
        {
            d.I.a("sleep");
            GL11.glDisable(2929);
            GL11.glDisable(3008);
            int l1 = d.g.bY();
            float f3 = (float)l1 / 100F;
            if(f3 > 1.0F)
            {
                f3 = 1.0F - (float)(l1 - 100) / 10F;
            }
            int j2 = (int)(220F * f3) << 24 | 0x101020;
            a(0, 0, j1, k1, j2);
            GL11.glEnable(3008);
            GL11.glEnable(2929);
            d.I.b();
        }
        if(d.b.f() && d.g.ce > 0)
        {
            d.I.a("expLevel");
            boolean flag2 = false;
            int k2 = flag2 ? 0xffffff : 0x80ff20;
            String s2 = (new StringBuilder()).append("").append(d.g.ce).toString();
            int i5 = (j1 - atq1.a(s2)) / 2;
            int l5 = k1 - 31 - 4;
            atq1.b(s2, i5 + 1, l5, 0);
            atq1.b(s2, i5 - 1, l5, 0);
            atq1.b(s2, i5, l5 + 1, 0);
            atq1.b(s2, i5, l5 - 1, 0);
            atq1.b(s2, i5, l5, k2);
            d.I.b();
        }
        if(d.y.D)
        {
            d.I.a("toolHighlight");
            if(k > 0 && l != null)
            {
                String s = l.r();
                int i3 = (j1 - atq1.a(s)) / 2;
                int i4 = k1 - 59;
                if(!d.b.b())
                {
                    i4 += 14;
                }
                int j5 = (int)(((float)k * 256F) / 10F);
                if(j5 > 255)
                {
                    j5 = 255;
                }
                if(j5 > 0)
                {
                    GL11.glPushMatrix();
                    GL11.glEnable(3042);
                    GL11.glBlendFunc(770, 771);
                    atq1.a(s, i3, i4, 0xffffff + (j5 << 24));
                    GL11.glDisable(3042);
                    GL11.glPopMatrix();
                }
            }
            d.I.b();
        }
        if(d.q())
        {
            d.I.a("demo");
            String s1 = "";
            if(d.e.F() >= 0x1d6b4L)
            {
                s1 = bm.a("demo.demoExpired");
            } else
            {
                s1 = String.format(bm.a("demo.remainingTime"), new Object[] {
                    km.a((int)(0x1d6b4L - d.e.F()))
                });
            }
            int j3 = atq1.a(s1);
            atq1.a(s1, j1 - j3 - 10, 5, 0xffffff);
            d.I.b();
        }
        WMLL.i.updategui(d, this);
        if(d.y.X)
        {
            d.I.a("debug");
            GL11.glPushMatrix();
            atq1.a((new StringBuilder()).append("Minecraft 1.4.7 (").append(d.K).append(")").toString(), 2, 2, 0xffffff);
            atq1.a(d.m(), 2, 12, 0xffffff);
            atq1.a(d.n(), 2, 22, 0xffffff);
            atq1.a(d.p(), 2, 32, 0xffffff);
            atq1.a(d.o(), 2, 42, 0xffffff);
            long l2 = Runtime.getRuntime().maxMemory();
            long l4 = Runtime.getRuntime().totalMemory();
            long l6 = Runtime.getRuntime().freeMemory();
            long l7 = l4 - l6;
            String s3 = (new StringBuilder()).append("Used memory: ").append((l7 * 100L) / l2).append("% (").append(l7 / 1024L / 1024L).append("MB) of ").append(l2 / 1024L / 1024L).append("MB").toString();
            b(atq1, s3, j1 - atq1.a(s3) - 2, 2, 0xe0e0e0);
            s3 = (new StringBuilder()).append("Allocated memory: ").append((l4 * 100L) / l2).append("% (").append(l4 / 1024L / 1024L).append("MB)").toString();
            b(atq1, s3, j1 - atq1.a(s3) - 2, 12, 0xe0e0e0);
            int i10 = ke.c(d.g.t);
            int i11 = ke.c(d.g.u);
            int l11 = ke.c(d.g.v);
            b(atq1, String.format("x: %.5f (%d) // c: %d (%d)", new Object[] {
                Double.valueOf(d.g.t), Integer.valueOf(i10), Integer.valueOf(i10 >> 4), Integer.valueOf(i10 & 0xf)
            }), 2, 64, 0xe0e0e0);
            b(atq1, String.format("y: %.3f (feet pos, %.3f eyes pos)", new Object[] {
                Double.valueOf(d.g.D.b), Double.valueOf(d.g.u)
            }), 2, 72, 0xe0e0e0);
            b(atq1, String.format("z: %.5f (%d) // c: %d (%d)", new Object[] {
                Double.valueOf(d.g.v), Integer.valueOf(l11), Integer.valueOf(l11 >> 4), Integer.valueOf(l11 & 0xf)
            }), 2, 80, 0xe0e0e0);
            int k12 = ke.c((double)((d.g.z * 4F) / 360F) + 0.5D) & 3;
            b(atq1, (new StringBuilder()).append("f: ").append(k12).append(" (").append(q.c[k12]).append(") / ").append(ke.g(d.g.z)).toString(), 2, 88, 0xe0e0e0);
            if(d.e != null && d.e.f(i10, i11, l11))
            {
                zz zz1 = d.e.d(i10, l11);
                b(atq1, (new StringBuilder()).append("lc: ").append(zz1.h() + 15).append(" b: ").append(zz1.a(i10 & 0xf, l11 & 0xf, d.e.t()).y).append(" bl: ").append(zz1.a(yo.b, i10 & 0xf, i11, l11 & 0xf)).append(" sl: ").append(zz1.a(yo.a, i10 & 0xf, i11, l11 & 0xf)).append(" rl: ").append(zz1.c(i10 & 0xf, i11, l11 & 0xf, 0)).toString(), 2, 96, 0xe0e0e0);
            }
            b(atq1, String.format("ws: %.3f, fs: %.3f, g: %b, fl: %d", new Object[] {
                Float.valueOf(d.g.cd.b()), Float.valueOf(d.g.cd.a()), Boolean.valueOf(d.g.E), Integer.valueOf(d.e.f(i10, l11))
            }), 2, 104, 0xe0e0e0);
            GL11.glPopMatrix();
            d.I.b();
        }
        if(h > 0)
        {
            d.I.a("overlayMessage");
            float f4 = (float)h - f1;
            int k3 = (int)((f4 * 256F) / 20F);
            if(k3 > 255)
            {
                k3 = 255;
            }
            if(k3 > 0)
            {
                GL11.glPushMatrix();
                GL11.glTranslatef(j1 / 2, k1 - 48, 0.0F);
                GL11.glEnable(3042);
                GL11.glBlendFunc(770, 771);
                int j4 = 0xffffff;
                if(i)
                {
                    j4 = Color.HSBtoRGB(f4 / 50F, 0.7F, 0.6F) & 0xffffff;
                }
                atq1.b(g, -atq1.a(g) / 2, -4, j4 + (k3 << 24));
                GL11.glDisable(3042);
                GL11.glPopMatrix();
            }
            d.I.b();
        }
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3008);
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, k1 - 48, 0.0F);
        d.I.a("chat");
        e.a(f);
        d.I.b();
        GL11.glPopMatrix();
        if(d.y.P.e && (!d.A() || d.g.a.c.size() > 1))
        {
            d.I.a("playerList");
            ayh ayh1 = d.g.a;
            java.util.List list = ayh1.c;
            int k4 = ayh1.d;
            int k5 = k4;
            int i6 = 1;
            for(; k5 > 20; k5 = ((k4 + i6) - 1) / i6)
            {
                i6++;
            }

            int k6 = 300 / i6;
            if(k6 > 150)
            {
                k6 = 150;
            }
            int j7 = (j1 - i6 * k6) / 2;
            byte byte0 = 10;
            a(j7 - 1, byte0 - 1, j7 + k6 * i6, byte0 + 9 * k5, 0x80000000);
            for(int l8 = 0; l8 < k4; l8++)
            {
                int k9 = j7 + (l8 % i6) * k6;
                int j10 = byte0 + (l8 / i6) * 9;
                a(k9, j10, (k9 + k6) - 1, j10 + 8, 0x20ffffff);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glEnable(3008);
                if(l8 >= list.size())
                {
                    continue;
                }
                ayt ayt1 = (ayt)list.get(l8);
                atq1.a(ayt1.a, k9, j10, 0xffffff);
                d.o.b(d.o.b("/gui/icons.png"));
                int i12 = 0;
                byte byte1 = 0;
                if(ayt1.b < 0)
                {
                    byte1 = 5;
                } else
                if(ayt1.b < 150)
                {
                    byte1 = 0;
                } else
                if(ayt1.b < 300)
                {
                    byte1 = 1;
                } else
                if(ayt1.b < 600)
                {
                    byte1 = 2;
                } else
                if(ayt1.b < 1000)
                {
                    byte1 = 3;
                } else
                {
                    byte1 = 4;
                }
                this.j += 100F;
                b((k9 + k6) - 12, j10, 0 + i12 * 10, 176 + byte1 * 8, 10, 8);
                this.j -= 100F;
            }

        }
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(2896);
        GL11.glEnable(3008);
    }

    private void d()
    {
        if(bai.c == null || bai.b <= 0)
        {
            return;
        }
        bai.b--;
        atq atq1 = d.p;
        aum aum1 = new aum(d.y, d.c, d.d);
        int j = aum1.a();
        char c1 = '\266';
        int i1 = j / 2 - c1 / 2;
        int j1 = (int)(bai.a * (float)(c1 + 1));
        byte byte0 = 12;
        b(i1, byte0, 0, 74, c1, 5);
        b(i1, byte0, 0, 74, c1, 5);
        if(j1 > 0)
        {
            b(i1, byte0, 0, 79, j1, 5);
        }
        String s = bai.c;
        atq1.a(s, j / 2 - atq1.a(s) / 2, byte0 - 10, 0xffffff);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glBindTexture(3553, d.o.b("/gui/icons.png"));
    }

    private void a(int j, int i1)
    {
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(3008);
        GL11.glBindTexture(3553, d.o.b("%blur%/misc/pumpkinblur.png"));
        baz baz1 = baz.a;
        baz1.b();
        baz1.a(0.0D, i1, -90D, 0.0D, 1.0D);
        baz1.a(j, i1, -90D, 1.0D, 1.0D);
        baz1.a(j, 0.0D, -90D, 1.0D, 0.0D);
        baz1.a(0.0D, 0.0D, -90D, 0.0D, 0.0D);
        baz1.a();
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
        GL11.glEnable(3008);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    private void a(float f1, int j, int i1)
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
        baz baz1 = baz.a;
        baz1.b();
        baz1.a(0.0D, i1, -90D, 0.0D, 1.0D);
        baz1.a(j, i1, -90D, 1.0D, 1.0D);
        baz1.a(j, 0.0D, -90D, 1.0D, 0.0D);
        baz1.a(0.0D, 0.0D, -90D, 0.0D, 0.0D);
        baz1.a();
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glBlendFunc(770, 771);
    }

    private void b(float f1, int j, int i1)
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
        float f2 = (float)(amq.bh.cl % 16) / 16F;
        float f3 = (float)(amq.bh.cl / 16) / 16F;
        float f4 = (float)(amq.bh.cl % 16 + 1) / 16F;
        float f5 = (float)(amq.bh.cl / 16 + 1) / 16F;
        baz baz1 = baz.a;
        baz1.b();
        baz1.a(0.0D, i1, -90D, f2, f5);
        baz1.a(j, i1, -90D, f4, f5);
        baz1.a(j, 0.0D, -90D, f4, f3);
        baz1.a(0.0D, 0.0D, -90D, f2, f3);
        baz1.a();
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
        GL11.glEnable(3008);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    private void a(int j, int i1, int j1, float f1)
    {
        ur ur1 = d.g.bJ.a[j];
        if(ur1 == null)
        {
            return;
        }
        float f2 = (float)ur1.b - f1;
        if(f2 > 0.0F)
        {
            GL11.glPushMatrix();
            float f3 = 1.0F + f2 / 5F;
            GL11.glTranslatef(i1 + 8, j1 + 12, 0.0F);
            GL11.glScalef(1.0F / f3, (f3 + 1.0F) / 2.0F, 1.0F);
            GL11.glTranslatef(-(i1 + 8), -(j1 + 12), 0.0F);
        }
        b.b(d.p, d.o, ur1, i1, j1);
        if(f2 > 0.0F)
        {
            GL11.glPopMatrix();
        }
        b.c(d.p, d.o, ur1, i1, j1);
    }

    public void a()
    {
        if(h > 0)
        {
            h--;
        }
        f++;
        if(d.g != null)
        {
            ur ur1 = d.g.bJ.g();
            if(ur1 == null)
            {
                k = 0;
            } else
            if(l == null || ur1.c != l.c || !ur.a(ur1, l) || !ur1.f() && ur1.j() != l.j())
            {
                k = 40;
            } else
            if(k > 0)
            {
                k--;
            }
            l = ur1;
        }
    }

    public void a(String s)
    {
        g = (new StringBuilder()).append("Now playing: ").append(s).toString();
        h = 60;
        i = true;
    }

    public atc b()
    {
        return e;
    }

    public int c()
    {
        return f;
    }

}
