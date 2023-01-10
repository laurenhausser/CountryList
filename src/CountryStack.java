
import javax.swing.JOptionPane; // This package facilitates dialog boxes, etc.

public class CountryStack
{
    // Global Data Items
    protected CountryNode nTop, nBottom;
    protected int sLength;
    
    // Constructor
    public CountryStack() 
    {
        nTop = nBottom = null;
        sLength = 0;
    } // End of Constructor
    
    // The push method
    public void push(Country thisCountry)
    {
        CountryNode newNode = new CountryNode();
        newNode.nInfo.modifyMe(thisCountry);
        newNode.nNext = nTop;
        nTop = newNode; // First.modifyMe(thisPatron);
        sLength++;
        if  (sLength == 1)nBottom = nTop; 
    } // End of push method
    
    // The pop method
    public Country pop()
    {
        CountryNode nCurrent = nTop;
        CountryNode nCurrentCopy = new CountryNode();
        nCurrentCopy.modifyMe(nCurrent);
        nTop = nTop.nNext;
        destroyMe(nCurrent); sLength--;
        return nCurrentCopy.nInfo;
    } // End of pop method
  
    // Node Modification method
    public void modifyMe(int pos, Country thisCountry)
    {
        // Find the modification point and modify the node there.
        int x = sLength;
        CountryNode nCurrent = nTop;
        //LibraryPatron[] thisList = toArray();
        while ((nCurrent.nNext != null)&&(x > pos)){nCurrent = nCurrent.nNext; x--;}
        if (x == pos)nCurrent.nInfo.modifyMe(thisCountry);
    } // End of Node Modification method
    
    // The getSize method
    public int getSize()
    {return sLength;}
        
    // The clearStack method
    public void clearStack()
    {
        CountryNode nCurrent = nTop;
        CountryNode nCurrentCopy;
        while (nCurrent != null){nCurrentCopy = nCurrent; nCurrent = nCurrent.nNext; destroyMe(nCurrentCopy);}
        nTop = nBottom = null; sLength = 0;
    } // End of the clearStack method
    
    // The getInfo method
    public Country getInfo(int pos)
    {
      // Find the retrieval point in the list and return the information at that location.
        int x = 1;
        Country[] thisList = toArray();
        return thisList[pos];
    } // End of getInfo method
    
    // The toArray method
    public Country[] toArrayCheat()
    {
        // Copy the information in each node to an array and return the array.
        int x = 1;
        Country[] patronArray = new Country[sLength];
        CountryNode nCurrent = nTop;
        for (x = sLength; ((nCurrent!= null) && (x >= 1)); x--)
        {
            patronArray[x-1] = new Country(); 
            patronArray[x-1].modifyMe(nCurrent.nInfo);
            nCurrent = nCurrent.nNext;
        }
        return patronArray;
    } // End of toArray method
    
    // The toArray method
    public Country[] toArray()
    {
        // Copy the information in each node to an array and return the array.
        int x = 1;
        Country[] thisList = new Country[sLength];
        int sLim = sLength; 
        
        // Load an array from the stack
        for (x = 1; x <= sLim; x++)
        {
            thisList[x-1] = new Country();
            thisList[x-1].modifyMe(pop()); 
        } // End of Array Load
       
        // Reload the stack from the array
        for (x = sLim; x >= 1; x--)
        { push(thisList[x-1]); } // End of Reload
        
        return thisList;
    } // End of toArray method
   
    // Cleanup method
    protected void finalize()throws java.lang.Throwable
    { destroyMe(this); }
   
    // Item Destruction method 
    protected void destroyMe(Object thisObj)
    {
      thisObj = null;
      System.gc(); // Call the Java garbage collector
    } // End of Item Destruction method 
    
} // End of PatronsCrudeStack class
