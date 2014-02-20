
public class Item {
	private String name;
	private double resPrice;
	private boolean canBeAuctioned;
	
	public Item(String n, double p){
		name = n;
		resPrice=p;
		canBeAuctioned=true;
	}
	
	public String getName(){
		return name;
	}
	
	public double getReservedPrice(){
		return resPrice;
	}
	
	public boolean isAuctionable(){
		return canBeAuctioned;
	}
	
	public void lock(){
		canBeAuctioned=false;
	}

}
