package com.sample.connect.app.atlassianconnectsampleappjava.repository;

import com.sample.connect.app.atlassianconnectsampleappjava.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogRepository extends JpaRepository<Log, Long> {
    List<Log> findByTenantId(int tenantId);
}
