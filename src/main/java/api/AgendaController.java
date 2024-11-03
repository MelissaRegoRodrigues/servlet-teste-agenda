package api;

import api.models.Evento;
import api.repository.EventoRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/agenda")
public class AgendaController extends HttpServlet {

    private List<Evento> eventos = new ArrayList<>();
    EventoRepository eventoRepository;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Exibir lista de eventos
        request.setAttribute("eventos", eventos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("listagem.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obter os dados do formulário
        String titulo = request.getParameter("titulo");
        String dataStr = request.getParameter("data");
        String horaStr = request.getParameter("hora");
        String descricao = request.getParameter("descricao");

        try {
            // Converter a string da data e hora para LocalDate e LocalTime
            LocalDate data = LocalDate.parse(dataStr);
            LocalTime hora = LocalTime.parse(horaStr);

            // Criar um novo evento
            Evento evento = new Evento(titulo, data, hora, descricao);

            // Adicionar o evento ao repositório
            eventoRepository.addEvento(evento);

            // Redirecionar para a lista de eventos
            response.sendRedirect("agenda");

        } catch (DateTimeParseException e) {
            // Lidar com erros de parsing de data/hora
            request.setAttribute("error", "Data ou hora inválida.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("formulario.jsp"); // Ou a página que contém o formulário
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Implementar lógica para deletar um evento
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Implementar lógica para atualizar um evento
    }
}
