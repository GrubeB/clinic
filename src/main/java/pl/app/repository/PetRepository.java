package pl.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pl.app.entity.Person;
import pl.app.entity.Pet;

@RepositoryRestResource(collectionResourceRel = "pets", path = "pets")
public interface PetRepository extends JpaRepository<Pet, Long> {

}
