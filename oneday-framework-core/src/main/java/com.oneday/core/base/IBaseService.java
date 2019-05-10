package com.oneday.core.base;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

/**
 * @author zhuangzf
 * @date 2018/8/31 15:39
 */
public interface IBaseService<T > {
    /**
     * 增加一条记录
     * @param t
     * @return T，带有ID
     */
    T add(T t);

    /**
     * 增加集合
     *
     * @param tList
     */
    List<T> addList(List<T> tList);

    /**
     * 通过ID查找
     *
     * @param id
     * @return
     */
    T findById(long id);

    T findByIdAndTenantId(long id);

    /**
     * 通过ID集合查找
     *
     * @param idList
     * @return
     */
    List<T> findByIdList(List<Long> idList);

    /**
     * 返回Optional
     *
     * @param id
     * @return  Optional<T>
     */
    Optional<T> findOptionalById(long id);


    /**
     * 获取全部记录
     *
     * @return
     */
    List<T> listAll();

    /**
     * 根据条件查询，不分页
     *
     * @param spec
     * @return
     */
    List<T> list(Specification<T> spec);

    /**
     * 根据条件查询，不分页,排序
     *
     * @param spec
     * @return
     */
    List<T> list(Specification<T> spec, Sort sort);

    /**
     * 根据条件查询，分页
     *
     * @param spec
     * @return
     */
    Page<T> list(Specification<T> spec, PageRequest pageRequest);

    /**
     * 根据条件查找一条记录
     *
     * @param spec
     * @return
     */
    T findOne(Specification<T> spec);

    T findOne(Example<T> example);

    /**
     *
     * @param spec
     * @return
     */
    Optional<T> findOptionalOne(Specification<T> spec);

    /**
     * 更新
     *
     * @param t
     * @return
     */
    T update(T t);

    /**
     * 更新集合
     *
     * @param tList
     */
    List<T> updateList(List<T> tList);

    String[] delete(String ids);

    String[] deleteByIdAndTenantId(String ids);

    void delete(T entity);

    void deleteAll(List<T> tList);

    void deleteById(long id);
}
