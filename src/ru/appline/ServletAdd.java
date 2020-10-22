package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.appline.logic.Model;
import ru.appline.logic.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(urlPatterns = "/add")
public class ServletAdd extends HttpServlet {

    private AtomicInteger counter = new AtomicInteger(4);

    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = 0;
        PrintWriter pw = resp.getWriter();

        try{ id = Integer.parseInt(req.getParameter("id"));
        } catch(Exception e) {e.printStackTrace();}

        if (id == 0) {
            resp.setContentType("application/json;charset=utf-8");
            pw = resp.getWriter();
            pw.print(gson.toJson(model.getFromList()));
        }
        else if (model.getFromList().containsKey(id)) {
            resp.setContentType("application/json;charset=utf-8");
            pw.print(gson.toJson(model.getFromList().get(id)));
        }
        else {
            pw.print("No such element");
        }

    }

    // Изменить сервлет ServletAdd таким образом, чтобы была возможность читать тело запроса из json, и возвращать ответ в json;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = req.getReader();
        String str;
        req.setCharacterEncoding("UTF-8");

        while ((str = reader.readLine()) != null) {
            sb.append(str);
        }

        JsonObject jobj = gson.fromJson(sb.toString(), JsonObject.class);

        String name = jobj.get("name").getAsString();
        String surname = jobj.get("surname").getAsString();
        double salary = jobj.get("salary").getAsDouble();

        User user = new User(name, surname, salary);
        model.add(user, counter.getAndIncrement());

        resp.setContentType("application/json;charset=utf-8");
        PrintWriter pw = resp.getWriter();

        pw.print(gson.toJson(model.getFromList()));
    }

    // Реализовать сервлет с методом doDelete(), который будет удалять записи о пользователях по id;

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = 0;
        PrintWriter pw = resp.getWriter();

        try{ id = Integer.parseInt(req.getParameter("id"));
        } catch(Exception e) {e.getMessage();}

        if (Model.getInstance().getFromList().get(id) != null) {
            resp.setContentType("application/json;charset=utf-8");
            pw.print(gson.toJson(Model.getInstance().getFromList().get(id) + " was removed"));

            Iterator<Map.Entry<Integer, User>> iterator = Model.getInstance().getFromList().entrySet().iterator();
            while(iterator.hasNext()) {
                if(iterator.next().getKey() == id)
                    iterator.remove();
            }

        } else {pw.print("no such element!");}
    }

    // Реализовать сервлет с методом doPut(), который будет обновлять (изменять) записи о пользователях
    // (необходимо передавать id, имя, фамилию и заплату пользователей).

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = 0;
        PrintWriter pw = resp.getWriter();
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = req.getReader();
        String str;
        req.setCharacterEncoding("UTF-8");

        try{ id = Integer.parseInt(req.getParameter("id"));
        } catch(Exception e) {e.getMessage();}

        if (model.getFromList().containsKey(id)) {
            while ((str = reader.readLine()) != null) sb.append(str);

            JsonObject jobj = gson.fromJson(sb.toString(), JsonObject.class);
            model.getFromList().get(id).setName(jobj.get("name").getAsString());
            model.getFromList().get(id).setSurname(jobj.get("surname").getAsString());
            model.getFromList().get(id).setSalary(jobj.get("salary").getAsDouble());

            pw.print(gson.toJson(model.getFromList().get(id)) + "successfully changed");

        } else pw.print("no such element");
    }


//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html;charset=utf-8");
//        req.setCharacterEncoding("UTF-8");
//        PrintWriter pw = resp.getWriter();
//
//        String name = req.getParameter("name");
//        String surname = req.getParameter("surname");
//        double salary = Double.parseDouble(req.getParameter("salary"));
//        User user = new User(name, surname, salary);
//        model.add(user, model.getFromList().size()+1);
//
//        pw.print("<html>" +
//                "<h3>User " + name + " " + surname + " with salary " + salary + " successfully created! </h3>" +
//                "<br/><a href=\"addUser.html\">Create New User</a><br/>" +
//                "<a href=\"index.jsp\">Home</a>" +
//                "<html>" );
//
//
//
//    }

}
