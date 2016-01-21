/**
 * @file BobViz.java
 * @class GroupPDM2application
 * @author Rhys Owen A4, Leopold Stiegler A5, Bradley Coles-Perkins A6
 * @date 01/03/2013
 * 
 * @brief This class creates a new instance of BobViz and parses 
 * it to all panels to ensure only one instance of BobViz is 
 * ever used.
 */

public class GroupPDM2application {
    
   /**
    * This class is used to execute all instances. This makes
    * object programming easier.
    * @param args -user input not used
    */
    public static void main( String[] args ) {
        /* create new instance of BobViz */
        BobViz bv = new BobViz();
        
        /* send all instances of bv to all classes */
        bv.GetViz().SetBV(bv);
        bv.GetImportJPan().SetBV(bv);
        bv.GetSettingJPan().SetBV(bv);
        bv.GetAboutJPan().SetBV(bv);
        bv.GetDataset().SetBV(bv);
        bv.GetStatusJPan().SetBV(bv);
        bv.GetVizTypeJPan().SetBV(bv);
        bv.GetGenerateVizJPan().SetBV(bv);
        
        bv.SetBV(bv);
    }
    
}