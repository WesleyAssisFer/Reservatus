package com.grupoBL8.Reservatus.Professor.Repository;

import com.grupoBL8.Reservatus.Professor.Model.ProfessorModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<ProfessorModel, Long> {
}
