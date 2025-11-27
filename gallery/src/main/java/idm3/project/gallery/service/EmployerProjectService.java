package idm3.project.gallery.service;

import idm3.project.gallery.model.Project;
import idm3.project.gallery.model.ProjectNote;
import idm3.project.gallery.repository.ProjectNoteRepository;
import idm3.project.gallery.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerProjectService {

    private final ProjectRepository projectRepo;
    private final ProjectNoteRepository noteRepo;

    public EmployerProjectService(ProjectRepository projectRepo,
                                  ProjectNoteRepository noteRepo) {
        this.projectRepo = projectRepo;
        this.noteRepo = noteRepo;
    }

    public List<Project> findAllProjects() {
        return projectRepo.findAll();
    }

    public List<ProjectNote> getSavedProjects(Long employerId) {
        return noteRepo.findByUserId(employerId)
                .stream()
                .filter(ProjectNote::isSaved)
                .toList();
    }

    public void saveProject(Long projectId, Long employerId) {
        Project project = projectRepo.findById(projectId)
                .orElseThrow();

        ProjectNote note = new ProjectNote();
        note.setProject(project);
        note.setUserId(employerId);
        note.setSaved(true);

        noteRepo.save(note);
    }

    public void removeSavedProject(Long projectId, Long employerId) {
        List<ProjectNote> notes =
                noteRepo.findByUserIdAndProject_ProjectId(employerId, projectId);

        notes.forEach(n -> n.setSaved(false));
        noteRepo.saveAll(notes);
    }

    public void addNote(Long projectId, Long employerId, String text) {
        Project project = projectRepo.findById(projectId)
                .orElseThrow();

        ProjectNote note = new ProjectNote();
        note.setProject(project);
        note.setUserId(employerId);
        note.setNoteText(text);
        note.setSaved(true);

        noteRepo.save(note);
    }

    public List<ProjectNote> getAllNotesForEmployer(Long employerId) {
        return noteRepo.findByUserId(employerId);
    }
}
