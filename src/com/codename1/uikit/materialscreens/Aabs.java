/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.materialscreens.entity.Abscense;
import com.codename1.uikit.materialscreens.service.AbscenseService;
import com.codename1.uikit.materialscreens.service.NotificationService;
import com.codename1.uikit.materialscreens.service.ServiceParent;
import com.codename1.uikit.materialscreens.service.ServicePupil;
import com.codename1.uikit.materialscreens.service.ServiceTimeTable;
import com.esprit.binder.utils.Statics;
import java.util.Date;
import java.util.HashMap;


/**
 *
 * @author Rahma
 */
public class Aabs extends Form{

    private Label l1;
    
    private Label l6;
     private final Label l2,l3,l4,l5;
    private final TextField puplTf,subTf,gpTf,hrTf,nbrTf;
    private Container mainContainer;
    private final Button edit,remove,backBtn;
    private Abscense currentAbscense;
    
 
    public Aabs(Integer id,String pupl,String subject,String groupe,String hour,String nb ){
      
        
        this.setLayout(new BorderLayout());
        mainContainer = new Container();
        mainContainer.setLayout(new GridLayout(8,2));
        l1 = new Label("Abscense ");
        //l1.getUnselectedStyle().setAlignment(Component.CENTER);
        l1.getUnselectedStyle().setFgColor(990066);
        Font l1_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        l1.getUnselectedStyle().setFont(l1_font);
        l2 = new Label("Pupl:");
        l2.getUnselectedStyle().setFgColor(990066);
            
        l1.getUnselectedStyle().setFont(l1_font);
        puplTf = new TextField(pupl); 
        l3 = new Label("Subject:");
        subTf = new TextField(subject);
        l4 = new Label("Groupe:");
        gpTf= new TextField(groupe);
        l5 = new Label("Hour:");
        hrTf= new TextField(hour);
       l6 = new Label("Nbr:");
       nbrTf= new TextField(nb);
        edit= new Button("Edit");
      
        edit.getUnselectedStyle().setFgColor(990066);
        remove= new Button("Remove");
        remove.getUnselectedStyle().setFgColor(990066);
        mainContainer.add(l1);
        mainContainer.add(new Label());
        Statics.setLabelStyle(l2);
        mainContainer.add(l2);
         mainContainer.add(puplTf);
        Statics.setLabelStyle(l3);
        mainContainer.add(l3);
       
        mainContainer.add(subTf);
        Statics.setLabelStyle(l4);
        mainContainer.add(l4);
          mainContainer.add(gpTf);
        Statics.setLabelStyle(l5);
        mainContainer.add(l5);
        
        mainContainer.add(hrTf);
     Statics.setLabelStyle(l6);
       mainContainer.add(l6);
      
       mainContainer.add(nbrTf);
        mainContainer.add(edit);
        mainContainer.add(remove);
        backBtn = Statics.createBackBtn();
       backBtn.getUnselectedStyle().setFgColor(990066);
        mainContainer.add(backBtn);
        currentAbscense = new Abscense( id,puplTf.getText(), subTf.getText(), gpTf.getText(),hrTf.getText(),nbrTf.getText());
       // currentAbscense = new Abscense(pupl,subject,groupe,hour,nbr);
         edit.addActionListener((ActionListener) (ActionEvent evt) -> {
            new AbscenseService().updateAbs(currentAbscense);
            });
               remove.addActionListener(new ActionListener() {

           @Override
            public void actionPerformed(ActionEvent evt) {
                new AbscenseService().removeAb(currentAbscense);
            }
        });
      backBtn.addActionListener(new ActionListener() {

           @Override
            public void actionPerformed(ActionEvent evt) {
new WelcomeTeacher().show();            }
        });
     
        this.add(BorderLayout.NORTH, mainContainer);
}

  
    
 

    public Aabs(Integer id,String pupl,String subject,String groupe,String hour,String nb ,Integer id_user,String role){
      
        
        this.setLayout(new BorderLayout());
        mainContainer = new Container();
        mainContainer.setLayout(new GridLayout(8,2));
        l1 = new Label("Abscense ");
        //l1.getUnselectedStyle().setAlignment(Component.CENTER);
        l1.getUnselectedStyle().setFgColor(990066);
        Font l1_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        l1.getUnselectedStyle().setFont(l1_font);
        l2 = new Label("Pupl:");
        l2.getUnselectedStyle().setFgColor(990066);
            
        l1.getUnselectedStyle().setFont(l1_font);
        puplTf = new TextField(pupl); 
        l3 = new Label("Subject:");
        subTf = new TextField(subject);
        l4 = new Label("Groupe:");
        gpTf= new TextField(groupe);
        l5 = new Label("Hour:");
        hrTf= new TextField(hour);
       l6 = new Label("Nbr:");
       nbrTf= new TextField(nb);
        edit= new Button("Edit");
      
        edit.getUnselectedStyle().setFgColor(990066);
        remove =new Button("Remove");
        remove.getUnselectedStyle().setFgColor(990066);
        mainContainer.add(l1);
        mainContainer.add(new Label());
        Statics.setLabelStyle(l2);
        mainContainer.add(l2);
          mainContainer.add(puplTf);
        Statics.setLabelStyle(l3);
        mainContainer.add(l3);
      
        mainContainer.add(subTf);
        Statics.setLabelStyle(l4);
        mainContainer.add(l4);
         mainContainer.add(gpTf);
        Statics.setLabelStyle(l5);
        mainContainer.add(l5);
      mainContainer.add(hrTf);
     Statics.setLabelStyle(l6);
       mainContainer.add(l6);
       
  
       mainContainer.add(nbrTf);
      mainContainer.add(edit);
      mainContainer.add(remove);
        backBtn = Statics.createBackBtn(); 
        mainContainer.add(backBtn);
        currentAbscense = new Abscense( id,puplTf.getText(), subTf.getText(), gpTf.getText(),hrTf.getText(),nbrTf.getText());
       // currentAbscense = new Abscense(pupl,subject,groupe,hour,nbr);
         edit.addActionListener((ActionListener) (ActionEvent evt) -> {
            new AbscenseService().updateAbs(currentAbscense);
            });
               remove.addActionListener(new ActionListener() {

           @Override
            public void actionPerformed(ActionEvent evt) {
                new AbscenseService().removeAb(currentAbscense);
            }
        });
      backBtn.addActionListener(new ActionListener() {

           @Override
            public void actionPerformed(ActionEvent evt) {
new ServicePupil().ServicePupilR(id_user, role);            }
        });
     
        this.add(BorderLayout.NORTH, mainContainer);
    }

 


   

   


 
}
