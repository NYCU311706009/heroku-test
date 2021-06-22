package com.example.demo.Repository;

import com.example.demo.Entity.ConfirmationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken,Long> {
    ConfirmationToken findByToken(String token);
}
