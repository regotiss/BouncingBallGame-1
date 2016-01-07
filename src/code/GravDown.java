package code;
import java.awt.Graphics;
import java.awt.Color;

class GravDown extends Item
{
	public GravDown(int x)
	{
		super(x);
	}
	public void paint(Graphics g)
	{
		g.setColor(Color.green);
		super.paint(g);
	}
	public void actionPerform(Ball b)
	{
		if(b.getGravity()>3)
		b.setGravity(b.getGravity()-3);
		if(b.getGravity()<3)
		{
			b.setGravity(3);
		}
	}
}
