package se331.lab.rest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import se331.lab.rest.entity.Auction;

public interface AuctionRepository extends JpaRepository<Auction, Long> {
    // Searching for titles containing the given string
    Page<Auction> findByTitleContaining(String title, Pageable pageable);
    // Searching for descriptions containing the given string
    Page<Auction> findByDescriptionContaining(String description, Pageable pageable);
    // Searching for types containing the given string
    Page<Auction> findByTypeContaining(String type, Pageable pageable);
    // Searching for titles or descriptions containing the given strings
    Page<Auction> findByTitleContainingOrDescriptionContaining(String title, String description, Pageable pageable);
    // Searching for titles or types containing the given strings
    Page<Auction> findByTitleContainingOrTypeContaining(String title, String type, Pageable pageable);
}