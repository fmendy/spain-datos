package es.spain.datos.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import es.spain.datos.dto.ComboDTO;
import es.spain.datos.service.TipoMatriculaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Tipo Matrículas", description = "Operaciones sobre tipos de matriculas")
@RestController
@RequestMapping("/api/tipo-matricula")
public class TipoMatriculaController {

	@Autowired
	private TipoMatriculaService tipoMatriculaService;

	@Operation(summary = "Lista de tipos de matrículas ", description = "Devuelve una lista con los tipos de matrículas disponibles")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operación correcta, devuelve una lista de tipos de matrículas ", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "400", description = "Error al optener tipos de matrícula ", content = @Content(mediaType = "application/json")) })
	@GetMapping("/validar")
	public ResponseEntity<List<ComboDTO>> getTiposDeMatricula() {
		try {
			return ResponseEntity.ok(tipoMatriculaService.getListComboDTO());
		} catch (Exception e) {
			throw new IllegalStateException("Error al obtener tipos de matrícula: " + e.getMessage(), e);
		}

	}
}
