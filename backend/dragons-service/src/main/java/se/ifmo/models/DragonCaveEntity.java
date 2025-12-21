package se.ifmo.models;

import jakarta.persistence.*;
import lombok.*;
import se.ifmo.annotations.CacheableEntity;

@Entity
@Table(name = "dragon_cave")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@CacheableEntity
public class DragonCaveEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Float depth;

  @Column(name = "number_of_treasures", nullable = false)
  private Long numberOfTreasures;
}
