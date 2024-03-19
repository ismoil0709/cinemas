package uz.pdp.cinemas.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.cinemas.entity.Theater;
import uz.pdp.cinemas.service.TheaterService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("${api.version}/theater")
@RequiredArgsConstructor
public class TheaterController {
    private final TheaterService theaterService;
    @PostMapping("/create")
    public ResponseEntity<Theater> create(@RequestBody @Valid Theater theater){
        return ResponseEntity.ok(theaterService.save(theater));
    }
    @PutMapping("/update")
    public ResponseEntity<Theater> update(@RequestBody @Valid Theater theater){
        return ResponseEntity.ok(theaterService.update(theater));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable @NotNull Long id){
        theaterService.delete(id);
        return ResponseEntity.ok(Map.of("message","Successfully"));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Theater> getById(@PathVariable @NotNull Long id){
        return ResponseEntity.ok(theaterService.getById(id));
    }
    @GetMapping("/all")
    public ResponseEntity<List<Theater>> getAll(){
        return ResponseEntity.ok(theaterService.getAll());
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<Theater> getByName(@PathVariable @NotBlank String name){
        return ResponseEntity.ok(theaterService.getByName(name));
    }
}
