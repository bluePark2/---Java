/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author beomj
 */
public class Drawing {


    public static void main(String[] args) throws IOException {
        ArrayList<Shape> list;
        list = new ArrayList<>();
        view v = new view(list);
        Frame frame = new Frame(v);
        controller control = new controller(frame);
 
    }
    
}
