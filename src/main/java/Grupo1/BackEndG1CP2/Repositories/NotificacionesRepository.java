package Grupo1.BackEndG1CP2.Repositories;

import Grupo1.BackEndG1CP2.Models.Notificaciones;
import Grupo1.BackEndG1CP2.Models.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificacionesRepository extends JpaRepository<Notificaciones,Long> {

    List<Notificaciones> findByPersona(Persona persona);
}
