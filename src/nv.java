// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode fieldsfirst noconv 
// Source File Name:   SourceFile

import java.util.List;
import net.minecraft.client.Minecraft;

public class nv extends aag
{

	private int a;
	private int b;

	public nv()
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
		s.add(new ahg(1, q / 2 - 100, r / 4 + 120 + byte0, dy.a("menu.returnToMenu")));
		if(p.z())
		{
			((ahg)s.get(0)).e = dy.a("menu.disconnect");
		}
		s.add(new ahg(4, q / 2 - 100, r / 4 + 24 + byte0, dy.a("menu.returnToGame")));
		s.add(new ahg(5, q / 2 - 100, r / 4 + 48 + byte0, 98, 20, dy.a("gui.achievements")));
		s.add(new ahg(6, q / 2 + 2, r / 4 + 48 + byte0, 98, 20, dy.a("gui.stats")));
        s.add(new ahg(0, q / 2 - 100, r / 4 + 96 + byte0, 98, 20, dy.a("menu.options")));
        s.add(new ahg(7, q / 2 + 2, r / 4 + 96 + byte0, 98, 20, "WMLL Options..."));
		if (WMLL.debugClassPresent)
			s.add(new ahg(9001, q - 52, r - 22, 50, 20, "Reload"));
	}

	protected void a(ahg ahg1)
	{
		switch(ahg1.f)
		{
		case 7:
			p.a(new WMLLOptions(this));
			break;
		case 9001:
			p.a(new nv());
		case 0: // '\0'
			p.a(new cz(this, p.z));
			break;

		case 1: // '\001'
			p.I.a(iz.j, 1);
			p.f.f();
			p.a((lr)null);
			p.a(new acy());
			break;

		case 4: // '\004'
			p.a((aag)null);
			p.g();
			break;

		case 5: // '\005'
			p.a(new vk(p.I));
			break;

		case 6: // '\006'
			p.a(new eg(this, p.I));
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
