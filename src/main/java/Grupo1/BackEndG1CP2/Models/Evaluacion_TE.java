package Grupo1.BackEndG1CP2.Models;

import javax.persistence.*;

@Entity
@Table(name = "evaluacion_TE")
public class Evaluacion_TE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
