package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

@QuarkusTest
public class EntityValidationTest {

  @Inject
  Validator validator;

  @Test
  public void simpleValidation() {
    Book entity = new Book();
    entity.authorEmail = "lucas.scharf@gmail.com";
    entity.description = "description";
    entity.title = "Title";
    entity.price = 4f;

    Set<ConstraintViolation<Book>> constraintViolations = validator.validate(entity);
    assertEquals(0, constraintViolations.size());
  }

  @Test
  public void simpleValidationWithError() {
    Book entity = new Book();
    entity.authorEmail = "lucas.scharf@gmail.com";
    entity.description = "description";
    entity.title = "Title";
    entity.price = 4.111f;

    Set<ConstraintViolation<Book>> constraintViolations = validator.validate(entity);
    assertEquals(1, constraintViolations.size());
  }
}