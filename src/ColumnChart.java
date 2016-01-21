/**
* @file ColumnChart.java
* @author Gavin Driscoll A4, Mark Leyshon A5, Bradley Coles-Perkins A6
* @class ColumnChart
* @date 30/04/2013
* @see Dataset.java, BobViz.java
*
* @brief A class that displays specific data in a Column Chart visualiser.
*
*/
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class ColumnChart implements ActionListener {

    /**
    * Create a constructor taking in the following parameters:
    * @param String[][] data - data used to create the visualisation.
    * @param Dataset dataset - the dataset used (rows and columns)
    * @param DataAttribute setting - Data attributes
    * Create the column chart dataset and instantiate it. 
    * JFreeChart.
    */
    public ColumnChart( String[][] data, 
    					Dataset dataset, 
    					DataAttribute setting ) {
    	
        m_Data = data;
        m_Setting = setting;
        m_ChartTitle = setting.GetTitle();
        m_XLabel = setting.GetAxisLabelX();
        m_YLabel = setting.GetAxisLabelY();
        m_Row = dataset.GetNoOfRows();
        m_C1 = setting.GetSelectedXIndex();
        m_C2 = setting.GetSelectedYIndex();
        
        m_XMin = setting.GetXAxisMin();
        m_XMax = setting.GetYAxisMax();
        m_XScale = setting.GetXAxisScale();
        m_YScale = setting.GetYAxisScale();

        CreateDataset( m_Data );
        ShowGraph( m_Dataset );
        
    }	
    

    /**
    * Create a constructor taking in the following parameters:
    * @param String[][] data - data used to create the visualisation.
    * @param Dataset dataset - the dataset used (rows and columns)
    * @param DataAttribute setting - Data attributes
    * @param String[][] secondData - second data used to create the visualisation.
    * @param Dataset secondDataset - the second dataset used (rows and columns)
    * @param DataAttribute secondSetting - Second Data attributes
    * 
    * Create both column chart datasets and instantiate it. 
    * JFreeChart.
    */
    public ColumnChart( String[][] data, Dataset dataset,
    		DataAttribute setting, String[][] secondData, Dataset secondDataset,
    		DataAttribute secondSetting) {

    	m_Data = data;
    	m_SecondData = secondData;
    	
    	m_Setting = setting;
    	m_SecondSetting = secondSetting;
    	
    	m_ChartTitle = setting.GetTitle();
        m_SecondChartTitle = secondSetting.GetSecondTitle();
    	
        m_XLabel = setting.GetAxisLabelX();
    	m_YLabel = setting.GetAxisLabelY();
    	m_Row = dataset.GetNoOfRows();
    	m_C1 = setting.GetSelectedXIndex();
    	m_C2 = setting.GetSelectedYIndex();
    	
    	m_SecondXLabel = secondSetting.GetSecondAxisLabelX();
    	m_SecondYLabel = secondSetting.GetSecondAxisLabelY();
    	m_SecondRow = secondDataset.GetNoOfRows();    
    	m_SecondC1 = secondSetting.GetSecondSelectedXIndex();
    	m_SecondC2 = secondSetting.GetSecondSelectedYIndex();

    	m_XMin = setting.GetXAxisMin();
    	m_XMax = setting.GetYAxisMax();
    	m_XScale = setting.GetXAxisScale();
    	m_YScale = setting.GetYAxisScale();

    	CreateDataset( m_Data );
    	CreateSecondDataset( m_SecondData );
    	ShowGraph( m_Dataset, m_SecondDataset);
    }	
    /**
     * constructor for JUnit Tests
     * @param setting
     */
    public ColumnChart(DataAttribute setting) {
        m_ChartTitle = setting.GetTitle();
        m_Setting = setting;
        m_XLabel = setting.GetAxisLabelX();
        m_YLabel = setting.GetAxisLabelY();
        m_C1 = setting.GetSelectedXIndex();
        m_C2 = setting.GetSelectedYIndex();
        m_XMin = setting.GetXAxisMin();
        m_XMax = setting.GetXAxisMax();
        m_YMin = setting.GetYAxisMin();
        m_YMax = setting.GetYAxisMax();
        m_XScale = setting.GetXAxisScale();
        m_YScale = setting.GetYAxisScale();
    }
    /**
     * second constructor for JUnit Tests
     * @param setting
     * @param secondSetting
     */
    public ColumnChart(DataAttribute setting, DataAttribute secondsetting) {
        m_ChartTitle = setting.GetTitle();
        m_Setting = setting;
        m_XLabel = setting.GetAxisLabelX();
        m_YLabel = setting.GetAxisLabelY();
        m_C1 = setting.GetSelectedXIndex();
        m_C2 = setting.GetSelectedYIndex();
        m_XMin = setting.GetXAxisMin();
        m_XMax = setting.GetXAxisMax();
        m_YMin = setting.GetYAxisMin();
        m_YMax = setting.GetYAxisMax();
        m_XScale = setting.GetXAxisScale();
        m_YScale = setting.GetYAxisScale();
        
        m_SecondChartTitle = secondsetting.GetSecondTitle();
        m_SecondSetting = secondsetting;
        m_SecondXLabel = secondsetting.GetSecondAxisLabelX();
        m_SecondYLabel = secondsetting.GetSecondAxisLabelY();
        m_SecondC1 = secondsetting.GetSecondSelectedXIndex();
        m_SecondC2 = secondsetting.GetSecondSelectedYIndex();;
    }

    /**
    * a test taking a String argument.
    * @param String - ChartTitle a String argument.
    * @return boolean - The test results.
    */
    public boolean SetChartTitle( String ChartTitle ){
        m_ChartTitle = ChartTitle;
        return true;
    }
    /**
     * a second test taking a String argument.
     * @param String - ChartTitle a String argument.
     * @return boolean - The test results.
     */
    public boolean SetSecondChartTitle( String ChartTitle ){
        m_SecondChartTitle = ChartTitle;
        return true;
    }
    
    /**
	 * Method to make a dataset usable by the BarChart
	 * @return XYSeriesCollection dataset
	 */
    /**
     * creates an XY data set for the two selected columns
     * 
     * @param String[][] data - pass the 2d array through
     * @return XYDataset dataset - returns the new data set with new values
     */
     private XYDataset CreateDataset( String[][] data ) {
         m_Dataset = new XYSeriesCollection();
         XYSeries dataset = new XYSeries( "Series" ); 
         int r = 0;
         for(r = 0; r < m_Row; r++ ) {
         	
         	dataset.add( Double.parseDouble( data[m_C1][r] ), 
                       	Double.parseDouble( data[m_C2][r] ));
         	m_IndexCount++;
         }
         m_Dataset.addSeries( dataset ); 
         return m_Dataset;
     }
     /**
 	 * Method to make a second dataset usable by the BarChart
 	 * @return XYSeriesCollection dataset
 	 */
     /**
      * creates an XY data set for the two selected columns
      * 
      * @param String[][] data - pass the 2d array through
      * @return XYDataset dataset - returns the new data set with new values
      */
     private XYDataset CreateSecondDataset( String[][] data ) {
         m_SecondDataset = new XYSeriesCollection();
         XYSeries dataset = new XYSeries( "Series" ); 
         int r = 0;
         for(r = 0; r < m_SecondRow; r++ ) {
         	
         	dataset.add( Double.parseDouble( data[m_SecondC1][r] ), 
                       	Double.parseDouble( data[m_SecondC2][r] ));
         	m_IndexCount++;
         }
         m_SecondDataset.addSeries( dataset ); 
         return m_SecondDataset;
     }

	/**
	 * @param XYSeriesCollection dataset - the dataset used to make the chart
	 * @return JFreeChart chart - the chart
	 */
     private JFreeChart CreateChart(XYSeriesCollection dataset) {
    	final JFreeChart chart = ChartFactory.createXYBarChart(
				m_ChartTitle, //Chart Title
				m_XLabel, //X Axis Label
				false,  //Legend
				m_YLabel, // Y Axis Label
				dataset, 
				PlotOrientation.VERTICAL, 
				false, //tooltips
				true, //Legend 
				false);

		XYPlot plot = chart.getXYPlot();
        plot.setOutlinePaint(Color.black);
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        m_Renderer = plot.getRenderer();
		m_Renderer.setSeriesItemLabelsVisible( 0, Boolean.TRUE );
		
        if(!m_Setting.GetUseDefault()) {
        	NumberAxis domain = (NumberAxis) plot.getDomainAxis();
            domain.setTickMarkPaint(Color.black);
            
            domain.setRange(m_XMin, m_XMax);
            domain.setTickUnit(new NumberTickUnit(m_XScale));
            domain.setVerticalTickLabels(true);
            
            NumberAxis range = (NumberAxis) plot.getRangeAxis();
            range.setRange(m_YMin, m_YMax);
        }
        return chart;    
    }
    
     /**
 	 * @param XYSeriesCollection dataset - the second dataset used to make the chart
 	 * @return JFreeChart chart - the chart
 	 */
     private JFreeChart CreateSecondChart(XYSeriesCollection dataset) {
		
    	final JFreeChart secondChart = ChartFactory.createXYBarChart(
				m_SecondChartTitle, //Chart Title
				m_SecondXLabel, //X Axis Label
				false,  //Legend
				m_SecondYLabel, // Y Axis Label
				dataset, 
				PlotOrientation.VERTICAL, 
				false, //tooltips
				true, //Legend 
				false);

		XYPlot plot = secondChart.getXYPlot();
        plot.setOutlinePaint(Color.black);
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
       
        m_SecondRenderer = plot.getRenderer();
		m_SecondRenderer.setSeriesItemLabelsVisible( 0, Boolean.TRUE );
		
        if(!m_SecondSetting.GetUseDefault()) {
        	NumberAxis domain = (NumberAxis) plot.getDomainAxis();
            domain.setTickMarkPaint(Color.black);
            
            domain.setRange(m_XMin, m_XMax);
            domain.setTickUnit(new NumberTickUnit(m_XScale));
            domain.setVerticalTickLabels(true);
            
            NumberAxis range = (NumberAxis) plot.getRangeAxis();
            range.setRange(m_YMin, m_YMax);
        }
        return secondChart;    
    }
    
    @Override
    public void actionPerformed( ActionEvent e ) {
        if( e.getSource() == m_ColChangeButton ) {
            ColourMap cM = new ColourMap();
            cM.SetupData( m_IndexCount, m_Renderer );
            cM.setVisible( false );
        }
    }
    
    
    
    /**
     *  visualises the column chart using JFreeChart and sets the 
     * default size and appearance of the window and graph
     * 
     * @param dataset - passes the XY data set through
     * @return true - returns test results
     */
     private boolean ShowGraph(XYSeriesCollection dataset ) {
     	
        JFreeChart graph = CreateChart( dataset );
        ChartPanel chartPanel = new ChartPanel( graph );
     	chartPanel.setPreferredSize( new java.awt.Dimension( CHART_WIDTH, CHART_HEIGHT ) );
     	JPanel colourButtonPanel = new JPanel(new BorderLayout());

     	m_ColChangeButton = new JButton( "Change Colours" );
     	colourButtonPanel.add( m_ColChangeButton,BorderLayout.SOUTH );
     	m_ColChangeButton.addActionListener( this );
     	
     	

     	JFrame columnChartFrame = new JFrame();
     	columnChartFrame.setLayout( new BorderLayout() );
     	columnChartFrame.setSize( FRAME_WIDTH, FRAME_HEIGHT );
     	columnChartFrame.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
     	columnChartFrame.add( chartPanel, BorderLayout.NORTH );
     	columnChartFrame.add(new InformationJPanel(m_Setting));
     	columnChartFrame.add( colourButtonPanel,BorderLayout.SOUTH );
     	columnChartFrame.setVisible( true );

     	return true;
     }
     

     /**
      *  visualises the column chart using JFreeChart and sets the 
      * default size and appearance of the window and graph
      * 
      * @param dataset - passes the XY data set through
      * @param secondDataset - passes the second XY data set through  
      * @return true - returns test results
      */
     private boolean ShowGraph(XYSeriesCollection dataset, XYSeriesCollection secondDataset ) {
      	
        JFreeChart graph = CreateChart( dataset );
        JFreeChart secondGraph = CreateSecondChart( secondDataset );
     	ChartPanel chartPanel = new ChartPanel( graph );
     	ChartPanel secondChartPanel = new ChartPanel( secondGraph );
     	chartPanel.setPreferredSize( new java.awt.Dimension( CHART_WIDTH, CHART_HEIGHT ) );
     	secondChartPanel.setPreferredSize( new java.awt.Dimension( CHART_WIDTH, CHART_HEIGHT ) );
     	
     	JPanel colourButtonPanel = new JPanel(new BorderLayout());

     	m_ColChangeButton = new JButton( "Change Colours" );
     	colourButtonPanel.add( m_ColChangeButton,BorderLayout.SOUTH );
     	m_ColChangeButton.addActionListener( this );
     	
     	JPanel charts = new JPanel();
     	charts.add( chartPanel, BorderLayout.WEST );
     	charts.add( secondChartPanel, BorderLayout.EAST );

     	JFrame columnChartFrame = new JFrame();
     	columnChartFrame.setLayout( new BorderLayout() );
     	columnChartFrame.setSize( LARGE_FRAME_WIDTH, LARGE_FRAME_HEIGHT );
     	columnChartFrame.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
     	columnChartFrame.add(charts, BorderLayout.NORTH);
     	columnChartFrame.add(new InformationJPanel(m_Setting));
     	columnChartFrame.add( colourButtonPanel,BorderLayout.SOUTH );
     	columnChartFrame.setVisible( true );

     	return true;
     }
     
    /**
     * main method to carry out JUnit Tests
     */
     public static void main( String[] args ) {
 		// Test to display a single chart
     	DataAttribute test = DataAttribute.GetTestDataAttribute();
     	ColumnChart columnTest = new ColumnChart(test);
     	
     	Random generator = new Random();
     	
     	XYSeriesCollection dataSeries = new XYSeriesCollection();
     	XYSeries dataset = new XYSeries("Test Series"); 
     	for(int i = 0;i < LOOP;i++) {
     		int temp1 = generator.nextInt(LIMIT);
     		int temp2 = generator.nextInt(LIMIT);
         	dataset.add(temp1,temp2);
     	}
     	dataSeries.addSeries(dataset);
     	
         columnTest.ShowGraph(dataSeries);
         
         // Test to display the two charts
         DataAttribute secondTest = DataAttribute.GetTestDataAttribute();
         ColumnChart columnSecondTest = new ColumnChart(test,secondTest);
         
        XYSeriesCollection secondDataSeries = new XYSeriesCollection();
      	XYSeries secondDataset = new XYSeries("Second Test Series"); 
      	for(int i = 0;i < LOOP;i++) {
      		int temp1 = generator.nextInt(LIMIT);
      		int temp2 = generator.nextInt(LIMIT);
          	secondDataset.add(temp1,temp2);
      	}
      	secondDataSeries.addSeries(dataset);
      	secondDataSeries.addSeries(secondDataset);
         
      	columnSecondTest.ShowGraph(dataSeries, secondDataSeries);
         
     }
     
     private static final int LOOP = 10;
     private static final int LIMIT = 100;
     private XYItemRenderer m_Renderer;
     private XYItemRenderer m_SecondRenderer;
     private XYPlot m_Plot;
     private JButton m_ColChangeButton;
     private int m_IndexCount;
    
     /** x and y label axis */
     private String m_ChartTitle;
     private String m_SecondChartTitle;
    
     /** an array of data for visualisation */
     private String m_Data[][];
     private String m_SecondData[][];
     
     /** represents a series of vertices to be used as a data set */
     private XYSeriesCollection m_Dataset;
     private XYSeriesCollection m_SecondDataset;
     
     /** DataAtrributes */
     private DataAttribute m_Setting;
     private DataAttribute m_SecondSetting;
     
     /** x and y label axis */
     private String m_XLabel;
     private String m_YLabel;
     private String m_SecondXLabel;
     private String m_SecondYLabel;
    
     /** amount of rows */
     private int m_Row;
     private int m_SecondRow;
    
     /** columns */
     private int m_C1;
     private int m_C2;
     private int m_SecondC1;
     private int m_SecondC2;
     private double m_XMin;
     private double m_XMax;
     private double m_YMin;
     private double m_YMax;
     private double m_XScale;
     private double m_YScale;
     

    
     /** sets the frame width */
     private final int FRAME_WIDTH = 800;
     private final int FRAME_HEIGHT = 570;
     private final int LARGE_FRAME_WIDTH = 1220;
     private final int LARGE_FRAME_HEIGHT = 570;
     private final int CHART_WIDTH = 600;
     private final int CHART_HEIGHT = 400;

}