package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Locale;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LessonControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    LessonRepository lessonRepository;

    @Test
    @Transactional
    @Rollback
    public void testCreate() throws Exception{

        MockHttpServletRequestBuilder request = post("/lessons")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content("{\n" +
                                                    "  \"title\": \"Java\",\n" +
                                                    "  \"deliveredOn\": \"2010-01-14\"\n" +
                                                    "}");

        this.mvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", instanceOf(Number.class)));

    }

    @Test
    @Transactional
    @Rollback
    public void testList() throws Exception{

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        Lesson lesson = new Lesson();
        lesson.setTitle("Colors");
        lesson.setDeliveredOn(formatter.parse("2009-01-14"));
        lessonRepository.save(lesson);

        MockHttpServletRequestBuilder request = get("/lessons")
                                                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", equalTo(lesson.getId().intValue())));
    }
}
