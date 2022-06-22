
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
		Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
		Rol rolEstudiante = new Rol(RolNombre.ROLE_ESTUDIANTE);
		Rol rolResponsable = new Rol(RolNombre.ROLE_RESPONSABLEPPP);
		Rol rolTutorA = new Rol(RolNombre.ROLE_TUTORACADEMICO);
		Rol rolTutorE = new Rol(RolNombre.ROLE_TUTOREMPRESARIAL);
		Rol rolEmpleado = new Rol(RolNombre.ROLE_EMPLEADO);
		
		
		rolService.save(rolDocente);
		rolService.save(rolAdmin);
		rolService.save(rolEstudiante);
		rolService.save(rolResponsable);
		rolService.save(rolTutorA);
		rolService.save(rolTutorE);
		rolService.save(rolEmpleado);
	}

}
