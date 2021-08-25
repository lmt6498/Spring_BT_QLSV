package services;

import models.Classes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.IClassesRepo;

import java.util.Optional;

@Service
public class ClassesService implements IClassesService{
    @Autowired
    private IClassesRepo classesRepo;

    @Override
    public Iterable<Classes> findAll() {
        return classesRepo.findAll();
    }

    @Override
    public Optional<Classes> findById(Integer id) {
        return classesRepo.findById(id);
    }

    @Override
    public void save(Classes classes) {
        classesRepo.save(classes);

    }

    @Override
    public void delete(Classes classes) {
        classesRepo.delete(classes);
    }
}
