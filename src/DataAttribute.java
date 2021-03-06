/**
* @file -DataAttribute.java
* @author - Matthew Rees A4, Leopold Stiegler A5, Bradley Coles-Perkins A6
* @class DataAttribute
* @date - 30/04/13
*
* @brief A class which is responsible setting graph attributes
* 
* A class used to give attributes such as title and axis titles 
* to the data visualisation this class will be used throughout 
* the program and collaborate with many classes.
*/

public class DataAttribute {
    /**
     * Constructor to initialise all variables
     */
	public DataAttribute() {
		
		m_getTitle = "";
		m_getAxisLabelX = "";
		m_getAxisLabelY = "";
		m_getAxisLabelZ = "";
		m_getSecondTitle = "";
		m_getSecondAxisLabelX = "";
		m_getSecondAxisLabelY = "";
		m_getSecondAxisLabelZ = "";
		m_XAxisMin = 0;
		m_XAxisMax = DEFAULT_MAX;
		m_YAxisMin = 0;
		m_YAxisMax = DEFAULT_MAX;
		m_XAxisScale = 1;
		m_YAxisScale = 1;
		m_ChartAuthor = "";
		m_ChartDescription = "";
	}
	

    /** 
     * Get method returns the user defined axis X label
    * @return Returns user defined axis X label
    */
    public String GetAxisLabelX(){
        return m_getAxisLabelX;
    }
    /** 
     * Get method returns the user defined axis X label
    * @return Returns second user defined axis X label
    */
    public String GetSecondAxisLabelX(){
        return m_getSecondAxisLabelX;
    }

    /** 
     * Get method returns the user defined axis Y label
     * @return Returns user defined axis Y label
     */
    public String GetAxisLabelY(){
        return m_getAxisLabelY;
    }
    /** 
     * Get method returns the user defined axis Y label
     * @return Returns second user defined axis Y label
     */
    public String GetSecondAxisLabelY(){
        return m_getSecondAxisLabelY;
    }
    
    /** 
     * Get method returns the user defined axis Z label
     * @return Returns user defined axis Z label
     */
     public String GetAxisLabelZ(){
         return m_getAxisLabelZ;
     }
     /** 
      * Get method returns the user defined axis Z label
      * @return Returns second user defined axis Z label
      */
     public String GetSecondAxisLabelZ(){
         return m_getSecondAxisLabelZ;
     }
    
    /** 
     * Gets the selected index of Axis X.
     * @return index reference of the X Axis.
     */
    public int GetSelectedXIndex() {
        return m_xAxisSelectedIndex;
    }
    /** 
     * Gets the selected index of the second Axis X.
     * @return index reference of the second Axis X.
     */
    public int GetSecondSelectedXIndex() {
        return m_SecondxAxisSelectedIndex;
    }
    /** 
     * Gets the selected index of axis Y.
     * @return index reference of axis Y. 
     */
    public int GetSelectedYIndex() {
        return m_yAxisSelectedIndex;
    }
    /** 
     * Gets the selected index of second axis Y.
     * @return index reference of second axis Y. 
     */
    public int GetSecondSelectedYIndex() {
        return m_SecondyAxisSelectedIndex;
    }
    /** 
     * Gets the selected index of axis Z.
     * @return index reference of axis Z. 
     */
    public int GetSelectedZIndex() {
		return m_zAxisSelectedIndex;
	}
    /** 
     * Gets the selected index of Second axis Z.
     * @return index reference of Second axis Z. 
     */
    public int GetSecondSelectedZIndex() {
		return m_SecondzAxisSelectedIndex;
	}
    
    /** 
    * Get method returns the user defined axis label
    * @return Returns user defined title
    */
    public String GetTitle(){
        return m_getTitle;
    }
    /** 
    * Get method returns the user defined Secnond axis label
    * @return Returns user defined second title
    */
    public String GetSecondTitle(){
        return m_getSecondTitle;
    }
    
    /** 
     * This method uses unit tests and uses the user input from the GUI, if 
     * the input is blank then the default label is given. Another test ensures
     * if the axis label given is too long it will warn the user that it is too
     * long.
     * @param zAxis- User Input.
     * @return boolean - if result is true
     */
     public boolean SetAxisLabelZ( String axisZ ){
    	 m_getAxisLabelZ = axisZ;
    	 boolean test = true;
         return true;
     }
     /** 
      * This method uses unit tests and uses the user input from the GUI, if 
      * the input is blank then the default label is given. Another test ensures
      * if the axis label given is too long it will warn the user that it is too
      * long.
      * @param zAxis- User Input.
      * @return boolean - if result is true
      */
     public boolean SetSecondAxisLabelZ( String axisZ ){
    	 m_getSecondAxisLabelZ = axisZ;
    	 boolean test = true;
         return true;
     }
     
    /** 
     * This method uses unit tests and uses the user input from the GUI, if 
    * the input is blank then the default label is given. Another test ensures
    * if the axis label given is too long it will warn the user that it is too
    * long.
    * @param yAxis- User Input.
    * @return boolean - if result is true
    */
    public boolean SetAxisLabelY( String axisY ){
    	m_getAxisLabelY = axisY;
    	boolean test = true;
        return true;
    }
    /** 
     * This method uses unit tests and uses the user input from the GUI, if 
    * the input is blank then the default label is given. Another test ensures
    * if the axis label given is too long it will warn the user that it is too
    * long.
    * @param yAxis- User Input.
    * @return boolean - if result is true
    */
    public boolean SetSecondAxisLabelY( String axisY ){
    	m_getSecondAxisLabelY = axisY;
    	boolean test = true;
        return true;
    }

    /** 
     * This method uses unit tests and uses the user input from the GUI, if 
    * the input is blank then the default label is given. Another test ensures
    * if the axis label given is too long it will warn the user that it is too
    * long.
    * @param axisX- User Input.
    * @return boolean - if result is true
    */
    public boolean SetAxisLabelX( String axisX ){
    	m_getAxisLabelX = axisX;
    	boolean test = true;
        return true;
    }
    /** 
    * This method uses unit tests and uses the user input from the GUI, if 
    * the input is blank then the default label is given. Another test ensures
    * if the axis label given is too long it will warn the user that it is too
    * long.
    * @param axisX- User Input.
    * @return boolean - if result is true
    */
    public boolean SetSecondAxisLabelX( String axisX ){
    	m_getSecondAxisLabelX = axisX;
    	boolean test = true;
        return true;
    }
    
    /** 
     * Sets the selected index of Y axis
     * @param index
     * @return boolean - if result is true
     */
    public boolean SetSelectedYIndex( int index ) {
        m_yAxisSelectedIndex = index;
        return true;
    }
    /** 
     * Sets the selected index of Second Y axis
     * @param index
     * @return boolean - if result is true
     */
    public boolean SetSecondSelectedYIndex( int index ) {
        m_SecondyAxisSelectedIndex = index;
        return true;
    }
    
    /** 
     * Sets the selected index of X axis
     * @param index
     * @return boolean - if result is true
     */
    public boolean SetSelectedXIndex( int index ) {
        m_xAxisSelectedIndex = index;
        return true;
    }
    /** 
     * Sets the selected index of Second X axis
     * @param index
     * @return boolean - if result is true
     */
    public boolean SetSecondSelectedXIndex( int index ) {
        m_SecondxAxisSelectedIndex = index;
        return true;
    }
    /** Sets the selected index of Z axis
     * @param index
     * @return boolean - if result is true
     */
    public boolean SetSelectedZIndex( int index ) {
        m_zAxisSelectedIndex = index;
        return true;
    }
    /** Sets the selected index of Second Z axis
     * @param index
     * @return boolean - if result is true
     */
    public boolean SetSecondSelectedZIndex( int index ) {
        m_SecondzAxisSelectedIndex = index;
        return true;
    }

    /** 
     * This method checks the user input to ensure that 
    * the user defined title is present and if not the 
    * method will set the visualisation title to "Default
    * Title". The unit tests given here ensure that if the title is left blank
    * a default title is given it also ensures that if a title given is too 
    * large the user is warned.
    * @param title- returns user defined title
    * @return boolean - if result is true
    */
    public boolean SetTitle( String title ){
    	m_getTitle = title;
    	boolean test = true;
        return true;
    }
    /** 
     * This method checks the user input to ensure that 
    * the user defined second title is present and if not the 
    * method will set the visualisation title to "Default
    * Title". The unit tests given here ensure that if the title is left blank
    * a default title is given it also ensures that if a title given is too 
    * large the user is warned.
    * @param secondTitle- returns user defined title
    * @return boolean - if result is true
    */
    public boolean SetSecondTitle( String secondTitle ){
    	m_getSecondTitle = secondTitle;
    	boolean test = true;
        return true;
    }

    /**
    * @param bv - a BobViz object
    * @return TRUE on success
    */
    public boolean SetBV( BobViz bv ) {
        boolean test = true;
        m_BV = bv;
        return true;
    }
    /**
     * Set the minimum for the x-axis
     * @param double xMin for the x-axis
     * @return Boolean true if success
     */
    public Boolean SetXAxisMin(double xMin) {
    	m_XAxisMin = xMin;
    	return true;
    }
    /**
     * Get the minimum for the x-axis
     * @return double xMin
     */
    public double GetXAxisMin() {
    	return m_XAxisMin;
    }
    /**
     * Set the maximum for the x-axis
     * @param double xMax for the x-axis
     * @return Boolean true if success
     */
    public Boolean SetXAxisMax(double xMax) {
    	m_XAxisMax = xMax;
    	return true;
    }
    /**
     * Get the maximum for the x-axis
     * @return double xMax
     */
    public double GetXAxisMax() {
    	return m_XAxisMax;
    }
    /**
     * Set the minimum for the y-axis
     * @param double yMin for the y-axis
     * @return Boolean true if success
     */
    public Boolean SetYAxisMin(double yMin) {
    	m_YAxisMin = yMin;
    	return true;
    }
    /**
     * Get the minimum for the y-axis
     * @return double yMin
     */
    public double GetYAxisMin() {
    	return m_YAxisMin;
    }
    /**
     * Set the maximum for the y-axis
     * @param double yMax for the y-axis
     * @return Boolean true if success
     */
    public Boolean SetYAxisMax(double yMax) {
    	m_YAxisMax = yMax;
    	return true;
    }
    /**
     * Get the maximum for the y-axis
     * @return double yMin
     */
    public double GetYAxisMax() {
    	return m_YAxisMax;
    }
    /**
     * Set the axis scale for the x-axis
     * @param double xScale for the x-axis
     * @return Boolean true if success
     */
    public Boolean SetXAxisScale(double xScale) {
    	m_XAxisScale = xScale;
    	return true;
    }
    /**
     * Get the scale for the x-axis
     * @return double xScale
     */
    public double GetXAxisScale() {
    	return m_XAxisScale;
    }
    /**
     * Set the axis scale for the y-axis
     * @param double yScale for the y-axis
     * @return Boolean true if success
     */
    public Boolean SetYAxisScale(double yScale) {
    	m_YAxisScale = yScale;
    	return true;
    }
    /**
     * Get the scale for the y-axis
     * @return double yScale
     */
    public double GetYAxisScale() {
    	return m_YAxisScale;
    }
    
    /**
     * Set the author for the chart
     * @param String author for the chart
     * @return Boolean true if success
     */
    public Boolean SetChartAuthor(String author) {
    	m_ChartAuthor = author;
    	return true;
    }
    /**
     * Get the author for the chart
     * @return String chartAuthor
     */
    public String GetTimeStamp() {
    	return m_TimeStamp;
    }
    /**
     * Set the author for the chart
     * @param String timestamp for the chart
     * @return Boolean true if success
     */
    public Boolean SetTimeStamp(String stamp) {
    	m_TimeStamp = stamp;
    	return true;
    }
    /**
     * Get the timestamp for the chart
     * @return String chartAuthor
     */
    public String GetChartAuthor() {
    	return m_ChartAuthor;
    }
    
    /**
     * Set the description for the chart
     * @param String description for the chart
     * @return Boolean true if success
     */
    public Boolean SetChartDesciption(String description) {
    	m_ChartDescription = description;
    	return true;
    }
    /**
     * Get the description for the chart
     * @return String chartDescription
     */
    public String GetChartDescription() {
    	return m_ChartDescription;
    }
    
    /**
     * Set whether to use defaults or not
     * @param Boolean state
     * @return Boolean true if success
     */
    public Boolean SetUseDefault(Boolean state) {
    	m_DefaultSetting = state;
    	return true;
    }
    /**
     * Get boolean on whether to use defaults instead
     * @return Boolean state
     */
    public Boolean GetUseDefault() {
    	return m_DefaultSetting;
    }
    
    /**
     * Private method to get the sample minimum
     * @return double SAMPLE_MIN_1
     */
    private static double getSampleMin1() {
    	return SAMPLE_MIN_1;
    }
    /**
     * Private method to get the sample maximum
     * @return double SAMPLE_MAX_1
     */
    private static double getSampleMax1() {
    	return SAMPLE_MAX_1;
    }
    /**
     * Private method to get the sample maximum
     * @return double SAMPLE_SCALE_1
     */
    private static double getSampleScale1() {
    	return SAMPLE_SCALE_1;
    }
    
    /**
     * This method displays all the variables content
     * @return Boolean true if success
     */
    public boolean DisplayAll() {
    	System.out.println("Chart title: " + GetTitle());
    	System.out.println("Second Chart title: " + GetSecondTitle());
    	System.out.println("X label: " + GetAxisLabelX());
    	System.out.println("Y label: " + GetAxisLabelY());
    	System.out.println("X index: " + GetSelectedXIndex());
    	System.out.println("Y index: " + GetSelectedYIndex());
    	System.out.println("Second X label: " + GetSecondAxisLabelX());
    	System.out.println("Second Y label: " + GetSecondAxisLabelY());
    	System.out.println("Second X index: " + GetSecondSelectedXIndex());
    	System.out.println("Second Y index: " + GetSecondSelectedYIndex());
    	System.out.println("X min: " + GetXAxisMin());
    	System.out.println("X max: " + GetXAxisMax());
    	System.out.println("Y min: " + GetYAxisMin());
    	System.out.println("Y max: " + GetYAxisMax());
    	System.out.println("X scale: " + GetXAxisScale());
    	System.out.println("Y scale: " + GetYAxisScale());
    	System.out.println("Chart author: " + GetChartAuthor());
    	System.out.println("Chart description: " + GetChartDescription());
    	
    	return true;
    }
    
    /**
     * This method generates a random data attributes which are then used in some tests
     * @return testSet
     */
    public static DataAttribute GetTestDataAttribute() {
    	DataAttribute testSet = new DataAttribute();
    	
    	testSet.SetTitle("Test 1");
    	testSet.SetAxisLabelX("Test xLabel");
    	testSet.SetAxisLabelY("Test yLabel");
    	testSet.SetSecondTitle("Test 2");
    	testSet.SetSecondAxisLabelX("Second Test xLabel");
    	testSet.SetSecondAxisLabelY("Second Test yLabel");
    	testSet.SetSelectedXIndex(0);
    	testSet.SetSelectedYIndex(1);
    	testSet.SetSecondSelectedXIndex(0);
    	testSet.SetSecondSelectedYIndex(1);
    	testSet.SetXAxisMin(DataAttribute.getSampleMin1());
    	testSet.SetXAxisMax(DataAttribute.getSampleMax1());
    	testSet.SetYAxisMin(DataAttribute.getSampleMin1());
    	testSet.SetYAxisMax(DataAttribute.getSampleMax1());
    	testSet.SetXAxisScale(DataAttribute.getSampleScale1());
    	testSet.SetYAxisScale(DataAttribute.getSampleScale1());
    	testSet.SetUseDefault(true);
    	testSet.SetChartAuthor("TestSet");
    	testSet.SetChartDesciption("These attributes are a test");
    	
    	return testSet;
    }

    
    /**
     * This method is used for testing purposes.
    * @param args - no user input needed.
    */
    public static void main( String args[] ) {
        boolean test = true;
        if ( test ) {
            System.out.println( "DataAttribute::main() BEGIN unit test");
        }
        
        DataAttribute setting1 = new DataAttribute();
    	setting1 = DataAttribute.GetTestDataAttribute();
    	setting1.DisplayAll();
    }
    
    
    /*Global variables used throughout data attribute to retrieve vital 
     * attributes which data visualisations will hold such as title
     * and axis titles.
     */
    private static final double SAMPLE_MIN_1 = 0.0;
	private static final double SAMPLE_MAX_1 = 10.0;
	private static final double SAMPLE_SCALE_1 = 1.0;
	
	private final double DEFAULT_MAX = 500;
	
	/**Defining variables for the dataset */
	private Boolean m_DefaultSetting;
    private String m_getTitle;
    private String m_getAxisLabelY;
    private String m_getAxisLabelX;
    private String m_getAxisLabelZ;
    private String m_defaultTitle = "Default Title";
    private String m_defaultAxisLabelY = "Default Label";
    private String m_defaultAxisLabelX = "Default Label";
    private int m_xAxisSelectedIndex = 0;
    private int m_yAxisSelectedIndex = 0;
    private int m_zAxisSelectedIndex = 0;
    private String m_TimeStamp;
    private Boolean m_SecondDefaultSetting;
    private String m_getSecondTitle;
    private String m_getSecondAxisLabelY;
    private String m_getSecondAxisLabelX;
    private String m_getSecondAxisLabelZ;
    private String m_defaultSecondTitle = "Second Default Title";
    private String m_defaultSecondAxisLabelY = "Second Default Label";
    private String m_defaultSecondAxisLabelX = "Second Default Label";
    private int m_SecondxAxisSelectedIndex = 0;
    private int m_SecondyAxisSelectedIndex = 0;
    private int m_SecondzAxisSelectedIndex = 0;
    private double m_XAxisMin;
    private double m_XAxisMax;
    private double m_YAxisMin;
    private double m_YAxisMax;
    private double m_XAxisScale;
    private double m_YAxisScale;
    private String m_ChartAuthor;
    private String m_ChartDescription;

    /* create new BobViz object */
    private BobViz m_BV;

}