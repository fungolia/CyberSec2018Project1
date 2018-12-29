package sec.project.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import sec.project.domain.Signup;

@Repository
public class SignupRepositoryImpl implements SignupRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Signup findByName(String name) {
        Query query = entityManager.createNativeQuery("SELECT * FROM Signup WHERE (name='" + name + "')",Signup.class);
        List<Signup> rs = query.getResultList();
        return rs.stream().findFirst().orElse(null);
    }

}
