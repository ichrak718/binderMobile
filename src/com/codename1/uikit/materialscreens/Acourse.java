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
import com.codename1.uikit.materialscreens.service.CourseDAO;
import com.esprit.binder.utils.Statics;


/**
 *
 * @author wael
 */
public class Acourse extends Form{

    private final Label l1,l2,l3,l4;
    private final TextField tfSub,tfCon,tfTea;
    private final Container mainContainer;
    private final Button editBtn,removeBtn,backBtn;
    private Course currentCourse;
    
    public Acourse(Integer id, String subject, String content, String teacher){
        
        this.setLayout(new BorderLayout());
        mainContainer = new Container();
        mainContainer.setLayout(new GridLayout(8,2));
        l1 = new Label("Course :");
        //l1.getUnselectedStyle().setAlignment(Component.CENTER);
        l1.getUnselectedStyle().setFgColor(-16777216);
        Font l1_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        l1.getUnselectedStyle().setFont(l1_font);
        l2 = new Label("subject:");
        tfSub = new TextField(subject); 
        l3 = new Label("content:");
        tfCon = new TextField(content);
        l4 = new Label("teacher:");
        tfTea= new TextField(teacher);
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
        mainContainer.add(tfSub);
        mainContainer.add(tfCon);
        //Statics.setLabelStyle(l4);
        mainContainer.add(l4);
        //Statics.setLabelStyle(l5);
        //mainContainer.add(l5);
        mainContainer.add(tfTea);
        //mainContainer.add(isbnTf);
        mainContainer.add(editBtn);
        mainContainer.add(removeBtn);
        backBtn = new Button("Back"); 
        mainContainer.add(backBtn);
        
        editBtn.addActionListener((ActionListener) (ActionEvent evt) -> {
            currentCourse = new Course(id, tfSub.getText(), tfCon.getText(), tfTea.getText());
            new CourseDAO().updateCourse(currentCourse);
            });
        removeBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                new CourseDAO().removeCourse(currentCourse);
            }
        });
        
        backBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                new CourseDAO().findAllCoursesT();
            }
        });
        
        this.add(BorderLayout.NORTH, mainContainer);
    }
    
}
