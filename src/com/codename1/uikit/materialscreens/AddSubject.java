/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;

import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.uikit.materialscreens.entity.Subject;
import com.codename1.uikit.materialscreens.service.SubjectDAO;
import static com.codename1.uikit.materialscreens.service.SubjectDAO.listOfSubjects;

import com.esprit.binder.utils.Statics;

/**
 *
 * @author wael
 */
public class AddSubject extends Form{
    private final Label l1,l2,l3,l4;
    private final TextField tfName,tfTeachers,tfClasses;
    private final Container mainContainer;
    private final Button addBtn,backBtn;
    
   public AddSubject(){
       
        this.setLayout(new BorderLayout());
        mainContainer = new Container();
        mainContainer.setLayout(new GridLayout(8,2));
        l1 = new Label("Add a new subject");
        l1.setAlignment(CENTER);
        //l1.getUnselectedStyle().setAlignment(Component.CENTER);
        l1.getUnselectedStyle().setFgColor(-16777216);
        Font l1_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        l1.getUnselectedStyle().setFont(l1_font);
        l2 = new Label("name:");
        tfName = new TextField(""); 
        l3 = new Label("teachers:");
        tfTeachers = new TextField("");
        l4 = new Label("classes:");
        tfClasses= new TextField("");
        //l5 = new Label("ISBN:");
        //isbnTf= new TextField("");
        addBtn= new Button("Add");
        addBtn.getUnselectedStyle().setFgColor(5542241);
        mainContainer.add(l1);
        mainContainer.add(new Label());
        Statics.setLabelStyle(l2);
        mainContainer.add(l2);
        Statics.setLabelStyle(l3);
        mainContainer.add(l3);
        mainContainer.add(tfName);
        mainContainer.add(tfTeachers);
        Statics.setLabelStyle(l4);
        mainContainer.add(l4);
        //Statics.setLabelStyle(l5);
        //mainContainer.add(l5);
        mainContainer.add(tfClasses);
        //mainContainer.add(isbnTf);
        mainContainer.add(addBtn);
        backBtn = Statics.createBackBtn(); 
        mainContainer.add(backBtn);
        addBtn.addActionListener((ActionListener) (ActionEvent evt) -> {
            // add a course
           
            Subject typedSubject = new Subject(tfName.getText(),tfTeachers.getText(),tfClasses.getText());
            new  SubjectDAO().addSubject(typedSubject);
            });
        backBtn.addActionListener((ActionListener) (ActionEvent evt) -> {
            listOfSubjects.showBack();
            });
        this.add(BorderLayout.NORTH, mainContainer);
   }
    
}
