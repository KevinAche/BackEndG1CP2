package Grupo1.BackEndG1CP2.Repositories.ViewRepositories;

import Grupo1.BackEndG1CP2.Models.Views.VistaEmpleadosEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ListaEmpleadosEmpRepository extends JpaRepository<VistaEmpleadosEmpresa,Long> {



}
