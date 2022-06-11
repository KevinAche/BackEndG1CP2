package Grupo1.BackEndG1CP2.Repositories;

import Grupo1.BackEndG1CP2.Models.Alumno;
import Grupo1.BackEndG1CP2.Models.TutorAcademico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorAcademicoRepository extends JpaRepository<TutorAcademico,Long> {

    //EN PROCESO ...

    TutorAcademico findByAlumno(Alumno alumno);
}
