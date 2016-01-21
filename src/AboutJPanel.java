/**
 * @file AboutJPanel.java
 * @author Rhys Owen A5, Bradley Coles-Perkins A6 
 * @class AboutJPanel
 * @date 30/04/2013
 * @see BobViz.java
 * 
 * @brief This class provides the user with information about the application.
 */
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AboutJPanel extends JPanel {
    public AboutJPanel() {
        /* create dimension for AboutJPanel */
        Dimension size = getPreferredSize();
        size.width = WIDTH;
        size.height = HEIGHT;
        /* set dimensions for AboutJPanel */
        setPreferredSize( size );
        /* set border with title for AboutJPanel */ 
        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder( Color.GRAY ), "About"));
        /* create about JLabel */
        JLabel about = new JLabel("<HTML><p>BobViz generates an array of charts"
                + " and graphs using a CSV file.</p> <p>This program was "
                + "created by group PDM1 at Swansea University, </p><p>" +
                "and then further implemented by Bradley Coles-Perkins</p></HTML>");
        /* add about JLabel to AboutJPanel */
        add( about );
   
    }
    public AboutJPanel( boolean b ) {
            
    }
    
    /**
    * @param bv - a BobViz object
    * @return TRUE on success
    */
    public boolean SetBV( BobViz bv ) {
        boolean test = true;
        if( ( test == true ) && ( bv == null) ) {
            System.err.println( "AboutJPanel::SetBV() ***Warning, GroupPDM2"
                    + "application is null. Value sent: " + bv );
        } else if ( test == true ) {
            System.out.println( "AboutJPanel::SetBV() GroupPDM2"
                    + "application. Value sent: " + bv );
        }
        m_BV = bv;
        return true;
    }
    
   /**
    * This method is used for testing.
    * @param args -user input not used
    */
    public static void main(String[] args) {
        AboutJPanel aJPan = new AboutJPanel( true );
        aJPan.SetBV( null );
    }
    
    /** create new BobViz object **/
    private BobViz m_BV;
    /** the height of the AboutJPanel panel */
    private static final int HEIGHT = 80;
    /** the width of the AboutJPanel panel */
    private static final int WIDTH = 550;
    
}