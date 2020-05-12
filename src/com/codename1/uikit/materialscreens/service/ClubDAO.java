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
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.materialscreens.entity.club;
import com.codename1.uikit.materialscreens.entity.participation;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Sam
 */
public class ClubDAO {
    
    private ConnectionRequest connectionRequest;
    public static Form listOfClubs;
    public static Form activityDetail;
    
        
    String ch;
    String login;
    Resources theme;
    club c=new club();
    
        
    
    public void addMember(participation p){
        connectionRequest=new ConnectionRequest(){
            @Override
            protected void postResponse() {
            Dialog d = new Dialog("Add to my particpation shelf");
            TextArea popupBody = new TextArea("member successfully added");
            popupBody.setUIID("PopupBody");
            popupBody.setEditable(false);
            d.setLayout(new BorderLayout());
            d.add(BorderLayout.CENTER, popupBody);
            d.showDialog();
            }
        };
        connectionRequest.setUrl("http://localhost/mobile/participation.php?name=" + p.getName() + "&classe=" + p.getClasse()+"&club="+p.getClub());
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
    
    
    
     public void findAllClubs(Integer id_class, Integer id_user, String role){
        connectionRequest = new ConnectionRequest() {
        List<club> Clubs = new ArrayList<>();
            @Override
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");

                    Map<String, Object> data = json.parseJSON(reader);
                    List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("root");
                    Clubs.clear();
                  
                    for (Map<String, Object> obj : content) {
                        Clubs.add(new club(Integer.parseInt((String) obj.get("id")),(String) obj.get("name"),(String) obj.get("specialty"),(String) obj.get("responsible")
                        ) );
                    }
                    
                    System.out.println(c.getId());
                     
                        //new ActivityDAO().findAllActivities(c);
                } catch (IOException err) {
                    Log.e(err);
                }
            }

            @Override
            protected void postResponse() {
                //System.out.println(libs.size());
                listOfClubs = new Form();
                com.codename1.ui.List uiLibsList = new com.codename1.ui.List();
                ArrayList<String> libsNoms = new ArrayList<>();
                for(club l :Clubs){
                    libsNoms.add(l.getName());
                }
                com.codename1.ui.list.DefaultListModel<String> listModel = new com.codename1.ui.list.DefaultListModel<>(libsNoms);
                uiLibsList.setModel(listModel);
                uiLibsList.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        club currentClub = Clubs.get(uiLibsList.getCurrentSelected());
                        System.out.println(currentClub.getId()+"aaaaaaaaa");
                        //new ActivityDAO().findAllActivities(currentClub.getId());
                       new ActivityDAO(currentClub.getId(),id_class,id_user,role);
                    }

                    
                });
                listOfClubs.setLayout(new BorderLayout());
                listOfClubs.add(BorderLayout.NORTH,uiLibsList);
                //listOfClubs.add(BorderLayout.SOUTH);
                listOfClubs.show();             
            }
        };
        connectionRequest.setUrl("http://localhost/mobile/getClubs.php");
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
     
     public void findAllClubs(){
        connectionRequest = new ConnectionRequest() {
        List<club> Clubs = new ArrayList<>();
            @Override
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");

                    Map<String, Object> data = json.parseJSON(reader);
                    List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("root");
                    Clubs.clear();
                  
                    for (Map<String, Object> obj : content) {
                        Clubs.add(new club(Integer.parseInt((String) obj.get("id")),(String) obj.get("name"),(String) obj.get("specialty"),(String) obj.get("responsible")
                        ) );
                    }
                    
                    System.out.println(c.getId());
                     
                        //new ActivityDAO().findAllActivities(c);
                } catch (IOException err) {
                    Log.e(err);
                }
            }

            @Override
            protected void postResponse() {
                //System.out.println(libs.size());
                listOfClubs = new Form();
                com.codename1.ui.List uiLibsList = new com.codename1.ui.List();
                ArrayList<String> libsNoms = new ArrayList<>();
                for(club l :Clubs){
                    libsNoms.add(l.getName());
                }
                com.codename1.ui.list.DefaultListModel<String> listModel = new com.codename1.ui.list.DefaultListModel<>(libsNoms);
                uiLibsList.setModel(listModel);
                uiLibsList.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        club currentClub = Clubs.get(uiLibsList.getCurrentSelected());
                        System.out.println(currentClub.getId()+"aaaaaaaaa");
                        //new ActivityDAO().findAllActivities(currentClub.getId());
                       new ActivityDAO(currentClub.getId());
                    }

                    
                });
                listOfClubs.setLayout(new BorderLayout());
                listOfClubs.add(BorderLayout.NORTH,uiLibsList);
                //listOfClubs.add(BorderLayout.SOUTH);
                listOfClubs.show();             
            }
        };
        connectionRequest.setUrl("http://localhost/mobile/getClubs.php");
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
}
    
    

