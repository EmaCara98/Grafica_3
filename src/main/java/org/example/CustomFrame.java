package org.example;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class CustomFrame extends JFrame
{
    private String msg = "";
    private Date date;

    public String getMsg() {
        return msg;
    }

    public void setDate(Date date) {
        //this.msg = date.toString();
        this.date = date;

        SwingUtilities.invokeLater( () -> {
                    this.repaint();             //la repaint la richiamiamo quando swing è pronto a ristampare
                });
    }

    public void paint(Graphics g) {
        super.paint(g); // always call this first - BUT MAY flicker.. on repaint..
        int w = this.getWidth();
        int h = this.getHeight();
        int centerW=w/2;
        int centerH=h/2;
        int radius = w>h ? h/2 : w/2;

        int Secs = 0;
        if(this.date != null)
        {
            Secs =this.date.getSeconds();
        }
/*
        if (w < radius){
            radius = w;
        } else if (h < radius) {
            radius = h;
        }*/
        int originW = centerW - radius/2;
        int originH =  centerH - radius/2;
        //g.drawString(msg, 200, 50);
        //2PI/60=x/Secs =>
        double radiantiSecs = (2*Math.PI/60)*Secs-Math.PI/2;
        int Xsec=centerW+ (int)(radius/2*Math.cos(radiantiSecs));
        int Ysec=centerH+ (int) (radius/2*Math.sin(radiantiSecs));
        g.drawOval(originW,originH,radius,radius);
        g.drawLine(centerW,centerH,Xsec,Ysec);

        g.drawString("Seconds: "+Secs,200,80);
    }
}
