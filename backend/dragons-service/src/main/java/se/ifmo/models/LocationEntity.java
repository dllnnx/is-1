package se.ifmo.models;

import jakarta.persistence.*;
import lombok.*;
import se.ifmo.annotations.CacheableEntity;

@Entity
@Table(name = "location")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@CacheableEntity
public class LocationEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Double x;

  @Column(nullable = false)
  private Integer y;

  @Column(length = 416)
  private String name;
}
