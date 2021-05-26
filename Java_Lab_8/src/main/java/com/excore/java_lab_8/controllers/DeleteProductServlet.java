package com.excore.java_lab_8.controllers;

import com.excore.java_lab_8.ProductDbConnection;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "DeleteProductServlet", value = "/delete-product")
public class DeleteProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Connection c = ProductDbConnection.getConnection();
            Statement s = c.createStatement();
            s.executeUpdate(String.format("delete from products where id=%d", id));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        response.sendRedirect("/list-products");
    }
}
