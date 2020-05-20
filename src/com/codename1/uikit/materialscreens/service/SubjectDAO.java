/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens.service;

import com.codename1.components.FloatingActionButton;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.uikit.materialscreens.AddSubject;
import com.codename1.uikit.materialscreens.Asubject;
import com.codename1.uikit.materialscreens.BrowserSub;
import com.codename1.uikit.materialscreens.WelcomeParent;
import com.codename1.uikit.materialscreens.WelcomePupil;
import com.codename1.uikit.materialscreens.WelcomeTeacher;
import com.codename1.uikit.materialscreens.entity.Subject;

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
 * @author wael
 */
public class SubjectDAO {
    private ConnectionRequest connectionRequest;
    public static Form listOfSubjects;
    private TextField tfSearch;
    public void addSubject(Subject subject){
        connectionRequest=new ConnectionRequest(){
            @Override
            protected void postResponse() {
            
            Dialog.show("succed", "subject has been added", "OK", null);
                new SubjectDAO().findAllSubjectsT();

            }
        };
        connectionRequest.setUrl("http://localhost/binder/insertsubject.php?name=" + subject.getName()+ "&teachers=" + subject.getTeachers()+"&classes="+subject.getClasses());
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
    public void removeSubject(Subject s){   // remove subject by id
        connectionRequest = new ConnectionRequest() {
            @Override
            protected void postResponse() {
            Dialog.show("succed", "Subject has been deleted", "OK", null);
            new SubjectDAO().findAllSubjectsT();
            }           
        };
        connectionRequest.setUrl("http://localhost/binder/removesubject.php?id=" + s.getId());
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
    public void findAllSubjects(Integer id_user,String role ,Integer id_pupil){
        connectionRequest = new ConnectionRequest() {
        List<Subject> subjects = new ArrayList<>();
            @Override
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");

                    Map<String, Object> data = json.parseJSON(reader);
                    List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("root");
                    subjects.clear();
                  
                    for (Map<String, Object> obj : content) {
                        subjects.add(new Subject(Integer.parseInt((String) obj.get("id")), (String) obj.get("name"),(String) obj.get("teachers"),(String) obj.get("classes"))
                        );
                    }
                } catch (IOException err) {
                    Log.e(err);
                }
            }

            @Override
            protected void postResponse() {
                //System.out.println(libs.size());
                Toolbar.setGlobalToolbar(true);

                listOfSubjects = new Form("All subjects");
                
                Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
                FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_BACKSPACE, s);
                FontImage icon2 = FontImage.createMaterial(FontImage.MATERIAL_SUBJECT, s);
                
                
                
                listOfSubjects.getToolbar().addCommandToLeftBar("Return", icon, (e) -> new ServicePupil().ServicePupilR(id_user, role));
                //listOfCourses.getToolbar().addCommandToRightBar("Open", icon2, (e) -> new Browser());
                com.codename1.ui.List uiLibsList = new com.codename1.ui.List();
                ArrayList<String> libsNoms = new ArrayList<>();
                for(Subject sub :subjects){
                    libsNoms.add(sub.getName()+" teached by "+sub.getTeachers()+"=>"+sub.getClasses());
                }
                com.codename1.ui.list.DefaultListModel<String> listModel = new com.codename1.ui.list.DefaultListModel<>(libsNoms);
                uiLibsList.setModel(listModel);
                uiLibsList.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        
                        Subject currentSubject = subjects.get(uiLibsList.getCurrentSelected());
                        //new Acourse(currentCourse.getId(), currentCourse.getSubject(),currentCourse.getContent(),currentCourse.getTeacher()).show();
                        new BrowserSub("https://www.google.com/search?q="+currentSubject.getName());
                    }
                    
                });
                
                
                listOfSubjects.setLayout(new BorderLayout());
                listOfSubjects.add(BorderLayout.CENTER,uiLibsList);
                
                tfSearch = new TextField(null, "Tap to search..");
                listOfSubjects.add(BorderLayout.NORTH,tfSearch);
                tfSearch.addActionListener((ActionListener) (ActionEvent evt) -> {
                    if(tfSearch.getText().length() > 0) {
                    
                } else {
                    Dialog.show("Error", "You need to enter a search string", "OK", null);
                }
                    
                });
                
                
                
             
                
                
                listOfSubjects.show();             
            }

          
        };
        connectionRequest.setUrl("http://localhost/binder/getsubjects.php");
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
    
    private void searchSubject(ActionEvent event) {
        
	
    }
    
    public void updateSubject(Subject s){
        connectionRequest = new ConnectionRequest() {
            
            @Override
            protected void postResponse() { 
                Dialog.show("succed", "Subject has been updated", "OK", null);
                new SubjectDAO().findAllSubjectsT();
            }
        };
        connectionRequest.setUrl("http://localhost/binder/updatesubject.php?id="+s.getId()+"&name="+s.getName()+
                                "&teachers="+s.getTeachers()+"&classes="+s.getClasses());
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }

    public void findAllSubjectsP() {
        connectionRequest = new ConnectionRequest() {
        List<Subject> subjects = new ArrayList<>();
            @Override
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");

                    Map<String, Object> data = json.parseJSON(reader);
                    List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("root");
                    subjects.clear();
                  
                    for (Map<String, Object> obj : content) {
                        subjects.add(new Subject(Integer.parseInt((String) obj.get("id")), (String) obj.get("name"),(String) obj.get("teachers"),(String) obj.get("classes"))
                        );
                    }
                } catch (IOException err) {
                    Log.e(err);
                }
            }

            @Override
            protected void postResponse() {
                //System.out.println(libs.size());
                Toolbar.setGlobalToolbar(true);

                listOfSubjects = new Form("All subjects");
                
                Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
                FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_BACKSPACE, s);
                FontImage icon2 = FontImage.createMaterial(FontImage.MATERIAL_SUBJECT, s);
                
                
                
                listOfSubjects.getToolbar().addCommandToLeftBar("Return", icon, (e) -> new WelcomeParent(19, 37,"a:1:{i:0;s:10:\"ROLE_PARENT\";}", "parent", "parent@gmail.com", "23056683", "anwar", 19, 19).showBack());
                //listOfCourses.getToolbar().addCommandToRightBar("Open", icon2, (e) -> new Browser());
                com.codename1.ui.List uiLibsList = new com.codename1.ui.List();
                ArrayList<String> libsNoms = new ArrayList<>();
                for(Subject sub :subjects){
                    libsNoms.add(sub.getName()+" teached by "+sub.getTeachers()+"=>"+sub.getClasses());
                }
                com.codename1.ui.list.DefaultListModel<String> listModel = new com.codename1.ui.list.DefaultListModel<>(libsNoms);
                uiLibsList.setModel(listModel);
                uiLibsList.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        
                        Subject currentSubject = subjects.get(uiLibsList.getCurrentSelected());
                        //new Acourse(currentCourse.getId(), currentCourse.getSubject(),currentCourse.getContent(),currentCourse.getTeacher()).show();
                        new BrowserSub("https://www.google.com/search?q="+currentSubject.getName());
                    }
                    
                });
                
                
                listOfSubjects.setLayout(new BorderLayout());
                listOfSubjects.add(BorderLayout.CENTER,uiLibsList);
                listOfSubjects.add(BorderLayout.SOUTH,Statics.createBackBtn());
                tfSearch = new TextField(null, "Tap to search..");
                listOfSubjects.add(BorderLayout.NORTH,tfSearch);
                tfSearch.addActionListener((ActionListener) (ActionEvent evt) -> {
                    if(tfSearch.getText().length() > 0) {
                    
                } else {
                    Dialog.show("Error", "You need to enter a search string", "OK", null);
                }
                    
                });
                
             
                
                
                listOfSubjects.show();             
            }

          
        };
        connectionRequest.setUrl("http://localhost/binder/getsubjects.php");
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }

    public void findAllSubjectsT() {
        connectionRequest = new ConnectionRequest() {
        List<Subject> subjects = new ArrayList<>();
            @Override
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");

                    Map<String, Object> data = json.parseJSON(reader);
                    List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("root");
                    subjects.clear();
                  
                    for (Map<String, Object> obj : content) {
                        subjects.add(new Subject(Integer.parseInt((String) obj.get("id")), (String) obj.get("name"),(String) obj.get("teachers"),(String) obj.get("classes"))
                        );
                    }
                } catch (IOException err) {
                    Log.e(err);
                }
            }

            @Override
            protected void postResponse() {
                //System.out.println(libs.size());
                Toolbar.setGlobalToolbar(true);

                listOfSubjects = new Form("All subjects");
                
                Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
                FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_BACKSPACE, s);
                FontImage icon2 = FontImage.createMaterial(FontImage.MATERIAL_SUBJECT, s);
                
                
                
                listOfSubjects.getToolbar().addCommandToLeftBar("Return", icon, (e) -> new WelcomeTeacher().showBack());
                //listOfCourses.getToolbar().addCommandToRightBar("Open", icon2, (e) -> new Browser());
                com.codename1.ui.List uiLibsList = new com.codename1.ui.List();
                ArrayList<String> libsNoms = new ArrayList<>();
                for(Subject sub :subjects){
                    libsNoms.add(sub.getName()+" teached by "+sub.getTeachers()+"=>"+sub.getClasses());
                }
                com.codename1.ui.list.DefaultListModel<String> listModel = new com.codename1.ui.list.DefaultListModel<>(libsNoms);
                uiLibsList.setModel(listModel);
                uiLibsList.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        
                        Subject currentSubject = subjects.get(uiLibsList.getCurrentSelected());
                        new Asubject(currentSubject.getId(), currentSubject.getName(),currentSubject.getTeachers(),currentSubject.getClasses()).show();
                        //new Browser("https://www.google.com/search?q="+currentSubject.getName());
                    }
                    
                });
                
                
                listOfSubjects.setLayout(new BorderLayout());
                listOfSubjects.add(BorderLayout.CENTER,uiLibsList);
                listOfSubjects.add(BorderLayout.SOUTH,Statics.createBackBtn());
                tfSearch = new TextField(null, "Tap to search..");
                listOfSubjects.add(BorderLayout.NORTH,tfSearch);
                tfSearch.addActionListener((ActionListener) (ActionEvent evt) -> {
                    if(tfSearch.getText().length() > 0) {
                    
                } else {
                    Dialog.show("Error", "You need to enter a search string", "OK", null);
                }
                    
                });
                
             FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
                //fab.addActionListener(e -> ToastBar.showErrorMessage("Not implemented yet..."));
                
                fab.addActionListener((ActionListener) (ActionEvent evt) -> {
                AddSubject addSubjectUi = new AddSubject();
                addSubjectUi.show();
                });
                fab.bindFabToContainer(listOfSubjects.getContentPane());
                
                
                listOfSubjects.show();             
            }

          
        };
        connectionRequest.setUrl("http://localhost/binder/getsubjects.php");
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
    
}
