package pl.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.app.entity.Visit;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {

}
