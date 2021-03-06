package Grupo1.BackEndG1CP2.Repositories;

import Grupo1.BackEndG1CP2.Models.Docente;
import Grupo1.BackEndG1CP2.Models.Persona;
import Grupo1.BackEndG1CP2.Models.PersonalEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocenteRepository extends JpaRepository<Docente,Long> {

    List<Docente> findAllByArea(String area);

    List<Docente> findAllByAbrevTitulo(String titulo);

    Docente findByPersona(Persona persona);

}
