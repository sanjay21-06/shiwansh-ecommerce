package com.shiwansh.ecommerce.infrastructure.persistence.repository.adapter;

import com.shiwansh.ecommerce.domain.model.User;
import com.shiwansh.ecommerce.domain.repository.UserRepository;
import com.shiwansh.ecommerce.infrastructure.persistence.entity.UserEntity;
import com.shiwansh.ecommerce.infrastructure.persistence.repository.UserJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryAdapter implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    public UserRepositoryAdapter(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public User save(User user) {

        UserEntity entity = toEntity(user);

        UserEntity savedEntity = userJpaRepository.save(entity);

        return toDomain(savedEntity);
    }

    @Override
    public Optional<User> findById(Long id) {

        return userJpaRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {

        return userJpaRepository.findByEmail(email)
                .map(this::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {

        return userJpaRepository.existsByEmail(email);
    }

    @Override
    public void deleteById(Long id) {

        userJpaRepository.deleteById(id);
    }

    private UserEntity toEntity(User user) {

        UserEntity entity = new UserEntity();

        entity.setId(user.getId());
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        entity.setRole(user.getRole());
        entity.setActive(user.isActive());

        return entity;
    }

    private User toDomain(UserEntity entity) {

        return new User(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getRole(),
                entity.isActive()
        );
    }
}