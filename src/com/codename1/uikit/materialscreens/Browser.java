/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;

import com.codename1.components.WebBrowser;
import com.codename1.io.URL;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import static com.codename1.uikit.materialscreens.service.CourseDAO.listOfCourses;


/**
 *
 * @author wael
 */
public class Browser extends Container{
    
    public Browser(){
        Form hi = new Form("Browser", new BorderLayout());
        BrowserComponent browser = new BrowserComponent();
        browser.setURL("https://www.facebook.com");
        hi.add(BorderLayout.CENTER, browser);
        hi.show();
    }
    public Browser(String url){
        
        Form hi = new Form("Browser", new BorderLayout());
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_BACKSPACE, s);
        hi.getToolbar().addCommandToLeftBar("Return", icon, (e) -> listOfCourses.showBack());
        BrowserComponent browser = new BrowserComponent();
        browser.setURL(url);
        hi.add(BorderLayout.CENTER, browser);
        hi.show();
    }
}
