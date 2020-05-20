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
import com.codename1.uikit.materialscreens.entity.Comment;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

/**
 *
 * @author Assma
 */
public class CommentsDetails {
    
    private ConnectionRequest connectionRequest;
    public static List listOfComments;
    public static Form comment;
    
        private ConnectionRequest conx;
    public static Form listComments;
    Comment a = new Comment();
    List<Comment> Comment = new ArrayList<>();
    Comment s;
    
    
    public CommentsDetails() {
    }
    
    
    public CommentsDetails(Integer id,Integer id_class,Integer id_user,String role) {
        
        conx = new ConnectionRequest("http://localhost/mobile/getComments.php?id_Activity=" + id);
        conx.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(conx.getResponseData());
                
                Map<String, Object> u;
                try {
                    JSONParser j = new JSONParser();
                    u = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    
                    List<Map<String, Object>> content = (List<Map<String, Object>>) u.get("root");
                    Comment.clear();
                    
                    for (Map<String, Object> obj : content) {
                        Comment.add(new Comment(Integer.parseInt((String) obj.get("id")), (String) obj.get("content"),Integer.parseInt((String) obj.get("id_Activity"))));
                        a.setId(Integer.parseInt((String) obj.get("id")));
                        a.setContent((String) obj.get("content"));
                        
                       
                        a.setId_Activity(Integer.parseInt((String) obj.get("id_Activity")));
                        
                    }
                    System.out.println(a.getId());
                                    //System.out.println(libs.size());
                                    //new ActivityForm(s.getId(),s.getAbout()).show();
                                    
                                    
                                    
                                    //*****************************************
                comment = new Form();
                com.codename1.ui.List uiLibsList = new com.codename1.ui.List();
                ArrayList<String> libsNoms = new ArrayList<>();
                for(Comment l :Comment){
                    libsNoms.add(l.getContent());
                }
                com.codename1.ui.list.DefaultListModel<String> listModel = new com.codename1.ui.list.DefaultListModel<>(libsNoms);
                uiLibsList.setModel(listModel);
                uiLibsList.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        Comment currentComment = Comment.get(uiLibsList.getCurrentSelected());
                        System.out.println(currentComment.getId()+"samar");
                        new ActivityDetails(currentComment.getId(),id_class,id_user,role);
                       
                    }

                    
                });
                comment.setLayout(new BorderLayout());
                comment.add(BorderLayout.NORTH,uiLibsList);
                //listOfClubs.add(BorderLayout.SOUTH);
                comment.show(); 
                   // new ActivityList(a.getId(), a.getAbout(), a.getLocation(), a.getDuration(), a.getDateA(),a.getClubAC(), a.getClub_id()).show();
                    
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueue(conx);
        
    }
     public CommentsDetails(Integer id) {
        
        conx = new ConnectionRequest("http://localhost/mobile/getComments.php?id_Activity=" + id);
        conx.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(conx.getResponseData());
                
                Map<String, Object> u;
                try {
                    JSONParser j = new JSONParser();
                    u = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    
                    List<Map<String, Object>> content = (List<Map<String, Object>>) u.get("root");
                    Comment.clear();
                    
                    for (Map<String, Object> obj : content) {
                        Comment.add(new Comment(Integer.parseInt((String) obj.get("id")), (String) obj.get("content"),Integer.parseInt((String) obj.get("id_Activity"))));
                        a.setId(Integer.parseInt((String) obj.get("id")));
                        a.setContent((String) obj.get("content"));
                       
                        a.setId_Activity(Integer.parseInt((String) obj.get("id_Activity")));
                        
                    }
                    System.out.println(a.getId());
                                    //System.out.println(libs.size());
                                    //new ActivityForm(s.getId(),s.getAbout()).show();
                                    
                                    
                                    
                                    //*****************************************
                comment = new Form();
                com.codename1.ui.List uiLibsList = new com.codename1.ui.List();
                ArrayList<String> libsNoms = new ArrayList<>();
                for(Comment l :Comment){
                    libsNoms.add(l.getContent());
                }
                com.codename1.ui.list.DefaultListModel<String> listModel = new com.codename1.ui.list.DefaultListModel<>(libsNoms);
                uiLibsList.setModel(listModel);
                uiLibsList.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        Comment currentComment = Comment.get(uiLibsList.getCurrentSelected());
                        System.out.println(currentComment.getId()+"samar");
                        new displayDetails(currentComment.getId());
                       
                    }

                    
                });
                comment.setLayout(new BorderLayout());
                comment.add(BorderLayout.NORTH,uiLibsList);
                //listOfClubs.add(BorderLayout.SOUTH);
                comment.show(); 
                   // new ActivityList(a.getId(), a.getAbout(), a.getLocation(), a.getDuration(), a.getDateA(),a.getClubAC(), a.getClub_id()).show();
                    
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueue(conx);
        
    }
    
    
    
}
