package uz.pdp.cinemas.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinemas.entity.Theater;
import uz.pdp.cinemas.exception.NotFoundException;
import uz.pdp.cinemas.repository.TheaterRepository;
import uz.pdp.cinemas.service.impl.TheaterServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/theater")
@RequiredArgsConstructor
public class TheaterController {
    private final TheaterServiceImpl theaterServiceImpl;
    private final TheaterRepository theaterRepository;

    @PostMapping("/create")
    public ResponseEntity<Theater> create(@RequestBody Theater theater){
        Theater theater1 = theaterServiceImpl.save(theater);
        return ResponseEntity.status(HttpStatus.CREATED).body(theater1);
    }

    @GetMapping("getId {id}")
    public ResponseEntity<Theater> getById(@PathVariable Long id){
        Theater theater = theaterServiceImpl.getById(id);
        return ResponseEntity.ok(theater);
    }

    @GetMapping("/getName {name}")
    public ResponseEntity<Theater> getByName (@PathVariable String name){
        Theater theater = theaterServiceImpl.getByName(name);
        return ResponseEntity.ok(theater);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Theater>> getAll (){
        List<Theater> theaters = theaterServiceImpl.getAll();
        return ResponseEntity.ok(theaters);
    }

    @PutMapping("/update {id}")
    public ResponseEntity<Theater> update(@PathVariable Long id){
        Theater theater = theaterRepository.findById(id).orElseThrow(() -> new NotFoundException("Theater not found"));
        Theater theater1 = theaterServiceImpl.update(theater);
        return ResponseEntity.ok(theater1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        theaterServiceImpl.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Theater deleted successfully");
    }


}
