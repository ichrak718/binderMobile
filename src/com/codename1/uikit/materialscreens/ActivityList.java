/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.materialscreens;

import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Sam
 */
public class ActivityList extends Form{
    
    //private final Container oneEvent;
     Form f1 = new Form();
     
      private final Resources res = UIManager.initFirstTheme("/theme");
    //private final Container mainContainer;


   public ActivityList( Integer id,String about,String duration,String location,String clubAC,String dateA,Integer club_id){

     
       
        
         
        
   
    f1 = new Form("Tous Les activities",new BoxLayout(BoxLayout.Y_AXIS));
       
              //  oneEvent=new Container(new BoxLayout(BoxLayout.X_AXIS));
            
           // oneEvent.add(lab3);
           
            
            
        //    f1.add(oneEvent);
        
         
     
        
     //new ClubDAO().findActivity();



      

         
  
       
        
             
        
          //f.getToolbar().addCommandToLeftBar(null, theme.getImage("cal_left_arrow.png"), (ev)->{HomeForm h=new HomeForm(theme);
         
          //});
         // f.setLayout(new BorderLayout());
   


   

   }
   

    
}
