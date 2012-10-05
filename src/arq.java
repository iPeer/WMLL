// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst noconv 
// Source File Name:   SourceFile

import java.awt.Color;
import java.util.Random;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

public class arq extends arr
{

    private static final azt b = new azt();
    private final Random c = new Random();
    private final Minecraft d;
    private final ara e;
    private int f;
    private String g;
    private int h;
    private boolean i;
    public float a;

    public arq(Minecraft minecraft)
    {
        f = 0;
        g = "";
        h = 0;
        i = false;
        a = 1.0F;
        d = minecraft;
        e = new ara(minecraft);
    }

    public void a(float f1, boolean flag, int j, int k)
    {
        asl asl1 = new asl(d.y, d.c, d.d);
        int l = asl1.a();
        int i1 = asl1.b();
        arp arp1 = d.p;
        d.t.c();
        GL11.glEnable(3042);
        if(Minecraft.t())
        {
            a(d.g.c(f1), l, i1);
        } else
        {
            GL11.glBlendFunc(770, 771);
        }
        tm tm1 = d.g.bJ.f(3);
        if(d.y.R == 0 && tm1 != null && tm1.c == akv.bc.ck)
        {
            a(l, i1);
        }
        if(!d.g.a(kr.k))
        {
            float f2 = d.g.cl + (d.g.j - d.g.cl) * f1;
            if(f2 > 0.0F)
            {
                b(f2, l, i1);
            }
        }
        if(!d.b.a())
        {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glBindTexture(3553, d.o.b("/gui/gui.png"));
            qa qa1 = d.g.bJ;
            this.j = -90F;
            b(l / 2 - 91, i1 - 22, 0, 0, 182, 22);
            b((l / 2 - 91 - 1) + qa1.c * 20, i1 - 22 - 1, 0, 22, 24, 22);
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
            int k1 = d.g.aS();
            int i3 = d.g.aR;
            c.setSeed(f * 0x4c627);
            boolean flag3 = false;
            qs qs1 = d.g.bY();
            int j5 = qs1.a();
            int l5 = qs1.b();
            d.I.a("bossHealth");
            d();
            d.I.b();
            if(d.b.b())
            {
                int j6 = l / 2 - 91;
                int i7 = l / 2 + 91;
                d.I.a("expBar");
                int l7 = d.g.bX();
                if(l7 > 0)
                {
                    char c1 = '\266';
                    int j9 = (int)(d.g.ch * (float)(c1 + 1));
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
                if(d.g.a(kr.l))
                {
                    i11 = f % 25;
                }
                d.I.c("healthArmor");
                for(int j11 = 0; j11 < 10; j11++)
                {
                    if(j10 > 0)
                    {
                        int i12 = j6 + j11 * 8;
                        if(j11 * 2 + 1 < j10)
                        {
                            b(i12, k9, 34, 9, 9, 9);
                        }
                        if(j11 * 2 + 1 == j10)
                        {
                            b(i12, k9, 25, 9, 9, 9);
                        }
                        if(j11 * 2 + 1 > j10)
                        {
                            b(i12, k9, 16, 9, 9, 9);
                        }
                    }
                    int j12 = 16;
                    if(d.g.a(kr.u))
                    {
                        j12 += 36;
                    } else
                    if(d.g.a(kr.v))
                    {
                        j12 += 72;
                    }
                    int i13 = 0;
                    if(flag1)
                    {
                        i13 = 1;
                    }
                    int l13 = j6 + j11 * 8;
                    int j14 = k8;
                    if(k1 <= 4)
                    {
                        j14 += c.nextInt(2);
                    }
                    if(j11 == i11)
                    {
                        j14 -= 2;
                    }
                    byte byte3 = 0;
                    if(d.e.J().t())
                    {
                        byte3 = 5;
                    }
                    b(l13, j14, 16 + i13 * 9, 9 * byte3, 9, 9);
                    if(flag1)
                    {
                        if(j11 * 2 + 1 < i3)
                        {
                            b(l13, j14, j12 + 54, 9 * byte3, 9, 9);
                        }
                        if(j11 * 2 + 1 == i3)
                        {
                            b(l13, j14, j12 + 63, 9 * byte3, 9, 9);
                        }
                    }
                    if(j11 * 2 + 1 < k1)
                    {
                        b(l13, j14, j12 + 36, 9 * byte3, 9, 9);
                    }
                    if(j11 * 2 + 1 == k1)
                    {
                        b(l13, j14, j12 + 45, 9 * byte3, 9, 9);
                    }
                }

                d.I.c("food");
                for(int k11 = 0; k11 < 10; k11++)
                {
                    int k12 = k8;
                    int j13 = 16;
                    byte byte2 = 0;
                    if(d.g.a(kr.s))
                    {
                        j13 += 36;
                        byte2 = 13;
                    }
                    if(d.g.bY().e() <= 0.0F && f % (j5 * 3 + 1) == 0)
                    {
                        k12 += c.nextInt(3) - 1;
                    }
                    if(flag3)
                    {
                        byte2 = 1;
                    }
                    int k14 = i7 - k11 * 8 - 9;
                    b(k14, k12, 16 + byte2 * 9, 27, 9, 9);
                    if(flag3)
                    {
                        if(k11 * 2 + 1 < l5)
                        {
                            b(k14, k12, j13 + 54, 27, 9, 9);
                        }
                        if(k11 * 2 + 1 == l5)
                        {
                            b(k14, k12, j13 + 63, 27, 9, 9);
                        }
                    }
                    if(k11 * 2 + 1 < j5)
                    {
                        b(k14, k12, j13 + 36, 27, 9, 9);
                    }
                    if(k11 * 2 + 1 == j5)
                    {
                        b(k14, k12, j13 + 45, 27, 9, 9);
                    }
                }

                d.I.c("air");
                if(d.g.a(aex.g))
                {
                    int l11 = d.g.al();
                    int l12 = jq.f(((double)(l11 - 2) * 10D) / 300D);
                    int k13 = jq.f(((double)l11 * 10D) / 300D) - l12;
                    for(int i14 = 0; i14 < l12 + k13; i14++)
                    {
                        if(i14 < l12)
                        {
                            b(i7 - i14 * 8 - 9, k9, 16, 18, 9, 9);
                        } else
                        {
                            b(i7 - i14 * 8 - 9, k9, 25, 18, 9, 9);
                        }
                    }

                }
                d.I.b();
            }
            GL11.glDisable(3042);
            d.I.a("actionBar");
            GL11.glEnable(32826);
            apy.c();
            for(int k6 = 0; k6 < 9; k6++)
            {
                int j7 = (l / 2 - 90) + k6 * 20 + 2;
                int i8 = i1 - 16 - 3;
                a(k6, j7, i8, f1);
            }

            apy.a();
            GL11.glDisable(32826);
            d.I.b();
        }
        if(d.g.bU() > 0)
        {
            d.I.a("sleep");
            GL11.glDisable(2929);
            GL11.glDisable(3008);
            int j1 = d.g.bU();
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
        if(d.b.f() && d.g.cf > 0)
        {
            d.I.a("expLevel");
            boolean flag2 = false;
            int j2 = flag2 ? 0xffffff : 0x80ff20;
            String s1 = (new StringBuilder()).append("").append(d.g.cf).toString();
            int i4 = (l - arp1.a(s1)) / 2;
            int k4 = i1 - 31 - 4;
            arp1.b(s1, i4 + 1, k4, 0);
            arp1.b(s1, i4 - 1, k4, 0);
            arp1.b(s1, i4, k4 + 1, 0);
            arp1.b(s1, i4, k4 - 1, 0);
            arp1.b(s1, i4, k4, j2);
            d.I.b();
        }
        if(d.q())
        {
            d.I.a("demo");
            String s = "";
            if(d.e.E() >= 0x1d6b4L)
            {
                s = bc.a("demo.demoExpired");
            } else
            {
                s = String.format(bc.a("demo.remainingTime"), new Object[] {
                    jy.a((int)(0x1d6b4L - d.e.E()))
                });
            }
            int k2 = arp1.a(s);
            arp1.a(s, l - k2 - 10, 5, 0xffffff);
            d.I.b();
        }
        WMLL.i.updategui(d, this);
        if(d.y.S)
        {
            d.I.a("debug");
            GL11.glPushMatrix();
            arp1.a((new StringBuilder()).append("Minecraft 12w40b (").append(d.K).append(")").toString(), 2, 2, 0xffffff);
            arp1.a(d.m(), 2, 12, 0xffffff);
            arp1.a(d.n(), 2, 22, 0xffffff);
            arp1.a(d.p(), 2, 32, 0xffffff);
            arp1.a(d.o(), 2, 42, 0xffffff);
            long l1 = Runtime.getRuntime().maxMemory();
            long l3 = Runtime.getRuntime().totalMemory();
            long l4 = Runtime.getRuntime().freeMemory();
            long l6 = l3 - l4;
            String s2 = (new StringBuilder()).append("Used memory: ").append((l6 * 100L) / l1).append("% (").append(l6 / 1024L / 1024L).append("MB) of ").append(l1 / 1024L / 1024L).append("MB").toString();
            b(arp1, s2, l - arp1.a(s2) - 2, 2, 0xe0e0e0);
            s2 = (new StringBuilder()).append("Allocated memory: ").append((l3 * 100L) / l1).append("% (").append(l3 / 1024L / 1024L).append("MB)").toString();
            b(arp1, s2, l - arp1.a(s2) - 2, 12, 0xe0e0e0);
            int l8 = jq.c(d.g.t);
            int l9 = jq.c(d.g.u);
            int k10 = jq.c(d.g.v);
            b(arp1, String.format("x: %.5f (%d) // c: %d (%d)", new Object[] {
                Double.valueOf(d.g.t), Integer.valueOf(l8), Integer.valueOf(l8 >> 4), Integer.valueOf(l8 & 0xf)
            }), 2, 64, 0xe0e0e0);
            b(arp1, String.format("y: %.3f (feet pos, %.3f eyes pos)", new Object[] {
                Double.valueOf(d.g.D.b), Double.valueOf(d.g.u)
            }), 2, 72, 0xe0e0e0);
            b(arp1, String.format("z: %.5f (%d) // c: %d (%d)", new Object[] {
                Double.valueOf(d.g.v), Integer.valueOf(k10), Integer.valueOf(k10 >> 4), Integer.valueOf(k10 & 0xf)
            }), 2, 80, 0xe0e0e0);
            b(arp1, (new StringBuilder()).append("f: ").append(jq.c((double)((d.g.z * 4F) / 360F) + 0.5D) & 3).append(" / ").append(jq.g(d.g.z)).toString(), 2, 88, 0xe0e0e0);
            if(d.e != null && d.e.e(l8, l9, k10))
            {
                yr yr1 = d.e.d(l8, k10);
                b(arp1, (new StringBuilder()).append("lc: ").append(yr1.h() + 15).append(" b: ").append(yr1.a(l8 & 0xf, k10 & 0xf, d.e.s()).y).append(" bl: ").append(yr1.a(xh.b, l8 & 0xf, l9, k10 & 0xf)).append(" sl: ").append(yr1.a(xh.a, l8 & 0xf, l9, k10 & 0xf)).append(" rl: ").append(yr1.c(l8 & 0xf, l9, k10 & 0xf, 0)).toString(), 2, 96, 0xe0e0e0);
            }
            b(arp1, String.format("ws: %.3f, fs: %.3f, g: %b, fl: %d", new Object[] {
                Float.valueOf(d.g.ce.b()), Float.valueOf(d.g.ce.a()), Boolean.valueOf(d.g.E), Integer.valueOf(d.e.f(l8, k10))
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
                arp1.b(g, -arp1.a(g) / 2, -4, j3 + (l2 << 24));
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
        if(d.y.K.e && (!d.A() || d.g.a.c.size() > 1))
        {
            d.I.a("playerList");
            awe awe1 = d.g.a;
            java.util.List list = awe1.c;
            int k3 = awe1.d;
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
                awq awq1 = (awq)list.get(k7);
                arp1.a(awq1.a, j8, i9, 0xffffff);
                d.o.b(d.o.b("/gui/icons.png"));
                int l10 = 0;
                byte byte1 = 0;
                if(awq1.b < 0)
                {
                    byte1 = 5;
                } else
                if(awq1.b < 150)
                {
                    byte1 = 0;
                } else
                if(awq1.b < 300)
                {
                    byte1 = 1;
                } else
                if(awq1.b < 600)
                {
                    byte1 = 2;
                } else
                if(awq1.b < 1000)
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
        if(ayb.c == null || ayb.b <= 0)
        {
            return;
        }
        ayb.b--;
        arp arp1 = d.p;
        asl asl1 = new asl(d.y, d.c, d.d);
        int j = asl1.a();
        char c1 = '\266';
        int k = j / 2 - c1 / 2;
        int l = (int)(ayb.a * (float)(c1 + 1));
        byte byte0 = 12;
        b(k, byte0, 0, 74, c1, 5);
        b(k, byte0, 0, 74, c1, 5);
        if(l > 0)
        {
            b(k, byte0, 0, 79, l, 5);
        }
        String s = ayb.c;
        arp1.a(s, j / 2 - arp1.a(s) / 2, byte0 - 10, 0xffffff);
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
        ayo ayo1 = ayo.a;
        ayo1.b();
        ayo1.a(0.0D, k, -90D, 0.0D, 1.0D);
        ayo1.a(j, k, -90D, 1.0D, 1.0D);
        ayo1.a(j, 0.0D, -90D, 1.0D, 0.0D);
        ayo1.a(0.0D, 0.0D, -90D, 0.0D, 0.0D);
        ayo1.a();
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
        ayo ayo1 = ayo.a;
        ayo1.b();
        ayo1.a(0.0D, k, -90D, 0.0D, 1.0D);
        ayo1.a(j, k, -90D, 1.0D, 1.0D);
        ayo1.a(j, 0.0D, -90D, 1.0D, 0.0D);
        ayo1.a(0.0D, 0.0D, -90D, 0.0D, 0.0D);
        ayo1.a();
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
        float f2 = (float)(akv.bg.cj % 16) / 16F;
        float f3 = (float)(akv.bg.cj / 16) / 16F;
        float f4 = (float)(akv.bg.cj % 16 + 1) / 16F;
        float f5 = (float)(akv.bg.cj / 16 + 1) / 16F;
        ayo ayo1 = ayo.a;
        ayo1.b();
        ayo1.a(0.0D, k, -90D, f2, f5);
        ayo1.a(j, k, -90D, f4, f5);
        ayo1.a(j, 0.0D, -90D, f4, f3);
        ayo1.a(0.0D, 0.0D, -90D, f2, f3);
        ayo1.a();
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
        GL11.glEnable(3008);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    private void a(int j, int k, int l, float f1)
    {
        tm tm1 = d.g.bJ.a[j];
        if(tm1 == null)
        {
            return;
        }
        float f2 = (float)tm1.b - f1;
        if(f2 > 0.0F)
        {
            GL11.glPushMatrix();
            float f3 = 1.0F + f2 / 5F;
            GL11.glTranslatef(k + 8, l + 12, 0.0F);
            GL11.glScalef(1.0F / f3, (f3 + 1.0F) / 2.0F, 1.0F);
            GL11.glTranslatef(-(k + 8), -(l + 12), 0.0F);
        }
        b.b(d.p, d.o, tm1, k, l);
        if(f2 > 0.0F)
        {
            GL11.glPopMatrix();
        }
        b.c(d.p, d.o, tm1, k, l);
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

    public ara b()
    {
        return e;
    }

    public int c()
    {
        return f;
    }

}
