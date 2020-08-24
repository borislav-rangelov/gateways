package com.example.demo.util.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class EnumElementValidator implements ConstraintValidator<EnumElement, String> {
   private Set<String> names;
   public void initialize(EnumElement constraint) {
      names = Arrays.stream(constraint.type().getEnumConstants())
              .map(x -> (Enum<?>)x)
              .map(Enum::name)
              .collect(Collectors.toSet());
   }

   public boolean isValid(String obj, ConstraintValidatorContext context) {
      return names.contains(obj);
   }
}
