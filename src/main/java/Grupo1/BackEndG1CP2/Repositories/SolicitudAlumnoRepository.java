package Grupo1.BackEndG1CP2.Repositories;

import Grupo1.BackEndG1CP2.Models.Alumno;
import Grupo1.BackEndG1CP2.Models.SolicitudAlumno;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitudAlumnoRepository extends JpaRepository<SolicitudAlumno,Long> {

    SolicitudAlumno findByAlumno(Alumno alumno);
   

}
