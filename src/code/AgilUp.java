package code;
import java.awt.Graphics;
import java.awt.Color;

class AgilUp extends Item
{
	public AgilUp(int x)
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
		if(b.getGravity()<8)
		b.setGravity(b.getGravity()+1);
		
	}
}
