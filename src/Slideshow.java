/**
* @file    Slideshow.java
* @author  Bradley Coles-Perkins A6
* @class   Slideshow
* @date    30/04/13
* @brief A class that creates a slideshow  of a single Chart or two Chart visualizations
* \ see Graph.java
* 
* This class is responsible for making the slideshow
* It was made using JFreeChart and following examples
* on how to set up the charts
* It uses the Graph.java to return a JFreeChart
*/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYZDataset;
import org.jfree.util.Rotation;
import org.jfree.data.xy.XYSeriesCollection;

import sun.misc.Regexp;

public class Slideshow extends JFrame {
	private static Boolean showSlideshow;

	/**
	    * creates a constructor taking in the following parameters:
	    * 
	    * @param String[][] data - data used to create the visualisation.
	    * @param Dataset dataset - the dataset used (rows and columns)
	    * @param DataAttribute setting - Data attributes
	    */
	public Slideshow( String[][] data, 
			Dataset dataset, 
			DataAttribute setting ) {
        m_XYData = data;
        m_PieData = data;
        m_ScatterData = data; 
        m_AreaData = data; 
        m_PolarData =data;
        m_LineData = data;
        m_BubbleData = data;
        
        m_Setting = setting;
        m_ChartTitle = setting.GetTitle();
        m_XLabel = setting.GetAxisLabelX();
        m_YLabel = setting.GetAxisLabelY();
        m_Row = dataset.GetNoOfRows();
        m_C1 = setting.GetSelectedXIndex();
        m_C2 = setting.GetSelectedYIndex();
        m_C3 = setting.GetSelectedZIndex();
        
        m_XMin = setting.GetXAxisMin();
        m_XMax = setting.GetYAxisMax();
        m_XScale = setting.GetXAxisScale();
        m_YScale = setting.GetYAxisScale();
        
        timerOption = new JFrame("Enter a timer");
        while (m_OptionPopup = true){
        	String userOption = JOptionPane.showInputDialog(timerOption, "Please enter a timer"
        			+ "delay in seconds:");
        	  if (userOption.matches("[0-9]+")){
        		  System.out.println("Matches");
        		  m_Timer = Integer.parseInt(userOption);
        		  m_Timer = m_Timer * m_Millisecond;
        		  m_OptionPopup = false;
        		  break;
        	  }
        	  System.out.println("Not entered match");
        } 
        
        CreateXYDataset(m_XYData);
        CreatePieChartDataset(m_PieData);
        CreateBubbleChartDataset(m_BubbleData);
        ShowSlideshow( m_XYDataset, m_PieChartDataset, m_XYDataset,
        		m_XYDataset, m_XYDataset, m_XYDataset, m_BubbleDataset);
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
	public Slideshow( String[][] data, Dataset dataset,
    		DataAttribute setting, String[][] secondData, Dataset secondDataset,
    		DataAttribute secondSetting) {

		  m_XYData = data;
	      m_PieData = data;
	      m_ScatterData = data; 
	      m_AreaData = data; 
	      m_PolarData =data;
	      m_LineData = data;
	      m_BubbleData = data;
	      m_SecondXYData = data;
	      m_SecondPieData = data;
	      m_SecondScatterData = data; 
	      m_SecondAreaData = data; 
	      m_SecondPolarData =data;
	      m_SecondLineData = data;
	      m_SecondBubbleData = data;
	      
          m_Setting = setting;
	      m_ChartTitle = setting.GetTitle();
	      m_XLabel = setting.GetAxisLabelX();
	      m_YLabel = setting.GetAxisLabelY();
	      m_Row = dataset.GetNoOfRows();
	      m_C1 = setting.GetSelectedXIndex();
	      m_C2 = setting.GetSelectedYIndex();
	      m_C3 = setting.GetSelectedZIndex();
	      m_SecondSetting = secondSetting;
	      m_SecondChartTitle = secondSetting.GetSecondTitle();
	      m_SecondXLabel = secondSetting.GetSecondAxisLabelX();
	      m_SecondYLabel = secondSetting.GetSecondAxisLabelY();
	      m_SecondRow = secondDataset.GetNoOfRows();
	      m_SecondC1 = secondSetting.GetSecondSelectedXIndex();
	      m_SecondC2 = secondSetting.GetSecondSelectedYIndex();
	      m_SecondC3 = secondSetting.GetSecondSelectedZIndex();		
	       
	      m_XMin = setting.GetXAxisMin();
	      m_XMax = setting.GetYAxisMax();
	      m_XScale = setting.GetXAxisScale();
	      m_YScale = setting.GetYAxisScale();
	       
	      timerOption  = new JFrame("Enter a timer");
	        while (m_OptionPopup = true){
	        	String userOption = JOptionPane.showInputDialog(timerOption, "Please enter a timer"
	        			+ "delay in seconds:");
	        	  if (userOption.matches("[0-9]+")){
	        		  System.out.println("Matches");
	        		  m_Timer = Integer.parseInt(userOption);
	        		  m_Timer = m_Timer * m_Millisecond;
	        		  m_OptionPopup = false;
	        		  break;
	        	  }
	        	  System.out.println("Not entered match");
	        } 
	      
	      CreateXYDataset(m_XYData);
	      CreateSecondXYDataset(m_SecondXYData);
	      CreatePieChartDataset(m_PieData);
	      CreateSecondPieChartDataset(m_SecondPieData);
	      CreateBubbleChartDataset(m_BubbleData);
	      CreateSecondBubbleChartDataset(m_SecondBubbleData);
	      ShowSecondSlideshow(  m_XYDataset, m_PieChartDataset, m_XYDataset,
	        	m_XYDataset, m_XYDataset, m_XYDataset, m_BubbleDataset,
	        	m_SecondXYDataset, m_SecondPieChartDataset,
	      		m_SecondXYDataset,m_SecondXYDataset, m_SecondXYDataset, 
	      		m_SecondXYDataset, m_SecondBubbleDataset);

    }
	/**
	 * creates an XY data set for the two selected columns
	 * 
	 * @param m_data - pass the 2d array through
	 * @return dataset - returns the new data set with new values
	 */
	private XYDataset CreateXYDataset( String[][] data ) {
        m_XYDataset = new XYSeriesCollection();
        XYSeries dataset = new XYSeries( "Series" ); 
        int r = 0;
        for(r = 0; r < m_Row; r++ ) {
        	dataset.add( Double.parseDouble( data[m_C1][r] ), 
                      	Double.parseDouble( data[m_C2][r] ));
        	m_IndexCount++;
        }
        m_XYDataset.addSeries( dataset ); 
        return m_XYDataset;
    }
	/**
	 * creates a Second XY data set for the two selected columns
	 * 
	 * @param m_data - pass the 2d array through
	 * @return dataset - returns the new data set with new values
	 */
	private XYDataset CreateSecondXYDataset( String[][] data ) {
        m_SecondXYDataset = new XYSeriesCollection();
        XYSeries dataset = new XYSeries( "Series" ); 
        int r = 0;
        for(r = 0; r < m_SecondRow; r++ ) {
        	dataset.add( Double.parseDouble( data[m_SecondC1][r] ), 
                      	Double.parseDouble( data[m_SecondC2][r] ));
        	m_IndexCount++;
        }
        m_SecondXYDataset.addSeries( dataset ); 
        return m_SecondXYDataset;
    }
	/**
	    * creates a Pie data set for the two selected columns
	    * 
	    * @param m_data - pass the 2d array through
	    * @return dataset - returns the new data set with new values
	    */
	private PieDataset CreatePieChartDataset(String[][] data) {
	    m_PieChartDataset = new DefaultPieDataset();       
	        for( int r = 0; r < m_Row; r++ ) {
	            double value1 = Double.parseDouble(data[m_C1][r]);
	            double value2 = Double.parseDouble(data[m_C2][r]);
	            m_PieChartDataset.setValue(value1+"",value2);		
	        }      
	        m_IndexCount = m_PieChartDataset.getItemCount();
	        return m_PieChartDataset;     
	}
	/**
	    * creates a second Pie data set for the two selected columns
	    * 
	    * @param m_data - pass the 2d array through
	    * @return dataset - returns the new data set with new values
	    */
	 private PieDataset CreateSecondPieChartDataset(String[][] data) {
	    	m_SecondPieChartDataset = new DefaultPieDataset();       
	        for( int r = 0; r < m_SecondRow; r++ ) {
	            double value1 = Double.parseDouble(data[m_SecondC1][r]);
	            double value2 = Double.parseDouble(data[m_SecondC2][r]);
	            m_SecondPieChartDataset.setValue(value1+"",value2);		
	        }   
	        m_IndexCount = m_SecondPieChartDataset.getItemCount();
	        return m_SecondPieChartDataset;     
	    }
	 /**
	    * creates an XYZ data set for the two selected columns
	    * 
	    * @param m_data - pass the 2d array through
	    * @return dataset - returns the new data set with new values
	    */
	 public XYZDataset CreateBubbleChartDataset(String[][] data) {
	    	m_BubbleDataset = new DefaultXYZDataset();
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
				m_BubbleDataset.addSeries("Series " + r, ad3);
			}
			return m_BubbleDataset;
		}
	 /**
	    * creates a second XYZ data set for the two selected columns
	    * 
	    * @param m_data - pass the 2d array through
	    * @return dataset - returns the new data set with new values
	    */
	 public XYZDataset CreateSecondBubbleChartDataset(String[][] data) {
	    	m_SecondBubbleDataset = new DefaultXYZDataset();
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
				m_SecondBubbleDataset.addSeries("Series " + r, ad3);
			}
			return m_SecondBubbleDataset;
		}
	 /**
	  	* creates a Column Chart using the XY data set 
	  	* and sets the appearance and plot orientation
		 * @param XYSeriesCollection dataset - the dataset used to make the chart
		 * @return JFreeChart chart - the chart
		 */
	private JFreeChart CreateColumnChart(XYSeriesCollection dataset) {	
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
  	 * creates a second Column Chart using the XY data set 
  	 * and sets the appearance and plot orientation
	 * @param XYSeriesCollection dataset - the dataset used to make the chart
	 * @return JFreeChart chart - the chart
	 */
	private JFreeChart CreateSecondColumnChart(XYSeriesCollection dataset) {	
    	final JFreeChart chart = ChartFactory.createXYBarChart(
				m_SecondChartTitle, //Chart Title
				m_SecondXLabel, //X Axis Label
				false,  //Legend
				m_SecondYLabel, // Y Axis Label
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
        return chart;    
    }
	 /**
  	* creates a Pie Chart using the Pie data set 
  	* and sets the appearance and plot orientation
	 * @param PieDataset dataset - the dataset used to make the chart
	 * @return JFreeChart chart - the chart
	 */
	public JFreeChart CreatePieChart( PieDataset dataset ) {
        
        JFreeChart chart = ChartFactory.createPieChart3D( 
        		m_ChartTitle,
        	dataset, true,                          
            true, false );
        
        //xyPlot = chart.getXYPlot();
        plotChart  = ( PiePlot3D ) chart.getPlot();      
        plotChart.setStartAngle( START_ANGLE );
        plotChart.setDirection( Rotation.CLOCKWISE ); 
        plotChart.setForegroundAlpha(TRANSPARENCY); 
        return chart;       
    }
	 /**
  	* creates a second Pie Chart using the Pie data set 
  	* and sets the appearance and plot orientation
	 * @param PieDataset dataset - the dataset used to make the chart
	 * @return JFreeChart chart - the chart
	 */
	public JFreeChart CreateSecondPieChart( PieDataset dataset ) {
        
        JFreeChart chart = ChartFactory.createPieChart3D( 
        		m_SecondChartTitle,
        	dataset, true,                          
            true, false );
        
        //xyPlot = chart.getXYPlot();
        plotSecondChart  = ( PiePlot3D ) chart.getPlot();      
        plotSecondChart.setStartAngle( START_ANGLE );
        plotSecondChart.setDirection( Rotation.CLOCKWISE ); 
        plotSecondChart.setForegroundAlpha(TRANSPARENCY); 
        return chart;       
    }
	 /**
  	* creates a Scatter Chart using the XY data set 
  	* and sets the appearance and plot orientation
	 * @param XYDataset dataset - the dataset used to make the chart
	 * @return JFreeChart chart - the chart
	 */
	private JFreeChart CreateScatterGraph( XYDataset dataset ) {
        JFreeChart graph = ChartFactory.createScatterPlot(
            m_ChartTitle,			
            m_XLabel,          
            m_YLabel,          
            dataset,                  	
            PlotOrientation.VERTICAL,
            false,
            true,
            false);
        
        m_Plot = graph.getXYPlot();
        if(!m_Setting.GetUseDefault()) {
        	NumberAxis domain = (NumberAxis) m_Plot.getDomainAxis();
            domain.setRange(m_XMin, m_XMax);
            domain.setTickUnit(new NumberTickUnit(m_XScale));
            domain.setVerticalTickLabels(true);
            
            NumberAxis range = (NumberAxis) m_Plot.getRangeAxis();
            range.setRange(m_YMin, m_YMax);
        }
        m_Renderer = m_Plot.getRenderer();
        return graph;
    }
	 /**
  	* creates a second Scatter Chart using the XY data set 
  	* and sets the appearance and plot orientation
	 * @param XYDataset dataset - the dataset used to make the chart
	 * @return JFreeChart chart - the chart
	 */
	private JFreeChart CreateSecondScatterGraph( XYDataset dataset ) {
        JFreeChart graph = ChartFactory.createScatterPlot(
            m_SecondChartTitle,			
            m_SecondXLabel,          
            m_SecondYLabel,          
            dataset,                  	
            PlotOrientation.VERTICAL,
            false,
            true,
            false);
        
        m_SecondPlot = graph.getXYPlot();
        if(!m_SecondSetting.GetUseDefault()) {
        	NumberAxis domain = (NumberAxis) m_SecondPlot.getDomainAxis();
            domain.setRange(m_XMin, m_XMax);
            domain.setTickUnit(new NumberTickUnit(m_XScale));
            domain.setVerticalTickLabels(true);
            
            NumberAxis range = (NumberAxis) m_SecondPlot.getRangeAxis();
            range.setRange(m_YMin, m_YMax);
        }
        m_SecondRenderer = m_SecondPlot.getRenderer();
        return graph;
    }
	 /**
  	* creates a Line Chart using the XY data set 
  	* and sets the appearance and plot orientation
	 * @param XYDataset dataset - the dataset used to make the chart
	 * @return JFreeChart chart - the chart
	 */
	private JFreeChart CreateLineGraph( XYDataset dataset ) {
        JFreeChart chart = ChartFactory.createXYLineChart(
            m_ChartTitle,			
            m_XLabel,          
            m_YLabel,          
            dataset,                  		
            PlotOrientation.VERTICAL,
            true,
            true,
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
  	* creates a Second Line Chart using the XY data set 
  	* and sets the appearance and plot orientation
	 * @param XYDataset dataset - the dataset used to make the chart
	 * @return JFreeChart chart - the chart
	 */
	private JFreeChart CreateSecondLineGraph( XYDataset dataset ) {
        JFreeChart chart = ChartFactory.createXYLineChart(
            m_SecondChartTitle,			
            m_SecondXLabel,          
            m_SecondYLabel,          
            dataset,                  		
            PlotOrientation.VERTICAL,
            true,
            true,
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
        m_SecondPlot = chart.getXYPlot();
        m_SecondRenderer = m_SecondPlot.getRenderer();
        return chart;
    }
	 /**
  	* creates a Polar Chart using the XY data set 
  	* and sets the appearance and plot orientation
	 * @param XYDataset dataset - the dataset used to make the chart
	 * @return JFreeChart chart - the chart
	 */
	private JFreeChart CreatePolarGraph( XYDataset dataset ) {
		JFreeChart chart = ChartFactory.createPolarChart(
				m_ChartTitle,
				dataset, 
				false, 
				false,
				false);
	    
		chart.setBackgroundPaint(Color.white);
		
		return chart;
	}
	 /**
  	* creates a second Polar Chart using the XY data set 
  	* and sets the appearance and plot orientation
	 * @param XYDataset dataset - the dataset used to make the chart
	 * @return JFreeChart chart - the chart
	 */
	private JFreeChart CreateSecondPolarGraph( XYDataset dataset ) {
		JFreeChart chart = ChartFactory.createPolarChart(
				m_SecondChartTitle,
				dataset, 
				false, 
				false,
				false);
	    
		chart.setBackgroundPaint(Color.white);
		
		return chart;
	}
	 /**
  	* creates an Area Chart using the XY data set 
  	* and sets the appearance and plot orientation
	 * @param XYDataset dataset - the dataset used to make the chart
	 * @return JFreeChart chart - the chart
	 */
	 private JFreeChart CreateAreaGraph( XYDataset dataset ) {
	        JFreeChart chart = ChartFactory.createXYAreaChart(
	            m_ChartTitle,			
	            m_XLabel,          
	            m_YLabel,          
	            dataset,                  		
	            PlotOrientation.VERTICAL,
	            true,
	            true,
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
	  	* creates a second Area Chart using the XY data set 
	  	* and sets the appearance and plot orientation
		 * @param XYDataset dataset - the dataset used to make the chart
		 * @return JFreeChart chart - the chart
		 */
	 private JFreeChart CreateSecondAreaGraph( XYDataset dataset ) {
	        JFreeChart chart = ChartFactory.createXYAreaChart(
	            m_SecondChartTitle,			
	            m_SecondXLabel,          
	            m_SecondYLabel,          
	            dataset,                  		
	            PlotOrientation.VERTICAL,
	            true,
	            true,
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
	        m_SecondPlot = chart.getXYPlot();
	        m_SecondRenderer = m_SecondPlot.getRenderer();       
	        return chart;
	    }
	 /**
	  	* creates a bubble Chart using the XYZ data set 
	  	* and sets the appearance and plot orientation
		 * @param XYZDataset dataset - the dataset used to make the chart
		 * @return JFreeChart chart - the chart
		 */
	 private JFreeChart CreateBubbleChart( XYZDataset dataset) {
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
		 * @param XYZDataset dataset - the dataset used to make the chart
		 * @return JFreeChart chart - the chart
		 */
	 private JFreeChart CreateSecondBubbleChart( XYZDataset dataset) {
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
	        m_SecondPlot = chart.getXYPlot();
	        if(!m_SecondSetting.GetUseDefault()) {
	        	NumberAxis domain = (NumberAxis) m_SecondPlot.getDomainAxis();
	            domain.setRange(m_XMin, m_XMax);
	            domain.setVerticalTickLabels(true);
	            
	            NumberAxis range = (NumberAxis) m_SecondPlot.getRangeAxis();
	            range.setRange(m_YMin, m_YMax);
	        }
	        m_Renderer = m_SecondPlot.getRenderer();
	    	return chart;
	    }
 
	 /**
	  * Creates the chart panels of the single charts, and sets the size of
	  * the panel, ready to be added to the slideshow JFrame
	  * 
	  * @param columnDataset
	  * @param pieDataset
	  * @param scatterDataset
	  * @param lineDataset
	  * @param polarDataset
	  * @param areaDataset
	  * @param bubbleDataset
	  * @return
	  */
	private Boolean ShowSlideshow(XYSeriesCollection columnDataset, PieDataset pieDataset, 
			XYSeriesCollection scatterDataset, XYSeriesCollection lineDataset,
			XYSeriesCollection polarDataset, XYSeriesCollection areaDataset,
			DefaultXYZDataset bubbleDataset) {
		JFreeChart columnGraph = CreateColumnChart( columnDataset );
		chartPanel = new ChartPanel( columnGraph );
    	chartPanel.setPreferredSize( new java.awt.Dimension( CHART_WIDTH, CHART_HEIGHT ) );
    	
    	JFreeChart pieGraph = CreateSecondPieChart( pieDataset );
		chartPanel2 = new ChartPanel( pieGraph );
    	chartPanel2.setPreferredSize( new java.awt.Dimension( CHART_WIDTH, CHART_HEIGHT ) );
    	
    	JFreeChart scatterGraph = CreateScatterGraph( scatterDataset );
		chartPanel3 = new ChartPanel( scatterGraph );
    	chartPanel3.setPreferredSize( new java.awt.Dimension( CHART_WIDTH, CHART_HEIGHT ) );
    	
    	JFreeChart lineGraph = CreateLineGraph( lineDataset );
		chartPanel4 = new ChartPanel( lineGraph );
    	chartPanel4.setPreferredSize( new java.awt.Dimension( CHART_WIDTH, CHART_HEIGHT ) );
    	
    	JFreeChart polarChart = CreatePolarGraph( polarDataset );
		chartPanel5 = new ChartPanel( polarChart );
    	chartPanel5.setPreferredSize( new java.awt.Dimension( CHART_WIDTH, CHART_HEIGHT ) );
    	
    	JFreeChart areaChart = CreateAreaGraph( areaDataset );
		chartPanel6 = new ChartPanel( areaChart );
    	chartPanel6.setPreferredSize( new java.awt.Dimension( CHART_WIDTH, CHART_HEIGHT ) );
    	
    	JFreeChart bubbleChart = CreateBubbleChart( bubbleDataset );
		chartPanel7 = new ChartPanel( bubbleChart );
    	chartPanel7.setPreferredSize( new java.awt.Dimension( CHART_WIDTH, CHART_HEIGHT ) );
    	slideshow startSlideshow = new slideshow();
    	startSlideshow.start();
		return true;
	}
	/**
	 * Creates the chart panels of the two charts, and sets the size of
	  * the panel, ready to be added to the slideshow JFrame
	  * 
	 * @param columnDataset
	 * @param pieDataset
	 * @param scatterDataset
	 * @param lineDataset
	 * @param polarDataset
	 * @param areaDataset
	 * @param bubbleDataset
	 * @param secondColumnDataset
	 * @param secondPieDataset
	 * @param secondScatterDataset
	 * @param secondLineDataset
	 * @param secondPolarDataset
	 * @param secondAreaDataset
	 * @param secondBubbleDataset
	 * @return
	 */
	private Boolean ShowSecondSlideshow( XYSeriesCollection columnDataset, PieDataset pieDataset, 
			XYSeriesCollection scatterDataset, XYSeriesCollection lineDataset,
			XYSeriesCollection polarDataset, XYSeriesCollection areaDataset,
			DefaultXYZDataset bubbleDataset, XYSeriesCollection secondColumnDataset, 
			PieDataset secondPieDataset, XYSeriesCollection secondScatterDataset, 
			XYSeriesCollection secondLineDataset,XYSeriesCollection secondPolarDataset, 
			XYSeriesCollection secondAreaDataset, DefaultXYZDataset secondBubbleDataset) {
			
			JFreeChart columnGraph = CreateColumnChart( columnDataset );
			JFreeChart secondColumnGraph = CreateSecondColumnChart( secondColumnDataset );
			firstChartPanel = new ChartPanel( columnGraph );
			secondChartPanel = new ChartPanel( secondColumnGraph );
	    	firstChartPanel.setPreferredSize( new java.awt.Dimension( CHART_WIDTH, CHART_HEIGHT ) );
	    	secondChartPanel.setPreferredSize( new java.awt.Dimension( CHART_WIDTH, CHART_HEIGHT ) );
	    	
	    	charts  = new JPanel();
	     	charts.add( firstChartPanel, BorderLayout.WEST );
	     	charts.add( secondChartPanel, BorderLayout.EAST );
		
	     	JFreeChart pieGraph = CreatePieChart( pieDataset );
	     	JFreeChart secondPieGraph = CreateSecondPieChart( secondPieDataset );
			chartPanel2 = new ChartPanel( pieGraph );
			secondChartPanel2 = new ChartPanel( secondPieGraph );
	    	chartPanel2.setPreferredSize( new java.awt.Dimension( CHART_WIDTH, CHART_HEIGHT ) );
	    	secondChartPanel2.setPreferredSize( new java.awt.Dimension( CHART_WIDTH, CHART_HEIGHT ) );
	    	
	    	charts2  = new JPanel();
	     	charts2.add( chartPanel2, BorderLayout.WEST );
	     	charts2.add( secondChartPanel2, BorderLayout.EAST );
	     	
	     	JFreeChart scatterGraph = CreateScatterGraph( scatterDataset );
	     	JFreeChart secondScatterGraph = CreateSecondScatterGraph( secondScatterDataset );
			chartPanel3 = new ChartPanel( scatterGraph );
			secondChartPanel3 = new ChartPanel( secondScatterGraph );
	    	chartPanel3.setPreferredSize( new java.awt.Dimension( CHART_WIDTH, CHART_HEIGHT ) );
	    	secondChartPanel3.setPreferredSize( new java.awt.Dimension( CHART_WIDTH, CHART_HEIGHT ) );
	    	
	    	charts3  = new JPanel();
	     	charts3.add( chartPanel3, BorderLayout.WEST );
	     	charts3.add( secondChartPanel3, BorderLayout.EAST );
	     	
	     	JFreeChart lineGraph = CreateLineGraph( lineDataset );
	     	JFreeChart secondLineGraph = CreateSecondLineGraph( secondLineDataset );
			chartPanel4 = new ChartPanel( lineGraph );
			secondChartPanel4 = new ChartPanel( secondLineGraph );
	    	chartPanel4.setPreferredSize( new java.awt.Dimension( CHART_WIDTH, CHART_HEIGHT ) );
	    	secondChartPanel4.setPreferredSize( new java.awt.Dimension( CHART_WIDTH, CHART_HEIGHT ) );
	    	
	    	charts4  = new JPanel();
	     	charts4.add( chartPanel4, BorderLayout.WEST );
	     	charts4.add( secondChartPanel4, BorderLayout.EAST );
	    	
	    	JFreeChart polarChart = CreatePolarGraph( polarDataset );
	    	JFreeChart secondPolarChart = CreateSecondPolarGraph( secondPolarDataset );
			chartPanel5 = new ChartPanel( polarChart );
			secondChartPanel5 = new ChartPanel( secondPolarChart );
	    	chartPanel5.setPreferredSize( new java.awt.Dimension( CHART_WIDTH, CHART_HEIGHT ) );
	    	secondChartPanel5.setPreferredSize( new java.awt.Dimension( CHART_WIDTH, CHART_HEIGHT ) );
	    	
	    	charts5  = new JPanel();
	     	charts5.add( chartPanel5, BorderLayout.WEST );
	     	charts5.add( secondChartPanel5, BorderLayout.EAST );
	    	
	    	JFreeChart areaChart = CreateAreaGraph( areaDataset );
	    	JFreeChart secondAreaChart = CreateSecondAreaGraph( secondAreaDataset );
			chartPanel6 = new ChartPanel( areaChart );
			secondChartPanel6 = new ChartPanel( secondAreaChart );
	    	chartPanel6.setPreferredSize( new java.awt.Dimension( CHART_WIDTH, CHART_HEIGHT ) );
	    	secondChartPanel6.setPreferredSize( new java.awt.Dimension( CHART_WIDTH, CHART_HEIGHT ) );
	    	
	    	charts6  = new JPanel();
	     	charts6.add( chartPanel6, BorderLayout.WEST );
	     	charts6.add( secondChartPanel6, BorderLayout.EAST );
	    	
	    	JFreeChart bubbleChart = CreateBubbleChart( bubbleDataset );
	    	JFreeChart secondBubbleChart = CreateSecondBubbleChart( secondBubbleDataset );
			chartPanel7 = new ChartPanel( bubbleChart );
			secondChartPanel7 = new ChartPanel( secondBubbleChart );
	    	chartPanel7.setPreferredSize( new java.awt.Dimension( CHART_WIDTH, CHART_HEIGHT ) );
	    	secondChartPanel7.setPreferredSize( new java.awt.Dimension( CHART_WIDTH, CHART_HEIGHT ) );
	    	
	    	charts7  = new JPanel();
	     	charts7.add( chartPanel7, BorderLayout.WEST );
	     	charts7.add( secondChartPanel7, BorderLayout.EAST );
	     	
	     	secondSlideshow startSecondSlideshow = new secondSlideshow();
	    	startSecondSlideshow.start();
		return true;
	}
	/**
	 *  Slideshow method that createa the JFrame for a single 
	 *  Chart and with the pause and resume function
	 *
	 */
 	private class slideshow extends Thread{
		public void run() {
			JPanel buttonPanel = new JPanel(new BorderLayout());
			m_PauseButton = new JButton( "Pause" );
			m_ResumeButton = new JButton( "Resume" );
			
			iJEventHandler= new SlideshowJPanelEventHandler();
	        m_PauseButton.addActionListener( iJEventHandler );
	        m_ResumeButton.addActionListener( iJEventHandler );
			
	     	buttonPanel.add( m_ResumeButton,BorderLayout.EAST );
			buttonPanel.add( m_PauseButton,BorderLayout.WEST);			
			
			JFrame slideshow = new JFrame();
			slideshow.setLayout( new BorderLayout());
			slideshow.setSize( LARGE_FRAME_WIDTH, LARGE_FRAME_HEIGHT);
			slideshow.setTitle( "Slideshow" );

			slideshow.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
			slideshow.add(buttonPanel, BorderLayout.SOUTH);
			slideshow.setVisible( true );
			
			boolean test = true;
				while (test == true) {
				slideshow.add(chartPanel, BorderLayout.NORTH);
				slideshow.repaint();
				slideshow.validate();
				slideshow.revalidate();
				try {
					Thread.sleep(m_Timer);
				} catch (InterruptedException e){
				
				}
				do {
					try {
						Thread.sleep(m_Millisecond);
					} catch (InterruptedException e){
					}
				} while (GetPauseJButEnabled() == true);
				slideshow.remove(chartPanel);
				slideshow.repaint();
				slideshow.validate();
				slideshow.revalidate();
				slideshow.add(chartPanel2, BorderLayout.NORTH);
				slideshow.repaint();
				slideshow.validate();
				slideshow.revalidate();
				try {
					Thread.sleep(m_Timer);
				} catch (InterruptedException e){
					
				}
				do {
					try {
						Thread.sleep(m_Millisecond);
					} catch (InterruptedException e){
					}
				} while (GetPauseJButEnabled() == true);
				slideshow.remove(chartPanel2);
				slideshow.repaint();
				slideshow.validate();
				slideshow.revalidate();
				slideshow.add(chartPanel3, BorderLayout.NORTH);
				slideshow.repaint();
				slideshow.validate();
				slideshow.revalidate();
				try {
					Thread.sleep(m_Timer);
				} catch (InterruptedException e){
					
				}
				do {
					try {
						Thread.sleep(m_Millisecond);
					} catch (InterruptedException e){
					}
				} while (GetPauseJButEnabled() == true);
				slideshow.remove(chartPanel3);
				slideshow.repaint();
				slideshow.validate();
				slideshow.revalidate();
				slideshow.add(chartPanel4, BorderLayout.NORTH);
				slideshow.repaint();
				slideshow.validate();
				slideshow.revalidate();
				try {
					Thread.sleep(m_Timer);
				} catch (InterruptedException e){
					
				}
				do {
					try {
						Thread.sleep(m_Millisecond);
					} catch (InterruptedException e){
					}
				} while (GetPauseJButEnabled() == true);
				slideshow.remove(chartPanel4);
				slideshow.repaint();
				slideshow.validate();
				slideshow.revalidate();
				slideshow.add(chartPanel5, BorderLayout.NORTH);
				slideshow.repaint();
				slideshow.validate();
				slideshow.revalidate();
				try {
					Thread.sleep(m_Timer);
				} catch (InterruptedException e){
					
				}
				do {
					try {
						Thread.sleep(m_Millisecond);
					} catch (InterruptedException e){
					}
				} while (GetPauseJButEnabled() == true);
				slideshow.remove(chartPanel5);
				slideshow.repaint();
				slideshow.validate();
				slideshow.revalidate();
				slideshow.add(chartPanel6, BorderLayout.NORTH);
				slideshow.repaint();
				slideshow.validate();
				slideshow.revalidate();
				try {
					Thread.sleep(m_Timer);
				} catch (InterruptedException e){
					
				}
				do {
					try {
						Thread.sleep(m_Millisecond);
					} catch (InterruptedException e){
					}
				} while (GetPauseJButEnabled() == true);
				slideshow.remove(chartPanel6);
				slideshow.repaint();
				slideshow.validate();
				slideshow.revalidate();
				slideshow.add(chartPanel7, BorderLayout.NORTH);
				slideshow.repaint();
				slideshow.validate();
				slideshow.revalidate();
				try {
					Thread.sleep(m_Timer);
				} catch (InterruptedException e){
					
				}
				do {
					try {
						Thread.sleep(m_Millisecond);
					} catch (InterruptedException e){
					}
				} while (GetPauseJButEnabled() == true);
				slideshow.remove(chartPanel7);
				slideshow.repaint();
				slideshow.validate();
				slideshow.revalidate();
			}
		}
	}
 	/**
	 *  secondSlideshow method that creates the JFrame for two 
	 *  Charts and with the pause and resume function
	 *
	 */
	private class secondSlideshow extends Thread{
		public void run() {
			JPanel buttonPanel = new JPanel(new BorderLayout());
			m_PauseButton = new JButton( "Pause" );
			m_ResumeButton = new JButton( "Resume" );
			
			iJEventHandler= new SlideshowJPanelEventHandler();
	        m_PauseButton.addActionListener( iJEventHandler );
	        m_ResumeButton.addActionListener( iJEventHandler );
			
	     	buttonPanel.add( m_ResumeButton,BorderLayout.EAST );
			buttonPanel.add( m_PauseButton,BorderLayout.WEST);
			
			JFrame slideshow = new JFrame();
			slideshow.setLayout( new BorderLayout());
			slideshow.setSize( LARGE_FRAME_WIDTH, LARGE_FRAME_HEIGHT);
			slideshow.setTitle( "Slideshow" );

			slideshow.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
			slideshow.add(buttonPanel, BorderLayout.SOUTH);
			slideshow.setVisible( true );
			
			boolean test = true;
			while (test == true) {
				slideshow.add(charts, BorderLayout.NORTH);
				slideshow.repaint();
				slideshow.validate();
				slideshow.revalidate();
				try {
					Thread.sleep(m_Timer);
				} catch (InterruptedException e){
				
				}
				do {
					try {
						Thread.sleep(m_Millisecond);
					} catch (InterruptedException e){
					}
				} while (GetPauseJButEnabled() == true);
				slideshow.remove(charts);
				slideshow.repaint();
				slideshow.validate();
				slideshow.revalidate();
				slideshow.add(charts2, BorderLayout.NORTH);
				slideshow.repaint();
				slideshow.validate();
				slideshow.revalidate();
				try {
					Thread.sleep(m_Timer);
				} catch (InterruptedException e){
				
				}
				do {
					try {
						Thread.sleep(m_Millisecond);
					} catch (InterruptedException e){
					}
				} while (GetPauseJButEnabled() == true);
				slideshow.remove(charts2);
				slideshow.repaint();
				slideshow.validate();
				slideshow.revalidate();
				slideshow.add(charts3, BorderLayout.NORTH);
				slideshow.repaint();
				slideshow.validate();
				slideshow.revalidate();
				try {
					Thread.sleep(m_Timer);
				} catch (InterruptedException e){
				
				}
				do {
					try {
						Thread.sleep(m_Millisecond);
					} catch (InterruptedException e){
					}
				} while (GetPauseJButEnabled() == true);
				slideshow.remove(charts3);
				slideshow.repaint();
				slideshow.validate();
				slideshow.revalidate();
				slideshow.add(charts4, BorderLayout.NORTH);
				slideshow.repaint();
				slideshow.validate();
				slideshow.revalidate();
				try {
					Thread.sleep(m_Timer);
				} catch (InterruptedException e){
				
				}
				do {
					try {
						Thread.sleep(m_Millisecond);
					} catch (InterruptedException e){
					}
				} while (GetPauseJButEnabled() == true);
				slideshow.remove(charts4);
				slideshow.repaint();
				slideshow.validate();
				slideshow.revalidate();
				slideshow.add(charts5, BorderLayout.NORTH);
				slideshow.repaint();
				slideshow.validate();
				slideshow.revalidate();
				try {
					Thread.sleep(m_Timer);
				} catch (InterruptedException e){
				
				}
				do {
					try {
						Thread.sleep(m_Millisecond);
					} catch (InterruptedException e){
					}
				} while (GetPauseJButEnabled() == true);
				slideshow.remove(charts5);
				slideshow.repaint();
				slideshow.validate();
				slideshow.revalidate();
				slideshow.add(charts6, BorderLayout.NORTH);
				slideshow.repaint();
				slideshow.validate();
				slideshow.revalidate();
				try {
					Thread.sleep(m_Timer);
				} catch (InterruptedException e){
				
				}
				do {
					try {
						Thread.sleep(m_Millisecond);
					} catch (InterruptedException e){
					}
				} while (GetPauseJButEnabled() == true);
				slideshow.remove(charts6);
				slideshow.repaint();
				slideshow.validate();
				slideshow.revalidate();
				slideshow.add(charts7, BorderLayout.NORTH);
				slideshow.repaint();
				slideshow.validate();
				slideshow.revalidate();
				try {
					Thread.sleep(m_Timer);
				} catch (InterruptedException e){
				
				}
				do {
					try {
						Thread.sleep(m_Millisecond);
					} catch (InterruptedException e){
					}
				} while (GetPauseJButEnabled() == true);
				slideshow.remove(charts7);
				slideshow.repaint();
				slideshow.validate();
				slideshow.revalidate();
				
			}
		}
	}
	/**
	 * event handler method for the SlideshowJPanelEventHandler
	 *
	 */
	private class SlideshowJPanelEventHandler implements ActionListener {
    	public void actionPerformed( ActionEvent e ) {
    		if (e.getSource() == m_PauseButton ) { 
    			m_Pause = true;
    			
    		} else if  (e.getSource() == m_ResumeButton ){
    			m_Pause = false;
    		}
    	}
	}
	 /**
	  * Method to see if the pause button has been selected
	  * @return m_Pause
	  */
	public boolean GetPauseJButEnabled() {
        return m_Pause;
    }
	/**
	 * Method to see if the resume button has been selected
	 * @return m_Resume
	 */
    public boolean GetResumeVizJButEnabled() {
        return m_ResumeButton.isEnabled();
    }	
	public static void main(String[] args) {    	
			
    }
    
	SelectionVizJPanel m_SelectPan = new SelectionVizJPanel();
    private static final int LOOP = 10;
    private static final int LIMIT = 100;
    private final int m_Millisecond = 1000;
    // Frame sizes
    private final int LARGE_FRAME_WIDTH = 1220;
    private final int LARGE_FRAME_HEIGHT = 570;
    
    // Pause JButton
    private JButton m_PauseButton;
    // Resume JButton
    private JButton m_ResumeButton;
    
    private int m_Timer;
    
    private String m_ChartTitle;
    private String m_SecondChartTitle;
   
    /** an array of data for visualisation */
    private String m_XYData[][];
    private String m_PieData[][];
    private String m_ScatterData[][];
    private String m_AreaData[][];
    private String m_PolarData[][];
    private String m_LineData[][];
    private String m_BubbleData[][];
    private String m_SecondXYData[][];
    private String m_SecondPieData[][];
    private String m_SecondScatterData[][];
    private String m_SecondAreaData[][];
    private String m_SecondPolarData[][];
    private String m_SecondLineData[][];
    private String m_SecondBubbleData[][];
    private String m_SecondData[][];
    /** represents a series of vertices to be used as a data set */
    private XYSeriesCollection m_XYDataset;
    private DefaultPieDataset m_PieChartDataset;
    private XYSeriesCollection m_ScatterDataset;
    private XYSeriesCollection m_LineDataset;
    private XYSeriesCollection m_PolarDataset;
    private XYSeriesCollection m_AreaDataset;
    private DefaultXYZDataset m_BubbleDataset;
    private XYSeriesCollection m_SecondXYDataset;
    private DefaultPieDataset m_SecondPieChartDataset;
    private XYSeriesCollection m_SecondScatterDataset;
    private XYSeriesCollection m_SecondLineDataset;
    private XYSeriesCollection m_SecondPolarDataset;
    private XYSeriesCollection m_SecondAreaDataset;
    private DefaultXYZDataset m_SecondBubbleDataset;
    
    private boolean m_Pause = false;
    
    private int m_IndexCount;
    private PiePlot3D plotChart; 
    private PiePlot3D plotSecondChart; 
    private XYItemRenderer m_Renderer;
    private XYItemRenderer m_SecondRenderer;
    private ColumnChart m_ColumnChart;
    private JFreeChart m_LineChart;
    private PieChart m_PieChart;
    private JFreeChart m_PolarChart;
    private JFreeChart m_ScatterPlotChart;
   
    // Data attribute instance
    private DataAttribute m_Setting;
    private DataAttribute m_SecondSetting;
    
    /** x and y label axis */
    private String m_XLabel;
    private String m_YLabel;
    
    private String m_SecondXLabel;
    private String m_SecondYLabel;
    
    private final float TRANSPARENCY = 0.6f;
    private final int START_ANGLE = 290;
    private final int CHART_WIDTH = 500;
    private final int CHART_HEIGHT = 400;
   
    private final static double VALUE_MULTIPLY = 2;
    private final static double VALUE_ADD = 1;
    private final static double MAX = 20;
    private final static double MIN = 0;
    
    /** amount of rows */
    private int m_Row;
    private int m_SecondRow;
    
    /** Create ChartPanel Instances */
    private ChartPanel chartPanel;
    private ChartPanel chartPanel2;
    private ChartPanel chartPanel3;
    private ChartPanel chartPanel4;
    private ChartPanel chartPanel5;
    private ChartPanel chartPanel6;
    private ChartPanel chartPanel7;
    private ChartPanel firstChartPanel;
    private ChartPanel secondChartPanel;
    private ChartPanel secondChartPanel2;
    private ChartPanel secondChartPanel3;
    private ChartPanel secondChartPanel4;
    private ChartPanel secondChartPanel5;
    private ChartPanel secondChartPanel6;
    private ChartPanel secondChartPanel7;
    
    /** Create JPanel instances */
    private JPanel charts;
    private JPanel charts2;
    private JPanel charts3;
    private JPanel charts4;
    private JPanel charts5;
    private JPanel charts6;
    private JPanel charts7;
    
    private SlideshowJPanelEventHandler iJEventHandler;
   
    /** columns */
    private int m_C1;
    private int m_C2;
    private int m_C3;
    private int m_SecondC1;
    private int m_SecondC2;
    private int m_SecondC3;
   
    private double m_XMin;
    private double m_XMax;
    private double m_YMin;
    private double m_YMax;
    private double m_XScale;
    private double m_YScale;
    private static int m_TestIndexCount = 0;
    
    private Frame timerOption;
    /**set the initial lower margin number*/
    private double m_LowerMargin = 0.0;
    
    /**set the initial upper margin number*/
    private double m_UpperMargin = 0.0;
    
    /**sets the chart Foreground Alpha */
    private float m_ForegroundAlpha = 0.65f;
    
    private boolean m_OptionPopup = true;
    
    private XYPlot m_Plot;
    private XYPlot m_SecondPlot; 
}
