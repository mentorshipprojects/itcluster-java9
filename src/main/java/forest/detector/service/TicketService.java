package forest.detector.service;

import forest.detector.dao.entity.Ticket;
import forest.detector.dao.repository.TicketRepository;
import forest.detector.dao.repository.UserRepository;

import java.util.List;

public class TicketService {

    private final TicketRepository repo;

    public TicketService(javax.sql.DataSource dataSource) {
        this.repo = new TicketRepository(dataSource);
    }

    public List<Ticket> getTickets() {
        return repo.getTickets();
    }
}
