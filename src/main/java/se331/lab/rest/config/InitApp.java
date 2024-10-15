package se331.lab.rest.config;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import se331.lab.rest.entity.*;
import se331.lab.rest.repository.*;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    final EventRepository eventRepository;
    final OrganizerRepository organizerRepository;
    final ParticipantRepository participantRepository;
    final AuctionRepository auctionRepository;
    final BidRepository bidRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        Organizer org1, org2, org3;
        org1 = organizerRepository.save(Organizer.builder()
                .name("CAMT").build());
        org2 = organizerRepository.save(Organizer.builder()
                .name("CMU").build());
        org3 = organizerRepository.save(Organizer.builder()
                .name("ChiangMai").build());

        // Participants
        Participant p1 = participantRepository.save(
                Participant.builder()
                        .name("Alice Johnson")
                        .telNo("123-456-7890")
                        .build()
        );
        Participant p2 = participantRepository.save(
                Participant.builder()
                        .name("Bob Smith")
                        .telNo("098-765-4321")
                        .build()
        );
        Participant p3 = participantRepository.save(
                Participant.builder()
                        .name("Carol White")
                        .telNo("555-123-4567")
                        .build()
        );
        Participant p4 = participantRepository.save(
                Participant.builder()
                        .name("David Green")
                        .telNo("111-222-3333")
                        .build()
        );
        Participant p5 = participantRepository.save(
                Participant.builder()
                        .name("Eve Black")
                        .telNo("222-333-4444")
                        .build()
        );

        Event tempEvent;
        tempEvent = eventRepository.save(Event.builder()
                .category("Academic")
                .title("Midterm Exam")
                .description("A time for taking the exam")
                .location("CAMT Building")
                .date("3rd Sept")
                .time("3.00-4.00 pm.")
                .petAllowed(false)
                .build());

        // Assign participants to the event
        tempEvent.setParticipants(List.of(p1, p2, p3));
        p1.getEventHistory().add(tempEvent);
        p2.getEventHistory().add(tempEvent);
        p3.getEventHistory().add(tempEvent);
        // Assign organizer
        tempEvent.setOrganizer(org1);
        org1.getOwnEvents().add(tempEvent);

        tempEvent = eventRepository.save(Event.builder()
                .category("Academic")
                .title("Commencement Day")
                .description ("A time for celebration")
                .location ("CMU Convention hall")
                .date ("21th Jan")
                .time("8.00am-4.00 pm.")
                .petAllowed(false)
                .build());

        // Assign participants to the event
        tempEvent.setParticipants(List.of(p2, p4, p5));
        p2.getEventHistory().add(tempEvent);
        p4.getEventHistory().add(tempEvent);
        p5.getEventHistory().add(tempEvent);
        // Assign organizer
        tempEvent.setOrganizer(org1);
        org1.getOwnEvents().add(tempEvent);

        tempEvent = eventRepository.save(Event.builder()
                .category ("Cultural")
                .title("Loy Krathong")
                .description("A time for Krathong")
                .location("Ping River")
                .date("21th Nov")
                .time("8.00-10.00 pm.")
                .petAllowed(false)
                .build());

        // Assign participants to the event
        tempEvent.setParticipants(List.of(p1, p3, p5));
        p1.getEventHistory().add(tempEvent);
        p3.getEventHistory().add(tempEvent);
        p5.getEventHistory().add(tempEvent);
        // Assign organizer
        tempEvent.setOrganizer(org2);
        org2.getOwnEvents().add(tempEvent);

        tempEvent = eventRepository.save(Event.builder()
                .category("Cultural")
                .title("Songkran")
                .description("Let's Play Water")
                .location("Chiang Mai Moat")
                .date("13th April")
                .time("10.00am - 6.00 pm.")
                .petAllowed(true)
                .build());

        // Assign participants to the event
        tempEvent.setParticipants(List.of(p3, p4, p5));
        p3.getEventHistory().add(tempEvent);
        p4.getEventHistory().add(tempEvent);
        p5.getEventHistory().add(tempEvent);
        // Assign organizer
        tempEvent.setOrganizer(org3);
        org3.getOwnEvents().add(tempEvent);

        // AUCTIONS
        Auction tempAuction;
        Bid tempBid1;
        Bid tempBid2;
        Bid tempBid3;

        // creator 1
        tempBid1 = bidRepository.save(
                Bid.builder()
                        .amount(BigDecimal.valueOf(55.99))
                        .dateTime("9th Jan : 10.00am")
                        .build()
        );
        tempBid2 = bidRepository.save(
                Bid.builder()
                        .amount(BigDecimal.valueOf(112.24))
                        .dateTime("9th Jan : 11.00am")
                        .build()
        );
        tempBid3 = bidRepository.save(
                Bid.builder()
                        .amount(BigDecimal.valueOf(100.00))
                        .dateTime("10th Jan : 01.00am")
                        .build()
        );
        tempAuction = auctionRepository.save(
                Auction.builder()
                        .title("Traditional Vase")
                        .description("Handle with care, do not drop, 2000 years old")
                        .type("Vase")
                        .build()
        );
        // setter
        tempAuction.setBids(List.of(tempBid1, tempBid2, tempBid3));
        tempBid1.setAuction(tempAuction);
        tempBid2.setAuction(tempAuction);
        tempBid3.setAuction(tempAuction);

        // creator 2
        tempBid1 = bidRepository.save(
                Bid.builder()
                        .amount(BigDecimal.valueOf(123.21))
                        .dateTime("10th Sep : 9.00am")
                        .build()
        );
        tempBid2 = bidRepository.save(
                Bid.builder()
                        .amount(BigDecimal.valueOf(456.24))
                        .dateTime("10th Sep : 11.00pm")
                        .build()
        );
        tempBid3 = bidRepository.save(
                Bid.builder()
                        .amount(BigDecimal.valueOf(99.56))
                        .dateTime("11th Sep : 09:03am")
                        .build()
        );
        tempAuction = auctionRepository.save(
                Auction.builder()
                        .title("Twin Tower Mini size")
                        .description("The exact moment we'll never forget")
                        .type("Model")
                        .build()
        );
        // setter
        tempAuction.setBids(List.of(tempBid1, tempBid2, tempBid3));
        tempBid1.setAuction(tempAuction);
        tempBid2.setAuction(tempAuction);
        tempBid3.setAuction(tempAuction);

        // creator 3
        tempBid1 = bidRepository.save(
                Bid.builder()
                        .amount(BigDecimal.valueOf(75.00))
                        .dateTime("10th Feb : 12.00pm")
                        .build()
        );
        tempBid2 = bidRepository.save(
                Bid.builder()
                        .amount(BigDecimal.valueOf(150.00))
                        .dateTime("10th Feb : 01.00pm")
                        .build()
        );
        tempBid3 = bidRepository.save(
                Bid.builder()
                        .amount(BigDecimal.valueOf(120.50))
                        .dateTime("11th Feb : 03.00am")
                        .build()
        );
        tempAuction = auctionRepository.save(
                Auction.builder()
                        .title("Antique Chair")
                        .description("Vintage chair from the early 1800s, needs careful handling")
                        .type("Chair")
                        .build()
        );
        // setter
        tempAuction.setBids(List.of(tempBid1, tempBid2, tempBid3));
        tempBid1.setAuction(tempAuction);
        tempBid2.setAuction(tempAuction);
        tempBid3.setAuction(tempAuction);

        // creator 4
        tempBid1 = bidRepository.save(
                Bid.builder()
                        .amount(BigDecimal.valueOf(95.50))
                        .dateTime("15th Mar : 09.00am")
                        .build()
        );
        tempBid2 = bidRepository.save(
                Bid.builder()
                        .amount(BigDecimal.valueOf(175.75))
                        .dateTime("15th Mar : 10.30am")
                        .build()
        );
        tempBid3 = bidRepository.save(
                Bid.builder()
                        .amount(BigDecimal.valueOf(130.00))
                        .dateTime("16th Mar : 02.00am")
                        .build()
        );
        tempAuction = auctionRepository.save(
                Auction.builder()
                        .title("Rare Painting")
                        .description("Original painting from a famous artist, handle with extreme care")
                        .type("Painting")
                        .build()
        );
        // setter
        tempAuction.setBids(List.of(tempBid1, tempBid2, tempBid3));
        tempBid1.setAuction(tempAuction);
        tempBid2.setAuction(tempAuction);
        tempBid3.setAuction(tempAuction);

        // creator 5
        tempBid1 = bidRepository.save(
                Bid.builder()
                        .amount(BigDecimal.valueOf(65.45))
                        .dateTime("20th Apr : 02.00pm")
                        .build()
        );
        tempBid2 = bidRepository.save(
                Bid.builder()
                        .amount(BigDecimal.valueOf(89.99))
                        .dateTime("20th Apr : 03.15pm")
                        .build()
        );
        tempBid3 = bidRepository.save(
                Bid.builder()
                        .amount(BigDecimal.valueOf(140.00))
                        .dateTime("21st Apr : 04.30am")
                        .build()
        );
        tempAuction = auctionRepository.save(
                Auction.builder()
                        .title("Ancient Sculpture")
                        .description("Intricate sculpture, over 1500 years old, requires delicate handling")
                        .type("Sculpture")
                        .build()
        );
        // setter
        tempAuction.setBids(List.of(tempBid1, tempBid2, tempBid3));
        tempBid1.setAuction(tempAuction);
        tempBid2.setAuction(tempAuction);
        tempBid3.setAuction(tempAuction);

        /*organizerRepository.save(Organizer.builder()
                .organizationName("CAMT")
                .address("CAMT Building")
                .build());
        organizerRepository.save(Organizer.builder()
                .organizationName("CMU")
                .address("CMU Convention hall")
                .build());
        organizerRepository.save(Organizer.builder()
                .organizationName("Chiang Mai")
                .address("Ping River")
                .build());
        organizerRepository.save(Organizer.builder()
                .organizationName("Chiang Mai Municipality")
                .address("Chiang Mai Moat")
                .build());*/
    }
}


