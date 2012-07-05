// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst noconv 
// Source File Name:   SourceFile

import java.util.List;
import net.minecraft.client.Minecraft;

public class ot extends abm
{

    private int a;
    private int b;

    public ot()
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
        s.add(new ain(1, q / 2 - 100, r / 4 + 120 + byte0, ek.a("menu.returnToMenu")));
        if(!p.A())
        {
            ((ain)s.get(0)).e = ek.a("menu.disconnect");
        }
        s.add(new ain(4, q / 2 - 100, r / 4 + 24 + byte0, ek.a("menu.returnToGame")));
        ain ain1;
        s.add(ain1 = new ain(7, q / 2 + 2, r / 4 + 96 + byte0, 98, 20, ek.a("menu.shareToLan")));
        s.add(new ain(5, q / 2 - 100, r / 4 + 48 + byte0, 98, 20, ek.a("gui.achievements")));
        s.add(new ain(6, q / 2 + 2, r / 4 + 48 + byte0, 98, 20, ek.a("gui.stats")));
				s.add(new ain(0, q / 2 - 100, r / 4 + 96 + byte0, 98, 20, ek.a("menu.options")));
		s.add(new ain(8, q / 2 - 100, r / 4 + 144 + byte0, "WMLL Options..."));
		if (WMLL.debugClassPresent)
			s.add(new ain(9001, q - 52, r - 22, 50, 20, "Reload"));
        ain1.h = p.B() && !p.C().ah();
    }

    protected void a(ain ain1)
    {
        switch(ain1.f)
        {
		case 9001:
        	p.a(new ot());
        	return;
        case 8:
        	p.a(new WMLLOptions(this));
        	return;
		
        case 0: // '\0'
            p.a(new dh(this, p.z));
            break;

        case 1: // '\001'
            ain1.h = false;
            p.F.a(jm.j, 1);
            p.f.f();
            p.a((mn)null);
            p.a(new aee());
            break;

        case 4: // '\004'
            p.a((abm)null);
            p.h();
            break;

        case 5: // '\005'
            p.a(new wl(p.F));
            break;

        case 6: // '\006'
            p.a(new er(this, p.F));
            break;

        case 7: // '\007'
            p.a(new ne(this));
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
        u_();
        a(u, "Game menu", q / 2, 40, 0xffffff);
        super.a(i, j, f);
    }
}
