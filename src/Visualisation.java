/**
* @file -Visualisation.java
* @author -Gavin Driscoll A4, Bradley Coles-Perkins A6
* @class  -Visualisation
* @date - 30/04/2013
* @see -BobsViz.java
*
* @brief A class that displays specific data in a Column Chart visualiser.
*
*/
import javax.swing.JPanel;

public class Visualisation extends JPanel {
    
    public ColumnChart GetColChart() {
        return m_ColumnChart;
    }
    /**
    * creates a column chart. 
    * @param String[][] data - data used to create the visualisation.
    * @param Dataset dataset - the dataset used (rows and columns)
    * @param DataAttribute setting - Data attributes
    * @return Boolean true - if succcess
    */
    public boolean SetColChart( String[][] data, 
    							Dataset dataset,
    							DataAttribute setting) {
    	
        m_ColumnChart = new ColumnChart( data, dataset, setting );
         
        return true;
    }
    /**
     * creates a column chart.
     * @param String[][] data - data used to create the visualisation.
     * @param Dataset dataset - the dataset used (rows and columns)
     * @param DataAttribute setting - Data attributes
     * @param String[][] secondData - data used to create the second visualisation.
     * @param Dataset secondDataset - the second dataset used (rows and columns)
     * @param DataAttribute secondSetting - second Data attributes
     * @return Boolean true - if succcess
     */
    public boolean SetColChart( String[][] data, Dataset dataset,
    		DataAttribute setting, String[][] secondData, Dataset secondDataset,
    		DataAttribute secondSetting ) {

    	m_ColumnChart = new ColumnChart( data, dataset, setting, secondData, secondDataset, secondSetting);

    	return true;
    }
    
    /**
     * Gets Pie Chart 
     * @return PieChart m_PieChart
     */
    public PieChart GetVizPieChart(){
        return m_PieChart;
    }
    /**
    * creates a pie chart.
    * @param String[] data - data used to create the visualisation.
    * @param Dataset dataset - the dataset used (rows and columns)
    * @param DataAttribute setting - Data attributes
    * @return Boolean true - if succcess
    */
    public boolean SetVizPieChart( String[][] data, 
								   Dataset dataset,
								   DataAttribute setting ) {
          
    	m_PieChart = new PieChart( data, dataset, setting);
        
        return true;
    }

    /**
	*creates a pie chart.
	* @param String[] data - data used to create the visualisation.
	* @param Dataset dataset - the dataset used (rows and columns)
* @param DataAttribute setting - Data attributes
* @return Boolean true - if succcess
*/
    public boolean SetVizPieChart( String[][] data, Dataset dataset,
    		DataAttribute setting, String[][] secondData, Dataset secondDataset,
    		DataAttribute secondSetting) {

    	m_PieChart = new PieChart( data, dataset, setting, secondData, secondDataset, secondSetting);

		return true;
    }
    /**
     * Gets Area Chart
     *  
     * @return AreaChart m_AreaChart
     */
    public AreaChart GetAreaChart() {
        return m_AreaChart;
    }
    /**
    * creates a area chart. 
    * @param String[][] data - data used to create the visualisation.
    * @param Dataset dataset - the dataset used (rows and columns)
    * @param DataAttribute setting - Data attributes
    * @return Boolean true - if succcess
    */
    public boolean SetAreaChart( String[][] data, 
    							Dataset dataset,
    							DataAttribute setting) {
    	
        m_AreaChart = new AreaChart( data, dataset, setting );
         
        return true;
    }
    
    public boolean SetAreaChart( String[][] data, Dataset dataset,
    		DataAttribute setting, String[][] secondData, Dataset secondDataset,
    		DataAttribute secondSetting ) {

    	m_AreaChart = new AreaChart( data, dataset, setting, secondData, secondDataset, secondSetting );

		return true;
    }
    
    /**
    * creates a table visualisation.
    * @param data - data used to create a visualisations. 
    * @param headers - gets the headers for the table visualisation.
    * @return - boolen true if success
    */
    public boolean SetTable( String[][] data, String[] headers, String title) {
        m_Table = new Table( data, headers, title );
        boolean test = true;
        
        if(( test == true ) && ( data == null )) {
        System.err.println( "SetTable::SetTable() ***Warning, object"
                    + "data is null. Value sent: " + data );
        }
           else if(( test == true ) && ( headers == null )) {
        System.err.println( "SetTable::SetTable() ***Warning, object"
                    + "headers is null. Value sent: " + headers );
        }
        
        return true;
    }
    /**
     * Gets Table
     *  
     * @return Table m_Table
     */
    public Table GetTable(){
        //Returns a Table
        return m_Table;
    }
    
    /**
    * creates a scatter plot graph visualisation.
    * @param String[][] data - data used to create the visualisation.
    * @param Dataset dataset - the dataset used (rows and columns)
    * @param DataAttribute setting - Data attributes
    * @return Boolean true - if succcess
    */
    public boolean SetScatterPlotGraph( String[][] data, 
										Dataset dataset,
										DataAttribute setting ) {
        m_ScatterPlotGraph = new ScatterPlotGraph(data, dataset, setting);
        
        return true;
    }
    /**
     * creates two scatter plot graph visualisations.
     * @param String[][] data - data used to create the visualisation.
     * @param Dataset dataset - the dataset used (rows and columns)
     * @param DataAttribute setting - Data attributes
     * @param String[][] secondData - second data used to create the visualisation.
     * @param Dataset secondDataset - the second dataset used (rows and columns)
     * @param DataAttribute secondSetting - second Data attributes
     * @return Boolean true - if succcess
     */
    public boolean SetScatterPlotGraph( String[][] data, Dataset dataset,
    		DataAttribute setting, String[][] secondData, Dataset secondDataset,
    		DataAttribute secondSetting ) {
    	
    	m_ScatterPlotGraph = new ScatterPlotGraph(data, dataset, setting, secondData, secondDataset, secondSetting);

		return true;
    }
    
    /**
     * Gets ScatterPlotGraph
     *  
     * @return ScatterPlotGraph m_ScatterPlotGraph
     */
    public ScatterPlotGraph GetScatterPlotGraph(){
        //Returns a Table
        return m_ScatterPlotGraph;
    }
    /**
     * Gets BubbleChart
     *  
     * @return BubbleChart m_BubbleChart
     */
    public BubbleChart GetBubbleChart() {
        return m_BubbleChart;
    }
    /**
    * creates a bubble chart. 
    * @param String[][] data - data used to create the visualisation.
    * @param Dataset dataset - the dataset used (rows and columns)
    * @param DataAttribute setting - Data attributes
    * @return Boolean true - if succcess
    */
    public boolean SetBubbleChart( String[][] data, 
    							Dataset dataset,
    							DataAttribute setting) {
    	
    	m_BubbleChart = new BubbleChart( data, dataset, setting );
         
        return true;
    }
    /**
     * creates two bubble graph visualisations.
     * @param String[][] data - data used to create the visualisation.
     * @param Dataset dataset - the dataset used (rows and columns)
     * @param DataAttribute setting - Data attributes
     * @param String[][] secondData - second data used to create the visualisation.
     * @param Dataset secondDataset - the second dataset used (rows and columns)
     * @param DataAttribute secondSetting - second Data attributes
     * @return Boolean true - if succcess
     */
    public boolean SetBubbleChart( String[][] data, Dataset dataset,
    		DataAttribute setting, String[][] secondData, Dataset secondDataset,
    		DataAttribute secondSetting ) {

    	m_BubbleChart = new BubbleChart(data, dataset, setting, secondData, secondDataset, secondSetting);

		return true;
    }
    /**
     * Gets Line Chart
     *  
     * @return LineChart m_LineChart
     */
    public LineChart GetLineChart() {
        return m_LineChart;
    }
    /**
    * creates a line chart. 
    * @param String[][] data - data used to create the visualisation.
    * @param Dataset dataset - the dataset used (rows and columns)
    * @param DataAttribute setting - Data attributes
    * @return Boolean true - if succcess
    */
    public boolean SetLineChart( String[][] data, 
    							Dataset dataset,
    							DataAttribute setting) {
    	
    	m_LineChart = new LineChart( data, dataset, setting );
         
        return true;
    }
    /**
     * creates two line graph visualisations.
     * @param String[][] data - data used to create the visualisation.
     * @param Dataset dataset - the dataset used (rows and columns)
     * @param DataAttribute setting - Data attributes
     * @param String[][] secondData - second data used to create the visualisation.
     * @param Dataset secondDataset - the second dataset used (rows and columns)
     * @param DataAttribute secondSetting - second Data attributes
     * @return Boolean true - if succcess
     */
    public boolean SetLineChart( String[][] data, Dataset dataset,
    		DataAttribute setting, String[][] secondData, Dataset secondDataset,
    		DataAttribute secondSetting ) {

    	m_LineChart = new LineChart( data, dataset, setting, secondData, secondDataset, secondSetting);

    	return true;
    }
    
    /**
     * Gets Polar Chart
     *  
     * @return PolarChart m_PolarChart
     */
    public PolarChart GetPolarChart() {
        return m_PolarChart;
    }
    /**
    * creates a polar chart. 
    * @param String[][] data - data used to create the visualisation.
    * @param Dataset dataset - the dataset used (rows and columns)
    * @param DataAttribute setting - Data attributes
    * @return Boolean true - if succcess
    */
    public boolean SetPolarChart( String[][] data, 
    							Dataset dataset,
    							DataAttribute setting) {
    	
    	m_PolarChart = new PolarChart( data, dataset, setting );
         
        return true;
    }
    /**
     * creates two polar chart graph visualisations.
     * @param String[][] data - data used to create the visualisation.
     * @param Dataset dataset - the dataset used (rows and columns)
     * @param DataAttribute setting - Data attributes
     * @param String[][] secondData - second data used to create the visualisation.
     * @param Dataset secondDataset - the second dataset used (rows and columns)
     * @param DataAttribute secondSetting - second Data attributes
     * @return Boolean true - if succcess
     */
    public boolean SetPolarChart( String[][] data, Dataset dataset,
    		DataAttribute setting, String[][] secondData, Dataset secondDataset,
    		DataAttribute secondSetting ) {

    		m_PolarChart = new PolarChart( data, dataset, setting, secondData, secondDataset, secondSetting );

		return true;
    }
    /**
     * creates a slideshow visualisation. 
     * @param String[][] data - data used to create the visualisation.
     * @param Dataset dataset - the dataset used (rows and columns)
     * @param DataAttribute setting - Data attributes
     * @return Boolean true - if succcess
     */
   public boolean SetSlideshow( String[][] data, 
			Dataset dataset,
			DataAttribute setting) {

		m_Slideshow = new Slideshow( data, dataset, setting );
		
		return true;
	}
   /**
    * creates two slideshow visualisations.
    * @param String[][] data - data used to create the visualisation.
    * @param Dataset dataset - the dataset used (rows and columns)
    * @param DataAttribute setting - Data attributes
    * @param String[][] secondData - second data used to create the visualisation.
    * @param Dataset secondDataset - the second dataset used (rows and columns)
    * @param DataAttribute secondSetting - second Data attributes
    * @return Boolean true - if succcess
    */
    public boolean SetSlideshow( String[][] data, Dataset dataset,
    		DataAttribute setting, String[][] secondData, Dataset secondDataset,
    		DataAttribute secondSetting) {
		m_Slideshow = new Slideshow( data, dataset, setting, secondData, secondDataset, secondSetting );
		
		return true;
	}

      /**
    * creates test instances. 
    */
    public static void main(String[][] args) {
        Visualisation v = new Visualisation();
        
        v.SetScatterPlotGraph( null, null, null );
        v.SetTable( null, null, null );
        v.SetVizPieChart( null, null, null );
        v.SetColChart( null, null, null );
    }
 
            
           
    /**
    * @return - returns the visualisation. boolean true
    * 			if successful 
    */
    public boolean SetBV( BobViz bv ) {
        boolean test = true;
        if(( test == true ) && ( bv == null) ) {
            System.err.println( "Visualisation::SetBV() ***Warning, GroupPDM2"
                    + "application is null. Value sent: " + bv );
        } else if ( test == true ) {
            System.out.println( "Visualisation::SetBV() GroupPDM2"
                    + "application. Value sent: " + bv );
        }
        m_BV = bv;
        return true;
    }
    
    
    private PieChart m_PieChart;
    private ScatterPlotGraph m_ScatterPlotGraph;
    private ColumnChart m_ColumnChart;
    private AreaChart m_AreaChart;
    private BubbleChart m_BubbleChart;
    private LineChart m_LineChart;
    private PolarChart m_PolarChart;
    private Table m_Table;
    private BobViz m_BV;
    private Slideshow m_Slideshow;

}