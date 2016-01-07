package code;
import java.awt.Color;
import java.util.Random;
import java.awt.*;
import java.applet.Applet;

public class PlatForm
{
	private int dx;
	private int x;
	private int y;
	private int width;
	private int height;
	private Image plat;
	private float frame=0;
	Applet sp;
	
	public PlatForm()
	{
		dx=-1;
		x=450;
		y=300;
		width=120;
		//plat=Pictures.platform;
		height=40;
	}
	public PlatForm(int a,int b)
	{
		dx=-1;
		x=a;
		y=b;
		width=120;
		//i=sp.getImage(sp.getDocumentBase(),"images/24.jpg");//
		height=40;
	}
	public void update(Applet sp,Ball b)
	{
		int tester=(int)(frame+.1);
		if(tester<3)
			frame+=.1;
		else
			frame=0;
		x+=-(Pictures.level);
	
		plat=sp.getImage(sp.getDocumentBase(),"images/back.jpg");//
		checkForCollision(b);
		if(x<0-width)
		{
			Random r=new Random();
			y=sp.getHeight()-40-r.nextInt(400);
			//x=sp.getWidth()+r.nextInt(300);
		}
		this.sp=sp;
		

	}
	public void checkForCollision(Ball b)
	{
		int ballX=b.getX();
		int ballY=b.getY();
		int radius=b.getRadius();
		if(ballY+radius>y&&ballY+radius<y+height)
		{
			if(ballX>x&&ballX<x+width)
			{
			double newDy=b.getGameDy();
			b.setY(y-radius);
			b.setDy(newDy);
			Pictures.bounce.play();
			}
		}
	}
	public void paint(Graphics g)
	{
		g.setColor(Color.BLUE);
		//g.fillRect(x,y,width,height);
		
		//g.drawImage(plat,x,y,Pictures.g);
		g.drawImage(plat,x,y,x+width,y+height,0,34*(int)frame,90,34*(int)frame+34,Pictures.ap); 
	}
	public int getX()
	{
		return x;
	}
	public void setX(int x)
	{
		this.x=x;
	}
	public int getWidth()
	{
		return width;
	}
	public void setWidth(int x)
	{
		this.width=x;
	}

}
