/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


/**
 *
 * @author beomj
 */
public class Circle extends Shape {
    

    Circle(int x1, int y1, int xPos, int yPos ){
        posXM = xPos;
        posYM = yPos;
        sizeX = x1;
        sizeY = y1;
        clicked = false;

    }
    
    @Override
    public void paintComponent(Graphics g){
        
        int r = Math.max(sizeX, sizeY);
        boundingBox[0] =r;
        boundingBox[1] =r;
        boundingPosition[0] = posXM;
        boundingPosition[1] = posYM;
        
        //super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
         g2d.translate(posXM,posYM);
         g2d.rotate(-theta);
         g2d.translate(-posXM, -posYM);

        
        if(color == null){ //색이 없으면 선만 그리기
            g2d.setColor(Color.black);
            g2d.drawOval(posXM-sizeX/2,posYM-sizeY/2,sizeX,sizeY);
        }
        else{ // 색이 있으면 채우기
            g2d.setColor(color);
            g2d.fillOval(posXM-sizeX/2,posYM-sizeY/2,sizeX,sizeY);
        }
        
        if(clicked){ // 클릭되면 주위로 빨간 선 그리기
            g2d.setColor(Color.red);
            g2d.drawOval(posXM-sizeX/2 - 5, posYM-sizeY/2 -5, sizeX +10,sizeY+10);
        }
        
         g2d.translate(posXM,posYM);
         g2d.rotate(+theta);
         g2d.translate(-posXM, -posYM);
        
        super.rotateSymbol(g2d);

        
    }
    
    
}
