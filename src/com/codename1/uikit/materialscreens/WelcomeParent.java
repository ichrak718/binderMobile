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
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.materialscreens.entity.activity;
import com.codename1.uikit.materialscreens.service.ClubDAO;
import com.codename1.uikit.materialscreens.service.ServiceParent;
import com.codename1.uikit.materialscreens.service.ServicePupil;
import com.codename1.uikit.materialscreens.service.ServiceTeacher;
import com.codename1.uikit.materialscreens.service.ServiceTimeTable;

/**
 *
 * @author Sam
 */
public class WelcomeParent extends Form {

    private Container oneEvent;
    Form f1 = new Form();
    // private final Label l1;
    private Button btnclub;

    Form f = new Form();
    private Form current;

    private final Resources res = UIManager.initFirstTheme("/theme");

    activity s;
    private Container mainContainer;

    public WelcomeParent(Integer id, String role, String name, String mail, String phone, String namePupil, Integer id_class,Integer id_user) {

        super(BoxLayout.y());

        Toolbar tb = getToolbar();
        tb.setTitleCentered(false);

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
                                new Label(name, "Title")
                        )
                )
        );

        FloatingActionButton fab = FloatingActionButton.createBadge("");
        fab.setVisible(false);
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));

        mainContainer = new Container();
        mainContainer.setLayout(new GridLayout(8, 2));

        Label nameP = new Label("Name:");

        nameP.getUnselectedStyle().setFgColor(39321);
        Font name_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);

        Label nameA = new Label(name);

        nameA.getUnselectedStyle().setFgColor(8482);
        Font name_fontA = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM);
        mainContainer.add(nameP);
        mainContainer.add(nameA);

        Label EmailLabel = new Label("Email:");
        EmailLabel.getUnselectedStyle().setFgColor(39321);
        Font Email_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
        Label EmailA = new Label(mail);
        EmailA.getUnselectedStyle().setFgColor(8482);
        Font birthday_fontA = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM);
        mainContainer.add(EmailLabel);
        mainContainer.add(EmailA);

        Label pupilLabel = new Label("Pupil Name:");
        pupilLabel.getUnselectedStyle().setFgColor(39321);
        Font email_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
        Label pupilA = new Label(name);
        pupilA.getUnselectedStyle().setFgColor(8482);
        Font email_fontA = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM);
        mainContainer.add(pupilLabel);
        mainContainer.add(pupilA);

        Label phoneLabel = new Label("phone:");
        phoneLabel.getUnselectedStyle().setFgColor(39321);
        Font class_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
        Label phoneA = new Label(phone);
        phoneA.getUnselectedStyle().setFgColor(8482);
        Font class_fontA = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM);
        mainContainer.add(phoneLabel);
        mainContainer.add(phoneA);

        add(mainContainer);

        setupSideMenu(id, name, id_class, role, mail, phone,id_user);

    }

    //f.getToolbar().addCommandToLeftBar(null, theme.getImage("cal_left_arrow.png"), (ev)->{HomeForm h=new HomeForm(theme);
    //});
    // f.setLayout(new BorderLayout());
    public void setupSideMenu(Integer id, String name, Integer id_class, String role, String mail, String phone,Integer id_user) {

        Image profilePic = res.getImage("user.jpg");
        Image mask = res.getImage("round-mask.png");
        mask = mask.scaledHeight(mask.getHeight() / 4 * 3);
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(name, profilePic, "SideMenuTitle");
        profilePicLabel.setMask(mask.createMask());

        Container sidemenuTop = BorderLayout.center(profilePicLabel);
        sidemenuTop.setUIID("SidemenuTop");

        getToolbar().addComponentToSideMenu(sidemenuTop);
        getToolbar().addMaterialCommandToSideMenu(" Profil", FontImage.MATERIAL_TOYS, e -> new ServiceParent().ServiceParent(id_user, role));
           getToolbar().addMaterialCommandToSideMenu(" club", FontImage.MATERIAL_TOYS,e->new ClubForm().getForm(id_class,id_user,role).show());
        getToolbar().addMaterialCommandToSideMenu("  Time Table", FontImage.MATERIAL_TRENDING_UP, e -> new ServiceTimeTable(id_class, id_user, role));
        getToolbar().addMaterialCommandToSideMenu("  Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> new LoginForm(res).show());

    }

}
