package uz.pdp.cinemas.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinemas.entity.Show;
import uz.pdp.cinemas.exception.NotFoundException;
import uz.pdp.cinemas.repository.ShowRepository;
import uz.pdp.cinemas.service.impl.ShowServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/show")
@RequiredArgsConstructor
public class ShowController {
    private final ShowServiceImpl showServiceImpl;
    private final ShowRepository showRepository;

    @PostMapping("/create")
    public ResponseEntity<Show> createShow(Show show) {
        Show show1 = showServiceImpl.save(show);
        return ResponseEntity.status(HttpStatus.CREATED).body(show1);
    }

    @GetMapping("/getId {id}")
    public ResponseEntity<Show> get(@PathVariable Long id){
        Show show = showServiceImpl.getById(id);
        return ResponseEntity.ok(show);
    }

    @GetMapping("/getName {name}")
    public ResponseEntity<Show> getName(@PathVariable String name){
        Show show = showServiceImpl.getByName(name);
        return ResponseEntity.ok(show);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Show>> getAll(){
        List<Show> shows = showServiceImpl.getALl();
        return ResponseEntity.ok(shows);
    }

    @GetMapping("/getAllMovie {id}")
    public ResponseEntity<List<Show>> getAllByMovie(@PathVariable Long id){
        List<Show> shows = showServiceImpl.getAllByMovie(id);
        return ResponseEntity.ok(shows);
    }

    @GetMapping("/getAllTheater {id}")
    public ResponseEntity<List<Show>> getAllByTheater(@PathVariable Long id){
        List<Show> shows = showServiceImpl.getAllByTheater(id);
        return ResponseEntity.ok(shows);
    }

    @PutMapping("/update {id}")
    public ResponseEntity<Show> update(@PathVariable Long id){
        Show show = showRepository.findById(id).orElseThrow(() -> new NotFoundException("Show not found"));
        Show updated = showServiceImpl.update(show);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        showServiceImpl.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Show deleted successfully");
    }


}
