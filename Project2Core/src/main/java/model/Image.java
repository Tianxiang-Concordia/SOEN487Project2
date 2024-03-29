package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "image")
@XmlAccessorType(XmlAccessType.FIELD)
public class Image {
    private byte[] content;
    private String mime;
    @XmlElement
    private int id;

    public Image(byte[] content, String mime) {
        this.content = content;
        this.mime = mime;
    }

    public Image() {

    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Image{" +
                ", imageId='" + id + '\'' +
                ", mime='" + mime + '\'' +
                '}';
    }

}
