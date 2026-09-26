package com.nova.repository;

import com.nova.entity.EmailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmailTemplateRepository extends JpaRepository<EmailTemplate, Long> {

    Optional<EmailTemplate> findByName(String name);

    List<EmailTemplate> findByIsActive(Boolean isActive);

    List<EmailTemplate> findByCategory(String category);

    @Query("SELECT e FROM EmailTemplate e WHERE e.isActive = true ORDER BY e.createdAt DESC")
    List<EmailTemplate> findAllActive();

    @Query("SELECT e FROM EmailTemplate e WHERE e.name LIKE %:searchTerm% OR e.description LIKE %:searchTerm%")
    List<EmailTemplate> searchByNameOrDescription(@Param("searchTerm") String searchTerm);
}