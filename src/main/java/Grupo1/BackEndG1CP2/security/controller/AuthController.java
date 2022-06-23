package Grupo1.BackEndG1CP2.security.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import Grupo1.BackEndG1CP2.Models.Mensaje;
import Grupo1.BackEndG1CP2.Models.Persona;
import Grupo1.BackEndG1CP2.Repositories.PersonaRepository;
import Grupo1.BackEndG1CP2.Repositories.ViewRepositories.ListarPersonalRepository;
import Grupo1.BackEndG1CP2.security.dto.JwtDto;
import Grupo1.BackEndG1CP2.security.dto.LoginUsuario;
import Grupo1.BackEndG1CP2.security.dto.NuevoUsuario;
import Grupo1.BackEndG1CP2.security.entity.Rol;
import Grupo1.BackEndG1CP2.security.entity.Usuario;
import Grupo1.BackEndG1CP2.security.enums.RolNombre;
import Grupo1.BackEndG1CP2.security.jwt.JwtProvider;
import Grupo1.BackEndG1CP2.security.services.RolService;
import Grupo1.BackEndG1CP2.security.services.UsuarioService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(value = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class AuthController {

	@Autowired
	public PasswordEncoder passwordEncoder;

	@Autowired
	public AuthenticationManager authenticationManager;

	@Autowired
	public UsuarioService usuarioService;

	@Autowired
	public RolService rolService;

	@Autowired
	public JwtProvider jwtProvider;
	
	@Autowired
	public PersonaRepository personaRepository;

	@PostMapping("/nuevo")
	public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return new ResponseEntity<>(new Mensaje("Campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
		if (usuarioService.existsByUsername(nuevoUsuario.getUsername()))
			return new ResponseEntity(new Mensaje("Ya existe ese username"), HttpStatus.BAD_REQUEST);
		if (usuarioService.existsByEmail(nuevoUsuario.getEmail()))
			return new ResponseEntity(new Mensaje("Ya existe el email"), HttpStatus.BAD_REQUEST);
		Persona persona = personaRepository.findById(nuevoUsuario.getPersona().getIdPersona()).get();
		Usuario usuario = new Usuario(
				persona.getPrimerNombre() + " " + persona.getPrimerApellido(),
				persona.getCedula(), persona.getCorreo(),
				passwordEncoder.encode(persona.getCedula()), nuevoUsuario.getPersona());
		Set<Rol> roles = new HashSet<>();
		if (nuevoUsuario.getRoles().contains("estudiante"))
			roles.add(rolService.getByUsername(RolNombre.ROLE_ESTUDIANTE).get());
		if (nuevoUsuario.getRoles().contains("admin"))
			roles.add(rolService.getByUsername(RolNombre.ROLE_ADMIN).get());
		if (nuevoUsuario.getRoles().contains("docente"))
			roles.add(rolService.getByUsername(RolNombre.ROLE_DOCENTE).get());
		if (nuevoUsuario.getRoles().contains("responsable"))
			roles.add(rolService.getByUsername(RolNombre.ROLE_RESPONSABLEPPP).get());
		if (nuevoUsuario.getRoles().contains("tacademico"))
			roles.add(rolService.getByUsername(RolNombre.ROLE_TUTORACADEMICO).get());
		if (nuevoUsuario.getRoles().contains("tempresarial"))
			roles.add(rolService.getByUsername(RolNombre.ROLE_TUTOREMPRESARIAL).get());
		if (nuevoUsuario.getRoles().contains("empleado"))
			roles.add(rolService.getByUsername(RolNombre.ROLE_EMPLEADO).get());
		usuario.setRoles(roles);
		usuarioService.save(usuario);
		return new ResponseEntity(new Mensaje("Usuario Creado"), HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return new ResponseEntity(new Mensaje("Campos mal puestos"), HttpStatus.BAD_REQUEST);
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUsuario.getUsername(), loginUsuario.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
		return new ResponseEntity<>(jwtDto, HttpStatus.OK);
	}

	public boolean creacionUsuarios(NuevoUsuario nuevoUsuario){
		System.out.println("ENTRO AL METODO");
		if (usuarioService.existsByUsername(nuevoUsuario.getUsername()))
			return false;
		if (usuarioService.existsByEmail(nuevoUsuario.getEmail()))
			return false;
		Persona persona = personaRepository.findById(nuevoUsuario.getPersona().getIdPersona()).get();
		Usuario usuario = new Usuario(
				persona.getPrimerNombre() + " " + persona.getPrimerApellido(),
				persona.getCedula(), persona.getCorreo(),
				passwordEncoder.encode(persona.getCedula()), nuevoUsuario.getPersona());
		Set<Rol> roles = new HashSet<>();
		if (nuevoUsuario.getRoles().contains("estudiante"))
			roles.add(rolService.getByUsername(RolNombre.ROLE_ESTUDIANTE).get());
		if (nuevoUsuario.getRoles().contains("admin"))
			roles.add(rolService.getByUsername(RolNombre.ROLE_ADMIN).get());
		if (nuevoUsuario.getRoles().contains("docente"))
			roles.add(rolService.getByUsername(RolNombre.ROLE_DOCENTE).get());
		if (nuevoUsuario.getRoles().contains("responsable"))
			roles.add(rolService.getByUsername(RolNombre.ROLE_RESPONSABLEPPP).get());
		if (nuevoUsuario.getRoles().contains("tacademico"))
			roles.add(rolService.getByUsername(RolNombre.ROLE_TUTORACADEMICO).get());
		if (nuevoUsuario.getRoles().contains("tempresarial"))
			roles.add(rolService.getByUsername(RolNombre.ROLE_TUTOREMPRESARIAL).get());
		if (nuevoUsuario.getRoles().contains("empleado"))
			roles.add(rolService.getByUsername(RolNombre.ROLE_EMPLEADO).get());
		usuario.setRoles(roles);
		usuarioService.save(usuario);
		return true;
	}
}
