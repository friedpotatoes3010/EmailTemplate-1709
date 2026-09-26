package com.nova.dao;

import com.nova.entity.EmailTemplate;
import java.util.List;
import java.util.Optional;

public interface EmailTemplateDao {

    EmailTemplate save(EmailTemplate template);

    EmailTemplate update(EmailTemplate template);

    void delete(Long id);

    Optional<EmailTemplate> findById(Long id);

    List<EmailTemplate> findAll();

    List<EmailTemplate> findByCategory(String category);

    List<EmailTemplate> findAllActive();

    Optional<EmailTemplate> findByName(String name);

    List<EmailTemplate> search(String searchTerm);
}