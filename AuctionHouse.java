import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.hamcrest.CoreMatchers.*;




@RunWith(JUnit4.class)
public class AuctionHouse {
	public ExpectedException thrown= ExpectedException.none();
	private Bidder b1 = new Bidder();
	private Bidder b2 = new Bidder();
	private double bidVal1 = 10.0;
	private double bidVal2 = 5.0;
	private double resPrice = 20.0;
	private String lockedError = "Cannot start an auction with a locked item.";
	private String itemName = "Remote Controlled Pony";
	private Item pony = new Item(itemName, resPrice);
	Auction ponyAuction = new Auction(pony);

	@Test
	public void biddersShouldHaveUniqueIDs() {
		assertThat(b1.getId(), not(equalTo(b2.getId())));
	}
	
	@Test
	public void itemShouldHaveName() {
		assertThat(pony.getName(), equalTo(itemName));
	}
	
	@Test
	public void itemShouldHaveReservedPrice() {
		assertThat(pony.getReservedPrice(), equalTo(resPrice));
	}
	
	@Test
	public void auctionShouldHaveAssociatedItem() {
		assertThat(ponyAuction.getItem(), equalTo(pony));
	}
	
	
	@Test
	public void auctionIsInactiveBeforeStart() {
		assertThat(ponyAuction.isActive(), equalTo(false));
	}
	
	@Test
	public void bidderCannotSubmitBidForInactiveAuction() {
		thrown.expect(Exception.class);
		b1.createBid(5.0, ponyAuction);
	}
	
	@Test
	public void auctionIsActiveAfterStart() throws Exception {
		ponyAuction.startAuction();
		assertThat(ponyAuction.isActive(), equalTo(true));
	}
	
	@Test
	public void highestBidStartsAtZero() {
		assertThat(ponyAuction.getHighestBid(), equalTo(0.0));
	}
	
	@Test
	public void bidderCanSubmitBidForActiveAuction() throws Exception {
		ponyAuction.startAuction();
		b1.createBid(bidVal1, ponyAuction);
	}
	
	@Test
	public void highestBidShouldChangeIfHigherBidIsSubmitted() throws Exception {
		ponyAuction.startAuction();
		b1.createBid(bidVal1, ponyAuction);
		assertThat(ponyAuction.getHighestBid(), equalTo(bidVal1));
	}
	
	@Test
	public void bidHasBiddersNumber() throws Exception {
		ponyAuction.startAuction();
		b1.createBid(bidVal1, ponyAuction);
		assertThat(ponyAuction.getHighestBiddder().getId(), equalTo(b1.getId()));
	}
	
	@Test
	public void bidHasPrice() throws Exception {
		ponyAuction.startAuction();
		b1.createBid(bidVal1, ponyAuction);
		assertThat(ponyAuction.getHighestBid(), equalTo(bidVal1));
	}
	
	@Test
	public void bidderCanSubmitBidSmallerThanCurrentHighest() throws Exception {
		ponyAuction.startAuction();
		b2.createBid(bidVal2, ponyAuction);
	}
	
	@Test
	public void highestBidShouldNotChangeIfBidIsLessThanHighest() throws Exception {
		ponyAuction.startAuction();
		b1.createBid(bidVal1, ponyAuction);
		b2.createBid(bidVal2, ponyAuction);
		assertThat(ponyAuction.getHighestBid(), equalTo(bidVal1));
	}
	
	@Test
	public void auctionShouldFailIfHighestBidisLessThanResPriceAtClose() throws Exception {
		ponyAuction.startAuction();
		b1.createBid(bidVal1, ponyAuction);
		ponyAuction.endAuction();
		assertThat(ponyAuction.isSuccessful(), equalTo(false));
	}
	
	@Test
	public void auctionShouldSucceedIfHighestBidisMoreThanResPriceAtClose() throws Exception {
		ponyAuction.startAuction();
		b1.createBid(100*bidVal1, ponyAuction);
		ponyAuction.endAuction();
		assertThat(ponyAuction.isSuccessful(), equalTo(true));
	}
	
	@Test
	public void itemCanNotBeReauctioned() {
		try {
			ponyAuction.startAuction();
		} catch (Exception e1) {
			System.out.println(lockedError + "new");
		}
		b1.createBid(100*bidVal1, ponyAuction);
		ponyAuction.endAuction();
		thrown.expect(Exception.class);
		try {
			ponyAuction.startAuction();
		} catch (Exception e) {
			System.out.println(lockedError);
		}
	}

}
