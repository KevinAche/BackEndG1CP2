package Grupo1.BackEndG1CP2.Repositories;

import Grupo1.BackEndG1CP2.Models.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlumnoRepository extends JpaRepository<Alumno,Long> {

   // @Query("select * from alumno a where a.promedio between :min and :max ")
    //List<Alumno> clasificarAlumnosPorRango(double min, double max);


    List<Alumno> findAllByPromedioIsBetween(double min, double max);

}
