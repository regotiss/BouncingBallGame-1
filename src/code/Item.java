package code;
import java.awt.Color;
import java.util.Random;
import java.awt.Graphics;
import java.applet.Applet;

public class Item 
{
	private int x,y,radius,dx;
	private Applet sp;
	private boolean creatNew=false;
	public Item(int a)
	{
		dx=-2;
		radius=10;
		x=a;
		Random r=new Random();
		y=r.nextInt(400)+radius;
		
	}
	public void update(Applet sp,Ball b)
	{
		x+=dx;
		this.sp=sp;
		checkForCollision(b);
		if(x<0-radius)
		{
			Random r=new Random();
			x=sp.getWidth()+2000+r.nextInt(300);
		}
		

	}
	public void checkForCollision(Ball b)
	{
		int ballX=b.getX();
		int ballY=b.getY();
		int ballR=b.getRadius();
		
		int a=x-ballX;
		int bb=y-ballY;
		int collision=radius+ballR;
		
		double c=Math.sqrt((double)(a*a)+(double)(bb*bb));
		if(c<collision)
		{
			actionPerform(b);
			creatNew=true;
		}
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public int getDx()
	{
		return dx;
	}
	public boolean isCreatNew()
	{
		return creatNew;
	}
	public void setCreatNew(boolean b)
	{
		creatNew=b;
	}
	public int getRadius()
	{
		return radius;
	}
	public void setX(int x)
	{
		this.x=x;
	}
	public void setY(int x)
	{
		y=x;
	}
	public void setDx(int x)
	{
		dx=x;
	}
	
	public void actionPerform(Ball b)
	{
		
	}
	public void paint(Graphics g)
	{
		//g.setColor(Color.green);
		g.fillOval(x-radius,y-radius,radius+radius,radius+radius);
	}
}
