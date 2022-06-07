package Grupo1.BackEndG1CP2.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reg_asistencias")
public class Registro_Asistencias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int num_hojas;

    private String hora_entrada;

    private String hora_salida;

    private Date fecha;

//    private String anexo9;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNum_hojas() {
        return num_hojas;
    }

    public void setNum_hojas(int num_hojas) {
        this.num_hojas = num_hojas;
    }

    public String getHora_entrada() {
        return hora_entrada;
    }

    public void setHora_entrada(String hora_entrada) {
        this.hora_entrada = hora_entrada;
    }

    public String getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(String hora_salida) {
        this.hora_salida = hora_salida;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Registro_Asistencias{" +
                "id=" + id +
                ", num_hojas=" + num_hojas +
                ", hora_entrada='" + hora_entrada + '\'' +
                ", hora_salida='" + hora_salida + '\'' +
                ", fecha=" + fecha +
                '}';
    }
}

