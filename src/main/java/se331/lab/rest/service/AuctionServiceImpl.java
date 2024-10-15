package se331.lab.rest.service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se331.lab.rest.dao.AuctionDao;
import se331.lab.rest.entity.Auction;
import se331.lab.rest.entity.Bid;
import se331.lab.rest.service.AuctionService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuctionServiceImpl implements AuctionService {
    final AuctionDao auctionDao;
    @Override
    public List<Auction> getAllAuctions() {
        return auctionDao.getAllAuctions();
    }
    @Override
    public Page<Auction> getAllAuctions(Integer pageSize, Integer page) {
        return auctionDao.getAllAuctions(pageSize, page);
    }
    @Override
    public Auction getAuctionById(Long id) {
        return auctionDao.getAuctionById(id);
    }
    @Override
    public Page<Auction> getAuctionsByTitle(String title, Pageable page) {
        return auctionDao.getAuctionsByTitle(title, page);
    }
    @Override
    public Page<Auction> getAuctionsByDescription(String description, Pageable page) {
        return auctionDao.getAuctionsByDescription(description, page);
    }
    @Override
    public Page<Auction> getAuctionsByTitleOrDescription(String title, String description, Pageable page) {
        return auctionDao.getAuctionsByTitleOrDescription(title, description, page);
    }
    @Override
    public Page<Auction> getAuctionsByType(String type, Pageable page) {
        return auctionDao.getAuctionsByType(type, page); // Implemented in DAO
    }
    @Override
    public Auction saveAuction(Auction auction) {
        return auctionDao.saveAuction(auction);
    }

    // Method to get auctions where the highest successful bid is less than the specified value
    public List<Auction> getAuctionsWithSuccessfulBidUnder(Double value) {
        BigDecimal threshold = BigDecimal.valueOf(value);

        return auctionDao.getAllAuctions().stream()
                .filter(auction -> {
                    List<Bid> bids = auction.getBids();
                    Bid highestBid = null;

                    // Loop through all bids to find the highest one
                    for (Bid bid : bids) {
                        if (highestBid == null || bid.getAmount().compareTo(highestBid.getAmount()) > 0) {
                            highestBid = bid;
                        }
                    }

                    // Check if the highest bid is less than the specified value
                    return highestBid != null && highestBid.getAmount().compareTo(threshold) < 0;
                })
                .collect(Collectors.toList());
    }
}