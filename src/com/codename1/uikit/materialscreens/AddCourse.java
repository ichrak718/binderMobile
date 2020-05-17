/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.uikit.materialscreens.entity.Course;
import com.codename1.uikit.materialscreens.service.CourseDAO;
import static com.codename1.uikit.materialscreens.service.CourseDAO.listOfCourses;

import com.esprit.binder.utils.Statics;
import java.util.Map;

/**
 *
 * @author wael
 */
public class AddCourse extends Form{
    private final Label l1,l2,l3,l4;
    private final TextField authorTf,categoryTf;
    private final Container mainContainer;
    private final Button addBtn,backBtn;
    private ComboBox cbox;
    
   public AddCourse(){
       
        this.setLayout(new BorderLayout());
        mainContainer = new Container();
        mainContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        l1 = new Label("Add a new course");
        l1.setAlignment(CENTER);
        //l1.getUnselectedStyle().setAlignment(Component.CENTER);
        l1.getUnselectedStyle().setFgColor(-16777216);
        Font l1_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        l1.getUnselectedStyle().setFont(l1_font);
        l2 = new Label("subject:");
        //titleTf = new TextField(""); 
        cbox = new ComboBox<Map<String, Object>>();        
        cbox.addItem("Anglais");
        cbox.addItem("SGBD");
        cbox.addItem("Francais");
        cbox.addItem("TL");
        cbox.addItem("unix");
        cbox.addItem("systeme logique");
        cbox.addItem("Python");
        l3 = new Label("content:");
        authorTf = new TextField("");
        
        
        
        l4 = new Label("teacher:");
        categoryTf= new TextField("");
        //l5 = new Label("ISBN:");
        //isbnTf= new TextField("");
        addBtn= new Button("Add");
        addBtn.getUnselectedStyle().setFgColor(5542241);
        mainContainer.add(l1);
        mainContainer.add(new Label());
        Statics.setLabelStyle(l2);
        mainContainer.add(l2);
        Statics.setLabelStyle(l3);
        mainContainer.add(cbox);
        mainContainer.add(l3);
        
        mainContainer.add(authorTf);
        //mainContainer.add(cbox);
        Statics.setLabelStyle(l4);
        mainContainer.add(l4);
        //Statics.setLabelStyle(l5);
        //mainContainer.add(l5);
        mainContainer.add(categoryTf);
        //mainContainer.add(isbnTf);
        mainContainer.add(addBtn);
        backBtn = Statics.createBackBtn(); 
        mainContainer.add(backBtn);
        addBtn.addActionListener((ActionListener) (ActionEvent evt) -> {
            // add a course
           
            Course typedCourse = new Course(cbox.getSelectedItem().toString(),authorTf.getText(),categoryTf.getText());
            new  CourseDAO().addCourse(typedCourse);
            });
        
        backBtn.addActionListener((ActionListener) (ActionEvent evt) -> {
            listOfCourses.showBack();
            });
        this.add(BorderLayout.NORTH, mainContainer);
   }
    
}
