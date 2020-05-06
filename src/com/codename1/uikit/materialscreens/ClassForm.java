/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.MultiButton;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.materialscreens.entity.Pupils;
import com.codename1.uikit.materialscreens.service.ServiceClasses;
import com.codename1.uikit.materialscreens.service.ServicePupil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asus
 */
public class ClassForm extends Form {

    List<Pupils> pupilList = new ArrayList<>();
    private Resources res = UIManager.initFirstTheme("/theme");
    private final Container mainContainer;
    private ConnectionRequest conx;
    Pupils p = new Pupils();
    Form f = new Form();

    public ClassForm(Integer id, String name, String session, Integer id_pupil) {

        super(BoxLayout.y());
        // System.out.println(+id+name+session+id_pupil+"bbbbbbbbbbbbbbbbbbbbb");
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
                                new Label(name, "Title"),
                                new Label(session, "SideMenuTitle")
                        )
                )
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

        Label nameL = new Label("Name:");
        nameL.getUnselectedStyle().setFgColor(39321);
        Font name_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);

        Label nameA = new Label(name);

        /* nameA.getUnselectedStyle().setFgColor(8482);
        Font name_fontA = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM);
        mainContainer.add(nameL);
        mainContainer.add(nameA);

        Label birthdayLabel = new Label("session:");
        birthdayLabel.getUnselectedStyle().setFgColor(39321);
        Font birthday_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
        Label birthdayA = new Label();
        birthdayA.getUnselectedStyle().setFgColor(8482);
        Font birthday_fontA = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM);
        mainContainer.add(birthdayLabel);
        mainContainer.add(birthdayA);
        Label emailLabel = new Label("Email:");
        emailLabel.getUnselectedStyle().setFgColor(39321);
        Font email_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
        Label emailA = new Label();
        emailA.getUnselectedStyle().setFgColor(8482);
        Font email_fontA = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM);
        mainContainer.add(emailLabel);
        mainContainer.add(emailA);*/
 /*       conx = new ConnectionRequest("http://localhost/mobile/readP.php?classes=" + id);
        conx.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(conx.getResponseData());

                Map<String, Object> u;
                try {
                    JSONParser j = new JSONParser();
                    u = j.parseJSON(new CharArrayReader(json.toCharArray()));

                    List<Map<String, Object>> content = (List<Map<String, Object>>) u.get("root");
                    pupilList.clear();

                    for (Map<String, Object> obj : content) {
                        // pupil.add(new Pupils(Integer.parseInt((String) obj.get("id")), (String) obj.get("fullname"), (String) obj.get("birthday"), (String) obj.get("email"), Integer.parseInt((String) obj.get("classes")), Integer.parseInt((String) obj.get("id_user"))));
                        p.setId(Integer.parseInt((String) obj.get("id")));
                        p.setFullname((String) obj.get("fullname"));
                        p.setBirthday((String) obj.get("birthday"));
                        p.setEmail((String) obj.get("email"));
                        p.setClasses(Integer.parseInt((String) obj.get("classes")));

                        p.setUser_id(Integer.parseInt((String) obj.get("id_user")));
                        pupilList.add(p);

                    }

                   
                } catch (IOException ex) {
                }
            }
        });


        NetworkManager.getInstance().addToQueue(conx);*/

        add(mainContainer);
//****************************************************************
        setupSideMenu(id, id_pupil, name);
    }

    private void addButtonBottom(Image arrowDown, String text, int color, boolean first) {
        MultiButton finishLandingPage = new MultiButton(text);
        finishLandingPage.setEmblem(arrowDown);
        finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("TodayEntry");
        finishLandingPage.setIcon(createCircleLine(color, finishLandingPage.getPreferredH(), first));
        finishLandingPage.setIconUIID("Container");
        // add(FlowLayout.encloseIn(finishLandingPage));
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

    public void setupSideMenu(Integer id, Integer id_pupil, String name) {
        Image profilePic = res.getImage("user.jpg");
        Image mask = res.getImage("round-mask.png");
        mask = mask.scaledHeight(mask.getHeight() / 4 * 3);
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label("", profilePic, "SideMenuTitle");
        profilePicLabel.setMask(mask.createMask());

        Container sidemenuTop = BorderLayout.center(profilePicLabel);
        sidemenuTop.setUIID("SidemenuTop");

        getToolbar().addComponentToSideMenu(sidemenuTop);
        getToolbar().addMaterialCommandToSideMenu("  Profile", FontImage.MATERIAL_DASHBOARD, e -> new ServicePupil().ServicePupilR(id_pupil));
        getToolbar().addMaterialCommandToSideMenu("  Classe", FontImage.MATERIAL_TRENDING_UP, e -> new ServiceClasses().ServiceClassesR(id, id_pupil));
        //getToolbar().addMaterialCommandToSideMenu("  Time Table", FontImage.MATERIAL_ACCESS_TIME,  e -> showOtherForm(res));
        //  getToolbar().addMaterialCommandToSideMenu("  Account Settings", FontImage.MATERIAL_SETTINGS,  e -> showOtherForm(res));
        getToolbar().addMaterialCommandToSideMenu("  Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> new LoginForm(res).show());

    }

}
