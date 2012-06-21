// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst noconv 
// Source File Name:   SourceFile

import java.util.List;
import net.minecraft.client.Minecraft;

public class oj extends aba
{

    private int a;
    private int b;

    public oj()
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
        s.add(new ahz(1, q / 2 - 100, r / 4 + 120 + byte0, eg.a("menu.returnToMenu")));
        if(!p.A())
        {
            ((ahz)s.get(0)).e = eg.a("menu.disconnect");
        }
        s.add(new ahz(4, q / 2 - 100, r / 4 + 24 + byte0, eg.a("menu.returnToGame")));
        ahz ahz1;
        s.add(ahz1 = new ahz(7, q / 2 + 2, r / 4 + 96 + byte0, 98, 20, eg.a("menu.shareToLan")));
        s.add(new ahz(5, q / 2 - 100, r / 4 + 48 + byte0, 98, 20, eg.a("gui.achievements")));
        s.add(new ahz(6, q / 2 + 2, r / 4 + 48 + byte0, 98, 20, eg.a("gui.stats")));
		s.add(new ahz(0, q / 2 - 100, r / 4 + 96 + byte0, 98, 20, eg.a("menu.options")));
        s.add(new ahz(8, q / 2 - 100, r / 4 + 144 + byte0, "WMLL Options..."));
		if (WMLL.debugClassPresent)
			s.add(new ahz(9001, q - 52, r - 22, 50, 20, "Reload"));
        ahz1.h = p.B() && !p.C().af();
    }

    protected void a(ahz ahz1)
    {
        switch(ahz1.f)
        {
	    case 9001:
        	p.a(new oj());
        	return;
        case 8:
        	p.a(new WMLLOptions(this));
        	return;
        	
        case 0: // '\0'
            p.a(new dh(this, p.z));
            break;

        case 1: // '\001'
            p.I.a(ji.j, 1);
            p.f.f();
            p.a((md)null);
            p.a(new ads());
            break;

        case 4: // '\004'
            p.a((aba)null);
            p.h();
            break;

        case 5: // '\005'
            p.a(new wb(p.I));
            break;

        case 6: // '\006'
            p.a(new en(this, p.I));
            break;

        case 7: // '\007'
            p.a(new mu(this));
            break;
        }
    }

    public void T_()
    {
        super.T_();
        b++;
    }

    public void a(int i, int j, float f)
    {
        t_();
        a(u, "Game menu", q / 2, 40, 0xffffff);
        super.a(i, j, f);
    }
}
