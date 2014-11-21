 void checkBoxes()   { 
     PImage[] togs = {loadImage("tog_button1.png"),loadImage("tog_button2.png"),loadImage("tog_button3.png")};
     channels = cp5.addCheckBox("channels")     // collect data on these channels
                .setPosition(20, 95)
                .setImages(togs[0],togs[1],togs[2])
                .setColorLabel(color(255))
                .setItemsPerRow(1)
                .setSpacingColumn(30)
                .setSpacingRow(2)
                .addItem("A", 1)
                .addItem("B", 2)
                ;

     chanDisp = cp5.addCheckBox("chanDisp")     // display these channels
                .setPosition(20, 320)
                .setImages(togs[0],togs[1],togs[2])
                .setColorLabel(color(255))
                .setSize(10, 10)
                .setItemsPerRow(1)
                .setSpacingColumn(30)
                .setSpacingRow(2)
                .addItem("A ", 1)
                .addItem("B ", 2)          
                ;
      ovrLay= cp5.addCheckBox("ovrLay")
                .setPosition(30, 233)
                .setImages(togs[1],togs[0],togs[2])
                .setColorLabel(color(255))
                .setSize(20, 20)
                .setItemsPerRow(1)
                .setSpacingColumn(95)
                .setSpacingRow(115)
       
                .addItem("", 1)   //overlay
                         .addItem(" ",10)          //mode
                                ;

  }
// }
 void keyPressed() {
  if (key==' ') {
    channels.deactivateAll();
    chanDisp.deactivateAll();
    ovrLay.deactivateAll();
  } 
  else {
    for (int i=0;i<1;i++) {
      // check if key 0-5 have been pressed and toggle
      // the checkbox item accordingly.
      if (keyCode==(48 + i)) { 
        // the index of checkbox items start at 0
        channels.toggle(i);
        chanDisp.toggle(i);
        ovrLay.toggle(i);
        println("toggle "+channels.getItem(i).name());
      }
    }
  }
}

//void controlEvent(ControlEvent theEvent) {

//}

