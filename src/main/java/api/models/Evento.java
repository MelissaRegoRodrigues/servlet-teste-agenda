package api.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Evento {
    private int id; // identificador único para o evento
    private String titulo; // título do evento
    private LocalDate data; // data do evento
    private LocalTime hora; // hora do evento
    private String descricao; // descrição do evento

    // Construtor
    public Evento(String titulo, LocalDate data, LocalTime hora, String descricao) {
        this.titulo = titulo;
        this.data = data;
        this.hora = hora;
        this.descricao = descricao;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
