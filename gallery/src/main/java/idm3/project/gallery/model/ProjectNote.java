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
    private Project project;

    @Column(name = "UserID", nullable = false)
    private Long userId;

    @Column(name = "NoteText", length = 2000)
    private String noteText;

    @Column(name = "Saved")
    private boolean saved = true;

    @Column(name = "CreatedAt")
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());
}
