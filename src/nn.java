// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst noconv 
// Source File Name:   SourceFile

import java.util.List;
import net.minecraft.client.Minecraft;

public class nn extends zp
{

    private int a;
    private int b;

    public nn()
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
        s.add(new agk(1, q / 2 - 100, r / 4 + 120 + byte0, dw.a("menu.returnToMenu")));
        if(p.l())
        {
            ((agk)s.get(0)).e = dw.a("menu.disconnect");
        }
        s.add(new agk(4, q / 2 - 100, r / 4 + 24 + byte0, dw.a("menu.returnToGame")));
        s.add(new agk(0, q / 2 - 100, r / 4 + 96 + byte0, 98, 20, dw.a("menu.options")));
        s.add(new agk(7, q / 2 + 2, r / 4 + 96 + byte0, 98, 20, "WMLL Options..."));
        s.add(new agk(5, q / 2 - 100, r / 4 + 48 + byte0, 98, 20, dw.a("gui.achievements")));
        s.add(new agk(6, q / 2 + 2, r / 4 + 48 + byte0, 98, 20, dw.a("gui.stats")));
				if (WMLL.debugClassPresent)
		s.add(new agk(9001, q - 52, r - 22, 50, 20, "Reload"));
    }

    protected void a(agk agk1)
    {
        switch(agk1.f)
        {
        case 7:
        	p.a(new WMLLOptions(this));
        	break;
        case 9001:
        	p.a(new nn());
        	break;
        case 2: // '\002'
        case 3: // '\003'
        default:
            break;

        case 0: // '\0'
            p.a(new cw(this, p.z));
            break;

        case 1: // '\001'
            p.I.a(ir.j, 1);
            if(p.l())
            {
                p.f.f();
            }
            p.a((lj)null);
            p.a(new acf());
            break;

        case 4: // '\004'
            p.a((nb)null);
            p.g();
            break;

        case 5: // '\005'
            p.a(new va(p.I));
            break;

        case 6: // '\006'
            p.a(new ee(this, p.I));
            break;
        }
    }

    public void V_()
    {
        super.V_();
        b++;
    }

    public void a(int i, int j, float f)
    {
        t_();
        a(u, "Game menu", q / 2, 40, 0xffffff);
        super.a(i, j, f);
    }
}
