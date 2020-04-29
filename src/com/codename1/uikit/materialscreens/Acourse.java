/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.materialscreens.entity.Classes;
import com.codename1.uikit.materialscreens.entity.Pupils;
import com.esprit.binder.utils.Statics;

/**
 *
 * @author wael
 */
public class Acourse extends Form{
    private Resources res = UIManager.initFirstTheme("/theme");

    private final Label l1,l2,l3,l4;
    private final TextField tfSub,tfCon;
    private final Container mainContainer;
    private final Button editBtn,removeBtn,backBtn;
    private Classes currentCourse;
    
    public Acourse(Integer id, String name, String session){
        
        this.setLayout(new BorderLayout());
        mainContainer = new Container();
        mainContainer.setLayout(new GridLayout(8,2));
        l1 = new Label("Course :");
        //l1.getUnselectedStyle().setAlignment(Component.CENTER);
        l1.getUnselectedStyle().setFgColor(-16777216);
        Font l1_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        l1.getUnselectedStyle().setFont(l1_font);
        l2 = new Label("subject:");
        tfSub = new TextField(name); 
        l3 = new Label("content:");
        tfCon = new TextField(session);
        l4 = new Label("teacher:");
        

        //l5 = new Label("ISBN:");
        //isbnTf= new TextField(isbn);
        editBtn= new Button("Edit");
        editBtn.getUnselectedStyle().setFgColor(5542241);
        removeBtn= new Button("Remove");
        removeBtn.getUnselectedStyle().setFgColor(5542241);
        mainContainer.add(l1);
        mainContainer.add(new Label());
        Statics.setLabelStyle(l2);
        mainContainer.add(l2);
        Statics.setLabelStyle(l3);
        mainContainer.add(l3);
        mainContainer.add(tfSub);
        mainContainer.add(tfCon);
        Statics.setLabelStyle(l4);
        mainContainer.add(l4);
        //Statics.setLabelStyle(l5);
        //mainContainer.add(l5);
       // mainContainer.add(tfTea);
        //mainContainer.add(isbnTf);
        mainContainer.add(editBtn);
        mainContainer.add(removeBtn);
        backBtn = Statics.createBackBtn(); 
        mainContainer.add(backBtn);
        currentCourse = new Classes(id, tfSub.getText(), tfCon.getText());

        this.add(BorderLayout.NORTH, mainContainer);
        //new HomeForm().setupSideMenu(id);
    }
    
}
