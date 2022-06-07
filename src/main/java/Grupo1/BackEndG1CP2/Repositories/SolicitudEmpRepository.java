package Grupo1.BackEndG1CP2.Repositories;

import Grupo1.BackEndG1CP2.Models.SolicitudEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolicitudEmpRepository extends JpaRepository<SolicitudEmpresa,Long> {
}
