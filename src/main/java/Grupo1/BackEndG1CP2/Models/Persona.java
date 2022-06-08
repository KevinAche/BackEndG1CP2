package Grupo1.BackEndG1CP2.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersona;

    @Column(length = 10, nullable = false, unique = true)
    private String cedula;

    @Column(length = 60,  nullable = false)
    private  String primerNombre;
    
    @Column(length = 60,  nullable = false)
    private  String segundoNombre;
    
    @Column(length = 60,  nullable = false)
    private  String primerApellido;
    
    @Column(length = 60,  nullable = false)
    private  String segundoApellido;

    @Column(name = "fecha_nac",  nullable = false)
    private Date fechaNac;

    @Column(length = 15,  nullable = false)
    private String telefono;

    @Column(length = 150,  nullable = false)
    private String direccion;

    @Column(length = 150,  nullable = false)
    private String correo;
    
    @OneToOne(mappedBy = "persona")
    private Alumno alumno;
    
    @OneToOne(mappedBy = "persona")
    private Docente docente;
    
    @OneToOne(mappedBy = "persona")
    private PersonalEmpresa personalEmpresa;
    
    
	public Persona() {
	}

	public Persona(String cedula, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, Date fechaNac, String telefono, String direccion, String correo) {
		this.cedula = cedula;
		this.primerNombre = primerNombre;
		this.segundoNombre = segundoNombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.fechaNac = fechaNac;
		this.telefono = telefono;
		this.direccion = direccion;
		this.correo = correo;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Docente getDocente() {
		return docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}

	public PersonalEmpresa getPersonalEmpresa() {
		return personalEmpresa;
	}

	public void setPersonalEmpresa(PersonalEmpresa personalEmpresa) {
		this.personalEmpresa = personalEmpresa;
	}
	
	
    
    

}
