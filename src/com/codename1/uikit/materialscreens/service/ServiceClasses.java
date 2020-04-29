/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens.service;


import com.codename1.io.NetworkEvent;

import com.codename1.uikit.materialscreens.entity.Pupils;

import java.io.UnsupportedEncodingException;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.uikit.materialscreens.Acourse;
import com.codename1.uikit.materialscreens.entity.Classes;

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
 * @author Asus
 */
public class ServiceClasses  {
    private ConnectionRequest connectionRequest;
    public static Form form;
    

      public void findAllClasses(){
        connectionRequest = new ConnectionRequest() {
        List<Classes> classes = new ArrayList<>();
            @Override
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");

                    Map<String, Object> data = json.parseJSON(reader);
                    List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("root");
                    classes.clear();
                  
                    for (Map<String, Object> obj : content) {
                       classes.add(new Classes(Integer.parseInt((String) obj.get("id")), (String) obj.get("name"), (String) obj.get("session")));
                     
                    }
                } catch (IOException err) {
                    Log.e(err);
                }
            }
    
            
            
            
                  @Override
            protected void postResponse() {
                //System.out.println(libs.size());
                form = new Form();
                com.codename1.ui.List uiLibsList = new com.codename1.ui.List();
                ArrayList<String> libsNoms = new ArrayList<>();
                for(Classes l :classes){
                    libsNoms.add(l.getNameClass());
                }
                com.codename1.ui.list.DefaultListModel<String> listModel = new com.codename1.ui.list.DefaultListModel<>(libsNoms);
                uiLibsList.setModel(listModel);
                uiLibsList.addActionListener(new ActionListener() {
                    List<String> listModel= new ArrayList<>(libsNoms);
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        Classes currentBook = classes.get(uiLibsList.getCurrentSelected());

                        new Acourse(currentBook.getId(), currentBook.getNameClass(),currentBook.getSession()).show();
                   
                    }
                });
                
                   Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
                form.setLayout(new BorderLayout());
                form.add(BorderLayout.NORTH,uiLibsList);
                form.add(BorderLayout.SOUTH,Statics.createBackBtn());
                form.add(menuButton);
                form.show();             
            }
        };
        connectionRequest.setUrl("http://localhost/mobile/readClass.php");
        connectionRequest.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String  ch;
                try {
                    ch = new String(connectionRequest.getResponseData(), "utf-8");
                                            System.out.println(ch);

                } catch (UnsupportedEncodingException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueue(connectionRequest);
        
        };
    
    
   /* 
    public ArrayList<Pupils> getListCategorie(String json) {

        ArrayList<Pupils> listCategories = new ArrayList<>();

        try {

            JSONParser j = new JSONParser();

            Map<String, Object> categories = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) categories.get("root");

            for (Map<String, Object> obj : list) {
                Pupils c = new Pupils();
                c.setFullname(obj.get("fullname").toString());

                listCategories.add(c);

            }

        } catch (IOException ex) {
        }

        return listCategories;

    }
    ArrayList<Pupils> listCategories = new ArrayList<>();

    public ArrayList<Pupils> getCategories() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/mobile/readPupil.php?id_user=16");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceClasses cs = new ServiceClasses();
                listCategories = cs.getListCategorie(new String(con.getResponseData()));
            }
        });
        System.out.println(listCategories);
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listCategories;
    }
*/
                }

