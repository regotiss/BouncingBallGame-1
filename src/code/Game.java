//By Sujata Regoti
//Date: 31 Dec 2014
package code;
import java.applet.Applet;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
/*<applet code="Game.class" width=800 height=600></applet>*/
public class Game extends Applet implements Runnable, KeyListener,MouseMotionListener,MouseListener
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image i;
	private Graphics doubleG;
	Ball b;	
	PlatForm p[]=new PlatForm[7];
	Item it[]=new Item[3];
	int score;
	double cityX=0;
	double cityDx=.3;
	URL url;
	Image city;
	int levelCheck=0;
	boolean gameOver=false;
	boolean mouseIn=false;

	public void init()
	{
		
		setSize(800,600);
		addKeyListener(this);
		addMouseMotionListener(this);
		addMouseListener(this);
		try
		{
			url=getDocumentBase();
		}
		catch (Exception e)
		{
		}
		city=getImage(url,"images/city.jpg");
		Pictures p=new Pictures(this);
		Pictures.music.loop();
	}
	public int getScore()
	{
		return score;
	}
	public void setScore(int s)
	{
		score=s;
	}
	public void start()
	{

		score=0;
		b=new Ball();

		for(int i=0;i<p.length;i++)
		{
			//Random r=new Random();
			p[i]=new PlatForm(i*90,300);
		}
		for(int i=0;i<it.length;i++)
		{
			Random r=new Random();
			switch(r.nextInt(5))
			{
						case 0:
								it[i]=new GravUp(getWidth()+2000*i);
								break;
						case 1:
								it[i]=new GravDown(getWidth()+2000*i);
								break;

						case 2:
								it[i]=new AgilUp(getWidth()+2000*i);
								break;

						case 3:
								it[i]=new AgilDown(getWidth()+2000*i);
								break;
						case 4:
								it[i]=new ScorePlus(getWidth()+2000*i,this);
								break;
			}
			
		}
		
		Thread t=new Thread(this);
		t.start();

	}
	public void run()
	{
		while(true)
		{
			for(int i=0;i<p.length;i++)
			{
				int testx=p[i].getX();
				if(testx<0-p[i].getWidth())
				{
					Random r=new Random();
					int facki=i;
					if(i==0)
						facki=p.length;
					p[i].setX(p[facki-1].getX()+p[i].getWidth()+Pictures.level*r.nextInt(25));
				}
			}
			gameOver=b.getGameOver();
			if(levelCheck>1000)
			{
				Pictures.level++;
				levelCheck=0;
			}
			levelCheck++;
			if(cityX>getWidth()*-1)
			{
				cityX-=cityDx;
			}else
			{
				cityX=0;
			}
			if(!gameOver)
			score++;
			Random r=new Random();
			for(int i=0;i<it.length;i++)
			{
				if(it[i].isCreatNew())
				{
					it[i]=null;
					switch(r.nextInt(5))
					{
						case 0:
								it[i]=new GravUp(getWidth()+10*r.nextInt(500));
								break;
						case 1:
								it[i]=new GravDown(getWidth()+10*r.nextInt(500));
								break;

						case 2:
								it[i]=new AgilUp(getWidth()+10*r.nextInt(500));
								break;

						case 3:
								it[i]=new AgilDown(getWidth()+10*r.nextInt(500));
								break;
						case 4:
								it[i]=new ScorePlus(getWidth()+10*r.nextInt(500),this);
								break;
					}
					it[i].setCreatNew(false);
					
				}
			}
			b.update(this);

			for(int i=0;i<p.length;i++)
			p[i].update(this,b);

			for(int i=0;i<it.length;i++)
			it[i].update(this,b);
			
			repaint();
			try{
			Thread.sleep(17);
			}
			catch(Exception e){}
			
		}

	}
	public void stop()
	{
	
	}
	public void destroy()
	{
	
	}
	public void update(Graphics g)
	{
		if(i==null)
		{
			i=createImage(this.getSize().width,this.getSize().height);
			doubleG=i.getGraphics();
		}

		doubleG.setColor(getBackground());
		doubleG.fillRect(0,0,this.getSize().width,this.getSize().height);
		doubleG.setColor(getForeground());
		paint(doubleG);
		g.drawImage(i,0,0,this);
	}

	public void paint(Graphics g)
	{
		g.setColor(new Color(15,77,147));
		g.fillRect(0,0,getWidth(),getHeight());
		g.drawImage(city,(int)cityX,0,getWidth()*2,getHeight(),this);//
		b.paint(g);
		//b1.paint(g);
		for(int i=0;i<p.length;i++)
			p[i].paint(g);
		for(int i=0;i<it.length;i++)
			it[i].paint(g);
		String s=Integer.toString(score);
		Font f=new Font("Serif",Font.BOLD,32);
		g.setFont(f);
		g.setColor(Color.black);
		g.drawString(s,getWidth()-150-2,50-2);
		g.setColor(new Color(198,226,255));
		g.drawString(s,getWidth()-150,50);

		if(gameOver){
			
			g.setColor(Color.black);
			g.drawString("GAME OVER",300-2,300-2);
			g.setColor(Color.blue);

			g.drawString("GAME OVER",300,300);
			g.drawRect(290,320,210,40);
			if(mouseIn)
			{
				g.setColor(Color.red);
				g.drawString("Play Again?",310,350);
			}
			else
			{
				g.setColor(Color.white);
				g.drawString("Play Again?",310,350);
			}
			g.setColor(Color.black);
			String str="Your Score:"+Integer.toString(score);
			g.drawString(str,290-2,250-2);
			g.setColor(Color.cyan);		
			g.drawString(str,290,250);
		}
		
		
	}

	public void keyPressed(KeyEvent ke)
	{
		switch(ke.getKeyCode())
		{
			case KeyEvent.VK_LEFT:
						b.moveLeft();
						break;
			case KeyEvent.VK_RIGHT:
						b.moveRight();
						break;
		}
	}
	public void keyReleased(KeyEvent ke){}
	public void keyTyped(KeyEvent ke){}

	public void mouseDragged(MouseEvent me){}
	public void mouseMoved(MouseEvent e)
	{
		
		if(e.getX()>290&&e.getX()<400)
		{
			if(e.getY()>320&&e.getY()<360)
				mouseIn=true;
		}
		else
			mouseIn=false;
		/*if(e.getX()<290||e.getX()>400)
			mouseIn=false;
		if(e.getY()>320||e.getY()<360)
			mouseIn=false;*/
	}

	public void mouseClicked(MouseEvent me)
	{
		if(mouseIn)
		{
			b=null;
			b=new Ball();
			score=0;
			Pictures.level=1;
			for(int i=0;i<p.length;i++)
			{
				//Random r=new Random();
				p[i]=new PlatForm(i*90,300);
			}
			for(int i=0;i<it.length;i++)
			{
				Random r=new Random();
				switch(r.nextInt(5))
				{
							case 0:
									it[i]=new GravUp(getWidth()+2000*i);
									break;
							case 1:
									it[i]=new GravDown(getWidth()+2000*i);
									break;

							case 2:
									it[i]=new AgilUp(getWidth()+2000*i);
									break;

							case 3:
									it[i]=new AgilDown(getWidth()+2000*i);
									break;
							case 4:
									it[i]=new ScorePlus(getWidth()+2000*i,this);
									break;
				}
				
			}
			mouseIn=false;
		}
	}
	public void mouseEntered(MouseEvent me){}
	public void mouseExited(MouseEvent me)
	{}
	public void mousePressed(MouseEvent me)
	{}
	public void mouseReleased(MouseEvent me)
	{}

}