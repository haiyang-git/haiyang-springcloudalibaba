package com.haiyang.sca.openapi.mapstruct;

import java.util.List;


/**
 * Mapper文件基类
 *
 * @param <D>目标对象，一般为DTO对象
 * @param <V>源对象，一般为需要转换的对象
 * @author -c
 */
public interface BaseDTO2VOMapper<D , V> {


    /**
     * 将源对象转换为目标对象
     *
     * @param v
     * @return D
     */
    D toDTO(V v);

    /**
     * 将源对象集合转换为目标对象集合
     *
     * @param es
     * @return List<D>
     */
    List<D> toDTO(List<V> es);


    /**
     * 将目标对象转换为源对象
     *
     * @param d
     * @return E
     */
    V toVO(D d);

    /**
     * 将目标对象集合转换为源对象集合
     *
     * @param ds
     * @return List<E>
     */
    List<V> toVO(List<D> ds);



}
