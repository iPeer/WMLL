// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SourceFile

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

public class aip extends oj
{

    public aip(Minecraft minecraft)
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
        afw afw1 = new afw(h.A, h.d, h.e);
        int k1 = afw1.a();
        int l1 = afw1.b();
        nh nh1 = h.q;
        h.u.b();
        GL11.glEnable(3042);
        if(Minecraft.s())
            a(h.h.a(f1), k1, l1);
        else
            GL11.glBlendFunc(770, 771);
        aag aag1 = h.h.ap.f(3);
        if(h.A.E == 0 && aag1 != null && aag1.c == ow.ba.bO)
            a(k1, l1);
        if(!h.h.a(zw.k))
        {
            float f2 = h.h.aS + (h.h.aR - h.h.aS) * f1;
            if(f2 > 0.0F)
                b(f2, k1, l1);
        }
        if(!h.c.e())
        {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glBindTexture(3553, h.p.b("/gui/gui.png"));
            aad aad1 = h.h.ap;
            g = -90F;
            b(k1 / 2 - 91, l1 - 22, 0, 0, 182, 22);
            b((k1 / 2 - 91 - 1) + aad1.c * 20, l1 - 22 - 1, 0, 22, 24, 22);
            GL11.glBindTexture(3553, h.p.b("/gui/icons.png"));
            GL11.glEnable(3042);
            GL11.glBlendFunc(775, 769);
            b(k1 / 2 - 7, l1 / 2 - 7, 0, 0, 16, 16);
            GL11.glDisable(3042);
            boolean flag2 = (h.h.Y / 3) % 2 == 1;
            if(h.h.Y < 10)
                flag2 = false;
            int i3 = h.h.ba();
            int j4 = h.h.by;
            f.setSeed(i * 0x4c627);
            boolean flag4 = false;
            na na1 = h.h.aF();
            int i6 = na1.a();
            int k6 = na1.b();
            c();
            if(h.c.d())
            {
                int k7 = k1 / 2 - 91;
                int j8 = k1 / 2 + 91;
                int l8 = h.h.aE();
                if(l8 > 0)
                {
                    char c1 = '\266';
                    int i10 = (int)(h.h.aW * (float)(c1 + 1));
                    int l10 = (l1 - 32) + 3;
                    b(k7, l10, 0, 64, c1, 5);
                    if(i10 > 0)
                        b(k7, l10, 0, 69, i10, 5);
                }
                int k9 = l1 - 39;
                int j10 = k9 - 10;
                int i11 = h.h.au();
                int j11 = -1;
                if(h.h.a(zw.l))
                    j11 = i % 25;
                for(int l11 = 0; l11 < 10; l11++)
                {
                    if(i11 > 0)
                    {
                        int k12 = k7 + l11 * 8;
                        if(l11 * 2 + 1 < i11)
                            b(k12, j10, 34, 9, 9, 9);
                        if(l11 * 2 + 1 == i11)
                            b(k12, j10, 25, 9, 9, 9);
                        if(l11 * 2 + 1 > i11)
                            b(k12, j10, 16, 9, 9, 9);
                    }
                    int l12 = 16;
                    if(h.h.a(zw.u))
                        l12 += 36;
                    int k13 = 0;
                    if(flag2)
                        k13 = 1;
                    int j14 = k7 + l11 * 8;
                    int k14 = k9;
                    if(i3 <= 4)
                        k14 += f.nextInt(2);
                    if(l11 == j11)
                        k14 -= 2;
                    byte byte5 = 0;
                    if(h.f.B().s())
                        byte5 = 5;
                    b(j14, k14, 16 + k13 * 9, 9 * byte5, 9, 9);
                    if(flag2)
                    {
                        if(l11 * 2 + 1 < j4)
                            b(j14, k14, l12 + 54, 9 * byte5, 9, 9);
                        if(l11 * 2 + 1 == j4)
                            b(j14, k14, l12 + 63, 9 * byte5, 9, 9);
                    }
                    if(l11 * 2 + 1 < i3)
                        b(j14, k14, l12 + 36, 9 * byte5, 9, 9);
                    if(l11 * 2 + 1 == i3)
                        b(j14, k14, l12 + 45, 9 * byte5, 9, 9);
                }

                for(int i12 = 0; i12 < 10; i12++)
                {
                    int i13 = k9;
                    int l13 = 16;
                    byte byte4 = 0;
                    if(h.h.a(zw.s))
                    {
                        l13 += 36;
                        byte4 = 13;
                    }
                    if(h.h.aF().d() <= 0.0F && i % (i6 * 3 + 1) == 0)
                        i13 += f.nextInt(3) - 1;
                    if(flag4)
                        byte4 = 1;
                    int l14 = j8 - i12 * 8 - 9;
                    b(l14, i13, 16 + byte4 * 9, 27, 9, 9);
                    if(flag4)
                    {
                        if(i12 * 2 + 1 < k6)
                            b(l14, i13, l13 + 54, 27, 9, 9);
                        if(i12 * 2 + 1 == k6)
                            b(l14, i13, l13 + 63, 27, 9, 9);
                    }
                    if(i12 * 2 + 1 < i6)
                        b(l14, i13, l13 + 36, 27, 9, 9);
                    if(i12 * 2 + 1 == i6)
                        b(l14, i13, l13 + 45, 27, 9, 9);
                }

                if(h.h.a(acg.g))
                {
                    int j12 = (int)Math.ceil(((double)(h.h.Y() - 2) * 10D) / 300D);
                    int j13 = (int)Math.ceil(((double)h.h.Y() * 10D) / 300D) - j12;
                    for(int i14 = 0; i14 < j12 + j13; i14++)
                        if(i14 < j12)
                            b(j8 - i14 * 8 - 9, j10, 16, 18, 9, 9);
                        else
                            b(j8 - i14 * 8 - 9, j10, 25, 18, 9, 9);

                }
            }
            GL11.glDisable(3042);
            GL11.glEnable(32826);
            ta.c();
            for(int l7 = 0; l7 < 9; l7++)
            {
                int k8 = (k1 / 2 - 90) + l7 * 20 + 2;
                int i9 = l1 - 16 - 3;
                a(l7, k8, i9, f1);
            }

            ta.a();
            GL11.glDisable(32826);
        }
        if(h.h.aB() > 0)
        {
            GL11.glDisable(2929);
            GL11.glDisable(3008);
            int i2 = h.h.aB();
            float f4 = (float)i2 / 100F;
            if(f4 > 1.0F)
                f4 = 1.0F - (float)(i2 - 100) / 10F;
            int j3 = (int)(220F * f4) << 24 | 0x101020;
            a(0, 0, k1, l1, j3);
            GL11.glEnable(3008);
            GL11.glEnable(2929);
        }
        if(h.c.f() && h.h.aU > 0)
        {
            boolean flag1 = false;
            int j2 = flag1 ? 0xffffff : 0x80ff20;
            String s = (new StringBuilder()).append("").append(h.h.aU).toString();
            int k4 = (k1 - nh1.a(s)) / 2;
            int l4 = l1 - 31 - 4;
            nh1.b(s, k4 + 1, l4, 0);
            nh1.b(s, k4 - 1, l4, 0);
            nh1.b(s, k4, l4 + 1, 0);
            nh1.b(s, k4, l4 - 1, 0);
            nh1.b(s, k4, l4, j2);
        }
        WMLL.i.updategui(h);
        if(h.A.F)
        {
            GL11.glPushMatrix();
            if(Minecraft.J > 0L)
                GL11.glTranslatef(0.0F, 32F, 0.0F);
            nh1.a((new StringBuilder()).append("Minecraft 12w08a (").append(h.M).append(")").toString(), 2, 2, 0xffffff);
            nh1.a(h.m(), 2, 12, 0xffffff);
            nh1.a(h.n(), 2, 22, 0xffffff);
            nh1.a(h.p(), 2, 32, 0xffffff);
            nh1.a(h.o(), 2, 42, 0xffffff);
            long l2 = Runtime.getRuntime().maxMemory();
            long l3 = Runtime.getRuntime().totalMemory();
            long l5 = Runtime.getRuntime().freeMemory();
            long l6 = l3 - l5;
            String s1 = (new StringBuilder()).append("Used memory: ").append((l6 * 100L) / l2).append("% (").append(l6 / 1024L / 1024L).append("MB) of ").append(l2 / 1024L / 1024L).append("MB").toString();
            b(nh1, s1, k1 - nh1.a(s1) - 2, 2, 0xe0e0e0);
            s1 = (new StringBuilder()).append("Allocated memory: ").append((l3 * 100L) / l2).append("% (").append(l3 / 1024L / 1024L).append("MB)").toString();
            b(nh1, s1, k1 - nh1.a(s1) - 2, 12, 0xe0e0e0);
            b(nh1, (new StringBuilder()).append("x: ").append(h.h.o).toString(), 2, 64, 0xe0e0e0);
            b(nh1, (new StringBuilder()).append("y: ").append(h.h.p).toString(), 2, 72, 0xe0e0e0);
            b(nh1, (new StringBuilder()).append("z: ").append(h.h.q).toString(), 2, 80, 0xe0e0e0);
            b(nh1, (new StringBuilder()).append("f: ").append(gh.c((double)((h.h.u * 4F) / 360F) + 0.5D) & 3).toString(), 2, 88, 0xe0e0e0);
            b(nh1, (new StringBuilder()).append("Seed: ").append(h.f.v()).toString(), 2, 104, 0xe0e0e0);
            GL11.glPopMatrix();
        }
        if(k > 0)
        {
            float f3 = (float)k - f1;
            int k2 = (int)((f3 * 256F) / 20F);
            if(k2 > 255)
                k2 = 255;
            if(k2 > 0)
            {
                GL11.glPushMatrix();
                GL11.glTranslatef(k1 / 2, l1 - 48, 0.0F);
                GL11.glEnable(3042);
                GL11.glBlendFunc(770, 771);
                int k3 = 0xffffff;
                if(l)
                    k3 = Color.HSBtoRGB(f3 / 50F, 0.7F, 0.6F) & 0xffffff;
                nh1.b(j, -nh1.a(j) / 2, -4, k3 + (k2 << 24));
                GL11.glDisable(3042);
                GL11.glPopMatrix();
            }
        }
        byte byte0 = 10;
        boolean flag3 = false;
        if(h.s instanceof xz)
        {
            byte0 = 20;
            flag3 = true;
        }
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3008);
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, l1 - 48, 0.0F);
        for(int i4 = 0; i4 < e.size() && i4 < byte0; i4++)
        {
            if(((np)e.get(i4)).b >= 200 && !flag3)
                continue;
            double d1 = (double)((np)e.get(i4)).b / 200D;
            d1 = 1.0D - d1;
            d1 *= 10D;
            if(d1 < 0.0D)
                d1 = 0.0D;
            if(d1 > 1.0D)
                d1 = 1.0D;
            d1 *= d1;
            int j5 = (int)(255D * d1);
            if(flag3)
                j5 = 255;
            if(j5 > 0)
            {
                byte byte1 = 2;
                int i7 = -i4 * 9;
                String s2 = ((np)e.get(i4)).a;
                a(byte1, i7 - 1, byte1 + 320, i7 + 8, j5 / 2 << 24);
                GL11.glEnable(3042);
                nh1.a(s2, byte1, i7, 0xffffff + (j5 << 24));
            }
        }

        GL11.glPopMatrix();
        if((h.h instanceof ahm) && h.A.y.e)
        {
            ade ade1 = ((ahm)h.h).cl;
            java.util.List list = ade1.c;
            int i5 = ade1.d;
            int k5 = i5;
            int j6 = 1;
            for(; k5 > 20; k5 = ((i5 + j6) - 1) / j6)
                j6++;

            int j7 = 300 / j6;
            if(j7 > 150)
                j7 = 150;
            int i8 = (k1 - j6 * j7) / 2;
            byte byte2 = 10;
            a(i8 - 1, byte2 - 1, i8 + j7 * j6, byte2 + 9 * k5, 0x80000000);
            for(int j9 = 0; j9 < i5; j9++)
            {
                int l9 = i8 + (j9 % j6) * j7;
                int k10 = byte2 + (j9 / j6) * 9;
                a(l9, k10, (l9 + j7) - 1, k10 + 8, 0x20ffffff);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glEnable(3008);
                if(j9 >= list.size())
                    continue;
                ag ag1 = (ag)list.get(j9);
                nh1.a(ag1.a, l9, k10, 0xffffff);
                h.p.b(h.p.b("/gui/icons.png"));
                int k11 = 0;
                byte byte3 = 0;
                k11 = 0;
                byte3 = 0;
                if(ag1.b < 0)
                    byte3 = 5;
                else
                if(ag1.b < 150)
                    byte3 = 0;
                else
                if(ag1.b < 300)
                    byte3 = 1;
                else
                if(ag1.b < 600)
                    byte3 = 2;
                else
                if(ag1.b < 1000)
                    byte3 = 3;
                else
                    byte3 = 4;
                g += 100F;
                b((l9 + j7) - 12, k10, 0 + k11 * 10, 176 + byte3 * 8, 10, 8);
                g -= 100F;
            }

        }
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(2896);
        GL11.glEnable(3008);
    }

    private void c()
    {
        if(om.c == null)
            return;
        ol ol1 = om.c;
        om.c = null;
        nh nh1 = h.q;
        afw afw1 = new afw(h.A, h.d, h.e);
        int i1 = afw1.a();
        char c1 = '\266';
        int j1 = i1 / 2 - c1 / 2;
        int k1 = (int)(((float)ol1.w() / (float)ol1.d()) * (float)(c1 + 1));
        byte byte0 = 12;
        b(j1, byte0, 0, 74, c1, 5);
        b(j1, byte0, 0, 74, c1, 5);
        if(k1 > 0)
            b(j1, byte0, 0, 79, k1, 5);
        String s = "Boss health";
        nh1.a(s, i1 / 2 - nh1.a(s) / 2, byte0 - 10, 0xff00ff);
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
        ads ads1 = ads.a;
        ads1.b();
        ads1.a(0.0D, j1, -90D, 0.0D, 1.0D);
        ads1.a(i1, j1, -90D, 1.0D, 1.0D);
        ads1.a(i1, 0.0D, -90D, 1.0D, 0.0D);
        ads1.a(0.0D, 0.0D, -90D, 0.0D, 0.0D);
        ads1.a();
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
        GL11.glEnable(3008);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    private void a(float f1, int i1, int j1)
    {
        f1 = 1.0F - f1;
        if(f1 < 0.0F)
            f1 = 0.0F;
        if(f1 > 1.0F)
            f1 = 1.0F;
        c += (double)(f1 - c) * 0.01D;
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(0, 769);
        GL11.glColor4f(c, c, c, 1.0F);
        GL11.glBindTexture(3553, h.p.b("%blur%/misc/vignette.png"));
        ads ads1 = ads.a;
        ads1.b();
        ads1.a(0.0D, j1, -90D, 0.0D, 1.0D);
        ads1.a(i1, j1, -90D, 1.0D, 1.0D);
        ads1.a(i1, 0.0D, -90D, 1.0D, 0.0D);
        ads1.a(0.0D, 0.0D, -90D, 0.0D, 0.0D);
        ads1.a();
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
        float f2 = (float)(ow.be.bN % 16) / 16F;
        float f3 = (float)(ow.be.bN / 16) / 16F;
        float f4 = (float)(ow.be.bN % 16 + 1) / 16F;
        float f5 = (float)(ow.be.bN / 16 + 1) / 16F;
        ads ads1 = ads.a;
        ads1.b();
        ads1.a(0.0D, j1, -90D, f2, f5);
        ads1.a(i1, j1, -90D, f4, f5);
        ads1.a(i1, 0.0D, -90D, f4, f3);
        ads1.a(0.0D, 0.0D, -90D, f2, f3);
        ads1.a();
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
        GL11.glEnable(3008);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    private void a(int i1, int j1, int k1, float f1)
    {
        aag aag1 = h.h.ap.a[i1];
        if(aag1 == null)
            return;
        float f2 = (float)aag1.b - f1;
        if(f2 > 0.0F)
        {
            GL11.glPushMatrix();
            float f3 = 1.0F + f2 / 5F;
            GL11.glTranslatef(j1 + 8, k1 + 12, 0.0F);
            GL11.glScalef(1.0F / f3, (f3 + 1.0F) / 2.0F, 1.0F);
            GL11.glTranslatef(-(j1 + 8), -(k1 + 12), 0.0F);
        }
        d.a(h.q, h.p, aag1, j1, k1);
        if(f2 > 0.0F)
            GL11.glPopMatrix();
        d.b(h.q, h.p, aag1, j1, k1);
    }

    public void a()
    {
        if(k > 0)
            k--;
        i++;
        for(int i1 = 0; i1 < e.size(); i1++)
            ((np)e.get(i1)).b++;

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
            for(i1 = 1; i1 < s.length() && h.q.a(s.substring(0, i1 + 1)) <= 320; i1++);
            a(s.substring(0, i1));
        }

        e.add(0, new np(s));
        for(; e.size() > 50; e.remove(e.size() - 1));
    }

    public void b(String s)
    {
        j = (new StringBuilder()).append("Now playing: ").append(s).toString();
        k = 60;
        l = true;
    }

    public void c(String s)
    {
        adg adg1 = adg.a();
        String s1 = adg1.b(s);
        a(s1);
    }

    private static tr d = new tr();
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

}
