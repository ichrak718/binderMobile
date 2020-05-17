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
public class Asb  extends Form{

        private Label l1;

            
     private final Label l2,l3;
 
    private Container mainContainer;
    private Button backBtn;
    private Abscense currentSubjectR;
    private final Label puplTf,subTf;
    
    
      public Asb(Integer id,String noms,String nombre,Integer id_user,String role ){
      
        
        this.setLayout(new BorderLayout());
        mainContainer = new Container();
        mainContainer.setLayout(new GridLayout(8,2));
        l1 = new Label("Subjects ");
        //l1.getUnselectedStyle().setAlignment(Component.CENTER);
        l1.getUnselectedStyle().setFgColor(990066);
        Font l1_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        l1.getUnselectedStyle().setFont(l1_font);
        l2 = new Label("Nom:");
  
        puplTf = new Label(noms); 
        l3 = new Label("Nombre:");
        subTf = new Label(nombre);
       
       
        mainContainer.add(l1);
        mainContainer.add(new Label());
        Statics.setLabelStyle(l2);
        mainContainer.add(l2);
         mainContainer.add(puplTf);
        Statics.setLabelStyle(l3);
        mainContainer.add(l3);
       
        mainContainer.add(subTf);
        
          //  Statics.setLabelStyle(l7);
      // mainContainer.add(l7);
      
//       mainContainer.add(date);
       
       
        
        backBtn = Statics.createBackBtn(); 
        mainContainer.add(backBtn);
        currentSubjectR = new Abscense( id,puplTf.getText(), subTf.getText());
       // currentAbscense = new Abscense(pupl,subject,groupe,hour,nbr);
    
      backBtn.addActionListener(new ActionListener() {

           @Override
            public void actionPerformed(ActionEvent evt) {
                new ServicePupil().ServicePupilR(id_user, role);            }

            
         //new WelcomeTeacher().show();            }
        
        });
     
        this.add(BorderLayout.NORTH, mainContainer);
}

 

  
    
    
}
