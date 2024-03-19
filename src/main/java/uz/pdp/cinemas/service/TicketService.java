package uz.pdp.cinemas.service;

import org.springframework.stereotype.Service;
import uz.pdp.cinemas.entity.Ticket;

import java.util.List;

@Service
public interface TicketService {
    Ticket create(Ticket ticket);
    Ticket getById(Long id);
    List<Ticket> getAll();
    List<Ticket> getByUpcoming(boolean upcoming);
    void download(Long id);
}
