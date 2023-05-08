package pl.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pl.app.entity.Pet;

@RepositoryRestResource(path = "pets")
public interface PetRepository extends JpaRepository<Pet, Long> {

}
