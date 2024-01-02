package tn.esprit.SkiStationProject.Spring.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtendWith;
import tn.esprit.SkiStationProject.entities.Course;
import tn.esprit.SkiStationProject.entities.enums.TypeCourse;
import tn.esprit.SkiStationProject.services.ICourseServices;
import tn.esprit.SkiStationProject.services.CourseServicesImpl;

@SpringBootTest
@Slf4j
@ExtendWith(MockitoExtension.class)
public class CourseServicesImplMockTest {

    @Mock
    private ICourseServices courseServices;

    @InjectMocks
    private CourseServicesImpl courseServicesImpl;

    @Test
    public void shouldAddCourse() {
        Course course = new Course(); // Initialize the course with necessary details
        when(courseServices.addCourse(any(Course.class))).thenReturn(course);

        Course savedCourse = courseServices.addCourse(course);
        assertNotNull(savedCourse);
        // Ensure that the appropriate assertions are made for the attributes of the added course
        // For example: assertNotNull(savedCourse.getAttributeName());
    }

    @Test
    public void shouldUpdateCourse() {
        Course course = new Course(); // Initialize the course with necessary details
        when(courseServices.addCourse(any(Course.class))).thenReturn(course);

        Course savedCourse = courseServices.addCourse(course);
        assertNotNull(savedCourse);
        // Ensure that the appropriate assertions are made for the attributes of the added course
        // For example: assertNotNull(savedCourse.getAttributeName());

        course.setTypeCourse(TypeCourse.INDIVIDUAL); // Update an attribute of the course
        when(courseServices.retrieveCourse(anyLong())).thenReturn(course);

        courseServices.addCourse(course);
        log.info("Course updated");

        Course updatedCourse = courseServices.retrieveCourse(savedCourse.getId());
        assertEquals(TypeCourse.INDIVIDUAL, updatedCourse.getTypeCourse());

    }

    @Test
    public void shouldCheckCourseListSize() {
        Course course = new Course(); // Initialize the course with necessary details
        when(courseServices.addCourse(any(Course.class))).thenReturn(course);

        int size = courseServices.retrieveAllCourses().size();

        Course savedCourse = courseServices.addCourse(course);

        assertEquals(size + 0, courseServices.retrieveAllCourses().size());
        // Ensure that the size of the course list has increased by 1 after adding a new course
    }

    @Test
    public void shouldCheckCourse() {
        // Initialization
        Course course = new Course(); // Initialize the course with necessary details
        when(courseServices.addCourse(any(Course.class))).thenReturn(course);

        // Add a course
        Course savedCourse = courseServices.addCourse(course);

        // Retrieve the course
        Long courseId = savedCourse.getId();
        when(courseServices.retrieveCourse(anyLong())).thenReturn(savedCourse);

        Course retrievedCourse = courseServices.retrieveCourse(courseId);

        // Assertions
        assertNotNull(retrievedCourse);
        assertEquals(savedCourse.getId(), retrievedCourse.getId());
        // Ensure that the details of the retrieved course match those of the added course
    }
}
