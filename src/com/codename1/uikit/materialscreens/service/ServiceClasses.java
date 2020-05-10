/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens.service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.NetworkEvent;

import com.codename1.uikit.materialscreens.entity.Pupils;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.uikit.materialscreens.ProfilForm;
import com.codename1.uikit.materialscreens.entity.Classes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class ServiceClasses {

    private ConnectionRequest conx;
    public static Form listPupil;
    Classes classe = new Classes();
    List<Classes> classList = new ArrayList<>();
    Pupils s;

    public ServiceClasses() {
    }

    //*****************************
    public ServiceClasses(Integer id_class, String fullName, String birthday, String email, Integer id_user, Integer idPupil,String role) {

        conx = new ConnectionRequest("http://localhost/mobile/readClass.php?id=" + id_class);
        conx.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(conx.getResponseData());

                Map<String, Object> u;
                try {
                    JSONParser j = new JSONParser();
                    u = j.parseJSON(new CharArrayReader(json.toCharArray()));

                    List<Map<String, Object>> content = (List<Map<String, Object>>) u.get("root");
                    classList.clear();

                    for (Map<String, Object> obj : content) {
                        classList.add(new Classes(Integer.parseInt((String) obj.get("id")), (String) obj.get("name"), (String) obj.get("session")));
                        classe.setId(Integer.parseInt((String) obj.get("id")));
                        classe.setNameClass((String) obj.get("name"));
                        classe.setSession((String) obj.get("session"));

                    }

                    new ProfilForm(idPupil, fullName, birthday, email, classe.getNameClass(), id_user,id_class,role).show();
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueue(conx);

    }
}
