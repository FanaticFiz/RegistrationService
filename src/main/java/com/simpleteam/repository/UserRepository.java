package com.simpleteam.repository;

import com.simpleteam.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * SpringData CRUD repository.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Find by email.
     *
     * @param email email
     * @return found user
     */
    User findByEmail(String email);

    /**
     * Find by UUID.
     *
     * @param uuid String
     * @return found user
     */
    User findByUuid(String uuid);

}
