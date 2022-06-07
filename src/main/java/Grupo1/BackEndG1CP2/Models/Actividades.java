package Grupo1.BackEndG1CP2.Models;

import javax.persistence.*;

@Entity
@Table(name = "actividades")
public class Actividades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idActividad;
    
    private String descripcion;
    
    private String area;
    
    @ManyToOne
    @JoinColumn(name = "idAsignatura", referencedColumnName = "idAsignatura")
    private Asignaturas asignatura;
    
    @ManyToOne
    @JoinColumn(name = "idSolicitud", referencedColumnName = "idSolicitud")
    private SolicitudEmpresa solicitudEmpresa;
    

}
