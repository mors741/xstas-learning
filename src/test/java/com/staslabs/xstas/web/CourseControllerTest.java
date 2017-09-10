package com.staslabs.xstas.web;

import com.staslabs.xstas.data.CourseRepository;
import com.staslabs.xstas.data.entity.Course;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class CourseControllerTest {

    @Test
    public void shouldShowRecentCourses() throws Exception {
        List<Course> courseList = createCourseList();

        CourseRepository repository = mock(CourseRepository.class);
        when(repository.getPopular(20)).thenReturn(courseList);

        CourseController controller = new CourseController(repository, null, null);

        MockMvc mockMvc = standaloneSetup(controller)
                .setSingleView(
                        new InternalResourceView("/WEB-INF/views/spittles.jsp")
                ).build();

        mockMvc.perform(get("/spittles"))
                .andExpect(view().name("spittles"))
                .andExpect(model().attributeExists("courseList"))
                .andExpect(model().attribute("courseList", hasItems(courseList.toArray())));
    }

    @Test
    public void shouldShowPagedCourses() throws Exception {
        List<Course> courseList = createCourseList();

        CourseRepository repository = mock(CourseRepository.class);
        when(repository.getPopular(10)).thenReturn(courseList);

        CourseController controller = new CourseController(repository, null, null);

        MockMvc mockMvc = standaloneSetup(controller)
                .setSingleView(
                        new InternalResourceView("/WEB-INF/views/spittles.jsp")
                ).build();

        mockMvc.perform(get("/spittles?max=238900&count=10"))
                .andExpect(view().name("spittles"))
                .andExpect(model().attributeExists("courseList"))
                .andExpect(model().attribute("courseList", hasItems(courseList.toArray())));
    }

    private List<Course> createCourseList() {
        return Collections.emptyList();
    }

}