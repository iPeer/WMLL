// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst noconv 
// Source File Name:   SourceFile

import java.util.List;
import net.minecraft.client.Minecraft;

public class nd extends yv
{

    private int a;
    private int b;

    public nd()
    {
        a = 0;
        b = 0;
    }

    public void c()
    {
        a = 0;
        s.clear();
        byte byte0 = -16;
        s.add(new afk(1, q / 2 - 100, r / 4 + 120 + byte0, dt.a("menu.returnToMenu")));
        if(p.l())
        {
            ((afk)s.get(0)).e = dt.a("menu.disconnect");
        }
        s.add(new afk(4, q / 2 - 100, r / 4 + 24 + byte0, dt.a("menu.returnToGame")));
        s.add(new afk(0, q / 2 - 100, r / 4 + 96 + byte0, 98, 20, dt.a("menu.options")));
        s.add(new afk(7, q / 2 + 2, r / 4 + 96 + byte0, 98, 20, "WMLL Options..."));
        s.add(new afk(5, q / 2 - 100, r / 4 + 48 + byte0, 98, 20, dt.a("gui.achievements")));
        s.add(new afk(6, q / 2 + 2, r / 4 + 48 + byte0, 98, 20, dt.a("gui.stats")));
				if (WMLL.debugClassPresent)
		s.add(new afk(9001, q - 52, r - 22, 50, 20, "Reload"));
    }

    protected void a(afk afk1)
    {
        switch(afk1.f)
        {
        case 2: // '\002'
        case 3: // '\003'
        default:
            break;

        case 0: // '\0'
            p.a(new ct(this, p.A));
            break;

        case 1: // '\001'
            p.K.a(ik.j, 1);
            if(p.l())
            {
                p.f.f();
            }
            p.a((lb)null);
            p.a(new abg());
            break;

        case 4: // '\004'
            p.a((yv)null);
            p.g();
            break;

        case 5: // '\005'
            p.a(new uk(p.K));
            break;

        case 6: // '\006'
            p.a(new ea(this, p.K));
            break;
			
		case 7:
        	p.a(new WMLLOptions(WMLL.i, this));
        	break;
        }
    }

    public void U_()
    {
        super.U_();
        b++;
    }

    public void a(int i, int j, float f)
    {
        t_();
        a(u, "Game menu", q / 2, 40, 0xffffff);
        super.a(i, j, f);
    }
}
