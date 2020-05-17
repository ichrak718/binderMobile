/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens.service;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;

import java.io.IOException;
import com.codename1.ui.TextField;
import com.codename1.uikit.materialscreens.Acourse;
import com.codename1.uikit.materialscreens.AddCourse;
import com.codename1.uikit.materialscreens.Browser;
import com.codename1.uikit.materialscreens.WelcomeParent;
import com.codename1.uikit.materialscreens.WelcomePupil;
import com.codename1.uikit.materialscreens.WelcomeTeacher;
import com.codename1.uikit.materialscreens.entity.Course;
import com.esprit.binder.utils.Statics;
import java.util.ArrayList;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;

/**
 *
 * @author wael
 */
public class CourseDAO {
    private ConnectionRequest connectionRequest;
    public static Form listOfCourses;
    private TextField tfSearch;
    public void addCourse(Course course){
        connectionRequest=new ConnectionRequest(){
            @Override
            protected void postResponse() {
            
            Dialog.show("succed", "Course has been added", "OK", null);
                new CourseDAO().findAllCoursesT();

            }
        };
        connectionRequest.setUrl("http://localhost/binder/insertcourse.php?subject=" + course.getSubject()+ "&content=" + course.getContent()+"&teacher="+course.getTeacher());
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
    public void removeCourse(Course c){   // remove course by id
        connectionRequest = new ConnectionRequest() {
            @Override
            protected void postResponse() {
            Dialog.show("succed", "Course has been deleted", "OK", null);
            new CourseDAO().findAllCoursesT();
            }           
        };
        connectionRequest.setUrl("http://localhost/binder/removecourse.php?id=" + c.getId());
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
    public void findAllCourses(){
        connectionRequest = new ConnectionRequest() {
        List<Course> courses = new ArrayList<>();
            @Override
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");

                    Map<String, Object> data = json.parseJSON(reader);
                    List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("root");
                    courses.clear();
                  
                    for (Map<String, Object> obj : content) {
                        courses.add(new Course(Integer.parseInt((String) obj.get("id")), (String) obj.get("subject"),(String) obj.get("content"),(String) obj.get("teacher"))
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

                listOfCourses = new Form("All courses");
                
                Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
                FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_BACKSPACE, s);
                FontImage icon2 = FontImage.createMaterial(FontImage.MATERIAL_BOOK, s);
                
                
                
                listOfCourses.getToolbar().addCommandToLeftBar("Return", icon, (e) -> new WelcomePupil(16, "anwar", "02/02/2000", "anwar@gmail.com", "3a26", 16, 16, "a:1:{i:0;s:10:\"ROLE_PUPILS\";}").showBack());
                //listOfCourses.getToolbar().addCommandToRightBar("Open", icon2, (e) -> new Browser());
                com.codename1.ui.List uiLibsList = new com.codename1.ui.List();
                ArrayList<String> libsNoms = new ArrayList<>();
                for(Course c :courses){
                    libsNoms.add(c.getSubject()+" uploaded by "+c.getTeacher()+"=>"+c.getContent());
                }
                com.codename1.ui.list.DefaultListModel<String> listModel = new com.codename1.ui.list.DefaultListModel<>(libsNoms);
                uiLibsList.setModel(listModel);
                uiLibsList.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        
                        Course currentCourse = courses.get(uiLibsList.getCurrentSelected());
                        //new Acourse(currentCourse.getId(), currentCourse.getSubject(),currentCourse.getContent(),currentCourse.getTeacher()).show();
                        new Browser(currentCourse.getContent());
                    }
                    
                });
                
                
                listOfCourses.setLayout(new BorderLayout());
                listOfCourses.add(BorderLayout.CENTER,uiLibsList);
                listOfCourses.add(BorderLayout.SOUTH,Statics.createBackBtn());
                tfSearch = new TextField(null, "Tap to search..");
                listOfCourses.add(BorderLayout.NORTH,tfSearch);
                tfSearch.addActionListener((ActionListener) (ActionEvent evt) -> {
                    if(tfSearch.getText().length() > 0) {
                    
                } else {
                    Dialog.show("Error", "You need to enter a search string", "OK", null);
                }
                    
                });
                
             
                
                
                listOfCourses.show();             
            }

          
        };
        connectionRequest.setUrl("http://localhost/binder/getcourses.php");
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
    
    private void searchCourse(ActionEvent event) {
        
	
    }
    
    public void updateCourse(Course c){
        connectionRequest = new ConnectionRequest() {
            
            @Override
            protected void postResponse() { 
                Dialog.show("succed", "Course has been updated", "OK", null);
                new CourseDAO().findAllCoursesT();
            }
        };
        connectionRequest.setUrl("http://localhost/binder/updatecourse.php?id="+c.getId()+"&subject="+c.getSubject()+
                                "&content="+c.getContent()+"&teacher="+c.getTeacher());
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }

    public void findAllCoursesP() {
        connectionRequest = new ConnectionRequest() {
        List<Course> courses = new ArrayList<>();
            @Override
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");

                    Map<String, Object> data = json.parseJSON(reader);
                    List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("root");
                    courses.clear();
                  
                    for (Map<String, Object> obj : content) {
                        courses.add(new Course(Integer.parseInt((String) obj.get("id")), (String) obj.get("subject"),(String) obj.get("content"),(String) obj.get("teacher"))
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

                listOfCourses = new Form("All courses");
                
                Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
                FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_BACKSPACE, s);
                FontImage icon2 = FontImage.createMaterial(FontImage.MATERIAL_BOOK, s);
                
                
                
                listOfCourses.getToolbar().addCommandToLeftBar("Return", icon, (e) -> new WelcomeParent(19, "a:1:{i:0;s:10:\"ROLE_PARENT\";}", "parent", "parent@gmail.com", "23056683", "anwar", 19, 19).showBack());
                //listOfCourses.getToolbar().addCommandToRightBar("Open", icon2, (e) -> new Browser());
                com.codename1.ui.List uiLibsList = new com.codename1.ui.List();
                ArrayList<String> libsNoms = new ArrayList<>();
                for(Course c :courses){
                    libsNoms.add(c.getSubject()+" uploaded by "+c.getTeacher()+"=>"+c.getContent());
                }
                com.codename1.ui.list.DefaultListModel<String> listModel = new com.codename1.ui.list.DefaultListModel<>(libsNoms);
                uiLibsList.setModel(listModel);
                uiLibsList.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        
                        Course currentCourse = courses.get(uiLibsList.getCurrentSelected());
                        //new Acourse(currentCourse.getId(), currentCourse.getSubject(),currentCourse.getContent(),currentCourse.getTeacher()).show();
                        new Browser(currentCourse.getContent());
                    }
                    
                });
                
                
                listOfCourses.setLayout(new BorderLayout());
                listOfCourses.add(BorderLayout.CENTER,uiLibsList);
                listOfCourses.add(BorderLayout.SOUTH,Statics.createBackBtn());
                tfSearch = new TextField(null, "Tap to search..");
                listOfCourses.add(BorderLayout.NORTH,tfSearch);
                tfSearch.addActionListener((ActionListener) (ActionEvent evt) -> {
                    if(tfSearch.getText().length() > 0) {
                    
                } else {
                    Dialog.show("Error", "You need to enter a search string", "OK", null);
                }
                    
                });
                
             
                
                
                listOfCourses.show();             
            }

          
        };
        connectionRequest.setUrl("http://localhost/binder/getcourses.php");
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }

    public void findAllCoursesT() {
        connectionRequest = new ConnectionRequest() {
        List<Course> courses = new ArrayList<>();
            @Override
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");

                    Map<String, Object> data = json.parseJSON(reader);
                    List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("root");
                    courses.clear();
                  
                    for (Map<String, Object> obj : content) {
                        courses.add(new Course(Integer.parseInt((String) obj.get("id")), (String) obj.get("subject"),(String) obj.get("content"),(String) obj.get("teacher"))
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

                listOfCourses = new Form("All courses");
                
                Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
                FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_BACKSPACE, s);
                FontImage icon2 = FontImage.createMaterial(FontImage.MATERIAL_BOOK, s);
                
                
                
                listOfCourses.getToolbar().addCommandToLeftBar("Return", icon, (e) -> new WelcomeTeacher().showBack());
                //listOfCourses.getToolbar().addCommandToRightBar("Open", icon2, (e) -> new Browser());
                com.codename1.ui.List uiLibsList = new com.codename1.ui.List();
                ArrayList<String> libsNoms = new ArrayList<>();
                for(Course c :courses){
                    libsNoms.add(c.getSubject()+" uploaded by "+c.getTeacher()+"=>"+c.getContent());
                }
                com.codename1.ui.list.DefaultListModel<String> listModel = new com.codename1.ui.list.DefaultListModel<>(libsNoms);
                uiLibsList.setModel(listModel);
                uiLibsList.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        
                        Course currentCourse = courses.get(uiLibsList.getCurrentSelected());
                        new Acourse(currentCourse.getId(), currentCourse.getSubject(),currentCourse.getContent(),currentCourse.getTeacher()).show();
                        //new Browser(currentCourse.getContent());
                    }
                    
                });
                
                
                listOfCourses.setLayout(new BorderLayout());
                listOfCourses.add(BorderLayout.CENTER,uiLibsList);
                
                tfSearch = new TextField(null, "Tap to search..");
                listOfCourses.add(BorderLayout.NORTH,tfSearch);
                tfSearch.addActionListener((ActionListener) (ActionEvent evt) -> {
                    if(tfSearch.getText().length() > 0) {
                    
                } else {
                    ToastBar.showErrorMessage("Empty search case...");
                }
                    
                });
                
                FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
                //fab.addActionListener(e -> ToastBar.showErrorMessage("Not implemented yet..."));
                
                fab.addActionListener((ActionListener) (ActionEvent evt) -> {
                AddCourse addCourseUi = new AddCourse();
                addCourseUi.show();
                });
                fab.bindFabToContainer(listOfCourses.getContentPane());
                
             
                
                
                listOfCourses.show();             
            }

          
        };
        connectionRequest.setUrl("http://localhost/binder/getcourses.php");
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
    
}
