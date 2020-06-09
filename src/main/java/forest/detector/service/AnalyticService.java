package forest.detector.service;

import forest.detector.dao.entity.Stat;
import forest.detector.dao.repository.AnalyticsRepository;

import javax.sql.DataSource;
import java.util.List;

public class AnalyticService {

    private final AnalyticsRepository analyticsRepository;

    public AnalyticService(DataSource dataSource) {
        this.analyticsRepository =  new AnalyticsRepository(dataSource);
    }

    public List<Stat> statCuttingType(int year){
        return analyticsRepository.statCuttingType(year);
    }

    public List<Stat> statForestUser(int year){
        return analyticsRepository.statForestUser(year);
    }

    public List<Stat> statForestry(int year){
        return analyticsRepository.statForestry(year);
    }

    public List<String> listOfForestUser(){
        return analyticsRepository.listOfForestUser();
    }

    public List<String> listOfForestry(String forestUser){
        return analyticsRepository.listOfForestry(forestUser);
    }
}
