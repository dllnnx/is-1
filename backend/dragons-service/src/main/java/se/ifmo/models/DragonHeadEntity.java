package se.ifmo.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "dragon_head")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DragonHeadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tooth_count")
    private Float toothCount;
}
