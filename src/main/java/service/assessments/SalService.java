package service.assessments;


import com.payAm.core.ebean.BaseService;
import dao.assessments.SalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalService extends BaseService {

    @Autowired
    SalRepository repository;

    public SalService() {
        setRepository(this.repository);
    }
}
