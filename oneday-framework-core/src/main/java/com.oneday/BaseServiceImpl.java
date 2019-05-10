package com.oneday;

import com.oneday.core.base.IBaseService;
import com.oneday.core.base.Unique;
import com.oneday.core.global.GlobalEnum;
import com.oneday.core.util.StringUtils;
import com.oneday.core.exception.BaseException;
import com.oneday.core.web.ProjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author zhuangzf
 * @date 2018/8/31 15:42
 */
public abstract class BaseServiceImpl<T extends Unique> implements IBaseService<T> {

    protected BaseRepository<T> baseRepository;

    @Autowired
    public BaseServiceImpl(BaseRepository<T> baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    public T add(T t) {
        return baseRepository.save(Unique.toAdd(t));
    }

    @Override
    public List<T> addList(List<T> tList) {
      return  baseRepository.saveAll(tList);
    }

    @Override
    public T findById(long id) {
        return baseRepository.findById(id).orElseThrow(() -> new BaseException(GlobalEnum.NON_EXIST));
    }

    @Override
    public T findByIdAndTenantId(long id) {
        return baseRepository.findOne(
                ((root, query, criteriaBuilder) -> query.where(
                        criteriaBuilder.equal(root.<Long>get("id"),id ),
                        criteriaBuilder.equal(root.<String>get("tenantCode"), ProjectUtil.getTenantCode() )
                ).getRestriction())
        ).orElseThrow(() -> new BaseException(GlobalEnum.NON_EXIST));
    }

    @Override
    public List<T> findByIdList(List<Long> idList) {
        return baseRepository.findAllById(idList);
    }

    @Override
    public Optional<T> findOptionalById(long id) {
        return baseRepository.findById(id);
    }

    @Override
    public List<T> listAll() {
        return baseRepository.findAll();
    }

    @Override
    public List<T> list(Specification<T> spec) {
        return baseRepository.findAll(spec);
    }
    @Override
    public List<T> list(Specification<T> spec, Sort sort) {
        return baseRepository.findAll(spec,sort);
    }

    @Override
    public Page<T> list(Specification<T> spec, PageRequest pageRequest) {
        return baseRepository.findAll(spec, pageRequest);
    }

    @Override
    public T findOne(Specification<T> spec) {
        return baseRepository.findOne(spec).orElseThrow(()->new BaseException(GlobalEnum.NON_EXIST));
    }

    @Override
    public T findOne(Example<T> example) {
        return baseRepository.findOne(example).orElseThrow(() -> new BaseException(GlobalEnum.NON_EXIST));
    }

    @Override
    public Optional<T> findOptionalOne(Specification<T> spec) {
        return baseRepository.findOne(spec);
    }

    @Override
    public T update(T t) {
        return baseRepository.save(Unique.toUpdate(t));
    }

    @Override
    public List<T> updateList(List<T> tList) {
        return baseRepository.saveAll(tList);
    }
    @Override
    public String[] delete(String ids) {
        String[] idArray = StringUtils.split(ids, StringUtils.COMMA);
        Arrays.stream(idArray).forEach(id -> baseRepository.deleteById(Long.valueOf(id)));
        return idArray;
    }

    @Override
    public String[] deleteByIdAndTenantId(String ids) {
        String[] idArray = StringUtils.split(ids, StringUtils.COMMA);
        Arrays.stream(idArray).forEach(id -> {
            findByIdAndTenantId(Long.valueOf(id));
            baseRepository.deleteById(Long.valueOf(id));
        });
        return idArray;
    }
    @Override
    public void delete(T entity) {
        baseRepository.delete(entity);
    }

    @Override
    public void deleteAll(List<T> tList) {
        baseRepository.deleteAll(tList);
    }

    @Override
    public void deleteById(long id) {
       baseRepository.deleteById(id);
    }
}
