package lt.timofey.cinemaSystem.payload;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lt.timofey.cinemaSystem.annotations.PasswordMatches;

@Data
@PasswordMatches
public class SignupRequest {
    @Email(message = "it should be email")
    @NotEmpty(message = "please enter email")
    private String email;
    @NotEmpty(message = "Please enter your username")
    private String username;
    @NotEmpty(message = "Password is required")
    @Size(min = 6)
    private String password;
    private String confirmPassword;
}
