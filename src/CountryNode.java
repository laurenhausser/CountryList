

/**
 *
 * @author laurenhausser
 */
public class CountryNode 
{
    // Global Data Items
    protected Country nInfo;
    protected CountryNode nNext;
    
    // Constructor
    public CountryNode() 
    {
        nInfo = new Country();
        nNext = null;
    } // End of constructor
    
    // modifyMe method
    public void modifyMe(CountryNode thisNode)
    {
        nInfo.modifyMe(thisNode.nInfo);
        nNext = thisNode.nNext;
    } // End of modifyMe method
    
    // inputData method
    public void inputData(int x)
    {
        nInfo.inputData(x);
        nNext = null;
    } // End of inputData method
    
     // printMe method
    public String printMe()
    { return nInfo.printMe();} // End of printMe method
    
} // End of PatronNode class

    
