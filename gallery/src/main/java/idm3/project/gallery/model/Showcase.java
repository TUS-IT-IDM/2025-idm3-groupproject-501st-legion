package idm3.project.gallery.model;

import jakarta.persistence.*;

@Entity
@Table(name = "showcase")
public class Showcase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ShowcaseId")
    private long showcaseId;
    @Column(name = "Name")
    private String name;
    @Column(name = "Description")
    private String description;
    @Column(name = "Image")
    private String image;
    @Column(name = "Status")
    private String status;


    public Showcase(long showcaseId, String name, String description, String image, String status) {
        this.showcaseId = showcaseId;
        this.name = name;
        this.description = description;
        this.image = image;
        this.status = status;
    }

    public Showcase() {
    }

    public long getShowcaseId() {
        return this.showcaseId;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getImage() {
        return this.image;
    }

    public String getStatus() {
        return this.status;
    }

    public void setShowcaseId(long showcaseId) {
        this.showcaseId = showcaseId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return "Showcase(showcaseId=" + this.getShowcaseId() + ", name=" + this.getName() + ", description=" + this.getDescription() + ", image=" + this.getImage() + ", status=" + this.getStatus() + ")";
    }
}
