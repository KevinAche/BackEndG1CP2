package Grupo1.BackEndG1CP2.Repositories.ViewRepositories;

import Grupo1.BackEndG1CP2.Models.Views.VistaAlumSolicitudes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ListaAlumSoliRepository extends JpaRepository<VistaAlumSolicitudes,Long> {

}
