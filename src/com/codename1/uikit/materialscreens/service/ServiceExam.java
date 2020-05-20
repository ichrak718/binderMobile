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
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;

import com.codename1.uikit.materialscreens.entity.exam;
import com.codename1.uikit.materialscreens.entity.grade;
import static com.codename1.uikit.materialscreens.service.ServiceGradu.listofgrades;
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
 * @author Assma
 */
public class ServiceExam {

    private ConnectionRequest conx;
    public static Form listExams;
    exam e = new exam();
   
    exam s;

    public ServiceExam() {
    }
/*
    public ServiceExam(Integer id, Float grade, String teacher, String pupil, Integer id_user, Integer id_pupil) {
        System.out.println(id);
        ArrayList<grade> a = new ArrayList<>();
        conx = new ConnectionRequest("http://localhost/apimobile/readexam.php?id=" + id);
        conx.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(conx.getResponseData());
                System.out.println(json + "cccccc");
                Map<String, Object> u;
                try {
                    JSONParser j = new JSONParser();
                    u = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    System.out.println(json + "bbbbbbbbbbb");
                    List<Map<String, Object>> content = (List<Map<String, Object>>) u.get("root");
                    exams.clear();

                    for (Map<String, Object> obj : content) {
                        exams.add(new exam(Integer.parseInt((String) obj.get("id")), (String) obj.get("subject"), (String) obj.get("file"),
                                Integer.parseInt((String) obj.get("duration")),
                                (String) obj.get("date")));
                        e.setId(Integer.parseInt((String) obj.get("id")));
                        e.setSubject((String) obj.get("subject"));
                        e.setFile((String) obj.get("file"));
                        e.setDuration(Integer.parseInt((String) obj.get("duration")));
                        e.setDate((String) obj.get("date"));
                    }

                    new GradeForm(id, e.getSubject(), grade, teacher, pupil, id, id_user).show();

                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueue(conx);

    }*/
  /*  
    public exam finding( Integer examname) {
        
        
        ArrayList<grade> a = new ArrayList<>();
         List<exam> exams = new ArrayList<>();
        conx = new ConnectionRequest("http://localhost/apimobile/readexam.php?id=" + examname);
        
        conx.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(conx.getResponseData());
             
                Map<String, Object> u;
                try {
                    JSONParser j = new JSONParser();
                    u = j.parseJSON(new CharArrayReader(json.toCharArray()));
                  
                    List<Map<String, Object>> content = (List<Map<String, Object>>) u.get("root");
                    exams.clear();

                    for (Map<String, Object> obj : content) {
                        exams.add(new exam((String )obj.get("subject")));
                     
                        e.setSubject((String) obj.get("subject"));
                      
                        System.out.println("ssss"+e.getSubject());
                       // new  ServiceGradu(id, id_user, e.getSubject());
                        
                        
                        
                    }
     
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueue(conx);
        System.out.println(e);
        
            System.out.println(e.getSubject()+"hahah");
       
return  e;
    }
    
    */
}


