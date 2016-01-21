/**
 * @file   Table.java
 * @author Connor McFadden A4, Bradley Coles-Perkins A6
 * @class  Table
 * @date   30/04/2013
 *
 * @brief A simple class that displays data in a table visualisation
 *
 */

import java.awt.*;
import javax.swing.*;

public class Table extends JPanel {
    /**
    * @param m_data - the data to be passed from users to the table
    * @param m_headers - the headers to be passed to the table
    * @param tableTitle - the title to be passed to the table
    */
    public Table( String[][] m_data, String[] m_headers, String tableTitle ) {
        /**Instantiate a table taking in specific data from users*/
	JTable table = new JTable( m_data, m_headers );
	table.setBorder( BorderFactory.createLineBorder( Color.gray ) );
        JLabel title = new JLabel( tableTitle );
       
        JPanel panel = new JPanel();
        panel.add( title );
        /** Create a JScrollPane which adds the JTable*/
        JScrollPane scrollPane = new JScrollPane( table );
        scrollPane.setBorder( BorderFactory.createEmptyBorder() );
        JPanel tableJPan = new JPanel();
        JButton disabledBut = new JButton( "Change colour" );
        disabledBut.setEnabled( false );
        tableJPan.add( scrollPane );
        
        JFrame test = new JFrame();
        test.setLayout( new BorderLayout() );
        test.setSize( WIDTH_FRAME, HEIGHT_FRAME );
        test.setTitle( "Table" );
        test.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        test.add( tableJPan, BorderLayout.CENTER );
        test.add( panel, BorderLayout.NORTH );
        test.setVisible( true );
    }
    /**
     * 
     * @param m_data - the data to be passed from users to the table
     * @param m_header - the headers to be passed to the table
     * @param tableTitle - the title to be passed to the table
     * @param m_secondData - the second CSV data to be passed from users to the table
     * @param m_secondHeader - the second headers to be passed to the table
     * @param secondTableTitle - the second title to be passed to the table
     */
    public Table( String[][] m_data, String[] m_header, String tableTitle, 
    			  String [][] m_secondData, String[] m_secondHeader, String secondTableTitle){
    	JTable table = new JTable( m_data, m_header );
    	table.setBorder( BorderFactory.createLineBorder( Color.gray ) );
    		
            JLabel title = new JLabel( tableTitle + "      " +
            		"                                      " +
            		"                                      " +
            		secondTableTitle );
            JPanel panel = new JPanel();
            panel.add( title );
            
            /** Create a JScrollPane which adds the JTable*/
            JScrollPane scrollPane = new JScrollPane( table );
            scrollPane.setBorder( BorderFactory.createEmptyBorder() );
            JPanel tableJPan = new JPanel();
            JButton disabledBut = new JButton( "Change colour" );
            disabledBut.setEnabled( false );
            tableJPan.add( scrollPane );
           
        JTable secondTable = new JTable( m_secondData, m_secondHeader );
     	secondTable.setBorder( BorderFactory.createLineBorder( Color.gray ) );
            
            JScrollPane secondScrollPane = new JScrollPane( secondTable );
            secondScrollPane.setBorder( BorderFactory.createEmptyBorder() );
            JPanel secondTableJPan = new JPanel();
            JButton secondDisabledBut = new JButton( "Change colour" );
            secondDisabledBut.setEnabled( false );
            secondTableJPan.add( secondScrollPane );
           
        JFrame test = new JFrame();
        test.setLayout( new BorderLayout() );
        test.setSize( SECOND_WIDTH_FRAME, SECOND_HEIGHT_FRAME );
        test.setTitle( "Table with two csv files" );
        test.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        test.add( tableJPan, BorderLayout.WEST);
        test.add( panel , BorderLayout.NORTH);
        test.add( secondTableJPan, BorderLayout.EAST);        
        test.setVisible( true );
            
       
    }
    
    public static void main(String[] args) {
 
    	String array[][] = arrayTest;
    	String array1[] = {"header1","header2","header3","header4"};
    	Table Tabletest = new Table(array, array1, "Test Table");
   	
    	String arraya[][] = arrayTest;
    	String array2[] = {"header1","header2","header3","header4"};
    	String arrayb[][] = arrayTest2;
    	String array3[] = {"header5","header6","header7","header8"};
    	
    	Table Tabletest2 = new Table(arraya, array2, "Test Table 1", arrayb, array3, "Test Table 2");
    }
    private final static String[][] arrayTest = {{"1","2","3","4"},{"4","3","2","1"}};
    private final static String[][] arrayTest2 = {{"5","6","7","8"},{"8","7","6","5"}};
    
    private final int WIDTH_FRAME = 500;
    private final int HEIGHT_FRAME = 600;
    
    private final int SECOND_WIDTH_FRAME = 1000;
    private final int SECOND_HEIGHT_FRAME = 600;
}