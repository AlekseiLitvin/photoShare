package by.gsu.epamlab.controllers.images;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.ConstantsAddress;
import by.gsu.epamlab.constants.ConstantsError;
import by.gsu.epamlab.controllers.AbstractServlet;
import by.gsu.epamlab.controllers.images.filers.BWFilter;
import by.gsu.epamlab.dao.images.ImageDao;
import by.gsu.epamlab.factories.ImageDaoFactory;
import by.gsu.epamlab.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;


@WebServlet(urlPatterns = "/upload", name = "upload")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
public class UploadImageServlet extends AbstractServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Part part = req.getPart(Constants.FILE);
            String description = req.getParameter(Constants.DESCRIPTION);
            String blackAndWhite = req.getParameter(Constants.BLACK_AND_WHITE_FILTER);

            String validationResult = validate(part, description);
            if (!Constants.OK.equals(validationResult)){
                jumpError(validationResult, ConstantsAddress.MAIN_PAGE_SERVLET, req, resp);
                return;
            }

            InputStream inputStream = part.getInputStream();
            byte[] image = new byte[(int) part.getSize()];
            inputStream.read(image);
            inputStream.close();

            if (blackAndWhite != null){
                image = BWFilter.convertToBlackAndWhite(image);
            }

            ImageDao imageDao = ImageDaoFactory.getClassFromFactory();
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute(Constants.USER);
            imageDao.addImage(image, description, user);

            resp.sendRedirect(ConstantsAddress.MAIN_PAGE_SERVLET);

        }catch (IllegalStateException e){
            jumpError(ConstantsError.FILE_SIZE_ERROR, ConstantsAddress.MAIN_PAGE, req, resp);
        }
    }

    private String validate(Part part, String description){
        if (part.getSize() == 0){
            return ConstantsError.FILE_IS_EMPTY;
        }
        if (!part.getContentType().startsWith(Constants.IMAGE_PREFIX)){
            return ConstantsError.NO_IMAGE_ERROR;
        }
        if (description != null && description.length() >= Constants.MAX_DESCRIPTION_LENGTH){
            return ConstantsError.MAX_DESCR_LENGTH_ERR;
        }

        return Constants.OK;
    }
}
