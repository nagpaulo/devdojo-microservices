package br.com.microservices.course.endpoint.controller;


import br.com.microservices.course.endpoint.services.CourseService;
import br.com.microservices.model.Course;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("v1/admin/course")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseController {
    private final CourseService courseService;

    @GetMapping
    public Iterable<Course> list(
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "page", defaultValue = "0") int pag,
            @RequestParam(value = "ord", defaultValue = "title") String ord,
            @RequestParam(value = "dir", defaultValue = "ASC") String dir
    ) {
        PageRequest pageRequest = PageRequest.of(pag, size, Sort.Direction.valueOf(dir), ord);
        return this.courseService.list(pageRequest);
    }

}
