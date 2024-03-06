package com.broad.emc.module.service;


import com.broad.emc.module.entity.Cpxx;

import java.util.List;

/**
 * 产品信息
 *
 * @author tkh
 * @email 1743962435@qq.com
 * @date 2023-09-19 10:25:48
 */
public interface CpdmService {
    /**
     * 根据年度和产品类型查询产品信息
     * @param nd
     * @param cplx
     * @return
     */
    List<Cpxx> queryAll(String nd, String cplx);
    
    /**
     * 新增产品信息
     * @param cpdm
     * @return
     */
    int insertCpxx(Cpxx cpdm);

    /**
     * 根据产品编码删除产品信息
     * @param cpbm
     * @return
     */
    int delCpxxByCpbm(String cpbm);

    /**
     * 更新产品信息
     * @param cpbm
     * @return
     */
    int updateCpxxByCpbm(Cpxx cpbm);

    /**
     * 根据产品类型查询本类的当天数量
     * @param cplx
     * @return
     */
    int queryNum( String cplx);

}

