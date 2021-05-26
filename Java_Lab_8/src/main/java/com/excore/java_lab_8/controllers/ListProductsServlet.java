package com.excore.java_lab_8.controllers;

import com.excore.java_lab_8.ProductDbConnection;
import com.excore.java_lab_8.model.Wood;
import com.excore.java_lab_8.model.WoodProduct;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ListProductsServlet", value = "/list-products")
public class ListProductsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connection dbConnection = ProductDbConnection.getConnection();
            String productQuery = "select p.id, p.name, w.wood_name, p.weight\n" +
                    "from products p\n" +
                    "left join woods w on w.id = p.wood;";
            String woodQuery = "select * from woods";
            Statement s = dbConnection.createStatement();
            ResultSet result = s.executeQuery(productQuery);
            List<WoodProduct> productList = new ArrayList<>();
            while (result.next()) {
                int id = result.getInt("id");
                String pName = result.getString("name");
                String wName = result.getString("wood_name");
                float weight = (float) result.getDouble("weight");
                WoodProduct prod = new WoodProduct(id, pName, wName, weight);
                productList.add(prod);
            }

            result = s.executeQuery(woodQuery);

            List<Wood> woodList = new ArrayList<>();
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("wood_name");
                float density = (float) result.getDouble("density");
                Wood wood = new Wood(id, name, density);
                woodList.add(wood);
            }
            request.setAttribute("items", productList);
            request.setAttribute("woods", woodList);
            request.getRequestDispatcher("list-products.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
