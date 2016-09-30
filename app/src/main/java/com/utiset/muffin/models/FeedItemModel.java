package com.utiset.muffin.models;


import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by ife on 04/06/16.
 */
public class FeedItemModel extends BaseModel  {

    private String body, title, created, url, main_image, author;
    private Integer id;
    public Bitmap mainImageContainer;

    public FeedItemModel(String body, String title, String author, String created, String url, String main_image, Integer id)
    {
        setId(id);
        setBody(body);
        setCreated(created);
        setAuthor(author);
        setUrl(url);
        setMain_image(main_image);
        setTitle(title);

    }
    public FeedItemModel(String title, String main_image)
    {
        setTitle(title);
        setMain_image(main_image);
    }
    public FeedItemModel()
    {}


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMain_image() {
        return main_image;
    }

    public void setMain_image(String main_image) {
        this.main_image = main_image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Bitmap getMainImageContainer() {
        return mainImageContainer;
    }

    public void setMainImageContainer(Bitmap mainImageContainer) {
        this.mainImageContainer = mainImageContainer;
    }
}
