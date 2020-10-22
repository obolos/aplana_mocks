package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.appline.logic.Calculator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/calc")
public class ServletCalculator extends HttpServlet {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    // Написать сервлет-калькулятор, который будет принимать в формате json 3 параметра: число а, b и арифметическое действие
    //    {
    //        "a": 10,
    //        "b": 5,
    //        "math": "*"
    //    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = req.getReader();
        String str;
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=utf-8");

        while ((str = reader.readLine()) != null) sb.append(str);
        JsonObject jobj = gson.fromJson(sb.toString(), JsonObject.class);

        double a = jobj.get("a").getAsDouble();
        double b = jobj.get("b").getAsDouble();
        String math = jobj.get("math").getAsString();

//            {
//                "result":  50
//            }

        pw.print(gson.toJson(new Calculator(a, b, math).getM()));

    }


}
