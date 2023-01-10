

//import static country_hausserl.Country.HEADING;
import javax.swing.JOptionPane;
import java.util.Stack;
/**
 *
 * @author laurenhausser
 */
public class CountryMonitor {

    public static CountryStack countryList;
    static Country highestPerCapita, lowestPerCapita;
    static double totalPerCapita, averagePerCapita, stdDevCases;
    static double totalCases, totalDeaths;
    final static String HEADING = "The Countries Monitor of Lauren Hausser";
    public static String input;
    public static final int DEFAULT_NUMBER = 0;    
            
public static void main(String[] args)
{
        boolean exitTime = false; 
        int userOption, countryListSize;
        initialize();
        
        while (!exitTime)
        {
            input = JOptionPane.showInputDialog(null, "1. Enter Country Information \n"
                    + "2. Query a Country \n"
                    + "3. List Countries \n"
                    + "4. Sort Countries by PCI \n"
                    + "5. Pop Countries \n"
                    + "6. Summarize Countries \n"
                    + "7. Check size of stack \n"
                    + "8. Empty the stack \n"
                    + "9. Exit ", HEADING, JOptionPane.QUESTION_MESSAGE);
            userOption = Integer.parseInt(input);
            
            countryListSize = countryList.getSize();
            Object[] countryArray = null;
            
            
            switch (userOption)
            {
                case 1: {inputCountries(); break;}
                case 2: {if (countryListSize > 0) queryCountry(countryList); break;}
                case 3: {displayList(countryList); break;}
                case 4: {sortCountries(countryList); break;}
                case 5: {removeCountries(); break;}
                case 6: {summarizeCountries(countryList); break;}
                case 7: {checkSize(); break;}
                case 8: {emptyList(); break;}
                case 9: {exitTime = true; break;}
            }
            
        }
        
        
}//End of method
public static void initialize()
{
    countryList = new CountryStack();
    
    highestPerCapita = new Country();
    lowestPerCapita = new Country();
    
    highestPerCapita.setGrossNatInc(Double.MIN_VALUE);
    lowestPerCapita.setGrossNatInc(Double.MAX_VALUE);
    
    totalPerCapita = stdDevCases =0.0;
    averagePerCapita = 0.0;
}//End of method

public static void inputCountries()
{
    int x, cLimit;
    Country currentCountry;
    cLimit = Integer.parseInt(JOptionPane.showInputDialog(null, "How many Countries would you like to enter? ", HEADING, JOptionPane.QUESTION_MESSAGE));
    
    for (x = 1; x <= cLimit; x++ )
    {
        currentCountry = new Country();
        currentCountry.inputData(x-1);
        countryList.push(currentCountry);
    }
    
}//End of method
public static double standardDev (CountryStack thisCountry, int thisLimt, double thisAverage)
{
    int thisLimit = thisCountry.getSize();
    double difference = 0.0 ;
    double tDifference = 0.0 ;
    double standard = 0.0;
    
    for(int i = 1; i <= thisLimit; i++){
        difference = Math.pow(((Country) thisCountry.getInfo(i-1)).getPerCapita() - thisAverage, 2);
        tDifference += difference; 
        
    }
     standard = Math.sqrt(tDifference / thisLimit);
    return standard;
} //End of method

public static void highLow (Country thisOne)
{
    
   if (thisOne.getPerCapita() > highestPerCapita.getPerCapita()) highestPerCapita.modifyMe(thisOne);
   if (thisOne.getPerCapita() < lowestPerCapita.getPerCapita()) lowestPerCapita.modifyMe(thisOne);
   
         
   
}//End of method

public static void summarizeCountries (CountryStack thisList)
{
    String outputS = " ";
    int totalCases = 0 , totalPopulation = 0;
    int x, thisLimit;
    
    thisLimit = thisList.getSize();
    highestPerCapita = new Country();
    lowestPerCapita = new Country();
    
    highestPerCapita.setGrossNatInc(Double.MIN_VALUE);
    lowestPerCapita.setGrossNatInc(Double.MAX_VALUE);
    
    totalPerCapita = stdDevCases =0.0;
    averagePerCapita = 0.0;
    
    
    for(x = 1; x < thisLimit; x++){
        totalPerCapita += ((Country) thisList.getInfo(x-1)).getPerCapita();
        totalPopulation += ((Country)thisList.getInfo(x-1)).getPopulation();
        highLow((Country)thisList.getInfo(x-1));
    }
    
    averagePerCapita = totalPerCapita * 100 / totalPopulation;
    stdDevCases = standardDev(thisList, thisLimit, averagePerCapita);
    
    outputS = "Summary of COVID impact on countries in our list is as follows: \n"
        + "Per Capita in Countries:  \n" + highestPerCapita.printMe() + "\n"
        + " \n Total Per Capita: " + totalPerCapita + "\n" 
        + "Average Global Per Capita: " + averagePerCapita + "\n" 
        + "Global Standard Deviation: " + stdDevCases; 
 JOptionPane.showInputDialog(null, outputS, HEADING, JOptionPane.INFORMATION_MESSAGE); 
}//End of method


public static void sortCountries(CountryStack inList){
     int x, counter1, counter2;
    int thisLim = inList.getSize();
    Country dummyC = new Country();
    //inList.add(thisList);
    
    //Sort the array based on PCI
    for(counter1 = 1; counter1 < thisLim; counter1++){
        for(counter2 = counter1+1; counter2<thisLim; counter2++){
            if (((Country) inList.getInfo(counter1 - 1)).getPerCapita() >((Country) inList.getInfo(counter2 - 1)).getPerCapita()){
                dummyC.modifyMe((Country) inList.getInfo(counter1 - 1));
                ((Country) inList.getInfo(counter1 - 1)).modifyMe((Country) inList.getInfo(counter2 - 1));
                ((Country)inList.getInfo(counter2 - 1)).modifyMe(dummyC);
            }
        }
    }
    displayList(inList); 
       

}

public static void displayList(CountryStack thisList)
{
      int x, thisLim;
    String outputS = "";
    thisLim = thisList.getSize();
    
    for(x = 1; x <= thisLim; x++){
        outputS += ((Country)thisList.getInfo(x-1)).printMe();
    }
    JOptionPane.showMessageDialog(null, outputS, HEADING, JOptionPane.INFORMATION_MESSAGE);
}//End of method

public static void queryCountry(CountryStack thisList)
{
    boolean exitNow = false;
    String outString;
    int searchCode , nextUserAction;
    String heading = "Country Code Query";
    Country foundCode;
    int key; 
    
      
     if (thisList != null) // If there are patrons 
    {
    
    
    
     while (!exitNow) // While more processing required 
      {
         
         key  = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Code for Country to search:", heading, JOptionPane.QUESTION_MESSAGE));
        foundCode = new Country();
        foundCode.modifyMe(findCode(thisList, key));
     
        // Output the result of the search
        if (foundCode.getCode() != DEFAULT_NUMBER)outString = foundCode.printMe();
        else outString = "Library patron specified is not in the list.";
        JOptionPane.showMessageDialog(null, outString, heading, JOptionPane.INFORMATION_MESSAGE);
        
        // Prompt user whether to continue or not
        String More = JOptionPane.showInputDialog(null, "Press any key to continue or X to exit Country Query", heading, JOptionPane.QUESTION_MESSAGE);
        char exitKey = More.charAt(0);
        if (exitKey == 'X' || exitKey == 'x') exitNow = true;
      }
    }
}
public static Country findCode(CountryStack thisList, int key)
    {
      // Declarations
      Country Found = new Country();
      boolean exitNow = false;
    
      // Search ThisList for the LibraryPatron object, then return it
      for (int x = 1; ((x <= thisList.getSize()) && (!exitNow)); x += 1)
      {
        if (key == ((Country)thisList.getInfo(x-1)).getCode()) 
        {
          Found.modifyMe((Country)thisList.getInfo(x-1));
          exitNow = true;
        } 
      } // End For 
      return Found;
    } 
public static void checkSize(){
    String outputS;
    outputS = "The size of the list is " + countryList.getSize();
    JOptionPane.showMessageDialog(null,outputS , HEADING, JOptionPane.INFORMATION_MESSAGE);
}
public static void emptyList(){
    int x, nextUserAction;
        String removalPrompt = "You are about to empty the list. " + "Click Yes to Empty. Click No or Cancel to exit.";
        nextUserAction = JOptionPane.showConfirmDialog(null, removalPrompt);
        if (nextUserAction == JOptionPane.YES_OPTION) countryList.clearStack();
}

public static void removeCountries()
    {
        String removalPrompt, removalHeading = "Removal of Items from the List";
        int x, rStart, rStop, nextUserAction;
        
        // Prompt for range of items to be removed
        rStart = Integer.parseInt(JOptionPane.showInputDialog(null, "Starting Position: ", removalHeading, JOptionPane.QUESTION_MESSAGE));
        rStop = Integer.parseInt(JOptionPane.showInputDialog(null, "Ending Position: ", removalHeading, JOptionPane.QUESTION_MESSAGE));
        while ((rStop < rStart) || (rStart < 0)) // While invalid range
        {
            JOptionPane.showMessageDialog(null, "Invalid range specified", removalHeading, JOptionPane.ERROR_MESSAGE);
            rStart = Integer.parseInt(JOptionPane.showInputDialog(null, "Starting Position: ", removalHeading, JOptionPane.QUESTION_MESSAGE));
            rStop = Integer.parseInt(JOptionPane.showInputDialog(null, "Ending Position: ", removalHeading, JOptionPane.QUESTION_MESSAGE));
        } // End of While invalid range
        
        // Allow user to confirm removal of items
        removalPrompt = "Items " + rStart + " to " + rStop + " are about to be removed from the list.\n" + "Click Yes to remove the items. Click No or Cancel to exit.";
        nextUserAction = JOptionPane.showConfirmDialog(null, removalPrompt);
        if (nextUserAction == JOptionPane.YES_OPTION)
        {for (x = rStart; x < rStop; x++){
            countryList.pop();
                    
        
        }
        }
    }
}
//End of method
//End of class

