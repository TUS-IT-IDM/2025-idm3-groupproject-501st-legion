package idm3.project.gallery.controllers;

import idm3.project.gallery.model.Employer;
import idm3.project.gallery.model.Review;
import idm3.project.gallery.service.EmployerService;
import idm3.project.gallery.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/employer")
public class EmployerController {

    private final EmployerService employerService;
    private final ReviewService reviewService;

    @Autowired
    public EmployerController(EmployerService employerService,
                              ReviewService reviewService) {
        this.employerService = employerService;
        this.reviewService = reviewService;
    }


    @GetMapping({"", "/all"})
    public ModelAndView listEmployers() {
        List<Employer> all = employerService.findAll();
        List<Employer> saved = employerService.getSavedProjects();

        ModelAndView mv = new ModelAndView("employer");
        mv.addObject("employers", all);
        mv.addObject("savedProjects", saved);
        return mv;
    }


    @GetMapping("/details/{id}")
    public ModelAndView viewDetails(@PathVariable Long id) {
        Employer employer = employerService.findOne(id);

        ModelAndView mv = new ModelAndView("employerdetails");
        mv.addObject("employer", employer);
        mv.addObject("reviews", reviewService.getReviewsForEmployer(id));
        return mv;
    }


    @PostMapping("/addReview")
    public String addReview(@RequestParam Long employerId,
                            @RequestParam String comment,
                            @RequestParam int rating) {

        Employer employer = employerService.findOne(employerId);

        Review review = new Review();
        review.setComment(comment);
        review.setRating(rating);
        review.setEmployer(employer);

        reviewService.saveReview(review);

        return "redirect:/employer/details/" + employerId;
    }


    @PostMapping("/saveToList")
    public String saveToList(@RequestParam Long id) {
        employerService.saveToList(id);
        return "redirect:/employer";
    }


    @PostMapping("/removeFromList")
    public String removeFromList(@RequestParam Long id) {
        employerService.removeFromList(id);
        return "redirect:/employer";
    }


    @PostMapping("/updateNotes")
    public String updateNotes(@RequestParam Long id, @RequestParam String notes) {
        employerService.updateNotes(id, notes);
        return "redirect:/employer";
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam String keyword) {
        List<Employer> results = employerService.searchProject(keyword);
        List<Employer> saved = employerService.getSavedProjects();

        ModelAndView mv = new ModelAndView("employer");
        mv.addObject("employers", results);
        mv.addObject("savedProjects", saved);
        mv.addObject("searchKeyword", keyword);
        return mv;
    }

}








