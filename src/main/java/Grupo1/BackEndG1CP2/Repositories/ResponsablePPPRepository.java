package Grupo1.BackEndG1CP2.Repositories;

import Grupo1.BackEndG1CP2.Models.Docente;
import Grupo1.BackEndG1CP2.Models.ResponsablePPP;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponsablePPPRepository extends JpaRepository<ResponsablePPP,Long> {
	
	ResponsablePPP findByDocente(Docente docente);

    //EN PROCESO...
}
