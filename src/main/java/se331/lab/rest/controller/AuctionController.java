package se331.lab.rest.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se331.lab.rest.entity.Auction;
import se331.lab.rest.service.AuctionService;
import se331.lab.rest.util.LabMapper;
@Controller
@RequiredArgsConstructor
public class AuctionController {

    final AuctionService auctionService;

    @GetMapping("/auctions")
    public ResponseEntity<?> getAuctionLists(
            @RequestParam(value = "_limit", required = false) Integer perPage,
            @RequestParam(value = "_page", required = false) Integer page,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "type", required = false) String type) {
        // Set default values for pagination
        perPage = (perPage == null || perPage <= 0) ? 10 : perPage; // Change to a reasonable default
        page = (page == null || page <= 0) ? 1 : page;
        Page<Auction> pageOutput;
        // Check for search conditions and retrieve auctions accordingly
        // Check for search conditions and retrieve auctions accordingly
        if (title != null) {
            // Fetch auctions by title if it's provided
            pageOutput = auctionService.getAuctionsByTitle(title, PageRequest.of(page - 1, perPage));
        } else if (description != null) {
            // Fetch auctions by description if it's provided
            pageOutput = auctionService.getAuctionsByDescription(description, PageRequest.of(page - 1, perPage));
        } else if (type != null) {
            // Fetch auctions by type if it's provided
            pageOutput = auctionService.getAuctionsByType(type, PageRequest.of(page - 1, perPage));
        } else {
            // Fetch all auctions if no filters are applied
            pageOutput = auctionService.getAllAuctions(perPage, page);
        }
        // Set the response header for total count
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("X-Total-Count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(LabMapper.INSTANCE.getAuctionDTO(pageOutput.getContent()), responseHeader, HttpStatus.OK);
    }

    @GetMapping("/auctions/{id}")
    public ResponseEntity<?> getAuction(@PathVariable("id") Long id) {
        Auction output = auctionService.getAuctionById(id);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getAuctionDTO(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given auction id is not found");
        }
    }

    // New endpoint to query AuctionItem where the highest (successful) bid is less than the specified value
    @GetMapping("/auctions/under-successful-bid")
    public ResponseEntity<?> getAuctionsWithSuccessfulBidUnder(@RequestParam(value = "value", defaultValue = "100") Double value) {
        return ResponseEntity.ok(LabMapper.INSTANCE.getAuctionDTO(auctionService.getAuctionsWithSuccessfulBidUnder(value)));
    }
}