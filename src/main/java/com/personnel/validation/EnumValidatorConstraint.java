package com.personnel.validation;

import com.personnel.model.DepartmentType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class EnumValidatorConstraint implements ConstraintValidator<EnumValidator, CharSequence> {
    private List<String> acceptedValues;

    @Override
    public void initialize(EnumValidator annotation) {
        acceptedValues = DepartmentType.getEnumOptions();
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {

        if (value == null) {

            return true;
        }

        return acceptedValues.contains(value.toString());
    }
}