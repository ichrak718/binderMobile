/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;

import com.codename1.io.Log;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Calendar;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.uikit.materialscreens.entity.Abscense;
import com.codename1.uikit.materialscreens.service.AbscenseService;
import com.codename1.uikit.materialscreens.service.ServicePupil;
import com.esprit.binder.utils.Statics;
import java.util.Date;
import javafx.scene.control.DatePicker;


/**
 *
 * @author Rahma
 */
public class AddAb extends Form {
    
    
    private final Label l1,l2,l3,l4,l5;
    private final TextField pTf,sTf,gTf,hTf,nbrTf,date;
    private final Container mainContainer;
    private final Button addBtn,backBtn;
    private final Label l6;
                  private final Label l7;

    
   public AddAb(){

       
     this.setLayout(new BorderLayout());
        mainContainer = new Container();
        mainContainer.setLayout(new GridLayout(8,2));
        l1 = new Label("Add Abs");
        l1.setAlignment(CENTER);
        //l1.getUnselectedStyle().setAlignment(Component.CENTER);
        l1.getUnselectedStyle().setFgColor(-16777216);
        Font l1_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        l1.getUnselectedStyle().setFont(l1_font);
        l2 = new Label("Pupl:");
        pTf = new TextField(""); 
        l3 = new Label("Subject:");
        sTf = new TextField("");
        l4 = new Label("Groupe:");
        gTf= new TextField("");
         l7 = new Label("Date:");
        date= new TextField("");
        l5 = new Label("Hour:");
        hTf= new TextField("");
        l6 = new Label("Nbr:");
        nbrTf= new TextField("");
        addBtn= new Button("Add");
        addBtn.getUnselectedStyle().setFgColor(5542241);
        mainContainer.add(l1);
        mainContainer.add(new Label());
         
        Statics.setLabelStyle(l2);
        mainContainer.add(l2);
        mainContainer.add(pTf);
        Statics.setLabelStyle(l3);
        mainContainer.add(l3);
       
        mainContainer.add(sTf);
        Statics.setLabelStyle(l4);
        mainContainer.add(l4);
         mainContainer.add(gTf);
         mainContainer.add(l7);
         mainContainer.add(date);
        Statics.setLabelStyle(l5);
        mainContainer.add(l5);
       
       
        mainContainer.add(hTf);
       
        Statics.setLabelStyle(l6);
        mainContainer.add(l6);
        mainContainer.add(nbrTf);

        mainContainer.add(addBtn);
        backBtn = Statics.createBackBtn(); 
        mainContainer.add(backBtn);
        addBtn.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                // add a book
                 try {
                    
                
                if((pTf.getText().equals(""))||(sTf.getText().equals(""))||(gTf.getText().equals(""))||(date.getText().equals(""))||(hTf.getText().equals(""))||(nbrTf.getText().equals("")))
                    Dialog.show("Erreur", "Vous devez remplir touts les champs ", "Ok", null);
                
                       
                else                
                {
           //  Abscense typedAbscense = new Abscense(pTf.getText(),sTf.getText(),gTf.getText(),hTf.getText(),Integer.parseInt(nbrTf.getText()));
                 Abscense typedAbscense = new Abscense(pTf.getText(),sTf.getText(),gTf.getText(),date.getText(),date.getText(),hTf.getText(),nbrTf.getText());
                new AbscenseService().addAbscense(typedAbscense);
              
      
      
                
               
            }
                     } catch (NumberFormatException e) {
                      }
            }
        });
          backBtn.addActionListener(new ActionListener() {

           @Override
            public void actionPerformed(ActionEvent evt) {
                     new WelcomeTeacher().show();             }
        });

        this.add(BorderLayout.NORTH, mainContainer);
   }   
}  
    
    
    
    



