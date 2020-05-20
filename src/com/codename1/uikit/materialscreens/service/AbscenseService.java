/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens.service;

import com.codename1.components.MultiButton;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;

import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.uikit.materialscreens.Aabs;
//import com.codename1.uikit.materialscreens.DetailA;
//import com.codename1.uikit.materialscreens.DetailA;
import com.codename1.uikit.materialscreens.WelcomeTeacher;
import com.codename1.uikit.materialscreens.entity.Abscense;
import com.codename1.uikit.materialscreens.entity.SubjectR;
import com.codename1.uikit.materialscreens.testAb;
import com.esprit.binder.utils.Statics;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*import tn.esprit.ent.Abscense;
import tn.esprit.ent.Notification;
import tn.esprit.gui.Aabs;
import tn.esprit.gui.Anotif;
import static tn.esprit.service.NotificationService.listOfNotifications;
//import tn.esprit.gui.Anotif;

import tn.esprit.utils.Statics;*/

/**
 *
 * @author Rahma
 */
public class AbscenseService {
     private ConnectionRequest connectionRequest;
    public static Form listOfAscenses;

   
 
    
    
    //----------------------------------------------------ADD
       public void addAbscense(Abscense ab){
        connectionRequest=new ConnectionRequest(){
            @Override
            protected void postResponse() {
             Dialog.show("Done!", "successfully added", "Ok", null);
         
            }
        };
        connectionRequest.setUrl("http://localhost/mobile/insertA.php?pupl=" + ab.getPupl() + "&subject=" + ab.getSubject()+"&groupe="+ab.getGroupe()+"&hour="+ab.getHour()
                + "&nb"+ab.getNb()+ "&dateab"+ab.getDateab());
        NetworkManager.getInstance().addToQueue(connectionRequest);

    }
     //----------------------------------------------------REMOVE
          public void removeAb(Abscense c){   // remove course by id
        connectionRequest = new ConnectionRequest() {
            @Override
            protected void postResponse() {
            Dialog.show("Done!", "successfully removed", "Ok", null);
            }           
        };
        connectionRequest.setUrl("http://localhost/mobile/deleteAb.php?id=" + c.getId());
        NetworkManager.getInstance().addToQueue(connectionRequest);
    } 
    
          
          
         //----------------------------------------------------READ   
   public void findAbscensesiD(Integer id_user,String role,Integer id_pupil) {
        connectionRequest = new ConnectionRequest() {
        List<Abscense> abscenses = new ArrayList<>();
            @Override
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");

                    Map<String, Object> data = json.parseJSON(reader);
                    List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("root");
                 abscenses.clear();
                  
                    for (Map<String, Object> obj : content) {
                   
                   abscenses.add(new Abscense((String) obj.get("pupl"),(String) obj.get("subject"),(String) obj.get("hour"),(String) obj.get("groupe"),(String) obj.get("nb"))
                     );                      
                    }
                } catch (IOException err) {
                    Log.e(err);
                }
            }
       
    
    
        @Override
            protected void postResponse() {
                //System.out.println(libs.size());
                listOfAscenses = new Form();
                com.codename1.ui.List uiLibsList = new com.codename1.ui.List();
                ArrayList<String> libsNoms = new ArrayList<>();
                for(Abscense l :abscenses){
                    libsNoms.add(l.getPupl());
                }
                com.codename1.ui.list.DefaultListModel<String> listModel = new com.codename1.ui.list.DefaultListModel<>(libsNoms);
                uiLibsList.setModel(listModel);
                uiLibsList.addActionListener(new ActionListener() {
                
                   @Override
                    public void actionPerformed(ActionEvent evt) {
                    Abscense currentAbscense = abscenses.get(uiLibsList.getCurrentSelected());
              new testAb(currentAbscense.getId(),currentAbscense.getPupl(),currentAbscense.getSubject(),currentAbscense.getHour(),currentAbscense.getGroupe(),currentAbscense.getNb(),id_user,role).show();
                    }

                });
                 Button backBtn;
                    backBtn = Statics.createBackBtn(); 
                listOfAscenses.setLayout(new BorderLayout());
                listOfAscenses.add(BorderLayout.NORTH,uiLibsList);
                listOfAscenses .add(BorderLayout.SOUTH,backBtn);

      backBtn.addActionListener(new ActionListener() {

           @Override
            public void actionPerformed(ActionEvent evt) {
new ServicePupil().ServicePupilR(id_user, role);            }
        });
     
                listOfAscenses.show();             
            }
        };
        connectionRequest.setUrl("http://localhost/mobile/readABS.php?pupil_id="+id_pupil);
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
    
   
   
   
     //----------------------------------------------------UPDATE
       public void updateAbs(Abscense b){
        connectionRequest = new ConnectionRequest() {
            
            @Override
            protected void postResponse() { 
                Dialog.show("Done!", "successfully updated", "Ok", null);
            
            }
        };
        connectionRequest.setUrl("http://localhost/mobile/updateab.php?pupl="+b.getPupl()+"&subject="+b.getSubject()+
                                "&groupe="+b.getGroupe()+"&hour="+b.getHour()+"&nbr="+"&id=3");
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }

       
       
       
       
       
       
   public void findAllAbscenses() {
        connectionRequest = new ConnectionRequest() {
        List<Abscense> abscenses = new ArrayList<>();
            @Override
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");

                    Map<String, Object> data = json.parseJSON(reader);
                    List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("root");
                 abscenses.clear();
                  
                    for (Map<String, Object> obj : content) {
                   
                    abscenses.add(new Abscense((String) obj.get("pupl"),(String) obj.get("subject"),(String) obj.get("hour"),(String) obj.get("groupe"),(String) obj.get("nb"))
                      );                      
                    }
                } catch (IOException err) {
                    Log.e(err);
                }
            }
       
    
    
        @Override
            protected void postResponse() {
                //System.out.println(libs.size());
                listOfAscenses = new Form();
                com.codename1.ui.List uiLibsList = new com.codename1.ui.List();
                ArrayList<String> libsNoms = new ArrayList<>();
                for(Abscense l :abscenses){
                    libsNoms.add(l.getPupl());
                }
                com.codename1.ui.list.DefaultListModel<String> listModel = new com.codename1.ui.list.DefaultListModel<>(libsNoms);
                uiLibsList.setModel(listModel);
                uiLibsList.addActionListener(new ActionListener() {
                
                   @Override
                    public void actionPerformed(ActionEvent evt) {
                    Abscense currentAbscense = abscenses.get(uiLibsList.getCurrentSelected());
                 new Aabs(currentAbscense.getId(),currentAbscense.getPupl(),currentAbscense.getSubject(),currentAbscense.getHour(),currentAbscense.getGroupe(),currentAbscense.getNb()).show();
                    }

                });
                          Button backBtn;
                    backBtn = Statics.createBackBtn(); 
                listOfAscenses.setLayout(new BorderLayout());
                listOfAscenses.add(BorderLayout.NORTH,uiLibsList);
                listOfAscenses .add(BorderLayout.SOUTH,backBtn);

      backBtn.addActionListener(new ActionListener() {

           @Override
            public void actionPerformed(ActionEvent evt) {
              new WelcomeTeacher().show();           }
        });
     
                listOfAscenses.show();             
            }
        };         
         
        connectionRequest.setUrl("http://localhost/mobile/readA.php");
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }

   
   
   
   
   
   
   
   
   
   
   

       
    
    
    
       

       
       
       
       
}
