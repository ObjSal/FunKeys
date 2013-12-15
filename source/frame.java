import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class frame extends JFrame implements KeyListener
{
	int speed=8;
	panel p = new panel();
	boolean exitGame=false;
	
	frame()
	{
		super("--FunKeys--");
		add(p);
		setSize(431,529);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(this);
		setResizable(false);
		setVisible(true);
		start();
	}
	
	void start()
	{
		while(!exitGame)
		{
			Thread.yield();
			p.repaint();
			try{Thread.sleep(speed);}catch(Exception e){}
		}
		System.exit(0);
	}
	
	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case 27:	exitGame=true;	break;//ESC
			case 40:	p.deleteArrow(1);	break;//downArrow
			case 37:	p.deleteArrow(2);	break;//leftArrow
			case 39:	p.deleteArrow(3);	break;//rightArrow
			case 38:	p.deleteArrow(4);	break;//UpArrow
		}
	}
	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case 27:	exitGame=true;	break;//ESC
			case 40:	p.deleteArrow(1);	break;//downArrow
			case 37:	p.deleteArrow(2);	break;//leftArrow
			case 39:	p.deleteArrow(3);	break;//rightArrow
			case 38:	p.deleteArrow(4);	break;//UpArrow
		}
		
		p.initData();
	}
	public void keyTyped(KeyEvent e)
	{}
}