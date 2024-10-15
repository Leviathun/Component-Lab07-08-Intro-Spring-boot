package se331.lab.rest.auctions;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class BidDTO {
    Long id;
    BigDecimal amount;
    String dateTime;
    BidToAuctionDTO auction;
}