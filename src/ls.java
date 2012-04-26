// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst noconv 
// Source File Name:   SourceFile

import java.util.List;
import net.minecraft.client.Minecraft;

public class ls extends xa
{

    private int a;
    private int b;
    private xa gui = null;

    public ls()
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
        s.add(new adi(1, q / 2 - 100, r / 4 + 120 + byte0, db.a("menu.returnToMenu")));
        if(p.l())
        {
            ((adi)s.get(0)).e = db.a("menu.disconnect");
        }
        s.add(new adi(4, q / 2 - 100, r / 4 + 24 + byte0, db.a("menu.returnToGame")));
        s.add(new adi(0, q / 2 - 100, r / 4 + 96 + byte0, 98, 20, db.a("menu.options")));
        s.add(new adi(7, q / 2 + 2, r / 4 + 96 + byte0, 98, 20, "WMLL Options..."));
        s.add(new adi(5, q / 2 - 100, r / 4 + 48 + byte0, 98, 20, db.a("gui.achievements")));
        s.add(new adi(6, q / 2 + 2, r / 4 + 48 + byte0, 98, 20, db.a("gui.stats")));
		if (WMLL.debugClassPresent)
			s.add(new adi(9001, q - 52, r - 22, 50, 20, "Reload"));
    }

    protected void a(adi adi1)
    {
    	
        switch(adi1.f)
        {

        case 2: // '\002'
        case 3: // '\003'
        default:
            break;
            
        case 1:
            p.K.a(hg.j, 1);
            if(p.l())
            {
                p.f.g();
            }
            p.a((yu)null);
            p.a(new zk());
            break;
            
        case 0: // '\0'
            p.a(new cg(this, p.A));
            break;

        case 4: // '\004'
            p.a(gui);
            p.g();
            break;

        case 5: // '\005'
            p.a(new sv(p.K));
            break;

        case 6: // '\006'
            p.a(new dh(this, p.K));
            break;
            
        case 9001:
        	p.a(new ls());
        	break;
        	
        case 7:
        	p.a(new WMLLOptions(WMLL.i, this));
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
            f1 = gt.a(f1 * 3.141593F * 2.0F) * 0.2F + 0.8F;
            int k = (int)(255F * f1);
            b(u, "Saving level..", 8, r - 16, k << 16 | k << 8 | k);
        }
        a(u, "Game menu", q / 2, 40, 0xffffff);
        super.a(i, j, f);
    }
}
