package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface LessonRepository extends CrudRepository<Lesson, Long> {
    Optional<Lesson> findByTitle(String title);
    List<Lesson> findByDeliveredOnBetween(Date date1, Date date2);
}
