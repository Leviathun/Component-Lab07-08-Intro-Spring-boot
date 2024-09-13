package se331.lab.rest.dao;

import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;
import se331.lab.rest.entity.Organizer;
import se331.lab.rest.dao.OrganizerDao;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrganizerDaoImpl implements OrganizerDao {
    List<Organizer> organizerList;

    @PostConstruct
    public void init() {
        organizerList = new ArrayList<>();

        organizerList.add(Organizer.builder()
                .id(1L)
                .organizationName("Animal Welfare Organization")
                .address("123 Meow Town")
                .build());

        organizerList.add(Organizer.builder()
                .id(2L)
                .organizationName("Community Gardeners")
                .address("456 Flora City")
                .build());

        // Add more organizers as needed
    }

    @Override
    public Integer getOrganizerSize() {
        return organizerList.size();
    }

    @Override
    public List<Organizer> getOrganizers(Integer pageSize, Integer page) {
        pageSize = pageSize == null ? organizerList.size() : pageSize;
        page = page == null ? 1 : page;
        int firstIndex = (page - 1) * pageSize;
        return organizerList.subList(firstIndex, Math.min(firstIndex + pageSize, organizerList.size()));
    }

    @Override
    public Organizer getOrganizer(Long id) {
        return organizerList.stream()
                .filter(organizer -> organizer.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}