/**
* @file    AreaChart.java
* @author  Nathan Smith A5, Bradley Coles-Perkins A6
* @class   AreaChart
* @date    30/04/13
* @see     
* @brief    A class that creates a single Area Chart 
* or two Area Charts visualizations from a 2D Array of data
* \ see Graph.java
* 
* This class is responsible for making the Area chart
* It was made using JFreeChart and following examples
* on how to set up the Area chart
* It uses the Graph.java to return a JFreeChart
*/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;

public class AreaChart implements ActionListener, Graph {
	
    /**
    * creates a constructor taking in the following parameters:
    * 
    * @param String[][] data - data used to create the visualisation.
    * @param Dataset dataset - the dataset used (rows and columns)
    * @param DataAttribute setting - Data attributes
    */
    public AreaChart ( String[][] data,
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
        
        m_XMin = setting.GetXAxisMin();
        m_XMax = setting.GetXAxisMax();
        m_YMin = setting.GetYAxisMin();
        m_YMax = setting.GetYAxisMax();
        
        m_XScale = setting.GetXAxisScale();
        setting.GetYAxisScale();
        
        createDataset(m_Data);
        showChart( m_Dataset );
    }  
    /**
     * creates a constructor taking in the following parameters:
     * 
     *  @param String[][] data - data used to create the visualisation.
     * @param Dataset dataset - the dataset used (rows and columns)
     * @param DataAttribute setting - Data attributes
     * @param String[][] secondData - second data used to create the visualisation.
     * @param Dataset secondDataset - the second dataset used (rows and columns)
     * @param DataAttribute secondSetting - Second Data attributes
     */
    public AreaChart ( String[][] data, Dataset dataset,
    		DataAttribute setting, String[][] secondData, Dataset secondDataset,
    		DataAttribute secondSetting ) {
	
		m_Data = data;
		m_ChartTitle = setting.GetTitle();
		m_Setting = setting;
		m_XLabel = setting.GetAxisLabelX();
		m_YLabel = setting.GetAxisLabelY();
		m_Col = dataset.GetNoOfCols();
		m_Row = dataset.GetNoOfRows();
		m_C1 = setting.GetSelectedXIndex();
		m_C2 = setting.GetSelectedYIndex();
		
		m_XMin = setting.GetXAxisMin();
		m_XMax = setting.GetXAxisMax();
		m_YMin = setting.GetYAxisMin();
		m_YMax = setting.GetYAxisMax();
		
		m_XScale = setting.GetXAxisScale();
		setting.GetYAxisScale();
		
		m_SecondData = secondData;
		m_SecondChartTitle = secondSetting.GetSecondTitle();
		m_SecondSetting = secondSetting;
		m_SecondXLabel = secondSetting.GetSecondAxisLabelX();
		m_SecondYLabel = secondSetting.GetSecondAxisLabelY();
		m_SecondCol = secondDataset.GetNoOfCols();
		m_SecondRow = secondDataset.GetNoOfRows();
		m_SecondC1 = secondSetting.GetSecondSelectedXIndex();
		m_SecondC2 = secondSetting.GetSecondSelectedYIndex();

		secondSetting.GetYAxisScale();
		
		createDataset(m_Data);
		createSecondDataset(m_SecondData);
		showChart( m_Dataset, m_SecondDataset );
	} 
    
    /**
    * constructor for JUnit Tests
    * @param setting
    */
    public AreaChart (DataAttribute setting){       
   	 	m_ChartTitle = setting.GetTitle();
	     m_Setting = setting;
	     m_XLabel = setting.GetAxisLabelX();
	     m_YLabel = setting.GetAxisLabelY();
	     m_C1 = setting.GetSelectedXIndex();
	     m_C2 = setting.GetSelectedYIndex();
    }
    /**
     * second empty constructor for JUnit Tests
     * @param setting
     * @param secondSetting
     */
    public AreaChart (DataAttribute setting, DataAttribute secondSetting){       
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
	
    /** allows user to select the colour of the points on the area chart
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
    @Override
    public String SetLabelX( String m_xLabel ) {
    String m_labelX = m_xLabel;
		
        boolean test = true;
        if ( ( ( m_xLabel.isEmpty() )||( m_xLabel.length() > MAX_LABEL_LENGTH ) ) 
                 && ( test == true ) ) {
            System.err.println( "AreaChart::SetLabelX() " + 
                                m_xLabel );
        }
        return m_labelX;
    }
	
    /** 
    * @param m_yLabel - the y axis label passed from users to the graph
    * @return m_labelY - returns the user's y label value
    */
    @Override
    public String SetLabelY( String m_yLabel ) {
        String m_labelY = m_yLabel;
		
        boolean test = true;
        if ( ( ( m_yLabel.isEmpty() )||( m_yLabel.length() > MAX_LABEL_LENGTH ) ) 
                 && ( test == true ) ) {
            System.err.println( "AreaChart::SetLabelY() " + 
                                m_yLabel );
        }
        return m_labelY;
    }
    
    /** 
    * @param m_graphTitle - the title passed from users to the graph 
    * @return m_labelY - returns the user's title value
    */
    @Override
    public String SetTitle( String m_graphTitle ) {
        String m_title = m_graphTitle;
		
        boolean test = true;
        if ( ( ( m_graphTitle.isEmpty() )||( m_graphTitle.length() > MAX_TITLE_LENGTH ) ) 
                 && ( test == true ) ) {
            System.err.println( "AreaChart::SetTitle() " + 
                                m_graphTitle );
        }	
        return m_title;
    } 
 
    /**
    * creates an XY data set for the two selected columns
    * 
    * @param m_data - pass the 2d array through
    * @return dataset - returns the new data set with new values
    */
    private XYDataset createDataset(String[][] data) {
        m_Dataset = new XYSeriesCollection();
        XYSeries dataSeries = new XYSeries( Key ); 
        
        /* goes through each row and each column in the table */
            for( int c = 0; c < m_Col; c++ ) {
                for( int r = 0; r < m_Row; r++ ) {
	    		
                    /* takes every row value for the two selected columns,
                    * parses them as doubles, then adds them to the xy 
                    * data series 
                    */
                    dataSeries.add( Double.parseDouble( data[m_C1][r] ), 
                              Double.parseDouble( data[m_C2][r] ));  		
	        }
	    }
        m_Dataset.addSeries( dataSeries );     
        return m_Dataset; 
    }
    /**
     * creates the second XY data set for the two selected columns
     * 
     * @param m_data  pass the 2d array through
     * @return dataset  returns the new data set with new values
     */
    private XYDataset createSecondDataset(String[][] data) {
        m_SecondDataset = new XYSeriesCollection();
        XYSeries dataSeries = new XYSeries( Key ); 
        /* goes through each row and each column in the table */
            for( int c = 0; c < m_SecondCol; c++ ) {
                for( int r = 0; r < m_SecondRow; r++ ) {
	    		
                    /* takes every row value for the two selected columns,
                    * parses them as doubles, then adds them to the xy 
                    * data series 
                    */
                    dataSeries.add( Double.parseDouble( data[m_SecondC1][r] ), 
                              Double.parseDouble( data[m_SecondC2][r] ));  		
	        }
	    } 
        m_SecondDataset.addSeries( dataSeries );     
        return m_SecondDataset;
    }
    
    /**
    * creates an Area Chart using the XY data set 
    * and sets the appearance and plot orientation
    * 
    * @param dataset - passes the XY data set through
    * @return graph - returns the Area graph with selected title, x and y labels,
    * and data to be displayed
    */
    private JFreeChart createGraph( XYDataset dataset ) {
        JFreeChart chart = ChartFactory.createXYAreaChart(
            m_ChartTitle,			
            m_XLabel,          
            m_YLabel,          
            dataset,                  		
            /* sets the range (y) axis to vertical */
            PlotOrientation.VERTICAL,
            /* include legend */
            true,
            /* tool tips */
            true,
            /* URLs*/
            false);
     
        chart.setBackgroundPaint(Color.white);

        XYPlot plot = chart.getXYPlot();
        plot.setOutlinePaint(Color.black);
        plot.setBackgroundPaint(Color.lightGray);
        plot.setForegroundAlpha(m_ForegroundAlpha);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        
        if(!m_Setting.GetUseDefault()) {
        	NumberAxis domain = (NumberAxis) plot.getDomainAxis();
            domain.setTickMarkPaint(Color.black);
            domain.setLowerMargin(m_LowerMargin);
            domain.setUpperMargin(m_UpperMargin);
            
            domain.setRange(m_XMin, m_XMax);
            domain.setTickUnit(new NumberTickUnit(m_XScale));
            domain.setVerticalTickLabels(true);
            
            NumberAxis range = (NumberAxis) plot.getRangeAxis();
            range.setRange(m_YMin, m_YMax);
        }
        m_Plot = chart.getXYPlot();
        m_Renderer = m_Plot.getRenderer();
        return chart;
    }
    /**
     * creates the Area Chart using the XY data set 
     * and sets the appearance and plot orientation
     * 
     * @param dataset - passes the XY data set through
     * @return graph - returns the Area graph with selected title, x and y labels,
     * and data to be displayed
     */
    private JFreeChart createSecondGraph( XYDataset dataset ) {
        JFreeChart chart = ChartFactory.createXYAreaChart(
            m_SecondChartTitle,			
            m_SecondXLabel,          
            m_SecondYLabel,          
            dataset,                  		
            /* sets the range (y) axis to vertical */
            PlotOrientation.VERTICAL,
            /* include legend */
            true,
            /* tool tips */
            true,
            /* URLs*/
            false);
     
        chart.setBackgroundPaint(Color.white);

        XYPlot plot = chart.getXYPlot();
        plot.setOutlinePaint(Color.black);
        plot.setBackgroundPaint(Color.lightGray);
        plot.setForegroundAlpha(m_ForegroundAlpha);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        if(!m_SecondSetting.GetUseDefault()) {
        	NumberAxis domain = (NumberAxis) plot.getDomainAxis();
            domain.setTickMarkPaint(Color.black);
            domain.setLowerMargin(m_LowerMargin);
            domain.setUpperMargin(m_UpperMargin);
            
            domain.setRange(m_XMin, m_XMax);
            domain.setTickUnit(new NumberTickUnit(m_XScale));
            domain.setVerticalTickLabels(true);
            
            NumberAxis range = (NumberAxis) plot.getRangeAxis();
            range.setRange(m_YMin, m_YMax);
        }
        m_Plot = chart.getXYPlot();
        m_Renderer = m_Plot.getRenderer();
        return chart;
    }
    
    /**
    *  visualises the Area chart using JFreeChart and sets the 
    * default size and appearance of the window and graph
    * 
    * @param dataset - passes the XY data set through 
    * @return true - returns test results
    */
    private boolean showChart( XYDataset dataset ) {
    	
        JFreeChart chart = createGraph( dataset );
    	ChartPanel chartPanel = new ChartPanel( chart );
    	chartPanel.setPreferredSize( new java.awt.Dimension( CHART_WIDTH, CHART_HEIGHT ) );
    	JPanel colourButtonPanel = new JPanel(new BorderLayout());

    	m_ColChangeButton = new JButton( "Change Colours" );
    	colourButtonPanel.add( m_ColChangeButton,BorderLayout.SOUTH );
    	m_ColChangeButton.addActionListener( this );

    	JFrame AreaChartTest = new JFrame();
    	AreaChartTest.setLayout( new BorderLayout() );
    	AreaChartTest.setSize( HEIGHT_FRAME, WIDTH_FRAME );
    	AreaChartTest.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
    	AreaChartTest.add( chartPanel, BorderLayout.NORTH );
    	AreaChartTest.add(new InformationJPanel(m_Setting));
    	AreaChartTest.add( colourButtonPanel,BorderLayout.SOUTH );
    	AreaChartTest.setVisible( true );

    	return true;
    }
    /**
     *  visualises two Area charts using JFreeChart and sets the 
     * default size and appearance of the window and graph
     * @param dataset - passes the XY data set through
     * @param secondDataset - passes the second XY data set through
     * @return true - returns test results
     */
 private boolean showChart( XYDataset dataset, XYDataset secondDataset ) {
    	
        JFreeChart chart = createGraph( dataset );
        JFreeChart secondChart = createSecondGraph( secondDataset );
    	ChartPanel chartPanel = new ChartPanel( chart );
    	ChartPanel secondChartPanel = new ChartPanel( secondChart );
    	chartPanel.setPreferredSize( new java.awt.Dimension( CHART_WIDTH, CHART_HEIGHT ) );
    	secondChartPanel.setPreferredSize( new java.awt.Dimension( CHART_WIDTH, CHART_HEIGHT ) );
    	JPanel colourButtonPanel = new JPanel(new BorderLayout());

    	m_ColChangeButton = new JButton( "Change Colours" );
    	colourButtonPanel.add( m_ColChangeButton,BorderLayout.SOUTH );
    	m_ColChangeButton.addActionListener( this );

    	JPanel charts = new JPanel();
     	charts.add( chartPanel, BorderLayout.WEST );
     	charts.add( secondChartPanel, BorderLayout.EAST );
    	
    	JFrame AreaChartTest = new JFrame();
    	AreaChartTest.setLayout( new BorderLayout() );
    	AreaChartTest.setSize( LARGE_HEIGHT_FRAME, LARGE_WIDTH_FRAME );
    	AreaChartTest.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
    	AreaChartTest.add( charts, BorderLayout.NORTH );
    	AreaChartTest.add(new InformationJPanel(m_Setting));
    	AreaChartTest.add( colourButtonPanel,BorderLayout.SOUTH );
    	AreaChartTest.setVisible( true );

    	return true;
    }
    /**
     * Method for creating a test dataset 
     * @return dataset
     */
    private static XYDataset createDataset() {	
    	final XYSeries series1 = new XYSeries("Test data");
    	series1.add(TestA, TestB);
    	series1.add(TestC, TestD);
    	series1.add(TestE, TestF);
    	series1.add(TestG, TestH);
    	series1.add(TestI, TestJ);
    	series1.add(TestK, TestL);
    	series1.add(TestM, TestN);
    	series1.add(TestO, TestP);
    	
    	final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        
    	return dataset;
    }
    /**
     * Method for creating a second test dataset 
     * @return dataset
     */
    private static XYDataset createSecondDataset() {	
    	final XYSeries series1 = new XYSeries("Second Test data");
    	series1.add(TestA, TestP);
    	series1.add(TestC, TestN);
    	series1.add(TestE, TestL);
    	series1.add(TestG, TestJ);
    	series1.add(TestI, TestH);
    	series1.add(TestK, TestF);
    	series1.add(TestM, TestD);
    	series1.add(TestO, TestB);
    	
    	final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        
    	return dataset;
    }
    
    /**
    * main method to carry out JUnit Tests
    */
    public static void main( String[] args ) {
    	DataAttribute test = DataAttribute.GetTestDataAttribute();
    	AreaChart areaChartTest = new AreaChart(test);
    	
        final XYDataset testDataset = createDataset();
    	areaChartTest.showChart(testDataset);
    	
    	DataAttribute secondTest = DataAttribute.GetTestDataAttribute();
    	AreaChart secondAreaChartTest = new AreaChart(secondTest);
    	
        final XYDataset secondTestDataset = createSecondDataset();
    	secondAreaChartTest.showChart(testDataset, secondTestDataset);
    }
    
    /** represents a series of vertices to be used as a data set */
    private XYSeriesCollection m_Dataset;
    private XYSeriesCollection m_SecondDataset;
    
    /** renders the visual representation of the (x,y) items */
    private XYItemRenderer m_Renderer;
    
    /** displays a button that can select different colour options */
    private JButton m_ColChangeButton;
    
    /** plots data in the form (x,y) */
    private XYPlot m_Plot; 
	
    /** labels the key of the graph */
    private final String Key = "Data";
    
    /** data used to create the visualisation */
    private String[][] m_Data;
    private String[][] m_SecondData;
    private DataAttribute m_Setting;
    private DataAttribute m_SecondSetting;    
    /**Defining variables for the dataset */
    private int m_Col;
    private int m_Row;
    private String m_ChartTitle;
    private String m_XLabel;
    private String m_YLabel;
    private int m_C1;
    private int m_C2;
    private double m_XMin;
    private double m_XMax;
    private double m_YMin;
    private double m_YMax;
    private double m_XScale;
    private int m_SecondCol;
    private int m_SecondRow;
    private String m_SecondChartTitle;
    private String m_SecondXLabel;
    private String m_SecondYLabel;
    private int m_SecondC1;
    private int m_SecondC2;
    
    /** sets the frame width */
    private final int WIDTH_FRAME = 570;
    private final int HEIGHT_FRAME = 800;
    /** sets the frame width for two graphs */
    private final int LARGE_WIDTH_FRAME = 570;
    private final int LARGE_HEIGHT_FRAME = 1220;
    
    /** sets the chart panel width*/
    private final int CHART_WIDTH = 600;
    
    /** sets the chart panel height*/
    private final int CHART_HEIGHT = 400;
    
    /** sets the max length of label*/
    private final int MAX_LABEL_LENGTH = 25;
    
    /** sets the max length of label*/
    private final int MAX_TITLE_LENGTH = 35;
    
    /**sets the chart Foreground Alpha */
    private float m_ForegroundAlpha = 0.65f;
    
    /**set the initial lower margin number*/
    private double m_LowerMargin = 0.0;
    
    /**set the initial upper margin number*/
    private double m_UpperMargin = 0.0;
	
    /** Data for the test Coordinate A */
    private final static int TestA = 1862 ;
        
    /** Data for the test Coordinate B */
    private final static int TestB = 34;
    
    /** Data for the test Coordinate C */
    private final static int TestC = 1863;
    
    /** Data for the test Coordinate D */
    private final static int TestD = 49;
    
    /** Data for the test Coordinate E */
    private final static int TestE = 1861;
    
    /** Data for the test Coordinate F */
    private final static int TestF = 60;
    
    /** Data for the test Coordinate G */
    private final static int TestG = 1900;
    
    /** Data for the test Coordinate H */
    private final static int TestH = 83;
    
    /** Data for the test Coordinate I */
    private final static int TestI = 1910;
    
    /** Data for the test Coordinate J */
    private final static int TestJ = 25;
    
    /** Data for the test Coordinate K */
    private final static int TestK = 1925;
    
    /** Data for the test Coordinate L */
    private final static int TestL = 114;
    
    /** Data for the test Coordinate M */
    private final static int TestM = 1929;
    
    /** Data for the test Coordinate N */
    private final static int TestN = 73;
    
    /** Data for the test Coordinate O */
    private final static int TestO = 1934 ;
    
    /** Data for the test Coordinate P */
    private final static int TestP = 15;
    
}