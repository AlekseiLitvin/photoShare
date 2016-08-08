package by.gsu.epamlab.factories;

import by.gsu.epamlab.dao.images.ImageDao;
import by.gsu.epamlab.dao.images.ImageDaoImpl;

public class ImageDaoFactory {
    public static ImageDao getClassFromFactory(){
        return new ImageDaoImpl();
    }
}
