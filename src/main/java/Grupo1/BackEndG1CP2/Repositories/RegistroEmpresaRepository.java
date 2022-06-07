package Grupo1.BackEndG1CP2.Repositories;

import Grupo1.BackEndG1CP2.Models.Registro_VisitaEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroEmpresaRepository extends JpaRepository<Registro_VisitaEmpresa,Long> {
}
