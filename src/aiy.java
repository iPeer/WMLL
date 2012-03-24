// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst noconv 
// Source File Name:   SourceFile

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

public class aiy extends oo
{

    private static tw d = new tw();
    private java.util.List<nt> e;
    private java.util.List f;
    private Random h;
    private Minecraft i;
    public String a;
    private int j;
    private String k;
    private int l;
    private boolean m;
    private int n;
    private boolean o;
    public float b;
    float c;

    public aiy(Minecraft minecraft)
    {
        e = new ArrayList<nt>();
        f = new ArrayList();
        h = new Random();
        a = null;
        j = 0;
        k = "";
        l = 0;
        m = false;
        n = 0;
        o = false;
        c = 1.0F;
        i = minecraft;
    }

    public void a(float f1, boolean flag, int i1, int j1)
    {
        agd agd1 = new agd(i.A, i.d, i.e);
        int k1 = agd1.a();
        int l1 = agd1.b();
        nl nl1 = i.q;
        i.u.b();
        GL11.glEnable(3042);
        if(Minecraft.s())
        {
            a(i.h.a(f1), k1, l1);
        } else
        {
            GL11.glBlendFunc(770, 771);
        }
        aan aan1 = i.h.ap.f(3);
        if(i.A.E == 0 && aan1 != null && aan1.c == pb.ba.bO)
        {
            b(k1, l1);
        }
        if(!i.h.a(aad.k))
        {
            float f2 = i.h.aS + (i.h.aR - i.h.aS) * f1;
            if(f2 > 0.0F)
            {
                b(f2, k1, l1);
            }
        }
        if(!i.c.e())
        {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glBindTexture(3553, i.p.b("/gui/gui.png"));
            aak aak1 = i.h.ap;
            g = -90F;
            b(k1 / 2 - 91, l1 - 22, 0, 0, 182, 22);
            b((k1 / 2 - 91 - 1) + aak1.c * 20, l1 - 22 - 1, 0, 22, 24, 22);
            GL11.glBindTexture(3553, i.p.b("/gui/icons.png"));
            GL11.glEnable(3042);
            GL11.glBlendFunc(775, 769);
            b(k1 / 2 - 7, l1 / 2 - 7, 0, 0, 16, 16);
            GL11.glDisable(3042);
            boolean flag2 = (i.h.Y / 3) % 2 == 1;
            if(i.h.Y < 10)
            {
                flag2 = false;
            }
            int j3 = i.h.bb();
            int k4 = i.h.by;
            h.setSeed(j * 0x4c627);
            boolean flag3 = false;
            ne ne1 = i.h.aF();
            int j6 = ne1.a();
            int i7 = ne1.b();
            f();
            if(i.c.d())
            {
                int j7 = k1 / 2 - 91;
                int i8 = k1 / 2 + 91;
                int l8 = i.h.aE();
                if(l8 > 0)
                {
                    char c1 = '\266';
                    int l9 = (int)(i.h.aW * (float)(c1 + 1));
                    int k10 = (l1 - 32) + 3;
                    b(j7, k10, 0, 64, c1, 5);
                    if(l9 > 0)
                    {
                        b(j7, k10, 0, 69, l9, 5);
                    }
                }
                int k9 = l1 - 39;
                int i10 = k9 - 10;
                int l10 = i.h.au();
                int i11 = -1;
                if(i.h.a(aad.l))
                {
                    i11 = j % 25;
                }
                for(int j11 = 0; j11 < 10; j11++)
                {
                    if(l10 > 0)
                    {
                        int i12 = j7 + j11 * 8;
                        if(j11 * 2 + 1 < l10)
                        {
                            b(i12, i10, 34, 9, 9, 9);
                        }
                        if(j11 * 2 + 1 == l10)
                        {
                            b(i12, i10, 25, 9, 9, 9);
                        }
                        if(j11 * 2 + 1 > l10)
                        {
                            b(i12, i10, 16, 9, 9, 9);
                        }
                    }
                    int j12 = 16;
                    if(i.h.a(aad.u))
                    {
                        j12 += 36;
                    }
                    int i13 = 0;
                    if(flag2)
                    {
                        i13 = 1;
                    }
                    int l13 = j7 + j11 * 8;
                    int i14 = k9;
                    if(j3 <= 4)
                    {
                        i14 += h.nextInt(2);
                    }
                    if(j11 == i11)
                    {
                        i14 -= 2;
                    }
                    byte byte3 = 0;
                    if(i.f.B().s())
                    {
                        byte3 = 5;
                    }
                    b(l13, i14, 16 + i13 * 9, 9 * byte3, 9, 9);
                    if(flag2)
                    {
                        if(j11 * 2 + 1 < k4)
                        {
                            b(l13, i14, j12 + 54, 9 * byte3, 9, 9);
                        }
                        if(j11 * 2 + 1 == k4)
                        {
                            b(l13, i14, j12 + 63, 9 * byte3, 9, 9);
                        }
                    }
                    if(j11 * 2 + 1 < j3)
                    {
                        b(l13, i14, j12 + 36, 9 * byte3, 9, 9);
                    }
                    if(j11 * 2 + 1 == j3)
                    {
                        b(l13, i14, j12 + 45, 9 * byte3, 9, 9);
                    }
                }

                for(int k11 = 0; k11 < 10; k11++)
                {
                    int k12 = k9;
                    int j13 = 16;
                    byte byte2 = 0;
                    if(i.h.a(aad.s))
                    {
                        j13 += 36;
                        byte2 = 13;
                    }
                    if(i.h.aF().d() <= 0.0F && j % (j6 * 3 + 1) == 0)
                    {
                        k12 += h.nextInt(3) - 1;
                    }
                    if(flag3)
                    {
                        byte2 = 1;
                    }
                    int j14 = i8 - k11 * 8 - 9;
                    b(j14, k12, 16 + byte2 * 9, 27, 9, 9);
                    if(flag3)
                    {
                        if(k11 * 2 + 1 < i7)
                        {
                            b(j14, k12, j13 + 54, 27, 9, 9);
                        }
                        if(k11 * 2 + 1 == i7)
                        {
                            b(j14, k12, j13 + 63, 27, 9, 9);
                        }
                    }
                    if(k11 * 2 + 1 < j6)
                    {
                        b(j14, k12, j13 + 36, 27, 9, 9);
                    }
                    if(k11 * 2 + 1 == j6)
                    {
                        b(j14, k12, j13 + 45, 27, 9, 9);
                    }
                }

                if(i.h.a(acn.g))
                {
                    int l11 = (int)Math.ceil(((double)(i.h.Y() - 2) * 10D) / 300D);
                    int l12 = (int)Math.ceil(((double)i.h.Y() * 10D) / 300D) - l11;
                    for(int k13 = 0; k13 < l11 + l12; k13++)
                    {
                        if(k13 < l11)
                        {
                            b(i8 - k13 * 8 - 9, i10, 16, 18, 9, 9);
                        } else
                        {
                            b(i8 - k13 * 8 - 9, i10, 25, 18, 9, 9);
                        }
                    }

                }
            }
            GL11.glDisable(3042);
            GL11.glEnable(32826);
            tf.c();
            for(int k7 = 0; k7 < 9; k7++)
            {
                int j8 = (k1 / 2 - 90) + k7 * 20 + 2;
                int i9 = l1 - 16 - 3;
                a(k7, j8, i9, f1);
            }

            tf.a();
            GL11.glDisable(32826);
        }
        if(i.h.aB() > 0)
        {
            GL11.glDisable(2929);
            GL11.glDisable(3008);
            int i2 = i.h.aB();
            float f4 = (float)i2 / 100F;
            if(f4 > 1.0F)
            {
                f4 = 1.0F - (float)(i2 - 100) / 10F;
            }
            int k3 = (int)(220F * f4) << 24 | 0x101020;
            a(0, 0, k1, l1, k3);
            GL11.glEnable(3008);
            GL11.glEnable(2929);
        }
        if(i.c.f() && i.h.aU > 0)
        {
            boolean flag1 = false;
            int j2 = flag1 ? 0xffffff : 0x80ff20;
            String s = (new StringBuilder()).append("").append(i.h.aU).toString();
            int l4 = (k1 - nl1.a(s)) / 2;
            int j5 = l1 - 31 - 4;
            nl1.b(s, l4 + 1, j5, 0);
            nl1.b(s, l4 - 1, j5, 0);
            nl1.b(s, l4, j5 + 1, 0);
            nl1.b(s, l4, j5 - 1, 0);
            nl1.b(s, l4, j5, j2);
        }
        WMLL.i.updategui(i);
        if(i.A.F)
        {
            GL11.glPushMatrix();
            if(Minecraft.J > 0L)
            {
                GL11.glTranslatef(0.0F, 32F, 0.0F);
            }
            nl1.a((new StringBuilder()).append("Minecraft 1.2.4 (").append(i.M).append(")").toString(), 2, 2, 0xffffff);
            nl1.a(i.m(), 2, 12, 0xffffff);
            nl1.a(i.n(), 2, 22, 0xffffff);
            nl1.a(i.p(), 2, 32, 0xffffff);
            nl1.a(i.o(), 2, 42, 0xffffff);
            long l2 = Runtime.getRuntime().maxMemory();
            long l3 = Runtime.getRuntime().totalMemory();
            long l5 = Runtime.getRuntime().freeMemory();
            long l6 = l3 - l5;
            String s1 = (new StringBuilder()).append("Used memory: ").append((l6 * 100L) / l2).append("% (").append(l6 / 1024L / 1024L).append("MB) of ").append(l2 / 1024L / 1024L).append("MB").toString();
            b(nl1, s1, k1 - nl1.a(s1) - 2, 2, 0xe0e0e0);
            s1 = (new StringBuilder()).append("Allocated memory: ").append((l3 * 100L) / l2).append("% (").append(l3 / 1024L / 1024L).append("MB)").toString();
            b(nl1, s1, k1 - nl1.a(s1) - 2, 12, 0xe0e0e0);
            b(nl1, (new StringBuilder()).append("x: ").append(i.h.o).toString(), 2, 64, 0xe0e0e0);
            b(nl1, (new StringBuilder()).append("y: ").append(i.h.p).toString(), 2, 72, 0xe0e0e0);
            b(nl1, (new StringBuilder()).append("z: ").append(i.h.q).toString(), 2, 80, 0xe0e0e0);
            b(nl1, (new StringBuilder()).append("f: ").append(gk.c((double)((i.h.u * 4F) / 360F) + 0.5D) & 3).toString(), 2, 88, 0xe0e0e0);
            l2 = gk.c(i.h.o);
            int k2 = gk.c(i.h.p);
            l3 = gk.c(i.h.q);
            if(i.f != null && i.f.j((int)l2, k2, (int)l3))
            {
                ack ack1 = i.f.c((int)l2, (int)l3);
                b(nl1, (new StringBuilder()).append("lc: ").append(ack1.h() + 15).append(" b: ").append(ack1.a((int)l2 & 0xf,(int) l3 & 0xf, i.f.i()).y).append(" bl: ").append(ack1.a(wl.b, (int)l2 & 0xf, k2, (int)l3 & 0xf)).append(" sl: ").append(ack1.a(wl.a, (int)l2 & 0xf, k2, (int)l3 & 0xf)).append(" rl: ").append(ack1.c((int)l2 & 0xf, k2, (int)l3 & 0xf, 0)).toString(), 2, 96, 0xe0e0e0);
            }
            if(!i.f.F)
            {
                b(nl1, (new StringBuilder()).append("Seed: ").append(i.f.v()).toString(), 2, 112, 0xe0e0e0);
            }
            GL11.glPopMatrix();
        }
        if(l > 0)
        {
            float f3 = (float)l - f1;
            int i3 = (int)((f3 * 256F) / 20F);
            if(i3 > 255)
            {
                i3 = 255;
            }
            if(i3 > 0)
            {
                GL11.glPushMatrix();
                GL11.glTranslatef(k1 / 2, l1 - 48, 0.0F);
                GL11.glEnable(3042);
                GL11.glBlendFunc(770, 771);
                int i4 = 0xffffff;
                if(m)
                {
                    i4 = Color.HSBtoRGB(f3 / 50F, 0.7F, 0.6F) & 0xffffff;
                }
                nl1.b(k, -nl1.a(k) / 2, -4, i4 + (i3 << 24));
                GL11.glDisable(3042);
                GL11.glPopMatrix();
            }
        }
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3008);
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, l1 - 48, 0.0F);
        a(nl1);
        GL11.glPopMatrix();
        if((i.h instanceof ahv) && i.A.y.e)
        {
            adl adl1 = ((ahv)i.h).cl;
            java.util.List list = adl1.c;
            int j4 = adl1.d;
            int i5 = j4;
            int k5 = 1;
            for(; i5 > 20; i5 = ((j4 + k5) - 1) / k5)
            {
                k5++;
            }

            int i6 = 300 / k5;
            if(i6 > 150)
            {
                i6 = 150;
            }
            int k6 = (k1 - k5 * i6) / 2;
            byte byte0 = 10;
            a(k6 - 1, byte0 - 1, k6 + i6 * k5, byte0 + 9 * i5, 0x80000000);
            for(int l7 = 0; l7 < j4; l7++)
            {
                int k8 = k6 + (l7 % k5) * i6;
                int j9 = byte0 + (l7 / k5) * 9;
                a(k8, j9, (k8 + i6) - 1, j9 + 8, 0x20ffffff);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glEnable(3008);
                if(l7 >= list.size())
                {
                    continue;
                }
                ah ah1 = (ah)list.get(l7);
                nl1.a(ah1.a, k8, j9, 0xffffff);
                i.p.b(i.p.b("/gui/icons.png"));
                int j10 = 0;
                byte byte1 = 0;
                j10 = 0;
                byte1 = 0;
                if(ah1.b < 0)
                {
                    byte1 = 5;
                } else
                if(ah1.b < 150)
                {
                    byte1 = 0;
                } else
                if(ah1.b < 300)
                {
                    byte1 = 1;
                } else
                if(ah1.b < 600)
                {
                    byte1 = 2;
                } else
                if(ah1.b < 1000)
                {
                    byte1 = 3;
                } else
                {
                    byte1 = 4;
                }
                g += 100F;
                b((k8 + i6) - 12, j9, 0 + j10 * 10, 176 + byte1 * 8, 10, 8);
                g -= 100F;
            }

        }
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(2896);
        GL11.glEnable(3008);
    }

    private void a(nl nl1)
    {
        byte byte0 = 10;
        boolean flag = false;
        int i1 = 0;
        int j1 = e.size();
        if(j1 <= 0)
        {
            return;
        }
        if(e())
        {
            byte0 = 20;
            flag = true;
        }
        for(int k1 = 0; k1 + n < e.size() && k1 < byte0; k1++)
        {
            if(((nt)e.get(k1)).b >= 200 && !flag)
            {
                continue;
            }
            nt nt1 = (nt)e.get(k1 + n);
            double d1 = (double)nt1.b / 200D;
            d1 = 1.0D - d1;
            d1 *= 10D;
            if(d1 < 0.0D)
            {
                d1 = 0.0D;
            }
            if(d1 > 1.0D)
            {
                d1 = 1.0D;
            }
            d1 *= d1;
            int l2 = (int)(255D * d1);
            if(flag)
            {
                l2 = 255;
            }
            i1++;
            if(l2 > 2)
            {
                byte byte1 = 3;
                int j3 = -k1 * 9;
                String s = nt1.a;
                a(byte1, j3 - 1, byte1 + 320 + 4, j3 + 8, l2 / 2 << 24);
                GL11.glEnable(3042);
                nl1.a(s, byte1, j3, 0xffffff + (l2 << 24));
            }
        }

        if(flag)
        {
            GL11.glTranslatef(0.0F, nl1.b, 0.0F);
            int l1 = j1 * nl1.b + j1;
            int i2 = i1 * nl1.b + i1;
            int j2 = (n * i2) / j1;
            int k2 = (i2 * i2) / l1;
            if(l1 != i2)
            {
                char c1 = j2 <= 0 ? '`' : '\252';
                int i3 = o ? 0xcc3333 : 0x3333aa;
                a(0, -j2, 2, -j2 - k2, i3 + (c1 << 24));
                a(2, -j2, 1, -j2 - k2, 0xcccccc + (c1 << 24));
            }
        }
    }

    private void f()
    {
        if(or.c == null)
        {
            return;
        }
        oq oq1 = or.c;
        or.c = null;
        nl nl1 = i.q;
        agd agd1 = new agd(i.A, i.d, i.e);
        int i1 = agd1.a();
        char c1 = '\266';
        int j1 = i1 / 2 - c1 / 2;
        int k1 = (int)(((float)oq1.x() / (float)oq1.d()) * (float)(c1 + 1));
        byte byte0 = 12;
        b(j1, byte0, 0, 74, c1, 5);
        b(j1, byte0, 0, 74, c1, 5);
        if(k1 > 0)
        {
            b(j1, byte0, 0, 79, k1, 5);
        }
        String s = "Boss health";
        nl1.a(s, i1 / 2 - nl1.a(s) / 2, byte0 - 10, 0xff00ff);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glBindTexture(3553, i.p.b("/gui/icons.png"));
    }

    private void b(int i1, int j1)
    {
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(3008);
        GL11.glBindTexture(3553, i.p.b("%blur%/misc/pumpkinblur.png"));
        adz adz1 = adz.a;
        adz1.b();
        adz1.a(0.0D, j1, -90D, 0.0D, 1.0D);
        adz1.a(i1, j1, -90D, 1.0D, 1.0D);
        adz1.a(i1, 0.0D, -90D, 1.0D, 0.0D);
        adz1.a(0.0D, 0.0D, -90D, 0.0D, 0.0D);
        adz1.a();
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
        GL11.glEnable(3008);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    private void a(float f1, int i1, int j1)
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
        c += (double)(f1 - c) * 0.01D;
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(0, 769);
        GL11.glColor4f(c, c, c, 1.0F);
        GL11.glBindTexture(3553, i.p.b("%blur%/misc/vignette.png"));
        adz adz1 = adz.a;
        adz1.b();
        adz1.a(0.0D, j1, -90D, 0.0D, 1.0D);
        adz1.a(i1, j1, -90D, 1.0D, 1.0D);
        adz1.a(i1, 0.0D, -90D, 1.0D, 0.0D);
        adz1.a(0.0D, 0.0D, -90D, 0.0D, 0.0D);
        adz1.a();
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glBlendFunc(770, 771);
    }

    private void b(float f1, int i1, int j1)
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
        GL11.glBindTexture(3553, i.p.b("/terrain.png"));
        float f2 = (float)(pb.be.bN % 16) / 16F;
        float f3 = (float)(pb.be.bN / 16) / 16F;
        float f4 = (float)(pb.be.bN % 16 + 1) / 16F;
        float f5 = (float)(pb.be.bN / 16 + 1) / 16F;
        adz adz1 = adz.a;
        adz1.b();
        adz1.a(0.0D, j1, -90D, f2, f5);
        adz1.a(i1, j1, -90D, f4, f5);
        adz1.a(i1, 0.0D, -90D, f4, f3);
        adz1.a(0.0D, 0.0D, -90D, f2, f3);
        adz1.a();
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
        GL11.glEnable(3008);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    private void a(int i1, int j1, int k1, float f1)
    {
        aan aan1 = i.h.ap.a[i1];
        if(aan1 == null)
        {
            return;
        }
        float f2 = (float)aan1.b - f1;
        if(f2 > 0.0F)
        {
            GL11.glPushMatrix();
            float f3 = 1.0F + f2 / 5F;
            GL11.glTranslatef(j1 + 8, k1 + 12, 0.0F);
            GL11.glScalef(1.0F / f3, (f3 + 1.0F) / 2.0F, 1.0F);
            GL11.glTranslatef(-(j1 + 8), -(k1 + 12), 0.0F);
        }
        d.a(i.q, i.p, aan1, j1, k1);
        if(f2 > 0.0F)
        {
            GL11.glPopMatrix();
        }
        d.b(i.q, i.p, aan1, j1, k1);
    }

    public void a()
    {
        if(l > 0)
        {
            l--;
        }
        j++;
        for(int i1 = 0; i1 < e.size(); i1++)
        {
            ((nt)e.get(i1)).b++;
        }

    }

    public void b()
    {
        e.clear();
        f.clear();
    }

    public void a(String s)
    {
        boolean flag = e();
        boolean flag1 = true;
        String s1;
        for(Iterator iterator = i.q.c(s, 320).iterator(); iterator.hasNext(); e.add(0, new nt(s1)))
        {
            s1 = (String)iterator.next();
            if(flag && n > 0)
            {
                o = true;
                a(1);
            }
            if(!flag1)
            {
                s1 = (new StringBuilder()).append(" ").append(s1).toString();
            }
            flag1 = false;
        }

        for(; e.size() > 100; e.remove(e.size() - 1)) { }
    }

    public java.util.List c()
    {
        return f;
    }

    public void d()
    {
        n = 0;
        o = false;
    }

    public void a(int i1)
    {
        n += i1;
        int j1 = e.size();
        if(n > j1 - 20)
        {
            n = j1 - 20;
        }
        if(n <= 0)
        {
            n = 0;
            o = false;
        }
    }

    public dx a(int i1, int j1)
    {
        if(!e())
        {
            return null;
        }
        agd agd1 = new agd(i.A, i.d, i.e);
        j1 = j1 / agd1.c - 40;
        i1 = i1 / agd1.c - 3;
        if(i1 < 0 || j1 < 0)
        {
            return null;
        }
        int k1 = Math.min(20, e.size());
        if(i1 <= 320 && j1 < i.q.b * k1 + k1)
        {
            int l1 = j1 / (i.q.b + 1) + n;
            return new dx(i.q, (nt)e.get(l1), i1, (j1 - (l1 - n) * i.q.b) + l1);
        } else
        {
            return null;
        }
    }

    public void b(String s)
    {
        k = (new StringBuilder()).append("Now playing: ").append(s).toString();
        l = 60;
        m = true;
    }

    public boolean e()
    {
        return i.s instanceof yf;
    }

    public void c(String s)
    {
        adn adn1 = adn.a();
        String s1 = adn1.b(s);
        a(s1);
    }

}
