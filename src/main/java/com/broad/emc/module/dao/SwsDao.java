package com.broad.emc.module.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.broad.emc.module.entity.Sws;

import java.util.List;

public interface SwsDao extends BaseMapper<Sws> {
    List<Sws> selectYybm();

    Sws selectSwsIdBySwsmc(String swsmc);
    
}
