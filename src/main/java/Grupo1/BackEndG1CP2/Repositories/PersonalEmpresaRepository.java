package Grupo1.BackEndG1CP2.Repositories;

import Grupo1.BackEndG1CP2.Models.PersonalEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonalEmpresaRepository extends JpaRepository<PersonalEmpresa,Long> {

    List<PersonalEmpresa> findAllByCargo(String cargo);


}
