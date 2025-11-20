package idm3.project.gallery.model;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "project_note")
public class ProjectNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NoteID")
    private Long noteId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ProjectID", nullable = false)
    private Employer employers;

    @Column(name = "UserID")
    private Long userId = 1L;

    @Column(name = "NoteText", length = 1000)
    private String noteText;

    @Column(name = "Saved", nullable = false)
    private boolean saved = false;

    @Column(name = "CreatedAt")
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());
}
