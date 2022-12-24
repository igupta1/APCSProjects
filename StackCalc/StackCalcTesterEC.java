import java.util.ArrayList;
import java.util.List;

public class StackCalcTesterEC
{
	public static void main(String[] args) 
	{
		StackCalcTesterEC test = new StackCalcTesterEC();
		test.run();
	}
	
	public void run() 
	{
		ExprUtils utils = new ExprUtils();
		StackCalcEC stackIt = new StackCalcEC();
		String expr;
		
		System.out.println("\n\n");
		
		expr = "a = 2 + 3 * 5";
		double answer = stackIt.evaluateExpression(utils.tokenizeExpression(expr));
		System.out.printf("%s = %.4f%n%n", expr, answer);
		
		expr = "a - 2.1 + 3 * (5 - 4) + pi";
		answer = stackIt.evaluateExpression(utils.tokenizeExpression(expr));
		System.out.printf("%s = %.4f%n%n", expr, answer);
		
		expr = "x1 = 3.456 * 23 / (.5 - 23)";
		answer = stackIt.evaluateExpression(utils.tokenizeExpression(expr));
		System.out.printf("%s = %.4f%n%n", expr, answer);
		
		expr = "x = 54 + 0.12 * 3 - 4 / 5.6 - (2 ^ 3 - 4 * 8) + 1";
		answer = stackIt.evaluateExpression(utils.tokenizeExpression(expr));
		System.out.printf("%s = %.4f%n%n", expr, answer);
		
		expr = "a ^ pi - x ^ e + 2 * x - (x * 7.1 - 3 * (x + 2.3))";
		answer = stackIt.evaluateExpression(utils.tokenizeExpression(expr));
		System.out.printf("%s = %.4f%n%n", expr, answer);
		
		expr = "x = 27 - ((3 - 6 * 2) + 34 - a ^ 2 + a) - e";
		answer = stackIt.evaluateExpression(utils.tokenizeExpression(expr));
		System.out.printf("%s = %.4f%n%n", expr, answer);
		
		expr = "a = 2 ^ x + pi ^ e - 3.123 + e + (((x + e) + (e - pi) * 2.1) + 13.9)";
		answer = stackIt.evaluateExpression(utils.tokenizeExpression(expr));
		System.out.printf("%s = %.4f%n%n", expr, answer);
		
		expr = "x";
		answer = stackIt.evaluateExpression(utils.tokenizeExpression(expr));
		System.out.printf("%s = %.4f%n%n", expr, answer);
		
		System.out.println("\n\n");
	}
}
