package com.nova.service;

import com.nova.entity.EmailTemplate;
import java.util.List;
import java.util.Optional;

public interface EmailTemplateService {

    EmailTemplate createTemplate(EmailTemplate template);

    EmailTemplate updateTemplate(Long id, EmailTemplate template);

    void deleteTemplate(Long id);

    Optional<EmailTemplate> getTemplateById(Long id);

    List<EmailTemplate> getAllTemplates();

    List<EmailTemplate> getActiveTemplates();

    List<EmailTemplate> getTemplatesByCategory(String category);

    List<EmailTemplate> searchTemplates(String searchTerm);

    Optional<EmailTemplate> getTemplateByName(String name);
}