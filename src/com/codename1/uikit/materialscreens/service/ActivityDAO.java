/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens.service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.uikit.materialscreens.entity.activity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Sam
 */
public class ActivityDAO {
    
    private ConnectionRequest connectionRequest;
    public static List listOfActivities;
    public static Form activityDetail;
    
        private ConnectionRequest conx;
    public static Form listActivities;
    activity a = new activity();
    List<activity> activity = new ArrayList<>();
    activity s;
    
    public ActivityDAO() {
    }
    
    public ActivityDAO(Integer id,Integer id_class,Integer id_user,String role) {
        
        conx = new ConnectionRequest("http://localhost/mobile/activity1.php?id_club=" + id);
        conx.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(conx.getResponseData());
                
                Map<String, Object> u;
                try {
                    JSONParser j = new JSONParser();
                    u = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    
                    List<Map<String, Object>> content = (List<Map<String, Object>>) u.get("root");
                    activity.clear();
                    
                    for (Map<String, Object> obj : content) {
                        activity.add(new activity(Integer.parseInt((String) obj.get("id")), (String) obj.get("about"), (String) obj.get("location"), (String) obj.get("duration"), (String) obj.get("dateA"),(String) obj.get("clubAC"),Integer.parseInt((String) obj.get("id_club"))));
                        a.setId(Integer.parseInt((String) obj.get("id")));
                        a.setAbout((String) obj.get("about"));
                        a.setLocation((String) obj.get("location"));
                        a.setDuration((String) obj.get("duration"));
                        a.setDateA((String) obj.get("dateA"));
                        a.setClubAC((String) obj.get("clubAC"));
                        a.setClub_id(Integer.parseInt((String) obj.get("id_club")));
                        
                    }
                    System.out.println(a.getId());
                                    //System.out.println(libs.size());
                                    //new ActivityForm(s.getId(),s.getAbout()).show();
                                    
                                    
                                    
                                    //*****************************************
                activityDetail = new Form();
                com.codename1.ui.List uiLibsList = new com.codename1.ui.List();
                ArrayList<String> libsNoms = new ArrayList<>();
                for(activity l :activity){
                    libsNoms.add(l.getAbout());
                }
                com.codename1.ui.list.DefaultListModel<String> listModel = new com.codename1.ui.list.DefaultListModel<>(libsNoms);
                uiLibsList.setModel(listModel);
                uiLibsList.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        activity currentActivity = activity.get(uiLibsList.getCurrentSelected());
                        System.out.println(currentActivity.getId()+"samar");
                        new ActivityDetails(currentActivity.getId(),id_class,id_user,role);
                       
                    }

                    
                });
                activityDetail.setLayout(new BorderLayout());
                activityDetail.add(BorderLayout.NORTH,uiLibsList);
                //listOfClubs.add(BorderLayout.SOUTH);
                activityDetail.show(); 
                   // new ActivityList(a.getId(), a.getAbout(), a.getLocation(), a.getDuration(), a.getDateA(),a.getClubAC(), a.getClub_id()).show();
                    
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueue(conx);
        
    }
     public ActivityDAO(Integer id) {
        
        conx = new ConnectionRequest("http://localhost/mobile/activity1.php?id_club=" + id);
        conx.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(conx.getResponseData());
                
                Map<String, Object> u;
                try {
                    JSONParser j = new JSONParser();
                    u = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    
                    List<Map<String, Object>> content = (List<Map<String, Object>>) u.get("root");
                    activity.clear();
                    
                    for (Map<String, Object> obj : content) {
                        activity.add(new activity(Integer.parseInt((String) obj.get("id")), (String) obj.get("about"), (String) obj.get("location"), (String) obj.get("duration"), (String) obj.get("dateA"),(String) obj.get("clubAC"),Integer.parseInt((String) obj.get("id_club"))));
                        a.setId(Integer.parseInt((String) obj.get("id")));
                        a.setAbout((String) obj.get("about"));
                        a.setLocation((String) obj.get("location"));
                        a.setDuration((String) obj.get("duration"));
                        a.setDateA((String) obj.get("dateA"));
                        a.setClubAC((String) obj.get("clubAC"));
                        a.setClub_id(Integer.parseInt((String) obj.get("id_club")));
                        
                    }
                    System.out.println(a.getId());
                                    //System.out.println(libs.size());
                                    //new ActivityForm(s.getId(),s.getAbout()).show();
                                    
                                    
                                    
                                    //*****************************************
                activityDetail = new Form();
                com.codename1.ui.List uiLibsList = new com.codename1.ui.List();
                ArrayList<String> libsNoms = new ArrayList<>();
                for(activity l :activity){
                    libsNoms.add(l.getAbout());
                }
                com.codename1.ui.list.DefaultListModel<String> listModel = new com.codename1.ui.list.DefaultListModel<>(libsNoms);
                uiLibsList.setModel(listModel);
                uiLibsList.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        activity currentActivity = activity.get(uiLibsList.getCurrentSelected());
                        System.out.println(currentActivity.getId()+"samar");
                        new ActivityDetails(currentActivity.getId());
                       
                    }

                    
                });
                activityDetail.setLayout(new BorderLayout());
                activityDetail.add(BorderLayout.NORTH,uiLibsList);
                //listOfClubs.add(BorderLayout.SOUTH);
                activityDetail.show(); 
                   // new ActivityList(a.getId(), a.getAbout(), a.getLocation(), a.getDuration(), a.getDateA(),a.getClubAC(), a.getClub_id()).show();
                    
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueue(conx);
        
    }
    
    
}
