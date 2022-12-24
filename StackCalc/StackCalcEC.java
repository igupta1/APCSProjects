import java.util.List;		// used by expression evaluator		// used by expression evaluator
import java.util.ArrayList;

/**
 *	This program is an extra credit program that adds on to StackCalc
 * 	In this program we add stored variables to StackCalc
 * 	We can reference these stored variables in later expressions.
 * 
 *	@author	Ishaan Gupta
 *	@since	3-3-20
 */
public class StackCalcEC 
{
	
	private ExprUtils utils;	// expression utilities
	private ArrayStack<Double> valueStack;		// value stack
	private ArrayStack<String> operatorStack;	// operator stack
	
	private ArrayList<StackCalcIdentifier> identifiers;

		
	public StackCalcEC()
	{
		utils = new ExprUtils();
		valueStack = new ArrayStack<Double>();
		operatorStack = new ArrayStack<String>();
		
		identifiers = new ArrayList<StackCalcIdentifier>();
		addEandPi();
	}
	
	public void addEandPi()
	{
		identifiers.add(new StackCalcIdentifier("pi", Math.PI));
		identifiers.add(new StackCalcIdentifier("e", Math.E));
	}
	
	public static void main(String[] args) 
	{
		StackCalcEC sc = new StackCalcEC();
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
		boolean finish = false;
		
		while(!finish)
		{
			String user = Prompt.getString("\n-> ");
			
			if(user.equals("q"))
				finish = true;
			else if(user.equals("h"))
				printHelp();
			else if(user.equals("l"))
				printIdentifiers();
			else
				System.out.println(evaluateExpression(utils.tokenizeExpression(user)));
		}
		
	}
	
	/**	Print help */
	public void printHelp() 
	{
		System.out.println("Help:");
		System.out.println("  h - this message\n  q - quit\n");
		System.out.println("Expressions can contain:");
		System.out.println("  integers or decimal numbers");
		System.out.println("  arithmetic operators +, -, *, /, %, ^");
		System.out.println("  parentheses '(' and ')'");
	}
	
	public void printIdentifiers()
	{
		System.out.println("Variables:");
		
		for(int i = 0; i < identifiers.size(); i++)
		{
			identifiers.get(i).printValue();
		}
	}
	
	/**
	 *	Evaluate expression and return the value
	 *	@param tokens	a List of String tokens making up an arithmetic expression
	 *	@return			a double value of the evaluated expression
	 */
	public double evaluateExpression(List<String> tokens) 
	{
		boolean isStored = false;
		
		String stored = new String();
		
		double value = 0;
		
		
		
		for (int i = 0; i < tokens.size(); i++)
		{
			String token = new String(tokens.get(i));
			
			for(int j = 0; j < tokens.size() - 1; j++)
			{
				char temp = tokens.get(j).charAt(0);
				char temp1 = tokens.get(j + 1).charAt(0);
				
				if(!utils.isOperator(temp1) && (temp >= 65 && temp <= 90 || temp >= 97 && temp <= 122))
				{
					return 0;
				}
			}
			
			char temp = token.charAt(0);
			
			if(temp >= 65 && temp <= 90 || temp >= 97 && temp <= 122)
			{
				if(i + 1 == tokens.size() && i==0)
				{
					for(int q = 0; q < identifiers.size(); q++)
					{
						if(identifiers.get(q).getName().equals(token))
							return identifiers.get(q).getValue();
					}
					identifiers.add(new StackCalcIdentifier(token, 0));
					return 0;
				}
				else if(i == 0 && tokens.get(i + 1).equals("="))
				{
					isStored = true;
					
					stored = tokens.remove(0);
					tokens.remove(0);
					i = -1;
				}
				else
				{
					for(int r = 0; r < identifiers.size(); r++)
					{
						if(identifiers.get(r).getName().equals(token))
						{
							valueStack.push(identifiers.get(r).getValue());
						}
					}
				}
				
			}
			
			
			else if(!utils.isOperator(temp))
			{
				valueStack.push(Double.parseDouble(token));
			}
			
			else
			{
				if(token.equals("(") || operatorStack.isEmpty())
				{
					operatorStack.push(token);
				}
				else if(token.equals(")"))
				{
					while(!(operatorStack.peek().equals("(")))
					{
						process();
					}	
					operatorStack.pop();
				}
				else
				{
					if(hasPrecedence(token, operatorStack.peek()))
					{
						while(!operatorStack.isEmpty() && hasPrecedence(token, operatorStack.peek()))
						{
							process();
						}
					}
					
					operatorStack.push(token);
					
				}
				
			}
			
			
		}
		
		while(!operatorStack.isEmpty())
		{
			process();
		}
		
		value = valueStack.pop();
		
		if(isStored)
		{
			boolean found = false;
			
			for(int d = 0; d < identifiers.size(); d++)
			{
				if(identifiers.get(d).getName().equals(stored))
				{
					identifiers.get(d).setValue(value);
					found = true;
				}
			}
			if(!found)
			{
				identifiers.add(new StackCalcIdentifier(stored, value));
			}
			
			System.out.print(stored + " = ");
		}
		
		return value;
	}
	
	/**
	 *	Solves a simple expression and based on the outcomes operatorStack
	 * 	and valueStack are changed.
	 */
	public void process()
	{
		double second = valueStack.pop();
		double first = valueStack.pop();
		
		String temp = operatorStack.pop();
		
		if (temp.equals("+"))
			valueStack.push(first + second);
		else if (temp.equals("-"))
			valueStack.push(first - second);
		else if (temp.equals("*"))
			valueStack.push(first * second);
		else if (temp.equals("/"))
			valueStack.push(first / second);
		else if (temp.equals("^"))
			valueStack.push(Math.pow(first, second));
		else if (temp.equals("%"))
			valueStack.push(first % second);
		
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
