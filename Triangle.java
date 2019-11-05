/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

/**
 *
 * @author beomj
 */
public class Triangle extends Shape{

        int[] Xpoints = new int[3];
        int[] Ypoints = new int[3];

     Triangle(int x1, int y1, int xPos, int yPos ){
        posXM = xPos;
        posYM = yPos;
        sizeX = x1;
        sizeY = y1;
        clicked = false;
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        //boundingBox calculation

        double r1 = Math.sqrt(Math.pow(sizeX/2, 2) + Math.pow(sizeY/3,2)) ;
        double r2 = (double) sizeY/3 * 2;
        double alpha = Math.atan(((double)sizeY/3)/((double)sizeX/2));
        
        Xpoints[0] = posXM + (int) (r1 * Math.cos(Math.PI + alpha + theta));
        Xpoints[1] = posXM + (int) (r2 * Math.cos(Math.PI/2 + theta));
        Xpoints[2] = posXM + (int) (r1 * Math.cos(-alpha + theta));
        Ypoints[0] = posYM - (int) (r1 * Math.sin(Math.PI + alpha + theta));
        Ypoints[1] = posYM - (int) (r2 * Math.sin(Math.PI/2 + theta));
        Ypoints[2] = posYM - (int) (r1 * Math.sin(-alpha + theta));
        
        int minX = Xpoints[2];
        int maxX = Xpoints[2];
        int minY = Ypoints[2];
        int maxY = Ypoints[2];
        
        for(int i = 0; i< 2; i++){
            minX = Math.min(minX, Xpoints[i]);
            maxX = Math.max(maxX, Xpoints[i]);
            minY = Math.min(minY, Ypoints[i]);
            maxY = Math.max(maxY, Ypoints[i]);
        }
        
        boundingBox[0] = maxX - minX;
        boundingBox[1] = maxY - minY;
        boundingPosition[0] = (int) (minX + maxX)/2 ;
        boundingPosition[1] = (int) (minY + maxY)/2 ;
        
        
        //삼각형 그리기
        
        Xpoints[0] = posXM - (int) sizeX/2;
        Xpoints[1] = posXM;
        Xpoints[2] = posXM + (int) sizeX/2;
        Ypoints[0] = posYM + (int) sizeY/3;
        Ypoints[1] = posYM - (int) sizeY/3*2;
        Ypoints[2] = posYM + (int) sizeY/3;
        
         g2d.translate(posXM,posYM);
         g2d.rotate(-theta);
         g2d.translate(-posXM, -posYM);

        
        //다각형 색칠
        Polygon p = new Polygon(Xpoints, Ypoints, 3);
        if(color == null){

            g2d.setColor(Color.BLACK);
            g2d.drawPolygon(p);
        }
        else{
            g2d.setColor(color);
            g2d.fillPolygon(p);
        }
        
        
        if(clicked){
             g2d.setColor(Color.red);
             p.xpoints[0] = posXM - (int) sizeX/2 - 5 ;
             p.xpoints[1] = posXM;
             p.xpoints[2] = posXM + (int) sizeX/2 + 5;
             p.ypoints[0] = posYM + (int) sizeY/3 + 5;
             p.ypoints[1] = posYM - (int) sizeY/3*2 -5;
             p.ypoints[2] = posYM + (int) sizeY/3 + 5;
        
                g2d.drawPolygon(p);
             }

        
        g2d.translate(posXM,posYM);
        g2d.rotate(+theta);
        g2d.translate(-posXM, -posYM);
        super.rotateSymbol(g2d);
        
    }

}
