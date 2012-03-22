import org.lwjgl.opengl.GL11;


// Coding is updated here but due to ImprovedChat's code requiring nq instad of 1.2.4's nt, it will error - iPeer


public class WMLLImprovedChat extends oo {
	
	public static final WMLLImprovedChat i = new WMLLImprovedChat();
	private static int fade = 0;

	public void run(boolean chatActive, nl localni, int i, int i3) {
		if(ImprovedChat.Current != null)
		{
			if(ImprovedChat.Current.tabs.size() > 1)
			{
				int dt = -312;
				int loc = 0;
				for(int ti = 0; ti <= ImprovedChat.Current.currentTabIndex; ti++)
					dt += 4 + ((Tab)ImprovedChat.Current.tabs.get(ti)).width;

				if(dt < 0)
					dt = 0;
				for(int ti = 0; ti < ImprovedChat.Current.tabs.size(); ti++)
				{
					Tab tab = (Tab)ImprovedChat.Current.tabs.get(ti);
					if(loc + 4 + tab.width > 312)
						break;
					if(loc >= dt)
					{
						if(ImprovedChat.Current.currentTabIndex == ti)
						{
							if(chatActive)
							{
								localni.a(tab.name, 2 + loc, -180, 0xffffff);
							} else
							{
								if(tab.blinking)
								{
									fade = 64;
									tab.blinking = false;
								}
								if(fade > 0)
								{
									localni.a(tab.name, 2 + loc, -180, (fade << 25) + 0xffffff);
									fade--;
								}
							}
						} else
							if(tab.blinking)
							{
								int op = i % 40 - 20;
								if(op < 0)
									op = -op;
								op = (int)((double)op * 12.75D);
								if(op == 0)
									op++;
								localni.a(tab.name, 2 + loc, -180, (op << 24) + 0xffffff);
							} else
								if(chatActive)
									localni.a(tab.name, 2 + loc, -180, 0x80ffffff);
						loc += 4 + tab.width;
					} else
					{
						dt -= 4 + tab.width;
					}
				}

			}
			for(int it = ImprovedChat.currentTab().chatScroll; it < ImprovedChat.currentTab().e.size() && it < i3 + ImprovedChat.currentTab().chatScroll; it++)
			{
				if(((nt)ImprovedChat.currentTab().e.get(it)).b >= 200 && !chatActive)
					continue;
				double d1 = (double)((nt)ImprovedChat.currentTab().e.get(it)).b / 200D;
				d1 = 1.0D - d1;
				d1 *= 10D;
				if(d1 < 0.0D)
					d1 = 0.0D;
				if(d1 > 1.0D)
					d1 = 1.0D;
				d1 *= d1;
				int i110 = (int)((double)ImprovedChat.historyOpacity * d1);
				if(chatActive)
					i110 = ImprovedChat.historyOpacity;
				if(i110 > 0)
				{
					int i120 = 2;
					int i131 = (-it + ImprovedChat.currentTab().chatScroll) * 9;
					String str2 = ((nt)ImprovedChat.currentTab().e.get(it)).a;
					a(i120, i131 - 1, i120 + 320, i131 + 8, (i110 << 24) + ImprovedChat.historyColor);
					GL11.glEnable(3042);
					localni.a(str2, i120, i131, 0xffffff);
				}
			}
		}
	}
}
