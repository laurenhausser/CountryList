

import javax.swing.JOptionPane;

/**
 *
 * @author laurenhausser
 */
public class Country {
    final static String HEADING = "Country by Lauren Hausser";
    
    protected String cName;  //allows users to access certain things
    protected int cPopulation, cCode;
    protected double cGrossNatInc, cPerCapita;

    
 

 public Country()
{
    cName = "";
    cPopulation = 0;
    cCode = 0;
    cGrossNatInc = 0.00;
    cPerCapita = 0.00;
    
  
}//END of method

public void modifyMe(Country thisOne)
{
    cName = thisOne.cName;
    cPopulation = thisOne.cPopulation;
    cCode = thisOne.cCode;
    cGrossNatInc = thisOne.cGrossNatInc;
    cPerCapita = thisOne.cPerCapita;
    
    
}//END of method
public void inputData(int x)
{
   String inputName;
    
   int inputPopulation, inputCode;
   double inputGrossNatInc;
   
   inputCode = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter code for Country: ", HEADING, JOptionPane.QUESTION_MESSAGE));
   inputName = JOptionPane.showInputDialog(null, "Enter country name: ", HEADING, JOptionPane.QUESTION_MESSAGE);
   inputPopulation = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter population: ", HEADING, JOptionPane.QUESTION_MESSAGE));
   inputGrossNatInc = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter Gross National Income for Country: ", HEADING, JOptionPane.QUESTION_MESSAGE));
   //inputDeaths = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter deaths: ", HEADING, JOptionPane.QUESTION_MESSAGE));
   
   
   cName = inputName;
   cPopulation = inputPopulation; 
   cGrossNatInc = inputGrossNatInc;
   cPerCapita = cGrossNatInc / cPopulation;
   cCode = inputCode;
   
   
}//END of method
public String printMe()
{
    String printString;
    
    printString = "Country: " + cName + "-" + cCode + '\n'
            + "Population: " + cPopulation + '\n' 
            + "Gross National Income: " + cGrossNatInc + '\n'
            + "Per Capita: " + cPerCapita + '\n' + '\n';
    return printString;
    
}//END of method
public double getGrossNatInc()
{
    return cGrossNatInc; 
    
}//END of method
public double getPerCapita()
{
    return cPerCapita;
    
}//END of method

public int getPopulation()
{
    return cPopulation;

}//END of method
public int getCode()
{
    return cCode;

}//END of method
public void setGrossNatInc(double thisGrossNatInc)
{
    cGrossNatInc = thisGrossNatInc;
    if(cPopulation > 0)
        cPerCapita = cGrossNatInc / cPopulation;
 
}//END of method
}//END of method


