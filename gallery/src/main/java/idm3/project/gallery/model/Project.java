package idm3.project.gallery.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @Column(name = "ProjectID")
    private long projectId;

    @NotBlank(message = "Project name is required")
    @Size(max = 255, message = "Project name must not exceed 255 characters")
    @Column(name = "ProjectName")
    private String projectName;

    @NotBlank(message = "Project description is required")
    @Size(max = 500, message = "Project description must not exceed 500 characters")
    @Column(name = "ProjectDescription", length = 500)
    private String projectDescription;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @NotBlank(message = "Category is required")
    @Size(max = 255, message = "Category must not exceed 255 characters")
    @Column(name = "Category")
    private String category;

    @NotBlank(message = "Project Description Summary is required")
    @Size(max = 255, message = "Project Description Summary must not exceed 255 characters")
    @Column(name = "ProjectDescriptionSummary")
    private String projectDescriptionSummary;

    @Column(name = "ProjectHeroImage")
    private String projectHeroImage;

    @Column(name = "CreationDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.sql.Date creationDate;

    @ManyToOne
    @JoinColumn(name = "showcase_id")
    private Showcase showcase;
}
