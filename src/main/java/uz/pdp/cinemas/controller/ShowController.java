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
import uz.pdp.cinemas.entity.Show;
import uz.pdp.cinemas.service.ShowService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("${api.version}/show")
@RequiredArgsConstructor
public class ShowController {
    private final ShowService showService;
    @PostMapping("/create")
    public ResponseEntity<Show> create(@RequestBody @Valid Show show) {
        return ResponseEntity.ok(showService.save(show));
    }
    @PutMapping("/update")
    public ResponseEntity<Show> update(@RequestBody @Valid Show show){
        return ResponseEntity.ok(showService.update(show));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable @NotNull Long id){
        showService.delete(id);
        return ResponseEntity.ok(Map.of("message","Successfully"));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Show> getById(@PathVariable @NotNull Long id){
        return ResponseEntity.ok(showService.getById(id));
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<Show> getByName(@PathVariable @NotBlank String name){
        return ResponseEntity.ok(showService.getByName(name));
    }
    @GetMapping("/all")
    public ResponseEntity<List<Show>> getAll(){
        return ResponseEntity.ok(showService.getALl());
    }
    @GetMapping("/all/movieId/{id}")
    public ResponseEntity<List<Show>> getAllByMovie(@PathVariable @NotNull Long id){
        return ResponseEntity.ok(showService.getAllByMovie(id));
    }
    @GetMapping("/all/theaterId/{id}")
    public ResponseEntity<List<Show>> getAllByTheater(@PathVariable @NotNull Long id){
        return ResponseEntity.ok(showService.getAllByTheater(id));
    }
}
