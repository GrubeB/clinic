package pl.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateVisitDto {
    @NotBlank
    private String veterinarianName;
    @NotBlank
    private String clinicName;
    @NotNull
    private Long petId;
    @NotNull
    private Long personId;
}
