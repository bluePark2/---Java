/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author beomj
 */
public  class Shape extends JPanel{
    
    Color color;
    int sizeX;
    int sizeY;
    int posXM;
    int posYM;
    int[] boundingBox = new int[2];
    int[] boundingPosition = new int[2];
    double theta;
    boolean clicked;
    boolean RotateFlag;
    boolean SizeFlag;
    double sizeRatio;


    @Override
    public void paintComponent(Graphics g){
 
    }
  
    
    public void rotateSymbol(Graphics2D g2d){
        
        if(RotateFlag){
                
                String path = "D:\\Drawing\\rotateImage.png";
                BufferedImage image;
                try {
                    image = ImageIO.read(new File(path));
                    g2d.translate(posXM, posYM);
                    g2d.rotate(-theta);
                    g2d.drawImage(image, 10,10, this);
                    g2d.rotate(theta);
                    g2d.translate(-posXM, -posYM);
                    } catch (IOException ex) {  
                        System.out.println("error");
                }
                g2d.setColor(Color.blue);// 중심표시
                g2d.fillOval(posXM-5, posYM-5, 10, 10);
            
            }
        if(SizeFlag){
            g2d.setColor(Color.RED);
            g2d.fillOval(posXM-5, posYM-5, 10, 10);
        }
        
         if(clicked){ //클릭이 되면 bounding box 그려줌
                g2d.setColor(Color.blue);
                g2d.translate(boundingPosition[0] -boundingBox[0]/2 -30, boundingPosition[1] - boundingBox[1]/2 -30);
                g2d.drawString("bbox", 20, 20);
                g2d.translate(-boundingPosition[0] +boundingBox[0]/2 +30, -boundingPosition[1] + boundingBox[1]/2 +30);
                g2d.drawRect(boundingPosition[0] -boundingBox[0]/2 -2, boundingPosition[1] - boundingBox[1]/2 -2, boundingBox[0]+2,boundingBox[1]+2);

               
                
             }
    }
    
}