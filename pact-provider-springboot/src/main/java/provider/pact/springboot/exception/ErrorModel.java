package provider.pact.springboot.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorModel {
    private String code;
    private String errorMessage;

}
