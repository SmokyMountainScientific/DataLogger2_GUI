import processing.core.*; 
import processing.data.*; 
import processing.opengl.*; 

import org.gicentre.utils.stat.*; 
import controlP5.*; 
import processing.serial.*; 

import java.applet.*; 
import java.awt.Dimension; 
import java.awt.Frame; 
import java.awt.event.MouseEvent; 
import java.awt.event.KeyEvent; 
import java.awt.event.FocusEvent; 
import java.awt.Image; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class ASV_CV_Jan7_2013 extends PApplet {


///////////////////////////////////////// Imports/////////////////////////////////
    // For chart classes.
//import javax.swing.JFileChooser;


///////////////////////////////////////////////////////////////Classes///////////////

XYChart lineChart;
XYChart lineChartIf;
XYChart lineChartIb;
ControlP5 cp5;
Serial serial;
Textarea myTextarea;
Textarea myTextarea2;
Textfield Starting_Voltage, End_Voltage, Scan_Rate, Delay_Time;
//Textarea Serial_data;
DropdownList ports, mode;              //Define the variable ports and mode as a Dropdownlist.
//ListBox mode;
//////////////////////////////////variables//////////////////////

int Ss;                          //The dropdown list will return a float value, which we will connvert into an int. we will use this int for that).
String[] comList ;               //A string to hold the ports in.
String[] comList2;               // string to compare comlist to and update
boolean serialSet;               //A value to test if we have setup the Serial port.
boolean Comselected = false;     //A value to test if you have chosen a port in the list.
//////////////
int Modi; 
boolean Modesel = false;
String Modetorun;
//String Modetype;
int xacu;
int yacu;
float xspace;
float yspace;
ArrayList qdat;
boolean bool = false;
float p1;
float p2;
String StartV;
int iStartV;
String EndV;
int iEndV;
String ScanR;
int iScanR;
String DelayT;
int iDelayT;
int xPos = 150; 
String ComP;
int serialPortNumber;
String file1;  //"C:/Users/Public/Documents/Electrochem/logfiletest/TestLog.txt"
String file;
String[] sData = new String[3];  //String sData;
String sData2 = " ";
char cData;
int logDelay = 1000;
String Go = "1";
String ASVmod = "1";
String CVmod = "2";
String star = "*";
int i =0;
float[] V = {
  0
};
float[] I1 = {
  0
};  //float[]
float[] I2 = {
  0
};
float[] Idif = {
  0
};
float[] newV = {
  0
};
float[] newI1 = {
  0
};
float[] newI2 = {
  0
};
float[] newIdif = {
  0
};


///////////////////////////////Setup////////////////////////////////////////////////////
public void setup() {

  frameRate(100);
  size(800, 700);
  PFont font = createFont("arial", 20);
  PFont font2 = createFont("arial", 16);   
  textFont(font2);
  ///////////////////////////////////////////////////////////////////////gicentre///

  lineChart = new XYChart(this);
  lineChart.setData(new float[] {1, 2, 3}, new float[] {1, 2, 3});
  lineChart.showXAxis(true); 
  lineChart.showYAxis(true);
  lineChart.setXAxisLabel("Potential (V)");
  lineChart.setYAxisLabel("Current"); 
  lineChart.setMinY(0);   
  lineChart.setYFormat("##.##");  
  lineChart.setXFormat("##.##");       
  // Symbol colours
  lineChart.setPointColour(color(234, 28, 28));
  lineChart.setPointSize(5);
  lineChart.setLineWidth(2);

  lineChartIf = new XYChart(this);
  lineChartIf.setData(new float[] {1, 2, 3}, new float[] {1, 2, 3});
  lineChartIf.showXAxis(true); 
  lineChartIf.showYAxis(true);
  lineChartIf.setXAxisLabel("Potential (V)");
  lineChartIf.setYAxisLabel("Current"); 
  lineChartIf.setMinY(0);   
  lineChartIf.setYFormat("##.##");  
  lineChartIf.setXFormat("##.##");       
  // Symbol colours
  lineChartIf.setPointColour(color(234, 28, 28));
  lineChartIf.setPointSize(5);
  lineChartIf.setLineWidth(2);

  lineChartIb = new XYChart(this);
  lineChartIb.setData(new float[] {
    1, 2, 3
  }
  , new float[] {
    1, 2, 3
  }
  );
  lineChartIb.showXAxis(true); 
  lineChartIb.showYAxis(true);
  lineChartIb.setXAxisLabel("Potential (V)");
  lineChartIb.setYAxisLabel("Current"); 
  lineChartIb.setMinY(0);   
  lineChartIb.setYFormat("##.##");  
  lineChartIb.setXFormat("##.##");       
  // Symbol colours
  lineChartIb.setPointColour(color(234, 28, 28));
  lineChartIb.setPointSize(5);
  lineChartIb.setLineWidth(2);
  ////////////////////////////////////////////////Text Fields//////////////////////////////
  cp5 = new ControlP5(this);  //cp5 = new ControlP5(this);


  Starting_Voltage = cp5.addTextfield("Starting_Voltage")
    .setColor(0xff030302) 
      //.setColorActive(#AA8A16)
      .setColorBackground(0xffCEC6C6)//(#FFFEFC) 
        //.setColorCaptionLabel(#F01B1B) 
        .setColorForeground(0xffAA8A16) 
          //.setColorValueLabel(#F01B1B)
          .setPosition(20, 100)
            .setSize(100, 40)
              .setFont(font)
                .setFocus(false)
                  //.setLabel("initial voltage (mV)")                
                    .setText("-400");
                      controlP5.Label svl = Starting_Voltage.captionLabel(); 
                        svl.setFont(font2);
                          svl.toUpperCase(false);
                            svl.setText("Initial Voltage (mV)");
  ;

  End_Voltage = cp5.addTextfield("End_Voltage")
    .setColor(0xff030302) 
      //.setColorActive(#AA8A16)
      .setColorBackground(0xffCEC6C6) 
        //.setColorCaptionLabel(int) 
        .setColorForeground(0xffAA8A16) 
          //.setColorValueLabel(int)
          .setPosition(20, 170)
            .setSize(100, 40)
              .setFont(font)
                .setFocus(false)
                  //.setLabel("final voltage (mV)")
                    .setText("400");
                      controlP5.Label evl = End_Voltage.captionLabel(); 
                        evl.setFont(font2);
                          evl.toUpperCase(false);
                            evl.setText("End Voltage (mV)");
  ;

  Scan_Rate = cp5.addTextfield("Scan_Rate")
    .setColor(0xff030302) 
      //.setColorActive(#AA8A16)
      .setColorBackground(0xffCEC6C6) 
        //.setColorCaptionLabel(int) 
        .setColorForeground(0xffAA8A16) 
          //.setColorValueLabel(int)
          .setPosition(20, 240)
            .setSize(100, 40)
              .setFont(font)
                .setFocus(false)
                  //.setLabel("Scan Rate (mV/sec)")
                    .setText("100");
                      controlP5.Label srl = Scan_Rate.captionLabel(); 
                        srl.setFont(font2);
                          srl.toUpperCase(false);
                            srl.setText("Scan Rate (mV/sec)");
  ;

  Delay_Time = cp5.addTextfield("Delay_Time")
    .setColor(0xff030302) 
      //.setColorActive(#AA8A16)
      .setColorBackground(0xffCEC6C6) 
        //.setColorCaptionLabel(int) 
        .setColorForeground(0xffAA8A16) 
          //.setColorValueLabel(int)  
          .setPosition(20, 310)
            .setSize(100, 40)
              .setFont(font)
                .setFocus(false)
                  //.setLabel("concentration time (sec)")
                    .setText("60");
                      controlP5.Label dtl = Delay_Time.captionLabel(); 
                        dtl.setFont(font2);
                          dtl.toUpperCase(false);
                            dtl.setText("Concentration Time (sec)");                    
  ;

  ///////////////////////////////////////text area//////////////////////////

  myTextarea = cp5.addTextarea("txt")
    .setPosition(280, 5)
      .setSize(300, 45)
        .setFont(font)
          .setLineHeight(20)
            .setColor(0xff030302)
              .setColorBackground(0xffCEC6C6)
                .setColorForeground(0xffAA8A16)//#CEC6C6
                    ;

  myTextarea2 = cp5.addTextarea("txt2")
    .setPosition(170, 5)
      .setSize(100, 20)
        .setFont(createFont("arial", 12)) //(font)
          .setLineHeight(10)
            .setColor(0xff030302)
              .setColorBackground(0xffCEC6C6)
                .setColorForeground(0xffAA8A16)//#CEC6C6                 
                    ;


  /////////////////////////////Bang's///////////////////////////////////////////////////////////
  cp5.addBang("Start_Run")
    .setColorBackground(0xffFFFEFC)//#FFFEFC 
        .setColorCaptionLabel(0xff030302) //#030302
          .setColorForeground(0xffAA8A16)
          .setColorActive(0xff06CB49)
            .setPosition(20, 380)
              .setSize(150, 40)
                .setTriggerEvent(Bang.RELEASE)
                  .setLabel("Start Run") //
                    .getCaptionLabel().align(ControlP5.CENTER, ControlP5.CENTER)  //.setLabel("Start_Run")
                      ;

  cp5.addBang("Connect")
    .setColorBackground(0xffFFFEFC)//#FFFEFC 
        .setColorCaptionLabel(0xff030302) //#030302
          .setColorForeground(0xffAA8A16)  
          .setPosition(120, 15)
            .setSize(40, 20)
              .setTriggerEvent(Bang.RELEASE)
                .getCaptionLabel().align(ControlP5.CENTER, ControlP5.CENTER)  //.setLabel("Start_Run")
                  ;

  cp5.addBang("Set_Path")
    .setColorBackground(0xffFFFEFC)//#FFFEFC 
        .setColorCaptionLabel(0xff030302) //#030302
          .setColorForeground(0xffAA8A16)  
          .setPosition(590, 10)
            .setSize(50, 20)
              .setTriggerEvent(Bang.RELEASE)
                .setLabel("Set Path")
                  .getCaptionLabel().align(ControlP5.CENTER, ControlP5.CENTER)  //.setLabel("Start_Run")
                    ;

  //////////////////////////////////////////Dropdownlist////////////////////////////////////////
  ports = cp5.addDropdownList("list-1", 10, 30, 100, 84)
    .setBackgroundColor(color(200))
      .setItemHeight(20)
        .setBarHeight(20)
          .setColorBackground(color(60))
            .setColorActive(color(255, 128))
              .setUpdate(true)
                ;
  ports.captionLabel().set("Select COM port");
  ports.captionLabel().style().marginTop = 3;
  ports.captionLabel().style().marginLeft = 3;
  ports.valueLabel().style().marginTop = 3;
  comList = serial.list(); 
  for (int i=0; i< comList.length; i++)
  {
    ports.addItem(comList[i], i);
  } 

  mode = cp5.addDropdownList("list-2", 650, 30, 100, 84)  //mode = cp5.addDropdownList("list-2", 650, 30, 100, 84) 
    .setBackgroundColor(color(200))
      .setItemHeight(40)//.setItemHeight(20
          .setBarHeight(20)//.setBarHeight(15)
          .setColorBackground(color(60))
            .setColorActive(color(255, 128))
              .setUpdate(true)
                ;
  mode.captionLabel().set("Select Mode");
  mode.captionLabel().style().marginTop = 3;
  mode.captionLabel().style().marginLeft = 3;
  mode.valueLabel().style().marginTop = 3;
  mode.addItem("ASV", 0);
  mode.addItem("CV", 1);
}////////////////////End Setup/////////////////////////////
/////////////////////////////////////////////////Draw//////////////////////////////////////////////    

public void draw() {

  background(58, 2, 67);  
  comList2 = serial.list();
  if (comList.length != comList2.length) { //if(comList.equals(comList2)==false){
    ports.clear();
    comList = comList2;
    for (int i=0; i< comList.length; i++)
    {
      ports.addItem(comList[i], i);
    }
  } 


  if (Modetorun=="ASV") {
    fill(0xffFFFEFC);
    rect(200, 80, 580, 580);
    fill(120);
    textSize(16);
    text("Forward Current", 280, 100);
    text("Back Current", 550, 100);
    text("Forward-Back Current", 340, 340);
    lineChart.draw(280, 320, 350, 300);
    lineChartIf.draw(220, 80, 250, 200);
    lineChartIb.draw(490, 80, 250, 200);
  }
  if (Modetorun=="CV") {
    fill(0xffFFFEFC);
    rect(200, 80, 540, 540);
    fill(120);
    textSize(16);
    text("CV Data", 450, 100);
    lineChart.draw(230, 120, 450, 450);
  }  

  if (Modesel==false) {
    fill(58, 2, 67);
    noStroke();
    rect(0, 80, 780, 580);
    Starting_Voltage.hide();
    End_Voltage.hide();
    Delay_Time.hide();
    Scan_Rate.hide();
    cp5.controller("Start_Run").hide();
  }
  if (Modesel==true) {
    Starting_Voltage.show();
    End_Voltage.show();
    Delay_Time.show();
    Scan_Rate.show();
    cp5.controller("Start_Run").show();
  } 

  if (Modetorun=="CV") {
    Delay_Time.hide();
  }
  if (Modetorun=="ASV") {
    Delay_Time.show();
  } 


  if (bool == true) {        
    println("bool = true"); 
    V = newV;  //float[]
    I1 = newI1;
    I2 = newI2;
    Idif = newIdif;

    if (Modetorun=="ASV") {
      Starting_Voltage();
      End_Voltage();
      Scan_Rate();
      Delay_Time();
      serial.write(StartV);
      delay(100);
      serial.write(EndV);
      delay(100);
      serial.write(ScanR);
      delay(100);
      serial.write(DelayT);
      delay(100);
      serial.write(ASVmod);  // prg 1 for ASV
      delay(100);
      serial.write(Go);
      println(StartV);
      println(EndV);
      println(ScanR);
      println(DelayT);
      println(ASVmod);
      println(Go);
      read_serialASV();
    }

    if (Modetorun=="CV") {
      Starting_Voltage();
      End_Voltage();
      Scan_Rate();
      Delay_Time();
      serial.write(StartV);
      delay(100);
      serial.write(EndV);
      delay(100);
      serial.write(ScanR);
      delay(100);
      serial.write(DelayT);
      delay(100);
      serial.write(CVmod);   // prg 2 for CV
      delay(100);
      serial.write(Go);
      println(StartV);
      println(EndV);
      println(ScanR);
      println(CVmod);
      println(Go);
      read_serialCV();
    }



    bool=false;  
    myTextarea2.setText("FINISHED");
    if (Modetorun=="ASV") { 
      for (i=0;i<I1.length;i++) {
        if (i==0) {
          Idif[i] = I2[i]-I1[i];
        }
        else {
          Idif = append(Idif, (I2[i]-I1[i]));
        }
      }
    }
    if (Modetorun=="CV") { 
      for (i=0;i<I1.length;i++) {
        if (i==0) {
          Idif[i] = I1[i];
        }
        else {
          Idif = append(Idif, I1[i]);
        }
      }
    }
  }



  if (i!=0) {


    if (Modetorun=="ASV") {

      lineChart.setMaxX(max(V)+.05f);
      lineChart.setMaxY(max(Idif)+.05f); 
      lineChart.setMinX(min(V)-.05f);
      lineChart.setMinY(min(Idif)-.05f);
      lineChart.setData(V, Idif);
      lineChart.draw(280, 320, 350, 300);

      lineChartIf.setMaxX(max(V)+.05f);
      lineChartIf.setMaxY(max(I2)+.05f); 
      lineChartIf.setMinX(min(V)-.05f);
      lineChartIf.setMinY(min(I2)-.05f);
      lineChartIf.setData(V, I2);
      lineChartIf.draw(220, 80, 250, 200);

      lineChartIb.setMaxX(max(V)+.05f);
      lineChartIb.setMaxY(max(I1)+.05f); 
      lineChartIb.setMinX(min(V)-.05f);
      lineChartIb.setMinY(min(I1)-.05f);
      lineChartIb.setData(V, I1);
      lineChartIb.draw(490, 80, 250, 200);
    }
    if (Modetorun=="CV") {

      lineChart.setMaxX(max(V)+.05f);
      lineChart.setMaxY(max(Idif)+.05f); 
      lineChart.setMinX(min(V)-.05f);
      lineChart.setMinY(min(Idif)-.05f);
      lineChart.setData(V, Idif);
      lineChart.draw(280, 320, 350, 300);

      //lineChartIf.hide();//setData(new float[] {1, 2, 3}, new float[] {1, 2, 3});
      //lineChartIb.hide();//setData(new float[] {1, 2, 3}, new float[] {1, 2, 3});
    }
    i=0;
  }
}///////////////////////////End Draw////////////////////////

/////////////////////////////////////////////////group programs/////////////////////////////////
public void controlEvent(ControlEvent theEvent) {
  if (theEvent.isGroup()) 
  {
    if (theEvent.name().equals("list-1")) {//if (theEvent.getGroup().equals("list-1")) {

      float S = theEvent.group().value();
      Ss = PApplet.parseInt(S);
      Comselected = true;
    }
    if (theEvent.name().equals("list-2")) {
      float Mod = theEvent.group().value(); //float Mod = theEvent.group().value();
      Modi = PApplet.parseInt(Mod);
      String [][] Modetype = mode.getListBoxItems(); //  String [] Modetype = theEvent.group().Items();
      Modetorun = Modetype[Modi][Modi];
      Modesel = true;
      println(Modetorun);
    }
  }
}



//////////////////////////////////////text field programs////////////////////////////////

public void Starting_Voltage() {              //get start voltage from text box
  StartV = cp5.get(Textfield.class, "Starting_Voltage").getText();
  iStartV = round(PApplet.parseFloat(StartV));
  iStartV=iStartV+1852;
  StartV = nf(iStartV, 4);   // make StartV have 4 digits. pad with zero if no digits
}
public void End_Voltage() {               // get end voltage from text box
  EndV = cp5.get(Textfield.class, "End_Voltage").getText();
  iEndV = round(PApplet.parseFloat(EndV));
  iEndV=iEndV+1852;
  EndV = nf(iEndV, 4);   // make EndV have 4 digits. pad with zero if no digits
}
public void Scan_Rate() {                 // get scan rate from text box
  ScanR = cp5.get(Textfield.class, "Scan_Rate").getText();
  iScanR = round(PApplet.parseFloat(ScanR));
  ScanR = nf(iScanR, 3);   // make ScanR have 3 digits. pad with zero if no digits
}
public void Delay_Time() {                // get delay time from text box
  DelayT = cp5.get(Textfield.class, "Delay_Time").getText();
  iDelayT = round(PApplet.parseFloat(DelayT));
  DelayT = nf(iDelayT, 3);   // make DelayT have 3 digits. pad with zero if no digits
}

///////////////////////////////////////////////////bang programs//////////////////////////////////////////  
public void Connect() {             // conect to com port bang

  serial = new Serial(this, comList[Ss], 9600);
  println(comList[Ss]);
  myTextarea2.setText("CONNECTED");
}


public void Set_Path() {             // set path bang

  selectInput("Select a file to process:", "fileSelected");
}

public void fileSelected(File selection) {
  if (selection == null) {
    println("Window was closed or the user hit cancel.");
  } 
  else {
    println("User selected " + selection.getAbsolutePath());
    file1 = selection.getAbsolutePath();
    myTextarea.setText(file1);
  }
} 

public void Start_Run() {  // start run bang
  bool = true;
  myTextarea2.setText("RUNNING SCAN");
}


/////////////////////////////////////////////////Serial Read//////////////////////////////////////   
public void  read_serialASV() {

  i=0;
  while (cData!='&') {                       ////////read paramaters/////////
    //while (serial.available() <= 0) {}
    serial.bufferUntil('\n');//}

    if (serial.available() > 0) { 
      cData =  serial.readChar();
      logData(file1, cData, true); 
      if (cData == '&') {
        println("paramaters recieved ASV");
      }
    }
  }
  while (cData!='*') {   /////////read voltammetry data 


    while (serial.available () <= 0) {
    }
    //serial.bufferUntil('\n');//}

    if (serial.available() > 0) {
      // i =0;
      cData =  serial.readChar();
      logData(file1, cData, true); //logData(file1,getDateTime() + sData,true);logData(file1,sData,true);
      if (cData == '*') {
        println("stop");
      }
      // graph datat////////////////////         
      if (cData!='\n') { 
        sData2 = sData2+str(cData);
      }
      if (cData =='\n') {
        //int i =0;
        sData = split(sData2, '\t');     
        sData = trim(sData);
        if (sData.length ==3) { //if(sData.length >1){
          float[] fData= PApplet.parseFloat(sData);
          if (i==0) {
            V[i] = fData[0];
            I1[i]=fData[1]; //    g1[i]=fmap[i];
            I2[i]=fData[2]; //    g2[i]=fmap2[i];
          }
          if (i>0) {
            V = append(V, fData[0]);
            I1 = append(I1, fData[1]); //g1 = append(g1,fmap[i]);
            I2 = append(I2, fData[2]); // g2 = append(g2,fmap2[i]);
          }
          //Idif[i] = I2[i]-I1[i];

          i +=1;// ++i;
        }   
        sData2 = " ";
      }
    }
  }
}   

public void  read_serialCV() {
  i=0;
  while (cData!='&') {                       ////////read paramaters/////////
    //while (serial.available() <= 0) {}
    serial.bufferUntil('\n');//}

    if (serial.available() > 0) { 
      cData =  serial.readChar();
      logData(file1, cData, true); 
      if (cData == '&') {
        println("paramaters recieved CV");
      }
    }
  }
  while (cData!='*') {   /////////read voltammetry data 


    while (serial.available () <= 0) {
    }
    //serial.bufferUntil('\n');//}

    if (serial.available() > 0) {
      // i =0;
      cData =  serial.readChar();
      logData(file1, cData, true); //logData(file1,getDateTime() + sData,true);logData(file1,sData,true);
      if (cData == '*') {
        println("stop");
      }
      // graph datat////////////////////         
      if (cData!='\n') { 
        sData2 = sData2+str(cData);
      }
      if (cData =='\n') {
        //int i =0;
        sData = split(sData2, '\t');     
        sData = trim(sData);
        if (sData.length ==2) { //if(sData.length >1){
          float[] fData= PApplet.parseFloat(sData);
          if (i==0) {
            V[i] = fData[0];
            I1[i]=fData[1]; //    g1[i]=fmap[i];
            // I2[i]=fData[2]; //    g2[i]=fmap2[i];
          }
          if (i>0) {
            V = append(V, fData[0]);
            I1 = append(I1, fData[1]); //g1 = append(g1,fmap[i]);
            //I2 = append(I2, fData[2]); // g2 = append(g2,fmap2[i]);
          }
          //Idif[i] = I2[i]-I1[i];

          i +=1;// ++i;
        }   
        sData2 = " ";
      }
    }
  }
}  


public void logData( String fileName, char newData, boolean appendData)  //void logData( String fileName, String newData, boolean appendData)
{
  BufferedWriter bw=null;
  try { //try to open the file
    FileWriter fw = new FileWriter(fileName, appendData);
    bw = new BufferedWriter(fw);
    bw.write(newData);// + System.getProperty("line.separator"));
  } 
  catch (IOException e) {
  } 
  finally {
    if (bw != null) { //if file was opened try to close
      try {
        bw.close();
      } 
      catch (IOException e) {
      }
    }
  }
}

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "ASV_CV_Jan7_2013" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
