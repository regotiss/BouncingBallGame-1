package code;
import java.awt.Graphics;
import java.awt.Color;
public class GravUp extends Item 
{
	public GravUp(int x)
	{
		super(x);
	}
	public void paint(Graphics g)
	{
		g.setColor(Color.red);
		super.paint(g);
	}
	public void actionPerform(Ball b)
	{
		b.setGravity(b.getGravity()+3);
	}
}
