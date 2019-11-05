/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Calendar;
import javax.swing.JPanel;


public class Light {
    
    JPanel drawingPanel;
    BufferedImage bufferedImage;
    Calendar cal;
    Point p;
    boolean mouseFlag;
    
    public Light(JPanel pn ,Calendar calendar){ //객체 생성 시 그림판과 날짜를 받아와서 받은 정보를 이용해서 프로세스를 진행.
        drawingPanel = pn;
        drawingPanel.setSize(1200,1000);
        int w = drawingPanel.getBounds().width;
        int h = drawingPanel.getBounds().height;
        bufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        cal = calendar;
    }
    
    public BufferedImage go(){
        
        //buffered image에 JPanel을 그림
        Graphics2D g2d = bufferedImage.createGraphics(); 
        drawingPanel.paint(g2d);
        g2d.dispose();
        
        double t = (double)( cal.get(Calendar.HOUR_OF_DAY)) * 60 + (double) (cal.get(Calendar.MINUTE)); // 시간을 분으로 치환
        double weight = (1 - Math.cos(t* Math.PI *2 / 1440))/2; //cosine
        if(t<720){
            weight = Math.pow(weight, 2);
        }

        int rgb;
        int r;
        int g;
        int b;
        double radius;
        
        for(int x = 0; x< bufferedImage.getWidth();x++){
            for(int y= 0; y<bufferedImage.getHeight();y++){
                rgb  = bufferedImage.getRGB(x,y);
                if(mouseFlag){//마우스 불빛이 있을 때
                    radius = Math.sqrt(Math.pow(p.x -x ,2) + Math.pow(p.y - y, 2));
                    if(radius<220){
                        r = (int) (((double)((rgb>>16)&0x0ff))*  ((1-weight)/2 * Math.cos(Math.PI / 220 *radius) + (1+weight)/2)); // using cosine
                        g = (int) (((double)((rgb>>8) &0x0ff))* ((1-weight)/2 * Math.cos(Math.PI / 220 *radius) + (1+weight)/2));
                        b = (int) (((double)((rgb) &0x0ff))  * ((1-weight)/2 * Math.cos(Math.PI / 220 *radius) + (1+weight)/2));
                    }
                     else{
                         if(t>1020){ // 5시 이후로 빨강 강화
                            r = (int) (((double)((rgb>>16)&0x0ff)) * weight*1.035);}
                        else{
                            r = (int) (((double)((rgb>>16)&0x0ff)) * weight); }
                        g = (int) (((double)((rgb>>8) &0x0ff)) * weight);
                        b = (int) (((double)((rgb) &0x0ff))  * weight);          
                    }
                }
                
                else{ // 마우스불빛이 없을 때
                    if(t>1020){ // 5시 이후로 빨강 강화
                        r = (int) (((double)((rgb>>16)&0x0ff)) * weight*1.035); }
                    else{
                         r = (int) (((double)((rgb>>16)&0x0ff)) * weight);}
                        g = (int) (((double)((rgb>>8) &0x0ff)) * weight);
                        b = (int) (((double)((rgb) &0x0ff))  * weight);     
                }
                rgb = ((r&0x0ff)<<16)|((g&0x0ff)<<8)|(b&0x0ff);
                bufferedImage.setRGB(x, y, rgb); 
            }
        }
        return bufferedImage;  
    }
    
    
}
