package com.dzialsprzedazy.repository;

import com.dzialsprzedazy.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
