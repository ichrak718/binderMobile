/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.uikit.materialscreens.entity.Abscense;
import com.codename1.uikit.materialscreens.service.ServicePupil;
import com.esprit.binder.utils.Statics;

/**
 *
 * @author Rahma
 */
public class testAb extends Form{
     private Label l1;
    
    private Label l6;
     private Label l7;
            
     private final Label l2,l3,l4,l5;
    private final Label puplTf,subTf,gpTf,hrTf,nbrTf;
    private Container mainContainer;
    private final Button backBtn;
    private Abscense currentAbscense;
    
 
    public testAb(Integer id,String pupl,String subject,String groupe,String hour,String nb,Integer id_user,String role ){
      
        
        this.setLayout(new BorderLayout());
        mainContainer = new Container();
        mainContainer.setLayout(new GridLayout(8,2));
        l1 = new Label("Abscense ");
        //l1.getUnselectedStyle().setAlignment(Component.CENTER);
        l1.getUnselectedStyle().setFgColor(990066);
        Font l1_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        l1.getUnselectedStyle().setFont(l1_font);
        l2 = new Label("Pupl:");
        double name = 12.;
getUnselectedStyle().setFgColor(39321);
        Font  about_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
            
        l1.getUnselectedStyle().setFont(l1_font);
        puplTf = new Label(pupl); 
        l3 = new Label("Subject:");
        subTf = new Label(subject);
        l4 = new Label("Groupe:");
        gpTf= new Label(groupe);
         l7 = new Label("Date:");
//       date= new TextField(Dateab);
        l5 = new Label("Hour:");
        hrTf= new Label(hour);
       l6 = new Label("Nbr:");
       nbrTf= new Label(nb);
       
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
          //  Statics.setLabelStyle(l7);
      // mainContainer.add(l7);
      
//       mainContainer.add(date);
        Statics.setLabelStyle(l5);
        mainContainer.add(l5);
        
        mainContainer.add(hrTf);
     Statics.setLabelStyle(l6);
       mainContainer.add(l6);
      
       mainContainer.add(nbrTf);
       
        
        backBtn = Statics.createBackBtn(); 
        mainContainer.add(backBtn);
        currentAbscense = new Abscense( id,puplTf.getText(), subTf.getText(), gpTf.getText(),hrTf.getText(),nbrTf.getText());
       // currentAbscense = new Abscense(pupl,subject,groupe,hour,nbr);
    
      backBtn.addActionListener(new ActionListener() {

           @Override
            public void actionPerformed(ActionEvent evt) {
new ServicePupil().ServicePupilR(id_user, role);            }
        
        });
     
        this.add(BorderLayout.NORTH, mainContainer);
}

 

  
    

}
