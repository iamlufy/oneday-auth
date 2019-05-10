package com.oneday;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author zhuangzf
 * @date 2018/8/31 14:49
 */
public interface BaseRepository<T> extends JpaRepository<T,Long>,JpaSpecificationExecutor<T> {
}
