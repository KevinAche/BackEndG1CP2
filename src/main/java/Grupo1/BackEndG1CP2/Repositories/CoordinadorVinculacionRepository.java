package Grupo1.BackEndG1CP2.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import Grupo1.BackEndG1CP2.Models.CoordinadorVinculacion;
import Grupo1.BackEndG1CP2.Models.Docente;

public interface CoordinadorVinculacionRepository extends JpaRepository<CoordinadorVinculacion, Long>{
	
	CoordinadorVinculacion findByDocente(Docente docente);

}
