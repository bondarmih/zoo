package dev.bondarmih.sddt.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by bondarm on 22.09.16.
 */

@Entity
@Table(name = "Post")
@Data
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "message")
    private String message;

    @Column(name = "timestamp")
    private Date timestamp;

    public Post() {
        this.message = "";
        this.timestamp = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
