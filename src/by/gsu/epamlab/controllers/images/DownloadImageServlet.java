package by.gsu.epamlab.controllers.images;

import by.gsu.epamlab.constants.ConstantsAddress;
import by.gsu.epamlab.dao.images.ImageDao;
import by.gsu.epamlab.factories.ImageDaoFactory;
import by.gsu.epamlab.model.Image;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


@WebServlet(urlPatterns = "/download/*")
public class DownloadImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getPathInfo().substring(1));
        ImageDao imageDao = ImageDaoFactory.getClassFromFactory();
        Image image = imageDao.getImageById(id);

        if (image == null){
            resp.sendRedirect(ConstantsAddress.ERROR_PAGE);
            return;
        }

        int bytesRead;
        OutputStream out = resp.getOutputStream();

        resp.setContentType("image/jsp");
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        resp.setHeader("Content-disposition", "attachment; filename = img" + image.getId() + ".jpg;");

        try
        {
            bis = new BufferedInputStream(new ByteArrayInputStream(image.getImage()));
            bos = new BufferedOutputStream(out);

            while (-1 != (bytesRead = bis.read())) {
                bos.write(bytesRead);
                bos.flush();
            }
            resp.sendRedirect(ConstantsAddress.MAIN_PAGE_SERVLET);
        }
        finally
        {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
