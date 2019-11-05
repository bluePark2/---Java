/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author beomj
 */

public class view extends JPanel{
    ArrayList<Shape> list = new ArrayList<>();
    Light light;
    boolean lightFlag;
    Calendar cal;
    
    public view(ArrayList<Shape> list_temp){
        this.
        list = list_temp;
        light = new Light(this,cal);
    }
    

    @Override
    public void paintComponent(Graphics g){
       Graphics2D g2d = (Graphics2D) g;
       
       super.paintComponent(g2d);
       super.setBackground(Color.white);
        for(int i=0; i<list.size();i++){
            list.get(i).paintComponent(g2d);
            }

        if(lightFlag){
            lightFlag = false;
            light.cal = cal;
            g2d.drawImage(light.go(),0,0, this);
            lightFlag = true;

        }
    }
}

    