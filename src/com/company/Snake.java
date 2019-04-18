package com.company;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyListener;

/*<applet code="Snake" width=500 height=500></applet>*/

// Copied from https://gist.github.com/neelendra1811/02669d998b52e8e20b3e7b10a3ac6d45

public class Snake extends Applet implements KeyListener,Runnable
{
    Thread t;
    int sx[]=new int[100];
    int sy[]=new int[100];
    int snalen=10;
    Color c;
    int n,x=10,y=0,px=300,py=190;


    public void init()
    {
        addKeyListener(this);
        c= new Color(150, 150, 150);
        setBackground(c);
        sx[0]=250;sx[1]=240;sx[2]=230;sx[3]=220;
        sy[0]=250;sy[1]=250;sy[2]=250;sy[3]=250;
    }

    public void start()
    {
        t= new Thread(this);
        t.start();
    }

    public void keyTyped(KeyEvent ke){}
    public void keyPressed(KeyEvent ke)
    {
        n=ke.getKeyCode();
        switch(n)
        {
            case KeyEvent.VK_UP:
            {
                x=0;
                y=-10;
                showStatus("Up key is pressed:");
                break;
            }
            case KeyEvent.VK_DOWN:
            {
                x=0;
                y=10;
                showStatus("Down key is pressed:");
                break;
            }
            case KeyEvent.VK_LEFT:
            {
                x=-10;
                y=0;
                showStatus("Left key is pressed:");
                break;
            }
            case KeyEvent.VK_RIGHT:
            {
                x=10;
                y=0;
                showStatus("Right key is pressed:");
                break;
            }
        }
    }
    public void keyReleased(KeyEvent ke){}

    public void run()
    {
        go:while(true)
        {
            try
            {
                Thread.sleep(250-snalen*5);
                repaint();

                if(!(sx[0]>=6 && sx[0]<=489 && sy[0]>=6 && sy[0]<=489))
                {
                    break go;
                }
                for(int i=1;i<snalen;i++)
                {
                    if(sx[0]==sx[i] && sy[0]== sy[i])
                    {break go;}
                }
            }
            catch(InterruptedException e){}
        }

    }

    public void paint(Graphics g)
    {
        c=new Color(90,10,90);
        g.setColor(c);
        g.fillRect(5,5,490,490);			//frame

        c= new Color(200,200,200);
        g.setColor(c);

        for(int i=0;i<snalen;i++)
        {
            g.fillRect(sx[i],sy[i],8,8);   //snake
        }

        for(int i=snalen-2;i>=0;i--)
        {
            sx[i+1]=sx[i];
            sy[i+1]=sy[i];
        }
        sx[0]=sx[0]+x;
        sy[0]=sy[0]+y;

        g.fillRect(px,py,8,8);		//food

        if(sx[0]==px && sy[0]==py)
        {
            px=(int) Math.ceil(Math.random()*(sx[0]+50));
            py=(int) Math.floor(Math.random()*(sy[0]+100));

            if(!(px%10==0 && py%10==0))
            {
                int e1,e2;
                e1=px%10;
                e2=py%10;

                px-=e1;
                py-=e2;
            }

            if(!(px>10 && px<485 && py>10 && py<485))
            {
                px=50;
                py=100;
            }
            snalen++;
            showStatus("Score: " +snalen*10);
        }

    }

}