package com.redck.restaurantmsbff.repository;

import com.redck.restaurantmsbff.domain.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>
{

    Optional<User> findByUid(final String uid);

    Optional<User> findByUsername(final String username);

    void deleteByUid(final String uid);
}
