package com.kuaimi.domain.entity;

import com.kuaimi.domain.BasePersistable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author : chenwei
 * @create : 2017-10-05 13:36
 */
@Entity
@Table(name="roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BasePersistable {

    @Column(unique = true)
    private String name;
}
