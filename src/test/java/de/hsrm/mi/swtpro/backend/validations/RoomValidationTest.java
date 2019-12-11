package de.hsrm.mi.swtpro.backend.validations;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.hsrm.mi.swtpro.backend.model.Building;
import de.hsrm.mi.swtpro.backend.model.Room;


public class RoomValidationTest {

    private Validator validator;
    private Room room;
    private Building building;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        building = Building.builder()
        .build();
    }

    @Test
    public void whenNumberNotNull_thenNoConstraintViolation() {
        room = Room.builder()
        .id(17)
        .number(17)
        .seats(0)
        .building(building)
        .build();
        Set<ConstraintViolation<Room>> violations = validator.validate(room);

        Assert.assertEquals(0, violations.size());
    }
    @Test
    public void whenSeatsMinNull_thenNoConstraintViolation() {
        room = Room.builder()
        .id(17)
        .number(17)
        .seats(0)
        .building(building)
        .build();
        Set<ConstraintViolation<Room>> violations = validator.validate(room);

        Assert.assertEquals(0, violations.size());
    }
    @Test
    public void whenSeatsNotMinNull_thenConstraintViolation() {
        room = Room.builder()
        .id(17)
        .number(17)
        .seats(-13)
        .building(building)
        .build();
        Set<ConstraintViolation<Room>> violations = validator.validate(room);

        Assert.assertEquals(1, violations.size());
    }
    @Test
    public void whenSeatsMoreThanNull_thenNoConstraintViolation() {
        room = Room.builder()
        .id(17)
        .number(17)
        .seats(13)
        .building(building)
        .build();
        Set<ConstraintViolation<Room>> violations = validator.validate(room);

        Assert.assertEquals(0, violations.size());
    }
    @Test
    public void whenBuildingNotNull_thenNoConstraintViolation() {
        room = Room.builder()
        .id(17)
        .number(17)
        .seats(0)
        .building(building)
        .build();
        Set<ConstraintViolation<Room>> violations = validator.validate(room);

        Assert.assertEquals(0, violations.size());
    }
    @Test
    public void whenBuildingNull_thenConstraintViolation() {
        room = Room.builder()
        .id(17)
        .number(17)
        .seats(0)
        .building(null)
        .build();
        Set<ConstraintViolation<Room>> violations = validator.validate(room);

        Assert.assertEquals(1, violations.size());
    }
}
