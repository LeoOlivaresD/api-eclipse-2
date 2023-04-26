package cl.leo.apieclipse.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.leo.apieclipse.models.Auto;
import cl.leo.apieclipse.repositories.IAutoRepository;

@Service
public class AutoImplService {
	private IAutoRepository repoAuto;

	@Autowired
	public AutoImplService(IAutoRepository repoAuto) {
		super();
		this.repoAuto = repoAuto;
	}

	public void crearAuto(Auto auto) {
		repoAuto.save(auto);
	}

	public List<Auto> listaAutos() {
		return repoAuto.findAll();
	}

	public Optional<Auto> buscarAutoPorId(Long id) {
		return repoAuto.findById(id);
	}

	public List<Auto> buscarPorColor(String color) {
		return repoAuto.findByColor(color);
	}

	public List<Auto> buscarPorMarca(String marca) {
		return repoAuto.findByMarca(marca);
	}

	public List<Auto> buscarPorAnio(Long anio) {
		return repoAuto.findByAnio(anio);
	}

	public List<Auto> buscarPorModelo(String modelo) {
		return repoAuto.findByModelo(modelo);
	}

	public void actualizarAuto(Auto auto) {
		repoAuto.save(auto);
	}

	public void eliminarAuto(Long id) {
		repoAuto.deleteById(id);
	}
}
