package Grupo1.BackEndG1CP2.Repositories;

import Grupo1.BackEndG1CP2.Models.Actividades_Diarias;
import Grupo1.BackEndG1CP2.Models.Registro_Asistencias;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActividadesDiariasRepository extends JpaRepository<Actividades_Diarias,Long> {

    List<Actividades_Diarias> findByRegistroA(Registro_Asistencias registroAsistencias);

}
