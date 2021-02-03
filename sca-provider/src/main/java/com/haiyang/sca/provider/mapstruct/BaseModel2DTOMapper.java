package com.haiyang.sca.provider.mapstruct;

import java.util.List;
import java.util.Set;

/**
 * Mapper文件基类
 * @author -c
 * @param <D> 目标对象，一般为DTO对象
 * @param <M>源对象，一般为需要转换的对象
 */
public interface BaseModel2DTOMapper<D, M> {


    /**
     * 将源对象转换为目标对象
     * @param m
     * @return D
     */
    D toDTO(M m);

    /**
     * 将源对象集合转换为目标对象集合
     * @param ms
     * @return List<D>
     */
    List<D> toDTO(List<M> ms);

    /**
     * 将目标对象转换为源对象
     * @param d
     * @return E
     */
    M toModel(D d);

    /**
     * 将目标对象集合转换为源对象集合
     * @param ds
     * @return List<E>
     */
    List<M> toModel(List<D> ds);

}
