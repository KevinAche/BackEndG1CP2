package Grupo1.BackEndG1CP2.Models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "reg_asistencias")
public class Registro_Asistencias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRegistroAsistencia;

    //Anexo 9
    private String docRegistroA;

    @OneToOne
    @JoinColumn(name = "idAlumno",referencedColumnName = "idAlumno")
    private Alumno alumno;

    @OneToMany(mappedBy = "registroA")
    private List<Actividades_Diarias> actividades;

    public Registro_Asistencias() {
    }

    public Long getIdRegistroAsistencia() {
        return idRegistroAsistencia;
    }

    public void setIdRegistroAsistencia(Long idRegistroAsistencia) {
        this.idRegistroAsistencia = idRegistroAsistencia;
    }

    public String getDocRegistroA() {
        return docRegistroA;
    }

    public void setDocRegistroA(String docRegistroA) {
        this.docRegistroA = docRegistroA;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public List<Actividades_Diarias> getActividades() {
        return actividades;
    }

    public void setActividades(List<Actividades_Diarias> actividades) {
        this.actividades = actividades;
    }
}

