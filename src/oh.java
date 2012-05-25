// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst noconv 
// Source File Name:   SourceFile

import java.util.List;
import net.minecraft.client.Minecraft;

public class oh extends aas
{

    private int a;
    private int b;

    public oh()
    {
        a = 0;
        b = 0;
    }

    @SuppressWarnings("unchecked")
	public void b()
    {
        a = 0;
        s.clear();
        byte byte0 = -16;
        s.add(new ahs(1, q / 2 - 100, r / 4 + 120 + byte0, eh.a("menu.returnToMenu")));
        if(p.z())
        {
            ((ahs)s.get(0)).e = eh.a("menu.disconnect");
        }
        s.add(new ahs(4, q / 2 - 100, r / 4 + 24 + byte0, eh.a("menu.returnToGame")));
        s.add(new ahs(5, q / 2 - 100, r / 4 + 48 + byte0, 98, 20, eh.a("gui.achievements")));
        s.add(new ahs(6, q / 2 + 2, r / 4 + 48 + byte0, 98, 20, eh.a("gui.stats")));
		s.add(new ahs(0, q / 2 - 100, r / 4 + 96 + byte0, 98, 20, eh.a("menu.options")));
        s.add(new ahs(7, q / 2 + 2, r / 4 + 96 + byte0, 98, 20, "WMLL Options..."));
		if (WMLL.debugClassPresent)
			s.add(new ahs(9001, q - 52, r - 22, 50, 20, "Reload"));
    }

    protected void a(ahs ahs1)
    {
        switch(ahs1.f)
        {
        case 0: // '\0'
            p.a(new di(this, p.z));
            break;

        case 1: // '\001'
            p.I.a(ji.j, 1);
            p.f.f();
            p.a((md)null);
            p.a(new adk());
            break;

        case 4: // '\004'
            p.a((aas)null);
            p.g();
            break;

        case 5: // '\005'
            p.a(new vw(p.I));
            break;

        case 6: // '\006'
            p.a(new ep(this, p.I));
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
