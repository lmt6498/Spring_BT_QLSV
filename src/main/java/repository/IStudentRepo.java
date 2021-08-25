package repository;

import models.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface IStudentRepo extends PagingAndSortingRepository<Student,Integer> {
    @Query(value = "select s from Student s where s.name like concat('%',:name,'%')")
    Iterable<Student> findAllByName(@Param("name") String name);
}
