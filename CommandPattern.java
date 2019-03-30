import java.util.ArrayList;
import java.util.Iterator;

//
//The classes and/or objects participating in this pattern are:
//
//1. Command  (Command)
//		- declares an interface for executing an operation.
//2. ConcreteCommand  (CalculatorCommand)
//		- defines a binding between a Receiver object and an action.
//		- implements Execute by invoking the corresponding operation(s) on Receiver
//3. Client  (Calculator Application)
//		- creates a ConcreteCommand object and sets its receiver.
//4. Invoker  (User)
//		- asks the command to carry out the request
//5. Receiver  (Calculator)
//		- knows how to perform the operations associated with carrying out
//		  a request. Any class may serve as a Receiver.
//
//

//"Command"
//
interface Command {
	public void Execute();
}
//"ConcreteCommand"
//
class AdditionCommand implements Command {

	public AdditionCommand(Calculator calculator, int operand) { 
		_calculator = calculator;
		_operator = '+';
		_operand = operand;
	}
	@Override
	public void Execute() {
		// TODO Auto-generated method stub
		_calculator.Action(_operator, _operand);
	}

	private Calculator _calculator;
	private char _operator;
	private int _operand;
}
class SubtractionCommand implements Command {

	public SubtractionCommand(Calculator calculator, int operand) { 
		_calculator = calculator;
		_operator = '-';
		_operand = operand;
	}
	@Override
	public void Execute() {
		// TODO Auto-generated method stub
		_calculator.Action(_operator, _operand);
	}

	private Calculator _calculator;
	private char _operator;
	private int _operand;
}
class MultiplicationCommand implements Command {

	public MultiplicationCommand(Calculator calculator, double operand) { 
		_calculator = calculator;
		_operator = '*';
		_operand = operand;
	}
	@Override
	public void Execute() {
		// TODO Auto-generated method stub
		_calculator.Action(_operator, _operand);
	}

	private Calculator _calculator;
	private char _operator;
	private double _operand;
}
class DivisionCommand implements Command {

	public DivisionCommand(Calculator calculator, int operand) { 
		_calculator = calculator;
		_operator = '/';
		_operand = operand;
	}
	@Override
	public void Execute() {
		// TODO Auto-generated method stub
		_calculator.Action(_operator, _operand);
	}

	private Calculator _calculator;
	private char _operator;
	private int _operand;
}
class CircumferenceCommand implements Command {

	public CircumferenceCommand(Calculator calculator, int radius) { 
		_calculator = calculator;
		_operator = '*';
		_radius = radius;
		Command command = new AdditionCommand(_calculator, _radius);
		Add(command);
		command = new MultiplicationCommand(_calculator,2);
		Add(command);
		command = new MultiplicationCommand(_calculator,3.14);
		Add(command);
		
	}
	@Override
	public void Execute() {
		// TODO Auto-generated method stub
		for(int i = 0; i<sub_commands.size() ;i++) {
			sub_commands.get(i).Execute();
		}
				
	}
	public void Add(Command command) {
		sub_commands.add(command);
	}

	private Calculator _calculator;
	private char _operator;
	private int _radius;
	private ArrayList<Command> sub_commands = new ArrayList<Command>();
	private User user;
}
// "Receiver"
//
class Calculator {
	public Calculator() { 
		current_value = 0; 
	}
	public void Action(char _operator, double operand) {
		switch (_operator) {
		case '+': current_value += operand; break;
		case '-': current_value -= operand; break;
		case '*': current_value *= operand; break;
		case '/': current_value /= operand; break;
		}
		System.out.println("Current value " + current_value + " (following "
				+ _operator + " " + operand + ")");
	}
	private double current_value;
}

// "Invoker"
class User {
	public User() {	current = 0; }
	public void Redo(int levels) {
		System.out.println("\n---- Redo " + levels + " levels ");
		// Perform redo operations
		for (int i = 0; i < levels; i++) {
			if (current < _commands.size()) {
				Command command = _commands.get(current++);
				command.Execute();
			}
		}
	}

	void Compute(Command command) {
		command.Execute();
		// Add command to undo list
		_commands.add(command);
		current++;
	}

	// Initializers.
	private int current;
	private ArrayList<Command> _commands = new ArrayList<Command>();
};

public class CommandPattern {
	public static void main(String[] args) {

		// Create user and let her compute
		Command command = null;
		User user = new User();
		Calculator calculator = new Calculator();
		User user1 = new User();
		Calculator calculator1 = new Calculator();
		
		command = new CircumferenceCommand(calculator1, 5);
		user1.Compute(command);
		
		command = new AdditionCommand(calculator, 100);
		user.Compute(command);
		command = new SubtractionCommand(calculator, 50);
		user.Compute(command);
		command = new MultiplicationCommand(calculator,10);
		user.Compute(command);
		command = new DivisionCommand(calculator, 2);
		user.Compute(command);

		user.Redo(2);
	}
}
