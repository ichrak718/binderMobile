/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.uikit.materialscreens.service.ClubDAO;

/**
 *
 * @author Sam
 */
public class ClubForm extends Form{
    
      private Container oneEvent;
     Form f1 = new Form();
    // private final Label l1;
     private Button btnclub;
    public ClubForm(){
      f1 = new Form("Tous Les Clubs",new BoxLayout(BoxLayout.Y_AXIS));
       
                oneEvent=new Container(new BoxLayout(BoxLayout.X_AXIS));
            Label lab3=new Label("sam");
           // oneEvent.add(lab3);
           f1.setTitle("clubs");
            
            
        //    f1.add(oneEvent);
        
         
     
        
    



      

         
  
       
        
             
        }
          //f.getToolbar().addCommandToLeftBar(null, theme.getImage("cal_left_arrow.png"), (ev)->{HomeForm h=new HomeForm(theme);
         
          //});
         // f.setLayout(new BorderLayout());
   


   

  
     public Form getForm(Integer id_class, Integer id_user, String role){
          new ClubDAO().findAllClubs(id_class,id_user ,role);
   return f1;
   }
          public Form getForm(){
          new ClubDAO().findAllClubs();
   return f1;
   }
    
}
