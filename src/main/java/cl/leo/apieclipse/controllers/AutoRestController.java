package cl.leo.apieclipse.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.leo.apieclipse.models.Auto;
import cl.leo.apieclipse.services.AutoImplService;

@RestController
@RequestMapping("/api/auto/")
public class AutoRestController {
	private AutoImplService autoService;

	@Autowired
	public AutoRestController(AutoImplService autoService) {
		super();
		this.autoService = autoService;
	}

	@PostMapping(value = "crear", headers = "Accept=application/json")
	public void crearAuto(@RequestBody Auto auto) {
		autoService.crearAuto(auto);
	}

	@GetMapping(value = "listar", headers = "Accept=application/json")
	public List<Auto> listarAutos() {
		return autoService.listaAutos();
	}

	@GetMapping(value = "listarId/{id}", headers = "Accept=application/json")
	public Optional<Auto> filtratAutoPorId(@PathVariable Long id) {
		return autoService.buscarAutoPorId(id);
	}

	@GetMapping(value = "listarMarca/{marca}", headers = "Accept=application/json")
	public List<Auto> bucarPorMarca(@PathVariable String marca) {
		return autoService.buscarPorMarca(marca);
	}

	@GetMapping(value = "listarColor/{color}", headers = "Accept=application/json")
	public List<Auto> buscarPorColor(@PathVariable String color) {
		return autoService.buscarPorColor(color);
	}

	@GetMapping(value = "listarModelo/{modelo}", headers = "Accept=application/json")
	public List<Auto> buscarPorModelo(@PathVariable String modelo) {
		return autoService.buscarPorModelo(modelo);
	}

	@GetMapping(value = "listarAnio/{anio}", headers = "Accept=application/json")
	public List<Auto> buscarPorAnio(@PathVariable Long anio) {
		return autoService.buscarPorAnio(anio);
	}

	@PutMapping(value = "actualizar", headers = "Accept=application/json")
	public void actualizarAuto(@RequestBody Auto auto) {
		autoService.actualizarAuto(auto);
	}

	@DeleteMapping(value = "eliminar/{id}", headers = "Accept=application/json")
	public void eliminarAuto(@PathVariable Long id) {
		autoService.eliminarAuto(id);
	}
}
