package tn.esprit.SkiStationProject.Spring.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import tn.esprit.SkiStationProject.entities.Course;
import tn.esprit.SkiStationProject.entities.enums.Support;
import tn.esprit.SkiStationProject.entities.enums.TypeCourse;
import tn.esprit.SkiStationProject.services.ICourseServices;

@SpringBootTest
@Slf4j
public class CourseServicesImplTest {

    @Autowired
    private ICourseServices courseServices;

    @Test
    public void shouldAddCourse() {
        Course course = new Course();
        course.setLevel(3);
        course.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);
        course.setSupport(Support.SKI);
        course.setPrice(50.0f);
        course.setTimeSlot(60);

        Course savedCourse = courseServices.addCourse(course);

        assertNotNull(savedCourse);
        assertEquals(3, savedCourse.getLevel());
        assertEquals(TypeCourse.COLLECTIVE_CHILDREN, savedCourse.getTypeCourse());
        assertEquals(Support.SKI, savedCourse.getSupport());
        assertEquals(50.0f, savedCourse.getPrice());
        assertEquals(60, savedCourse.getTimeSlot());
    }

    @Test
    public void shouldUpdateCourse() {
        Course course = new Course();
        course.setLevel(3);
        course.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);
        course.setSupport(Support.SKI);
        course.setPrice(50.0f);
        course.setTimeSlot(60);

        Course savedCourse = courseServices.addCourse(course);

        assertNotNull(savedCourse);

        course.setTypeCourse(TypeCourse.INDIVIDUAL);
        courseServices.updateCourse(course);
        log.info("Course updated");

        Course updatedCourse = courseServices.retrieveCourse(savedCourse.getId());
        assertEquals(TypeCourse.INDIVIDUAL, updatedCourse.getTypeCourse());

        log.info("Cleanup done");
    }

    @Test
    public void shouldCheckCourseListSize() {
        int size = courseServices.retrieveAllCourses().size();
        Course course = new Course();
        course.setLevel(3);
        course.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);
        course.setSupport(Support.SKI);
        course.setPrice(50.0f);
        course.setTimeSlot(60);

        Course savedCourse = courseServices.addCourse(course);

        int newSize = courseServices.retrieveAllCourses().size();
        assertEquals(size + 1, newSize);
    }

    @Test
    public void shouldRetrieveCourse() {
        Course course = new Course();
        course.setLevel(3);
        course.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);
        course.setSupport(Support.SKI);
        course.setPrice(50.0f);
        course.setTimeSlot(60);

        Course savedCourse = courseServices.addCourse(course);

        assertNotNull(savedCourse);

        Course retrievedCourse = courseServices.retrieveCourse(savedCourse.getId());

        assertNotNull(retrievedCourse);
        assertEquals(savedCourse.getId(), retrievedCourse.getId());

        log.info("Course retrieved");

        // Cleanup (optional)
        // If you want to remove the added course, you can do it here.
        // courseServices.deleteCourse(savedCourse.getId());
        // log.info("Cleanup done");
    }
}
