package com.nova.service.impl;

import com.nova.dao.EmailTemplateDao;
import com.nova.entity.EmailTemplate;
import com.nova.service.EmailTemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class EmailTemplateServiceImpl implements EmailTemplateService {

    private final EmailTemplateDao emailTemplateDao;

    @Override
    public EmailTemplate createTemplate(EmailTemplate template) {
        log.info("Creating new email template: {}", template.getName());
        return emailTemplateDao.save(template);
    }

    @Override
    public EmailTemplate updateTemplate(Long id, EmailTemplate template) {
        log.info("Updating email template with ID: {}", id);
        Optional<EmailTemplate> existingTemplate = emailTemplateDao.findById(id);
        if (existingTemplate.isPresent()) {
            EmailTemplate existing = existingTemplate.get();
            existing.setName(template.getName());
            existing.setDescription(template.getDescription());
            existing.setHtmlContent(template.getHtmlContent());
            existing.setCssContent(template.getCssContent());
            existing.setCategory(template.getCategory());
            existing.setIsActive(template.getIsActive());
            return emailTemplateDao.update(existing);
        }
        throw new RuntimeException("Email template not found with ID: " + id);
    }

    @Override
    public void deleteTemplate(Long id) {
        log.info("Deleting email template with ID: {}", id);
        emailTemplateDao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EmailTemplate> getTemplateById(Long id) {
        log.info("Fetching email template with ID: {}", id);
        return emailTemplateDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmailTemplate> getAllTemplates() {
        log.info("Fetching all email templates");
        return emailTemplateDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmailTemplate> getActiveTemplates() {
        log.info("Fetching all active email templates");
        return emailTemplateDao.findAllActive();
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmailTemplate> getTemplatesByCategory(String category) {
        log.info("Fetching email templates by category: {}", category);
        return emailTemplateDao.findByCategory(category);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmailTemplate> searchTemplates(String searchTerm) {
        log.info("Searching email templates with term: {}", searchTerm);
        return emailTemplateDao.search(searchTerm);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EmailTemplate> getTemplateByName(String name) {
        log.info("Fetching email template by name: {}", name);
        return emailTemplateDao.findByName(name);
    }
}