// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst noconv 
// Source File Name:   SourceFile

import java.awt.Color;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import org.lwjgl.opengl.GL11;

public class ava extends avb
{

    private static final bjd b = new bjd("textures/misc/vignette.png");
    private static final bjd c = new bjd("textures/gui/widgets.png");
    private static final bjd d = new bjd("textures/misc/pumpkinblur.png");
    private static final bgl e = new bgl();
    private final Random f = new Random();
    private final atn g;
    private final aul h;
    private int i;
    private String j;
    private int o;
    private boolean p;
    public float ipeer_a_rename;
    private int q;
    private xz r;

    public ava(atn atn1)
    {
        j = "";
        ipeer_a_rename = 1.0F;
        g = atn1;
        h = new aul(atn1);
    }

    public void a(float f1, boolean flag, int k, int l)
    {
        avw avw1 = new avw(g.t, g.c, g.d);
        int i1 = avw1.a();
        int j1 = avw1.b();
        auz auz1 = g.k;
        g.o.c();
        GL11.glEnable(3042);
        if(atn.s())
        {
            a(g.g.d(f1), i1, j1);
        } else
        {
            GL11.glBlendFunc(770, 771);
        }
        xz xz1 = g.g.bn.f(3);
        if(g.t.aa == 0 && xz1 != null && xz1.d == aqs.bf.cF)
        {
            b(i1, j1);
        }
        if(!g.g.a(nf.k))
        {
            float f2 = g.g.bO + (g.g.bN - g.g.bO) * f1;
            if(f2 > 0.0F)
            {
                b(f2, i1, j1);
            }
        }
        if(!g.b.a())
        {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            g.J().a(c);
            ty ty1 = g.g.bn;
            n = -90F;
            b(i1 / 2 - 91, j1 - 22, 0, 0, 182, 22);
            b((i1 / 2 - 91 - 1) + ty1.c * 20, j1 - 22 - 1, 0, 22, 24, 22);
            g.J().a(m);
            GL11.glEnable(3042);
            GL11.glBlendFunc(775, 769);
            b(i1 / 2 - 7, j1 / 2 - 7, 0, 0, 16, 16);
            GL11.glDisable(3042);
            g.B.a("bossHealth");
            d();
            g.B.b();
            if(g.b.b())
            {
                a(i1, j1);
            }
            GL11.glDisable(3042);
            g.B.a("actionBar");
            GL11.glEnable(32826);
            atl.c();
            for(int i2 = 0; i2 < 9; i2++)
            {
                int k2 = (i1 / 2 - 90) + i2 * 20 + 2;
                int j3 = j1 - 16 - 3;
                a(i2, k2, j3, f1);
            }

            atl.a();
            GL11.glDisable(32826);
            g.B.b();
        }
        if(g.g.bz() > 0)
        {
            g.B.a("sleep");
            GL11.glDisable(2929);
            GL11.glDisable(3008);
            int k1 = g.g.bz();
            float f3 = (float)k1 / 100F;
            if(f3 > 1.0F)
            {
                f3 = 1.0F - (float)(k1 - 100) / 10F;
            }
            int l2 = (int)(220F * f3) << 24 | 0x101020;
            a(0, 0, i1, j1, l2);
            GL11.glEnable(3008);
            GL11.glEnable(2929);
            g.B.b();
        }
        int l1 = 0xffffff;
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        int j2 = i1 / 2 - 91;
        if(g.g.t())
        {
            g.B.a("jumpBar");
            g.J().a(avb.m);
            float f4 = g.g.bJ();
            char c1 = '\266';
            int k4 = (int)(f4 * (float)(c1 + 1));
            int i6 = (j1 - 32) + 3;
            b(j2, i6, 0, 84, c1, 5);
            if(k4 > 0)
            {
                b(j2, i6, 0, 89, k4, 5);
            }
            g.B.b();
        } else
        if(g.b.f())
        {
            g.B.a("expBar");
            g.J().a(avb.m);
            int i3 = g.g.bC();
            if(i3 > 0)
            {
                char c2 = '\266';
                int l4 = (int)(g.g.bJ * (float)(c2 + 1));
                int j6 = (j1 - 32) + 3;
                b(j2, j6, 0, 64, c2, 5);
                if(l4 > 0)
                {
                    b(j2, j6, 0, 69, l4, 5);
                }
            }
            g.B.b();
            if(g.g.bH > 0)
            {
                g.B.a("expLevel");
                boolean flag1 = false;
                int i5 = flag1 ? 0xffffff : 0x80ff20;
                String s2 = (new StringBuilder()).append("").append(g.g.bH).toString();
                int i7 = (i1 - auz1.a(s2)) / 2;
                int k7 = j1 - 31 - 4;
                boolean flag2 = false;
                auz1.b(s2, i7 + 1, k7, 0);
                auz1.b(s2, i7 - 1, k7, 0);
                auz1.b(s2, i7, k7 + 1, 0);
                auz1.b(s2, i7, k7 - 1, 0);
                auz1.b(s2, i7, k7, i5);
                g.B.b();
            }
        }
        if(g.t.D)
        {
            g.B.a("toolHighlight");
            if(q > 0 && r != null)
            {
                String s = r.s();
                int k3 = (i1 - auz1.a(s)) / 2;
                int j5 = j1 - 59;
                if(!g.b.b())
                {
                    j5 += 14;
                }
                int k6 = (int)(((float)q * 256F) / 10F);
                if(k6 > 255)
                {
                    k6 = 255;
                }
                if(k6 > 0)
                {
                    GL11.glPushMatrix();
                    GL11.glEnable(3042);
                    GL11.glBlendFunc(770, 771);
                    auz1.a(s, k3, j5, 0xffffff + (k6 << 24));
                    GL11.glDisable(3042);
                    GL11.glPopMatrix();
                }
            }
            g.B.b();
        }
        if(g.p())
        {
            g.B.a("demo");
            String s1 = "";
            if(g.e.I() >= 0x1d6b4L)
            {
                s1 = bjq.a("demo.demoExpired");
            } else
            {
                s1 = String.format(bjq.a("demo.remainingTime"), new Object[] {
                    lx.a((int)(0x1d6b4L - g.e.I()))
                });
            }
            int i4 = auz1.a(s1);
            auz1.a(s1, i1 - i4 - 10, 5, 0xffffff);
            g.B.b();
        }
        if (!WMLL.i.useML)
        	WMLL.i.updategui(g, f1, this);
        if(g.t.ab)
        {
            g.B.a("debug");
            GL11.glPushMatrix();
            auz1.a((new StringBuilder()).append("Minecraft 1.6.1 (").append(g.D).append(")").toString(), 2, 2, 0xffffff);
            auz1.a(g.l(), 2, 12, 0xffffff);
            auz1.a(g.m(), 2, 22, 0xffffff);
            auz1.a(g.o(), 2, 32, 0xffffff);
            auz1.a(g.n(), 2, 42, 0xffffff);
            long l3 = Runtime.getRuntime().maxMemory();
            long l5 = Runtime.getRuntime().totalMemory();
            long l7 = Runtime.getRuntime().freeMemory();
            long l8 = l5 - l7;
            String s3 = (new StringBuilder()).append("Used memory: ").append((l8 * 100L) / l3).append("% (").append(l8 / 1024L / 1024L).append("MB) of ").append(l3 / 1024L / 1024L).append("MB").toString();
            int i9 = 0xe0e0e0;
            b(auz1, s3, i1 - auz1.a(s3) - 2, 2, 0xe0e0e0);
            s3 = (new StringBuilder()).append("Allocated memory: ").append((l5 * 100L) / l3).append("% (").append(l5 / 1024L / 1024L).append("MB)").toString();
            b(auz1, s3, i1 - auz1.a(s3) - 2, 12, 0xe0e0e0);
            int k9 = lp.c(g.g.u);
            int i10 = lp.c(g.g.v);
            int k10 = lp.c(g.g.w);
            b(auz1, String.format("x: %.5f (%d) // c: %d (%d)", new Object[] {
                Double.valueOf(g.g.u), Integer.valueOf(k9), Integer.valueOf(k9 >> 4), Integer.valueOf(k9 & 0xf)
            }), 2, 64, 0xe0e0e0);
            b(auz1, String.format("y: %.3f (feet pos, %.3f eyes pos)", new Object[] {
                Double.valueOf(g.g.E.b), Double.valueOf(g.g.v)
            }), 2, 72, 0xe0e0e0);
            b(auz1, String.format("z: %.5f (%d) // c: %d (%d)", new Object[] {
                Double.valueOf(g.g.w), Integer.valueOf(k10), Integer.valueOf(k10 >> 4), Integer.valueOf(k10 & 0xf)
            }), 2, 80, 0xe0e0e0);
            int l10 = lp.c((double)((g.g.A * 4F) / 360F) + 0.5D) & 3;
            b(auz1, (new StringBuilder()).append("f: ").append(l10).append(" (").append(WMLL.i.getPlayerDirection(l10)).append(") / ").append(lp.g(g.g.A)).toString(), 2, 88, 0xe0e0e0);
            if(g.e != null && g.e.f(k9, i10, k10))
            {
                adm adm1 = g.e.d(k9, k10);
                b(auz1, (new StringBuilder()).append("lc: ").append(adm1.h() + 15).append(" b: ").append(adm1.a(k9 & 0xf, k10 & 0xf, g.e.u()).y).append(" bl: ").append(adm1.a(acc.b, k9 & 0xf, i10, k10 & 0xf)).append(" sl: ").append(adm1.a(acc.a, k9 & 0xf, i10, k10 & 0xf)).append(" rl: ").append(adm1.c(k9 & 0xf, i10, k10 & 0xf, 0)).toString(), 2, 96, 0xe0e0e0);
            }
            b(auz1, String.format("ws: %.3f, fs: %.3f, g: %b, fl: %d", new Object[] {
                Float.valueOf(g.g.bG.b()), Float.valueOf(g.g.bG.a()), Boolean.valueOf(g.g.F), Integer.valueOf(g.e.f(k9, k10))
            }), 2, 104, 0xe0e0e0);
            GL11.glPopMatrix();
            g.B.b();
        }
        if(o > 0)
        {
            g.B.a("overlayMessage");
            float f5 = (float)o - f1;
            int j4 = (int)((f5 * 255F) / 20F);
            if(j4 > 255)
            {
                j4 = 255;
            }
            if(j4 > 8)
            {
                GL11.glPushMatrix();
                GL11.glTranslatef(i1 / 2, j1 - 68, 0.0F);
                GL11.glEnable(3042);
                GL11.glBlendFunc(770, 771);
                int k5 = 0xffffff;
                if(p)
                {
                    k5 = Color.HSBtoRGB(f5 / 50F, 0.7F, 0.6F) & 0xffffff;
                }
                auz1.b(j, -auz1.a(j) / 2, -4, k5 + (j4 << 24 & 0xff000000));
                GL11.glDisable(3042);
                GL11.glPopMatrix();
            }
            g.B.b();
        }
        asx asx1 = g.e.X().a(1);
        if(asx1 != null)
        {
            a(asx1, j1, i1, auz1);
        }
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3008);
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, j1 - 48, 0.0F);
        g.B.a("chat");
        h.a(i);
        g.B.b();
        GL11.glPopMatrix();
        asx1 = g.e.X().a(0);
        if(g.t.T.e && (!g.A() || g.g.a.c.size() > 1 || asx1 != null))
        {
            g.B.a("playerList");
            bcn bcn1 = g.g.a;
            java.util.List list = bcn1.c;
            int l6 = bcn1.d;
            int j7 = l6;
            int i8 = 1;
            for(; j7 > 20; j7 = ((l6 + i8) - 1) / i8)
            {
                i8++;
            }

            int j8 = 300 / i8;
            if(j8 > 150)
            {
                j8 = 150;
            }
            int k8 = (i1 - i8 * j8) / 2;
            byte byte0 = 10;
            a(k8 - 1, byte0 - 1, k8 + j8 * i8, byte0 + 9 * j7, 0x80000000);
            for(int j9 = 0; j9 < l6; j9++)
            {
                int l9 = k8 + (j9 % i8) * j8;
                int j10 = byte0 + (j9 / i8) * 9;
                a(l9, j10, (l9 + j8) - 1, j10 + 8, 0x20ffffff);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glEnable(3008);
                if(j9 >= list.size())
                {
                    continue;
                }
                bcy bcy1 = (bcy)list.get(j9);
                asy asy1 = g.e.X().i(bcy1.a);
                String s4 = asy.a(asy1, bcy1.a);
                auz1.a(s4, l9, j10, 0xffffff);
                if(asx1 != null)
                {
                    int i11 = l9 + auz1.a(s4) + 5;
                    int k11 = (l9 + j8) - 12 - 5;
                    if(k11 - i11 > 5)
                    {
                        asz asz1 = asx1.a().a(bcy1.a, asx1);
                        String s5 = (new StringBuilder()).append(a.o).append("").append(asz1.c()).toString();
                        auz1.a(s5, k11 - auz1.a(s5), j10, 0xffffff);
                    }
                }
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                g.J().a(m);
                int j11 = 0;
                byte byte1 = 0;
                if(bcy1.b < 0)
                {
                    byte1 = 5;
                } else
                if(bcy1.b < 150)
                {
                    byte1 = 0;
                } else
                if(bcy1.b < 300)
                {
                    byte1 = 1;
                } else
                if(bcy1.b < 600)
                {
                    byte1 = 2;
                } else
                if(bcy1.b < 1000)
                {
                    byte1 = 3;
                } else
                {
                    byte1 = 4;
                }
                n += 100F;
                b((l9 + j8) - 12, j10, 0 + j11 * 10, 176 + byte1 * 8, 10, 8);
                n -= 100F;
            }

        }
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(2896);
        GL11.glEnable(3008);
    }

    private void a(asx asx1, int k, int l, auz auz1)
    {
        atc atc1 = asx1.a();
        Collection collection = atc1.i(asx1);
        if(collection.size() > 15)
        {
            return;
        }
        int i1 = auz1.a(asx1.d());
        for(Iterator iterator = collection.iterator(); iterator.hasNext();)
        {
            asz asz1 = (asz)iterator.next();
            asy asy1 = atc1.i(asz1.e());
            String s = (new StringBuilder()).append(asy.a(asy1, asz1.e())).append(": ").append(a.m).append(asz1.c()).toString();
            i1 = Math.max(i1, auz1.a(s));
        }

        int j1 = collection.size() * auz1.a;
        int k1 = k / 2 + j1 / 3;
        byte byte0 = 3;
        int l1 = l - i1 - byte0;
        int i2 = 0;
        Iterator iterator1 = collection.iterator();
        do
        {
            if(!iterator1.hasNext())
            {
                break;
            }
            asz asz2 = (asz)iterator1.next();
            i2++;
            asy asy2 = atc1.i(asz2.e());
            String s1 = asy.a(asy2, asz2.e());
            String s2 = (new StringBuilder()).append(a.m).append("").append(asz2.c()).toString();
            int j2 = l1;
            int k2 = k1 - i2 * auz1.a;
            int l2 = (l - byte0) + 2;
            a(j2 - 2, k2, l2, k2 + auz1.a, 0x50000000);
            auz1.b(s1, j2, k2, 0x20ffffff);
            auz1.b(s2, l2 - auz1.a(s2), k2, 0x20ffffff);
            if(i2 == collection.size())
            {
                String s3 = asx1.d();
                a(j2 - 2, k2 - auz1.a - 1, l2, k2 - 1, 0x60000000);
                a(j2 - 2, k2 - 1, l2, k2, 0x50000000);
                auz1.b(s3, (j2 + i1 / 2) - auz1.a(s3) / 2, k2 - auz1.a, 0x20ffffff);
            }
        } while(true);
    }

    private void a(int k, int l)
    {
        boolean flag = (g.g.af / 3) % 2 == 1;
        if(g.g.af < 10)
        {
            flag = false;
        }
        int i1 = lp.f(g.g.aJ());
        int j1 = lp.f(g.g.ax);
        f.setSeed(i * 0x4c627);
        boolean flag1 = false;
        us us1 = g.g.bD();
        int k1 = us1.a();
        int l1 = us1.b();
        op op1 = g.g.a(tm.a);
        int i2 = k / 2 - 91;
        int j2 = k / 2 + 91;
        int k2 = l - 39;
        float f1 = (float)op1.e();
        float f2 = g.g.bj();
        int l2 = lp.f((f1 + f2) / 2.0F / 10F);
        int i3 = Math.max(10 - (l2 - 2), 3);
        int j3 = k2 - (l2 - 1) * i3 - 10;
        float f3 = f2;
        int k3 = g.g.aM();
        int l3 = -1;
        if(g.g.a(nf.l))
        {
            l3 = i % lp.f(f1 + 5F);
        }
        g.B.a("armor");
        for(int i4 = 0; i4 < 10; i4++)
        {
            if(k3 <= 0)
            {
                continue;
            }
            int k4 = i2 + i4 * 8;
            if(i4 * 2 + 1 < k3)
            {
                b(k4, j3, 34, 9, 9, 9);
            }
            if(i4 * 2 + 1 == k3)
            {
                b(k4, j3, 25, 9, 9, 9);
            }
            if(i4 * 2 + 1 > k3)
            {
                b(k4, j3, 16, 9, 9, 9);
            }
        }

        g.B.c("health");
        for(int j4 = lp.f((f1 + f2) / 2.0F) - 1; j4 >= 0; j4--)
        {
            int l4 = 16;
            if(g.g.a(nf.u))
            {
                l4 += 36;
            } else
            if(g.g.a(nf.v))
            {
                l4 += 72;
            }
            int k5 = 0;
            if(flag)
            {
                k5 = 1;
            }
            int k6 = lp.f((float)(j4 + 1) / 10F) - 1;
            int j7 = i2 + (j4 % 10) * 8;
            int i8 = k2 - k6 * i3;
            if(i1 <= 4)
            {
                i8 += f.nextInt(2);
            }
            if(j4 == l3)
            {
                i8 -= 2;
            }
            byte byte1 = 0;
            if(g.e.N().t())
            {
                byte1 = 5;
            }
            b(j7, i8, 16 + k5 * 9, 9 * byte1, 9, 9);
            if(flag)
            {
                if(j4 * 2 + 1 < j1)
                {
                    b(j7, i8, l4 + 54, 9 * byte1, 9, 9);
                }
                if(j4 * 2 + 1 == j1)
                {
                    b(j7, i8, l4 + 63, 9 * byte1, 9, 9);
                }
            }
            if(f3 > 0.0F)
            {
                if(f3 == f2 && f2 % 2.0F == 1.0F)
                {
                    b(j7, i8, l4 + 153, 9 * byte1, 9, 9);
                } else
                {
                    b(j7, i8, l4 + 144, 9 * byte1, 9, 9);
                }
                f3 -= 2.0F;
                continue;
            }
            if(j4 * 2 + 1 < i1)
            {
                b(j7, i8, l4 + 36, 9 * byte1, 9, 9);
            }
            if(j4 * 2 + 1 == i1)
            {
                b(j7, i8, l4 + 45, 9 * byte1, 9, 9);
            }
        }

        nk nk1 = g.g.o;
        if(nk1 == null)
        {
            g.B.c("food");
            for(int i5 = 0; i5 < 10; i5++)
            {
                int l5 = k2;
                int l6 = 16;
                byte byte0 = 0;
                if(g.g.a(nf.s))
                {
                    l6 += 36;
                    byte0 = 13;
                }
                if(g.g.bD().e() <= 0.0F && i % (k1 * 3 + 1) == 0)
                {
                    l5 += f.nextInt(3) - 1;
                }
                if(flag1)
                {
                    byte0 = 1;
                }
                int j8 = j2 - i5 * 8 - 9;
                b(j8, l5, 16 + byte0 * 9, 27, 9, 9);
                if(flag1)
                {
                    if(i5 * 2 + 1 < l1)
                    {
                        b(j8, l5, l6 + 54, 27, 9, 9);
                    }
                    if(i5 * 2 + 1 == l1)
                    {
                        b(j8, l5, l6 + 63, 27, 9, 9);
                    }
                }
                if(i5 * 2 + 1 < k1)
                {
                    b(j8, l5, l6 + 36, 27, 9, 9);
                }
                if(i5 * 2 + 1 == k1)
                {
                    b(j8, l5, l6 + 45, 27, 9, 9);
                }
            }

        } else
        if(nk1 instanceof oc)
        {
            g.B.c("mountHealth");
            oc oc1 = (oc)nk1;
            int i6 = (int)Math.ceil(oc1.aJ());
            float f4 = oc1.aP();
            int k7 = (int)(f4 + 0.5F) / 2;
            if(k7 > 30)
            {
                k7 = 30;
            }
            int k8 = k2;
            for(int l8 = 0; k7 > 0; l8 += 20)
            {
                int i9 = Math.min(k7, 10);
                k7 -= i9;
                for(int j9 = 0; j9 < i9; j9++)
                {
                    byte byte2 = 52;
                    int k9 = 0;
                    if(flag1)
                    {
                        k9 = 1;
                    }
                    int l9 = j2 - j9 * 8 - 9;
                    b(l9, k8, byte2 + k9 * 9, 9, 9, 9);
                    if(j9 * 2 + 1 + l8 < i6)
                    {
                        b(l9, k8, byte2 + 36, 9, 9, 9);
                    }
                    if(j9 * 2 + 1 + l8 == i6)
                    {
                        b(l9, k8, byte2 + 45, 9, 9, 9);
                    }
                }

                k8 -= 10;
            }

        }
        g.B.c("air");
        if(g.g.a(ajv.h))
        {
            int j5 = g.g.aj();
            int j6 = lp.f(((double)(j5 - 2) * 10D) / 300D);
            int i7 = lp.f(((double)j5 * 10D) / 300D) - j6;
            for(int l7 = 0; l7 < j6 + i7; l7++)
            {
                if(l7 < j6)
                {
                    b(j2 - l7 * 8 - 9, j3, 16, 18, 9, 9);
                } else
                {
                    b(j2 - l7 * 8 - 9, j3, 25, 18, 9, 9);
                }
            }

        }
        g.B.b();
    }

    private void d()
    {
        if(beo.c == null || beo.b <= 0)
        {
            return;
        }
        beo.b--;
        auz auz1 = g.k;
        avw avw1 = new avw(g.t, g.c, g.d);
        int k = avw1.a();
        char c1 = '\266';
        int l = k / 2 - c1 / 2;
        int i1 = (int)(beo.a * (float)(c1 + 1));
        byte byte0 = 12;
        b(l, byte0, 0, 74, c1, 5);
        b(l, byte0, 0, 74, c1, 5);
        if(i1 > 0)
        {
            b(l, byte0, 0, 79, i1, 5);
        }
        String s = beo.c;
        auz1.a(s, k / 2 - auz1.a(s) / 2, byte0 - 10, 0xffffff);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        g.J().a(m);
    }

    private void b(int k, int l)
    {
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(3008);
        g.J().a(d);
        bff bff1 = bff.a;
        bff1.b();
        bff1.a(0.0D, l, -90D, 0.0D, 1.0D);
        bff1.a(k, l, -90D, 1.0D, 1.0D);
        bff1.a(k, 0.0D, -90D, 1.0D, 0.0D);
        bff1.a(0.0D, 0.0D, -90D, 0.0D, 0.0D);
        bff1.a();
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
        ipeer_a_rename += (double)(f1 - ipeer_a_rename) * 0.01D;
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(0, 769);
        GL11.glColor4f(ipeer_a_rename, ipeer_a_rename, ipeer_a_rename, 1.0F);
        g.J().a(b);
        bff bff1 = bff.a;
        bff1.b();
        bff1.a(0.0D, l, -90D, 0.0D, 1.0D);
        bff1.a(k, l, -90D, 1.0D, 1.0D);
        bff1.a(k, 0.0D, -90D, 1.0D, 0.0D);
        bff1.a(0.0D, 0.0D, -90D, 0.0D, 0.0D);
        bff1.a();
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
        mp mp1 = aqs.bj.m(1);
        g.J().a(bhz.b);
        float f2 = mp1.c();
        float f3 = mp1.e();
        float f4 = mp1.d();
        float f5 = mp1.f();
        bff bff1 = bff.a;
        bff1.b();
        bff1.a(0.0D, l, -90D, f2, f5);
        bff1.a(k, l, -90D, f4, f5);
        bff1.a(k, 0.0D, -90D, f4, f3);
        bff1.a(0.0D, 0.0D, -90D, f2, f3);
        bff1.a();
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
        GL11.glEnable(3008);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    private void a(int k, int l, int i1, float f1)
    {
        xz xz1 = g.g.bn.a[k];
        if(xz1 == null)
        {
            return;
        }
        float f2 = (float)xz1.c - f1;
        if(f2 > 0.0F)
        {
            GL11.glPushMatrix();
            float f3 = 1.0F + f2 / 5F;
            GL11.glTranslatef(l + 8, i1 + 12, 0.0F);
            GL11.glScalef(1.0F / f3, (f3 + 1.0F) / 2.0F, 1.0F);
            GL11.glTranslatef(-(l + 8), -(i1 + 12), 0.0F);
        }
        e.b(g.k, g.J(), xz1, l, i1);
        if(f2 > 0.0F)
        {
            GL11.glPopMatrix();
        }
        e.c(g.k, g.J(), xz1, l, i1);
    }

    public void a()
    {
        if(o > 0)
        {
            o--;
        }
        i++;
        if(g.g != null)
        {
            xz xz1 = g.g.bn.h();
            if(xz1 == null)
            {
                q = 0;
            } else
            if(r == null || xz1.d != r.d || !xz.a(xz1, r) || !xz1.g() && xz1.k() != r.k())
            {
                q = 40;
            } else
            if(q > 0)
            {
                q--;
            }
            r = xz1;
        }
    }

    public void a(String s)
    {
        a((new StringBuilder()).append("Now playing: ").append(s).toString(), true);
    }

    public void a(String s, boolean flag)
    {
        j = s;
        o = 60;
        p = flag;
    }

    public aul b()
    {
        return h;
    }

    public int c()
    {
        return i;
    }

}
