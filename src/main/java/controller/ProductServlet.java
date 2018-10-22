package controller;

import model.Product;
import service.ProductService;
import service.ProductServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProductServlet extends HttpServlet {
    private ProductService productService = new ProductServiceImpl();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null){
            action = "";
        }

        switch (action){
            case "add":
                showaddProduct(req, resp);
                break;
            case "edit":
                editProduct(req, resp);
                break;
            case "delete":
                showdeleteProduct(req, resp);
                break;
            case "view":
                viewProduct(req, resp);
                break;
            case "search":
                showSearch(req, resp);
                break;
            default:
                showAllProduct(req, resp);
                break;

        }
    }

    private void showSearch(HttpServletRequest req, HttpServletResponse resp) {
        String prodname = req.getParameter("prodname");
        Product product = this.productService.findByName(prodname);
        RequestDispatcher dispatcher;
        if(product == null){
            dispatcher = req.getRequestDispatcher("a.jsp");
        } else {
            req.setAttribute("product", product);
            dispatcher = req.getRequestDispatcher("/product/search.jsp");
        }
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void viewProduct(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = this.productService.findByID(id);
        RequestDispatcher dispatcher;
        if(product == null){
            dispatcher = req.getRequestDispatcher("404.jsp");
        } else {
            req.setAttribute("product", product);
            dispatcher = req.getRequestDispatcher("/product/view.jsp");
        }
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showdeleteProduct(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = this.productService.findByID(id);
        RequestDispatcher dispatcher;
        if(product == null){
            dispatcher = req.getRequestDispatcher("404.jsp");
        } else {
            req.setAttribute("product", product);
            dispatcher = req.getRequestDispatcher("/product/delete.jsp");
        }
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void editProduct(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = this.productService.findByID(id);
        RequestDispatcher dispatcher;

        if(product == null){
            dispatcher = req.getRequestDispatcher("404.jsp");
        } else {
            req.setAttribute("product", product);
            dispatcher = req.getRequestDispatcher("/product/edit.jsp");
        }

        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showaddProduct(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/product/add.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAllProduct(HttpServletRequest req, HttpServletResponse resp) {
        List<Product> productList = this.productService.findAll();
        req.setAttribute("products", productList);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/product/list.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null){
            action = "";
        }

        switch (action){
            case "add":
                addProduct(req, resp);
                break;
            case "edit":
                updateProduct(req, resp);
                break;
            case "delete":
                delProduct(req,resp);
                break;
            case "search":
                searchProduct(req, resp);
                break;
            default:
                break;

        }
    }

    private void searchProduct(HttpServletRequest req, HttpServletResponse resp) {
        String search = req.getParameter("search");
        Product product = this.productService.findByName(search);
        RequestDispatcher dispatcher;
        if(product == null){
            dispatcher = req.getRequestDispatcher("404.jsp");
        } else {
            dispatcher = req.getRequestDispatcher("/product/search.jsp");
            try {
                resp.sendRedirect("/product");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void delProduct(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = this.productService.findByID(id);
        RequestDispatcher dispatcher;
        if(product == null){
            dispatcher = req.getRequestDispatcher("404.jsp");
        } else {
            this.productService.remove(id);
            try {
                resp.sendRedirect("/product");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateProduct(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        String productname = req.getParameter("prodname");
        double price = Double.parseDouble(req.getParameter("price"));
        String description = req.getParameter("description");
        String vendorx = req.getParameter("vendorx");
        Product product = this.productService.findByID(id);
        RequestDispatcher dispatcher;
        if(product == null){
            dispatcher = req.getRequestDispatcher("404.jsp");
        } else {
            product.setProdname(productname);
            product.setPrice(price);
            product.setDescription(description);
            product.setVendorx(vendorx);
            this.productService.update(id, product);
            req.setAttribute("product", product);
            req.setAttribute("message", "Product information was updated");
            dispatcher = req.getRequestDispatcher("/product/edit.jsp");
        }
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addProduct(HttpServletRequest req, HttpServletResponse resp) {
        String prodname = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        String description = req.getParameter("description");
        String vendorx = req.getParameter("brand");
        int id = (int)(Math.random() * 10000);

        Product product = new Product(id, prodname, price, description, vendorx);
        this.productService.save(product);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/product/add.jsp");
        req.setAttribute("message", "New product was created");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
