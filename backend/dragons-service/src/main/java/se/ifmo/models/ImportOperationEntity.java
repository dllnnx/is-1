package se.ifmo.models;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import lombok.*;

@Entity
@Table(name = "import_operation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImportOperationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ImportStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    private UserRole userRole;

    @Column(name = "added_count")
    private Integer addedCount;

    @Column(name = "error_message")
    private String errorMessage;

    @Column(name = "file_key")
    private String fileKey;

    @Builder.Default
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();
}
