package com.broad.emc.module.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.broad.emc.module.vo.CustomerVo;

import java.util.List;

/**
 * @author tkh
 * @date 2023-09-16
 */
public interface CustomerDao extends BaseMapper<CustomerVo> {
    
    List<CustomerVo> queryList(CustomerVo customerVo);

}
