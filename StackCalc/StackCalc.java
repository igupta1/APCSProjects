import java.util.List;		// used by expression evaluator

/**
 *	This program uses a stack for the operand and a stack for the operator 
 *  to solve expressions in the PEMDAS fashion.
 * 	The expressions and supplied by the user or by StackCalcTester. 
 *  In addition this program uses ArrayStack and uses an ArrayList to represent the stack. 
 *
 *	@author	Ishaan Gupta
 *	@since	3-3-20
 */
public class StackCalc 
{
	
	private ExprUtils utils;	// expression utilities
	private ArrayStack<Double> valueStack;		// value stack
	private ArrayStack<String> operatorStack;	// operator stack

	// constructor	
	public StackCalc() 
	{
		utils = new ExprUtils();
		valueStack = new ArrayStack<Double>();
		operatorStack = new ArrayStack<String>();
	}
	
	public static void main(String[] args) 
	{
		StackCalc sc = new StackCalc();
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
			
			char temp = token.charAt(0);
			
			if(!utils.isOperator(temp))
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
