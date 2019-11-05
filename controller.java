/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


/**
 *
 * @author beomj
 */
public class controller {
    
    boolean flags[] = new boolean[10];     //flag list는 ppt파일참조
    Frame frame;
    Shape clickedShape;
    Color fillColor = Color.BLACK;
    int[] ShapePositionDff = new int[2];
    int colorIndex;
    int fileOutputHelper;
    boolean mousePressed;
    double rotateSpeed = 0.015; 
    

    
    
    public controller(Frame f){
        frame = f;
        this.Control();
        flags[0] = true;
    }
    
    
    
    public void Control(){
        
        frame.drawingPanel.addMouseListener(new MouseListener1());
        frame.drawingPanel.addMouseMotionListener(new MouseMotionListener1());

        
        frame.rightButtons[0].addActionListener(new ActionListener(){   // 원만들기
            @Override
            public void actionPerformed(ActionEvent e) {
                if(clickedShape != null){
                    clickedShape.RotateFlag = false;
                    clickedShape.SizeFlag = false;
                    clickedShape.clicked = false;
                    clickedShape = null;
                }
                if(flags[5] == true){
                    frame.rightButtons[4].setBackground(Color.WHITE);
                }
                for(int i=0;i<8;i++){
                    flags[i] = false;
                }
                flags[1] = true;
            }     
        });
        
        frame.rightButtons[1].addActionListener(new ActionListener(){   // 삼각형만들기
            @Override
            public void actionPerformed(ActionEvent e) {
                if(clickedShape != null){
                    clickedShape.RotateFlag = false;
                    clickedShape.SizeFlag = false;
                    clickedShape.clicked = false;
                    clickedShape = null;
                }
                if(flags[5] == true){
                    frame.rightButtons[4].setBackground(Color.WHITE);
                }
                for(int i=0;i<8;i++){
                    flags[i] = false;
                }
                flags[2] = true;
            }
        });
        
        frame.rightButtons[2].addActionListener(new ActionListener(){   // 사각형만들기
            @Override
            public void actionPerformed(ActionEvent e) {
                if(clickedShape != null){
                    clickedShape.RotateFlag = false;
                    clickedShape.SizeFlag = false;
                    clickedShape.clicked = false;
                    clickedShape = null;
                }
                if(flags[5] == true){
                    frame.rightButtons[4].setBackground(Color.WHITE);
                }
                for(int i=0;i<8;i++){
                    flags[i] = false;
                }
                flags[3] = true;
            }
        });
        
        frame.rightButtons[3].addActionListener(new ActionListener(){   //마름모
            @Override
            public void actionPerformed(ActionEvent e) {
                if(clickedShape != null){
                    clickedShape.RotateFlag = false;
                    clickedShape.SizeFlag = false;
                    clickedShape.clicked = false;
                    clickedShape = null;
                }
                if(flags[5] == true){
                    frame.rightButtons[4].setBackground(Color.WHITE);
                }
                for(int i=0;i<8;i++){
                    flags[i] = false;
                }
                flags[4] = true;
            }
        });

        frame.rightButtons[4].addActionListener(new ActionListener(){   // r - 0 라인만들기
            @Override
            public void actionPerformed(ActionEvent e) {
                if(clickedShape != null){
                    clickedShape.RotateFlag = false;
                    clickedShape.SizeFlag = false;
                    clickedShape.clicked = false;
                    clickedShape = null;
                }
                if(flags[5] == false){
                    for(int i=0;i<8;i++){
                        flags[i] = false;
                    }
                    flags[5] = true;
                    frame.rightButtons[4].setBackground(Color.getHSBColor((float)0 ,(float)0.3, (float)1));
                }
                else{
                    flags[5] = false;
                    frame.rightButtons[4].setBackground(Color.WHITE);
                    flags[0] = true;
                }
            }
        });

        frame.rightButtons[5].addActionListener(new ActionListener(){   // r - 0 확대
            @Override
            public void actionPerformed(ActionEvent e) {
                if(flags[5] == true){
                    frame.rightButtons[4].setBackground(Color.WHITE);
                }
                for(int i=0;i<8;i++){
                    flags[i] = false;
                    
                }
                flags[6] = true;
                
                if(clickedShape != null){
                    clickedShape.RotateFlag = false;
                    clickedShape.SizeFlag = true;
                }
                frame.drawingPanel.repaint();
            }
        });
        
         frame.rightButtons[6].addActionListener(new ActionListener(){   //  회전
            @Override
            public void actionPerformed(ActionEvent e) {
                if(flags[5] == true){
                    frame.rightButtons[4].setBackground(Color.WHITE);
                }
                if(clickedShape != null){
                for(int i=0;i<8;i++){
                    flags[i] = false;
                }
                flags[7] = true;
                 clickedShape.RotateFlag = true;
                 clickedShape.SizeFlag = false;
                 frame.drawingPanel.repaint();
                }
                }  
        });
        
        for(int i=0;i<frame.itemList.length-1;i++){ /// 색깔 메뉴 버튼 1~19
            JMenuItem item = frame.itemList[i];
            colorIndex = i;
            item.addActionListener(new ActionListener(){
                Color c = frame.colorList[colorIndex];
                public void actionPerformed(ActionEvent e) {
                    fillColor = c;
                    frame.menuList[0].setPreferredSize(new Dimension(100,30));
                    frame.menuList[0].setText("");
                    frame.menuList[0].setBackground(fillColor);
                    if(clickedShape != null){
                        clickedShape.color = c;
                        frame.drawingPanel.repaint();
                    }
                 }
            });
        }
        
        JMenuItem item = frame.itemList[19]; //색깔 없애기 버튼에 액션리스너
            item.addActionListener(new ActionListener(){
                
                public void actionPerformed(ActionEvent e) {
                    fillColor = null;
                    frame.menuList[0].setText("채우기X");
                    frame.menuList[0].setBackground(null);
                    if(clickedShape != null){
                        clickedShape.color = null;   
                        if(clickedShape.getClass().toString().equals("class drawing.Line")){
                            clickedShape.color =Color.BLACK;
                        }
                        
                        frame.drawingPanel.repaint(); 
                    }

                 }
            });
                    
                    
        
        
        frame.upperButtons[0].addActionListener(new ActionListener(){   //  저장
            @Override
            public void actionPerformed(ActionEvent e) {
                    if(clickedShape != null){
                       clickedShape.RotateFlag = false;
                       clickedShape.SizeFlag = false;
                       clickedShape.clicked = false;
                       clickedShape = null;
                   }
                
                    FileOutputStream fos;
                    ObjectOutputStream oos;
                    String path = "D:\\Drawing\\Save-icon.png";
                    BufferedImage image = null;
                try {
                    image = ImageIO.read(new File(path));
                    ImageIcon icon = new ImageIcon(image);
                    
                    int result = JOptionPane.showConfirmDialog(null, "저장하시겠습니까?" , " 저장",JOptionPane.YES_NO_CANCEL_OPTION,1,icon);
                    if(result == JOptionPane.YES_OPTION){
                        String fileName = (String) JOptionPane.showInputDialog(null, "저장할 파일의 이름을 적어주세요..", "파일 저장" ,JOptionPane.INFORMATION_MESSAGE, icon, null,"");
                        
                        fos = new FileOutputStream("D:\\Drawing\\SaveFile\\"+ fileName + ".ser");
                        oos = new ObjectOutputStream(fos);  
                        oos.writeObject(frame.drawingPanel.list);
                        oos.close();
                        JOptionPane.showMessageDialog(null, "파일을 저장했습니다 ^.~ ","저장", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (FileNotFoundException ex) {
                } catch (IOException ex) {
                }                
            }
        });
        
        frame.upperButtons[1].addActionListener(new ActionListener(){   //  불러오기
            @Override
            public void actionPerformed(ActionEvent e) {
                
                FileInputStream fis;
                ObjectInputStream ois;
                Object readObject;
                
                JFileChooser fc = new JFileChooser(); // directory 탐색
                File directoryPath = new File("D:\\Drawing\\SaveFile");
                fc.setCurrentDirectory(directoryPath);

                    int ret = fc.showOpenDialog(null);
                    if(ret != JFileChooser.APPROVE_OPTION){return;}
                    String fileName = fc.getSelectedFile().toString();
                try {
                    fis = new FileInputStream(fileName);
                    ois = new ObjectInputStream(fis);
                    readObject = ois.readObject();
                    frame.drawingPanel.list = (ArrayList<Shape>) readObject;
                    frame.repaint(); 
                    JOptionPane.showMessageDialog(null, "파일을 불러왔습니다.");
                    
                } catch (FileNotFoundException ex) {
                    System.out.println("no");
                } catch (IOException | ClassNotFoundException ex) {
                }

            }
        });
            
            frame.upperButtons[2].addMouseListener(new MouseAdapter() {
             public void mousePressed(MouseEvent e) {
                mousePressed = true;
                new Thread() {
                public void run() {
                    while (mousePressed) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(controller.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    frame.calendar.set(Calendar.MINUTE, frame.calendar.get(Calendar.MINUTE) +1);
                    frame.upperButtons[2].setText(frame.calendar.get(Calendar.HOUR_OF_DAY)+ " : " + frame.calendar.get(Calendar.MINUTE));
                    frame.repaint();
                    
                    }
                }

            }.start();
            }

                    
                
            public void mouseReleased(MouseEvent e) {
                mousePressed = false;
            } 
            });
       
            frame.upperButtons[3].addActionListener(new ActionListener(){   //  LightImage 실행
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(!flags[8]){
                        flags[8] = true;
                        frame.drawingPanel.lightFlag  = true;
                        frame.repaint();
                        rotateSpeed = 0.08; //Graphic 처리로 회전 속도 느려지므로 회전속도 증가
                    }
                    else{
                        flags[8] = false;
                        flags[9] = false; //마우스 불빛도 같이 끄기
                        frame.drawingPanel.lightFlag = false;
                        frame.drawingPanel.light.mouseFlag = false;
                        frame.repaint();
                        rotateSpeed = 0.015; // 회전 속도 초기화
                    }
                }
            });
            
            frame.upperButtons[4].addActionListener(new ActionListener(){   //  마우스 불빛
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(flags[8]){ //햇빛이 있을 때만 작동
                        if(!flags[9]){
                            flags[9] = true;
                            frame.drawingPanel.light.mouseFlag = true;
                        }
                        else{
                            frame.drawingPanel.light.mouseFlag = false;
                            flags[9] = false;
                            frame.drawingPanel.repaint();
                        }
                    }
                }
            });
    }

    
    public class MouseListener1 implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            if(flags[0] == true){ // 클릭된 위치의 도형 찾기
                
                if(clickedShape != null){ //기존 클릭된 도형의 클릭된 상태 해제 
                    clickedShape.clicked  =false;
                    clickedShape = null;
                    }
                
                
                if(frame.drawingPanel.list.size() >0){ //도형이 그려져 있을 때만 도형 찾기
                    int i = frame.drawingPanel.list.size() - 1; 
                    Shape temp; // 마지막 도형 선택
                    boolean isMouseInShape;
    
                    while(i>=0){
                            
                            temp = frame.drawingPanel.list.get(i);
                            if(temp.getClass().toString().equals("class drawing.Line")){  // 도형이 선일 경우
                                Line line = (Line) temp;
                                for(int k = 0; k< line.x.size();k++){
                                    if((Math.abs(line.x.get(k) - e.getX()) <15)&&(Math.abs(line.y.get(k) - e.getY()))<15){
                                        clickedShape = temp;
                                        clickedShape.clicked = true;
                                        break;
                                    }
                                }
                                if(clickedShape != null){
                                    break;  
                                }
                            }
                            else{ //선이 아닐 경우
                                isMouseInShape = (Math.abs(e.getX() - temp.boundingPosition[0])< temp.boundingBox[0]/2 && Math.abs(e.getY() - temp.boundingPosition[1]) < temp.boundingBox[1]/2); // boundingBox 안에 있는 지 확인
                                if(isMouseInShape){
                                    clickedShape = temp;
                                    clickedShape.clicked = true;
                                    break;
                                }
                            }
                            i = i-1;
                        }
                    
                     }
                    }
                frame.drawingPanel.repaint();
                }
  

        @Override
        public void mousePressed(MouseEvent e) {
            if(flags[0]){
                if(clickedShape != null){
                    ShapePositionDff[0] = e.getX() - clickedShape.posXM;
                    ShapePositionDff[1] = e.getY() - clickedShape.posYM;
                }
            }
            
             if(flags[1]){ // 원  
                frame.drawingPanel.list.add(new Circle(10,10 , e.getX(),e.getY()));
                frame.drawingPanel.list.get(frame.drawingPanel.list.size()-1).color = fillColor;
            }
             
            if(flags[2]){ // 삼각형
                frame.drawingPanel.list.add(new Triangle(10,10 , e.getX(),e.getY()));
                frame.drawingPanel.list.get(frame.drawingPanel.list.size()-1).color = fillColor;
            }
            
             if(flags[3]){ // 사각형
                frame.drawingPanel.list.add(new Rectangle(10,10 , e.getX(),e.getY()));
                frame.drawingPanel.list.get(frame.drawingPanel.list.size()-1).color = fillColor;
            }
             if(flags[4]){ // 마름모
                frame.drawingPanel.list.add(new Diamond(10,10 , e.getX(),e.getY()));
                frame.drawingPanel.list.get(frame.drawingPanel.list.size()-1).color = fillColor;
            }
             if(flags[5]){ // 선그리기
                frame.drawingPanel.list.add(new Line( e.getX(),e.getY()));
                frame.drawingPanel.list.get(frame.drawingPanel.list.size() -1).color = fillColor;
                if(fillColor == null){
                     frame.drawingPanel.list.get(frame.drawingPanel.list.size() -1).color = Color.BLACK;
                }
            }
            if(flags[6]){ // 사이즈
                if(clickedShape != null){
                     ShapePositionDff[0] = e.getX();
                     ShapePositionDff[1] = e.getY(); 

                }
            }
            
             if(flags[7]){ // 회전  
                 ShapePositionDff[0] = e.getX();
                 ShapePositionDff[1] = e.getY(); 
             }
        }
        
        @Override
        public void mouseReleased(MouseEvent e) {
            
            if(flags[1] == true){ // 원
                Circle s = (Circle) frame.drawingPanel.list.get(frame.drawingPanel.list.size()-1);
                s.sizeRatio = ((double)s.sizeY)/((double)s.sizeX);
                flags[1] = false;
                flags[0] = true;
               }
            
             if(flags[2] == true){ // 삼각
                Triangle s = (Triangle) frame.drawingPanel.list.get(frame.drawingPanel.list.size()-1);
                s.sizeRatio = ((double)s.sizeY)/((double)s.sizeX);
                flags[2] = false;
                flags[0] = true;
               }
             
             if(flags[3] == true){ // 사각
                Rectangle s = (Rectangle) frame.drawingPanel.list.get(frame.drawingPanel.list.size()-1);
                s.sizeRatio = ((double)s.sizeY)/((double)s.sizeX);
                flags[3] = false;
                flags[0] = true;
               }
             
             if(flags[4] == true){ // 마름
                Diamond s = (Diamond) frame.drawingPanel.list.get(frame.drawingPanel.list.size()-1);
                s.sizeRatio = ((double)s.sizeY)/((double)s.sizeX);
                flags[4] = false;
                flags[0] = true;
               }
             
   
             
            if(flags[6] == true){ //  사이즈
                flags[6] = false;
                if(clickedShape != null){
                    clickedShape.SizeFlag = false;
                }
                flags[0] = true;                
            }
                   
            if(flags[7] == true){ // 회전
                if(clickedShape != null){
                flags[7] = false;
                clickedShape.RotateFlag = false;
                flags[0] = true; 
                }
            }

        
        }

        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) { }
        
    }
    
    public class MouseMotionListener1 implements MouseMotionListener{

        @Override
        public void mouseDragged(MouseEvent e) {
            if(flags[0]){ // 이동
                if(clickedShape != null){
                    
                    if(!clickedShape.getClass().toString().equals("class drawing.Line")){
                        clickedShape.posXM = e.getX() -  ShapePositionDff[0];
                        clickedShape.posYM = e.getY() -  ShapePositionDff[1];

                        frame.drawingPanel.repaint();
                        }
                    else{
                        
                        Line line = (Line) clickedShape;
                        
                        int tempX =  clickedShape.posXM;
                        int tempY =  clickedShape.posYM;
                        clickedShape.posXM = e.getX() - ShapePositionDff[0];
                        clickedShape.posYM = e.getY() - ShapePositionDff[1];

                        for(int i=0; i<line.x.size() ;i++){
                            line.x.set(i,line.x.get(i) + clickedShape.posXM - tempX);
                            line.y.set(i,line.y.get(i) + clickedShape.posYM - tempY);
                        }

                        frame.drawingPanel.repaint();
                        
                    }
                }
            }
            
             if(flags[1] == true){ // 원
             frame.drawingPanel.list.get(frame.drawingPanel.list.size() - 1).sizeX = Math.abs( e.getX() -  frame.drawingPanel.list.get(frame.drawingPanel.list.size() - 1).posXM);
             frame.drawingPanel.list.get(frame.drawingPanel.list.size() - 1).sizeY = Math.abs( e.getY() -  frame.drawingPanel.list.get(frame.drawingPanel.list.size() - 1).posYM);
             frame.drawingPanel.repaint();

             
            }
             if(flags[2] == true){ // 삼각
             frame.drawingPanel.list.get(frame.drawingPanel.list.size() - 1).sizeX = Math.abs( e.getX() -  frame.drawingPanel.list.get(frame.drawingPanel.list.size() - 1).posXM);
             frame.drawingPanel.list.get(frame.drawingPanel.list.size() - 1).sizeY = Math.abs( e.getY() -  frame.drawingPanel.list.get(frame.drawingPanel.list.size() - 1).posYM);
             frame.drawingPanel.repaint();

            }
             
             if(flags[3] == true){ // 사각
             frame.drawingPanel.list.get(frame.drawingPanel.list.size() - 1).sizeX = Math.abs( e.getX() -  frame.drawingPanel.list.get(frame.drawingPanel.list.size() - 1).posXM);
             frame.drawingPanel.list.get(frame.drawingPanel.list.size() - 1).sizeY = Math.abs( e.getY() -  frame.drawingPanel.list.get(frame.drawingPanel.list.size() - 1).posYM);
             frame.drawingPanel.repaint();

            }
             
             if(flags[4] == true){ // 마름
             frame.drawingPanel.list.get(frame.drawingPanel.list.size() - 1).sizeX = Math.abs( e.getX() -  frame.drawingPanel.list.get(frame.drawingPanel.list.size() - 1).posXM);
             frame.drawingPanel.list.get(frame.drawingPanel.list.size() - 1).sizeY = Math.abs( e.getY() -  frame.drawingPanel.list.get(frame.drawingPanel.list.size() - 1).posYM);
             frame.drawingPanel.repaint();

            }
        
            if(flags[5]){ // 선그리기
                Line line = (Line) frame.drawingPanel.list.get(frame.drawingPanel.list.size() - 1);
                
                if(e.getX() <line.x.get(line.x.size() - 1)){ line.posXM = e.getX(); }
                if(e.getY() <line.y.get(line.y.size() - 1)){ line.posYM = e.getY(); }
                if(Math.abs(e.getX() - line.posXM) > line.sizeX){ line.sizeX = Math.abs(e.getX() - line.posXM);}
                if(Math.abs(e.getY() - line.posYM) > line.sizeY){ line.sizeY = Math.abs(e.getY() - line.posYM);}
                
                line.x.add(e.getX());
                line.y.add(e.getY());
                
                frame.drawingPanel.repaint();  
                }

            if(flags[6]){ // 사이즈
                if(clickedShape != null){
                    double dff = Math.pow(e.getX() -clickedShape.posXM,2) +Math.pow(e.getY() - clickedShape.posYM,2) 
                            - Math.pow(ShapePositionDff[0] - clickedShape.posXM,2) - Math.pow(ShapePositionDff[1]- clickedShape.posYM,2);
                    if(dff>=0){
                                double sizeWeight =  1 + 2/ (double)Math.min(clickedShape.sizeX,clickedShape.sizeY);
                                clickedShape.sizeX = (int) ((double)clickedShape.sizeX *sizeWeight);
                                clickedShape.sizeY = (int) ((double)clickedShape.sizeX * clickedShape.sizeRatio);
                                
                                if(flags[8]){ // Graphic 처리에 의한 속도저하 보완
                                    clickedShape.sizeX = (int) ((double)clickedShape.sizeX *1.05*sizeWeight);
                                clickedShape.sizeY = (int) ((double)clickedShape.sizeX * clickedShape.sizeRatio);
                                }
                    }
                    else{ // 축소
                        if((clickedShape.sizeX>10 && clickedShape.sizeY>10)){ // minimum size = 5
                            clickedShape.sizeX = (int) ((double)clickedShape.sizeX *0.985);
                            clickedShape.sizeY = (int) ((double)clickedShape.sizeY *0.985);    
                        }
                        else{
                            if(clickedShape.sizeX<10 && clickedShape.sizeY>10){
                                clickedShape.sizeY = (int) ((double)clickedShape.sizeY *0.985);  
                            }
                            else{
                                clickedShape.sizeX = (int) ((double)clickedShape.sizeX *0.985);
                            }
                        }
                        if(flags[8]){ // Graphic 처리에 의한 속도저하 보완
                                 clickedShape.sizeX = (int) ((double)clickedShape.sizeX *0.955);
                                 clickedShape.sizeY = (int) ((double)clickedShape.sizeY *0.955);
                                }
                       if(clickedShape.sizeX <= 0){
                           clickedShape.sizeX  = 1;
                       }
                       if(clickedShape.sizeY <= 0 ){
                           clickedShape.sizeY = 1;
                       }
                    }
                   frame.drawingPanel.repaint();
                   ShapePositionDff[0] = e.getX();
                   ShapePositionDff[1] = e.getY();
                }       
            }


            if(flags[7]){ //회전
                if(clickedShape != null){

                    double startPoint = Math.atan((double)(e.getY()-clickedShape.posYM)/ (double)(e.getX()-clickedShape.posXM));
                    double endPoint   = Math.atan((double)(ShapePositionDff[1]-clickedShape.posYM) / (double)(ShapePositionDff[0]-clickedShape.posXM));  
                    if(endPoint - startPoint >0){
                    clickedShape.theta =  clickedShape.theta +  rotateSpeed;
                            }
                    else{
                        clickedShape.theta =  clickedShape.theta  - rotateSpeed;
                    }
                   ShapePositionDff[0] = e.getX() ;
                   ShapePositionDff[1] = e.getY() ;
                   frame.drawingPanel.repaint();
                   clickedShape.repaint();
                    }
                }
            
                if(flags[9] == true){ // 마우스 불빛
                        frame.drawingPanel.light.p = e.getPoint();
                        frame.drawingPanel.repaint();
                }
            }
        
        @Override
        public void mouseMoved(MouseEvent e) {
                if(flags[9] == true){ // 마우스 불빛
                    frame.drawingPanel.light.p = e.getPoint();
                    frame.drawingPanel.repaint();
            }
        }    
    }
}
