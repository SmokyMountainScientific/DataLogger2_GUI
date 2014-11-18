/////////////////////////////////////////////////group programs/////////////////////////////////

void controlEvent(ControlEvent theEvent) {
  if (theEvent.isGroup()) 
  {
    if (theEvent.name().equals("list-1")) {

      float S = theEvent.group().value();
      Ss = int(S);
      Comselected = true;
    }
/*    if (theEvent.name().equals("list-2")) {
      float Mod = theEvent.group().value(); 
      int Modi = int(Mod);
      String [][] Modetype = mode.getListBoxItems(); 
      //Modetorun = Modetype[Modi][Modi];
      runMode = Modetype[Modi][0]; // replaced earlier line in newer sketch?
      Modesel = true;
      println(runMode);
    }
    if (theEvent.name().equals("list-3")) {
      float ovr = theEvent.group().value(); 
      overlay = int(ovr);
//      String [][] Modetype = mode.getListBoxItems(); 
//      runMode = Modetype[Modi][0]; // replaced earlier line in newer sketch?
//      Modesel = true;
//      println(runMode);
    } */
  }
  if (theEvent.isFrom(channels)) {
    iChan = 0;
    print("got an event from "+channels.getName()+"\t\n");
    // checkbox uses arrayValue to store the state of 
    // individual checkbox-items. usage:
    println(channels.getArrayValue());
//    int col = 0;
    for (int i=0;i<channels.getArrayValue().length;i++) {
      int n = (int)channels.getArrayValue()[i];
      print(n);
      if(n==1) {
//        myColorBackground += channels.getItem(i).internalValue();
        iChan += channels.getItem(i).internalValue();   // internalValue = 2 for B
      }
    }
    println();   
   println(iChan);
//   iChan = 3-iChan;
  if (iChan == 0) {
 //     channelSel.setText("A and B");
      colTxt = "Channels A and B";
       }
 else if (iChan == 1) {
//  channelSel.setText("B Only");
      colTxt = "Channel B only";
 }
  else if (iChan ==2) {
//   channelSel.setText("A Only");
      colTxt = "Channel A only";
  }
  else {
//   channelSel.setText("Select");
      colTxt = "Select channel";
  }
  }
////////////// channel display ///////////////  
    if (theEvent.isFrom(chanDisp)) {
    chanD = 0;
    print("got an event from "+chanDisp.getName()+"\t\n");
    // checkbox uses arrayValue to store the state of 
    // individual checkbox-items. usage:
    println(chanDisp.getArrayValue());
//    int col = 0;
    for (int i=0;i<chanDisp.getArrayValue().length;i++) {
      int n = (int)chanDisp.getArrayValue()[i];
      print(n);
      if(n==1) {
//        myColorBackground += channels.getItem(i).internalValue();
        chanD += chanDisp.getItem(i).internalValue();   // internalValue = 2 for B
      }
    }
    println();   
   println(chanD);
//   iChan = 3-iChan;
  if (chanD == 0) {
//      channelDisp.setText("A and B");
      disTxt = "Channels A and B";
      }
 else if (chanD == 1) {
//  channelDisp.setText("B Only");
    disTxt = "Channel B only"; }
  else if (chanD ==2) {
//   channelDisp.setText("A Only");
      disTxt = "Channel A only";
  }
  else {
 //  channelDisp.setText("Select");
        disTxt = "Select channel";
      }
  }
  
  ///////////  overlay and mode /////////////
      if (theEvent.isFrom(ovrLay)) {
    runMode = 0;
    overlay = 0;
    print("got an event from "+ovrLay.getName()+"\t\n");
    // checkbox uses arrayValue to store the state of 
    // individual checkbox-items. usage:
    println(ovrLay.getArrayValue());
//    int val = 0;
//    for (int i=0;i<ovrLay.getArrayValue().length;i++) {
      overlay = (int)ovrLay.getArrayValue()[1];
      runMode = (int)ovrLay.getArrayValue()[0];     /*print(n);
      if(n==1) {
        */
//        myColorBackground += channels.getItem(i).internalValue();
/*        overlay = ovrLay.getItem(0).internalValue();   // internalValue = 2 for B
        runMode = ovrLay.getItem(1).internalValue();*/
//        runMode = runMode/10; 
        println(overlay);
        println(runMode);
      //}
      if(overlay == 0) {
       ovrTxt = "No overlay"; 
      }
      else {
        ovrTxt = "Overlay";
      }
      if(runMode == 0) {
      modTxt = "Continuous";
    }
      else {
        modTxt = "Discrete";
      }
    }
/*    println();   
   println(chanD);
//   iChan = 3-iChan;
  if (chanD == 0) {
      channelDisp.setText("A and B");
       }
 else if (chanD == 1) {
  channelDisp.setText("B Only");
 }
  else if (chanD ==2) {
   channelDisp.setText("A Only");
  }
  else {
   channelDisp.setText("Select");
  }*/
  }
//}
//}
