package by.gsu.epamlab.dao.images;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.dao.users.HibernateUtil;
import by.gsu.epamlab.exceptions.DaoException;
import by.gsu.epamlab.model.Image;
import by.gsu.epamlab.model.User;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ImageDaoImpl implements ImageDao {
    @Override
    public List<Image> getImageList() {
        Session session = HibernateUtil.getSession();
        Criteria criteria = session.createCriteria(Image.class);
        @SuppressWarnings("unchecked")
        List<Image> list = criteria.list();
        return list;
    }

    @Override
    public Image getImageById(int id) {
        Session session = HibernateUtil.getSession();
        Criteria criteria = session.createCriteria(Image.class);
        criteria.add(Restrictions.eq(Constants.ID, id));
        return  (Image)criteria.uniqueResult();
    }

    @Override
    public void addImage(byte[] image, String description, User user) {
        Session session = HibernateUtil.getSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            session.save(new Image(image, new Date(), description, user));
            tr.commit();
        }catch (Exception e){
            if (tr != null) {
                tr.rollback();
            }
            throw new DaoException(e);
        }finally {
            session.close();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Image> getImageListById(int id) {
        Session session = HibernateUtil.getSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq(Constants.ID, id));
        User user = (User) criteria.uniqueResult();
        List<Image> list = user.getImageList();
        Collections.reverse(list);
        return list;
    }


    @Override
    public void deleteImageById(int userId, int imageId) {
        final int USER_ID_INDEX = 0, IMAGE_ID_INDEX = 1;
        Session session = HibernateUtil.getSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();

            SQLQuery query = session.createSQLQuery("DELETE FROM images WHERE user_id= ? AND id = ?");
            query.setInteger(USER_ID_INDEX, userId);
            query.setInteger(IMAGE_ID_INDEX, imageId);

            query.executeUpdate();
            tr.commit();
        }catch (Exception e){
            if (tr != null) {
                tr.rollback();
            }
            throw new DaoException(e);
        }finally {
            session.close();
        }
    }


}
