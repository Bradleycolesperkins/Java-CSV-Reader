/**
* @file    BubbleChart.java
* @author  Nathan Smith A5, Bradley Coles-Perkins A6
* @class   BubbleChart
* @date    30/04/13
* @see     
* @brief    A class that creates a single Bubble Chart 
* or two Bubble Chart visualizations from a 2D Array of data
* \ see Graph.java
* 
* This class is responsible for making the Bubble chart
* It was made using JFreeChart and following examples
* on how to set up the Bubble chart
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
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.xy.XYZDataset;

public class BubbleChart implements ActionListener, Graph {
	
    /**
    * creates a constructor taking in the following parameters:
    * 
    * @param String[][] data - data used to create the visualisation.
    * @param Dataset dataset - the dataset used (rows and columns)
    * @param DataAttribute setting - Data attributes
    */
    public BubbleChart ( String[][] data,
    				   Dataset dataset, 
    				   DataAttribute setting ) {
        
    	m_Data = data;
    	m_Setting = setting;
        m_ChartTitle = setting.GetTitle();
        m_XLabel = setting.GetAxisLabelX();
        m_YLabel = setting.GetAxisLabelY();
        m_Col = dataset.GetNoOfCols();
        m_Row = dataset.GetNoOfRows();
        m_C1 = setting.GetSelectedXIndex();
        m_C2 = setting.GetSelectedYIndex();
        m_C3 = setting.GetSelectedZIndex();
        
        m_XMin = setting.GetXAxisMin();
        m_XMax = setting.GetYAxisMax();
        m_XScale = setting.GetXAxisScale();
        m_YScale = setting.GetYAxisScale();
        
        CreateDataset( m_Data );
        ShowChart( m_Dataset );
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
    public BubbleChart ( String[][] data, Dataset dataset,
    		DataAttribute setting, String[][] secondData, Dataset secondDataset,
    		DataAttribute secondSetting ) {

		m_Data = data;
		m_Setting = setting;
		m_ChartTitle = setting.GetTitle();
		m_XLabel = setting.GetAxisLabelX();
		m_YLabel = setting.GetAxisLabelY();
		m_Col = dataset.GetNoOfCols();
		m_Row = dataset.GetNoOfRows();
		m_C1 = setting.GetSelectedXIndex();
		m_C2 = setting.GetSelectedYIndex();
		m_C3 = setting.GetSelectedZIndex();
		
		m_XMin = setting.GetXAxisMin();
		m_XMax = setting.GetYAxisMax();
		m_XScale = setting.GetXAxisScale();
		m_YScale = setting.GetYAxisScale();
		
		m_SecondData = secondData;
		m_SecondSetting = secondSetting;
		m_SecondChartTitle = secondSetting.GetSecondTitle();
		m_SecondXLabel = secondSetting.GetSecondAxisLabelX();
		m_SecondYLabel = secondSetting.GetSecondAxisLabelY();
		m_SecondCol = secondDataset.GetNoOfCols();
		m_SecondRow = secondDataset.GetNoOfRows();
		m_SecondC1 = secondSetting.GetSecondSelectedXIndex();
		m_SecondC2 = secondSetting.GetSecondSelectedYIndex();
		m_SecondC3 = secondSetting.GetSecondSelectedZIndex();
		
		 CreateDataset(m_Data);
	     CreateSecondDataset(m_SecondData);
		 ShowChart(m_Dataset, m_SecondDataset);
	} 

    /**
    * constructor for JUnit Tests
    * @param setting
    */
    public BubbleChart(DataAttribute setting) {
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
    public BubbleChart(DataAttribute setting, DataAttribute secondSetting) {
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
    
    /**
    * second empty constructor for JUnit Tests
    */
    public BubbleChart () {}
	
    /** allows user to select the colour of the points on the Bubble chart
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
        return m_labelX;
    }
	
    /** 
    * @param m_yLabel - the y axis label passed from users to the graph
    * @return m_labelY - returns the user's y label value
    */
    @Override
    public String SetLabelY( String m_yLabel ) {
        String m_labelY = m_yLabel;
        return m_labelY;
    }
    
    /** 
    * @param m_graphTitle - the title passed from users to the graph 
    * @return m_labelY - returns the user's title value
    */
    @Override
    public String SetTitle( String m_graphTitle ) {
        String m_title = m_graphTitle;
        return m_title;
    } 
    
    /**
     * Gets the settings
     * @return DataAttribute setting
     */
    public DataAttribute GetSetting() {
    	return m_Setting;
    }
    
    /**
     * Method creates the XYZ Dataset needed for the visualisation
     * @param data
     * @return dataset
     */
    public XYZDataset CreateDataset(String[][] data) {
    	m_Dataset = new DefaultXYZDataset();
		for(int r = 0; r < m_Row;r++) {
			double xArray[] = new double[m_Row];
			double yArray[] = new double[m_Row];
			double zArray[] = new double[m_Row];
			
			xArray[r] = Double.parseDouble(data[m_C1][r]);
			yArray[r] = Double.parseDouble(data[m_C2][r]);
			zArray[r] = Double.parseDouble(data[m_C3][r]);
			
			double ad3[][] = {
					xArray, yArray, zArray
			};
			m_Dataset.addSeries("Series " + r, ad3);
		}
		return m_Dataset;
	}
    /**
     * Method creates the second XYZ Dataset needed for the visualisation
     * @param data 
     * @return dataset
     */
    public XYZDataset CreateSecondDataset(String[][] data) {	
    	m_SecondDataset = new DefaultXYZDataset();
		for(int r = 0; r < m_SecondRow;r++) {
			double xArray[] = new double[m_SecondRow];
			double yArray[] = new double[m_SecondRow];
			double zArray[] = new double[m_SecondRow];
			
			xArray[r] = Double.parseDouble(data[m_SecondC1][r]);
			yArray[r] = Double.parseDouble(data[m_SecondC2][r]);
			zArray[r] = Double.parseDouble(data[m_SecondC3][r]);
			
			double ad3[][] = {
					xArray, yArray, zArray
			};
			m_SecondDataset.addSeries("Series " + r, ad3);
		}
		return m_SecondDataset;
	}
    /**
     * creates a Bubble Chart using the XYZ data set 
     * and sets the appearance and plot orientation
     * 
     * @param dataset - passes the XYZ data set through
     * @return graph - returns the Area graph with selected title, x and y labels,
     * and data to be displayed
     */
    private JFreeChart CreateChart(XYZDataset dataset) {
    	JFreeChart chart = ChartFactory.createBubbleChart(
        		m_ChartTitle, 
        		m_XLabel, 
				m_YLabel, 
				dataset, 
				PlotOrientation.VERTICAL,
				false,
				true,
				false
		);
        
    	 m_Plot = chart.getXYPlot();
         if(!m_Setting.GetUseDefault()) {
         	NumberAxis domain = (NumberAxis) m_Plot.getDomainAxis();
             domain.setRange(m_XMin, m_XMax);
             domain.setVerticalTickLabels(true);
             
             NumberAxis range = (NumberAxis) m_Plot.getRangeAxis();
             range.setRange(m_YMin, m_YMax);
         }
         
         m_Renderer = m_Plot.getRenderer();
        return chart;    
    }
    /**
     * creates a second Bubble Chart using the XYZ data set 
     * and sets the appearance and plot orientation
     * 
     * @param dataset - passes the XYZ data set through
     * @return graph - returns the Area graph with selected title, x and y labels,
     * and data to be displayed
     */
    private JFreeChart CreateSecondChart(XYZDataset dataset) {
    	JFreeChart chart = ChartFactory.createBubbleChart(
        		m_SecondChartTitle, 
        		m_SecondXLabel, 
				m_SecondYLabel, 
				dataset, 
				PlotOrientation.VERTICAL,
				false,
				true,
				false
		);
        
    	 m_Plot = chart.getXYPlot();
         if(!m_SecondSetting.GetUseDefault()) {
         	NumberAxis domain = (NumberAxis) m_Plot.getDomainAxis();
             domain.setRange(m_XMin, m_XMax);
             domain.setVerticalTickLabels(true);
             
             NumberAxis range = (NumberAxis) m_Plot.getRangeAxis();
             range.setRange(m_YMin, m_YMax);
         }
         
         m_Renderer = m_Plot.getRenderer();
        return chart;    
    }
    /**
    *  visualises the Bubble chart using JFreeChart and sets the 
    * default size and appearance of the window and graph
    * 
    * @param m_Dataset - passes the XYZ data set through  
    * @return true - returns test results
    */
    private boolean ShowChart( XYZDataset dataset) {
    	 JFreeChart graph = CreateChart( dataset );
    	ChartPanel chartPanel = new ChartPanel(graph);
    	chartPanel.setPreferredSize( new java.awt.Dimension( CHART_WIDTH, CHART_HEIGHT ) );
    	JPanel colourButtonPanel = new JPanel(new BorderLayout());

    	m_ColChangeButton = new JButton( "Change Colours" );
    	colourButtonPanel.add( m_ColChangeButton,BorderLayout.SOUTH );
    	m_ColChangeButton.addActionListener( this );

    	JFrame BubbleChartTest = new JFrame();
    	BubbleChartTest.setLayout( new BorderLayout() );
    	BubbleChartTest.setSize( FRAME_WIDTH, FRAME_HEIGHT );
    	BubbleChartTest.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
    	BubbleChartTest.add( chartPanel, BorderLayout.NORTH );
    	BubbleChartTest.add(new InformationJPanel(m_Setting));
    	BubbleChartTest.add( colourButtonPanel,BorderLayout.SOUTH );
    	BubbleChartTest.setVisible( true );
        
    	return true;
    }
    /**
     *  visualises two Bubble charts using JFreeChart and sets the 
     * default size and appearance of the window and graph
     * 
     * @param m_Dataset - passes the XYZ data set through 
     * @param m_Dataset - passes the second XYZ data set through 
     * @return true - returns test results
     */
    private boolean ShowChart( XYZDataset dataset, XYZDataset secondDataset) {
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

    	JFrame BubbleChartTest = new JFrame();
    	BubbleChartTest.setLayout( new BorderLayout() );
    	BubbleChartTest.setSize( LARGE_FRAME_WIDTH, LARGE_FRAME_HEIGHT );
    	BubbleChartTest.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
    	BubbleChartTest.add(charts, BorderLayout.NORTH);
    	BubbleChartTest.add(new InformationJPanel(m_Setting));
    	BubbleChartTest.add( colourButtonPanel,BorderLayout.SOUTH );
    	BubbleChartTest.setVisible( true );
              
    	return true;
    }
    
    /**
    * main method to carry out JUnit Tests
    */
    public static void main( String[] args ) {
    
    	DataAttribute test = DataAttribute.GetTestDataAttribute();
    	BubbleChart bubbleTest = new BubbleChart(test);
    	DataAttribute secondTest = DataAttribute.GetTestDataAttribute();
    	BubbleChart secondBubbleTest = new BubbleChart(secondTest);
    	
    	Random gen = new Random();
    	
    	double x1 = gen.nextDouble() * VALUE_MULTIPLY + VALUE_ADD;
    	double x2 = gen.nextDouble() * VALUE_MULTIPLY + VALUE_ADD;
    	double x3 = gen.nextDouble() * VALUE_MULTIPLY + VALUE_ADD;
    	double[] xTest = {x1, x2, x3};    	
    	
    	double y1 = gen.nextDouble() * VALUE_MULTIPLY + VALUE_ADD + VALUE_ADD;
    	double y2 = gen.nextDouble() * VALUE_MULTIPLY + VALUE_ADD + VALUE_ADD;
    	double y3 = gen.nextDouble() * VALUE_MULTIPLY + VALUE_ADD + VALUE_ADD;
    	double[] yTest = {y1, y2, y3};
    	
    	double z1 = gen.nextDouble() * VALUE_MULTIPLY + VALUE_ADD + VALUE_ADD;
    	double z2 = gen.nextDouble() * VALUE_MULTIPLY + VALUE_ADD + VALUE_ADD;
    	double z3 = gen.nextDouble() * VALUE_MULTIPLY + VALUE_ADD + VALUE_ADD;
    	double[] zTest = {z1, z2, z3};
    	
    	double[][] xyzTest = {xTest, yTest, zTest};
    	double[][] xyzSecondTest = {xTest, yTest, zTest};
    	
    	DefaultXYZDataset dataset = new DefaultXYZDataset();
    	dataset.addSeries("testSeries", xyzTest);
    	bubbleTest.GetSetting().SetUseDefault(false);
    	bubbleTest.GetSetting().SetXAxisMin(MIN);
    	bubbleTest.GetSetting().SetXAxisMax(MAX);
    	bubbleTest.GetSetting().SetYAxisMin(MIN);
    	bubbleTest.GetSetting().SetXAxisMax(MAX);
    	
        
    	secondBubbleTest.ShowChart(dataset);
        
        DefaultXYZDataset secondDataset = new DefaultXYZDataset();
    	secondDataset.addSeries("SecondTestSeries", xyzSecondTest);
    	
    	secondBubbleTest.GetSetting().SetUseDefault(false);
    	secondBubbleTest.GetSetting().SetXAxisMin(MIN);
    	secondBubbleTest.GetSetting().SetXAxisMax(MAX);
    	secondBubbleTest.GetSetting().SetYAxisMin(MIN);
    	secondBubbleTest.GetSetting().SetXAxisMax(MAX);
        
        bubbleTest.ShowChart(dataset, secondDataset);
	    
    }
    
    /** renders the visual representation of the (x,y) items */
    private XYItemRenderer m_Renderer;
    
    /** displays a button that can select different colour options */
    private JButton m_ColChangeButton;
    
    /** plots data in the form (x,y) */
    private XYPlot m_Plot; 
    private XYPlot m_SecondPlot; 
    
    /** Create the datasets */
    private DefaultXYZDataset m_Dataset;
    private DefaultXYZDataset m_SecondDataset;
	
    /** Define bubble attributes */ 
    private final static double VALUE_MULTIPLY = 2;
    private final static double VALUE_ADD = 1;
    private final static double MAX = 20;
    private final static double MIN = 0;
    
    /** data used to create the visualisation */
    private String[][] m_Data;
    
    private DataAttribute m_Setting;
    
    /**Defining variables for the dataset */
    private int m_Col;
    private int m_Row;;
    private String m_ChartTitle;
    private String m_XLabel;
    private String m_YLabel;
    private int m_C1;
    private int m_C2;
    private int m_C3;
    private double m_XMin;
    private double m_XMax;
    private double m_YMin;
    private double m_YMax;
    private double m_XScale;
    private double m_YScale;
    
    private String[][] m_SecondData;
	private DataAttribute m_SecondSetting;
	private String m_SecondChartTitle;
	private String m_SecondXLabel;
	private String m_SecondYLabel; 
	private int m_SecondCol;
	private int m_SecondRow; 
	private int m_SecondC1;
	private int m_SecondC2;
	private int m_SecondC3;

    
    /** sets the frame width */
    private final int FRAME_HEIGHT = 570;
    private final int FRAME_WIDTH = 800;
    private final int CHART_WIDTH = 600;
    private final int CHART_HEIGHT = 400;
    private final int LARGE_FRAME_WIDTH = 1220;
    private final int LARGE_FRAME_HEIGHT = 600;    
}