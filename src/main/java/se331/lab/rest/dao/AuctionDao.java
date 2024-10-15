package se331.lab.rest.dao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.rest.entity.Auction;

import java.util.List;

public interface AuctionDao {
    List<Auction> getAllAuctions();
    Page<Auction> getAllAuctions(Integer pageSize, Integer page);
    Auction getAuctionById(Long id);
    // for querying
    Page<Auction> getAuctionsByTitle(String title, Pageable page);
    Page<Auction> getAuctionsByDescription(String description, Pageable page);
    Page<Auction> getAuctionsByType(String type, Pageable page);
    Page<Auction> getAuctionsByTitleOrDescription(String title, String description, Pageable page);
    Page<Auction> getAuctionsByTitleOrType(String title, String type, Pageable page);
    // saving
    Auction saveAuction(Auction auction);
}