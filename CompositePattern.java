import java.util.ArrayList;

interface Car {
	void Add(Car d);
	void Display(int indent);
	public int getPrice();
	public String getName();
	double totalPrice(int indent);
}

class PrimitiveParts implements Car {
	private String name;
	public String getName() { return name;}
	
	private int price;
	public int getPrice() { return price;}
	
	public PrimitiveParts(String name, int price) {this.name = name; this.price=price;}
	
	public void Add(Car c) {
		System.out.println("Cannot add to a screw.");
	}
	
	public void Display(int indent) {
		for(int i = 1;i <= indent;i++) 	System.out.print("-");
		System.out.println(" "  + name);
	}
	// Since it's a leaf
	public double totalPrice(int indent) {
		return price;
	}

	
}

class CompositeParts implements Car {
	private	ArrayList<Car> parts = new ArrayList<Car>();
	
	private int price;
	public int getPrice() { return price;}
	
	private String name;
	public String getName() { return name;}
	public CompositeParts(String name, int price) {this.price = price; this.name=name;}
	
	public void Add(Car d) {parts.add(d);};
	
	public	void Display(int indent) {
		for(int i = 1;i <= indent;i++) System.out.print("-");
		System.out.println( "+ " + getName());
		
		// Display each child element on this node
		for (int i= 0; i< parts.size(); i++) {
			parts.get(i).Display(indent+2);
		}
}
	
	public double totalPrice(int indent) {
	        double total = 0.0;
	        for(int i = 1;i <= indent;i++)
	        total += getPrice();

	        for (int i= 0; i< parts.size(); i++) {
	            total += parts.get(i).totalPrice(indent);
	        }
	       
	        return total;
	    }
}

public class CompositePattern {
	public static void main(String[] args) {
	
		Car root = new CompositeParts("Car",10);
		root.Add(new PrimitiveParts("Chassis",10));
		root.Add(new PrimitiveParts("Cylinders",10));
		root.Add(new PrimitiveParts("Pedals",10));
		
		Car comp = new CompositeParts("Engine",10);
		comp.Add(new PrimitiveParts("Pistons",10));
		root.Add(comp);
		
		root.Display(1);
		System.out.println("Total Price = " + root.totalPrice(1));
		
	
		

	}
}
