package api.repository;


import api.DatabaseConnection;
import api.models.Evento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventoRepository {

    public void addEvento(Evento evento) {
        String sql = "INSERT INTO eventos (titulo, data, hora, descricao) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, evento.getTitulo());
            stmt.setDate(2, Date.valueOf(evento.getData())); // Converte LocalDate para Date
            stmt.setTime(3, Time.valueOf(evento.getHora())); // Converte LocalTime para Time
            stmt.setString(4, evento.getDescricao());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Evento> getAllEventos() {
        List<Evento> eventos = new ArrayList<>();
        String sql = "SELECT * FROM eventos";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Evento evento = new Evento(
                        rs.getString("titulo"),
                        rs.getDate("data").toLocalDate(), // Converte Date para LocalDate
                        rs.getTime("hora").toLocalTime(), // Converte Time para LocalTime
                        rs.getString("descricao")
                );
                eventos.add(evento);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eventos;
    }

    // Métodos para update e delete podem ser implementados aqui
}
