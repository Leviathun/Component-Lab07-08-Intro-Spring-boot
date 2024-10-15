package se331.lab.rest.service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se331.lab.rest.dao.BidDao;
import se331.lab.rest.entity.Bid;
import se331.lab.rest.service.BidService;
import se331.lab.rest.repository.BidRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BidServiceImpl implements BidService {
    final BidDao bidDao;
    @Override
    public List<Bid> getBids() {
        return bidDao.getBids(Pageable.unpaged()).getContent();
    }
    @Override
    public Page<Bid> getBids(Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return bidDao.getBids(pageable);
    }
    @Override
    public Optional<Bid> getBidById(Long id) {
        return bidDao.findById(id);
    }

    // New method to retrieve bids with amounts less than the specified value
    public List<Bid> getBidsUnderValue(Double value) {
        return bidDao.findByAmountLessThan(value);
    }

}