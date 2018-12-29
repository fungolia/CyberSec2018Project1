package sec.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sec.project.domain.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    

}
