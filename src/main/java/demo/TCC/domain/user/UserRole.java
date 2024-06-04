package demo.TCC.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
    LOCADOR("locador"),
    LOCATARIO("locatario");

    private final String role;

}
