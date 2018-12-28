package sec.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sec.project.domain.Signup;

public interface SignupRepository extends JpaRepository<Signup, Long> {

    @Query("SELECT s FROM Signup s WHERE s.name=?1")
    public Signup findByName(String name);
}
