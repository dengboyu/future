package by.future.common.structure.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 泛型dao
 *
 * @author by@Deng
 * @create 2017-10-14 07:54
 */
public interface BaseDao<T> {

    /**
     * 添加一条记录
     * @author by@Deng
     * @date 2017/10/14 上午10:56
     */
    Integer insertEntity(T t) throws SQLException;

    /**
     * 批量添加
     * @author by@Deng
     * @date 2017/10/20 下午11:16
     */
    Integer insertEntityByBatch(List<T> t) throws SQLException;

    /**
     * 更新一条记录
     * @author by@Deng
     * @date 2017/10/14 上午10:59
     */
    Integer updateEntity(T t) throws SQLException;

    /**
     * 根据主键删除
     * @author by@Deng
     * @date 2017/10/14 上午10:59
     */
    Integer deleteEntity(Object id) throws SQLException;

    /**
     * 根据主键查找
     * @author by@Deng
     * @date 2017/10/14 上午10:59
     */
    T findEntityByMainId(Object id) throws SQLException;

    /**
     * 根据条件查出来是单条实体类
     * @author by@Deng
     * @date 2017/10/14 上午10:59
     */
    T findEntityByOne(T t) throws SQLException;

    /**
     * 根据列条件查找实体类集合
     * @author by@Deng
     * @date 2017/10/14 上午11:06
     */
    List<T> findEntityList(T t) throws SQLException;

    /**
     * 查询记录数
     * @author by@Deng
     * @date 2017/10/23 下午9:10
     */
    Long findEntityCount(T t) throws SQLException;


    /**
     * 单表分页查询
     * @author by@Deng
     * @date 2017/10/23 下午9:12
     */
    List<T> findPageEntityList(Map<String, Object> map) throws SQLException;


    /**
     * 单表分页查询总共数量
     * @author by@Deng
     * @date 2017/10/23 下午9:12
     */
    Long findPageEntityCount(Map<String, Object> map) throws SQLException;

}
