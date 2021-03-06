/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.materialscreens.entity.activity;
import com.codename1.uikit.materialscreens.service.ServicePupil;
import com.codename1.uikit.materialscreens.service.ServiceTimeTable;

/**
 *
 * @author Assma
 */
public class CommentDetails extends Form{
    
    Form f = new Form();
    private Form current;
    
           private final Resources res = UIManager.initFirstTheme("/theme");
           
            activity s;
      private Container mainContainer;
      private Button btnRetour;
      
      public CommentDetails(int id, String content, int id_Activity,Integer id_class,Integer id_user,String role){
          
          
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
                                new Label("" +  "Comment details")
                        )
                ).add(BorderLayout.WEST, profilePicLabel)
        );
        
        FloatingActionButton fab = FloatingActionButton.createBadge("");
        fab.setVisible(false);
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));
        
        
        
        
        
        
         mainContainer = new Container();
        mainContainer.setLayout(new GridLayout(8, 2));

        Label contentA = new Label("content:");

        contentA.getUnselectedStyle().setFgColor(39321);
        Font  contentA_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);

        Label ContentAA = new Label(content);

        ContentAA.getUnselectedStyle().setFgColor(8482);
        Font ContentAA_fontA = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM);
        mainContainer.add(contentA);
        mainContainer.add(ContentAA);
       btnRetour = new Button("Go to Home");
       btnRetour.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent evt) {
                   System.out.println(id_user+role+"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
new ServicePupil().ServicePupilR(id_user, role);               }
           });

        
mainContainer.add(btnRetour);
     
        
        //add(btnRetour);
    
        add(mainContainer);

     Form f1 = new Form("commentDetails", BoxLayout.y());
    f1.setTitle("Comments");
    
    f1=this;
    
f1.show();

setupSideMenu(id_user, id_class, role);
    
}
    
     public CommentDetails(int id, String content, int id_Activity){
        
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
                                new Label("" +  "comments")
                        )
                ).add(BorderLayout.WEST, profilePicLabel)
        );
        
        FloatingActionButton fab = FloatingActionButton.createBadge("");
        fab.setVisible(false);
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));
        
        
        
         
        
         mainContainer = new Container();
        mainContainer.setLayout(new GridLayout(8, 2));

         Label contentA = new Label("content:");

        contentA.getUnselectedStyle().setFgColor(39321);
        Font  contentA_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);

        Label ContentAA = new Label(content);

        ContentAA.getUnselectedStyle().setFgColor(8482);
        Font ContentAA_fontA = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM);
        mainContainer.add(contentA);
        mainContainer.add(ContentAA);
        
        btnRetour = new Button("Go to Home");
        
                mainContainer.add(btnRetour);
                
                
        add(mainContainer);
        
        
       
        
        
        
        


     Form f1 = new Form("Informations", BoxLayout.y());
     
    f1.setTitle("informations");
    
    f1=this;
    
    f1.show();


//setupSideMenu();
    
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
    
    public void setupSideMenu(Integer id,Integer id_class, String role) {
       
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
            getToolbar().addMaterialCommandToSideMenu("  Profile", FontImage.MATERIAL_DASHBOARD, e -> new ServicePupil().ServicePupilR(id, role));
            getToolbar().addMaterialCommandToSideMenu("  Time Table", FontImage.MATERIAL_TRENDING_UP, e -> new ServiceTimeTable(id_class, id, role));
        getToolbar().addMaterialCommandToSideMenu(" club", FontImage.MATERIAL_TOYS,e->new ClubForm().getForm(id_class,id,role).show());
            
           getToolbar().addMaterialCommandToSideMenu(" signUp", FontImage.MATERIAL_ACCOUNT_CIRCLE, e->new ClubSignUp(id_class,id,role).show() );
           
           
           getToolbar().addMaterialCommandToSideMenu(" opinion",  FontImage.MATERIAL_MAIL,  e -> new Opinion(id_class,id,role).show());
        }
        else  if (role.equals("a:1:{i:0;s:10:\"ROLE_TEACHER\";}")) {
             getToolbar().addMaterialCommandToSideMenu(" club", FontImage.MATERIAL_TOYS,e->new ClubForm().getForm(id_class,id,role).show());
            
            }
        else if (role.equals("a:1:{i:0;s:10:\"ROLE_PARENT\";}")) {
             
           
           getToolbar().addMaterialCommandToSideMenu(" opinion",  FontImage.MATERIAL_MAIL,  e -> new Opinion(id_class,id,role).show());
             }
        
          
        getToolbar().addMaterialCommandToSideMenu("  Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> new LoginForm(res).show());

    }


    
}
