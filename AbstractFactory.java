package lab6;

import java.util.ArrayList;

abstract class Part {
	abstract public String displayName();
	abstract double getPrice();
	protected double price;
	protected String size;
	abstract String getSize();

}
abstract class Socket extends Part {
	abstract public String displayName();
	abstract double getPrice();
	abstract String getSize();
}
abstract class Wrench extends Part {
	abstract public String displayName();
	abstract double getPrice();
	abstract String getSize();

}
class Socket_Metrics extends Socket {
	public Socket_Metrics(double p, String s) { 
		price = p;
		size = s;
		System. out.println("\tSocket_Metrics is created...");
	}
	public String displayName() { return new String("\tDewalt Socket"); }
	public double getPrice(){ return price; }
	public String getSize() {return size;}
}
class Wrench_Metrics extends Wrench{
	public Wrench_Metrics(double p, String s) { 
		price = p;
		size = s;
		System. out.println("\tWrench_Metrics is created...");
	}
	public String displayName() { return new String("\tStanley Wrench"); }
	public double getPrice(){ return price; }
	public String getSize() {return size;}
}

class Socket_Imperial extends Socket {
	public Socket_Imperial(double p, String s) { 
		price = p;
		size = s;
		System. out.println("\tSocket_Imperial is created...");
	}
	public String displayName() {
		return new String("\tBosch Socket");
	}
	public double getPrice(){
		return price;
	}
	public String getSize() {return size;}
}
class Wrench_Imperial extends Wrench {
	public Wrench_Imperial(double p, String s) { 
		price = p;
		size = s;
		System. out.println("\tWrench_Imperial is created...");
	}
	public String displayName() {
		return new String("\tFacom Wrench");
	}
	public double getPrice(){
		return price;
	}
	public String getSize() {return size;}
}

abstract class Custom extends Part {
	abstract public String displayName();
	abstract double getPrice();
	abstract String getSize();
}

class MyCustom_Socket extends Custom {
	public MyCustom_Socket(double p, String s) { 
		price = p;
		size = s;
		System. out.println("\tMyCustom_ Socket is created...");
	}
	public String displayName() {
		return new String("\tMySocket");
	}
	public double getPrice(){
		return price;
	}
	public String getSize() {return size;}
}
class MyCustom_Wrench extends Custom {
	public MyCustom_Wrench(double p, String s) { 
		price = p;
		size = s;
		System. out.println("\tMyCustom_Wrench is created...");
	}
	public String displayName() {
		return new String("\tMyWrench");
	}
	public double getPrice(){
		return price;
	}
	public String getSize() {return size;}
}

abstract class ToolkitFactory {
	abstract public Part createPart(String type);
}

class ImperialFactory extends ToolkitFactory {
	public Part createPart(String type) {
		if(type == "Socket") {
			return new Socket_Imperial(10000, "inch");
		}else
			return new Wrench_Imperial(20000, "inch");
	}
}
class MetricsFactory extends ToolkitFactory {
	public Part createPart(String type) {
		if(type == "Socket") {
			return new Socket_Metrics(10000, "mm");
		}else
			return new Wrench_Metrics(20000, "mm");
	}

}
class CustomFactory extends ToolkitFactory {
	public Part createPart(String type) {
		if(type == "Socket") {
			return new MyCustom_Socket(10000, "Custom Measurement");
		}else
			return new MyCustom_Wrench(20000, "Custom Measurement");
	}
}
class BuildToolkit {
	// Object creation is delegated to factory.
	public void buildToolkit(ToolkitFactory factory) {
		parts = new ArrayList<Part>();
		parts.add(factory.createPart("Socket"));
		parts.add(factory.createPart("Wrench"));

	}
	void displayParts() {
		System.out.println("\tCreating Toolkit\n\t-------------");
		parts.forEach(p  -> System.out.println(p.displayName() + 
				            " " + p.getPrice() +
				            " " + p.getSize()));
	}
	private ArrayList<Part> parts;
}

public class AbstractFactory{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 ImperialFactory imperial = new ImperialFactory();
		 MetricsFactory metrics = new MetricsFactory();
		 CustomFactory custom = new CustomFactory();
		 
		 BuildToolkit toolkit = new BuildToolkit();
		 System.out.println("Creating Imperial Toolkit");
		 toolkit.buildToolkit(imperial);
		 toolkit.displayParts();
		 
		 BuildToolkit toolkit1 = new BuildToolkit();
		 System.out.println("Creating Metrics Toolkit");
		 toolkit1.buildToolkit(metrics);
		 toolkit1.displayParts();
		  
		 BuildToolkit toolkit2 = new BuildToolkit();
		 System.out.println("Creating Custom Toolkit");
		 toolkit2.buildToolkit(custom);
		 toolkit2.displayParts();
	}

}
