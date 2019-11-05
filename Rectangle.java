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


public class Rectangle extends Shape {
    

    Rectangle(int x1, int y1, int xPos, int yPos ){
        posXM = xPos;
        posYM = yPos;
        sizeX = x1;
        sizeY = y1;
        //clicked = false;
        boundingBox[0] = sizeX;
        boundingBox[1] = sizeY;
        

    }
    @Override
    public void paintComponent(Graphics g){
        
        Graphics2D g2d = (Graphics2D) g;

        int Xpoints [] = new int[4];
        int Ypoints [] = new int[4];  
        double r = Math.sqrt(Math.pow(sizeX,2) + Math.pow(sizeY,2))/2;
        double alpha = Math.atan((double) sizeY/ (double) sizeX);

        Xpoints[0] = posXM + (int) (r*Math.cos(alpha + theta));
        Xpoints[1] = posXM + (int) (r*Math.cos(Math.PI - alpha + theta));
        Xpoints[2] = posXM + (int) (r*Math.cos(Math.PI + alpha + theta));
        Xpoints[3] = posXM + (int) (r*Math.cos(-alpha + theta));
        
        Ypoints[0] = posYM - (int) (r*Math.sin(alpha + theta));
        Ypoints[1] = posYM - (int) (r*Math.sin(Math.PI - alpha + theta));
        Ypoints[2] = posYM - (int) (r*Math.sin(Math.PI + alpha + theta));
        Ypoints[3] = posYM - (int) (r*Math.sin(-alpha + theta));
     
        
        //boundingBox 계산
        int minX = Xpoints[3];
        int maxX = Xpoints[3];
        int minY = Ypoints[3];
        int maxY = Ypoints[3];
        
        for(int i = 0; i< 3; i++){
            minX = Math.min(minX, Xpoints[i]);
            maxX = Math.max(maxX, Xpoints[i]);
            minY = Math.min(minY, Ypoints[i]);
            maxY = Math.max(maxY, Ypoints[i]);
        }
        boundingBox[0] = maxX - minX;
        boundingBox[1] = maxY - minY;
        boundingPosition[0] = posXM;
        boundingPosition[1] = posYM;
        
        //다각형 색칠
        Polygon p = new Polygon(Xpoints, Ypoints, 4);
        
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
                
                p.xpoints[0] = posXM + (int) ((r+10)*Math.cos(alpha + theta));
                p.xpoints[1] = posXM + (int) ((r+10)*Math.cos(Math.PI - alpha + theta));
                p.xpoints[2] = posXM + (int) ((r+10)*Math.cos(Math.PI + alpha + theta));
                p.xpoints[3] = posXM + (int) ((r+10)*Math.cos(-alpha + theta));

                p.ypoints[0] = posYM - (int) ((r+10)*Math.sin(alpha + theta));
                p.ypoints[1] = posYM - (int) ((r+10)*Math.sin(Math.PI - alpha + theta));
                p.ypoints[2] = posYM - (int) ((r+10)*Math.sin(Math.PI + alpha + theta));
                p.ypoints[3] = posYM - (int) ((r+10)*Math.sin(-alpha + theta));
                
                g2d.drawPolygon(p);
             }
        super.rotateSymbol(g2d);
        
        
    }
    
}
