import java.lang.Exception;

public class Auction {
	private boolean active;
	private Item auctionItem;
	private Bid currentBid;
	private boolean success;

	public Auction(Item i){
		active=false;
		auctionItem=i;
		currentBid = new Bid(0,null);
	}

	public boolean isActive(){
		return active;
	}
	
	public boolean isSuccessful(){
		return success;
	}
	
	public double getHighestBid(){
		return currentBid.getValue();
	}

	public Bidder getHighestBiddder(){
		return currentBid.getBidder();
	}

	public Item getItem(){
		return auctionItem;
	}

	public void startAuction() throws Exception{
		if(!auctionItem.isAuctionable()){
			throw new Exception();
		}
		if(active)
			System.out.println("\tAuction already in progress.");
		else
			active=true;
	}

	public void endAuction(){
		active=false;
		if (currentBid.getValue() >= auctionItem.getReservedPrice()){
			auctionItem.lock();
			success=true;
		}
		else
			success=false;
	}

	public void submitBid(Bid newBid) throws Exception{
		if(active){
			if(newBid.getValue() > currentBid.getValue()){
				currentBid = newBid;
			}
		}
		else
			throw new Exception();
	}
}
