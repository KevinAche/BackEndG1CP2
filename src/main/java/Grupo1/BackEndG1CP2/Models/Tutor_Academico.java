package Grupo1.BackEndG1CP2.Models;

import javax.persistence.*;

@Entity
@Table(name = "tutor_academico")
public class Tutor_Academico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
