package entities;

import java.io.Serializable;

public class Descriptor implements Serializable {

    private String bookName;

    private String authorName;

    private String description;

    public Descriptor(String bookName, String authorName, String description) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.description = description;
    }
    public Descriptor()
    {

    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Descriptor{" +
                "bookName='" + bookName + '\'' +
                ", authorName='" + authorName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
