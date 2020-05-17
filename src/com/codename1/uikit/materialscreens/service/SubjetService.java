/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens.service;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.uikit.materialscreens.Aabs;
import com.codename1.uikit.materialscreens.Asb;
import com.codename1.uikit.materialscreens.WelcomeTeacher;
import com.codename1.uikit.materialscreens.entity.Abscense;
import com.codename1.uikit.materialscreens.entity.SubjectR;
import static com.codename1.uikit.materialscreens.service.AbscenseService.listOfAscenses;
import com.esprit.binder.utils.Statics;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Rahma
 */
public class SubjetService {
    
    
    
           
            
    private ConnectionRequest connectionRequest;
    public static Form listOfSubjects;
       
   public void findAllSubjects(Integer id_user,String role) {
        connectionRequest = new ConnectionRequest() {
        List<SubjectR> subjects = new ArrayList<>();
            @Override
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");

                    Map<String, Object> data = json.parseJSON(reader);
                    List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("root");
                 subjects.clear();
                  
                    for (Map<String, Object> obj : content) {
                   
                    subjects.add(new SubjectR((String) obj.get("noms"),(String) obj.get("nombre"))
                      );                      
                    }
                } catch (IOException err) {
                    Log.e(err);
                }
            }
       
    
    
        @Override
            protected void postResponse() {
                //System.out.println(libs.size());
                listOfSubjects = new Form();
                com.codename1.ui.List uiLibsList = new com.codename1.ui.List();
                ArrayList<String> libsNoms = new ArrayList<>();
                for(SubjectR l :subjects){
                    libsNoms.add(l.getNoms());
                }
                com.codename1.ui.list.DefaultListModel<String> listModel = new com.codename1.ui.list.DefaultListModel<>(libsNoms);
                uiLibsList.setModel(listModel);
                uiLibsList.addActionListener(new ActionListener() {
                
                   @Override
                    public void actionPerformed(ActionEvent evt) {
                    SubjectR currentSubjectR = subjects.get(uiLibsList.getCurrentSelected());
                 new Asb(currentSubjectR.getId(),currentSubjectR.getNoms(),currentSubjectR.getNombre(),id_user,role).show();
                    }

                  

                });
                          Button backBtn;
                  backBtn = Statics.createBackBtn(); 
                listOfSubjects.setLayout(new BorderLayout());
              listOfSubjects.add(BorderLayout.NORTH,uiLibsList);
              listOfSubjects .add(BorderLayout.SOUTH,backBtn);

      backBtn.addActionListener(new ActionListener() {

           @Override
            public void actionPerformed(ActionEvent evt) {
              new WelcomeTeacher().show();           }
        });
     
                listOfSubjects.show();             
            }
        };         
         
        connectionRequest.setUrl("http://localhost/mobile/readS.php");
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }

    
}
