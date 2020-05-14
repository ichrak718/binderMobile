/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;


/**
 *
 * @author wael
 */
public class Binder {
    private Form current;
    public static Form mainForm;
    private Button addBookBtn,listBooksBtn;
    private Container mainContainer;
    private Resources theme;

    public void init(Object context) {
        theme = UIManager.initFirstTheme("/theme");
    }
    
    public void start() {
        if(current != null){
            current.show();
            return;
        }
        UIBuilder ui = new UIBuilder();
        addBookBtn = new Button("Add new course");
        addBookBtn.getUnselectedStyle().setFgColor(5542241);
        listBooksBtn = new Button("Show courses");
        listBooksBtn.getUnselectedStyle().setFgColor(5542241);
        mainContainer = new Container();
        mainContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        mainContainer.add(addBookBtn);
        mainContainer.add(listBooksBtn);
        mainForm=new Form();
        mainForm.setLayout(new BorderLayout());
        mainForm.add(BorderLayout.CENTER,mainContainer);
        addBookBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                AddCourse addCourseUi = new AddCourse();
                addCourseUi.show();
            }
        });
        listBooksBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               // Browser.mainForm.show();
            }
        });
        mainForm.show();
    }

    public void stop() {
        current = Display.getInstance().getCurrent();
        if(current instanceof Dialog) {
            ((Dialog)current).dispose();
            current = Display.getInstance().getCurrent();
        }
    }
    
    public void destroy() {
    }

}
