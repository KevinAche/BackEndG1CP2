package Grupo1.BackEndG1CP2.Repositories.ViewRepositories;

import Grupo1.BackEndG1CP2.Models.Views.VistaLista_Responsables;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListarResponsablesRepository extends JpaRepository<VistaLista_Responsables,Long> {

    VistaLista_Responsables findByCedula(String cedula );

}
