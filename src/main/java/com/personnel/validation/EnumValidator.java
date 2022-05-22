package com.personnel.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = EnumValidatorConstraint.class)
@Retention(RUNTIME)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
public @interface EnumValidator {

    Class<? extends Enum<?>> enumClass();
    String message() default "must be any of enum 'Personal, Verkauf, Entwicklung'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
