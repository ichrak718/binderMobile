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
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.materialscreens.entity.participation;
import com.codename1.uikit.materialscreens.entity.Pupils;
import com.codename1.uikit.materialscreens.service.ClubDAO;
import com.codename1.uikit.materialscreens.service.ServicePupil;
import com.codename1.uikit.materialscreens.service.ServiceTimeTable;

/**
 *
 * @author Sam
 */
public class ClubSignUp extends Form {

    private Container mainContainer;
    private Form current;
    private final Resources res = UIManager.initFirstTheme("/theme");
    private Container oneEvent;
    // Form f1 = new Form();
    // private final Label l1;
    private Button btnSignUp;
    private Button btnRetour;

    public ClubSignUp(Integer id_class, Integer id_user, String role) {

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
                                new Label("sign Up!")
                        )
                ).add(BorderLayout.WEST, profilePicLabel)
        );

        FloatingActionButton fab = FloatingActionButton.createBadge("");
        fab.setVisible(false);
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));

        Form signup = new Form("", BoxLayout.y());
        signup = this;
        Label Lname = new Label("name");
        Label Lclasse = new Label("classe");
        Label Lclub = new Label("club");

        signup.setTitle("inscription");
        TextField tfname = new TextField("", "name");
        TextField tfclasse = new TextField("", "classe");
        TextField tfclub = new TextField("", "club");

        signup.add(tfname);
        signup.add(tfclasse);
        signup.add(tfclub);

        Button btnValider = new Button("valider");
 
        signup.add(btnValider);

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                participation p = new participation(tfname.getText(), tfclasse.getText(), tfclub.getText());
                new ClubDAO().addMember(p);

            }
        });

        
        btnRetour = new Button("Home");
        
        signup.add(btnRetour);
        
        
        
        btnRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
             new  ServicePupil().ServicePupilR(id_user, role);
            }
        });
        
        
        
        signup.show();

        setupSideMenu(id_user, id_class, role);

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

    public void setupSideMenu(Integer id_user, Integer id_class, String role) {
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
            getToolbar().addMaterialCommandToSideMenu("  Time Table", FontImage.MATERIAL_TRENDING_UP, e -> new ServiceTimeTable(id_class, id_user, role));
         getToolbar().addMaterialCommandToSideMenu(" club", FontImage.MATERIAL_TOYS,e->new ClubForm().getForm(id_class,id_user,role).show());
            
           getToolbar().addMaterialCommandToSideMenu(" signUp", FontImage.MATERIAL_ACCOUNT_CIRCLE, e->new ClubSignUp(id_class,id_user,role).show() );
           
           
           getToolbar().addMaterialCommandToSideMenu(" opinion",  FontImage.MATERIAL_MAIL,  e -> new Opinion(id_class,id_user,role).show());
        }
        else  if (role.equals("a:1:{i:0;s:10:\"ROLE_TEACHER\";}")) {
             getToolbar().addMaterialCommandToSideMenu(" club", FontImage.MATERIAL_TOYS,e->new ClubForm().getForm(id_class,id_user,role).show());
            
            }
        else if (role.equals("a:1:{i:0;s:10:\"ROLE_PARENT\";}")) {
             
           
           getToolbar().addMaterialCommandToSideMenu(" opinion",  FontImage.MATERIAL_MAIL,  e -> new Opinion(id_class,id_user,role).show());
             }
         getToolbar().addMaterialCommandToSideMenu("  Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> new LoginForm(res).show());

    }

}
