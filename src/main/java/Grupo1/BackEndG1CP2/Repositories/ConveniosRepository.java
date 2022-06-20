package Grupo1.BackEndG1CP2.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import Grupo1.BackEndG1CP2.Models.Convenio;
import Grupo1.BackEndG1CP2.Models.PersonalEmpresa;

public interface ConveniosRepository extends JpaRepository<Convenio, Long>{
	
	Convenio findByGerente(PersonalEmpresa gerente);

}
