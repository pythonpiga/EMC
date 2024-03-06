package com.broad.emc.module.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.broad.emc.module.entity.Ywy;
import com.broad.emc.module.vo.HtywyVo;
import com.broad.emc.module.vo.YwyVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface YwyDao extends BaseMapper<Ywy> {

    /**
     * 通过事务所查询对应的业务员
     * @param id
     * @return
     */
    List<YwyVo> selectYwyBySwsId(@Param("id") String id);

    /**
     * 通过业务员姓名查询对应的id卡号
     * @param xm
     * @return
     */
    String selectYwyIdByXm(@Param("xm") String xm);

    /**
     * 根据业务员/签单人员/项目经理 姓名查询所在事务所
     * @return
     */
    @Select(" select bm.sws_id as swsId from ht_ywy ywy inner join ht_ywy_bm bm on ywy.ywy_id=bm.ywy_id where ywy_xm=#{khjl}  and zzbz=1 ")
    HtywyVo selectSwsByKhjl(@Param("khjl") String khjl);
}
