// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst noconv 
// Source File Name:   SourceFile

import java.util.List;
import net.minecraft.client.Minecraft;

public class ln extends wq
{

    private int a;
    private int b;

    public ln()
    {
        a = 0;
        b = 0;
    }

    @SuppressWarnings("unchecked")
	public void c()
    {
        a = 0;
        s.clear();
        byte byte0 = -16;
        s.add(new acv(1, q / 2 - 100, r / 4 + 120 + byte0, cz.a("menu.returnToMenu")));
        if(p.l())
        {
            ((acv)s.get(0)).e = cz.a("menu.disconnect");
        }
        s.add(new acv(4, q / 2 - 100, r / 4 + 24 + byte0, cz.a("menu.returnToGame")));
        s.add(new acv(0, q / 2 - 100, r / 4 + 96 + byte0, 98, 20, cz.a("menu.options")));
        s.add(new acv(7, q / 2 + 2, r / 4 + 96 + byte0, 98, 20, "WMLL Options..."));
        s.add(new acv(5, q / 2 - 100, r / 4 + 48 + byte0, 98, 20, cz.a("gui.achievements")));
        s.add(new acv(6, q / 2 + 2, r / 4 + 48 + byte0, 98, 20, cz.a("gui.stats")));
		if (WMLL.debugClassPresent)
			s.add(new acv(9001, q - 52, r - 22, 50, 20, "Reload"));
    }

    protected void a(acv acv1)
    {
        switch(acv1.f)
        {
        case 7:
        	p.a(new WMLLOptions(WMLL.i, this));
        	break;
        case 9001:
        	p.a(new ln());
        	break;
        case 2: // '\002'
        case 3: // '\003'
        default:
            break;

        case 0: // '\0'
            p.a(new ce(this, p.A));
            break;

        case 1: // '\001'
            p.K.a(ha.j, 1);
            if(p.l())
            {
                p.f.g();
            }
            p.s = null;
            p.a(new yy());
            break;

        case 4: // '\004'
            p.s = null;
            p.g();
            break;

        case 5: // '\005'
            p.a(new sm(p.K));
            break;

        case 6: // '\006'
            p.a(new df(this, p.K));
            break;
        }
    }

    public void a()
    {
        super.a();
        b++;
    }

    public void a(int i, int j, float f)
    {
        r_();
        boolean flag = !p.f.c(a++);
        if(flag || b < 20)
        {
            float f1 = ((float)(b % 10) + f) / 10F;
            f1 = gp.a(f1 * 3.141593F * 2.0F) * 0.2F + 0.8F;
            int k = (int)(255F * f1);
            b(u, "Saving level..", 8, r - 16, k << 16 | k << 8 | k);
        }
        a(u, "Game menu", q / 2, 40, 0xffffff);
        WMLLOptions.renderWMLLVersion();
        super.a(i, j, f);
    }
}
