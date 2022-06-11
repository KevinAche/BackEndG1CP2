package Grupo1.BackEndG1CP2.Models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

import Grupo1.BackEndG1CP2.Models.Empresa;
import Grupo1.BackEndG1CP2.Models.TutorEmpresarial;
import Grupo1.BackEndG1CP2.Models.Alumno;

@Entity
@Table(name = "informe_culminacion")
public class Informe_Culminacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInformeCulminacion;

    private Date fechaEmision;


    @OneToOne
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;


    @OneToOne
    @JoinColumn(name = "id_tutor_empresarial")
    private TutorEmpresarial tutorEmpresarial;

    @OneToOne
    @JoinColumn(name = "idAlumno",referencedColumnName = "idAlumno")
    private Alumno alumno;


    private String tiempo;


    @OneToOne
    @JoinColumn(name = "tutor_academico_id_tutor_academico")
    private TutorAcademico tutorAcademico;

    private double tiempo_duracion;

    private Date fecha_finalizacion;


    //private List<Actividades_Diarias> actividadesDiarias;

    @OneToOne
    @JoinColumn(name = "registro_asistencias_id_registro_asistencia")
    private Registro_Asistencias registro_asistencias;


    public Informe_Culminacion() {
    }

    public Long getIdInformeCulminacion() {
        return idInformeCulminacion;
    }

    public void setIdInformeCulminacion(Long idInformeCulminacion) {
        this.idInformeCulminacion = idInformeCulminacion;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public TutorEmpresarial getTutorEmpresarial() {
        return tutorEmpresarial;
    }

    public void setTutorEmpresarial(TutorEmpresarial tutorEmpresarial) {
        this.tutorEmpresarial = tutorEmpresarial;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public TutorAcademico getTutorAcademico() {
        return tutorAcademico;
    }

    public void setTutorAcademico(TutorAcademico tutorAcademico) {
        this.tutorAcademico = tutorAcademico;
    }

    public double getTiempo_duracion() {
        return tiempo_duracion;
    }

    public void setTiempo_duracion(double tiempo_duracion) {
        this.tiempo_duracion = tiempo_duracion;
    }

    public Date getFecha_finalizacion() {
        return fecha_finalizacion;
    }

    public void setFecha_finalizacion(Date fecha_finalizacion) {
        this.fecha_finalizacion = fecha_finalizacion;
    }

    public Registro_Asistencias getRegistro_asistencias() {
        return registro_asistencias;
    }

    public void setRegistro_asistencias(Registro_Asistencias registro_asistencias) {
        this.registro_asistencias = registro_asistencias;
    }


}
