package by.gsu.epamlab.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;


@Entity
@Table(name = "images")
public class Image implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "date")
    private Date date;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public Image() {
    }

    public Image(byte[] image, Date date, String description, User user) {
        this.image = image;
        this.date = date;
        this.description = description;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image1 = (Image) o;

        return Arrays.equals(image, image1.image);

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(image);
    }
}
