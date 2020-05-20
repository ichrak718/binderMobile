/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens.service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;

import com.codename1.uikit.materialscreens.entity.exam;
import com.codename1.uikit.materialscreens.entity.grade;
import com.esprit.binder.utils.Statics;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Assma
 */
public class ServiceGradu {

    public ConnectionRequest connectionRequest;
    public static Form listofgrades;
    private ConnectionRequest conx;
    public static Form listExams;
    exam e = new exam();
    List<exam> exams = new ArrayList<>();
    exam s;

    public ServiceGradu() {
    }

    public ServiceGradu(Integer id, Integer id_user, String role) {
        connectionRequest = new ConnectionRequest() {
            List<grade> grades = new ArrayList<>();

            @Override
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");

                    Map<String, Object> data = json.parseJSON(reader);
                    List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("root");
                    grades.clear();

                    for (Map<String, Object> obj : content) {
                        grades.add(new grade(Integer.parseInt((String) obj.get("id")), Integer.parseInt((String) obj.get("examname")),
                                Float.parseFloat((String) obj.get("grade")), (String) obj.get("teacher"), (String) obj.get("pupil"), Integer.parseInt((String) obj.get("idpupil"))));

                    }
                } catch (IOException err) {
                    Log.e(err);
                }
            }

            @Override
            protected void postResponse() {
                //System.out.println(libs.size());
                listofgrades = new Form();
                Button bt = new Button("Download grades to your device");
                bt.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        OutputStream stream = null;
                        try {
                            FileSystemStorage fs = FileSystemStorage.getInstance();
                            Dialog.show("success", "", "Ok", null);
                            stream = fs.openOutputStream(FileSystemStorage.getInstance().getAppHomePath() + "grades.csv");
                            String entete = "Grade;Teacher\n";
                            stream.write(entete.getBytes());
                            for (grade g : grades) {
                                String infos = "" + g.getGrade() + ";" + g.getTeacher() + "\n";
                                stream.write(infos.getBytes());
                            }
                            stream.close();
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                });
                com.codename1.ui.List uiLibsList = new com.codename1.ui.List();
                ArrayList<String> libsNoms = new ArrayList<>();
                for (grade c : grades) {
                    System.out.println("aaa" + c.getExamname());
                    libsNoms.add(c.getGrade() + " , Attributed by: " + c.getTeacher());
                }
                com.codename1.ui.list.DefaultListModel<String> listModel = new com.codename1.ui.list.DefaultListModel<>(libsNoms);
                uiLibsList.setModel(listModel);
                Button backBtn;
                backBtn = Statics.createBackBtn();
                listofgrades.setLayout(new BorderLayout());
                listofgrades.add(BorderLayout.NORTH, uiLibsList);
                listofgrades.add(BorderLayout.CENTER, bt);
                listofgrades.add(BorderLayout.SOUTH, backBtn);
                backBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        System.out.println(id+"asma2");
                         if (role.equals("a:1:{i:0;s:10:\"ROLE_PUPILS\";}")) {
                        new ServicePupil().ServicePupilR(id_user, role);}
                         else {
                             new ServiceParent().ServiceParent(id_user, role);
                         }                        
                    }
                });
                listofgrades.show();
            }
        };
        connectionRequest.setUrl("http://localhost/apimobile/readgrade.php?idpupil=" + id);
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }

    public void findgradeover10(Integer id, String role,Integer id_user) {
        float count;
        grade g = new grade();
        List<grade> grades = new ArrayList<>();
        conx = new ConnectionRequest("http://localhost/apimobile/readgradeover10.php?idpupil=" + id);
        conx.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(conx.getResponseData());

                Map<String, Object> u;
                try {
                    JSONParser j = new JSONParser();
                    u = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    List<Map<String, Object>> content = (List<Map<String, Object>>) u.get("root");
                    grades.clear();

                    for (Map<String, Object> obj : content) {

                        g.setCount(Integer.parseInt((String) obj.get("count(*)")));

                        System.out.println(g.getCount() + "1");
                    }
                    System.out.println(g.getCount() + "2");
                    //System.out.println(timeTable.getId() + timeTable.getId_class() + timeTable.getPdf() + id_user);
                    // new piechart().createPieChartForm(g.getCount(),0).show();
                    findgradeunder10(id, g.getCount(),id_user,role);
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueue(conx);

    }

    public void findgradeunder10(Integer id, Integer count, Integer id_user,String role) {

        grade g = new grade();
        List<grade> grades = new ArrayList<>();
        conx = new ConnectionRequest("http://localhost/apimobile/readgradeunder10.php?idpupil=" + id);
        conx.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(conx.getResponseData());

                Map<String, Object> u;
                try {
                    JSONParser j = new JSONParser();
                    u = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    List<Map<String, Object>> content = (List<Map<String, Object>>) u.get("root");
                    grades.clear();

                    for (Map<String, Object> obj : content) {

                        g.setCount2(Integer.parseInt((String) obj.get("count(*)")));

//System.out.println(g.getCount()+"1");
                    }
                    // System.out.println(g.getCount()+"2");
                    //System.out.println(timeTable.getId() + timeTable.getId_class() + timeTable.getPdf() + id_user);
                    new piechart().createPieChartForm(count, g.getCount2(),id_user,role).show();
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueue(conx);

    }

    /*@Override
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
            }*/
}
