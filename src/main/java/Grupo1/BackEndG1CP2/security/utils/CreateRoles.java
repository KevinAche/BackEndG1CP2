
package Grupo1.BackEndG1CP2.security.utils; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import Grupo1.BackEndG1CP2.security.entity.Rol;
import Grupo1.BackEndG1CP2.security.enums.RolNombre;
import Grupo1.BackEndG1CP2.security.services.RolService;

@Component
public class CreateRoles implements CommandLineRunner{

	@Autowired
	RolService rolService;
	
	@Override
	public void run(String... args) throws Exception {
		Rol rolDocente = new Rol(RolNombre.ROLE_DOCENTE);
		// rolService.save(rolDocente);
	}

}
