package idm3.project.gallery.service;

import idm3.project.gallery.model.ProjectNote;
import idm3.project.gallery.repository.ProjectNoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerNoteService {

    private final ProjectNoteRepository noteRepo;

    public EmployerNoteService(ProjectNoteRepository noteRepo) {
        this.noteRepo = noteRepo;
    }

    public List<ProjectNote> getAllNotesForEmployer(Long employerId) {
        return noteRepo.findByUserIdOrderByCreatedAtDesc(employerId);
    }
}
