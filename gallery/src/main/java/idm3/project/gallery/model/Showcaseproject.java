package idm3.project.gallery.model;

import jakarta.persistence.*;

@Entity
@Table(name = "showcaseproject")
public class Showcaseproject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ShowcaseProjectID")
    private long showcaseProjectId;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ProjectId")
    private Project projectId;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ShowcaseID")
    private Showcase showcaseId;


    public Showcaseproject(long showcaseProjectId, Project projectId, Showcase showcaseId) {
        this.showcaseProjectId = showcaseProjectId;
        this.projectId = projectId;
        this.showcaseId = showcaseId;
    }

    public Showcaseproject() {
    }

    public long getShowcaseProjectId() {
        return this.showcaseProjectId;
    }

    public Project getProjectId() {
        return this.projectId;
    }

    public Showcase getShowcaseId() {
        return this.showcaseId;
    }

    public void setShowcaseProjectId(long showcaseProjectId) {
        this.showcaseProjectId = showcaseProjectId;
    }

    public void setProjectId(Project projectId) {
        this.projectId = projectId;
    }

    public void setShowcaseId(Showcase showcaseId) {
        this.showcaseId = showcaseId;
    }

    public String toString() {
        return "Showcaseproject(showcaseProjectId=" + this.getShowcaseProjectId() + ", projectId=" + this.getProjectId() + ", showcaseId=" + this.getShowcaseId() + ")";
    }
}
