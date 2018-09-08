package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/lessons")
public class LessonsController {

    private final LessonRepository repository;

    public LessonsController(LessonRepository repository){
        this.repository = repository;
    }

    @GetMapping("")
    public Iterable<Lesson> all() {
        return this.repository.findAll();
    }

    @PostMapping("")
    public Lesson create(@RequestBody Lesson lesson){
        return this.repository.save(lesson);
    }

    @GetMapping("/{id}")
    public Optional<Lesson> findByID(@PathVariable long id){
        return this.repository.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteByID(@PathVariable long id){
        this.repository.deleteById(id);
    }

    @PatchMapping("/{id}")
    public Lesson updateByID(@PathVariable long id, @RequestBody Map<String, String> body) throws Exception{

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Optional<Lesson> optionalLesson = this.repository.findById(id);

        if (optionalLesson.isPresent()){
            Lesson lesson = optionalLesson.get();
            lesson.setTitle(body.get("title"));
            lesson.setDeliveredOn(formatter.parse(body.get("deliveredOn")));
            return this.repository.save(lesson);
        }
        else
            return null;
    }

}
