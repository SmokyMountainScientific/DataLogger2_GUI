void charts_gic_setup(){
  
              ////////////////////////////////gicentre charts///
  lineChart = new XYChart(this);
  lineChart.setData(new float[] {1, 2, 3}, new float[] {1, 2, 3});
  lineChart.showXAxis(true); 
  lineChart.showYAxis(true);
 // lineChart.setXAxisLabelColour(color(234, 28, 28));  
//  fill(#DEC507);    
//  lineChart.setXAxisLabel("Time (ms)");
//  lineChart.setYAxisLabel("ADC Response (of 1024)"); 
  //lineChart.setMinY(0);   
  lineChart.setYFormat("##.##");  
  lineChart.setXFormat("##.##");       
  // Symbol colours
  lineChart.setPointColour(color(234, 28, 28));
  lineChart.setPointSize(5);
  lineChart.setLineWidth(2);
  
  lineChart2 = new XYChart(this);
  lineChart2.setData(new float[] {1, 2, 3}, new float[] {1, 2, 3});
  lineChart2.showXAxis(true); 
  lineChart2.showYAxis(true);
  lineChart2.setYFormat("##.##");  
  lineChart2.setXFormat("##.##");       
  lineChart2.setPointColour(color(1, 40, 142));
  lineChart2.setPointSize(5);
  lineChart2.setLineWidth(2);

 
}

////////////////////////////////////////////////end charts_gic_setup///////////////////////////////////////////////
