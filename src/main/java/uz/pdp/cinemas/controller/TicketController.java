package uz.pdp.cinemas.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinemas.entity.Ticket;
import uz.pdp.cinemas.service.impl.TicketServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/ticket")
@RequiredArgsConstructor
public class TicketController {
    private static TicketServiceImpl ticketServiceImpl;

    @PostMapping("/create")
    public ResponseEntity<Ticket> create(@RequestBody Ticket ticket){
        Ticket ticket1 = ticketServiceImpl.create(ticket);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticket1);
    }

    @GetMapping("/getId {id}")
    public ResponseEntity<Ticket> getId (@PathVariable Long id){
        Ticket ticket = ticketServiceImpl.getById(id);
        return ResponseEntity.ok(ticket);
    }

    @GetMapping("/getByUpcoming {upcoming}")
    public ResponseEntity<List<Ticket>> getByUpcoming (@PathVariable boolean upcoming){
        List<Ticket> tickets = ticketServiceImpl.getByUpcoming(upcoming);
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Ticket>> getAll(){
        List<Ticket> tickets = ticketServiceImpl.getAll();
        return ResponseEntity.ok(tickets);
    }





}
