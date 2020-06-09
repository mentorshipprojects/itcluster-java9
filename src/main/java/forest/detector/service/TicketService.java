package forest.detector.service;

import forest.detector.dao.entity.Ticket;
import forest.detector.dao.repository.TicketRepository;
import javax.sql.DataSource;
import java.util.List;

public class TicketService {

    private final TicketRepository repo;

    public TicketService(DataSource dataSource) {
        this.repo = new TicketRepository(dataSource);
    }

    public List<Ticket> getTickets() {
        return repo.getTickets();
    }
}
