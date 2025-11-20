package idm3.project.gallery.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "showcase")
public class Showcase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ShowcaseId")
    private long showcaseId;

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 255, message = "Name must be between 3 and 255 characters")
    @Column(name = "Name")
    private String name;

    @NotBlank(message = "Description is required")
    @Size(min = 2, max = 500, message = "Description must be between 2 and 500 characters")
    @Column(name = "Description", length = 500)
    private String description;

    @Column(name = "Image")
    private String image;

    @Column(name = "ThumbnailImage")
    private String thumbnailImage;

    @NotBlank(message = "Status is required")
    @Size(min = 2, max = 50, message = "Status must be LIVE or PENDING")
    @Column(name = "Status")
    private String status;
}
