package se.ifmo.models;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "dragon")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DragonEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "coordinates_id", nullable = false)
  private CoordinatesEntity coordinatesEntity;

  @Builder.Default
  @Column(name = "creation_date", nullable = false)
  private OffsetDateTime creationDate = OffsetDateTime.now();

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "cave_id", nullable = false)
  private DragonCaveEntity cave;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "killer_id")
  private PersonEntity killer;

  @Column(nullable = false)
  private Integer age;

  @Enumerated(EnumType.STRING)
  @JdbcTypeCode(SqlTypes.NAMED_ENUM)
  @Column(nullable = false, columnDefinition = "color")
  private Color color;

  @Enumerated(EnumType.STRING)
  @JdbcTypeCode(SqlTypes.NAMED_ENUM)
  @Column(nullable = false, columnDefinition = "dragon_type")
  private DragonType type;

  @Enumerated(EnumType.STRING)
  @JdbcTypeCode(SqlTypes.NAMED_ENUM)
  @Column(columnDefinition = "dragon_character")
  private DragonCharacter character;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "head_id")
  private DragonHeadEntity head;
}
