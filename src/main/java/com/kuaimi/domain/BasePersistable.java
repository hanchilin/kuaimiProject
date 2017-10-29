package com.kuaimi.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author : chenwei
 * @create : 2017-10-04 23:19
 */
@MappedSuperclass
@Data
public class BasePersistable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Version
    private Integer version;

    @PrePersist
    @PreUpdate
    public void updateDates(){
        if(createdAt == null){
            createdAt = new Date();
        }
        updatedAt = new Date();
    }
}
