package se331.lab.rest.dao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import se331.lab.rest.dao.AuctionDao;
import se331.lab.rest.entity.Auction;
import se331.lab.rest.repository.AuctionRepository;
import java.util.List;
@Repository
public class AuctionDaoImpl implements AuctionDao {
    final AuctionRepository auctionRepository;
    public AuctionDaoImpl(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }
    @Override
    public List<Auction> getAllAuctions() {
        return auctionRepository.findAll();
    }
    @Override
    public Page<Auction> getAllAuctions(Integer pageSize, Integer page) {
        return auctionRepository.findAll(PageRequest.of(page - 1, pageSize));
    }
    @Override
    public Auction getAuctionById(Long id) {
        return auctionRepository.findById(id).orElse(null);
    }
    @Override
    public Page<Auction> getAuctionsByTitle(String title, Pageable page) {
        return auctionRepository.findByTitleContaining(title, page);
    }
    @Override
    public Page<Auction> getAuctionsByDescription(String description, Pageable page) {
        return auctionRepository.findByDescriptionContaining(description, page);
    }
    @Override
    public Page<Auction> getAuctionsByType(String type, Pageable page) {
        return auctionRepository.findByTypeContaining(type, page);
    }
    @Override
    public Page<Auction> getAuctionsByTitleOrDescription(String title, String description, Pageable page) {
        return auctionRepository.findByTitleContainingOrDescriptionContaining(title, description, page);
    }
    @Override
    public Page<Auction> getAuctionsByTitleOrType(String title, String type, Pageable page) {
        return auctionRepository.findByTitleContainingOrTypeContaining(title, type, page);
    }
    @Override
    public Auction saveAuction(Auction auction) {
        return auctionRepository.save(auction);
    }
}