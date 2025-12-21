package se.ifmo.models;

import jakarta.persistence.*;
import lombok.*;
import se.ifmo.annotations.CacheableEntity;

@Entity
@Table(name = "coordinates")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@CacheableEntity
public class CoordinatesEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private Float x;

  @Column(nullable = false)
  private Double y;
}
