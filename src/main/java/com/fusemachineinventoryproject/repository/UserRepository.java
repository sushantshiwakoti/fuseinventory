package com.fusemachineinventoryproject.repository;

import com.fusemachineinventoryproject.model.Users;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ExtendedRepository<Users,String> {
}
