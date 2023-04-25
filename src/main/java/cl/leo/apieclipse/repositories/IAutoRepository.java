package cl.leo.apieclipse.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.leo.apieclipse.models.Auto;

@Repository
public interface IAutoRepository extends JpaRepository<Auto, Long> {
	Optional<Auto> findByMarca(String marca);
	Optional<Auto> findByModelo(String modelo);
	Optional<Auto> findByAnio(Long anio);
	Optional<Auto> findByColor(String color);
}
