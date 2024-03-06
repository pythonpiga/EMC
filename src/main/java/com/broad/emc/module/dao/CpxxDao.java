package com.broad.emc.module.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.broad.emc.module.entity.Cpxx;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 产品信息设置
 *
 * @author tkh
 * @email 1743962435@qq.com
 * @date 2023-09-19 10:25:48
 */
@Mapper
public interface CpxxDao extends BaseMapper<Cpxx> {

    List<Cpxx> selectByNdAndCplx(@Param("nd") String nd, @Param("cplx") String cplx);

    @Delete("delete from ht_cpdm  where cpbh=#{cpbm}")
    int deleteByCpbm(@Param("cpbm")String cpbm);

    int updateByCpbm(Cpxx cpdm);
    
    @Select("select count(*) from ht_cpdm where  htfylb=#{cplx} and CONVERT(VARCHAR(10), lrsj, 120)=CONVERT(VARCHAR(10), GETDATE(), 120) ")
    int selectNum( @Param("cplx") String cplx);

}
