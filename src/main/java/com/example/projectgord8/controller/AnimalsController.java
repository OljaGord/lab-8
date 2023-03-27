package com.example.projectgord8.controller;

import com.example.projectgord8.entity.Animal;
import com.example.projectgord8.repository.AnimalRepository;
import com.example.projectgord8.service.UserActionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Slf4j
@Controller
public class AnimalsController {
    @Autowired
    private AnimalRepository animalsRepository;
    @Autowired
    private UserActionService userActionService;


    @GetMapping("/list")
    public ModelAndView getAnimals() {
        log.info("/list -> connection");
        Authentication authInfo = SecurityContextHolder.getContext().getAuthentication();
        userActionService.saveAction(authInfo.getName(), "list");
        ModelAndView mav = new ModelAndView("list-animals");
        mav.addObject("animals", animalsRepository.findAll());
        return mav;
    }

    @GetMapping("/addAnimalForm")
    public ModelAndView addAnimalForm() {
        Authentication authInfo = SecurityContextHolder.getContext().getAuthentication();
        userActionService.saveAction(authInfo.getName(), "addAnimalForm");
        ModelAndView mav = new ModelAndView("add-animal-form");
        Animal animal = new Animal();
        mav.addObject("animal", animal);
        return mav;
    }

    @PostMapping("/saveAnimal")
    public String saveAnimal(@ModelAttribute Animal animal) {
        Authentication authInfo = SecurityContextHolder.getContext().getAuthentication();
        userActionService.saveAction(authInfo.getName(), "saveAnimal");
        animalsRepository.save(animal);
        return "redirect:/list";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long animalId) {
        Authentication authInfo = SecurityContextHolder.getContext().getAuthentication();
        userActionService.saveAction(authInfo.getName(), "showUpdateForm");
        ModelAndView mav = new ModelAndView("add-animal-form");
        Optional<Animal> optionalAnimal = animalsRepository.findById(animalId);
        Animal animal = new Animal();
        if (optionalAnimal.isPresent()) {
            animal = optionalAnimal.get();
        }
        mav.addObject("animal", animal);
        return mav;
    }

    @GetMapping("deleteAnimal")
    public String deleteAnimal(@RequestParam Long animalId) {
        Authentication authInfo = SecurityContextHolder.getContext().getAuthentication();
        userActionService.saveAction(authInfo.getName(), "deleteAnimal");
        animalsRepository.deleteById(animalId);
        return "redirect:/list";
    }
}
