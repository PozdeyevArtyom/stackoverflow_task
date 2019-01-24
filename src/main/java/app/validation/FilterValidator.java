package app.validation;

import app.entities.Filter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class FilterValidator implements ConstraintValidator<FilterConstraint, Filter> {
    private static List<String> orderValues;
    private static List<String> sortValues;

    public FilterValidator() {
        String[] ord = { "desc", "asc" };
        orderValues = Arrays.asList(ord);

        String[] sort = { "activity", "votes", "creation", "relevance" };
        sortValues = Arrays.asList(sort);
    }

    @Override
    public boolean isValid(Filter o, ConstraintValidatorContext constraintValidatorContext) {
        if (o.getTitle().isEmpty() &&  o.getInclude().isEmpty()) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("Title or included tags must be specified")
                    .addConstraintViolation();
            return false;
        }

        if (o.getFrom() != null && o.getTo() != null && o.getFrom().compareTo(o.getTo()) > 0) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("\"From\" date must be lower than \"To\" date")
                    .addConstraintViolation();
            return false;
        }

        if (!sortValues.contains(o.getSort())) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("Unknown sort type")
                    .addConstraintViolation();
            return false;
        }

        if (!orderValues.contains(o.getOrder())) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("Unknown order type")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
