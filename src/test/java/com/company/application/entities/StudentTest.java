package com.company.application.entities;

import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.*;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentTest {
    @Test
    public void studentValidator() {
        Validator validator = ValidatorBuilder.create().with(new GetterMustExistRule()).with(new SetterMustExistRule())
                .with(new SetterTester()).with(new GetterTester()).with(new NoStaticExceptFinalRule())
                .with(new NoFieldShadowingRule()).with(new NoPublicFieldsExceptStaticFinalRule())
                .with(new TestClassMustBeProperlyNamedRule()).build();

        validator.validate("com.company.application.entities");
    }

    @Test
    public void testToString() {
        Student student = new Student();
        Long id = 1L;
        String firstName = "Krish";
        String lastName = "Karlapudi";
        String email = "krish@gmail.com";

        student.setId(id);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);

        String expectedString = "Student{" +
            "id=" + id +
                    ", firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", email='" + email + '\'' +
                    "}";
        assertEquals(expectedString,student.toString());

    }
}
