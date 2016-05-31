package com.simpleteam;

import com.simpleteam.entity.User;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class HibernateValidationTest {
    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void passwordTest() {
        User user = new User();
        user.setPassword("failedPassword");
        Set<ConstraintViolation<User>> constraintViolations = validator.validate( user );

        assertEquals(1, constraintViolations.size());
        assertEquals(
                "must contain at least 2 digits and one \"!\" symbol",
                constraintViolations.iterator().next().getMessage()
        );

        user.setPassword("WithoutDigits!");
        constraintViolations = validator.validate( user );
        assertEquals(1, constraintViolations.size());
        assertEquals(
                "must contain at least 2 digits and one \"!\" symbol",
                constraintViolations.iterator().next().getMessage()
        );

        user.setPassword("WithoutExclamation314");
        constraintViolations = validator.validate( user );
        assertEquals(1, constraintViolations.size());
        assertEquals(
                "must contain at least 2 digits and one \"!\" symbol",
                constraintViolations.iterator().next().getMessage()
        );

        user.setPassword("correct99Password!");
        constraintViolations = validator.validate( user );
        assertEquals(0, constraintViolations.size());

    }

    @Test
    public void emailTest() {
        User user = new User();
        user.setEmail("wrongEmail.com");
        Set<ConstraintViolation<User>> constraintViolationsFalse = validator.validate( user );
        assertEquals(1, constraintViolationsFalse.size());
        assertEquals(
                "not a well-formed email address",
                constraintViolationsFalse.iterator().next().getMessage()
        );

        user.setEmail("good@gmail.com");
        Set<ConstraintViolation<User>> constraintViolationsTrue = validator.validate( user );
        assertEquals(0, constraintViolationsTrue.size());
    }

}
