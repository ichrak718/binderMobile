/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.materialscreens.service.AbscenseService;
import com.codename1.uikit.materialscreens.service.CourseDAO;
import com.codename1.uikit.materialscreens.service.NotificationService;
import com.codename1.uikit.materialscreens.service.ServiceGradu;
import com.codename1.uikit.materialscreens.service.ServiceParent;
import com.codename1.uikit.materialscreens.service.ServicePupil;
import com.codename1.uikit.materialscreens.service.ServiceTimeTable;
import com.codename1.uikit.materialscreens.service.SubjectDAO;
import com.codename1.util.StringUtil;
import java.io.IOException;

/**
 *
 * @author Asus
 */
public class TimeTableForm extends Form {

    private Resources res = UIManager.initFirstTheme("/theme");
    private final Container mainContainer;
String maChaine;
    Form f = new Form();

    public TimeTableForm(Integer id, Integer class_id, String pdf, Integer id_user, String role) {

        super(BoxLayout.y());

        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
        Image profilePic = res.getImage("user.jpg");
        Image mask = res.getImage("round-mask.png");
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic, "ProfilePicTitle");
        profilePicLabel.setMask(mask.createMask());
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        Container remainingTasks = BoxLayout.encloseY(
                new Label("12", "CenterTitle"),
                new Label("remaining tasks", "CenterSubTitle")
        );
        remainingTasks.setUIID("RemainingTasks");

        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton),
                BorderLayout.centerAbsolute(
                        BoxLayout.encloseY( //*************************************
                                // new Label("" + fullName, "Title")
                                )
                ).add(BorderLayout.WEST, profilePicLabel)
        );
        //*******************************************************
        FloatingActionButton fab = FloatingActionButton.createBadge("");
        fab.setVisible(false);
        //fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
        //fab.getAllStyles().setMargin(BOTTOM, completedTasks.getPreferredH() - fab.getPreferredH() / 2);
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));
        // add(new Label("Today", "TodayTitle"));
        /*
        FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);  
        addButtonBottom(arrowDown, "Finish landing page concept", 0xd997f1, true);
        addButtonBottom(arrowDown, "Design app illustrations", 0x5ae29d, false);
        addButtonBottom(arrowDown, "Javascript training ", 0x4dc2ff, false);
        addButtonBottom(arrowDown, "Surprise Party for Matt", 0xffc06f, false);*/

        //*******************************************************************
        mainContainer = new Container();
        mainContainer.setLayout(new GridLayout(8, 2));

        Label name = new Label("Time Table:");

        name.getUnselectedStyle().setFgColor(39321);
        Font name_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);

        mainContainer.add(name);
        System.out.println(pdf+"charfa");
        Button pdfBtn = new Button("Download");
                 String result = StringUtil.replaceAll(pdf, "C:\\XAMPP\\htdocs\\", "http://localhost/");
        System.out.println(result+"good");
        pdfBtn.addActionListener(e -> {
          //   stream = fs.openOutputStream(FileSystemStorage.getInstance().getAppHomePath() + "grades.csv");
            FileSystemStorage fs = FileSystemStorage.getInstance();
            String fileName = fs.getAppHomePath() + "timetable.pdf";
                Util.downloadUrlToStorage(result, fileName, true);  
                Display.getInstance().execute(fileName);
                Dialog.show("success", "", "Ok", null);
           


        });
        mainContainer.add(pdfBtn);

        add(mainContainer);
//****************************************************************
        setupSideMenu(id, id_user, class_id, role,id);
    }
    

    private void addButtonBottom(Image arrowDown, String text, int color, boolean first) {
        MultiButton finishLandingPage = new MultiButton(text);
        finishLandingPage.setEmblem(arrowDown);
        finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("TodayEntry");
        finishLandingPage.setIcon(createCircleLine(color, finishLandingPage.getPreferredH(), first));
        finishLandingPage.setIconUIID("Container");
        add(FlowLayout.encloseIn(finishLandingPage));
    }

    private Image createCircleLine(int color, int height, boolean first) {
        Image img = Image.createImage(height, height, 0);
        Graphics g = img.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0xcccccc);
        int y = 0;
        if (first) {
            y = height / 6 + 1;
        }
        g.drawLine(height / 2, y, height / 2, height);
        g.drawLine(height / 2 - 1, y, height / 2 - 1, height);
        g.setColor(color);
        g.fillArc(height / 2 - height / 4, height / 6, height / 2, height / 2, 0, 360);
        return img;
    }
//*********************************************************************************

    public void setupSideMenu(Integer id, Integer id_user, Integer class_id, String role, Integer id_pupil) {
        Image profilePic = res.getImage("user.jpg");
        Image mask = res.getImage("round-mask.png");
        mask = mask.scaledHeight(mask.getHeight() / 4 * 3);
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label("", profilePic, "SideMenuTitle");
        profilePicLabel.setMask(mask.createMask());

        Container sidemenuTop = BorderLayout.center(profilePicLabel);
        sidemenuTop.setUIID("SidemenuTop");
        System.out.println(role);
        getToolbar().addComponentToSideMenu(sidemenuTop);

 if (role.equals("a:1:{i:0;s:10:\"ROLE_PUPILS\";}")) {
            getToolbar().addMaterialCommandToSideMenu("  Profile", FontImage.MATERIAL_DASHBOARD, e -> new ServicePupil().ServicePupilR(id_user, role));
            getToolbar().addMaterialCommandToSideMenu("  Time Table", FontImage.MATERIAL_TRENDING_UP, e -> new ServiceTimeTable(class_id, id_user, role));
          
            getToolbar().addMaterialCommandToSideMenu(" Course", FontImage.MATERIAL_SAVE,e->new CourseDAO().findAllCourses());
            getToolbar().addMaterialCommandToSideMenu(" Subject", FontImage.MATERIAL_SUBJECT,e->new SubjectDAO().findAllSubjects(id,role,id_pupil));
            getToolbar().addMaterialCommandToSideMenu(" club", FontImage.MATERIAL_TOYS,e->new ClubForm().getForm(class_id,id,role).show());    
              
           getToolbar().addMaterialCommandToSideMenu("  Notification", FontImage.MATERIAL_TRENDING_UP, e -> new NotificationService().findAllNotificationsiD(id_user, role,id_pupil));
          getToolbar().addMaterialCommandToSideMenu(" Abscenses", FontImage.MATERIAL_TRENDING_UP, e -> new AbscenseService().findAbscensesiD(id, role,id_pupil));
           getToolbar().addMaterialCommandToSideMenu(" Course", FontImage.MATERIAL_SAVE,e->new CourseDAO().findAllCourses());
            getToolbar().addMaterialCommandToSideMenu(" Subject", FontImage.MATERIAL_SUBJECT,e->new SubjectDAO().findAllSubjects(id_user,role,id_pupil));
           
            getToolbar().addMaterialCommandToSideMenu(" signUp", FontImage.MATERIAL_ACCOUNT_CIRCLE, e->new ClubSignUp(class_id,id_user,role).show() );
              getToolbar().addMaterialCommandToSideMenu("  Grade", FontImage.MATERIAL_ACCESS_TIME,  e -> new ServiceGradu(id_pupil,id,role));
           getToolbar().addMaterialCommandToSideMenu("  Statistics", FontImage.MATERIAL_ACCESS_TIME,  e -> new ServiceGradu().findgradeover10(id_pupil,role,id));
           
            getToolbar().addMaterialCommandToSideMenu(" opinion",  FontImage.MATERIAL_MAIL,  e -> new Opinion(class_id,id_user,role).show());
 }
  else if (role.equals("a:1:{i:0;s:10:\"ROLE_PARENT\";}")) {
             
           
        
      getToolbar().addMaterialCommandToSideMenu(" Profil", FontImage.MATERIAL_TOYS, e -> new ServiceParent().ServiceParent(id_user, role));
      getToolbar().addMaterialCommandToSideMenu(" Course", FontImage.MATERIAL_SAVE,e->new CourseDAO().findAllCoursesP());  
      getToolbar().addMaterialCommandToSideMenu(" Subject", FontImage.MATERIAL_SUBJECT,e->new SubjectDAO().findAllSubjectsP());
             getToolbar().addMaterialCommandToSideMenu("  Grade", FontImage.MATERIAL_ACCESS_TIME,  e -> new ServiceGradu(id_user,id_pupil,role));
        getToolbar().addMaterialCommandToSideMenu("  Statistics", FontImage.MATERIAL_ACCESS_TIME,  e -> new ServiceGradu().findgradeover10(id_user,role,id_pupil));
      getToolbar().addMaterialCommandToSideMenu(" club", FontImage.MATERIAL_TOYS,e->new ClubForm().getForm(class_id,id_user,role).show());
    
        
      getToolbar().addMaterialCommandToSideMenu("  Time Table", FontImage.MATERIAL_TRENDING_UP, e -> new ServiceTimeTable(class_id, id_user, role));       
  }
        
          

    
            
        getToolbar().addMaterialCommandToSideMenu("  Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> new LoginForm(res).show());

    }
}
