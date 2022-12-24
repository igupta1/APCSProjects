import java.util.List;		// used by expression evaluator
import java.util.ArrayList;

/**
 *	This program is an extension of StackCalc.java, adding stored values
 * 	or variables that can be used repeatedly throughout the runtime of the 
 * 	code.
 * 
 *	@author	Sahil Goel
 *	@since	February 28, 2020
 */
public class StackCalcEC3
{
	
	private ExprUtils utils;	// expression utilities
	
	private ArrayStack<Double> valueStack;		// value stack
	private ArrayStack<String> operatorStack;	// operator stack

	private ArrayList<StackCalcIdentifier> identifiers;

	// constructor	
	public StackCalcEC3() 
	{
		utils = new ExprUtils();
		
		valueStack = new ArrayStack<Double>();
		operatorStack = new ArrayStack<String>();
		
		identifiers = new ArrayList<StackCalcIdentifier>();
		addConstants();
	}
	
	public void addConstants()
	{
		identifiers.add(new StackCalcIdentifier("pi", Math.PI));
		identifiers.add(new StackCalcIdentifier("e", Math.E));
	}
	
	public static void main(String[] args) 
	{
		StackCalcEC3 sc = new StackCalcEC3();
		sc.run();
	}
	
	public void run() 
	{
		System.out.println("\n\n\nWelcome to StackCalc!!!");
		runCalc();
		System.out.println("\nThanks for using StackCalc! Goodbye.\n\n\n");
	}
	
	/**
	 *	Prompt the user for expressions, run the expression evaluator,
	 *	and display the answer.
	 */
	public void runCalc() 
	{
		boolean end = false;
		
		while(!end)
		{
			String input = Prompt.getString("\n-> ");
			
			if(input.equals("q"))
				end = true;
			else if(input.equals("h"))
				printHelp();
			else if(input.equals("l"))
				printIdentifiers();
			else
				System.out.println(evaluateExpression(utils.tokenizeExpression(input)));
		}
		
	}
	
	/**	Print help */
	public void printHelp() 
	{
		System.out.println("Help:");
		System.out.println("  h - this message\n  q - quit\n  l - print identifiers\n");
		System.out.println("Expressions can contain:");
		System.out.println("  previously defined identifiers");
		System.out.println("  integers or decimal numbers");
		System.out.println("  arithmetic operators +, -, *, /, %, ^");
		System.out.println("  parentheses '(' and ')'");
	}
	
	public void printIdentifiers()
	{
		System.out.println("Variables:");
		
		for(StackCalcIdentifier sCI: identifiers)
		{
			sCI.printIdentifier();
		}
	}
	
	/**
	 *	Evaluate expression and return the value
	 *	@param tokens	a List of String tokens making up an arithmetic expression
	 *	@return			a double value of the evaluated expression
	 */
	public double evaluateExpression(List<String> tokens) 
	{
		double value = 0;
		boolean storing = false;
		String storeIn = new String();
		
		for(int i = 0; i < tokens.size(); i++)
		{
			String token = new String(tokens.get(i));
			
			for(int x = 0; x < tokens.size() - 1; x++)
			{
				char place = tokens.get(x).charAt(0);
				char place1 = tokens.get(x + 1).charAt(0);
				
				if((place >= 65 && place <= 90 || place >= 97 && place <= 122) && !utils.isOperator(place1))
				{
					return 0;
				}
			}
			
			char place = token.charAt(0);
			if(place >= 65 && place <= 90 || place >= 97 && place <= 122)
			{
				if(i == 0 && i + 1 == tokens.size())
				{
					for(StackCalcIdentifier sCI: identifiers)
					{
						if(sCI.getName().equals(token))
							return sCI.getValue();
					}
					identifiers.add(new StackCalcIdentifier(token, 0));
					return 0;
				}
				else if(i == 0 && tokens.get(i + 1).equals("="))
				{
					storing = true;
					
					storeIn = tokens.remove(0);
					tokens.remove(0);
					i = -1;
				}
				else
				{
					for(StackCalcIdentifier sCI: identifiers)
					{
						if(sCI.getName().equals(token))
						{
							valueStack.push(sCI.getValue());
						}
					}
				}
				
			}
			else if(!utils.isOperator(place))
			{
				valueStack.push(Double.parseDouble(token));
			}
			else
			{
				if(operatorStack.isEmpty() || token.equals("("))
				{
					operatorStack.push(token);
				}
				else if(token.equals(")"))
				{
					while(!operatorStack.peek().equals("("))
					{
						evaluateMiddle();
					}
					
					operatorStack.pop();
				}
				else
				{
					if(hasPrecedence(token, operatorStack.peek()))
					{
						while(!operatorStack.isEmpty() && hasPrecedence(token, operatorStack.peek()))
						{
							evaluateMiddle();
						}
					}
					
					operatorStack.push(token);
					
				}
				
			}
			
			
		}
		
		while(!operatorStack.isEmpty())
		{
			evaluateMiddle();
		}
		
		value = valueStack.pop();
		
		if(storing)
		{
			boolean found = false;
			
			for(StackCalcIdentifier sCI: identifiers)
			{
				if(sCI.getName().equals(storeIn))
				{
					sCI.setValue(value);
					found = true;
				}
			}
			if(!found)
			{
				identifiers.add(new StackCalcIdentifier(storeIn, value));
			}
			
			System.out.print(storeIn + " = ");
		}
		
		return value;
	}
	
	/**
	 *	Evaluate a singular expression and change the values in operatorStack
	 * 	and valueStack appropriately.
	 */
	public void evaluateMiddle()
	{
		double second = valueStack.pop();
		double first = valueStack.pop();
		
		switch(operatorStack.pop())
		{
			case "+":
				valueStack.push(first + second);
				break;
			case "-":
				valueStack.push(first - second);
				break;
			case "/":
				valueStack.push(first/second);
				break;
			case "*":
				valueStack.push(first*second);
				break;
			case "^":
				valueStack.push(Math.pow(first,second));
				break;
			case "%":
				valueStack.push(first % second);
				break;
		}
	}
	/**
	 *	Precedence of operators
	 *	@param op1		operator 1
	 *	@param op2		operator 2
	 *	@return			true if op2 has higher or same precedence as op1; false otherwise
	 *	Algorithm:
	 *		if op1 is exponent, then false
	 *		if op2 is either left or right parenthesis, then false
	 *		if op1 is multiplication or division or modulus and 
	 *				op2 is addition or subtraction, then false
	 *		otherwise true
	 */
	private boolean hasPrecedence(String op1, String op2) 
	{
		if (op1.equals("^")) 
			return false;
		if (op2.equals("(") || op2.equals(")")) 
			return false;
		if ((op1.equals("*") || op1.equals("/") || op1.equals("%")) && (op2.equals("+") || op2.equals("-")))
			return false;
		return true;
	}
}
