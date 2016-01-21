/**
* @file    LineChart.java
* @author  Bradley Coles-Perkins A5, A6
* @class   LineChart
* @date    30/04/13
* @see     
* @brief    A class that creates a Line Chart visualisation from 
* a 2D Array of data
*/

import java.awt.BorderLayout;
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
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;

import java.awt.Color;

public class LineChart implements ActionListener, Graph {
	
	/**
	    * creates a constructor taking in the following parameters:
	    * 
	    * @param String[][] data - data used to create the visualisation.
	    * @param Dataset dataset - the dataset used (rows and columns)
	    * @param DataAttribute setting - Data attributes
	    */
    public LineChart ( String[][] data,
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
        m_YScale = setting.GetYAxisScale();
        
        createDataset(m_Data);
        showChart(m_Dataset);
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
    public LineChart ( String[][] data, Dataset dataset,
    		DataAttribute setting, String[][] secondData, Dataset secondDataset,
    		DataAttribute secondSetting) {
    	
    	m_Data = data;
    	m_SecondData = secondData;
    	
        m_ChartTitle = setting.GetTitle();
        m_SecondChartTitle = secondSetting.GetSecondTitle();
        
        m_Setting = setting;
        m_SecondSetting = secondSetting;
        
        m_XLabel = setting.GetAxisLabelX();
        m_YLabel = setting.GetAxisLabelY();
        m_SecondXLabel = secondSetting.GetSecondAxisLabelX();
        m_SecondYLabel = secondSetting.GetSecondAxisLabelY();
        
        m_Col = dataset.GetNoOfCols();
        m_Row = dataset.GetNoOfRows();
        m_SecondCol = secondDataset.GetNoOfCols();
        m_SecondRow = secondDataset.GetNoOfRows();
        
        m_C1 = setting.GetSelectedXIndex();
        m_C2 = setting.GetSelectedYIndex();

        m_SecondC1 = secondSetting.GetSecondSelectedXIndex();
        m_SecondC2 = secondSetting.GetSecondSelectedYIndex();
        
        m_XMin = setting.GetXAxisMin();
        m_XMax = setting.GetXAxisMax();
        m_YMin = setting.GetYAxisMin();
        m_YMax = setting.GetYAxisMax();
        
        m_XScale = setting.GetXAxisScale();
        m_YScale = setting.GetYAxisScale();

        createDataset(m_Data);
        createSecondDataset(m_SecondData);
        showChart(m_Dataset, m_SecondDataset);
    }  
    /**
     * constructor for JUnit Tests
     * @param setting
     */
    public LineChart(DataAttribute setting) {
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
     */
    public LineChart(DataAttribute setting, DataAttribute secondSetting) {
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
        
        m_SecondChartTitle = secondSetting.GetSecondTitle();
        m_SecondSetting = secondSetting;
        m_SecondXLabel = secondSetting.GetSecondAxisLabelX();
        m_SecondYLabel = secondSetting.GetSecondAxisLabelY();
        m_SecondC1 = secondSetting.GetSecondSelectedXIndex();
        m_SecondC2 = secondSetting.GetSecondSelectedYIndex();
    }
    /** allows user to select the colour of the points on the line chart
    */
    @Override
    public void actionPerformed( ActionEvent e ) {
        if( e.getSource() == colChangeButton ) {
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
    	m_XLabel = m_xLabel;
        return m_XLabel;
    }

    /** 
    * @param m_xLabel - the second x axis label passed from users to the graph 
    * @return  m_SecondLabelX - returns the user's second x label value
    */
    public String SetSeondLabelX( String m_xLabel ) {
    	m_SecondXLabel = m_xLabel;
        return m_SecondXLabel;
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
     * @param m_yLabel - the y axis label passed from users to the graph
     * @return m_SecondLabelY - returns the user's second y label value
     */
    public String SetSecondLabelY( String m_yLabel ) {
    	m_SecondYLabel = m_yLabel;
        return m_SecondYLabel;
    }
    
    /** 
    * @param String graphTitle - the title passed from users to the graph 
    * @return graphTitle - returns the user's title value
    */
    public String SetTitle( String graphTitle ) {
        String m_title = graphTitle;
        return m_title;
    } 
    /** 
     * @param String graphTitle - the title passed from users to the graph 
     * @return SecondGraphTitle - returns the user's second title value
     */
    public String SetSecondTitle( String graphTitle ) {
        m_SecondChartTitle = graphTitle;
        return m_SecondChartTitle;
    } 
  	
    /**
    * creates an XY data set for the two selected columns
    * 
    * @param m_Data - pass the 2d array through
    * @return dataset - returns the new data set with new values
    */
    private XYDataset createDataset(String[][] data) {
        m_Dataset = new XYSeriesCollection();
        XYSeries dataSeries = new XYSeries( Key ); 
        
            for( int r = 0; r < m_Row; r++ ) {
    		
                dataSeries.add( Double.parseDouble( data[m_C1][r] ), 
                          Double.parseDouble( data[m_C2][r] ));  		
            }
    
	    
        m_Dataset.addSeries( dataSeries );     
        return m_Dataset;        
    }
	
    /**
    * creates a second XY data set for the two selected columns
    * 
    * @param m_SecondData - pass the 2d array through
    * @return secondDataset - returns the new data set with new values
    */
    private XYDataset createSecondDataset( String[][] data ) {
        m_SecondDataset = new XYSeriesCollection();
        XYSeries dataSeries = new XYSeries( Key ); 

        for( int r = 0; r < m_SecondRow; r++ ) {
		
            dataSeries.add( Double.parseDouble( data[m_SecondC1][r] ), 
                      Double.parseDouble( data[m_SecondC2][r] ));  		
        }
        m_SecondDataset.addSeries( dataSeries ); 
        return m_SecondDataset;
    }
    
    /**
    * creates a Line Chart using the XY data set 
    * and sets the appearance and plot orientation
    * 
    * @param dataset - passes the XY data set through
    * @return graph - returns the graph with selected title, x and y labels,
    * and data to be displayed
    */
    private JFreeChart createGraph( XYDataset dataset ) {
        JFreeChart chart = ChartFactory.createXYLineChart(
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

        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, true);
        renderer.setSeriesShapesVisible(0, false);
        plot.setRenderer(renderer);

        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        
        if(!m_Setting.GetUseDefault()) {
        	NumberAxis domain = (NumberAxis) plot.getDomainAxis();
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
     * creates two Line Charts using the XY data set 
     * and sets the appearance and plot orientation
     * 
     * @param dataset - passes the XY data set through
     * @return graph - returns the second graph with selected title, x and y labels,
     * and data to be displayed
     */
    private JFreeChart createSecondGraph( XYDataset dataset ) {
        JFreeChart chart = ChartFactory.createXYLineChart(
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

        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, true);
        renderer.setSeriesShapesVisible(0, false);
        plot.setRenderer(renderer);

        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        
        if(!m_SecondSetting.GetUseDefault()) {
        	NumberAxis domain = (NumberAxis) plot.getDomainAxis();
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
    *  visualises the line chart using JFreeChart and sets the 
    * default size and appearance of the window and graph
    * 
    * @param dataset - passes the XY data set through 
    * @return true - returns test results
    */
    private boolean showChart( XYDataset dataset) {
    	
        JFreeChart chart = createGraph( dataset );
    	ChartPanel chartPanel = new ChartPanel( chart );
    	chartPanel.setPreferredSize( new java.awt.Dimension( CHART_WIDTH, CHART_HEIGHT ) );
    	JPanel colourButtonPanel = new JPanel(new BorderLayout());

    	colChangeButton = new JButton( "Change Colours" );
    	colourButtonPanel.add( colChangeButton,BorderLayout.SOUTH );
    	colChangeButton.addActionListener( this );

    	JFrame LineChartTest = new JFrame();
    	LineChartTest.setLayout( new BorderLayout() );
    	LineChartTest.setSize( WIDTH_FRAME, HEIGHT_FRAME );
    	LineChartTest.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
    	LineChartTest.add( chartPanel, BorderLayout.NORTH );
    	LineChartTest.add(new InformationJPanel(m_Setting));
    	LineChartTest.add( colourButtonPanel,BorderLayout.SOUTH );
    	LineChartTest.setVisible( true );

    	return true;
    }
    /**
     *  visualises two line charts using JFreeChart and sets the 
     * default size and appearance of the window and graph
     * 
     * @param dataset - passes the XY data set through 
     * @param secondDataset - passes the second XY data set through 
     * @return true - returns test results
     */
    private boolean showChart( XYDataset dataset, XYDataset secondDataset) {
    	
        JFreeChart chart = createGraph( dataset );
        JFreeChart secondGraph = createSecondGraph( secondDataset );
    	ChartPanel chartPanel = new ChartPanel( chart );
     	ChartPanel secondChartPanel = new ChartPanel( secondGraph );
    	chartPanel.setPreferredSize( new java.awt.Dimension( CHART_WIDTH, CHART_HEIGHT ) );
    	secondChartPanel.setPreferredSize( new java.awt.Dimension( CHART_WIDTH, CHART_HEIGHT ) );
    	
    	JPanel colourButtonPanel = new JPanel(new BorderLayout());

    	colChangeButton = new JButton( "Change Colours" );
    	colourButtonPanel.add( colChangeButton,BorderLayout.SOUTH );
    	colChangeButton.addActionListener( this );

    	JPanel charts = new JPanel();
     	charts.add( chartPanel, BorderLayout.WEST );
     	charts.add( secondChartPanel, BorderLayout.EAST );
    	
    	JFrame LineChartTest = new JFrame();
    	LineChartTest.setLayout( new BorderLayout() );
    	LineChartTest.setSize( LARGE_FRAME_WIDTH, LARGE_FRAME_HEIGHT );
    	LineChartTest.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
    	LineChartTest.add(charts, BorderLayout.NORTH);
    	LineChartTest.add(new InformationJPanel(m_Setting));
    	LineChartTest.add( colourButtonPanel,BorderLayout.SOUTH );
    	LineChartTest.setVisible( true );

    	return true;
    }

    /**
     * creates a test XYDataset 
     * @return XYDataset dataset - returns a test dataset
     */
    private static XYDataset createDataset() {	
    	final XYSeries series1 = new XYSeries("Test data");
    	series1.add(TestDataW, TestDataX);
    	series1.add(TestDataY, TestDataZ);
    	
    	final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        
    	return dataset;
    }
    /**
     * creates a second test XYDataset 
     * @return XYDataset dataset - returns a test dataset
     */
    private static XYDataset createSecondDataset() {	
    	final XYSeries series1 = new XYSeries("Test data");
    	series1.add(TestDataW, TestDataZ);
    	series1.add(TestDataY, TestDataX);
    	
    	final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        
    	return dataset;
    }
    
    /**
    * main method to carry out JUnit Tests
    */
    public static void main( String[] args ) {
    	 // Test to display a single chart
    	DataAttribute test = DataAttribute.GetTestDataAttribute();
    	LineChart lineChartTest = new LineChart(test);
    	
        final XYDataset datasetTest = createDataset();
    	lineChartTest.showChart(datasetTest);

    	// Test to display the two charts
        DataAttribute secondTest = DataAttribute.GetTestDataAttribute();
        
        LineChart secondLineChartTest = new LineChart(test, secondTest);
    	
        final XYDataset firstDatasetTest = createDataset();
        final XYDataset secondDatasetTest = createSecondDataset();
        
    	secondLineChartTest.showChart(firstDatasetTest,secondDatasetTest);
    	
    }
    
    /** represents a series of vertices to be used as a data set */
    private XYSeriesCollection m_Dataset;
    private XYSeriesCollection m_SecondDataset;
    
    
    /** renders the visual representation of the (x,y) items */
    private XYItemRenderer m_Renderer;
    
    /** displays a button that can select different colour options */
    private JButton colChangeButton;
    
    /** plots data in the form (x,y) */
    private XYPlot m_Plot; 
	
    /** labels the key of the graph */
    private final String Key = "Data";
    
    /** sets the frame width */
    private final int WIDTH_FRAME = 800;
    
    /** sets the frame height */
    private final int HEIGHT_FRAME = 570;
    
    /** sets the frame width for 2 graphs */
    private final int LARGE_FRAME_WIDTH = 1220;
    
    /** sets the frame height for 2 graphs */
    private final int LARGE_FRAME_HEIGHT = 570;
    
    private String[][] m_Data;
    private String[][] m_SecondData;
    
    private DataAttribute m_Setting;
    
    /**Defining variables for the dataset */
    private int m_Col;
    private int m_Row;
    private int m_SecondCol;
    private int m_SecondRow;
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
    private double m_YScale;
    private String m_SecondChartTitle; 
    private DataAttribute m_SecondSetting;
    private String m_SecondXLabel; 
    private String m_SecondYLabel;
    private int m_SecondC1;
    private int m_SecondC2; 

    
    /** sets the chart panel width*/
    private final int CHART_WIDTH = 600;
    
    /** sets the chart panel height*/
    private final int CHART_HEIGHT = 400;
    
    /** magic numbers for the test method */
    private final static Double TestDataW = 1.0;
    
    /** magic numbers for the test method */
    private final static Double TestDataX = 1.0;
    
    /** magic numbers for the test method */
    private final static Double TestDataY = 2.0;
    
    /** magic numbers for the test method */
    private final static Double TestDataZ = 7.0;
}
