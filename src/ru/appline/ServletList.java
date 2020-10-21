package ru.appline;

import ru.appline.logic.Model;
import ru.appline.logic.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(urlPatterns = "/get")
public class ServletList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        Map<Integer, User> model = Model.getInstance().getFromList();
        PrintWriter pw = resp.getWriter();
        int param = 0;
        try{
            param = Integer.parseInt(req.getParameter("user-id"));
        } catch (Exception e) {e.printStackTrace();}


        if(param == 0) {
            pw.println("<html>" +
                    "<h3>Available users</h3><br/>" +
                    "UserID: " +
                    "<ul>");
            for (Map.Entry<Integer, User> entry : model.entrySet()) {
                pw.print("<li>" + entry.getKey() + "</li>" +
                        "<ul>" +
                        "<li>Name: " + entry.getValue().getName() + "</li>" +
                        "<li>Surname: " + entry.getValue().getSurname() + "</li>" +
                        "<li>Salary: " + entry.getValue().getSalary() + "</li>" +
                        "</ul>");
            }
            pw.print("</ul>" +
                    "<a href=\"index.jsp\">Home</a><br/>" +
                    "</html>");
        }


        else if (param <= model.size() && param > 0) {
            pw.print("<html>" +
                    "<h3>Requested User</h3>" +
                    "<br/>" +
                    "Name: " + model.get(param).getName() + "<br/>" +
                    "Surname: " + model.get(param).getSurname() + "<br/>" +
                    "Salary: " + model.get(param).getSalary() + "<br/>" +

                    "<a href=\"addUser.html\">Create New User</a><br/>" +
                    "<a href=\"index.jsp\">Home</a><br/>" +
                    "</html>");
        }

        else
            pw.print("<html>" +
                    "No such element" +
                    "</html>");

    }


}
