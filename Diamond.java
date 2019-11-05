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
public class Diamond extends Shape {


    Diamond(int x1, int y1, int xPos, int yPos ){
        posXM = xPos;
        posYM = yPos;
        sizeX = x1;
        sizeY = y1;
        clicked = false;
        boundingBox[0] = sizeX;
        boundingBox[1] = sizeY;        
    }
    @Override
    public void paintComponent(Graphics g){
        
        Graphics2D g2d = (Graphics2D) g;

        int Xpoints [] = new int[4];
        int Ypoints [] = new int[4];  

        Xpoints[0] = posXM + (int) (sizeX*Math.cos(theta));
        Xpoints[1] = posXM + (int) (sizeY*Math.cos(Math.PI/2  + theta));
        Xpoints[2] = posXM + (int) (sizeX*Math.cos(Math.PI + theta));
        Xpoints[3] = posXM + (int) (sizeY*Math.cos(Math.PI*3/2 + theta));
        
        Ypoints[0] = posYM - (int) (sizeX*Math.sin(theta));
        Ypoints[1] = posYM - (int) (sizeY*Math.sin(Math.PI/2 + theta));
        Ypoints[2] = posYM - (int) (sizeX*Math.sin(Math.PI + theta));
        Ypoints[3] = posYM - (int) (sizeY*Math.sin(Math.PI*3/2 + theta));
     
        
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
        
        if(color == null){ //색이 없으면 선만 그리기
            g2d.setColor(Color.BLACK);
            g2d.drawPolygon(p);
        }
        else{ // 색이 있으면 채우기
            g2d.setColor(color);
            g2d.fillPolygon(p);
        }
        
        if(clicked){
                g2d.setColor(Color.red);
               
                p.xpoints[0] = posXM + (int) ((sizeX+5)*Math.cos(theta)) ;
                p.xpoints[1] = posXM + (int) ((sizeY+5)*Math.cos(Math.PI/2  + theta));
                p.xpoints[2] = posXM + (int) ((sizeX+5)*Math.cos(Math.PI + theta));
                p.xpoints[3] = posXM + (int) ((sizeY+5)*Math.cos(Math.PI*3/2 + theta));

                p.ypoints[0] = posYM - (int) ((sizeX+5)*Math.sin(theta));
                p.ypoints[1] = posYM - (int) ((sizeY+5)*Math.sin(Math.PI/2 + theta));
                p.ypoints[2] = posYM - (int) ((sizeX+5)*Math.sin(Math.PI + theta));
                p.ypoints[3] = posYM - (int) ((sizeY+5)*Math.sin(Math.PI*3/2 + theta));
                
                g2d.drawPolygon(p);
               
             }
        super.rotateSymbol(g2d);
        
        
    }
    
}
