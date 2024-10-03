package se331.lab.rest.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Organizer {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @EqualsAndHashCode.Exclude
        Long id;
        String name;
        @OneToMany(mappedBy = "organizer")
        List<Event> ownEvents;
}
