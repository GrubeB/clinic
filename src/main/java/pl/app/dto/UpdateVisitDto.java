package pl.app.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class UpdateVisitDto {
    private String veterinarianName;
    private String clinicName;
    private Instant date;
    private Long petId;
    private Long personId;
}
