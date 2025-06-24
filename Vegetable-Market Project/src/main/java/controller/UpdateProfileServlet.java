package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import daoImpl.MainDao;
import domain.User;

@WebServlet("/updateProfile")
@MultipartConfig
public class UpdateProfileServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Get current user
    User user = (User) request.getSession().getAttribute("user");
    if (user == null) {
      response.sendRedirect(request.getContextPath() + "/login.jsp");
      return;
    }

    // Handle file upload
    Part filePart = request.getPart("profileImage");
    String fileName = getSubmittedFileName(filePart);
    if (fileName != null && !fileName.isEmpty()) {
      // Save image to server
      String uploadPath = getServletContext().getRealPath("") + "assets/profile_images/";
      File uploadDir = new File(uploadPath);
      if (!uploadDir.exists()) uploadDir.mkdirs();
      String filePath = uploadPath + user.getUser_id() + "_" + fileName;
      filePart.write(filePath);

      // Save path to database
      String dbPath = "assets/profile_images/" + user.getUser_id() + "_" + fileName;
      MainDao dao = new MainDao();
      dao.updateProfileImage(user.getUser_id(), dbPath);

      // Update user in session
      user.setProfileImage(dbPath);
      request.getSession().setAttribute("user", user);
    }
    response.sendRedirect(request.getContextPath() + "/profile.jsp");
  }

  private String getSubmittedFileName(Part part) {
    for (String cd : part.getHeader("content-disposition").split(";")) {
      if (cd.trim().startsWith("filename")) {
        String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
        return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1);
      }
    }
    return null;
  }
}
