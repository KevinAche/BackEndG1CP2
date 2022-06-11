package Grupo1.BackEndG1CP2.Repositories.ViewRepositories;

import Grupo1.BackEndG1CP2.Models.Views.VistaListarTutoresEmp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ListarTutoresEmpRepository extends JpaRepository<VistaListarTutoresEmp,Long> {

    @Query(nativeQuery = true,value = "select * from listar_tutores_emp")
    List<VistaListarTutoresEmp> ListarTutoresE();
}
