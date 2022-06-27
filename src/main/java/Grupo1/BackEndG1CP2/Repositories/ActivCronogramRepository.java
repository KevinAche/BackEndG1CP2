package Grupo1.BackEndG1CP2.Repositories;

import Grupo1.BackEndG1CP2.Models.Actividades_Cronograma;
import Grupo1.BackEndG1CP2.Models.Actividades_Diarias;
import Grupo1.BackEndG1CP2.Models.Cronograma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivCronogramRepository extends JpaRepository<Actividades_Cronograma,Long> {

    List<Actividades_Cronograma> findByCronograma(Cronograma cronograma);

    Actividades_Cronograma findByActividadesDiarias(Actividades_Diarias actividadesDiarias);
}
