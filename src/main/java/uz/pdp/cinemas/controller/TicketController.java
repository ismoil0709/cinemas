package uz.pdp.cinemas.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.cinemas.entity.Ticket;
import uz.pdp.cinemas.service.TicketService;

import java.util.List;

@RestController
@RequestMapping("${api.version}/ticket")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;
    @PostMapping("/create")
    public ResponseEntity<Ticket> create(@RequestBody @Valid Ticket ticket){
        return ResponseEntity.ok(ticketService.create(ticket));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getById(@PathVariable @NotNull Long id){
        return ResponseEntity.ok(ticketService.getById(id));
    }
    @GetMapping("/upcoming/{upcoming}")
    public ResponseEntity<List<Ticket>> getByUpcoming(@PathVariable @NotNull boolean upcoming){
        return ResponseEntity.ok(ticketService.getByUpcoming(upcoming));
    }
    @GetMapping("/all")
    public ResponseEntity<List<Ticket>> getAll() {
        return ResponseEntity.ok(ticketService.getAll());
    }
    @GetMapping("/download/{id}")
    public void download(@PathVariable @NotNull String id) {

    }
}
