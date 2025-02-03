package mil_sabores_backend.domain.dto;

import lombok.Data;

@Data
public class AuthRequestDto {

    private String ci;

    private String password;
}
