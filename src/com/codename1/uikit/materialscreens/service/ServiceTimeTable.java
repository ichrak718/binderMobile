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
import com.codename1.uikit.materialscreens.TimeTableForm;
import com.codename1.uikit.materialscreens.entity.Classes;
import com.codename1.uikit.materialscreens.entity.Pupils;
import com.codename1.uikit.materialscreens.entity.TimeTable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class ServiceTimeTable {

    private ConnectionRequest conx;
    public static Form listt;
    TimeTable timeTable = new TimeTable();
    List<TimeTable> tList = new ArrayList<>();
    TimeTable t;

    public ServiceTimeTable(Integer id_class, Integer id_user,String role) {

        conx = new ConnectionRequest("http://localhost/mobile/readT.php?classe=" + id_class);
        conx.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(conx.getResponseData());

                Map<String, Object> u;
                try {
                    JSONParser j = new JSONParser();
                    u = j.parseJSON(new CharArrayReader(json.toCharArray()));

                    List<Map<String, Object>> content = (List<Map<String, Object>>) u.get("root");
                    tList.clear();

                    for (Map<String, Object> obj : content) {
                        tList.add(new TimeTable(Integer.parseInt((String) obj.get("id")), Integer.parseInt((String) obj.get("classe")), (String) obj.get("pdf")));
                        timeTable.setId(Integer.parseInt((String) obj.get("id")));
                        timeTable.setId_class(Integer.parseInt((String) obj.get("classe")));
                        timeTable.setPdf((String) obj.get("pdf"));

                    }

                    System.out.println(timeTable.getId() + timeTable.getId_class() + timeTable.getPdf() + id_user);
                    new TimeTableForm(timeTable.getId(), timeTable.getId_class(), timeTable.getPdf(), id_user,role).show();
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueue(conx);

    }

}
