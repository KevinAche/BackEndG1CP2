package Grupo1.BackEndG1CP2.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Grupo1.BackEndG1CP2.security.entity.Rol;
import Grupo1.BackEndG1CP2.security.enums.RolNombre;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer>{
	
	public Optional<Rol> findByRolNombre(RolNombre rolNombre);

}
