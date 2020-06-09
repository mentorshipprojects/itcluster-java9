package forest.detector.service;

import forest.detector.dao.entity.Tract;
import forest.detector.dao.repository.TractRepository;

import javax.sql.DataSource;

public class TractService {

    private final TractRepository tractRepository;

    public TractService(DataSource dataSource) {
        this.tractRepository = new TractRepository(dataSource);
    }

    public void save(Tract tract){
        tractRepository.save(tract);
    }

    public Tract check(Tract tract){
        return tractRepository.check(tract);
    }

    public void update(Tract tract){
        tractRepository.update(tract);
    }
}
