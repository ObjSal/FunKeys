import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

class panel extends JPanel 
{
	private Image [][] _arrows= new Image[5][4];
	private int _score=0;
	private int _level=0;
	private int _y=0;
	private int line=1;
	private String note="note1";
	private int _state=state.INGAME;
	
	boolean upArrowDeleted=false;
	boolean leftArrowDeleted=false;
	boolean rightArrowDeleted=false;
	boolean downArrowDeleted=false;
	
	public Graphics _g=null;

	panel(){
		initArrows();
		setSize(425,500);
		nextArrows();
	}
	
	void initArrows()
	{
		for(int y=0;y<_arrows.length;y++)
			for(int x=0;x<_arrows[0].length;x++)
				_arrows[y][x]=null;
	}
	
	Image getArrow(int col)
	{
		Random rand = new Random();
        int color=1+rand.nextInt(4);
		
		switch(col)
		{
			case 1://column 1
				switch(color)
				{
					case 1://blue
						return img.LITTLE_DOWN_ARROW_B;
					case 2://green
						return img.LITTLE_DOWN_ARROW_G;
					case 3://red
						return img.LITTLE_DOWN_ARROW_R;
					case 4://yellow
						return img.LITTLE_DOWN_ARROW_Y;
				}
			case 2://column 2
				switch(color)
				{
					case 1://blue
						return img.LITTLE_LEFT_ARROW_B;
					case 2://green
						return img.LITTLE_LEFT_ARROW_G;
					case 3://red
						return img.LITTLE_LEFT_ARROW_R;
					case 4://yellow
						return img.LITTLE_LEFT_ARROW_Y;
				}
			case 3://column3
				switch(color)
				{
					case 1://blue
						return img.LITTLE_RIGHT_ARROW_B;
					case 2://green
						return img.LITTLE_RIGHT_ARROW_G;
					case 3://red
						return img.LITTLE_RIGHT_ARROW_R;
					case 4://yellow
						return img.LITTLE_RIGHT_ARROW_Y;
				}
			case 4://column4
				switch(color)
				{
					case 1://blue
						return img.LITTLE_UP_ARROW_B;
					case 2://green
						return img.LITTLE_UP_ARROW_G;
					case 3://red
						return img.LITTLE_UP_ARROW_R;
					case 4://yellow
						return img.LITTLE_UP_ARROW_Y;
				}
		}
		return null;
	}
	void deleteArrow(int col)
	{
		switch(col)
		{
			case 1:
				if(_arrows[4][0]!=null)
				{
					_arrows[4][0]=null;
					upArrowDeleted=true;
				}
				break;
			case 2:
				if(_arrows[4][1]!=null)
				{
					_arrows[4][1]=null;
					leftArrowDeleted=true;
				}
				break;
			case 3:
				if(_arrows[4][2]!=null)
				{
					_arrows[4][2]=null;
					rightArrowDeleted=true;
				}
				break;
			case 4:
				if(_arrows[4][3]!=null)
				{
					_arrows[4][3]=null;
					downArrowDeleted=true;
				}
				break;
		}
	}
	void addArrows(String str)
	{
		for(int i=0;i<str.length();i++)
		{
			char car=str.charAt(i);
			if(car=='X')
				//if(_arrows[0][i]==null)
					_arrows[0][i]=getArrow(i+1);
		}
	}
	void nextArrows()// throws Exception
	{
		//System.out.println("Next Arrows");
		//if(_delayTmp==_delay)
		{
			try
			{
			
				FileInputStream f = new FileInputStream(note);
				InputStreamReader input = new InputStreamReader(f);
				BufferedReader reader = new BufferedReader(input);
				int lines=Integer.parseInt(reader.readLine());
				
				if(lines==line)
					//_state=state.ENDGAME;
					line=1;
				
				for(int i=1;i<=lines;i++)
				{
					if(i<line)
						reader.readLine();
					else
					{
						addArrows(reader.readLine());
						break;
					}
				}
				
			}catch(Exception e){}
			line++;
			
			//return true;
		}
		//return false;
	}
	public void drawDust(Graphics g)
	{
		int x=-10;
		int y=380;
		
		if(upArrowDeleted)
				g.drawImage(img.DUST,x,y,null);
		x+=105;
		if(leftArrowDeleted)
			g.drawImage(img.DUST,x,y,null);
		x+=105;
		if(rightArrowDeleted)
			g.drawImage(img.DUST,x,y,null);
		x+=105;
		if(downArrowDeleted)
			g.drawImage(img.DUST,x,y,null);

	}
	public void paint(Graphics g)
	{
		_g=g;
		//System.out.println("Entre al paint");
		switch(_state)
		{
			case state.INGAME:
				
				//background
				g.drawImage(img.BACKGROUND,0,0,null);
				
				drawShadows(g);
				
				drawPlayerArrows(g);
				
				drawTopArrows(g);
				
				drawDust(g);
				
				g.setColor(Color.BLACK);
				g.fillRect(7,0,95,15);
				g.setColor(Color.WHITE);
				g.drawString("Score: "+_score+" Level:"+_level,10,10);
				break;
			case state.ENDGAME:
				g.drawImage(img.BACKGROUND,0,0,null);
				g.setColor(Color.BLACK);
				g.setFont(new Font( "Arial", Font.BOLD + Font.ITALIC, 20 ) );
				g.drawString("GAME OVER",125,250);
				break;
		}
	}
	void drawShadows(Graphics g)
	{
		int x=-10;
		int y=380;
		g.drawImage(img.SHADOW_1,x,y,null);
		x+=105;
		g.drawImage(img.SHADOW_2,x,y,null);
		x+=105;
		g.drawImage(img.SHADOW_3,x,y,null);
		x+=105;
		g.drawImage(img.SHADOW_4,x,y,null);
	}
	int getX(int x)
	{
		int x1=-10;
		int x2=x1+105;
		int x3=x2+105;
		int x4=x3+105;
		
		switch(x)
		{
			case 0:
				return x1;
			case 1:
				return x2;
			case 2:
				return x3;
			case 3:
				return x4;
		}
		return 0;
	}
	
	void drawGlow(Graphics g,int x)
	{
		int y=380;
		switch(x)
		{
			case 0:
				g.drawImage(img.GLOW_DOWN_ARROW,getX(x),y,null);
				break;
			case 1:
				g.drawImage(img.GLOW_LEFT_ARROW,getX(x),y,null);
				break;
			case 2:
				g.drawImage(img.GLOW_RIGHT_ARROW,getX(x),y,null);
				break;
			case 3:
				g.drawImage(img.GLOW_UP_ARROW,getX(x),y,null);
				break;
		}
	}

	void allOneDown()
	{
		for(int y=_arrows.length-1;y>=0;y--)
			for(int x=0;x<_arrows[0].length;x++)
				if(_arrows[y][x]!=null)
				{
					if(y==_arrows.length-1)
						_arrows[y][x]=null;
					
					else if(y<_arrows.length-1)
					{
						_arrows[y+1][x]=_arrows[y][x];
						_arrows[y][x]=null;
					}
				}
	}
	int getY(int y)
	{
		//if(_y==100)
		if(_y==500/_arrows.length)
			//y=((y*100)-17);
			y=((y*(500/_arrows.length))-17);
		else
			//y=((y*100)-17)+_y;
			y=((y*(500/_arrows.length))-17)+_y;
		return y;
	}
	void initData()
	{
		System.out.println("initData()");
		upArrowDeleted=false;
		leftArrowDeleted=false;
		rightArrowDeleted=false;
		downArrowDeleted=false;
	}
	void drawPlayerArrows(Graphics g)
	{
		//if(_y==100)
		if(_y==500/_arrows.length)
		{
			allOneDown();
			nextArrows();
			//initData();
		}

		for(int y=0;y<_arrows.length;y++)
			for(int x=0;x<_arrows[0].length;x++)
				if(_arrows[y][x]!=null)
				{
					if(y==_arrows.length-1)
						drawGlow(g,x);
					
					g.drawImage(_arrows[y][x],getX(x),getY(y),null);
				}
				
		//if(_y==100)
		if(_y==500/_arrows.length)
			_y=0;
		else
			_y++;
	}
	void drawTopArrows(Graphics g)
	{
		int x=-10;
		int y=380;
		g.drawImage(img.ARROW_1,x,y,null);
		x+=105;
		g.drawImage(img.ARROW_2,x,y,null);
		x+=105;
		g.drawImage(img.ARROW_3,x,y,null);
		x+=105;
		g.drawImage(img.ARROW_4,x,y,null);
	}
}