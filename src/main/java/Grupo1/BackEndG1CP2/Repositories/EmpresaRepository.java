package Grupo1.BackEndG1CP2.Repositories;

import Grupo1.BackEndG1CP2.Models.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpresaRepository extends JpaRepository<Empresa,Long> {

    List<Empresa> findAllByNombreEmpresa(String nombre);

}
