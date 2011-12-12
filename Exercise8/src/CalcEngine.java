import java.util.HashSet;

/**
 * The main part of the calculator doing the calculations.
 * 
 * @author  David J. Barnes and Michael Kolling 
 * @version 2008.03.30
 */
public class CalcEngine
{
    // The calculator's state is maintained in three fields:
    //     buildingDisplayValue, haveLeftOperand, and lastOperator.
    
    // Are we already building a value in the display, or will the
    // next digit be the first of a new one?
    private boolean buildingDisplayValue;

    // The current value (to be) shown in the display.
    private int displayValue;

    private HashSet<Integer> leftSet = new HashSet<Integer>();
    private HashSet<Integer> rightSet = new HashSet<Integer>();
    private HashSet<Integer> solution = new HashSet<Integer>();
    private String operator;

    /**
     * Create a CalcEngine.
     */
    public CalcEngine()
    {
        clear();
    }

    /**
     * @return The value that should currently be displayed
     * on the calculator display.
     */
    public int getDisplayValue()
    {
        return displayValue;
    }

    /**
     * A number button was pressed.
     * Either start a new operand, or incorporate this number as
     * the least significant digit of an existing one.
     * @param number The number pressed on the calculator.
     */
    public void numberPressed(int number)
    {
        if(buildingDisplayValue) {
            // Incorporate this digit.
            displayValue = displayValue*10 + number;
        }
        else {
            // Start building a new number.
            displayValue = number;
            buildingDisplayValue = true;
        }
    }

    
    /**
     * The '=' button was pressed.
     */
    public void equals()
    {
    	if(operator == "+") {
    		solution.addAll(rightSet);
    		solution.addAll(leftSet);	
    	}
    	else if(operator == "-") {
    		leftSet.removeAll(rightSet);
    		solution = leftSet;
    	}
    	else if(operator == "inter") {
    		for(Integer number : leftSet) {
    			if(rightSet.contains(number)) {
    				solution.add(number);
    			}
    		}
    	}
		
    }

    /**
     * The 'C' (clear) button was pressed.
     * Reset everything to a starting state.
     */
    public void clear()
    {
        buildingDisplayValue = false;
        displayValue = 0;
    }
    
    public void initialize() {
    	solution.clear();
    	rightSet.clear();
    	leftSet.clear();
    	operator = "";
    	clear();
    }

    /**
     * @return The title of this calculation engine.
     */
    public String getTitle()
    {
        return "Java Calculator";
    }


    /**
     * @return The version number of this engine.
     */
    public String getVersion()
    {
       return "Version 1.0";
    }

    
    public void pushToSet(String number, boolean set) {
    	Integer i = new Integer(number);
    	if(set) {
    	leftSet.add(i);
    	}
    	else {
    	rightSet.add(i);
    	}
    }
    
    public void setOperator(String op) {
    	operator = op;
    }
    
    public String getSet(boolean isLeftOperand) {
    	if(isLeftOperand) {
    		return leftSet.toString();
    	}
    	else {
    		return rightSet.toString();
    	}
    }
    
    public String getSolution() {
    	return solution.toString();
    }
}
