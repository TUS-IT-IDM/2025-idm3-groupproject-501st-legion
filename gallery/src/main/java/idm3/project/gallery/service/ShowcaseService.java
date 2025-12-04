package idm3.project.gallery.service;

import idm3.project.gallery.model.Showcase;
import idm3.project.gallery.repository.ShowcaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ShowcaseService {

    @Autowired
    private ShowcaseRepository showcaseRepository;

    @Autowired
    private ThumbnailService thumbnailService;

    private final String uploadDir = "src/main/resources/static/uploads/";
    private final String thumbDir  = "src/main/resources/static/uploads/thumbs/";

    public List<Showcase> findAll() {
        return StreamSupport.stream(showcaseRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
    public Showcase findById(Long id) {
        return showcaseRepository.findById(id).orElse(null);
    }

    public Showcase saveShowcase(Showcase showcase, MultipartFile imageFile) {

        try {
            if (imageFile != null && !imageFile.isEmpty()) {

                String filename = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();

                Path originalPath = Paths.get(uploadDir + filename);
                Path thumbPath = Paths.get(thumbDir + filename);

                Files.createDirectories(originalPath.getParent());
                Files.createDirectories(thumbPath.getParent());

                Files.copy(imageFile.getInputStream(), originalPath, StandardCopyOption.REPLACE_EXISTING);

                thumbnailService.generateThumbnailShowcase(
                        originalPath.toFile(),
                        thumbPath.toFile()
                );

                showcase.setImage("/uploads/" + filename);
                showcase.setThumbnailImage("/uploads/thumbs/" + filename);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error saving image file", e);
        }

        return showcaseRepository.save(showcase);
    }

    public void deleteById(Long id) {
        showcaseRepository.deleteById(id);
    }


    public List<Showcase> searchByName(String keyword) {
        return showcaseRepository.findByNameContainingIgnoreCase(keyword);
    }

    public List<Showcase> filterByStatus(String status) {
        return showcaseRepository.findByStatusIgnoreCase(status);
    }

    public List<Showcase> sortShowcases(String sort) {
        return switch (sort.toLowerCase()) {
            case "az"     -> showcaseRepository.findAllByOrderByNameAsc();
            case "za"     -> showcaseRepository.findAllByOrderByNameDesc();
            case "newest" -> showcaseRepository.findAllByOrderByShowcaseIdDesc();
            case "oldest" -> showcaseRepository.findAllByOrderByShowcaseIdAsc();
            default       -> findAll();
        };
    }
}

