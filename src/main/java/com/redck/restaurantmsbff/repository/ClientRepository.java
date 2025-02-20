package com.redck.restaurantmsbff.repository;

import com.redck.restaurantmsbff.domain.Client;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>
{

    Optional<Client> findByUid(final String uid);

    Optional<Client> findByUsername(final String username);
    Optional<Client> findByEmail(final String email);

    void deleteByUid(final String uid);
}
