package com.nova.controller;

import com.nova.entity.EmailTemplate;
import com.nova.service.EmailTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/templates")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EmailTemplateController {

    private final EmailTemplateService emailTemplateService;

    @PostMapping
    public ResponseEntity<EmailTemplate> createTemplate(@RequestBody EmailTemplate template) {
        EmailTemplate created = emailTemplateService.createTemplate(template);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<EmailTemplate>> getAllTemplates() {
        List<EmailTemplate> templates = emailTemplateService.getAllTemplates();
        return ResponseEntity.ok(templates);
    }

    @GetMapping("/active")
    public ResponseEntity<List<EmailTemplate>> getActiveTemplates() {
        List<EmailTemplate> templates = emailTemplateService.getActiveTemplates();
        return ResponseEntity.ok(templates);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmailTemplate> getTemplateById(@PathVariable Long id) {
        return emailTemplateService.getTemplateById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<EmailTemplate> getTemplateByName(@PathVariable String name) {
        return emailTemplateService.getTemplateByName(name)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<EmailTemplate>> getTemplatesByCategory(@PathVariable String category) {
        List<EmailTemplate> templates = emailTemplateService.getTemplatesByCategory(category);
        return ResponseEntity.ok(templates);
    }

    @GetMapping("/search")
    public ResponseEntity<List<EmailTemplate>> searchTemplates(@RequestParam String query) {
        List<EmailTemplate> templates = emailTemplateService.searchTemplates(query);
        return ResponseEntity.ok(templates);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmailTemplate> updateTemplate(@PathVariable Long id, @RequestBody EmailTemplate template) {
        try {
            EmailTemplate updated = emailTemplateService.updateTemplate(id, template);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTemplate(@PathVariable Long id) {
        emailTemplateService.deleteTemplate(id);
        return ResponseEntity.noContent().build();
    }
}