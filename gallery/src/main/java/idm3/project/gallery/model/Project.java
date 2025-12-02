package idm3.project.gallery.model;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    private String projectName;

    @Column(length = 2000)
    private String projectDescription;

    private String projectDescriptionSummary;

    private String category;

    private String projectHeroImage;

    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = "showcase_id")
    private Showcase showcase;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
