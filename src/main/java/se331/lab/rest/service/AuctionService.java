package se331.lab.rest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.rest.entity.Auction;

import java.util.List;

public interface AuctionService {
    List<Auction> getAllAuctions(); // Keep this for non-paged access
    Page<Auction> getAllAuctions(Integer pageSize, Integer page);
    Auction getAuctionById(Long id);
    Page<Auction> getAuctionsByTitle(String title, Pageable page);
    Page<Auction> getAuctionsByDescription(String description, Pageable page);
    Page<Auction> getAuctionsByTitleOrDescription(String title, String description, Pageable page);
    // New method for searching by type
    Page<Auction> getAuctionsByType(String type, Pageable page);
    Auction saveAuction(Auction auction);
    public List<Auction> getAuctionsWithSuccessfulBidUnder(Double value);
}