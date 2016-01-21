/**
 * @file SelectionVizJPanel.java
 * @author Rhys Owen A4, Bradley Coles-Perkins A5,A6
 * @class  SelectionVizJPanel
 * @date 30/04/2013
 * @see Table.java, ColumnChart.java, ScatterGraph.java, PieChart.java
 * 
 * @brief This class passes the data from the GUI to the constructors of the
 * visualisation types.
 */
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SelectionVizJPanel extends JPanel {
    
    public SelectionVizJPanel() {
        
        /* create new visualisation button */
        m_VisualizeJBut = new JButton( "Generate Visualization!" );
        /* create new slideshow visualisation button */
        m_SlideshowVisualizeJBut = new JButton( "Generate Slideshow!" );

        /* set visualisation font */
        m_VisualizeJBut.setFont( new Font( "Courier", Font.BOLD, FONT_SIZE ) );
        m_VisualizeJBut.setEnabled( false );
        add( m_VisualizeJBut );
        m_SlideshowVisualizeJBut.setFont( new Font( "Courier", Font.BOLD, FONT_SIZE ) );
        m_SlideshowVisualizeJBut.setEnabled( false );
        add( m_SlideshowVisualizeJBut );


        /* set listeners */
        GenerateVizJPanelEventHandler iJEventHandler = new 
                GenerateVizJPanelEventHandler();
        m_VisualizeJBut.addActionListener( iJEventHandler );
        
        GenerateSlideshowVizJPanelEventHandler iSlideshowJEventHandler = new 
                GenerateSlideshowVizJPanelEventHandler();
        m_SlideshowVisualizeJBut.addActionListener( iSlideshowJEventHandler );
        
    }
    
   /**
    * This method will cast the BobViz instance from
    * BobVizDemo.
    * @param bv -a BobViz object
    * @return boolean if true
    */
    public boolean SetBV( BobViz bv ) {
        boolean test = true;
        if( ( test == true ) && ( bv == null ) ) {
            System.err.println( "GenerateVizJPanel::SetBV() ***Warning, object"
                    + "is null. Value sent: " + bv );
        } else if ( test == true ) {
            System.out.println( "GenerateVizJPanel::SetBV() Object is valid. "
                    + "Value sent: " + bv );
        }
        m_BV = bv;
        return true;
    }
    
   /**
    * This method changes the enabled state of the visualisation button.
    * @param b -a boolean object
    * @return boolean true if successful
    */
    public boolean SetVizJButEnabled( boolean b ) {
        boolean test = true;
        if ( test == true ) {
            System.out.println( "GenerateVizJPanel::SetVizJButEnabled() m_"
                    + "VisualizeJBut enabled status: " + b );
        }
        m_VisualizeJBut.setEnabled( b );
        return true;
    }
    /**
     * This method changes the enabled state of the Slideshow button.
     * @param b -a boolean object
     * @return boolean true if successful
     */
    public boolean SetSlideshowVizJButEnabled( boolean b ) {
        boolean test = true;
        if ( test == true ) {
            System.out.println( "GenerateVizJPanel::SetSlideshowVizJButEnabled() m_"
                    + "SlideshowVisualizeJBut enabled status: " + b );
        }
        m_SlideshowVisualizeJBut.setEnabled( b );
        return true;
    }
    
    /**
     * @return the state of visualise button
     */
    public boolean GetVizJButEnabled() {
        return m_VisualizeJBut.isEnabled();
    }
    /**
     * @return the state of the slideshow button
     */
    public boolean GetSlideshowVizJButEnabled() {
        return m_SlideshowVisualizeJBut.isEnabled();
    }

    /**
     * Sets X & Y axis labels in DataAttribute
     * @return boolean true if successful
     */
    public boolean SetDataAttribute() {
    	m_BV.GetDataAttribute().SetTitle( m_BV.GetSettingJPan().
                GetTitle() );
    	m_BV.GetDataAttribute().SetSecondTitle( m_BV.GetSettingJPan().
                GetSecondTitle() );
        /* store axis label x and y in DataAttribute */
      	m_BV.GetDataAttribute().SetAxisLabelX( m_BV.
                GetSettingJPan().GetAxisLabelX());        
      	m_BV.GetDataAttribute().SetAxisLabelY( m_BV.
                GetSettingJPan().GetAxisLabelY());
      	m_BV.GetDataAttribute().SetAxisLabelZ( m_BV.
                GetSettingJPan().GetAxisLabelZ());
      	m_BV.GetDataAttribute().SetSecondAxisLabelX( m_BV.
                GetSettingJPan().GetSecondAxisLabelX());        
      	m_BV.GetDataAttribute().SetSecondAxisLabelY( m_BV.
                GetSettingJPan().GetSecondAxisLabelY());
      	m_BV.GetDataAttribute().SetSecondAxisLabelZ( m_BV.
                GetSettingJPan().GetSecondAxisLabelZ());
      	
      	m_BV.GetDataAttribute().SetXAxisMin( m_BV.
      			GetSettingJPan().GetXAxisMin());
      	m_BV.GetDataAttribute().SetXAxisMax( m_BV.
      			GetSettingJPan().GetXAxisMax());
      	m_BV.GetDataAttribute().SetYAxisMin( m_BV.
      			GetSettingJPan().GetYAxisMin());
      	m_BV.GetDataAttribute().SetYAxisMax( m_BV.
      			GetSettingJPan().GetYAxisMax());
      	
      	m_BV.GetDataAttribute().SetXAxisScale( m_BV.
      			GetSettingJPan().GetXAxisScale());
      	m_BV.GetDataAttribute().SetYAxisScale( m_BV.
      			GetSettingJPan().GetYAxisScale());
      	
      	m_BV.GetDataAttribute().SetChartAuthor( m_BV.
      			GetSettingJPan().GetAuthor());
      	m_BV.GetDataAttribute().SetChartDesciption( m_BV.
      			GetSettingJPan().GetDescription());
      	m_BV.GetDataAttribute().DisplayAll();
      	
      	m_BV.GetDataAttribute().SetUseDefault(
      			m_BV.GetSettingJPan().GetDefaultButton().isSelected());
      	
    	return true;
    }
 
    /* event handler for GenerateVizJPanel */
    private class GenerateVizJPanelEventHandler implements ActionListener {
    	public void actionPerformed( ActionEvent e ) {
    		if (e.getSource() == m_VisualizeJBut ) {
    			if(m_BV.GetVizTypeJPan().GetColumnChartButton().isSelected()) {
    				if(( m_BV.GetImportJPan().GetFirstCheck() == true) &&  (m_BV.GetImportJPan().GetSecondCheck() == false)){
	    				SetDataAttribute();
	                	m_BV.GetViz().SetColChart(
	                          m_BV.GetDataset().GetArray(),
	                          m_BV.GetDataset(),
	                          m_BV.GetDataAttribute());
	    			}
    				else if((m_BV.GetImportJPan().GetFirstCheck() == true) &&  (m_BV.GetImportJPan().GetSecondCheck() == true)){
    					SetDataAttribute();
                    	m_BV.GetViz().SetColChart(
                    		  m_BV.GetDataset().GetArray(),
                              m_BV.GetDataset(),
                              m_BV.GetDataAttribute(),
                              m_BV.GetSecondDataset().GetArray(),
                              m_BV.GetSecondDataset(),
                              m_BV.GetDataAttribute());	
    				}
                	
    			} else if(m_BV.GetVizTypeJPan().GetPieChartButton().isSelected()) {
    				if(( m_BV.GetImportJPan().GetFirstCheck() == true) &&  (m_BV.GetImportJPan().GetSecondCheck() == false)){
	    				SetDataAttribute();
	    				m_BV.GetViz().SetVizPieChart( 
	    						m_BV.GetDataset().GetArray()
	                          , m_BV.GetDataset()
	                          , m_BV.GetDataAttribute());
    				}
    				else if((m_BV.GetImportJPan().GetFirstCheck() == true) &&  (m_BV.GetImportJPan().GetSecondCheck() == true)){
    					SetDataAttribute();
                    	m_BV.GetViz().SetVizPieChart(
                              m_BV.GetDataset().GetArray(),
                              m_BV.GetDataset(),
                              m_BV.GetDataAttribute(),
                              m_BV.GetSecondDataset().GetArray(),
                              m_BV.GetSecondDataset(),
                              m_BV.GetDataAttribute());		
    				}
    			} else if(m_BV.GetVizTypeJPan().GetScatterChartButton().isSelected()) {
    				if(( m_BV.GetImportJPan().GetFirstCheck() == true) &&  (m_BV.GetImportJPan().GetSecondCheck() == false)){
    					SetDataAttribute();
	    				m_BV.GetViz().SetScatterPlotGraph(
	                            m_BV.GetDataset().GetArray(),
	                            m_BV.GetDataset(),
	                            m_BV.GetDataAttribute());
    				}
    				else if((m_BV.GetImportJPan().GetFirstCheck() == true) &&  (m_BV.GetImportJPan().GetSecondCheck() == true)){
    					SetDataAttribute();
                    	m_BV.GetViz().SetScatterPlotGraph(
                              m_BV.GetDataset().GetArray(),
                              m_BV.GetDataset(),
                              m_BV.GetDataAttribute(),
                              m_BV.GetSecondDataset().GetArray(),
                              m_BV.GetSecondDataset(),
                              m_BV.GetDataAttribute());
    				}
    			} else if(m_BV.GetVizTypeJPan().GetAreaChartButton().isSelected()) {
    				if(( m_BV.GetImportJPan().GetFirstCheck() == true) &&  (m_BV.GetImportJPan().GetSecondCheck() == false)){
	    				SetDataAttribute();
	    				m_BV.GetViz().SetAreaChart(
	                            m_BV.GetDataset().GetArray(),
	                            m_BV.GetDataset(),
	                            m_BV.GetDataAttribute());
    				}
    				else if((m_BV.GetImportJPan().GetFirstCheck() == true) &&  (m_BV.GetImportJPan().GetSecondCheck() == true)){
    					SetDataAttribute();
                    	m_BV.GetViz().SetAreaChart(
                              m_BV.GetDataset().GetArray(),
                              m_BV.GetDataset(),
                              m_BV.GetDataAttribute(),
                              m_BV.GetSecondDataset().GetArray(),
                              m_BV.GetSecondDataset(),
                              m_BV.GetDataAttribute());
    				}
    			} else if(m_BV.GetVizTypeJPan().GetBubbleChartButton().isSelected()) {
    				if(( m_BV.GetImportJPan().GetFirstCheck() == true) &&  (m_BV.GetImportJPan().GetSecondCheck() == false)){
	    				SetDataAttribute();
	    				m_BV.GetViz().SetBubbleChart(
	                            m_BV.GetDataset().GetArray(),
	                            m_BV.GetDataset(),
	                            m_BV.GetDataAttribute());
    				}
    				else if((m_BV.GetImportJPan().GetFirstCheck() == true) &&  (m_BV.GetImportJPan().GetSecondCheck() == true)){
	    					SetDataAttribute();
	                    	m_BV.GetViz().SetBubbleChart(
	                              m_BV.GetDataset().GetArray(),
	                              m_BV.GetDataset(),
	                              m_BV.GetDataAttribute(),
	                              m_BV.GetSecondDataset().GetArray(),
	                              m_BV.GetSecondDataset(),
	                              m_BV.GetDataAttribute());
    				}
    			} else if(m_BV.GetVizTypeJPan().GetLineChartButton().isSelected()) {
    				if(( m_BV.GetImportJPan().GetFirstCheck() == true) &&  (m_BV.GetImportJPan().GetSecondCheck() == false)){
	    				SetDataAttribute();
	    				m_BV.GetViz().SetLineChart(
	                            m_BV.GetDataset().GetArray(),
	                            m_BV.GetDataset(),
	                            m_BV.GetDataAttribute());
    				}
    				else if((m_BV.GetImportJPan().GetFirstCheck() == true) &&  (m_BV.GetImportJPan().GetSecondCheck() == true)){
    					SetDataAttribute();
                    	m_BV.GetViz().SetLineChart(
                              m_BV.GetDataset().GetArray(),
                              m_BV.GetDataset(),
                              m_BV.GetDataAttribute(),
                              m_BV.GetSecondDataset().GetArray(),
                              m_BV.GetSecondDataset(),
                              m_BV.GetDataAttribute());
    				}
    			} else if(m_BV.GetVizTypeJPan().GetPolarChartButton().isSelected()) {
    				if(( m_BV.GetImportJPan().GetFirstCheck() == true) &&  (m_BV.GetImportJPan().GetSecondCheck() == false)){
	    				SetDataAttribute();
	    				m_BV.GetViz().SetPolarChart(
	                            m_BV.GetDataset().GetArray(),
	                            m_BV.GetDataset(),
	                            m_BV.GetDataAttribute());
    				}
    				else if((m_BV.GetImportJPan().GetFirstCheck() == true) &&  (m_BV.GetImportJPan().GetSecondCheck() == true)){
    					SetDataAttribute();
                    	m_BV.GetViz().SetPolarChart(
                              m_BV.GetDataset().GetArray(),
                              m_BV.GetDataset(),
                              m_BV.GetDataAttribute(),
                              m_BV.GetSecondDataset().GetArray(),
                              m_BV.GetSecondDataset(),
                              m_BV.GetDataAttribute());
    				}
    			} 
    			if(m_BV.GetVizTypeJPan().GetTableViewButton().isSelected()) {
    				m_BV.GetTableJPan();
    			}  
    			
    		}
    	}
    }
    /* event handler for GenerateSlideshowVizJPanel */
    private class GenerateSlideshowVizJPanelEventHandler implements ActionListener {
    	public void actionPerformed( ActionEvent e ) {
    		if (e.getSource() == m_SlideshowVisualizeJBut ) {
    			if(( m_BV.GetImportJPan().GetFirstCheck() == true) &&  (m_BV.GetImportJPan().GetSecondCheck() == false)){
    				SetDataAttribute();
						m_BV.GetViz().SetSlideshow(
						m_BV.GetDataset().GetArray(),
				        m_BV.GetDataset(),
				        m_BV.GetDataAttribute());
					}
    			else if((m_BV.GetImportJPan().GetFirstCheck() == true) &&  (m_BV.GetImportJPan().GetSecondCheck() == true)){
    				SetDataAttribute();
                	m_BV.GetViz().SetSlideshow(
                          m_BV.GetDataset().GetArray(),
                          m_BV.GetDataset(),
                          m_BV.GetDataAttribute(),
                          m_BV.GetSecondDataset().GetArray(),
                          m_BV.GetSecondDataset(),
                          m_BV.GetDataAttribute());
    			}
    		}
    	}
    }
    
    /** create visualisation JButton */
    private JButton m_VisualizeJBut;
    /** create slideshow visualisation JButton */
    private JButton m_SlideshowVisualizeJBut;
    /** font size of visualisation button JLabel */
    private final int FONT_SIZE = 13;
    private final int TEXT_WIDTH = 50;
    private final int TEXT_HEIGHT = 30;
    private JLabel about;
 
    
    /** create BobViz object */
    private BobViz m_BV;
    
}