package controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import daoImpl.VendorProductDao;
import domain.ProductVegie;

@WebServlet("/vendor/products/add")
@MultipartConfig
public class AddProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer vendorId = (Integer) session.getAttribute("user_id");
        String userType = (String) session.getAttribute("userType");

        if (vendorId == null || !"vendor".equalsIgnoreCase(userType)) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int price = Integer.parseInt(request.getParameter("price"));
        int quantity_kg = Integer.parseInt(request.getParameter("quantity_kg"));

        // Get the uploaded file
        Part filePart = request.getPart("image");
        String fileName = getSubmittedFileName(filePart);

        // Define where to save the file
        String uploadPath = getServletContext().getRealPath("") + File.separator + "assets" + File.separator + "vegetables";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdirs();

        // Save the file
        String filePath = uploadPath + File.separator + fileName;
        filePart.write(filePath);

        // Store the path in the database (relative to webapp)
        String imageAddress = "assets/vegetables/" + fileName;

        ProductVegie product = new ProductVegie();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setQuantity_kg(quantity_kg);
        product.setImage_address(imageAddress);

        VendorProductDao vendorProductDao = new VendorProductDao();
        vendorProductDao.insertProduct(product, vendorId);

        response.sendRedirect(request.getContextPath() + "/vendor/products/list");
    }

    private static String getSubmittedFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1);
            }
        }
        return null;
    }
}

