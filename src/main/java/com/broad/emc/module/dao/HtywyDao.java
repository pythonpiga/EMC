package com.broad.emc.module.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.broad.emc.module.entity.Htywy;
import com.broad.emc.module.vo.HtywyVo;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

/**
 * @author tkh
 * @date 2023-09-16
 */
public interface HtywyDao extends BaseMapper<Htywy> {

    List<HtywyVo>  selectInfoList(String htsno);

    List<HtywyVo>  selectTcryHistoryList(String htsno);

    int insertTcry(HtywyVo htywyVo);

    int updateTcryByHtsno(HtywyVo htywyVo);

    @Delete(" delete from ht_htywy where ht_sno=#{htsno} and  ywy_id =(select dkxh  from rs_jx where xm=#{khjl} )  and tcbl =#{tcbl} ")
    int delTcryByHtsno(HtywyVo htywyVo);

}
