package com.oneday.core.base;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.oneday.core.exception.BaseException;
import com.oneday.core.global.GlobalEnum;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

/**
 * @author zzf
 * @date 2016/11/21
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)

public class Unique implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @LastModifiedDate
    @Column(columnDefinition = "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ")
    protected Date lastModified;


    @CreatedDate
    @Column(nullable = false, updatable = false)
    protected Date createTime;


    @Column(columnDefinition = "tinyint(1) default 1", nullable = false)
    protected Integer available=1;


    public Unique() {
    }




    public final static int BAN = 0;
    public final static int ACTIVE = 1;
    public final static String ID = "id";
    public final static String CREATE_TIME = "createTime";
    public static final String LAST_MODIFIED = "lastModified";
    public final static String AVAILABLE = "available";




    public static  <T extends Unique> T toAdd(T t) {
        if (Objects.isNull(t.getId())) {
            return t;
        }
        throw new BaseException(GlobalEnum.ID_MUST_BE_NULL);
    }

    public static <T extends Unique> T toUpdate(T t) {
        Optional.ofNullable(Objects.requireNonNull(t).getId()).orElseThrow(() -> new BaseException(GlobalEnum.ID_MUST_NOT_BE_NULL));
        return t;
    }
}
