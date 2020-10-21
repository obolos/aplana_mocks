package ru.appline;

import ru.appline.logic.Model;
import ru.appline.logic.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(urlPatterns = "/add")
public class ServletAdd extends HttpServlet {

    // private AtomicInteger counter = new AtomicInteger(5);
    Model model = Model.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        PrintWriter pw = resp.getWriter();

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        double salary = Double.parseDouble(req.getParameter("salary"));
        User user = new User(name, surname, salary);
        model.add(user, model.getFromList().size()+1);

        pw.print("<html>" +
                "<h3>User " + name + " " + surname + " with salary " + salary + " successfully created! </h3>" +
                "<br/><a href=\"addUser.html\">Create New User</a><br/>" +
                "<a href=\"index.jsp\">Home</a>" +
                "<html>" );



    }
}
