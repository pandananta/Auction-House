
public class Bid {
	private double value;
	private Bidder name;
	
	public Bid(double v, Bidder b){
		value = v;
		name = b;
	}
	
	public double getValue(){
		return value;
	}
	
	public Bidder getBidder(){
		return name;
	}
}
