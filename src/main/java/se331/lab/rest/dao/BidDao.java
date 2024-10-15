package se331.lab.rest.dao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.rest.entity.Bid;

import java.util.List;
import java.util.Optional;

public interface BidDao {
    Page<Bid> getBids(Pageable pageable);
    Optional<Bid> findById(Long id);
    // New method to query bids with amounts less than the provided value
    public List<Bid> findByAmountLessThan(Double value);
}