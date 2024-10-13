package se331.lab.rest.service;

import org.springframework.data.domain.Page;
import se331.lab.rest.entity.Organizer;

import java.util.List;

public interface OrganizerService {
    List<Organizer> getAllOrganizer();
    Page<Organizer> getOrganizer(Integer page, Integer pageSize);
}

