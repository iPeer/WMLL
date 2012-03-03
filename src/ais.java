// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst noconv 
// Source File Name:   SourceFile

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

public class ais extends ok
{

    private static ts d = new ts();
    private java.util.List e;
    private Random f;
    private Minecraft h;
    public String a;
    private int i;
    private String j;
    private int k;
    private boolean l;
    public float b;
    float c;

    public ais(Minecraft minecraft)
    {
        e = new ArrayList();
        f = new Random();
        a = null;
        i = 0;
        j = "";
        k = 0;
        l = false;
        c = 1.0F;
        h = minecraft;
    }

    public void a(float f1, boolean flag, int i1, int j1)
    {
        afy afy1 = new afy(h.A, h.d, h.e);
        int k1 = afy1.a();
        int l1 = afy1.b();
        ni ni1 = h.q;
        h.u.b();
        GL11.glEnable(3042);
        if(Minecraft.s())
        {
            a(h.h.a(f1), k1, l1);
        } else
        {
            GL11.glBlendFunc(770, 771);
        }
        aai aai1 = h.h.ap.f(3);
        if(h.A.E == 0 && aai1 != null && aai1.c == ox.ba.bO)
        {
            a(k1, l1);
        }
        if(!h.h.a(zy.k))
        {
            float f2 = h.h.aS + (h.h.aR - h.h.aS) * f1;
            if(f2 > 0.0F)
            {
                b(f2, k1, l1);
            }
        }
        if(!h.c.e())
        {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glBindTexture(3553, h.p.b("/gui/gui.png"));
            aaf aaf1 = h.h.ap;
            g = -90F;
            b(k1 / 2 - 91, l1 - 22, 0, 0, 182, 22);
            b((k1 / 2 - 91 - 1) + aaf1.c * 20, l1 - 22 - 1, 0, 22, 24, 22);
            GL11.glBindTexture(3553, h.p.b("/gui/icons.png"));
            GL11.glEnable(3042);
            GL11.glBlendFunc(775, 769);
            b(k1 / 2 - 7, l1 / 2 - 7, 0, 0, 16, 16);
            GL11.glDisable(3042);
            boolean flag2 = (h.h.Y / 3) % 2 == 1;
            if(h.h.Y < 10)
            {
                flag2 = false;
            }
            int j3 = h.h.ba();
            int k4 = h.h.by;
            f.setSeed(i * 0x4c627);
            boolean flag4 = false;
            nb nb1 = h.h.aF();
            int j6 = nb1.a();
            int i7 = nb1.b();
            c();
            if(h.c.d())
            {
                int l7 = k1 / 2 - 91;
                int k8 = k1 / 2 + 91;
                int i9 = h.h.aE();
                if(i9 > 0)
                {
                    char c1 = '\266';
                    int j10 = (int)(h.h.aW * (float)(c1 + 1));
                    int i11 = (l1 - 32) + 3;
                    b(l7, i11, 0, 64, c1, 5);
                    if(j10 > 0)
                    {
                        b(l7, i11, 0, 69, j10, 5);
                    }
                }
                int l9 = l1 - 39;
                int k10 = l9 - 10;
                int j11 = h.h.au();
                int k11 = -1;
                if(h.h.a(zy.l))
                {
                    k11 = i % 25;
                }
                for(int i12 = 0; i12 < 10; i12++)
                {
                    if(j11 > 0)
                    {
                        int l12 = l7 + i12 * 8;
                        if(i12 * 2 + 1 < j11)
                        {
                            b(l12, k10, 34, 9, 9, 9);
                        }
                        if(i12 * 2 + 1 == j11)
                        {
                            b(l12, k10, 25, 9, 9, 9);
                        }
                        if(i12 * 2 + 1 > j11)
                        {
                            b(l12, k10, 16, 9, 9, 9);
                        }
                    }
                    int i13 = 16;
                    if(h.h.a(zy.u))
                    {
                        i13 += 36;
                    }
                    int l13 = 0;
                    if(flag2)
                    {
                        l13 = 1;
                    }
                    int k14 = l7 + i12 * 8;
                    int l14 = l9;
                    if(j3 <= 4)
                    {
                        l14 += f.nextInt(2);
                    }
                    if(i12 == k11)
                    {
                        l14 -= 2;
                    }
                    byte byte5 = 0;
                    if(h.f.B().s())
                    {
                        byte5 = 5;
                    }
                    b(k14, l14, 16 + l13 * 9, 9 * byte5, 9, 9);
                    if(flag2)
                    {
                        if(i12 * 2 + 1 < k4)
                        {
                            b(k14, l14, i13 + 54, 9 * byte5, 9, 9);
                        }
                        if(i12 * 2 + 1 == k4)
                        {
                            b(k14, l14, i13 + 63, 9 * byte5, 9, 9);
                        }
                    }
                    if(i12 * 2 + 1 < j3)
                    {
                        b(k14, l14, i13 + 36, 9 * byte5, 9, 9);
                    }
                    if(i12 * 2 + 1 == j3)
                    {
                        b(k14, l14, i13 + 45, 9 * byte5, 9, 9);
                    }
                }

                for(int j12 = 0; j12 < 10; j12++)
                {
                    int j13 = l9;
                    int i14 = 16;
                    byte byte4 = 0;
                    if(h.h.a(zy.s))
                    {
                        i14 += 36;
                        byte4 = 13;
                    }
                    if(h.h.aF().d() <= 0.0F && i % (j6 * 3 + 1) == 0)
                    {
                        j13 += f.nextInt(3) - 1;
                    }
                    if(flag4)
                    {
                        byte4 = 1;
                    }
                    int i15 = k8 - j12 * 8 - 9;
                    b(i15, j13, 16 + byte4 * 9, 27, 9, 9);
                    if(flag4)
                    {
                        if(j12 * 2 + 1 < i7)
                        {
                            b(i15, j13, i14 + 54, 27, 9, 9);
                        }
                        if(j12 * 2 + 1 == i7)
                        {
                            b(i15, j13, i14 + 63, 27, 9, 9);
                        }
                    }
                    if(j12 * 2 + 1 < j6)
                    {
                        b(i15, j13, i14 + 36, 27, 9, 9);
                    }
                    if(j12 * 2 + 1 == j6)
                    {
                        b(i15, j13, i14 + 45, 27, 9, 9);
                    }
                }

                if(h.h.a(aci.g))
                {
                    int k12 = (int)Math.ceil(((double)(h.h.Y() - 2) * 10D) / 300D);
                    int k13 = (int)Math.ceil(((double)h.h.Y() * 10D) / 300D) - k12;
                    for(int j14 = 0; j14 < k12 + k13; j14++)
                    {
                        if(j14 < k12)
                        {
                            b(k8 - j14 * 8 - 9, k10, 16, 18, 9, 9);
                        } else
                        {
                            b(k8 - j14 * 8 - 9, k10, 25, 18, 9, 9);
                        }
                    }

                }
            }
            GL11.glDisable(3042);
            GL11.glEnable(32826);
            tb.c();
            for(int i8 = 0; i8 < 9; i8++)
            {
                int l8 = (k1 / 2 - 90) + i8 * 20 + 2;
                int j9 = l1 - 16 - 3;
                a(i8, l8, j9, f1);
            }

            tb.a();
            GL11.glDisable(32826);
        }
        if(h.h.aB() > 0)
        {
            GL11.glDisable(2929);
            GL11.glDisable(3008);
            int i2 = h.h.aB();
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
        if(h.c.f() && h.h.aU > 0)
        {
            boolean flag1 = false;
            int j2 = flag1 ? 0xffffff : 0x80ff20;
            String s = (new StringBuilder()).append("").append(h.h.aU).toString();
            int l4 = (k1 - ni1.a(s)) / 2;
            int i5 = l1 - 31 - 4;
            ni1.b(s, l4 + 1, i5, 0);
            ni1.b(s, l4 - 1, i5, 0);
            ni1.b(s, l4, i5 + 1, 0);
            ni1.b(s, l4, i5 - 1, 0);
            ni1.b(s, l4, i5, j2);
        }
        WMLL.i.updategui(h);
        if(h.A.F)
        {
            GL11.glPushMatrix();
            if(Minecraft.J > 0L)
            {
                GL11.glTranslatef(0.0F, 32F, 0.0F);
            }
            ni1.a((new StringBuilder()).append("Minecraft 1.2.3 (").append(h.M).append(")").toString(), 2, 2, 0xffffff);
            ni1.a(h.m(), 2, 12, 0xffffff);
            ni1.a(h.n(), 2, 22, 0xffffff);
            ni1.a(h.p(), 2, 32, 0xffffff);
            ni1.a(h.o(), 2, 42, 0xffffff);
            long l2 = Runtime.getRuntime().maxMemory();
            long l3 = Runtime.getRuntime().totalMemory();
            long l5 = Runtime.getRuntime().freeMemory();
            long l6 = l3 - l5;
            String s1 = (new StringBuilder()).append("Used memory: ").append((l6 * 100L) / l2).append("% (").append(l6 / 1024L / 1024L).append("MB) of ").append(l2 / 1024L / 1024L).append("MB").toString();
            b(ni1, s1, k1 - ni1.a(s1) - 2, 2, 0xe0e0e0);
            s1 = (new StringBuilder()).append("Allocated memory: ").append((l3 * 100L) / l2).append("% (").append(l3 / 1024L / 1024L).append("MB)").toString();
            b(ni1, s1, k1 - ni1.a(s1) - 2, 12, 0xe0e0e0);
            b(ni1, (new StringBuilder()).append("x: ").append(h.h.o).toString(), 2, 64, 0xe0e0e0);
            b(ni1, (new StringBuilder()).append("y: ").append(h.h.p).toString(), 2, 72, 0xe0e0e0);
            b(ni1, (new StringBuilder()).append("z: ").append(h.h.q).toString(), 2, 80, 0xe0e0e0);
            b(ni1, (new StringBuilder()).append("f: ").append(gh.c((double)((h.h.u * 4F) / 360F) + 0.5D) & 3).toString(), 2, 88, 0xe0e0e0);
            l2 = gh.c(h.h.o);
            int k2 = gh.c(h.h.p);
            l3 = gh.c(h.h.q);
            if(h.f != null && h.f.j((int)l2, k2, (int)l3))
            {
                acf acf1 = h.f.c((int)l2, (int)l3);
                b(ni1, (new StringBuilder()).append("lc: ").append(acf1.h() + 15).append(" b: ").append(acf1.a((int)l2 & 0xf, (int)l3 & 0xf, h.f.i()).y).append(" bl: ").append(acf1.a(wh.b, (int)l2 & 0xf, k2, (int)l3 & 0xf)).append(" sl: ").append(acf1.a(wh.a, (int)l2 & 0xf, k2, (int)l3 & 0xf)).append(" rl: ").append(acf1.c((int)l2 & 0xf, k2, (int)l3 & 0xf, 0)).toString(), 2, 96, 0xe0e0e0);
            }
            b(ni1, (new StringBuilder()).append("Seed: ").append(h.f.v()).toString(), 2, 112, 0xe0e0e0);
            GL11.glPopMatrix();
        }
        if(k > 0)
        {
            float f3 = (float)k - f1;
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
                if(l)
                {
                    i4 = Color.HSBtoRGB(f3 / 50F, 0.7F, 0.6F) & 0xffffff;
                }
                ni1.b(j, -ni1.a(j) / 2, -4, i4 + (i3 << 24));
                GL11.glDisable(3042);
                GL11.glPopMatrix();
            }
        }
        byte byte0 = 10;
        boolean flag3 = false;
        if(h.s instanceof yb)
        {
            byte0 = 20;
            flag3 = true;
        }
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3008);
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, l1 - 48, 0.0F);
        for(int j4 = 0; j4 < e.size() && j4 < byte0; j4++)
        {
            if(((nq)e.get(j4)).b >= 200 && !flag3)
            {
                continue;
            }
            double d1 = (double)((nq)e.get(j4)).b / 200D;
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
            int k5 = (int)(255D * d1);
            if(flag3)
            {
                k5 = 255;
            }
            if(k5 > 0)
            {
                byte byte1 = 2;
                int j7 = -j4 * 9;
                String s2 = ((nq)e.get(j4)).a;
                a(byte1, j7 - 1, byte1 + 320, j7 + 8, k5 / 2 << 24);
                GL11.glEnable(3042);
                ni1.a(s2, byte1, j7, 0xffffff + (k5 << 24));
            }
        }

        GL11.glPopMatrix();
        if((h.h instanceof ahp) && h.A.y.e)
        {
            adg adg1 = ((ahp)h.h).cl;
            java.util.List list = adg1.c;
            int j5 = adg1.d;
            int i6 = j5;
            int k6 = 1;
            for(; i6 > 20; i6 = ((j5 + k6) - 1) / k6)
            {
                k6++;
            }

            int k7 = 300 / k6;
            if(k7 > 150)
            {
                k7 = 150;
            }
            int j8 = (k1 - k6 * k7) / 2;
            byte byte2 = 10;
            a(j8 - 1, byte2 - 1, j8 + k7 * k6, byte2 + 9 * i6, 0x80000000);
            for(int k9 = 0; k9 < j5; k9++)
            {
                int i10 = j8 + (k9 % k6) * k7;
                int l10 = byte2 + (k9 / k6) * 9;
                a(i10, l10, (i10 + k7) - 1, l10 + 8, 0x20ffffff);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glEnable(3008);
                if(k9 >= list.size())
                {
                    continue;
                }
                ag ag1 = (ag)list.get(k9);
                ni1.a(ag1.a, i10, l10, 0xffffff);
                h.p.b(h.p.b("/gui/icons.png"));
                int l11 = 0;
                byte byte3 = 0;
                l11 = 0;
                byte3 = 0;
                if(ag1.b < 0)
                {
                    byte3 = 5;
                } else
                if(ag1.b < 150)
                {
                    byte3 = 0;
                } else
                if(ag1.b < 300)
                {
                    byte3 = 1;
                } else
                if(ag1.b < 600)
                {
                    byte3 = 2;
                } else
                if(ag1.b < 1000)
                {
                    byte3 = 3;
                } else
                {
                    byte3 = 4;
                }
                g += 100F;
                b((i10 + k7) - 12, l10, 0 + l11 * 10, 176 + byte3 * 8, 10, 8);
                g -= 100F;
            }

        }
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(2896);
        GL11.glEnable(3008);
    }

    private void c()
    {
        if(on.c == null)
        {
            return;
        }
        om om1 = on.c;
        on.c = null;
        ni ni1 = h.q;
        afy afy1 = new afy(h.A, h.d, h.e);
        int i1 = afy1.a();
        char c1 = '\266';
        int j1 = i1 / 2 - c1 / 2;
        int k1 = (int)(((float)om1.w() / (float)om1.d()) * (float)(c1 + 1));
        byte byte0 = 12;
        b(j1, byte0, 0, 74, c1, 5);
        b(j1, byte0, 0, 74, c1, 5);
        if(k1 > 0)
        {
            b(j1, byte0, 0, 79, k1, 5);
        }
        String s = "Boss health";
        ni1.a(s, i1 / 2 - ni1.a(s) / 2, byte0 - 10, 0xff00ff);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glBindTexture(3553, h.p.b("/gui/icons.png"));
    }

    private void a(int i1, int j1)
    {
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(3008);
        GL11.glBindTexture(3553, h.p.b("%blur%/misc/pumpkinblur.png"));
        adu adu1 = adu.a;
        adu1.b();
        adu1.a(0.0D, j1, -90D, 0.0D, 1.0D);
        adu1.a(i1, j1, -90D, 1.0D, 1.0D);
        adu1.a(i1, 0.0D, -90D, 1.0D, 0.0D);
        adu1.a(0.0D, 0.0D, -90D, 0.0D, 0.0D);
        adu1.a();
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
        GL11.glBindTexture(3553, h.p.b("%blur%/misc/vignette.png"));
        adu adu1 = adu.a;
        adu1.b();
        adu1.a(0.0D, j1, -90D, 0.0D, 1.0D);
        adu1.a(i1, j1, -90D, 1.0D, 1.0D);
        adu1.a(i1, 0.0D, -90D, 1.0D, 0.0D);
        adu1.a(0.0D, 0.0D, -90D, 0.0D, 0.0D);
        adu1.a();
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
        GL11.glBindTexture(3553, h.p.b("/terrain.png"));
        float f2 = (float)(ox.be.bN % 16) / 16F;
        float f3 = (float)(ox.be.bN / 16) / 16F;
        float f4 = (float)(ox.be.bN % 16 + 1) / 16F;
        float f5 = (float)(ox.be.bN / 16 + 1) / 16F;
        adu adu1 = adu.a;
        adu1.b();
        adu1.a(0.0D, j1, -90D, f2, f5);
        adu1.a(i1, j1, -90D, f4, f5);
        adu1.a(i1, 0.0D, -90D, f4, f3);
        adu1.a(0.0D, 0.0D, -90D, f2, f3);
        adu1.a();
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
        GL11.glEnable(3008);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    private void a(int i1, int j1, int k1, float f1)
    {
        aai aai1 = h.h.ap.a[i1];
        if(aai1 == null)
        {
            return;
        }
        float f2 = (float)aai1.b - f1;
        if(f2 > 0.0F)
        {
            GL11.glPushMatrix();
            float f3 = 1.0F + f2 / 5F;
            GL11.glTranslatef(j1 + 8, k1 + 12, 0.0F);
            GL11.glScalef(1.0F / f3, (f3 + 1.0F) / 2.0F, 1.0F);
            GL11.glTranslatef(-(j1 + 8), -(k1 + 12), 0.0F);
        }
        d.a(h.q, h.p, aai1, j1, k1);
        if(f2 > 0.0F)
        {
            GL11.glPopMatrix();
        }
        d.b(h.q, h.p, aai1, j1, k1);
    }

    public void a()
    {
        if(k > 0)
        {
            k--;
        }
        i++;
        for(int i1 = 0; i1 < e.size(); i1++)
        {
            ((nq)e.get(i1)).b++;
        }

    }

    public void b()
    {
        e.clear();
    }

    public void a(String s)
    {
        int i1;
        for(; h.q.a(s) > 320; s = s.substring(i1))
        {
            for(i1 = 1; i1 < s.length() && h.q.a(s.substring(0, i1 + 1)) <= 320; i1++) { }
            a(s.substring(0, i1));
        }

        e.add(0, new nq(s));
        for(; e.size() > 50; e.remove(e.size() - 1)) { }
    }

    public void b(String s)
    {
        j = (new StringBuilder()).append("Now playing: ").append(s).toString();
        k = 60;
        l = true;
    }

    public void c(String s)
    {
        adi adi1 = adi.a();
        String s1 = adi1.b(s);
        a(s1);
    }

}
