package Grupo1.BackEndG1CP2.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "persona")
public class persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10)
    private String cedula;

    @Column(length = 60)
    private  String nombres;

    @Column(length = 60)
    private String apellidos;

    @Column(name = "fecha_nac")
    private Date fecha_nac;

    @Column(length = 15)
    private String telefono;

    @Column(length = 150)
    private String direccion;

    @Column(length = 150)
    private String correo;


}
