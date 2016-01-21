/**
* @file   PieChart.java
* @author Connor McFadden A4, Leopold Stiegler A5, Bradley Coles-Perkins A6
* @class  PieChart
* @date   30/04/2013
*
* @brief A simple class that displays data in a single
*  Pie Chart, or two Pie Chart visualisations
*
*/

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;

import org.jfree.chart.*;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

public class PieChart extends JFrame implements Chart, ActionListener {
    
    /**
    * @param String[][] data - data used to create the visualisation.
    * @param Dataset dataset - the dataset used (rows and columns)
    * @param DataAttribute setting - Data attributes
    */
    public PieChart( String[][] data, 
    				 Dataset dataset, 
    				 DataAttribute setting) {
    	
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
        m_XMax = setting.GetYAxisMax();
        m_XScale = setting.GetXAxisScale();
        m_YScale = setting.GetYAxisScale();
	    
        CreateDataset( m_Data );
        ShowChart( m_dataset );
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
    public PieChart( String[][] data, Dataset dataset,
    		DataAttribute setting, String[][] secondData, Dataset secondDataset,
    		DataAttribute secondSetting) {

		m_Data = data;
		m_ChartTitle = setting.GetTitle();
		m_Setting = setting;
		m_XLabel = setting.GetAxisLabelX();
		m_YLabel = setting.GetAxisLabelY();
		m_Col = dataset.GetNoOfCols();
		m_Row = dataset.GetNoOfRows();
		m_C1 = setting.GetSelectedXIndex();
		m_C2 = setting.GetSelectedYIndex();
		
		m_SecondData = data;
		m_SecondChartTitle = setting.GetSecondTitle();
		m_SecondSetting = secondSetting;
		m_SecondXLabel = setting.GetSecondAxisLabelX();
		m_SecondYLabel = setting.GetSecondAxisLabelY();
		m_SecondCol = dataset.GetNoOfCols();
		m_SecondRow = dataset.GetNoOfRows();
		m_SecondC1 = setting.GetSecondSelectedXIndex();
		m_SecondC2 = setting.GetSecondSelectedYIndex();
		
		m_XMin = setting.GetXAxisMin();
		m_XMax = setting.GetYAxisMax();
		m_XScale = setting.GetXAxisScale();
		m_YScale = setting.GetYAxisScale();
		
		m_SecondXMin = setting.GetXAxisMin();
		m_SecondXMax = setting.GetYAxisMax();
		m_SecondXScale = setting.GetXAxisScale();
		m_SecondYScale = setting.GetYAxisScale();
		

        CreateDataset( m_Data );
        CreateSecondDataset(m_SecondData);
        ShowChart( m_dataset, m_secondDataset );		
	}
    /**
     * constructor for JUnit Tests
     * @param setting
     */
    public PieChart(DataAttribute setting) {
        
        m_Setting = setting;
        m_ChartTitle = setting.GetTitle();
        m_XLabel = setting.GetAxisLabelX();
        m_YLabel = setting.GetAxisLabelY();
        m_C1 = setting.GetSelectedXIndex();
        m_C2 = setting.GetSelectedYIndex();
        
        m_XMin = setting.GetXAxisMin();
        m_XMax = setting.GetYAxisMax();
        m_XScale = setting.GetXAxisScale();
        m_YScale = setting.GetYAxisScale();
    }
    
    /**
     * second empty constructor for JUnit Tests
     * @param setting
     * @param secondSetting
     */
    public PieChart(DataAttribute setting, DataAttribute secondSetting) {
        
        m_Setting = setting;
        m_ChartTitle = setting.GetTitle();
        m_XLabel = setting.GetAxisLabelX();
        m_YLabel = setting.GetAxisLabelY();
        m_C1 = setting.GetSelectedXIndex();
        m_C2 = setting.GetSelectedYIndex();
        
        m_XMin = setting.GetXAxisMin();
        m_XMax = setting.GetYAxisMax();
        m_XScale = setting.GetXAxisScale();
        m_YScale = setting.GetYAxisScale();
        
        m_SecondSetting = secondSetting;
        m_SecondChartTitle = secondSetting.GetSecondTitle();
        m_SecondXLabel = secondSetting.GetSecondAxisLabelX();
        m_SecondYLabel = secondSetting.GetSecondAxisLabelY();
        m_SecondC1 = secondSetting.GetSecondSelectedXIndex();
        m_SecondC2 = secondSetting.GetSecondSelectedYIndex();
        
        m_SecondXMin = secondSetting.GetXAxisMin();
        m_SecondXMax = secondSetting.GetYAxisMax();
        m_SecondXScale = secondSetting.GetXAxisScale();
        m_SecondYScale = secondSetting.GetYAxisScale();
    }
    
    /**
     * @return - return a variable which stores the data.
     */
    public String[][] GetData() {
        return m_Data;
    }
    /**
     * @return - return a variable with the current index 
     * of each pie slice.
     */
    public int GetIndexCount() {
        return m_IndexCount;
    }
    /**
     * @return - a variable which returns the current number of columns
     */
    public int GetNoCols() {
        return m_Col;
    }
    /**
     * @return - a variable which returns the current number of rows
     */
    public int GetNoRows() {
        return m_Row;
    }
    /**
     * @param xLabel - the xLabel to be passed from the users to the chart
     */
    @Override
    public String SetLabelX( String xLabel ) {
        boolean test = true;
        
        if ( ( ( xLabel.isEmpty() )||( xLabel.length() > MAX_LABEL_LENGTH ) ) 
			   && ( test == true ) ) {
            System.err.println( "ScatterPlotGraph::SetLabelX " + xLabel );
	}
        
        return xLabel;
    }
    /**
     * @param data - variable to test if m_IntData has any errors
     * @return - returns boolean if test is true
     */
    public boolean SetData( String[][] data ) {
        boolean test = true;
        if ( data == null && test == true ) {
            System.err.println( "PieChart::setData() ***Warning, data set to: " 
                    + data );
        }
        else if ( test == true ) {
            System.out.println( "PieChart::setData() file location set to: "
                    + data );
        }
            m_Data = data;
            return true;
    }
    /**
     * @param index - variable to test if m_IndexCount has any errors
     * @return - returns boolean if test is true
     */
    public boolean SetIndexCount( int index ) {
        boolean test = true;
        if ( index < 0 && test == true ) {
            System.err.println( "PieChart::setIndexCount() ***Warning, index "
                        + "set to: " + index );
        }
        else if ( test == true ) {
            System.out.println( "PieChart::setIndexCount() index set to: " 
                    + index );
        }
        m_IndexCount = index;
        return true;
    }
    /**
     * @param cols - variable to test if m_NoOfCols has any errors
     * @return - returns boolean if test is true
     */
    public boolean SetNoCols( int cols ) {
        boolean test = true;
        if ( cols < 0 && test == true ) {
            System.err.println( "PieChart::setNoCols() ***Warning, cols "
                        + "set to: " + cols );
        }
        else if ( test == true ) {
            System.out.println( "PieChart::setNoCols() cols set to: " 
                    + cols );
        }
        m_Col = cols;
        return true;
    }
    /**
     * @param rows - variable to test if m_NoOfRows has any errors
     * @return - returns boolean if test is true
     */
    public boolean SetNoRows( int rows ) {
        boolean test = true;
        if ( rows < 0 && test == true ) {
            System.err.println( "PieChart::setNoRows() ***Warning, rows "
                        + "set to: " + rows );
        }
        else if ( test == true ) {
            System.out.println( "PieChart::setNoRows() rows set to: " 
                    + rows );
        }
        m_Row = rows;
        return true;
    }
    /**
     * @param m_chartTitle - the title to be passed from users for the chart
     */

    public String SetTitle( String m_chartTitle ) {
        boolean test = true;
        
        if ( ( ( m_chartTitle.isEmpty() )||( m_chartTitle.length() > MAX_CHART_TITLE_LENGTH ) )
			   && ( test == true ) ) {
            System.err.println( "ScatterPlotGraph::SetTitle " + m_chartTitle );
        }
        
        return m_chartTitle;
    }
    /**
     * @param e - action button to allow choosers to select a colour
     */
    @Override
    public void actionPerformed( ActionEvent e ) {
        if( e.getSource() == colChangeButton ) {
            ColourMap cM = new ColourMap();
            cM.SetupData( m_IndexCount,plotChart );
            cM.setVisible( false );
        }
    }
    
    /**
     * create the 3D pie chart and set the appearance 
     * @param dataset - pass the pie dataset through
     * @param chartTitle - pass through the chart title
     * @return - a 3D piechart
     */
    public JFreeChart CreateChart( PieDataset dataset ) {
        
        JFreeChart chart = ChartFactory.createPieChart3D( 
        		m_ChartTitle,
        	m_dataset, true,                          
            true, false );
        
        //xyPlot = chart.getXYPlot();
        plotChart  = ( PiePlot3D ) chart.getPlot();      
        plotChart.setStartAngle( START_ANGLE );
        plotChart.setDirection( Rotation.CLOCKWISE ); 
        plotChart.setForegroundAlpha(TRANSPARENCY); 
        return chart;       
    }
    /**
     * creates the second 3D pie chart and set the appearance 
     * @param dataset - pass the pie dataset through
     * @param seconChartTitle - pass through the second chart title
     * @return - a 3D piechart
     */
    public JFreeChart CreateSecondChart( PieDataset dataset ) {
        JFreeChart chart = ChartFactory.createPieChart3D( 
        		m_SecondChartTitle,
        	m_secondDataset, true,                          
            true, false );
        
        //xyPlot = chart.getXYPlot();
        plotChart  = ( PiePlot3D ) chart.getPlot();      
        plotChart.setStartAngle( START_ANGLE );
        plotChart.setDirection( Rotation.CLOCKWISE ); 
        plotChart.setForegroundAlpha(TRANSPARENCY); 
        return chart;       
    }
    /**
     * create a dataset which will store data chosen by the user
     * @return - data ready to be displayed
     */
    private PieDataset CreateDataset(String[][] data) {
    	m_dataset = new DefaultPieDataset();       
        /* loop through the rows and store them in m_IntData */
        
        for( int r = 0; r < m_Row; r++ ) {
            double value1 = Double.parseDouble(data[m_C1][r]);
            double value2 = Double.parseDouble(data[m_C2][r]);
           // double m_pieSlice = (m_dataInput/m_Row)*TOTAL_PERCENTAGE;
            m_dataset.setValue(value1+"",value2);		
        }      
        
        m_IndexCount = m_dataset.getItemCount();
        return m_dataset;     
    }
    /**
     * create a second dataset which will store data chosen by the user
     * @return - second data ready to be displayed
     */
    private PieDataset CreateSecondDataset(String[][] data) {
    	m_secondDataset = new DefaultPieDataset();       
        /* loop through the rows and store them in m_IntData */
        
        for( int r = 0; r < m_SecondRow; r++ ) {
            double value1 = Double.parseDouble(data[m_SecondC1][r]);
            double value2 = Double.parseDouble(data[m_SecondC2][r]);
           // double m_pieSlice = (m_dataInput/m_Row)*TOTAL_PERCENTAGE;
            m_secondDataset.setValue(value1+"",value2);		
        }      
        
        m_IndexCount = m_secondDataset.getItemCount();
        return m_secondDataset;     
    }
    /**
     *  visualises the Pie chart using JFreeChart and sets the 
     * default size and appearance of the window and graph
     * 
     * @param datas - passes the Pie data through 
     * @return true - returns test results
     */
    public Boolean ShowChart(PieDataset data) {
    	JFreeChart chart = CreateChart(data);         
		ChartPanel chartPanel = new ChartPanel( chart );           
			    
		/* instantiate colour options */
		JPanel colourButtonPanel = new JPanel( new BorderLayout() );
		colChangeButton = new JButton( "Change Colours" );
		colourButtonPanel.add( colChangeButton,BorderLayout.SOUTH );
        colChangeButton.addActionListener( PieChart.this );
	    
        JFrame test = new JFrame();
        test.setLayout(new BorderLayout());
        test.setSize( FRAME_WIDTH, FRAME_HEIGHT );
        test.setTitle("Pie Chart");
        test.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        test.add(chartPanel, BorderLayout.NORTH);
        test.add(new InformationJPanel(m_Setting), BorderLayout.CENTER);
        test.add(colourButtonPanel, BorderLayout.SOUTH);
        test.setVisible(true);
        
        return true;
    }
    /**
     *  visualises the Pie chart using JFreeChart and sets the 
     * default size and appearance of the window and graph
     * 
     * @param data - passes the Pie data through 
     * @param secondData - passes the second Pie data through 
     * @return true - returns test results
     */
    public Boolean ShowChart(PieDataset data, PieDataset secondData) {
    	JFreeChart chart = CreateChart(data);
    	JFreeChart secondChart = CreateSecondChart(data);
		ChartPanel chartPanel = new ChartPanel( chart );           
		ChartPanel secondChartPanel = new ChartPanel( secondChart );  
    	chartPanel.setPreferredSize( new java.awt.Dimension( CHART_WIDTH, CHART_HEIGHT ) );
    	secondChartPanel.setPreferredSize( new java.awt.Dimension( CHART_WIDTH, CHART_HEIGHT ) );
			    
		/* instantiate colour options */
		JPanel colourButtonPanel = new JPanel( new BorderLayout() );
		colChangeButton = new JButton( "Change Colours" );
		colourButtonPanel.add( colChangeButton,BorderLayout.SOUTH );
        colChangeButton.addActionListener( PieChart.this );
	    
        JPanel charts = new JPanel();
     	charts.add( chartPanel, BorderLayout.WEST );
     	charts.add( secondChartPanel, BorderLayout.EAST );
        
        JFrame test = new JFrame();
        test.setLayout(new BorderLayout());
        test.setSize( LARGE_FRAME_WIDTH, LARGE_FRAME_HEIGHT );
        test.setTitle("Pie Chart");
        test.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        test.add(charts, BorderLayout.NORTH);
        test.add(new InformationJPanel(m_Setting), BorderLayout.CENTER);
        test.add(colourButtonPanel, BorderLayout.SOUTH);
        test.setVisible(true);
        
        return true;
    }
    /**
     * Method for creating a test dataset 
     * @return dataset
     */
    private static PieDataset createTestDataset() {	
    	DefaultPieDataset testDataset = new DefaultPieDataset();  
    	Random generator = new Random();
    	
    	for(int i = 0;i<LOOP;i++) {
    		double temp1 = generator.nextInt(LIMIT);
    		double temp2 = generator.nextInt(LIMIT);
    		
    		testDataset.setValue(temp1+"",temp2);
    	}
        
        m_TestIndexCount = testDataset.getItemCount();
        return testDataset;
    }
     
    /**
     * Main method which implement 3 unit tests for each 'set' method
     * @param args - no user input needed
     */
    public static void main( String[] args ){
        DataAttribute test = DataAttribute.GetTestDataAttribute();
        PieChart pieChartTest = new PieChart(test);
    	
        final PieDataset datasetTest = createTestDataset();
    	pieChartTest.ShowChart(datasetTest);
    	
    	DataAttribute secondTest = DataAttribute.GetTestDataAttribute();
    	PieChart secondPieChartTest = new PieChart(secondTest);
    	
        final PieDataset secondDatasetTest = createTestDataset();
    	secondPieChartTest.ShowChart(datasetTest, secondDatasetTest);    	
    }
    /** declare variables */
    private static final int LIMIT = 30;
    private static final int LOOP = 10;  
    private String[][] m_Data;
    private String[][] m_SecondData;
    /** the angle of the pie chart */
    private final int START_ANGLE = 290;
    /** the transparency of the pie chart */
    private final float TRANSPARENCY = 0.6f;
    /** the button for colour use */
    private JButton colChangeButton; 
    private PiePlot3D plotChart; 
    /** IndexCount declared for each part of the visualiser */
    private int m_IndexCount = 0;
    private static int m_TestIndexCount = 0;
    private DataAttribute m_Setting;
    private DataAttribute m_SecondSetting;
    private int m_Col;
    private int m_Row;
    private int m_SecondCol;
    private int m_SecondRow;
    private String m_ChartTitle;
    private String m_SecondChartTitle;
    private String m_XLabel;
    private String m_YLabel;
    private String m_SecondXLabel;
    private String m_SecondYLabel;
    private int m_C1;
    private int m_C2;
    private int m_SecondC1;
    private int m_SecondC2;
    private double m_XMin;
    private double m_XMax;
    private double m_YMin;
    private double m_YMax;
    private double m_SecondXMin;
    private double m_SecondXMax;
    private double m_SecondYMin;
    private double m_SecondYMax;
    private double m_XScale;
    private double m_YScale;
    private double m_SecondXScale;
    private double m_SecondYScale;
    /**  Create the dataset instances */
    private DefaultPieDataset m_dataset;
    private DefaultPieDataset m_secondDataset;
    
    /** Create the frame sizes */
    private final int FRAME_HEIGHT = 570;
    private final int FRAME_WIDTH = 800;
    private final int CHART_HEIGHT = 400;
    private final int CHART_WIDTH = 600;
    private final int LARGE_FRAME_WIDTH = 1220;
    private final int LARGE_FRAME_HEIGHT = 600;
    
    private final int MAX_LABEL_LENGTH = 25;
    private final int MAX_CHART_TITLE_LENGTH = 35;
    
}