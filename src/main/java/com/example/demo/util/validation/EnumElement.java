package com.example.demo.util.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EnumElementValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumElement {

    Class<?> type();

    String message() default "Invalid IPv4 address.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
