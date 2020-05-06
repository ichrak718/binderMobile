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
import com.codename1.uikit.materialscreens.entity.FOSUser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Asus
 */
public class ServiceFosUser {

    private ConnectionRequest conx;
    public static Form listOfBooks;
    String ch;
    String login;
    Resources theme;
    FOSUser fos = new FOSUser();

    public void login(String email, String password) {
        List<FOSUser> list = new ArrayList<>();

        conx = new ConnectionRequest("http://localhost/mobile/login.php?email=" + email + "&password=" + password);

        conx.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {

                String json = new String(conx.getResponseData());
//**************************************************
                if (!json.equals("failed")) {
                    System.out.println(json);
                    try {
                        JSONParser j = new JSONParser();
                        Map<String, Object> u = j.parseJSON(new CharArrayReader(json.toCharArray()));
                        List<Map<String, Object>> content = (List<Map<String, Object>>) u.get("root");
                        list.clear();
//******************************
                        for (Map<String, Object> obj : content) {
                            list.add(new FOSUser(Integer.parseInt((String) obj.get("id"))));
                            fos.setId(Integer.parseInt((String) obj.get("id")));

                        }

                        System.out.println(fos.getId());

                        //*****************************
                        new ServicePupil().ServicePupilR(fos.getId());
                        //******************************
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                }
            }
        });
        NetworkManager.getInstance().addToQueue(conx);

        System.out.println(email + password);

    }
}
