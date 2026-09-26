package com.nova.dao.impl;

import com.nova.dao.EmailTemplateDao;
import com.nova.entity.EmailTemplate;
import com.nova.repository.EmailTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmailTemplateDaoImpl implements EmailTemplateDao {

    private final EmailTemplateRepository repository;

    @Override
    public EmailTemplate save(EmailTemplate template) {
        return repository.save(template);
    }

    @Override
    public EmailTemplate update(EmailTemplate template) {
        return repository.save(template);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<EmailTemplate> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<EmailTemplate> findAll() {
        return repository.findAll();
    }

    @Override
    public List<EmailTemplate> findByCategory(String category) {
        return repository.findByCategory(category);
    }

    @Override
    public List<EmailTemplate> findAllActive() {
        return repository.findAllActive();
    }

    @Override
    public Optional<EmailTemplate> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<EmailTemplate> search(String searchTerm) {
        return repository.searchByNameOrDescription(searchTerm);
    }
}