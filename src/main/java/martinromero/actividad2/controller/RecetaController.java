package martinromero.actividad2.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import martinromero.actividad2.Repository.RecetaRepository;
import martinromero.actividad2.model.Receta;

@RestController
@RequestMapping("/recetas")
public class RecetaController {

    private final RecetaRepository recetaRepository;

    public RecetaController(RecetaRepository recetaRepository) {
        this.recetaRepository = recetaRepository;
    }

    @GetMapping
    public ResponseEntity<List<Receta>> obtenerRecetas() {
        return ResponseEntity.ok(recetaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receta> obtenerReceta(@PathVariable Long id) {
        return recetaRepository.findById(id)
                .map(receta -> ResponseEntity.ok(receta))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Receta>> buscarPorNombre(
            @RequestParam String nombre) {

        List<Receta> recetas =
                recetaRepository.findByNombreContainingIgnoreCase(nombre);

        return ResponseEntity.ok(recetas);
    }

    @PostMapping
    public ResponseEntity<Receta> crearReceta(@RequestBody Receta receta) {

        Receta nuevaReceta = recetaRepository.save(receta);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(nuevaReceta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Receta> actualizarReceta(
            @PathVariable Long id,
            @RequestBody Receta receta) {

        Receta recetaExistente =
                recetaRepository.findById(id).orElse(null);

        if (recetaExistente == null) {
            return ResponseEntity.notFound().build();
        }

        recetaExistente.setNombre(receta.getNombre());
        recetaExistente.setDescripcion(receta.getDescripcion());
        recetaExistente.setCategoria(receta.getCategoria());
        recetaExistente.setTiempoPreparacion(
                receta.getTiempoPreparacion()
        );

        Receta recetaActualizada =
                recetaRepository.save(recetaExistente);

        return ResponseEntity.ok(recetaActualizada);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarReceta(@PathVariable Long id) {

        if (!recetaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        recetaRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}