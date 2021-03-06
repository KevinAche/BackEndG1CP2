package Grupo1.BackEndG1CP2.Repositories;

import Grupo1.BackEndG1CP2.Models.Alumno;
import Grupo1.BackEndG1CP2.Models.Docente;
import Grupo1.BackEndG1CP2.Models.Persona;
import Grupo1.BackEndG1CP2.Models.TutorEmpresarial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TutorEmpresarialRepository extends JpaRepository<TutorEmpresarial,Long> {


    List<TutorEmpresarial> findAllByControlContaining(String control);

    TutorEmpresarial findByAlumno(Alumno alumno);



}
