package com.sample.connect.app.atlassianconnectsampleappjava.repository;

import com.sample.connect.app.atlassianconnectsampleappjava.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TenantRepository extends JpaRepository<Tenant, Long> {
    List<Tenant> findByHost(String host);
}
