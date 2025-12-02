package idm3.project.gallery.controllers;

import idm3.project.gallery.model.Project;
import idm3.project.gallery.model.Showcase;
import idm3.project.gallery.service.ProjectService;
import idm3.project.gallery.service.ShowcaseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/MainGallery")
public class MainGalleryController {

    private final ProjectService projectService;
    private final ShowcaseService showcaseService;

    public MainGalleryController(ProjectService projectService,
                                 ShowcaseService showcaseService) {
        this.projectService = projectService;
        this.showcaseService = showcaseService;
    }

    @GetMapping({"", "/", "/HomePage"})
    public ModelAndView showHomePage() {

        ModelAndView mav = new ModelAndView("homepage");

        List<Project> allProjects = projectService.findAll();
        List<Showcase> allShowcases = showcaseService.findAll();

        allProjects.forEach(project ->
                project.setProjectHeroImage("/assets/images/projects/thumbnail/" + project.getProjectHeroImage()));

        allShowcases.forEach(showcase ->
                showcase.setThumbnailImage("/assets/images/showcases/thumbnail/" + showcase.getThumbnailImage()));

        mav.addObject("AllProjectsRecentFirst", allProjects);
        mav.addObject("AllLiveShowcases", allShowcases);

        return mav;
    }

}
