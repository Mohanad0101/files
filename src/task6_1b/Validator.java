package task6_1b;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

// Validator class with reflection-based validation
class Validator {

    /**
     * Validates an object based on its annotations
     * @param obj the object to validate
     * @return list of error messages (empty if validation passes)
     */
    public static List<String> validate(Object obj) {
        List<String> errors = new ArrayList<>();

        if (obj == null) {
            errors.add("Object is null");
            return errors;
        }

        // Get all fields of the object's class
        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true); // Allow access to private fields

            try {
                Object value = field.get(obj);

                // Check @NotEmpty annotation
                if (field.isAnnotationPresent(NotEmpty.class)) {
                    NotEmpty annotation = field.getAnnotation(NotEmpty.class);

                    // Check if field is String type
                    if (field.getType() != String.class) {
                        errors.add("@NotEmpty can only be applied to String fields");
                        continue;
                    }

                    String strValue = (String) value;
                    if (strValue == null || strValue.trim().isEmpty()) {
                        errors.add(annotation.message());
                    }
                }

                // Check @Range annotation
                if (field.isAnnotationPresent(Range.class)) {
                    Range annotation = field.getAnnotation(Range.class);

                    // Check if field is int type
                    if (field.getType() != int.class && field.getType() != Integer.class) {
                        errors.add("@Range can only be applied to int fields");
                        continue;
                    }

                    int intValue = ((Number) value).intValue();
                    if (intValue < annotation.min() || intValue > annotation.max()) {
                        errors.add(annotation.message());
                    }
                }

            } catch (IllegalAccessException e) {
                errors.add("Error accessing field: " + field.getName());
            }
        }

        return errors;
    }
}