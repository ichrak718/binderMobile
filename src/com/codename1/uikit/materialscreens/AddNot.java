/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;

import com.codename1.components.MultiButton;
import com.codename1.components.ToastBar;
import com.codename1.facebook.User;
import static com.codename1.io.Log.e;
import com.codename1.location.Geofence;
import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.messaging.Message;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.uikit.materialscreens.entity.Notification;
import com.codename1.uikit.materialscreens.service.NotificationService;
import com.codename1.uikit.materialscreens.service.ServicePupil;
import com.esprit.binder.utils.Statics;
import java.util.HashMap;
import java.util.Map;









/**
 *
 * @author Rahma
 */
public class AddNot extends Form{
    


  
    private final Label l1,l2,l3,l4,l5;
    private final TextField s,r,contentTf;
    private final Container mainContainer;
    private final Button addBtn,backBtn;
    private final ComboBox cbox;
     private Button btnRetour;
     //private final Label l7;
 
      Form precedent;
    
   
    public AddNot( ){
       
       
      
        Form signup = new Form("", BoxLayout.y());
        signup = this;
       this.setLayout(new BorderLayout());
        mainContainer = new Container();
        mainContainer.setLayout(new GridLayout(8,2));
        l1 = new Label("Add Notif");
        l1.setAlignment(CENTER);
        //l1.getUnselectedStyle().setAlignment(Component.CENTER);
        l1.getUnselectedStyle().setFgColor(-16777216);
        Font l1_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        l1.getUnselectedStyle().setFont(l1_font);
        l2 = new Label("Category");
      //  c = new TextField("");
        cbox = new ComboBox<Map<String, Object>>();        
        cbox.addItem("Abscense");
        cbox.addItem("Another Remark");
        cbox.addItem("Response");
        l3 = new Label("Sender:");
        s = new TextField("");
        /*cbox1 = new ComboBox<Map<String, Object>>();        
        cbox1.addItem("Sami Akrmi");
        cbox1.addItem("Baha Tayeb");
        cbox1.addItem("Nour Mzini");
        cbox1.addItem("Bayrem Semi");
        cbox1.addItem("Baha Demousi");
        cbox1.addItem("Nour Mzini");*/
        l4 = new Label("Receiver:");
        r = new TextField("");
        /*cbox2 = new ComboBox<Map<String, Object>>();        
             
        cbox2.addItem("Maram Agrbi");
        cbox2.addItem("Mouna Haj ");
        cbox2.addItem("Maysa Suil");
        cbox2.addItem("Ahmed anso");
        cbox2.addItem("Bekri Samia");
        cbox2.addItem("Sami Akrmi");*/
        
        l5 = new Label("Content:");
        contentTf= new TextField("");
  
      
        addBtn= new Button("Add");
        addBtn.getUnselectedStyle().setFgColor(5542241);
        mainContainer.add(l1);
        mainContainer.add(new Label());
        Statics.setLabelStyle(l2);
        mainContainer.add(l2);
        mainContainer.add(cbox);
//       mainContainer.add(c);
        Statics.setLabelStyle(l3);
        mainContainer.add(l3);
       // mainContainer.add(cbox1);
        mainContainer.add(s);
        Statics.setLabelStyle(l4);
        mainContainer.add(l4);
       // mainContainer.add(cbox2);
       mainContainer.add(r);
        Statics.setLabelStyle(l5);
        mainContainer.add(l5);
        
        mainContainer.add(contentTf);
        mainContainer.add(addBtn);
   
      
        
    
       
        backBtn = Statics.createBackBtn(); 
        mainContainer.add(backBtn);
         
        

        
        
        
     
        addBtn.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                // add a book
                try {
                    
                
                if((contentTf.getText().equals(""))||(s.getText().equals(""))||(r.getText().equals("")))
                    Dialog.show("Erreur", "Vous devez remplir touts les champs ", "Ok", null);
                else                
                {
                Notification typedNotification = new Notification(contentTf.getText(),s.getText(),r.getText());
                String toString = cbox.getSelectedItem().toString();
                 //cbox1.getSelectedItem().toString();
                // cbox2.getSelectedItem().toString();
                new  NotificationService().addNotification(typedNotification);
        
                   /*  Message m = new Message("Hello");
        String textAttachmentUri = null;
        m.getAttachments().put(textAttachmentUri, "text/plain");
      // m.getAttachments().put(imageAttachmentUri, "image/png");
      Display.getInstance().sendMessage(new String[] {"rahma.belhadj1@esprit.tn"}, "Notif", m);
    
                 /*  String email = "r.line1997@gmail.com";
                    Message message = new Message("test");
                    Display.getInstance().sendMessage(new String[]{email}, "notification", message);*/
                   // precedent.show();
    
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
        