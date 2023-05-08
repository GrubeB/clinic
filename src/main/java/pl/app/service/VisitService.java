package pl.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.app.dto.CreateVisitDto;
import pl.app.dto.UpdateVisitDto;
import pl.app.entity.Person;
import pl.app.entity.Pet;
import pl.app.entity.Visit;
import pl.app.repository.PersonRepository;
import pl.app.repository.PetRepository;
import pl.app.repository.VisitRepository;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VisitService {
    private final VisitRepository visitRepository;
    private final PetRepository petRepository;
    private final PersonRepository personRepository;

    public List<Visit> fetchAll() {
        return visitRepository.findAll();
    }

    public Visit fetchById(Long id) {
        Assert.notNull(id, "Id must not be null.");
        Optional<Visit> visit = visitRepository.findById(id);
        return visit.orElseThrow(() -> new RuntimeException("Not found visit with id: " + id));
    }

    public Visit create(CreateVisitDto dto) {
        Pet pet = petRepository.findById(dto.getPetId()).orElse(null);
        Person person = personRepository.findById(dto.getPersonId()).orElse(null);
        Visit newVisit = new Visit(null, dto.getVeterinarianName(), dto.getClinicName(), Instant.now(), pet, person);
        return visitRepository.save(newVisit);
    }

    public Visit update(Long id, UpdateVisitDto dto) {
        Assert.notNull(id, "Id must not be null.");
        Visit existingEntity = visitRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found visit with id: " + id));
        updateVisits(dto, existingEntity);
        return visitRepository.save(existingEntity);
    }

    public void delete(Long id) {
        Assert.notNull(id, "Id must not be null.");
        visitRepository.deleteById(id);
    }

    private void updateVisits(UpdateVisitDto dto, Visit existingEntity) {
        if (Objects.nonNull(dto.getVeterinarianName())) {
            existingEntity.setVeterinarianName(dto.getVeterinarianName());
        }
        if (Objects.nonNull(dto.getClinicName())) {
            existingEntity.setClinicName(dto.getClinicName());
        }
        if (Objects.nonNull(dto.getDate())) {
            existingEntity.setDate(dto.getDate());
        }
        if (Objects.nonNull(dto.getPetId())) {
            petRepository.findById(dto.getPetId()).ifPresent(existingEntity::setPet);
        }
        if (Objects.nonNull(dto.getPersonId())) {
            personRepository.findById(dto.getPersonId()).ifPresent(existingEntity::setPerson);
        }
    }
}
