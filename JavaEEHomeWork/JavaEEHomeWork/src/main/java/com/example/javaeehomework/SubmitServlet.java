package com.example.javaeehomework;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(name = "submitServlet", value = "/submit")
public class SubmitServlet extends HttpServlet {
    private AtomicInteger runningCount = new AtomicInteger(0);
    private AtomicInteger weightCount = new AtomicInteger(0);
    private AtomicInteger morningCount = new AtomicInteger(0);
    private AtomicInteger eveningCount = new AtomicInteger(0);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String discipline = request.getParameter("discipline");
        String day = request.getParameter("day");

        if ("running".equals(discipline)) {
            runningCount.incrementAndGet();
        } else if ("weight training".equals(discipline)) {
            weightCount.incrementAndGet();
        }

        if ("morning".equals(day)) {
            morningCount.incrementAndGet();
        } else if ("evening".equals(day)) {
            eveningCount.incrementAndGet();
        }

        response.sendRedirect(request.getContextPath() + "/submit?action=showResults");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        if ("reset".equals(action)) {
            runningCount.set(0);
            weightCount.set(0);
            morningCount.set(0);
            eveningCount.set(0);
        response.sendRedirect(request.getContextPath() + "/index.jsp");

    } else if ("showResults".equals(action)) {
        request.setAttribute("runningCount", runningCount.get());
        request.setAttribute("weightCount", weightCount.get());
        request.setAttribute("morningCount", morningCount.get());
        request.setAttribute("eveningCount", eveningCount.get());

            RequestDispatcher dispatcher = request.getRequestDispatcher("/results.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }
    public void destroy() {
    }
}