/**
* @file    PolarChart.java
* @author  Mark Leyshon A5, Bradley Coles-Perkins A6
* @class   PolarChart
* @date    14/04/13
* @see     
* @brief    A class that creates a single Polar Chart or two Polar
* Chart visualisations from 2D Arrays of data
* \ see Graph.java
* 
* This class is responsible for making the Polar chart
* It was made using JFreeChart and following examples
* on how to set up the Polar chart
* It uses the Graph.java to return a JFreeChart
*/
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PolarPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.DefaultPolarItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class PolarChart implements ActionListener, Graph {
	
	/**
	    * creates a constructor taking in the following parameters:
	    * 
	    * @param String[][] data - data used to create the visualisation.
	    * @param Dataset dataset - the dataset used (rows and columns)
	    * @param DataAttribute setting - Data attributes
	    */
	    public PolarChart ( String[][] data,
	    				   Dataset dataset, 
	    				   DataAttribute setting ) {
	        
	    	m_Data = data;
	        m_ChartTitle = setting.GetTitle();
	        m_Setting = setting;
	        m_XLabel = setting.GetAxisLabelX();
	        m_YLabel = setting.GetAxisLabelY();
	        m_Col = dataset.GetNoOfCols();
	        m_Row = dataset.GetNoOfRows();
	        m_C1 = setting.GetSelectedXIndex();
	        m_C2 = setting.GetSelectedYIndex();
	        
	        CreateDataset(m_Data);
	        ShowChart(m_Dataset);
	    }  
	    /**
	     * creates a constructor taking in the following parameters:
	     * 
	     * @param String[][] data - data used to create the visualisation.
	     * @param Dataset dataset - the dataset used (rows and columns)
	     * @param DataAttribute setting - Data attributes
	     * @param String[][] secondData - second data used to create the visualisation.
	     * @param Dataset secondDataset - the second dataset used (rows and columns)
	     * @param DataAttribute secondSetting - Second Data attributes
	     */
	    public PolarChart ( String[][] data, Dataset dataset,
	    		DataAttribute setting, String[][] secondData, Dataset secondDataset,
	    		DataAttribute secondSetting) {
	    	m_Data = data;
			m_SecondData = secondData;
			m_Setting = setting;
			m_SecondSetting = secondSetting;
			m_ChartTitle = setting.GetTitle();
			m_SecondChartTitle = secondSetting.GetSecondTitle();
			m_XLabel = setting.GetAxisLabelX();
			m_SecondXLabel = secondSetting.GetSecondAxisLabelX();
			m_YLabel = setting.GetAxisLabelY();
			m_SecondYLabel = secondSetting.GetSecondAxisLabelY();
			m_Col = dataset.GetNoOfCols();
			m_SecondCol = secondDataset.GetNoOfCols();
			m_Row = dataset.GetNoOfRows();
			m_SecondRow = secondDataset.GetNoOfRows();
			m_C1 = setting.GetSelectedXIndex();
			m_SecondC1 = secondSetting.GetSecondSelectedXIndex();
			m_C2 = setting.GetSelectedYIndex();
			m_SecondC2 = secondSetting.GetSecondSelectedYIndex();
			 
			 CreateDataset(m_Data);
		     CreateSecondDataset(m_SecondData);
			 ShowChart(m_Dataset, m_SecondDataset);
		}  
	    
	    /**
	    * constructor for JUnit Tests
	    */
	    public PolarChart (DataAttribute setting) {
	    	 m_ChartTitle = setting.GetTitle();
	         m_Setting = setting;
	         m_XLabel = setting.GetAxisLabelX();
	         m_YLabel = setting.GetAxisLabelY();
	         m_C1 = setting.GetSelectedXIndex();
	         m_C2 = setting.GetSelectedYIndex();
	        
	    }
	    /**
	     * second constructor for JUnit Tests
	     */
	    public PolarChart (DataAttribute setting, DataAttribute secondSetting) {
	    	 m_ChartTitle = setting.GetTitle();
	         m_Setting = setting;
	         m_XLabel = setting.GetAxisLabelX();
	         m_YLabel = setting.GetAxisLabelY();
	         m_C1 = setting.GetSelectedXIndex();
	         m_C2 = setting.GetSelectedYIndex();
	         
	         m_SecondChartTitle = secondSetting.GetSecondTitle();
	         m_SecondSetting = secondSetting;
	         m_SecondXLabel = secondSetting.GetSecondAxisLabelX();
	         m_SecondYLabel = secondSetting.GetSecondAxisLabelY();
	         m_SecondC1 = secondSetting.GetSecondSelectedXIndex();
	         m_SecondC2 = secondSetting.GetSecondSelectedYIndex();
	        
	    }
		
	    /** allows user to select the colour of the points on the Polar chart
	    */
	    @Override
	    public void actionPerformed( ActionEvent e ) {
	        if( e.getSource() == m_ColChangeButton ) {
	            ColourMap cM = new ColourMap();
	            cM.SetupData( m_Plot.getSeriesCount(), 
	                          m_Renderer );
	            cM.setVisible( false );
	        }
	    }
		
	    /** 
	    * @param m_xLabel - the x axis label passed from users to the graph 
	    * @return  m_labelX - returns the user's x label value
	    */
	
	    public String SetLabelX( String m_xLabel ) {
	    	String m_labelX = m_xLabel;
	        return m_labelX;
	    }
		
	    /** 
	    * @param m_yLabel - the y axis label passed from users to the graph
	    * @return m_labelY - returns the user's y label value
	    */
	    
	    public String SetLabelY( String m_yLabel ) {
	        String m_labelY = m_yLabel;
	        return m_labelY;
	    }
	    
	    /** 
	    * @param m_graphTitle - the title passed from users to the graph 
	    * @return m_title - returns the user's title value
	    */
	
	    public String SetTitle( String m_graphTitle ) {
	        String m_title = m_graphTitle;
	        return m_title;
	    } 
	  	
	    /**
	    * creates an XY data set for the two selected columns
	    * 
	    * @param String[][] data - pass the 2d array through
	    * @return XYDataset dataset - returns the new data set with new values
	    */
	    private XYDataset CreateDataset(String[][] data) {
	    	XYSeriesCollection collection = new XYSeriesCollection();
            for( int r = 0; r < m_Row; r++ ) {
            	XYSeries dataSeries = new XYSeries( Key + r); 
                dataSeries.add( Double.parseDouble( data[m_C1][r] ), 
                				Double.parseDouble( data[m_C2][r] ));  		
                collection.addSeries( dataSeries );   
	        }
            m_Dataset = collection;
	        return collection;
	    }
	    /**
	     * creates a second XY data set for the two selected columns
	     * 
	     * @param String[][] data - pass the 2d array through
	     * @return XYDataset dataset - returns the second data set with new values
	     */
	    private XYDataset CreateSecondDataset(String[][] data) {
	    	XYSeriesCollection collection = new XYSeriesCollection();
            for( int r = 0; r < m_SecondRow; r++ ) {
            	XYSeries dataSeries = new XYSeries( Key + r); 
                dataSeries.add( Double.parseDouble( data[m_SecondC1][r] ), 
                				Double.parseDouble( data[m_SecondC2][r] ));  		
                collection.addSeries( dataSeries );   
	        }
            m_SecondDataset = collection;
	        return collection;
	    }
	    /**
	     * creates a Polar Chart using the XY data set 
	     * and sets the appearance and plot orientation
	     * 
	     * @param dataset - passes the XY data set through
	     * @return graph - returns the Area graph with selected title, x and y labels,
	     * and data to be displayed
	     */
	    private JFreeChart createGraph( XYDataset dataset ) {
	    	JFreeChart chart = ChartFactory.createPolarChart(
	    			m_ChartTitle,
	    			dataset, 
	    			false, 
	    			false,
	    			false);
	        
	    	chart.setBackgroundPaint(Color.white);
	    	
	    	final PolarPlot plot = (PolarPlot) chart.getPlot();
	        final DefaultPolarItemRenderer renderer = (DefaultPolarItemRenderer) plot.getRenderer();
	        renderer.setSeriesFilled(2, true);
	        
	        m_Plot = plot;
	        m_Renderer = renderer;
	        
	        return chart;
	    }
	    /**
	     * creates a second Polar Chart using the XY data set 
	     * and sets the appearance and plot orientation
	     * 
	     * @param dataset - passes the XY data set through
	     * @return graph - returns the Area graph with selected title, x and y labels,
	     * and data to be displayed
	     */
	    private JFreeChart createSecondGraph( XYDataset dataset ) {
	    	JFreeChart chart = ChartFactory.createPolarChart(
	    			m_SecondChartTitle,
	    			dataset, 
	    			false, 
	    			false,
	    			false);
	        
	    	chart.setBackgroundPaint(Color.white);	    	
	    	final PolarPlot plot = (PolarPlot) chart.getPlot();
	        final DefaultPolarItemRenderer renderer = (DefaultPolarItemRenderer) plot.getRenderer();
	        renderer.setSeriesFilled(2, true);
	        
	        m_Plot = plot;
	        m_Renderer = renderer;
	        
	        return chart;
	    }
	    
	    /**
	    *  visualises the Polar chart using JFreeChart and sets the 
	    * default size and appearance of the window and graph
	    * 
	    * @param XYDataset dataset - passes the XY data set through 
	    * @return Boolean true - returns test results
	    */
	    private boolean ShowChart( XYDataset dataset ) {
	    	JFreeChart graph = createGraph( dataset );
	    	ChartPanel chartPanel = new ChartPanel( graph );
	    	chartPanel.setPreferredSize( new java.awt.Dimension( CHART_WIDTH, CHART_HEIGHT ) );
	    	JPanel colourButtonPanel = new JPanel(new BorderLayout());

	    	m_ColChangeButton = new JButton( "Change Colours" );
	    	colourButtonPanel.add( m_ColChangeButton,BorderLayout.SOUTH );
	    	m_ColChangeButton.addActionListener( this );
	        
	    	JFrame test = new JFrame();
	        test.setLayout( new BorderLayout());
	        test.setSize( FRAME_HEIGHT, FRAME_WIDTH );
	        test.setTitle( "Polar Chart" );
	        test.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
	        test.add( chartPanel, BorderLayout.NORTH );
	        test.add(new InformationJPanel(m_Setting), BorderLayout.CENTER);
	        test.add( m_ColChangeButton, BorderLayout.SOUTH );
	        test.setVisible( true );
	         
	    	return true;
	    }
	    
	    /**
	    *  visualises two Polar charts using JFreeChart and sets the 
	    * default size and appearance of the window and graph
	    * 
	    * @param XYDataset dataset - passes the XY data set through
	    * @param XYDataset secondDataset - passes the second XY data set through  
	    * @return Boolean true - returns test results
	    */
	    private boolean ShowChart( XYDataset dataset, XYDataset secondDataset ) {
	    	JFreeChart graph = createGraph( dataset );
	    	JFreeChart secondGraph = createSecondGraph( secondDataset );
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
	        
	    	JFrame test = new JFrame();
	        test.setLayout( new BorderLayout());
	        test.setSize( LARGE_FRAME_WIDTH, LARGE_FRAME_HEIGHT);
	        test.setTitle( "Polar Chart" );
	        test.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
	        test.add(charts, BorderLayout.NORTH);
	        test.add(new InformationJPanel(m_Setting), BorderLayout.CENTER);
	        test.add( m_ColChangeButton, BorderLayout.SOUTH );
	        test.setVisible( true );
	         
	    	return true;
	    }
	    
	    /**
	    * main method to carry out JUnit Tests
	    */
	    public static void main( String[] args ) {
			
			Random generator = new Random();
			// Test to display a single chart
			XYSeriesCollection testCollection = new XYSeriesCollection();
			XYSeries testDataset = new XYSeries("Test Series");
			
			DataAttribute test = DataAttribute.GetTestDataAttribute();
			PolarChart polarChartTest = new PolarChart(test);
	    	
	        for(int i = 0;i< LOOP;i++) {
	        	int temp1 = generator.nextInt(LIMIT);
	        	int temp2 = generator.nextInt(LIMIT);
	        	
	        	testDataset.add(temp1,temp2);
	        }
	        testCollection.addSeries(testDataset);
	    	polarChartTest.ShowChart(testCollection);
	    	
	    	// Test to display two charts
	    	XYSeriesCollection secondTestCollection = new XYSeriesCollection();
			XYSeries secondTestDataset = new XYSeries("Second Test Series");
			
			DataAttribute secondTest = DataAttribute.GetTestDataAttribute();
			PolarChart secondPolarChartTest = new PolarChart(secondTest);
	    	
	        for(int i = 0;i< LOOP;i++) {
	        	int temp1 = generator.nextInt(LIMIT);
	        	int temp2 = generator.nextInt(LIMIT);
	        	
	        	secondTestDataset.add(temp1,temp2);
	        }
	        secondTestCollection.addSeries(secondTestDataset);
	    	secondPolarChartTest.ShowChart(testCollection,secondTestCollection);
	    
	    
	    }
	    
	    /** declare variables */
	    private final static int LOOP =10;
	    private final static int LIMIT = 360;
	    /** represents a series of vertices to be used as a data set */
	    private XYSeriesCollection m_Dataset;
	    private XYSeriesCollection m_SecondDataset;
	    /** renders the visual representation of the (x,y) items */
	    private DefaultPolarItemRenderer m_Renderer;
	    /** displays a button that can select different colour options */
	    private JButton m_ColChangeButton;
	    
	    /** plots data in the form (x,y) */
	    private PolarPlot m_Plot; 
		
	    /** labels the key of the graph */
	    private final String Key = "Data";
	    
	    /** data used to create the visualisation */
	    private String[][] m_Data;
	    private String[][] m_SecondData;
	    
	    //private DataAttribute m_Setting;
	    
	    /**Defining variables for the dataset */
	    private int m_Col;
	    private int m_Row;;
	    private String m_ChartTitle;
	    private String m_XLabel;
	    private String m_YLabel;
	    private int m_C1;
	    private int m_C2;
		private String m_SecondChartTitle;
		private String m_SecondXLabel;
		private String m_SecondYLabel;
		private int m_SecondCol;
		private int m_SecondRow; 
		private int m_SecondC1; 
		private int m_SecondC2;
	    
	    /** sets the frame width */
	    private final int FRAME_WIDTH = 570;
	    private final int FRAME_HEIGHT = 800;
	    private final int LARGE_FRAME_WIDTH = 1130;
	    private final int LARGE_FRAME_HEIGHT = 600;
	    /**  Create the dataset instances */
	    private DataAttribute m_Setting;
	    private DataAttribute m_SecondSetting;
    
	    /**  Create the chart sizes */
        private final int CHART_HEIGHT = 400;
        private final int CHART_WIDTH = 550;
}
