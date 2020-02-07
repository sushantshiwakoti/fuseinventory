package com.fusemachineinventoryproject.repository;

import com.fusemachineinventoryproject.model.ApiLog;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiLogRepository extends ExtendedRepository<ApiLog,String> {
}
