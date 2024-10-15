package se331.lab.rest.auctions;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class AuctionDTO {
    Long id;
    String title;
    String description;
    String type;
    List<AuctionBidDTO> bids = new ArrayList<>();
}