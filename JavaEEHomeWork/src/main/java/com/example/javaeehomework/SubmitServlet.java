package com.example.javaeehomework;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import java.io.IOException;

@WebServlet(name = "submitServlet", value = "/submit")
public class SubmitServlet extends HttpServlet {

    int statYes = 0;
    int statNo = 0;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String question1 = request.getParameter("question1");
        String question2 = request.getParameter("question2");

        if (question1.equals("Yes")) {
            statYes++;
        } else {
            statNo++;
        }
        if (question2.equals("Yes")) {
            statYes++;
        } else {
            statNo++;
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("question1", question1);
        session.setAttribute("question2", question2);
        session.setAttribute("Yes", statYes);
        session.setAttribute("No", statNo);
        session.setAttribute("Yes", statYes);
        session.setAttribute("No", statNo);

        try {
            response.sendRedirect("index.jsp");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("result.jsp");
    }

    public void destroy() {
    }
}