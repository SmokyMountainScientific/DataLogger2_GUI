/*  DataLogGraph_1_4
 *   GUI for graphing serial data. 
 *   Based on BHickman potentiostat GUI
 *   modified by Jack Summers 11-24-13
 *  For use with AnalogReadSerialOut-test Energia sketch 
 *    Com port selection tool works
 *    Data logged to file OK. Nov 15, 2012
 *    Currently revision supports graphing in real time.
 *    most recent runnig version saved under N:/Summers/pump
 *    Revision 1.4 attempts to improve speed of updating with 
 *    readStringUntil(LINE_FEED)
 */
 
///////////////////////////////////////// Imports///////////////////////////////
import org.gicentre.utils.gui.TextPopup; // for warning window
import org.gicentre.utils.stat.*;    // For chart classes.
import org.gicentre.utils.multisketch.*; // for integration window
import controlP5.*;
import processing.serial.*;
import java.io.*;                        // this is needed for BufferedWriter
import java.util.Arrays;
/////////////////////////////////////////Classes////////////////////////////////
XYChart lineChart, lineChart2;  

ControlP5 cp5,cp5b,cp5c;
Serial serialPort;
CheckBox ovrLay, channels, chanDisp; 
//Textarea myTextarea;   // com port and status window
Textarea myTextarea2, channelSel, channelDisp;    // save file path window
Textfield gainA, gainB, interval;
DropdownList ports, mode, ovrLy;   // get rid of overlay dropdown
//DropdownList ports;              //Define the variable ports and mode as a Dropdownlist.
//DropdownList ports, mode;      //Define the variable ports and mode as a Dropdownlist.
//////////////////////////////////variables/////////////////////////////////////
float runMode;
boolean Modesel = false;
float overlay = 0;
char[] strtochar;
//char cData;
String sData3;
//String sData3 ="";
//String[] sData = new String[3];  //String sData;
/*float[] V = {0};
float[] I1 = {0};
float[] newV = {0};   // added to reset V after each run Nov 19 BH
float[] newI1 = {0}; */ // added to reset I1 after each run Nov 19 BH
int LINE_FEED = 10; // "\n";  // lines 39 to 43 added 11-22-13 JS,  
//String endTheMaddness = "@";   //single quote gives char
String tab ="\t";

float[] xData = {0};   //must be float, not Float
float[] yData = {0};
float[] yData2 = {0};
float[] nullData = {0};
float[] nullY = {0};
float xRead = 0;   //must be float, not Float
float yReadA = 0;
float yReadB = 0;
float yRead1 = 0;
float yRead2 = 0;

String cGainA;
String cGainB;
String inVal;     // interval
int iGainA;
int iGainB;
int iRate;

int Ss;                          //The dropdown list returns a float, must connvert into an int. 
String[] comList ;               //A string to hold the ports in.
String[] comList2;               // string to compare comlist to and update
boolean serialSet;               //A value to test if we have setup the Serial port.
boolean Comselected = false;     //A value to test if you have chosen a port in the list.
boolean gotparams = false;

boolean run = false;           // start run at bang
float p1;
float p2;

String ComP;
int serialPortNumber;
String file1 = "logdata.txt";
String file2;                  // save file path
//String file;
String[] sData = new String[3];  //String sData;
String sData2 = " ";
char cData;
char cData2;

String colTxt = "Channels A and B";  // collect data
String disTxt = "Channels A and B";   // dipslay data
String ovrTxt = "No overlay";         // overlay text
String modTxt = "Continuous";         // mode = continuous or discrete


//String Go = "1";
//String star = "*";
int i =0;
int p = 0;           //stop signal
PImage logo;
int channel;
int  iChan = 0;
int chanD = 0;
String chanText;
String runTxt;
//////////////font variables////////////////////////////////////////////////////
PFont font = createFont("arial", 18);
PFont font2 = createFont("arial", 16);
PFont font3 = createFont("arial",12); 
PFont font4 = createFont("andalus",16);
/////////////// setup //////////////////////////////////////////////////////////
void setup()
{
  setup_bangs();
  charts_gic_setup();
  cp5_controllers_setup();
  dropLists();
  checkBoxes();
  frameRate(2000);
  size(730, 550); 
   logo = loadImage("LogoSMS.png");
  //frame.setResizable(true);

}
///////////////////End Setup////////////////////////////////////////////////////


void draw()
{
  background(#1A3543);
    image(logo, 29, 500, 130, 34);
 
 ///// boxes /////
  stroke(255);
  noFill();
  rect (12,55,176,200);   //  collection channel box
 // rect (12,143,160,90);
  rect (12,280,176,100);   // display channel box
//  rect (19,89,12,12);
  pushMatrix();
  textFont(font,12);

  fill(#D18B19);       //#AA8A16);
 textAlign(RIGHT);
  text("https://github.com/SmokyMountainScientific",680,height-12);
  popMatrix(); 
  textAlign(LEFT);
  ///////////////////////////// lables for channel toggle buttons
  fill(255);
  textFont(font,12);
  text("A",35,103);
  text("B",35,120);  
  text("A",35,328);
  text("B",35,345);  
  ////////////////////////// Run / stop button lables
  if (run == true) {
   runTxt = "Stop";
   }
 else { 
   runTxt = "Run";
   } 
   
 textFont(font,24);
  fill(255);
  text(runTxt, 90, height-93);
 
  fill(#EADFC9);               // background color
  //noStroke();
  rect(200, 50, 475, 470);    // chart background
  fill(#D18B19);   //#AA8A16);   //250,250,250);             //Chart heading color
  textSize(20); // was 16
  text("Data Collection", 20, 80);
  text("Data Display", 20, 305);
  textSize(16);
  fill(255);   //white
//  String over = "No Overlay";
  text(colTxt,50, 110);
  text(disTxt,50,338);
  text(ovrTxt,50, 370);
  text(modTxt,50, 243);
/*  fill(0,0,0);  
  text("Collect", 125, 80);
  text("Display", 125, 115); */
//  if (iChan ==0 || iChan == 2) {
  if (chanD ==0 || chanD == 2) {
    lineChart.draw(235, 65, 430, 420);    
  }
//  if (iChan ==0 || iChan == 1) {
    if (chanD == 0 || chanD == 1) {
    lineChart2.draw(235, 65, 430, 420);  
  }
  ////////////// update com ports from Ben, added 6/13/14 /////
comList2 = Serial.list();
if(Arrays.equals(comList,comList2)==false) {
  ports.clear();
  comList = comList2;
  for (int i=0; i<comList.length; i++) {
    ports.addItem(comList[i],i);
  }
}
if(comList.length == 0){
  myTextarea2.setText("NOT CONN.");
  ports.clear();
  ports.captionLabel().set("Select COM port");
  try{
    serialPort.stop();
  }
  catch(Exception e){
  }
  Comselected = false;
}
/////////////////////// show or hide text boxes //////////////////
if (iChan == 3){
gainA.hide();
gainB.hide();
interval.hide();
}
else if (iChan == 2){
gainA.show();
gainB.hide();
interval.show();
}
else if (iChan == 1){
gainA.hide();
gainB.show();
interval.show();
}
else {
gainA.show();
gainB.show();
interval.show();
}
//////////////////// y axis label //////////////////////
     fill(0,0,0);
   int posX =220;  // x position for center of y axis
   int posY = 260;  // y position for center of y axis
  translate(posX,posY);
  rotate(3.14159*3/2);
  textAlign(CENTER);

  String yChartLabel = "Reading  (mV)";  // use if statements to change to pH, etc

  text(yChartLabel, 0, 0);
  rotate(3.14159/2);        // return orientation and location
  translate(-posX,-posY);  
////////////////// x axis label ///////////////////////
  String xChartLabel = "Time (seconds)";
  posX = 475;
  posY = 515;
  translate(posX,posY);
  textAlign(CENTER);
  text(xChartLabel, 0, 0);
  translate(-posX,-posY);  

///////////////////////  start run //////////////////
  if (run == true && Comselected == true)
  {
    if(gotparams == false)   // added to update chart in real time Nov19 BH
    {
     if (yData.length != 0 && overlay == 0) {// from Ben's-6/13/14
        xData = nullData;  /// Clear X and Y data to redraw chart
        yData = nullY;
        yData2 = nullY;
        xData[0] = 0;  //shows up in the final graph when in SerialRead.
        }
   getParams();    // get paramaters from text fields (text field programs)

///////// copied from WheeStat   
  delay(500);  
  serialPort.write("&");    // initiate parameter read

  delay(100);
    if (runMode == 0) {  //"continuous"){
    serialPort.write("000000");
    }
    else {
    serialPort.write("000001");
    }

// serialPort.write(runMode);   // define channels to read
    delay(100);   
// iChan = nf(iChan, 6);
 serialPort.write(iChan);   // define channels to read
    delay(100); 
  serialPort.write(inVal);    // read interval
   delay(100);
  serialPort.write(cGainA);   // gain on channel A
    delay(100);  
  serialPort.write(cGainB);
    delay(100);  
   
      p=0;     // not in WheeStat               // reset counter for serial read
    println("&");
    if (runMode == 0) {  //"continuous"){
    println("000000");
    }
    else {
    println("000001");
    }
    println(iChan);
    println(inVal);
    println(cGainA);
    println(cGainB);
    println("begin run");   // shows up in bottom window
      delay(100);
      
      logData(file1, "", false);     // log data to file 1, do not append, start new file
      
      ////////read parameter input until LaunchPad transmits '&'/////////
      while (cData!='&' && cData !='@')
      {         
          if (serialPort.available () <= 0) {}
          if (serialPort.available() > 0)
          {
            cData =  serialPort.readChar();     // cData is character read from serial comm. port
            sData2 = str(cData);            //sData2  is string of cData 
            logData(file1, sData2, true);   // at this point we are logging the parameters
            
            println(sData2);
       //     errorText.setText("");             
            
            if (cData == '&')               //  Launchpad sends & char at end of serial write
            {
              println("parameters received");
              gotparams = true;
              logData(file1,"\r\n",true);  // added 6/13-from Ben, what does this do?
           }
          }
      }  // end of while loop with params
  } // end if gotparam == false   Nov 19 BH
    
       //////////// graph data //////////////////////////////////////////////
          read_serial();
      }
        if (xData.length>4 && xData.length==yData.length)
        {
          float maxY = max(yData);
          float minY = min(yData);
          float maxY2 = max(yData2);
          float minY2 = min(yData2);
          if (maxY <= maxY2) {
            maxY = maxY2;
          }
          if (minY >= minY2) {
            minY = minY2;
          }
          lineChart.setMaxX(max(xData));
          lineChart.setMaxY(maxY);
          lineChart.setMinX(min(xData));
          lineChart.setMinY(minY);
          lineChart.setData(xData, yData);
          
          lineChart2.setMaxX(max(xData));
          lineChart2.setMaxY(maxY);
          lineChart2.setMinX(min(xData));
          lineChart2.setMinY(minY);
          lineChart2.setData(xData, yData2);

  //        updatechart = i;
        } // End of if (V.length stuff
  if(run==true && comList.length == 0){
    run = false;
    Comselected = false;
    myTextarea2.setText("No COM");
    println("comm not connected");
  }

    } /// end of while (cData not @) loop





