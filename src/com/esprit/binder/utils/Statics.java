/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.binder.utils;


import com.codename1.ui.Button;
import com.codename1.ui.Font;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;


/**
 *
 * @author wael
 */
public class Statics {
    public static void setLabelStyle(Label l){
        l.getUnselectedStyle().setFgColor(-16777216);
        l.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));
    }
     
     public static Button createBackBtn(){
         Button b=new Button("Back");
         b.getUnselectedStyle().setFgColor(5542241);
         b.addActionListener((ActionListener) new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent evt) {
                //.mainForm.show();
             }
         });
         return b;
     }
 
}
