package idm3.project.gallery.service;

import idm3.project.gallery.model.Project;
import idm3.project.gallery.model.ProjectNote;
import idm3.project.gallery.repository.ProjectNoteRepository;
import idm3.project.gallery.repository.ProjectRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public void saveProject(Long projectId, Long employerId) {
        Project project = projectRepo.findById(projectId).orElseThrow();

        // Prevent duplicate save rows
        boolean alreadySaved = noteRepo
                .findByUserIdAndProject_ProjectId(employerId, projectId)
                .stream()
                .anyMatch(ProjectNote::isSaved);

        if (alreadySaved) return;

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
        Project project = projectRepo.findById(projectId).orElseThrow();

        ProjectNote note = new ProjectNote();
        note.setProject(project);
        note.setUserId(employerId);
        note.setNoteText(text);
        note.setSaved(true);

        noteRepo.save(note);
    }

    // ----------------------------
    //  NEW: Filter + group correctly
    // ----------------------------
    public List<ProjectNote> getUniqueSavedProjects(Long employerId) {

        List<ProjectNote> all = noteRepo.findByUserId(employerId).stream()
                .filter(ProjectNote::isSaved)
                .toList();

        Map<Long, ProjectNote> grouped = all.stream()
                .collect(Collectors.toMap(
                        n -> n.getProject().getProjectId(),
                        n -> n,
                        (existing, replacement) -> {
                            boolean existingHasNote = existing.getNoteText() != null && !existing.getNoteText().isBlank();
                            boolean replacementHasNote = replacement.getNoteText() != null && !replacement.getNoteText().isBlank();

                            if (!existingHasNote && replacementHasNote) {
                                return replacement;
                            }
                            return existing;
                        }
                ));

        return grouped.values().stream().toList();
    }

    public void deleteNote(Long projectId, Long employerId) {


        // Find the note row for this employer & project
        List<ProjectNote> notes =
                noteRepo.findByUserIdAndProject_ProjectId(employerId, projectId);

        for (ProjectNote note : notes) {

            // Only delete actual notes, not the saved-project row
            if (note.getNoteText() != null && !note.getNoteText().isBlank()) {
                note.setNoteText(null); // Remove note text
            }
        }

        noteRepo.saveAll(notes);
    }


    public List<ProjectNote> getAllNotesForEmployer(Long employerId) {
        return noteRepo.findByUserId(employerId);
    }

    public List<Project> searchProjects(String keyword) {

        if (keyword == null || keyword.isBlank()) {
            return projectRepo.findAll();
        }

        String lower = keyword.toLowerCase();

        return projectRepo.findAll().stream()
                .filter(p ->
                        (p.getProjectName() != null && p.getProjectName().toLowerCase().contains(lower)) ||
                                (p.getProjectDescriptionSummary() != null &&
                                        p.getProjectDescriptionSummary().toLowerCase().contains(lower)) ||
                                (p.getCategory() != null && p.getCategory().toLowerCase().contains(lower))
                )
                .toList();
    }

}
