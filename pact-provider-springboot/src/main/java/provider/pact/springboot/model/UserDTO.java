package provider.pact.springboot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

    private Long id;

    @NotNull(message = "Owner name cannot be null")
    @NotEmpty(message = "Owner name cannot be empty")
    private String ownerName;

    @NotNull(message = "Owner e-mail cannot be null")
    @NotEmpty(message = "Owner e-mail cannot be empty")
    @Email
    private String ownerEmail;

    private String phone;

    @NotNull(message = "Owner password cannot be null")
    @NotEmpty(message = "Owner password cannot be empty")
    private String password;

}
