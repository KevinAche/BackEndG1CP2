package Grupo1.BackEndG1CP2.Models;

import javax.persistence.*;

@Entity
@Table(name = "persona")
public class persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

// private string nombre;

    //private String apellidos;

}
