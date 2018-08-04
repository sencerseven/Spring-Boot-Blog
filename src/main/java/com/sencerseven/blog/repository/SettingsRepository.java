package com.sencerseven.blog.repository;

import com.sencerseven.blog.domain.Settings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SettingsRepository extends JpaRepository<Settings, Long> {
}
