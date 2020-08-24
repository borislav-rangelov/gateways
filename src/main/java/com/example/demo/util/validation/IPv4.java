package com.example.demo.util.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IPv4Validator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IPv4 {

    String message() default "Invalid IPv4 address.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
