import java.util.List;		// used by expression evaluator

/**
 *	This program utilizes two stacks in order to solve a mathematical expression
 * 	either given by the user or supplied by StackCalcTester.java. This program
 *  uses the ArrayStack class to model a stack and ExprUtils to break mathematical
 * 	expressions into parts. 
 *
 *	@author	Sahil Goel
 *	@since	February 28, 2020
 */
public class StackCalc2 
{
	
	private ExprUtils utils;	// expression utilities
	
	private ArrayStack<Double> valueStack;		// value stack
	private ArrayStack<String> operatorStack;	// operator stack

	// constructor	
	public StackCalc2() 
	{
		utils = new ExprUtils();
		
		valueStack = new ArrayStack<Double>();
		operatorStack = new ArrayStack<String>();
	}
	
	public static void main(String[] args) 
	{
		StackCalc2 sc = new StackCalc2();
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
			else
				System.out.println(evaluateExpression(utils.tokenizeExpression(input)));
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
	
	/**
	 *	Evaluate expression and return the value
	 *	@param tokens	a List of String tokens making up an arithmetic expression
	 *	@return			a double value of the evaluated expression
	 */
	public double evaluateExpression(List<String> tokens) 
	{
		double value = 0;
		
		for (int i = 0; i < tokens.size(); i++)
		{
			String token = new String(tokens.get(i));
			char place = token.charAt(0);
			if(!utils.isOperator(place))
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
