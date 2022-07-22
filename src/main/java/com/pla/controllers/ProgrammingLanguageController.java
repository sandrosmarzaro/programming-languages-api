package com.pla.controllers;

import com.pla.models.ProgrammingLanguage;
import com.pla.repositories.ProgrammingLanguageRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/languages")
public class ProgrammingLanguageController {

    ProgrammingLanguageRepository programmingLanguageRepository;

    @GetMapping
    public List<ProgrammingLanguage> readAll() {
        return programmingLanguageRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgrammingLanguage> readById(@PathVariable String id) {
        return programmingLanguageRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProgrammingLanguage create(@RequestBody ProgrammingLanguage programmingLanguage) {
        programmingLanguageRepository.save(programmingLanguage);
        return programmingLanguage;
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProgrammingLanguage> update(
        @PathVariable String id,
        @RequestBody ProgrammingLanguage programmingLanguage) {
        return programmingLanguageRepository.findById(id)
            .map(language -> {
                language.setName(programmingLanguage.getName());
                language.setImage(programmingLanguage.getImage());
                programmingLanguageRepository.save(language);
                return ResponseEntity.ok(language);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (!programmingLanguageRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        programmingLanguageRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/rankings")
    public List<ProgrammingLanguage> ranking() {
        return programmingLanguageRepository.findAll(
            Sort.by(Sort.Direction.DESC, "votes")
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> patch(@PathVariable String id) {
        if (!programmingLanguageRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        ProgrammingLanguage language = programmingLanguageRepository
            .findById(id)
            .get();
        language.setVotes(language.getVotes() + 1);
        programmingLanguageRepository.save(language);
        return ResponseEntity.noContent().build();
    }
}
