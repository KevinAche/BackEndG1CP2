package Grupo1.BackEndG1CP2.security.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Grupo1.BackEndG1CP2.security.entity.Rol;
import Grupo1.BackEndG1CP2.security.entity.Usuario;
import Grupo1.BackEndG1CP2.security.enums.RolNombre;
import Grupo1.BackEndG1CP2.security.repository.RolRepository;
import Grupo1.BackEndG1CP2.security.repository.UsuarioRepository;

@Service
@Transactional
public class RolService {

	@Autowired
	public RolRepository rolRepository;
	
	public Optional<Rol> getByUsername(RolNombre rolNombre){
		return rolRepository.findByRolNombre(rolNombre);
	}
	
	public void save(Rol rol) {
		rolRepository.save(rol);
	}
	
}
