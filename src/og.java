// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst noconv 
// Source File Name:   SourceFile

import java.util.List;
import net.minecraft.client.Minecraft;

public class og extends aau
{

    private int a;
    private int b;

    public og()
    {
        a = 0;
        b = 0;
    }

    public void c()
    {
        a = 0;
        s.clear();
        byte byte0 = -16;
        s.add(new ahu(1, q / 2 - 100, r / 4 + 120 + byte0, eg.a("menu.returnToMenu")));
        if(!p.A())
        {
            ((ahu)s.get(0)).e = eg.a("menu.disconnect");
        }
        s.add(new ahu(4, q / 2 - 100, r / 4 + 24 + byte0, eg.a("menu.returnToGame")));
        s.add(new ahu(5, q / 2 - 100, r / 4 + 48 + byte0, 98, 20, eg.a("gui.achievements")));
        s.add(new ahu(6, q / 2 + 2, r / 4 + 48 + byte0, 98, 20, eg.a("gui.stats")));
		s.add(new ahu(0, q / 2 - 100, r / 4 + 96 + byte0, 98, 20, eg.a("menu.options")));
        s.add(new ahu(7, q / 2 + 2, r / 4 + 96 + byte0, 98, 20, "WMLL Options..."));
		if (WMLL.debugClassPresent)
			s.add(new ahu(9001, q - 52, r - 22, 50, 20, "Reload"));
    }

    protected void a(ahu ahu1)
    {
        switch(ahu1.f)
        {
        case 9001:
        	p.a(new og());
        	return;
        case 7:
        	p.a(new WMLLOptions(this));
        	return;
        case 0: // '\0'
            p.a(new dh(this, p.z));
            break;

        case 1: // '\001'
            p.I.a(jh.j, 1);
            p.f.f();
            p.a((mb)null);
            p.a(new adm());
            break;

        case 4: // '\004'
            p.a((aau)null);
            p.h();
            break;

        case 5: // '\005'
            p.a(new vx(p.I));
            break;

        case 6: // '\006'
            p.a(new en(this, p.I));
            break;
        }
    }

    public void R_()
    {
        super.R_();
        b++;
    }

    public void a(int i, int j, float f)
    {
        t_();
        a(u, "Game menu", q / 2, 40, 0xffffff);
        super.a(i, j, f);
    }
}
