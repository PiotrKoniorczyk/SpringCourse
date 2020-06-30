package com.github.piotrkoniorczyk.todo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Lang", urlPatterns = {"/api/todos/*"})
public class TodoServlet extends HttpServlet {

    private final Logger logger = LoggerFactory.getLogger(TodoServlet.class);

    private TodoRepository todoRepository;
    private ObjectMapper mapper;

    @SuppressWarnings("unused")
    public TodoServlet() {
        this(new TodoRepository(), new ObjectMapper());
    }

    TodoServlet(TodoRepository todoRepository, ObjectMapper mapper) {
        this.todoRepository = todoRepository;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Got request with parameters: " + request.getParameterMap());
        response.setContentType("application/json;charset=UTF-8");
        mapper.writeValue(response.getOutputStream(), todoRepository.findAll());
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Got request with parameters: " + request.getParameterMap());
        var info = request.getPathInfo();
        try {
            Integer number = Integer.valueOf(info.replaceAll("[^0-9]", ""));
            var todo = todoRepository.toggleTodo(number);
            response.setContentType("application/json;charset=UTF-8");
            mapper.writeValue(response.getOutputStream(), todo);
        } catch (NumberFormatException n) {
            logger.warn("This path doesn't exist: " + info);
        }
    }
}
