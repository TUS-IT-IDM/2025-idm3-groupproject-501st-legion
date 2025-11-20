package idm3.project.gallery.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.sql.Date;

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

    //@Column(name = "UserID")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "UserID")
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
    private java.sql.Date creationDate;
    @Column(name = "additionalDoc")
    private String additionalDoc;

    public Project(long projectId, String projectName, String projectDescription, User user, String category, String projectDescriptionSummary, String projectHeroImage, Date creationDate, String additionalDoc) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.user = user;
        this.category = category;
        this.projectDescriptionSummary = projectDescriptionSummary;
        this.projectHeroImage = projectHeroImage;
        this.creationDate = creationDate;
        this.additionalDoc = additionalDoc;
    }

    public Project() {
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                ", user=" + user +
                ", category='" + category + '\'' +
                ", projectDescriptionSummary='" + projectDescriptionSummary + '\'' +
                ", projectHeroImage='" + projectHeroImage + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }

    public Long getProjectId() {
        return this.projectId;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public String getProjectDescription() {
        return this.projectDescription;
    }

    public User getUser() {
        return this.user;
    }

    public String getCategory() {
        return this.category;
    }

    public String getProjectDescriptionSummary() {
        return this.projectDescriptionSummary;
    }

    public String getProjectHeroImage() {
        return this.projectHeroImage;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }



    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setProjectDescriptionSummary(String projectDescriptionSummary) {
        this.projectDescriptionSummary = projectDescriptionSummary;
    }

    public void setProjectHeroImage(String projectHeroImage) {
        this.projectHeroImage = projectHeroImage;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

}
