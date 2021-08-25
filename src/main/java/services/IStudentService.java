package services;

import models.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IStudentService extends IGeneralService<Student>{
    Page<Student> findAll(Pageable pageable);
    Iterable<Student> findAllByName(String name);
}
