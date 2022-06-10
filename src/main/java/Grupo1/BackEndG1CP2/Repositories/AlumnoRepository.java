package Grupo1.BackEndG1CP2.Repositories;

import Grupo1.BackEndG1CP2.Models.Alumno;
import Grupo1.BackEndG1CP2.Models.Persona;
import Grupo1.BackEndG1CP2.Models.PersonalEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlumnoRepository extends JpaRepository<Alumno,Long> {

   // @Query("select * from alumno a where a.promedio between :min and :max ")
    //List<Alumno> clasificarAlumnosPorRango(double min, double max);


    List<Alumno> findAllByPromedioIsBetween(double min, double max);

    @Query(nativeQuery = true,value = "select p.id_persona, p.cedula, p.primer_nombre,p.segundo_nombre, p.primer_apellido, p.segundo_apellido, p.correo, p.direccion, p.fecha_nac, p.telefono,\n" +
            "a.id_alumno,a.ciclo, a.paralelo, a.promedio,\n" +
            "c.nombre as \"Nombre Carrera\"\n" +
            "from persona p, alumno a, carreras c\n" +
            "where p.id_persona=a.id_persona and a.id_carrera=c.id_carrera")
    List<Alumno> ListaAlumnos();

    Alumno findByPersona(Persona persona);

}
