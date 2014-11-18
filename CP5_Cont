void cp5_controllers_setup() {
 

  
  // cp5 = new ControlP5(this);  //cp5 = new ControlP5(this);
//  PFont font = createFont("arial", 20);
//  PFont font2 = createFont("arial", 16);
//  PFont font3 = createFont("arial",12); 
  
  
  gainA = cp5.addTextfield("gainA")
    .setColor(#030302) 
      .setColorBackground(#CEC6C6)//(#FFFEFC) 
        .setColorForeground(#AA8A16) 
          .setPosition(35, 133)
            .setSize(40, 20)
              .setFont(font2)
                .setFocus(false)                
                    .setText("1");
                      controlP5.Label gnA = gainA.captionLabel(); 
                        gnA.setFont(font3);
                          gnA.toUpperCase(false);
                            gnA.setText("Gain A");
  ;
    gainB = cp5.addTextfield("gainB")
    .setColor(#030302) 
      .setColorBackground(#CEC6C6)//(#FFFEFC) 
        .setColorForeground(#AA8A16) 
          .setPosition(108, 133)
            .setSize(40, 20)   // was 32
              .setFont(font2)
                .setFocus(false)                
                    .setText("1");
                      controlP5.Label gnB = gainB.captionLabel(); 
                        gnB.setFont(font3);
                          gnB.toUpperCase(false);
                            gnB.setText("Gain B");
  ;
    interval = cp5.addTextfield("interval")
    .setColor(#030302) 
      .setColorBackground(#CEC6C6)//(#FFFEFC) 
        .setColorForeground(#AA8A16) 
          .setPosition(35, 178)
            .setSize(40, 20)
              .setFont(font2)
                .setFocus(false)                
                    .setText("50");
                      controlP5.Label ivl = interval.captionLabel(); 
                        ivl.setFont(font3);
                          ivl.toUpperCase(false);
                            ivl.setText("Interval (ms)");
  ;
}
  /*
  
    Disp_V = cp5.addTextfield("Disp_V")
    .setColor(#030302) 
      .setColorBackground(#CEC6C6) 
       .setColorForeground(#AA8A16) 
         .setPosition(20, 100)
            .setSize(80, 32)
              .setFont(font2)
                .setFocus(false)
                   .setText("0200");
                      controlP5.Label evl = Disp_V.captionLabel(); 
                        evl.setFont(font3);
                          evl.toUpperCase(false);
                            evl.setText("Dispense Volume (uL)");
  ;

    Syringe_Dia = cp5.addTextfield("Syringe_Dia")
    .setColor(#030302) 
      .setColorBackground(#CEC6C6) 
       .setColorForeground(#AA8A16) 
         .setPosition(200, 180)
            .setSize(80, 32)
              .setFont(font2)
                .setFocus(false)
                   .setText("05");
                      controlP5.Label srl = Syringe_Dia.captionLabel(); 
                        srl.setFont(font3);
                          srl.toUpperCase(false);
                            srl.setText("Syringe Diameter (mm)");
  ;
  
  n_Addns = cp5.addTextfield("n_Addns")
    .setColor(#030302) 
      .setColorBackground(#CEC6C6) 
        .setColorForeground(#AA8A16) 
          .setPosition(20, 180)
            .setSize(80, 32)
              .setFont(font2)
                .setFocus(false)
                  .setText("10");
                      controlP5.Label dtl = n_Addns.captionLabel(); 
                        dtl.setFont(font3);
                          dtl.toUpperCase(false);
                            dtl.setText("Number of Additions (2 digits)");                    
  ;

ad_Delay = cp5.addTextfield("ad_Delay")
    .setColor(#030302) 
      .setColorBackground(#CEC6C6) 
        .setColorForeground(#AA8A16) 
          .setPosition(20, 260)
            .setSize(80, 32)
              .setFont(font2)
                .setFocus(false)
                  .setText("005");
                      controlP5.Label invl = ad_Delay.captionLabel(); 
                        invl.setFont(font3);
                          invl.toUpperCase(false);
                            invl.setText("Delay Between Injections (s)");
  ;


  maxLength = cp5.addTextfield("maxLength")  // txt field
    .setColor(#030302) 
      .setColorBackground(#CEC6C6) 
         .setColorForeground(#AA8A16) 
           .setPosition(200, 260)
            .setSize(80, 32)
              .setFont(font2)
                .setFocus(false)
                   .setText("60");
                      controlP5.Label fnlvl = maxLength.captionLabel(); 
                        fnlvl.setFont(font3);
                          fnlvl.toUpperCase(false);
                            fnlvl.setText("Length of Syringe");                    
  ;
  
     Direction = cp5.addTextfield("Direction")  // time txt field
    .setColor(#030302) 
      .setColorBackground(#CEC6C6) 
        .setColorForeground(#AA8A16) 
          .setPosition(20, 420)
            .setSize(80, 32)
              .setFont(font2)
                .setFocus(false)
                    .setText("0");
                      controlP5.Label norl = Direction.captionLabel(); 
                        norl.setFont(font3);
                          norl.toUpperCase(false);
                            norl.setText("Direction (0 = withdraw, 1 = infuse)");                    
  ; 
  
      Speed = cp5.addTextfield("Speed")  // time based txt field
    .setColor(#030302) 
      .setColorBackground(#CEC6C6) 
        .setColorForeground(#AA8A16) 
          .setPosition(20, 340)
            .setSize(80, 32)
              .setFont(font2)
                .setFocus(false)
                    .setText("10");
                      controlP5.Label ril = Speed.captionLabel(); 
                        ril.setFont(font3);
                          ril.toUpperCase(false);
                            ril.setText("Injection Speed (0-10)");                    
  ;

  
}
//////////////////////////////////////text field programs////////////////////////////////

public void Syringe_V() {              //get syringe volume from text box
  Syringe_Vol = cp5.get(Textfield.class, "Syringe_V").getText();
 int iSyringe_V = round(1000*float(Syringe_Vol));
  Syringe_Vol = nf(iSyringe_V, 5);   // make StartV have 4 digits. pad with zero if no digits
}

public void Disp_V() {              //get Dispensed volume from text box
  Disp_Vol = cp5.get(Textfield.class, "Disp_V").getText();
 int iDisp_V = round(float(Disp_Vol));
  Disp_Vol = nf(iDisp_V, 4);   // make StartV have 4 digits. pad with zero if no digits
}
public void Syringe_Dia() {              //get Syringe diameter from text box
  Syringe_D = cp5.get(Textfield.class, "Syringe_Dia").getText();
 int iSyr_D = round(float(Syringe_D));
  Syringe_D = nf(iSyr_D, 2);   // make StartV have 2 digits. pad with zero if no digits
}

public void nAddns() {              //number of Additions from text box
  n_Adds = cp5.get(Textfield.class, "n_Addns").getText();
 int iAdds = round(float(n_Adds));
  n_Adds = nf(iAdds, 2);   // make StartV have 2 digits. pad with zero if no digits
}

public void ad_Delay() {              //Delay between additions from text box
  ad_Del = cp5.get(Textfield.class, "ad_Delay").getText();
 int iadDel = round(float(ad_Del));
  ad_Del = nf(iadDel, 3);   //  3 digits. pad with zero if no digits
}

public void maxLength() {              //syringe length from text box
  maxL = cp5.get(Textfield.class, "maxLength").getText();
 int imaxL = round(float(maxL));
  maxL = nf(imaxL, 3);   //  pad with zero if no digits
}

public void Speed() {              //inject or withdraw
  Spd = cp5.get(Textfield.class, "Speed").getText();
 int ispd = round(float(Spd)); 
  Spd = nf(ispd, 2);   
}

public void Direction() {              //inject or withdraw
  Dir = cp5.get(Textfield.class, "Direction").getText();
  int iDir = round(float(Dir));  //*2-1;
  Dir = nf(iDir,1);   // make StartV have 3 digits. pad with zero if no digits
//  Dir = nf(iDir, 2);   // make StartV have 3 digits. pad with zero if no digits
}  */
