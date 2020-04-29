/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens.service;

import com.codename1.components.InfiniteProgress;
import com.codename1.db.Cursor;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.uikit.materialscreens.HomeForm;
import com.codename1.uikit.materialscreens.entity.Classes;
import com.codename1.uikit.materialscreens.entity.FOSUser;
import com.codename1.uikit.materialscreens.entity.Pupils;
import static com.codename1.uikit.materialscreens.service.ServicePupil.listPupil;
import com.esprit.binder.utils.Statics;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
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
    FOSUser m=new FOSUser();

    public void login(String email, String password) {
        List<FOSUser> classes = new ArrayList<>();

        conx = new ConnectionRequest("http://localhost/mobile/login.php?email=" + email + "&password=" + password);

        conx.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(conx.getResponseData());
                // ch = new String(conx.getResponseData());

                if (!json.equals("failed")) {
                    System.out.println(json);
                    try {
                        JSONParser j = new JSONParser();

                        Map<String, Object> u = j.parseJSON(new CharArrayReader(json.toCharArray()));
                        List<Map<String, Object>> content = (List<Map<String, Object>>) u.get("root");
                        classes.clear();

                        for (Map<String, Object> obj : content) {
                            classes.add(new FOSUser(Integer.parseInt((String) obj.get("id"))));
                            m.setId(Integer.parseInt((String) obj.get("id")));

                        }
                        System.out.println(m.getId());
                     
                        new ServicePupil(m.getId());
                        //new HomeForm(m.getId()).show();
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
