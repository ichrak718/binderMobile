/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;

import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.list.ListModel;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.materialscreens.entity.Notification;
import com.codename1.uikit.materialscreens.service.NotificationService;
import static com.codename1.uikit.materialscreens.service.NotificationService.listOfNotifications;
import com.codename1.uikit.materialscreens.service.ServiceParent;
import com.codename1.uikit.materialscreens.service.ServicePupil;
import com.codename1.uikit.materialscreens.service.ServiceTimeTable;
import com.esprit.binder.utils.Statics;
import static jdk.nashorn.internal.runtime.Debug.id;
/*import tn.esprit.ent.Notification;
import tn.esprit.service.AbscenseService;
import tn.esprit.service.NotificationService;
import static tn.esprit.service.NotificationService.listOfNotifications;
import tn.esprit.utils.Statics;*/

/**
 *
 * @author Rahma
 */
public class Anotif extends Form {

    private final Label l1,l2,l3,l4,l5;
    private final TextField categoryTf,senderTf,receiverTf,contentTf;
    private final Container mainContainer;
    private final Button removeBtn;
    private Button  backBtn;
   // private Notification currentNotification;
   private Notification currentNotification;
    

    public Anotif(Integer id,String category,String sender,String receiver,String content){
        
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
      
        removeBtn= new Button("Remove");
        removeBtn.getUnselectedStyle().setFgColor(5542241);
       
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
      
        mainContainer.add(removeBtn);
        
        backBtn = Statics.createBackBtn(); 
        mainContainer.add(backBtn);
          
        
        
      
    
       currentNotification = new Notification( id,categoryTf.getText(), senderTf.getText(), receiverTf.getText(),contentTf.getText());
       //currentNotification = new Notification( category,sender,receiver,content);
          
       
        
         
                removeBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                new NotificationService().removeCourse(currentNotification);
            }
        });
       
          backBtn.addActionListener(new ActionListener() {

           @Override
            public void actionPerformed(ActionEvent evt) {
            new WelcomeTeacher().show();            }
        });
    
     
        this.add(BorderLayout.NORTH, mainContainer);
    }

  

   
    
 
    public Anotif(Integer id,String category,String sender,String receiver,String content,Integer id_user,String role){
        
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
      
        removeBtn= new Button("Remove");
        removeBtn.getUnselectedStyle().setFgColor(5542241);
       
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
      
        mainContainer.add(removeBtn);
        
        backBtn = Statics.createBackBtn(); 
        mainContainer.add(backBtn);
          
        
        
      
    
       currentNotification = new Notification( id,categoryTf.getText(), senderTf.getText(), receiverTf.getText(),contentTf.getText());
       //currentNotification = new Notification( category,sender,receiver,content);
       
       
        
         
                removeBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                new NotificationService().removeCourse(currentNotification);
            }
        });
       
          backBtn.addActionListener(new ActionListener() {

           @Override
            public void actionPerformed(ActionEvent evt) {
             new ServiceParent().ServiceParent(id_user, role);           }
        });
    
     
        this.add(BorderLayout.NORTH, mainContainer);
    }

  

}

   
 

 

   
