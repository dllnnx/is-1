package se.ifmo.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "coordinates")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoordinatesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Float x;

    @Column(nullable = false)
    private Double y;
}
