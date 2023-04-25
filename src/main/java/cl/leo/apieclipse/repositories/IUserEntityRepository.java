package cl.leo.apieclipse.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.leo.apieclipse.models.UsersEntity;

@Repository
public interface IUserEntityRepository extends JpaRepository<UsersEntity, Long> {
	Optional<UsersEntity> findByUsername(String username);

	Boolean existsByUsername(String username);
}
