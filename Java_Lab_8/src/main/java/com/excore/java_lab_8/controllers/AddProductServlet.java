package com.excore.java_lab_8.controllers;

import com.excore.java_lab_8.ProductDbConnection;
import com.excore.java_lab_8.model.Cylinder;
import com.excore.java_lab_8.model.Timber;
import com.excore.java_lab_8.model.Waste;
import com.excore.java_lab_8.model.Wood;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(name = "AddProductServlet", value = "/add-product")
public class AddProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String product = request.getParameter("product");
        if (product == null) {
            response.sendRedirect("/list-products");
            return;
        }

        try {
            Connection c = ProductDbConnection.getConnection();
            Statement s = c.createStatement();
            switch (product) {
                case "timber":
                    float length = Float.parseFloat(request.getParameter("dim1"));
                    float height = Float.parseFloat(request.getParameter("dim2"));
                    float width = Float.parseFloat(request.getParameter("dim3"));
                    int woodId = Integer.parseInt(request.getParameter("wood"));
                    ResultSet r = s.executeQuery(String.format("select * from woods where id=%d", woodId));
                    r.next();
                    String woodName = r.getString("wood_name");
                    float density = r.getFloat("density");
                    Wood w = new Wood(woodId, woodName, density);
                    Timber t = new Timber(w, length, height, width);
                    String addQuery = "insert into products (name, weight, wood, dim1, dim2, dim3) " +
                            String.format("values ('%s', %f, %d, %f, %f, %f)", t.getName(), t.getWeight(),
                                    t.getWood().getId(), t.getLength(), t.getHeight(), t.getWidth());
                    s.executeUpdate(addQuery);
                    break;
                case "cylinder":
                    length = Float.parseFloat(request.getParameter("dim1"));
                    float diameter = Float.parseFloat(request.getParameter("dim2"));
                    woodId = Integer.parseInt(request.getParameter("wood"));
                    r = s.executeQuery(String.format("select * from woods where id=%d", woodId));
                    r.next();
                    woodName = r.getString("wood_name");
                    density = r.getFloat("density");
                    w = new Wood(woodId, woodName, density);
                    Cylinder cylinder = new Cylinder(w, length, diameter);
                    addQuery = "insert into products (name, weight, wood, dim1, dim2) " +
                            String.format("values ('%s', %f, %d, %f, %f)", cylinder.getName(), cylinder.getWeight(),
                                    cylinder.getWood().getId(), cylinder.getLength(), cylinder.getDiameter());
                    s.executeUpdate(addQuery);
                    break;
                case "waste":
                    float weight = Float.parseFloat(request.getParameter("weight"));
                    Waste waste = new Waste(weight);
                    addQuery = "insert into products (name, weight) " +
                            String.format("values ('%s', %f)", "Waste", waste.getWeight());
                    s.executeUpdate(addQuery);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("/list-products");
    }
}
