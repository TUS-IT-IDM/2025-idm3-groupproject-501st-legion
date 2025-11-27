package idm3.project.gallery.model;

import jakarta.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
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

    @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();


    // --------- GETTERS / SETTERS ----------

    public Long getEmployerId() {
        return employerId;
    }

    public void setEmployerId(Long employerId) {
        this.employerId = employerId;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public String getEmployerDescription() {
        return employerDescription;
    }

    public void setEmployerDescription(String employerDescription) {
        this.employerDescription = employerDescription;
    }

    public String getEmployerDescriptionSummary() {
        return employerDescriptionSummary;
    }

    public void setEmployerDescriptionSummary(String employerDescriptionSummary) {
        this.employerDescriptionSummary = employerDescriptionSummary;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getAdditionalDoc() {
        return additionalDoc;
    }

    public void setAdditionalDoc(String additionalDoc) {
        this.additionalDoc = additionalDoc;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Boolean getSaved() {
        return saved;
    }

    public void setSaved(Boolean saved) {
        this.saved = saved;
    }

    public boolean isSaved() {
        return saved != null && saved;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}











