package Grupo1.BackEndG1CP2.Repositories;

import Grupo1.BackEndG1CP2.Models.Alumno;
import Grupo1.BackEndG1CP2.Models.Registro_Asistencias;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroAsistenciaRepository extends JpaRepository<Registro_Asistencias,Long> {


    Registro_Asistencias findByAlumno(Alumno alumno);
}
