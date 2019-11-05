/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.Calendar;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author beomj
 */
public class Frame extends JFrame{
    
    static int xSize = 1200;
    static int ySize = 1000;
    view drawingPanel;
    JPanel rightPanel;
    JPanel upperPanel;
    JButton[] rightButtons = new JButton[7];
    JButton[] upperButtons = new JButton[5];
    JMenuBar mBar = new JMenuBar();
    JMenu[] menuList = new JMenu[10];
    JMenuItem[] itemList = new JMenuItem[20];
    Color[] colorList = new Color[20];
    Calendar calendar = Calendar.getInstance();
    
    
    public Frame(view viewPanel) throws IOException{
        
        
        drawingPanel = viewPanel; //패널 만들기
        rightPanel = new JPanel();
        upperPanel = new JPanel();
        Font font = new Font("AppleGothic", Font.BOLD, 30); 
        Box rightBox = Box.createVerticalBox(); //right버튼은 box에 집어 넣기위해서
        JLabel label = new JLabel("BJ's Drawing ");
        label.setFont(font);
        upperPanel.add(label);
        
        font = new Font("고딕", Font.BOLD, 17); // 폰트 수정
         
        rightButtons[0] = new JButton("     ● Circle    ");
        rightButtons[1] = new JButton("   ▲ Triangle  ");
        rightButtons[2] = new JButton("  ■ Rectangle ");
        rightButtons[3] = new JButton("   ◆ diamond  ");
        rightButtons[4] = new JButton("     ㅡ Line       ");
        rightButtons[5] = new JButton("   사이즈조절   ");
        rightButtons[6] = new JButton("       회전         ");

        upperButtons[0] = new JButton(" 저장 ");
        upperButtons[1] = new JButton("불러오기");
        upperButtons[2] = new JButton(calendar.get(Calendar.HOUR_OF_DAY) +" : " + calendar.get(Calendar.MINUTE) );
        upperButtons[3] = new JButton(" 햇빛 ");
        upperButtons[4] = new JButton("마우스불빛");


        menuList[0] = new JMenu("         "); //색깔을 넣을 메뉴 생성
        menuList[0].setOpaque(true);
        menuList[0].setBackground(Color.BLACK);
        menuList[0].setSize(15,15);
        colorList[0] = Color.black;
        colorList[1] = Color.white;
        colorList[2] = Color.blue;
        colorList[3] = Color.GREEN;
        colorList[4] = Color.ORANGE;
        colorList[5] = Color.RED;
        colorList[6] = Color.cyan;
        colorList[7] = Color.YELLOW;
        colorList[8] = Color.gray;
        colorList[9] = Color.pink;
        
        for(int i = 10; i<20 ; i++ ){ // 10가지 렌덤색깔 만들기
        colorList[i] = Color.getHSBColor((float)i/20, (float)i/20,(float)i/20);
        }
        
        for(int i = 0; i< 20 ; i++){ //메뉴에 집어 넣기
            itemList[i] = new JMenuItem(); 
             menuList[0].add(itemList[i]);
             itemList[i].setBackground(colorList[i]);
        }
        itemList[19].setBackground(Color.white); // 마지막은 채우기X로
        itemList[19].setText("채우기x");

        

         // 생성 끝. 버튼 집어넣기 
        mBar.add(menuList[0]);
        
        for (JButton rightButton : rightButtons) {
            rightButton.setPreferredSize(new Dimension(150,50));
            rightButton.setFont(font);
            rightButton.setBackground(Color.WHITE);
            rightBox.add(rightButton);
            rightBox.add(Box.createVerticalGlue());
            rightBox.add(Box.createVerticalStrut(15));
        }
        
        
        for (JButton upperButton : upperButtons) {
            upperButton.setPreferredSize(new Dimension(140,38));
            upperButton.setBackground(Color.WHITE);
            upperButton.setForeground(Color.BLACK);
            upperButton.setFont(font);
            upperPanel.add(upperButton);
        }
        
        //오른쪽, 위쪽 레이블 세팅
        
        rightPanel.setBackground(Color.yellow);
        upperPanel.setBackground(Color.getHSBColor((float)0.5,(float)0.5,(float)0.9)); 
        
        upperPanel.add(mBar);
      
        
        this.menuList[0].setPreferredSize(new Dimension(100,30));
        this.menuList[0].setText("");
        this.menuList[0].setBackground(Color.black);
        
        rightPanel.add(rightBox);
        drawingPanel.cal = calendar;
        
         
        
        
         this.go();
    }
    
    public void go(){
        
        // frame에 넣기
        this.getContentPane().add(BorderLayout.CENTER, drawingPanel);
        this.getContentPane().add(BorderLayout.EAST, rightPanel); //Frame에 넣기
        this.getContentPane().add(BorderLayout.NORTH, upperPanel);
      
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setSize(xSize,ySize);
        this.setVisible(true);
        
    }

}
