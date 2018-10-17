package com.roowoo.log.common.service;

import com.roowoo.log.common.persistence.CrudDao;
import com.roowoo.log.common.persistence.DataEntity;
import com.roowoo.log.common.persistence.Page;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Service基类
 *
 * @author ThinkGem
 * @version 2014-05-16
 */
public abstract class CrudService<D extends CrudDao<T>, T extends DataEntity<T>> extends BaseService {

    /**
     * 持久层对象
     */
    @Autowired
    protected D dao;

    /**
     * 获取单条数据
     *
     * @param id
     * @return
     */
    public T get(String id) {
        return dao.get(id);
    }

    /**
     * 获取单条数据
     *
     * @param entity
     * @return
     */
    public T get(T entity) {
        return dao.get(entity);
    }

    /**
     * 查询列表数据
     *
     * @param entity
     * @return
     */
    public List<T> findList(T entity) {
        return dao.findList(entity);
    }

    /**
     * 查询所有
     *
     * @param entity
     * @return
     */
    public List<T> findAllList(T entity) {
        return dao.findAllList(entity);
    }

    /**
     * 查询分页数据
     *
     * @param page   分页对象
     * @param entity
     * @return
     */
    public Page<T> findPage(Page<T> page, T entity) {
        entity.setPage(page);
        page.setList(dao.findList(entity));
        return page;
    }

    /**
     * 保存数据（插入或更新）
     *
     * @param entity
     */
    public void save(T entity) {
        if (entity.getIsNewRecord()) {
            entity.preInsert();
            dao.insert(entity);
        } else {
            entity.preUpdate();
            dao.update(entity);
        }
    }

    /**
     * 删除数据
     *
     * @param entity
     */
    public void delete(T entity) {
        dao.delete(entity);
    }

}
