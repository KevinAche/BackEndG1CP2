package Grupo1.BackEndG1CP2.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SolicitudAlumno")
public class SolicitudAlumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSolicitudAlumno;

    private Date fechaEmision;

    @ManyToOne
    @JoinColumn(name = "idConvocatoria", referencedColumnName = "idConvocatoria")
    private Convocatoria convocatoria;

    @ManyToOne
    @JoinColumn(name = "idAlumno", referencedColumnName = "idAlumno")
    private Alumno alumno;
}
