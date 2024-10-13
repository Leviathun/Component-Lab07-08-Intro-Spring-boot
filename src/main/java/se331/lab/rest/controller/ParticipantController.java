package se331.lab.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se331.lab.rest.entity.Participant;
import se331.lab.rest.entity.ParticipantDTO;  // Import the ParticipantDTO
import se331.lab.rest.service.ParticipantService;
import se331.lab.rest.util.LabMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/participants")
@RequiredArgsConstructor
public class ParticipantController {

    final ParticipantService participantService;  // Use a service for business logic

    @GetMapping
    public ResponseEntity<List<ParticipantDTO>> getParticipantsWithEvents() {
        List<Participant> participants = participantService.getAllParticipants();  // Call the service method
        // Convert the list of participants to DTOs
        List<ParticipantDTO> participantDTOs = participants.stream()
                .map(LabMapper.INSTANCE::getParticipantDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(participantDTOs);  // Return the DTOs
    }
}