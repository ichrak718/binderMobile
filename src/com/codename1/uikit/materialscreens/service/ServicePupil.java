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
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.uikit.materialscreens.HomeForm;
import com.codename1.uikit.materialscreens.ProfilForm;
import com.codename1.uikit.materialscreens.entity.Classes;
import com.codename1.uikit.materialscreens.entity.FOSUser;
import com.codename1.uikit.materialscreens.entity.Pupils;
import com.esprit.binder.utils.Statics;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class ServicePupil {
    
    private ConnectionRequest conx;
    public static Form listPupil;
    Pupils p = new Pupils();
    List<Pupils> pupil = new ArrayList<>();
    Pupils s;
    
    public ServicePupil() {
    }
    
    public ServicePupil(Integer id) {
        
        conx = new ConnectionRequest("http://localhost/mobile/readPupil.php?id_user=" + id);
        conx.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(conx.getResponseData());
                
                Map<String, Object> u;
                try {
                    JSONParser j = new JSONParser();
                    u = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    
                    List<Map<String, Object>> content = (List<Map<String, Object>>) u.get("root");
                    pupil.clear();
                    
                    for (Map<String, Object> obj : content) {
                        pupil.add(new Pupils(Integer.parseInt((String) obj.get("id")), (String) obj.get("fullname"), (String) obj.get("birthday"), (String) obj.get("email"), Integer.parseInt((String) obj.get("classes")), Integer.parseInt((String) obj.get("id_user"))));
                        p.setId(Integer.parseInt((String) obj.get("id")));
                        p.setFullname((String) obj.get("fullname"));
                        p.setBirthday((String) obj.get("birthday"));
                        p.setEmail((String) obj.get("email"));
                        p.setUser_id(Integer.parseInt((String) obj.get("id_user")));
                        
                    }
                    
                    new ProfilForm(p.getId(), p.getFullname(), p.getBirthday(), p.getEmail(), p.getClasses(), p.getUser_id()).show();
                    
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueue(conx);
        
    }
    
    public void ServicePupilR(Integer id) {
        
        conx = new ConnectionRequest("http://localhost/mobile/readPupil.php?id_user=" + id);
        conx.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(conx.getResponseData());
                
                Map<String, Object> u;
                try {
                    JSONParser j = new JSONParser();
                    u = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    
                    List<Map<String, Object>> content = (List<Map<String, Object>>) u.get("root");
                    pupil.clear();
                    
                    for (Map<String, Object> obj : content) {
                        pupil.add(new Pupils(Integer.parseInt((String) obj.get("id")), (String) obj.get("fullname"), (String) obj.get("birthday"), (String) obj.get("email"), Integer.parseInt((String) obj.get("classes")), Integer.parseInt((String) obj.get("id_user"))));
                        p.setId(Integer.parseInt((String) obj.get("id")));
                        p.setFullname((String) obj.get("fullname"));
                        p.setBirthday((String) obj.get("birthday"));
                        p.setEmail((String) obj.get("email"));
                        p.setUser_id(Integer.parseInt((String) obj.get("id_user")));
                        
                    }
                    System.out.println(p.getFullname());
                    new ProfilForm(p.getId(), p.getFullname(), p.getBirthday(), p.getEmail(), p.getClasses(), p.getUser_id()).show();
                    
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueue(conx);
        
    }
    
}
