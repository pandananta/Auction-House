import java.util.ArrayList;


public class Bidder {
	private String id;
	ArrayList<Bid> bids = new ArrayList<Bid>();
	
	public Bidder(){
		id = java.util.UUID.randomUUID().toString();
	}
	
	public String getId(){
		return id;
	}
	
	public void createBid(double value, Auction a){
		Bid myBid = new Bid(value,this);
		bids.add(myBid);
		try {
			a.submitBid(myBid);
		} catch (Exception e) {
			System.out.println("Inactive auction Exception.");
		}
	}
}
