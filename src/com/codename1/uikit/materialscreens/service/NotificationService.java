/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens.service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.location.Geofence;
import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.messaging.Message;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.uikit.materialscreens.Anotif;
import com.codename1.uikit.materialscreens.DeatilNo;
import com.codename1.uikit.materialscreens.WelcomeTeacher;
import com.codename1.uikit.materialscreens.entity.Notification;
import static com.codename1.uikit.materialscreens.service.AbscenseService.listOfAscenses;
import com.esprit.binder.utils.Statics;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/*import tn.esprit.ent.Abscense;

import tn.esprit.ent.Notification;
import tn.esprit.gui.AddNot;
import tn.esprit.gui.Anotif;
import tn.esprit.gui.Home;
import tn.esprit.utils.Statics;
import tn.esprit.utils.not;*/

/**
 *
 * @author Rahma
 */
public class NotificationService {
    

            
    private ConnectionRequest connectionRequest;
    public static Form listOfNotifications;
   
    public void addNotification(Notification not){
        connectionRequest=new ConnectionRequest(){
            @Override
            protected void postResponse() {
          
        Dialog.show("Done!", "successfully added", "Ok", null);
          
            }
        };
        connectionRequest.setUrl("http://localhost/mobile/insertN.php?category=" + not.getCategory() + "&sender=" + not.getSender()+"&receiver="+not.getReceiver()+"&content="+not.getContent());
        NetworkManager.getInstance().addToQueue(connectionRequest);
         Message m = new Message("Body of message");
       // String textAttachmentUri = null;
      //  m.getAttachments().put(textAttachmentUri, "text/plain");
       // String imageAttachmentUri = null;
      // m.getAttachments().put(imageAttachmentUri, "image/png");
    Display.getInstance().sendMessage(new String[] {"rahma.belhadj1@esprit.tn"}, "Merci de consulter notre plateforme et controler votre fils ", m);
            
    
}

    
     public void removeCourse(Notification c){   // remove course by id
        connectionRequest = new ConnectionRequest() {
            @Override
            protected void postResponse() {
            Dialog.show("Done!", "successfully removed", "Ok", null);
            }           
        };
        connectionRequest.setUrl("http://localhost/mobile/removenoti.php?id=" + c.getId());
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
     
      
     

    public void findAllNotifications() {
      
        connectionRequest = new ConnectionRequest() {
        List<Notification> notifications = new ArrayList<>();
            @Override
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");

                    Map<String, Object> data = json.parseJSON(reader);
                    List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("root");
                    notifications.clear();
                  
                    for (Map<String, Object> obj : content) {
                      notifications.add(new Notification(Integer.parseInt((String) obj.get("id")),(String) obj.get("category"),(String) obj.get("sender"),(String) obj.get("receiver"),(String) obj.get("content"))
                        );
                        
                    }
                } catch (IOException err) {
                    Log.e(err);
                }
            }
       
    
    
        @Override
            protected void postResponse() {
                //System.out.println(libs.size());
                listOfNotifications = new Form();
                com.codename1.ui.List uiLibsList = new com.codename1.ui.List();
                ArrayList<String> libsNoms = new ArrayList<>();
                for(Notification l :notifications){
                    libsNoms.add(l.getCategory()+" Sent by "+l.getSender()+" To "+l.getReceiver());
                }
                com.codename1.ui.list.DefaultListModel<String> listModel = new com.codename1.ui.list.DefaultListModel<>(libsNoms);
                uiLibsList.setModel(listModel);
                uiLibsList.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        Notification currentNotification = notifications.get(uiLibsList.getCurrentSelected());
             new Anotif(currentNotification.getId(),currentNotification.getCategory(),currentNotification.getSender(),currentNotification.getReceiver(),currentNotification.getContent()).show();
                    }
                });
                          Button backBtn;
                    backBtn = Statics.createBackBtn(); 
                listOfNotifications.setLayout(new BorderLayout());
                listOfNotifications.add(BorderLayout.NORTH,uiLibsList);
               listOfNotifications .add(BorderLayout.SOUTH,backBtn);

      backBtn.addActionListener(new ActionListener() {

           @Override
            public void actionPerformed(ActionEvent evt) {
              new WelcomeTeacher().show();           }
        });
     
              listOfNotifications.show();             
            }
        };  
        connectionRequest.setUrl("http://localhost/mobile/readN.php");
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
    
    // ----------------------------------------------------------------------------------
    
    

    
    
    

    
    

    public void findAllNotificationsiD(Integer id_user,String role,Integer id_pupil) {
      
        connectionRequest = new ConnectionRequest() {
        List<Notification> notifications = new ArrayList<>();
            @Override
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");

                    Map<String, Object> data = json.parseJSON(reader);
                    List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("root");
                    notifications.clear();
                  
                    for (Map<String, Object> obj : content) {
                      notifications.add(new Notification(Integer.parseInt((String) obj.get("id")),(String) obj.get("category"),(String) obj.get("sender"),(String) obj.get("receiver"),(String) obj.get("content"))
                        );
                        
                    }
                } catch (IOException err) {
                    Log.e(err);
                }
            }
       
    
    
        @Override
            protected void postResponse() {
                //System.out.println(libs.size());
                listOfNotifications = new Form();
                com.codename1.ui.List uiLibsList = new com.codename1.ui.List();
                ArrayList<String> libsNoms = new ArrayList<>();
                for(Notification l :notifications){
                    libsNoms.add(l.getCategory()+" Sent by "+l.getSender()+" To "+l.getReceiver());
                }
                com.codename1.ui.list.DefaultListModel<String> listModel = new com.codename1.ui.list.DefaultListModel<>(libsNoms);
                uiLibsList.setModel(listModel);
                uiLibsList.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        Notification currentNotification = notifications.get(uiLibsList.getCurrentSelected());
             new DeatilNo(currentNotification.getId(),currentNotification.getCategory(),currentNotification.getSender(),currentNotification.getReceiver(),currentNotification.getContent(),id_user,role).show();
                    }
                });
                          Button backBtn;
                    backBtn = Statics.createBackBtn(); 
                listOfNotifications.setLayout(new BorderLayout());
                listOfNotifications.add(BorderLayout.NORTH,uiLibsList);
               listOfNotifications .add(BorderLayout.SOUTH,backBtn);

      backBtn.addActionListener(new ActionListener() {

           @Override
            public void actionPerformed(ActionEvent evt) {
             new ServicePupil().ServicePupilR(id_user, role);         }
        });
     
              listOfNotifications.show();             
            }
        };  
           connectionRequest.setUrl("http://localhost/mobile/readID.php?parent_id="+id_pupil);
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
     
     
     
   

        }