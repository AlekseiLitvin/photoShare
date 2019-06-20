package by.gsu.epamlab.dao.images;

import by.gsu.epamlab.model.Image;
import by.gsu.epamlab.model.User;

import java.util.List;

public interface ImageDao {
    List<Image> getImageList();

    Image getImageById(int id);

    void addImage(byte[] image, String description, User user);

    List<Image> getImageListById(int id);

    void deleteImageById(int userId, int imageId);
}
