/*
 * Copyright (c) 2012, Codename One and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Codename One designates this
 * particular file as subject to the "Classpath" exception as provided
 *  in the LICENSE file that accompanied this code.
 *  
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 * 
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 * 
 * Please contact Codename One through http://www.codenameone.com/ if you 
 * need additional information or have any questions.
 */
package com.codename1.uikit.materialscreens;


import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.io.Log;
import com.codename1.io.Preferences;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import static com.codename1.ui.CN.*;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.animations.ComponentAnimation;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.codename1.uikit.materialscreens.service.CourseDAO;


public class KitchenSink  {
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
        addBookBtn.addActionListener((ActionListener) (ActionEvent evt) -> {
            AddCourse addCourseUi = new AddCourse();
            addCourseUi.show();
        });
        listBooksBtn.addActionListener((ActionListener) (ActionEvent evt) -> {
            new CourseDAO().findAllCourses();
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
