package net.wikmans.srmsexample.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Daniel on 2015-03-13.
 */
@Entity
public class Item {

    private @Id
    @GeneratedValue
    Long id;
    private String description;

    public Item() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}