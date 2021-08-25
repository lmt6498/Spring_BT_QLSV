package repository;

import models.Classes;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IClassesRepo extends PagingAndSortingRepository<Classes,Integer> {
}
