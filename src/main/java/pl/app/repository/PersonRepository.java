package pl.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pl.app.entity.Person;

@RepositoryRestResource(collectionResourceRel = "persons", path = "persons")
public interface PersonRepository extends JpaRepository<Person, Long> {

}
