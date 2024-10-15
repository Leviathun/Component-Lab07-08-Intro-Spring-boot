package se331.lab.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se331.lab.rest.entity.Bid;

import java.util.List;

public interface BidRepository extends JpaRepository<Bid, Long> {

    // New method to query bids with amounts less than the provided value
    List<Bid> findByAmountLessThan(Double value);

}