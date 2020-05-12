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
import com.codename1.ui.events.ActionListener;
import com.codename1.uikit.materialscreens.WelcomePupil;
import com.codename1.uikit.materialscreens.entity.Parent;
import com.codename1.uikit.materialscreens.entity.Pupils;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class ServiceParent {

    private ConnectionRequest conx;
    public static Form listPupil;
    Parent p = new Parent();
    List<Parent> pupil = new ArrayList<>();
    Pupils s;

    public ServiceParent() {
    }

    public void ServiceParent(Integer id,String role) {

        conx = new ConnectionRequest("http://localhost/mobile/readParent.php?id_user=" + id);
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
                        pupil.add(new Parent(Integer.parseInt((String) obj.get("id")), (String) obj.get("name"), (String) obj.get("mail"),(String) obj.get("phone"), Integer.parseInt((String) obj.get("id_user"))));
                        p.setId(Integer.parseInt((String) obj.get("id")));
                        p.setName((String) obj.get("name"));
                        p.setMail((String) obj.get("mail"));
                        p.setPhone((String) obj.get("phone"));
                        p.setId_user(Integer.parseInt((String) obj.get("id_user")));


                    }
                    System.out.println(p.getName());
                   new ServicePupil().ServicePupilP(p.getId(), role,p.getName(),p.getMail(),p.getPhone(),id);
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueue(conx);

    }

     
    
    
}
