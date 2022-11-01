package ru.korobko.warehouse.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegistrationForm {

    @Size(min = 4, max = 20, message = "Длина имени не должна быть менее 4 символов или превышать 20 символов")
    @Pattern(regexp = "[a-zA-Z0-9_\\.-]*", message = "В имени пользователя допускается использовать только буквы, цифры, нижние подчёркивания, точки и дефисы")
    private String username;

    @Size(min = 4, max = 20, message = "Длина пароля не должна быть менее 4 символов или превышать 20 символов")
    private String password;

    private String passwordConfirmation;
}
