/**
 * @file -SettingJPanel.java
 * @author -Rhys Owen A4, Leopold Stiegler A5, Bradley Coles-Perkins A6
 * @class  -SettingJPanel
 * @date -30/04/2013
 * @see BobViz.java
 * 
 * @brief This class provides the settings for each visualisation.
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SettingJPanel extends JPanel {
    
    public SettingJPanel() {
        /* set new size dimensions */
        Dimension size = getPreferredSize();
        size.width = PAN_WIDTH;
        size.height = PAN_HEIGHT;
        setPreferredSize( size );
        
        setBorder( BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder( Color.GRAY ), SELECT_VIZ_PREF
                 ) );
        setLayout( new FlowLayout( FlowLayout.CENTER ) );
        
        /* create new JPanel */
        JPanel centerJPan = new JPanel();
        centerJPan.setLayout( new GridLayout( GRID_ROW, GRID_COL, GRID_VGAP, 
                GRID_HGAP ) );
        
        /* set default x and y axis */
        String[] xAxis = {""};
        String[] yAxis = {""};
        String[] zAxis = {""};
        
        /* create new jlabels */
        JLabel blank = new JLabel( "" );
        JLabel firstCSVTitle = new JLabel( "First CSV" );
        JLabel secondCSVTitle = new JLabel( "Second CSV" );
        JLabel chartTitJLab = new JLabel( "Chart title:" );
        JLabel xAxisJLab = new JLabel( "X axis:" );
        JLabel yAxisJLab = new JLabel( "Y axis:" );
        JLabel zAxisJLab = new JLabel( "Z axis:" );
        
        /* create new input fields */
        m_ChartTitJTextF = new JTextField( TEXT_FIELD_LEN );
        m_xAxisJCom = new JComboBox( xAxis );
        m_yAxisJCom = new JComboBox( yAxis );
        m_zAxisJCom = new JComboBox( zAxis );
        m_zAxisJCom.setEnabled(false);
        
        m_SecondChartTitJTextF = new JTextField( TEXT_FIELD_LEN );
        m_SecondxAxisJCom = new JComboBox( xAxis );
        m_SecondyAxisJCom = new JComboBox( yAxis );
        m_SecondzAxisJCom = new JComboBox( zAxis );
        m_SecondzAxisJCom.setEnabled(false);
        
        
        /* set all input fields disabled by default */
        m_ChartTitJTextF.setEnabled( false );
        m_SecondChartTitJTextF.setEnabled( false );
        m_xAxisJCom.setEnabled( false );
        m_yAxisJCom.setEnabled( false );
        m_zAxisJCom.setEnabled( false );
        m_SecondxAxisJCom.setEnabled( false );
        m_SecondyAxisJCom.setEnabled( false );
        m_SecondzAxisJCom.setEnabled( false );
        m_AdvancedButton.setEnabled( false );
        
        /* add all components to centerJPan */
        centerJPan.add( blank );
        centerJPan.add( firstCSVTitle );
        centerJPan.add( secondCSVTitle );
        centerJPan.add( chartTitJLab );
        centerJPan.add( m_ChartTitJTextF );
        centerJPan.add( m_SecondChartTitJTextF );
        centerJPan.add( xAxisJLab );
        centerJPan.add( m_xAxisJCom );
        centerJPan.add( m_SecondxAxisJCom );
        centerJPan.add( yAxisJLab );
        centerJPan.add( m_yAxisJCom );
        centerJPan.add( m_SecondyAxisJCom );
        centerJPan.add( zAxisJLab );
        centerJPan.add( m_zAxisJCom );
        centerJPan.add( m_SecondzAxisJCom );
        centerJPan.add( m_AdvancedButton );
        
        /* add centerJPan to SettingJPanel */
        add( centerJPan );
        
        /* set listeners */
        SettingJPanelEventHandler iJEventHandler = new 
                SettingJPanelEventHandler();
        m_xAxisJCom.addActionListener( iJEventHandler );
        m_yAxisJCom.addActionListener( iJEventHandler );
        m_zAxisJCom.addActionListener( iJEventHandler );
        
        m_AdvancedButton.addActionListener( iJEventHandler ); 
        
        SettingSecondJPanelEventHandler iSecondJEventHandler = new 
                SettingSecondJPanelEventHandler();
        m_SecondxAxisJCom.addActionListener( iSecondJEventHandler );
        m_SecondyAxisJCom.addActionListener( iSecondJEventHandler );
        m_SecondzAxisJCom.addActionListener( iSecondJEventHandler );
        
    }
    /**
     * Gets X Axis Min Field
     *  
     * @return X Axis Min Field
     */
    public double GetXAxisMin() {
    	return m_SettingPanel.GetXAxisMin();
    }
    /**
     * Gets X Axis Max Field
     *  
     * @return X Axis Max Field
     */
    public double GetXAxisMax() {
    	return m_SettingPanel.GetXAxisMax();
    }
    /**
     * Gets Y Axis Min Field
     *  
     * @return Y Axis Min Field
     */
    public double GetYAxisMin() {
    	return m_SettingPanel.GetYAxisMin();
    }
    /**
     * Gets Y Axis Max Field
     *  
     * @return X Axis Max Field
     */
    public double GetYAxisMax() {
    	return m_SettingPanel.GetYAxisMax();
    }
    /**
     * Gets X Axis Scale Field
     *  
     * @return X Axis Scale Field
     */
    public double GetXAxisScale() {
    	return m_SettingPanel.GetXAxisScale();
    }
    /**
     * Gets Y Axis Scale Field
     *  
     * @return Y Axis Scale Field
     */
    public double GetYAxisScale() {
    	return m_SettingPanel.GetYAxisScale();
    }
    /**
     * Gets Authour Text
     *  
     * @return Author Field
     */
    public String GetAuthor() {
    	return m_SettingPanel.GetAuthor();
    }
    /**
     * Gets Description Text
     *  
     * @return Description Text Field
     */
    public String GetDescription() {
    	return m_SettingPanel.GetDescription();
    }
    /**
     * Gets Default Button
     *  
     * @return Default Button
     */
    public JToggleButton GetDefaultButton() {
    	return m_SettingPanel.GetDefaultButton();
    }
    /**
     * Gets ZAxis Combo Box
     *  
     * @return JComboBox ZAxis value
     */
    public JComboBox GetZAxisComboBox() {
    	return m_zAxisJCom;
    }
    /**
     * Gets Second ZAxis Combo Box
     *  
     * @return SecondJComboBox ZAxis value
     */
    public JComboBox GetSecondZAxisComboBox() {
    	return m_SecondzAxisJCom;
    }
    
   /**
    * @param bv - a BobViz object
    * @return TRUE on success
    */
    public boolean SetBV(BobViz bv) {
        boolean test = true;
        if((test == true) && (bv == null)) {
            System.err.println( "SettingJPanel::SetBV() ***Warning, object"
                    + "is null. Value sent: " + bv );
        } else if (test == true) {
            System.out.println( "SettingJPanel::SetBV() Object is valid. Value"
                    + "sent: " + bv );
        }
        m_BV = bv;
        return true;
    }
    
   /**
    * This method sets the setting panel enabled or disabled. This
    * will prevent the user selecting x and y axis' values
    * before they have imported a file.
    * @param b - a boolean object
    * @return TRUE on success
    */
    public boolean SetSettingsEnabled(boolean b) {
       m_ChartTitJTextF.setEnabled(b);
       m_xAxisJCom.setEnabled(b);
       m_yAxisJCom.setEnabled(b);
       m_AdvancedButton.setEnabled(b);
       return true;
    }
    /**
     * This method sets the second setting panel enabled or disabled. This
     * will prevent the user selecting x and y axis' values
     * before they have imported a file.
     * @param b - a boolean object
     * @return TRUE on success
     */
    public boolean SetSecondSettingsEnabled(boolean b) {
        m_SecondChartTitJTextF.setEnabled(b);
        m_SecondxAxisJCom.setEnabled(b);
        m_SecondyAxisJCom.setEnabled(b);
        m_AdvancedButton.setEnabled(b);
        return true;
     }
    
   /**
    * This method sets the correct visualisation system type.
    * @param headings - an array list of current listed headings from the
    * CSV file.
    * @return TRUE on success
    */
    public boolean SetVizSysType(String[] headings) {
        m_xAxisJCom.removeAllItems();
        m_yAxisJCom.removeAllItems();
        m_zAxisJCom.removeAllItems();
        for(String s: headings){
            m_xAxisJCom.addItem(s);
            m_yAxisJCom.addItem(s);
            m_zAxisJCom.addItem(s);
        }
        return true;
    }
    /**
     * This method sets the correct second visualisation system type.
     * @param headings - an array list of current listed headings from the
     * CSV file.
     * @return TRUE on success
     */
    public boolean SetSecondVizSysType(String[] headings) {
        m_SecondxAxisJCom.removeAllItems();
        m_SecondyAxisJCom.removeAllItems();
        m_SecondzAxisJCom.removeAllItems();
        for(String s: headings){
            m_SecondxAxisJCom.addItem(s);
            m_SecondyAxisJCom.addItem(s);
            m_SecondzAxisJCom.addItem(s);
        }
        return true;
    }
    
   /**
    * This method will return the title of the visualisation.
    * @return String - the current title.
    */
    public String GetTitle() {
        m_Title = m_ChartTitJTextF.getText();
        return m_Title;
    }
    /**
     * This method will return the second title of the visualisation.
     * @return String - the current title.
     */
    public String GetSecondTitle() {
        m_SecondTitle = m_SecondChartTitJTextF.getText();
        return m_SecondTitle;
    }
    
   /**
    * This method will return the x axis label.
    * @return String - the current x axis label.
    */
    public String GetAxisLabelX() {
        return (String) m_xAxisJCom.getSelectedItem();
    }
    /**
     * This method will return the second x axis label.
     * @return String - the current x axis label.
     */
    public String GetSecondAxisLabelX() {
        return (String) m_SecondxAxisJCom.getSelectedItem();
    }
    
   /**
    * This method will return the y axis label.
    * @return String - the current y axis label.
    */
    public String GetAxisLabelY() {
        return (String) m_yAxisJCom.getSelectedItem();
    }
    /**
     * This method will return the second y axis label.
     * @return String - the current y axis label.
     */
    public String GetSecondAxisLabelY() {
        return (String) m_SecondyAxisJCom.getSelectedItem();
    }
    
    /**
     * This method will return the z axis label.
     * @return String - the current z axis label.
     */
     public String GetAxisLabelZ() {
         return (String) m_zAxisJCom.getSelectedItem();
     }
     /**
      * This method will return the second z axis label.
      * @return String - the current z axis label.
      */
     public String GetSecondAxisLabelZ() {
         return (String) m_SecondzAxisJCom.getSelectedItem();
     }
      
   /**
    * This method will return the current x index.
    * @return int - the current selected x index.
    */
    public int GetSelectedXIndex() {
        return m_xAxisJCom.getSelectedIndex();
    }
    /**
     * This method will return the current second x index.
     * @return int - the current selected x index.
     */
    public int GetSecondSelectedXIndex() {
        return m_SecondxAxisJCom.getSelectedIndex();
    }
    
   /**
    * This method will return the current y index.
    * @return int - the current selected y index.
    */
    public int GetSelectedYIndex() {
        return m_yAxisJCom.getSelectedIndex();
    }
    /**
     * This method will return the current second y index.
     * @return int - the current selected y index.
     */
    public int GetSecondSelectedYIndex() {
        return m_SecondyAxisJCom.getSelectedIndex();
    }
    
    /**
     * This method will return the current z index.
     * @return int - the current selected z index.
     */
     public int GetSelectedZIndex() {
         return m_zAxisJCom.getSelectedIndex();
     }
     /**
      * This method will return the current second z index.
      * @return int - the current selected z index.
      */
     public int GetSecondSelectedZIndex() {
         return m_SecondzAxisJCom.getSelectedIndex();
     }
   /**
    * This method will check if all settings are enabled.
    * @return boolean - the result of all setting objects (to
    * check if they're enabled).
    */
    public boolean GetSettingsEnabled() {
       return m_ChartTitJTextF.isEnabled()
               && m_xAxisJCom.isEnabled()
               && m_yAxisJCom.isEnabled()
               && m_AdvancedButton.isEnabled();
   }
    /**
     * This method will check if all secondSettings are enabled.
     * @return boolean - the result of all secondSetting objects (to
     * check if they're enabled).
     */
    public boolean GetSecondSettingsEnabled() {
        return m_SecondChartTitJTextF.isEnabled()
                && m_SecondxAxisJCom.isEnabled()
                && m_SecondyAxisJCom.isEnabled()
                && m_AdvancedButton.isEnabled();
    }
    /**
     * Gets Panel Width
     *  
     * @return Panel Width
     */
    public int GetPanelWidth() {
    	return PAN_WIDTH;
    }
    /**
     * Gets Panel Height
     *  
     * @return Panel Height
     */
    public int GetPanelHeight() {
    	return PAN_HEIGHT;
    }
   /** width of SettingJPanel */
   private final int PAN_WIDTH = 550;
   /** height of SettingJPanel */
   private final int PAN_HEIGHT = 230;
    
   private final String SELECT_VIZ_PREF = "Select Visualization Preferences";
    
   /** the values of the grid */
   private final int GRID_ROW = 6;
   private final int GRID_COL = 3;
   private final int GRID_VGAP = 1;
   private final int GRID_HGAP = 5;
        
   /** the length of the text field */
   private final int TEXT_FIELD_LEN = 14;
   private JComboBox m_xAxisJCom;
   private JComboBox m_yAxisJCom;
   private JComboBox m_zAxisJCom;
   private JComboBox m_SecondxAxisJCom;
   private JComboBox m_SecondyAxisJCom;
   private JComboBox m_SecondzAxisJCom;
   
   private JButton m_AdvancedButton = new JButton("More");
   private JTextField m_ChartTitJTextF = new JTextField(TEXT_FIELD_LEN);
   private JTextField m_SecondChartTitJTextF = new JTextField(TEXT_FIELD_LEN);
    
   private String m_Title;
   private String m_SecondTitle;
    
   /** create new BobViz object */
   private BobViz m_BV;
   
   private DataAttribute m_ChartSetting = new DataAttribute();
   private MoreSettingJPanel m_SettingPanel = new MoreSettingJPanel(m_ChartSetting);
    
   /* event handler for setting jpanel */
   private class SettingJPanelEventHandler implements ActionListener {
	   Boolean autoGen = true;
        @Override
        public void actionPerformed( ActionEvent e ) {
            String xLabel;
            String yLabel;
            String zLabel;
            
        	try {
            	xLabel = (String) m_xAxisJCom.getSelectedItem();
            	yLabel = (String) m_yAxisJCom.getSelectedItem();
            	zLabel = (String) m_zAxisJCom.getSelectedItem();
            } catch (Exception er) {
            	xLabel = "";
            	yLabel = "";
            	zLabel = "";
            }
        	
        	if( e.getSource() == m_xAxisJCom ) {
                m_BV.GetDataAttribute()
                	.SetSelectedXIndex( m_xAxisJCom.getSelectedIndex() );
                
                if(autoGen) {
                	m_ChartTitJTextF
                		.setText(((String) m_xAxisJCom.getSelectedItem())
                				+ " against " + ((String) m_yAxisJCom.getSelectedItem()));
                }
            } else if( e.getSource() == m_yAxisJCom ) {
                m_BV.GetDataAttribute()
                	.SetSelectedYIndex( m_yAxisJCom.getSelectedIndex() );
          
                if(autoGen) {
                	m_ChartTitJTextF
                		.setText(((String) m_xAxisJCom.getSelectedItem())
                				+ " against " + ((String) m_yAxisJCom.getSelectedItem()));
                }
            } else if( e.getSource() == m_zAxisJCom ) {
                m_BV.GetDataAttribute()
            	.SetSelectedZIndex( m_zAxisJCom.getSelectedIndex() );
	            
	            if(autoGen && m_BV.GetVizTypeJPan().GetBubbleChartButton().isSelected()) {
	            	m_ChartTitJTextF
	            		.setText(((String) m_xAxisJCom.getSelectedItem())
	            				+ " against " + ((String) m_yAxisJCom.getSelectedItem()
	            				+ " and "	  +	(String) m_zAxisJCom.getSelectedItem()));
	            }
            } else if( e.getSource() == m_AdvancedButton ) {
            	m_SettingPanel.setVisible(true);
            } else if ( e.getSource() == m_ChartTitJTextF) {
            	autoGen = false;
            }
        }
    }
   /* event handler for secondSetting jpanel */
   private class SettingSecondJPanelEventHandler implements ActionListener {
	   Boolean autoGen = true;
        @Override
        public void actionPerformed( ActionEvent e ) {
            String xLabel;
            String yLabel;
            String zLabel;
            
        	try {
            	xLabel = (String) m_SecondxAxisJCom.getSelectedItem();
            	yLabel = (String) m_SecondyAxisJCom.getSelectedItem();
            	zLabel = (String) m_SecondzAxisJCom.getSelectedItem();
            } catch (Exception er) {
            	xLabel = "";
            	yLabel = "";
            	zLabel = "";
            }
        	
        	if( e.getSource() == m_SecondxAxisJCom ) {
                m_BV.GetDataAttribute()
                	.SetSecondSelectedXIndex( m_SecondxAxisJCom.getSelectedIndex() );
                
                if(autoGen) {
                	m_SecondChartTitJTextF
                		.setText(((String) m_SecondxAxisJCom.getSelectedItem())
                				+ " against " + ((String) m_SecondyAxisJCom.getSelectedItem()));
                }
            } else if( e.getSource() == m_SecondyAxisJCom ) {
                m_BV.GetDataAttribute()
                	.SetSecondSelectedYIndex( m_SecondyAxisJCom.getSelectedIndex() );
          
                if(autoGen) {
                	m_SecondChartTitJTextF
                		.setText(((String) m_SecondxAxisJCom.getSelectedItem())
                				+ " against " + ((String) m_SecondyAxisJCom.getSelectedItem()));
                }
            } else if( e.getSource() == m_SecondzAxisJCom ) {
                m_BV.GetDataAttribute()
            	.SetSecondSelectedZIndex( m_SecondzAxisJCom.getSelectedIndex() );
	            
	            if(autoGen && m_BV.GetVizTypeJPan().GetBubbleChartButton().isSelected()) {
	            	m_SecondChartTitJTextF
	            		.setText(((String) m_SecondxAxisJCom.getSelectedItem())
	            				+ " against " + ((String) m_SecondyAxisJCom.getSelectedItem()
	            				+ " and "	  +	(String) m_SecondzAxisJCom.getSelectedItem()));
	            }
            } else if( e.getSource() == m_AdvancedButton ) {
            	m_SettingPanel.setVisible(true);
            } else if ( e.getSource() == m_SecondChartTitJTextF) {
            	autoGen = false;
            }
        }
    }
   
   public static void main(String[] args) {
   		JFrame frame = new JFrame();
	    SettingJPanel settingJPan = new SettingJPanel();
	    JPanel panel = new JPanel();
	    panel.add(settingJPan);
	    frame.setSize(new Dimension(settingJPan.GetPanelWidth(),settingJPan.GetPanelHeight()));
	    frame.add(panel);
	    frame.setVisible(true);
   }
}