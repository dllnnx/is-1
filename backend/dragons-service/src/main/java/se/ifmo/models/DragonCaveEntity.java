package se.ifmo.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "dragon_cave")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DragonCaveEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Float depth;

  @Column(name = "number_of_treasures", nullable = false)
  private Long numberOfTreasures;
}
