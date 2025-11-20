package idm3.project.gallery.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Employer")
public class Employer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EmployerID")
    private Long employerId;

    @Column(name = "EmployerName", nullable = false)
    private String employerName;

    @Column(name = "EmployerDescription", length = 2000)
    private String employerDescription;

    @Column(name = "EmployerDescriptionSummary")
    private String employerDescriptionSummary;

    @Column(name = "Category")
    private String category;

    @Column(name = "EmployerHeroImage")
    private String employerHeroImage;

    @Column(name = "CreationDate")
    private Date creationDate;

    @Column(name = "additionalDoc")
    private String additionalDoc;

    @Column(length = 2000)
    private String notes;

    @Column(name = "saved")
    private Boolean saved = false;


    @OneToMany(
            mappedBy = "employer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Review> reviews = new ArrayList<>();



    public boolean isSaved() {
        return saved != null && saved;
    }

    public Boolean getSaved() {
        return saved;
    }

    public void setSaved(Boolean saved) {
        this.saved = saved;
    }

    public String getEmployerHeroImage() {
        return employerHeroImage;
    }

    public void setEmployerHeroImage(String employerHeroImage) {
        this.employerHeroImage = employerHeroImage;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}











