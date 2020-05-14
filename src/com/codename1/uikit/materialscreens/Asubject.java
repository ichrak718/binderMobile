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
import com.codename1.uikit.materialscreens.entity.Course;
import com.codename1.uikit.materialscreens.entity.Subject;
import com.codename1.uikit.materialscreens.service.CourseDAO;
import com.codename1.uikit.materialscreens.service.SubjectDAO;

import com.esprit.binder.utils.Statics;

/**
 *
 * @author wael
 */
public class Asubject extends Form{

    private final Label l1,l2,l3,l4;
    private final TextField tfName,tfTeachers,tfClasses;
    private final Container mainContainer;
    private final Button editBtn,removeBtn,backBtn;
    private Subject currentSubject;
    
    public Asubject(Integer id, String name, String teachers, String classes){
        
        this.setLayout(new BorderLayout());
        mainContainer = new Container();
        mainContainer.setLayout(new GridLayout(8,2));
        l1 = new Label("Subject :");
        //l1.getUnselectedStyle().setAlignment(Component.CENTER);
        l1.getUnselectedStyle().setFgColor(-16777216);
        Font l1_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        l1.getUnselectedStyle().setFont(l1_font);
        l2 = new Label("name:");
        tfName = new TextField(name); 
        l3 = new Label("teachers:");
        tfTeachers = new TextField(teachers);
        l4 = new Label("classes:");
        tfClasses= new TextField(classes);
        //l5 = new Label("ISBN:");
        //isbnTf= new TextField(isbn);
        editBtn= new Button("Edit");
        editBtn.getUnselectedStyle().setFgColor(5542241);
        removeBtn= new Button("Remove");
        removeBtn.getUnselectedStyle().setFgColor(5542241);
        mainContainer.add(l1);
        mainContainer.add(new Label());
        //Statics.setLabelStyle(l2);
        mainContainer.add(l2);
        //Statics.setLabelStyle(l3);
        mainContainer.add(l3);
        mainContainer.add(tfName);
        mainContainer.add(tfTeachers);
        //Statics.setLabelStyle(l4);
        mainContainer.add(l4);
        //Statics.setLabelStyle(l5);
        //mainContainer.add(l5);
        mainContainer.add(tfClasses);
        //mainContainer.add(isbnTf);
        mainContainer.add(editBtn);
        mainContainer.add(removeBtn);
        backBtn = new Button("Back"); 
        mainContainer.add(backBtn);
        
        editBtn.addActionListener((ActionListener) (ActionEvent evt) -> {
            currentSubject = new Subject(id, tfName.getText(), tfTeachers.getText(), tfClasses.getText());
            new SubjectDAO().updateSubject(currentSubject);
            });
        removeBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                new SubjectDAO().removeSubject(currentSubject);
            }
        });
        
        backBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                new SubjectDAO().findAllSubjectsT();
            }
        });
        
        this.add(BorderLayout.NORTH, mainContainer);
    }
    
}
