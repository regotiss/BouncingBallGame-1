package code;
import java.awt.Color;
import java.awt.Graphics;
import java.applet.Applet;
import java.util.Random;

class ScorePlus extends Item 
{
	
	Game ap;
	public ScorePlus(int x,Game a)
	{
		super(x);
		ap=a;
	} 

	public void actionPerform(Ball b)
	{
		Random r=new Random();
		ap.setScore(ap.getScore()+500+r.nextInt(2000));
		
	}
	public void paint(Graphics g)
	{
		g.setColor(Color.BLUE);
		super.paint(g);
	}
}
