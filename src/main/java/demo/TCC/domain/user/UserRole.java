package demo.TCC.domain.user;

public enum UserRole {
    LOCATARIO("locatario"),
    LOCADOR("locador");

    private String role;

    UserRole(String role){
        this.role = role;
    }

}
