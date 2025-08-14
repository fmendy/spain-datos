package es.spain.datos.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import es.spain.datos.service.MatriculaService;
import es.spain.datos.utils.MatriculaUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Matricula", description = "Operaciones sobre matrículas, validación")
@RestController
@RequestMapping("/api/matricula")
public class MatriculaController {

	@Autowired
	private MatriculaService matriculaService;

	@Operation(summary = "Generar matrícula", description = "Devuelve un string con una matrícula válida aleatoria")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Error al generar matrícula", content = @Content(mediaType = "application/json")) })
	@GetMapping("/generar")
	public ResponseEntity<String> generar() {
		String matricula = matriculaService.generarMatricula();
		if (!StringUtils.hasText(matricula)) {
			throw new IllegalStateException("No se pudo generar una matrícula válida");
		}
		return ResponseEntity.ok(matricula);
	}

	@Operation(summary = "Validar matrícula", description = "Devuelve un boolean indicando si la matrícula es válida o no")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Matrícula validada", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "400", description = "Error de validación de matrícula", content = @Content(mediaType = "application/json")) })
	@GetMapping("/validar")
	public ResponseEntity<Boolean> validar(@RequestParam String matricula) {
		if (!StringUtils.hasText(matricula)) {
			throw new IllegalArgumentException("La matrícula no puede estar vacía");
		}
		return ResponseEntity.ok(MatriculaUtils.validar(matricula));
	}
}
