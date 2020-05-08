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
import com.codename1.ui.Display;
import com.codename1.ui.Font;
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
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.materialscreens.entity.Classes;
import com.codename1.uikit.materialscreens.service.ServiceClasses;
import com.codename1.uikit.materialscreens.service.ServicePupil;
import com.codename1.uikit.materialscreens.service.ServiceTimeTable;
import com.esprit.binder.utils.Statics;
import java.io.IOException;
import java.io.OutputStream;


/**
 *
 * @author Asus
 */
public class TimeTableForm extends Form {

    private Resources res = UIManager.initFirstTheme("/theme");
    private final Container mainContainer;

    Form f = new Form();

    public TimeTableForm(Integer id, Integer class_id, String pdf, Integer id_user) {

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
        
        /*
        Button pdfBtn = new Button("Download");
        
        
        pdfBtn.addActionListener(e -> {
            FileSystemStorage fs = FileSystemStorage.getInstance();
            String fileName = fs.getAppHomePath() + "pdf-sample.pdf";
            if (!fs.exists(fileName)) {
                    Util.downloadUrlToFile("file:///"+pdf, fileName, true);

              
            }
            try {
                fs.openOutputStream(fileName);
            } catch (IOException ex) {
            }
        });
        mainContainer.add(pdfBtn);*/
        Button bt = new Button("Download");
        bt.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            OutputStream stream = null;
            try {
                FileSystemStorage fs = FileSystemStorage.getInstance();
                stream = fs.openOutputStream(FileSystemStorage.getInstance().getAppHomePath()+"asma.csv");
                String entete = "Subject;Grade;Teacher\n";
                String infos = ""+class_id+";"+id+";"+id_user+"\n";
                stream.write(entete.getBytes());
                stream.write(infos.getBytes());
                stream.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    });
        mainContainer.add(bt);
        add(mainContainer);
//****************************************************************
        setupSideMenu(id, id_user, class_id);
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

    public void setupSideMenu(Integer id, Integer id_user, Integer class_id) {
        Image profilePic = res.getImage("user.jpg");
        Image mask = res.getImage("round-mask.png");
        mask = mask.scaledHeight(mask.getHeight() / 4 * 3);
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label("", profilePic, "SideMenuTitle");
        profilePicLabel.setMask(mask.createMask());

        Container sidemenuTop = BorderLayout.center(profilePicLabel);
        sidemenuTop.setUIID("SidemenuTop");

        getToolbar().addComponentToSideMenu(sidemenuTop);
        getToolbar().addMaterialCommandToSideMenu("  Profile", FontImage.MATERIAL_DASHBOARD, e -> new ServicePupil().ServicePupilR(id_user));
        getToolbar().addMaterialCommandToSideMenu("  Time Table", FontImage.MATERIAL_TRENDING_UP, e -> new ServiceTimeTable(class_id, id_user));
        //getToolbar().addMaterialCommandToSideMenu("  Time Table", FontImage.MATERIAL_ACCESS_TIME,  e -> showOtherForm(res));
        //  getToolbar().addMaterialCommandToSideMenu("  Account Settings", FontImage.MATERIAL_SETTINGS,  e -> showOtherForm(res));
        getToolbar().addMaterialCommandToSideMenu("  Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> new LoginForm(res).show());

    }

}
