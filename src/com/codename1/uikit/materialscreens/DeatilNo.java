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
import com.codename1.uikit.materialscreens.entity.Notification;
import com.codename1.uikit.materialscreens.service.NotificationService;
import com.codename1.uikit.materialscreens.service.ServicePupil;
import com.esprit.binder.utils.Statics;

/**
 *
 * @author Rahma
 */
public class DeatilNo extends Form{
    private final Label l1,l2,l3,l4,l5;
    private final TextField categoryTf,senderTf,receiverTf,contentTf;
    private final Container mainContainer;
    private final Button editBtn;
    private Button  backBtn;
   // private Notification currentNotification;
   private Notification currentNotification;
    
    
   public DeatilNo(Integer id,String category,String sender,String receiver,String content,Integer id_user,String role){
        
        this.setLayout(new BorderLayout());
        mainContainer = new Container();
        mainContainer.setLayout(new GridLayout(8,2));
        l1 = new Label("Notification");
        //l1.getUnselectedStyle().setAlignment(Component.CENTER);
        l1.getUnselectedStyle().setFgColor(-16777216);
        Font l1_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        l1.getUnselectedStyle().setFont(l1_font);
        l2 = new Label("Category:");
        categoryTf = new TextField(category); 
        l3 = new Label("Sender:");
        senderTf = new TextField(sender);
        l4 = new Label("Receiver:");
       receiverTf= new TextField(receiver);
        l5 = new Label("Content:");
        contentTf= new TextField(content);
        editBtn= new Button("Response");
        editBtn.getUnselectedStyle().setFgColor(5542241);
        
       
        mainContainer.add(l1);
        mainContainer.add(new Label());
        Statics.setLabelStyle(l2);
        mainContainer.add(l2);
        mainContainer.add(categoryTf);
        Statics.setLabelStyle(l3);
        mainContainer.add(l3);
        
        mainContainer.add(senderTf);
        Statics.setLabelStyle(l4);
        mainContainer.add(l4);
        mainContainer.add(receiverTf);
        Statics.setLabelStyle(l5);
        mainContainer.add(l5);
        
        mainContainer.add(contentTf);
       mainContainer.add(editBtn);
        
        backBtn = Statics.createBackBtn(); 
        mainContainer.add(backBtn);
          
        
        
      
    
       currentNotification = new Notification( id,categoryTf.getText(), senderTf.getText(), receiverTf.getText(),contentTf.getText());
       //currentNotification = new Notification( category,sender,receiver,content);
           
       
               editBtn.addActionListener((ActionListener) (ActionEvent evt) -> {
            new NotificationService().addNotification(currentNotification);
      
            });
         
       
       
          backBtn.addActionListener(new ActionListener() {

           @Override
            public void actionPerformed(ActionEvent evt) {
     new ServicePupil().ServicePupilR(id_user, role);            }
            
        });
    
     
        this.add(BorderLayout.NORTH, mainContainer);
    }
}
    
 
   