package org.bitnick.portfolio.repository;

import org.bitnick.portfolio.model.Resume;
import org.bitnick.portfolio.model.WelcomeText;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, String> {
    Resume findTopByOrderByIdDesc();
}
