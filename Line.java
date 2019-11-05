/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author beomj
 */
public class Line extends Shape{
    
        
        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Integer> y = new ArrayList<>();

        
        Line(int xPos, int yPos){
        posXM = xPos;
        posYM = yPos;
        x.add(posXM);
        y.add(posYM);

         }
    
    
        @Override
        public void paintComponent(Graphics g){
       
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);

        //boundingBox calculation
        int minX = x.get(0);
        int maxX = x.get(0);
        int minY = y.get(0);
        int maxY = y.get(0);
        
        for(int i = 1; i<x.size(); i++){
            minX = Math.min(minX, x.get(i));
            maxX = Math.max(maxX, x.get(i));
            minY = Math.min(minY, y.get(i));
            maxY = Math.max(maxY, y.get(i));
            
        }
            
        boundingBox[0] = maxX - minX;
        boundingBox[1] = maxY - minY;
        boundingPosition[0] = (int) (maxX + minX)/2;
        boundingPosition[1] = (int) (maxY + minY)/2;

        
        //라인 그리기

       for(int i =0; i< x.size()-1; i++){
            g2d.drawLine(x.get(i), y.get(i),x.get(i+1), y.get(i+1));
            }
       
        RotateFlag = false; //선은 rotate불가
         super.rotateSymbol(g2d);
         
       }
       
        
        
    
}
