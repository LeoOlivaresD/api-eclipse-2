package cl.leo.apieclipse.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.leo.apieclipse.models.Roles;

@Repository
public interface IRolesRepository extends JpaRepository<Roles, Long> {
	Optional<Roles> findByName(String name);
}
