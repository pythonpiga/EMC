package com.broad.emc.module.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.broad.emc.module.entity.HtHte;
import com.broad.emc.module.vo.HtHteVo;
import com.broad.emc.module.vo.HtHtetzxxVo;
import com.broad.emc.module.vo.HtywyVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 合同额dao
 *
 * @author tkh
 * @email 1743962435@qq.com
 * @date 2023-10-09 10:18:28
 */
@Mapper
public interface HtHteDao extends BaseMapper<HtHte> {

   
    List<HtHteVo> queryAnnualContractByHtsno(String htsno);

    List<HtHtetzxxVo> queryHistoryById(String id);


    int addAnnualContract(HtHteVo htHteVo);
    
    @Delete(" update  ht_hte  set flag='0' where id=#{id}  ")
    int delAnnualContract(HtHteVo htHteVo);

    int updateByIdAndHtsno(HtHteVo htHteVo);

    int addRecord(HtHtetzxxVo htHtetzxxVo);

    List<HtywyVo> getYwyListByHtywyvo(HtywyVo htywyVo);

    int addYwyByHtywyvo(HtywyVo htywyVo);

    int updateYwyByHtywyvo(HtywyVo htywyVo);

    @Delete( " delete from ht_ywy where ywy_id=#{dkxh} " )
    int deleteYwyByHtywyvo(HtywyVo htywyVo);
    
}
