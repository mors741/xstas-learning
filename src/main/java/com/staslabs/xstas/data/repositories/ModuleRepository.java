package com.staslabs.xstas.data.repositories;

import com.staslabs.xstas.data.entity.Course;
import com.staslabs.xstas.data.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModuleRepository extends JpaRepository<Module, Long> {
    List<Module> findByCourse(Course course);
}
