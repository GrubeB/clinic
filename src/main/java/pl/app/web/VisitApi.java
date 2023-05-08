package pl.app.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.app.dto.CreateVisitDto;
import pl.app.dto.UpdateVisitDto;
import pl.app.entity.Visit;
import pl.app.service.VisitService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(VisitApi.resourcePath)
@RequiredArgsConstructor
public class VisitApi {
    public static final String resourceName = "visits";
    public static final String resourcePath = "/api/" + resourceName;
    private final VisitService service;

    @GetMapping
    private ResponseEntity<List<Visit>> fetchAll() {
        List<Visit> visits = service.fetchAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(visits);
    }

    @GetMapping(path = "/{id}")
    private ResponseEntity<Visit> fetchById(@PathVariable Long id) {
        Visit visit = service.fetchById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(visit);
    }

    @DeleteMapping(path = "/{id}")
    private ResponseEntity<Long> deleteById(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(id);
    }

    @PostMapping
    private ResponseEntity<Visit> create(@RequestBody @Valid CreateVisitDto dto, HttpServletRequest request) {
        Visit visit = service.create(dto);
        return ResponseEntity
                .created(URI.create(request.getRequestURI() + '/' + visit.getId()))
                .body(visit);
    }

    @PutMapping(path = "/{id}")
    private ResponseEntity<Visit> update(@RequestBody @Valid UpdateVisitDto dto, @PathVariable Long id) {
        Visit mergedVisit = service.update(id, dto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mergedVisit);
    }
}
