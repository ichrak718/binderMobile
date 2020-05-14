/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;

import com.codename1.components.FloatingActionButton;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.materialscreens.entity.activity;
import com.codename1.uikit.materialscreens.service.ClubDAO;
import com.codename1.uikit.materialscreens.service.CourseDAO;
import com.codename1.uikit.materialscreens.service.ServiceTeacher;
import com.codename1.uikit.materialscreens.service.SubjectDAO;

/**
 *
 * @author Sam
 */
public class WelcomeTeacher extends Form {

    private Container oneEvent;
    Form f1 = new Form();
    // private final Label l1;
    private Button btnclub;
    
     Form f = new Form();
    private Form current;
    
           private final Resources res = UIManager.initFirstTheme("/theme");
           
            activity s;
      private Container mainContainer;
    public WelcomeTeacher()  {
         
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
                        BoxLayout.encloseY(
                                //*************************************
                                new Label("" , "Title")
                        )
                ).add(BorderLayout.WEST, profilePicLabel)
        );
        
        FloatingActionButton fab = FloatingActionButton.createBadge("");
        fab.setVisible(false);
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));
        
        
        
        
     Form f1 = new Form("Informations", BoxLayout.y());
    f1.setTitle("informations");
    
    f1=this;
    
f1.show();
setupSideMenu();
        
       
    }
    
    //f.getToolbar().addCommandToLeftBar(null, theme.getImage("cal_left_arrow.png"), (ev)->{HomeForm h=new HomeForm(theme);

    //});
    // f.setLayout(new BorderLayout());
    public void setupSideMenu() {
       
        Image profilePic = res.getImage("user.jpg");
        Image mask = res.getImage("round-mask.png");
        mask = mask.scaledHeight(mask.getHeight() / 4 * 3);
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label("", profilePic, "SideMenuTitle");
        profilePicLabel.setMask(mask.createMask());

        Container sidemenuTop = BorderLayout.center(profilePicLabel);
        sidemenuTop.setUIID("SidemenuTop");
       
        getToolbar().addComponentToSideMenu(sidemenuTop);
        getToolbar().addMaterialCommandToSideMenu(" Course", FontImage.MATERIAL_SAVE,e->new CourseDAO().findAllCoursesT());
        getToolbar().addMaterialCommandToSideMenu(" Subject", FontImage.MATERIAL_SUBJECT,e->new SubjectDAO().findAllSubjectsT());
      
             getToolbar().addMaterialCommandToSideMenu(" club", FontImage.MATERIAL_TOYS,e->new ClubForm().getForm().show());
            
            
      
        
          
        getToolbar().addMaterialCommandToSideMenu("  Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> new LoginForm(res).show());

    }

}
