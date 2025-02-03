package mil_sabores_backend.domain.dto;

import jakarta.persistence.Column;
import lombok.Data;
import mil_sabores_backend.domain.entity.Rol;
import mil_sabores_backend.domain.entity.enums.UserStatus;

@Data
public class UserDto {

    private Long id;

    private String fullName;

    private String ci;

    private String password;

    private UserStatus status;

    private Rol rolId;
}
