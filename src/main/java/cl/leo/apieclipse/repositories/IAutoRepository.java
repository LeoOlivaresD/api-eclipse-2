package cl.leo.apieclipse.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.leo.apieclipse.models.Auto;

@Repository
public interface IAutoRepository extends JpaRepository<Auto, Long> {
	List<Auto> findByMarca(String marca);
	List<Auto> findByModelo(String modelo);
	List<Auto> findByAnio(Long anio);
	List<Auto> findByColor(String color);
}
