package thinking.decorator;

// Whipped
// steamed milk(wet), foamed milk(dry)
// 

public class CoffeeShop {
	public static void main(String[] args) {
		// Cappuccino
		DrinkComponent cappuccino = new Espresso(new FoamedMilk(new Mug()));
		System.out.println(cappuccino.getDescription().trim() + ": $" + cappuccino.getTotalCost());

		// Mocha Cafe
		DrinkComponent cafeMocha = new Espresso(new SteamedMilk(new Chocolate(new Whipped(new Decaf(new Mug())))));
		System.out.println(cafeMocha.getDescription().trim() + ": $" + cafeMocha.getTotalCost());
	}
}

interface DrinkComponent {
	String getDescription();
	float getTotalCost();
}

class Mug implements DrinkComponent {
	public String getDescription() {
		return "mug";
	}

	public float getTotalCost() {
		return 0;
	}
}

abstract class Decorator implements DrinkComponent {
	protected DrinkComponent component;

	Decorator(DrinkComponent component) {
		this.component = component;
	}
	public float getTotalCost() {
		return component.getTotalCost();
	}

	public abstract String getDescription();
}

class Espresso extends Decorator {
	private float cost = 0.75f;
	private String description = " espresso";
	public Espresso(DrinkComponent component) {
		super(component);
	}
	public float getTotalCost() {
		return component.getTotalCost() + cost;
	}
	public String getDescription() {
		return component.getDescription() + description;
	}
}

class Decaf extends Decorator {
	private String description = " decaf";
	public Decaf(DrinkComponent component) {
		super(component);
	}

	public String getDescription() {
		return component.getDescription() + description;
	}
}

class FoamedMilk extends Decorator {
	private float cost = 0.25f;
	private String description = " foamed milk";
	public FoamedMilk(DrinkComponent component) {
		super(component);
	}
	public float getTotalCost() {
		return component.getTotalCost() + cost;
	}
	public String getDescription() {
		return component.getDescription() + description;
	}
}

class SteamedMilk extends Decorator {
	private float cost = 0.25f;
	private String description = " steamed milk";
	public SteamedMilk(DrinkComponent component) {
		super(component);
	}
	public float getTotalCost() {
		return component.getTotalCost() + cost;
	}

	public String getDescription() {
		return component.getDescription() + description;
	}
}

class Whipped extends Decorator {
	private float cost = 0.25f;
	private String description = " whipped cream";
	public Whipped(DrinkComponent component) {
		super(component);
	}
	public float getTotalCost() {
		return component.getTotalCost() + cost;
	}

	public String getDescription() {
		return component.getDescription() + description;
	}
}

class Chocolate extends Decorator {
	private float cost = 0.25f;
	private String description = " chocolate";
	public Chocolate(DrinkComponent component) {
		super(component);
	}
	public float getTotalCost() {
		return component.getTotalCost() + cost;
	}

	public String getDescription() {
		return component.getDescription() + description;
	}
}
