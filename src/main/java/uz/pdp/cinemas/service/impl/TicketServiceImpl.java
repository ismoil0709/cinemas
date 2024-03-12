package uz.pdp.cinemas.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.cinemas.entity.Ticket;
import uz.pdp.cinemas.exception.InvalidArgumentException;
import uz.pdp.cinemas.exception.NotFoundException;
import uz.pdp.cinemas.repository.TicketRepository;
import uz.pdp.cinemas.service.TicketService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    @Override
    public Ticket create(Ticket ticket) {
        if (ticket.getId() != null)
            throw new InvalidArgumentException("Id");
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket getById(Long id) {
       return ticketRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("Ticket")
        );
    }

    @Override
    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    @Override
    public List<Ticket> getByUpcoming(boolean upcoming) {
        return ticketRepository.getByUpcoming(upcoming);
    }
}
