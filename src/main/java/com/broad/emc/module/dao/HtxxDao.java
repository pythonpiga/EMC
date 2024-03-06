package com.broad.emc.module.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.broad.emc.module.entity.*;
import com.broad.emc.module.vo.*;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * ${comments}
 *
 * @author tkh
 * @email 1743962435@qq.com
 * @date 2023-09-16 19:38:02
 */
@Mapper
public interface HtxxDao extends BaseMapper<Htxx> {
    
    int insertContract(HtxxVo htvo);
    
    int insertDhmx(HtDhmx htDhmx);

    int insertDhmxDetail(HtDhmxVo htDhmxVo);

    int insertJzbh(HtJzbh jzxx);

    List<HtxxVo> queryContract(HtxxVo htxxVo);

    List<HtxxVo> queryContract1(HtxxVo htxxVo);


    @Select("select * from ht_ht where htbh=#{htbh}")
    Htxx queryContractByHtbh(String htbh);

    List<HtSkVo> queryHtskByHtsno(String htsno, String time);
    
    int insertHtSk(HtSkVo htSkVo);
    
    int updateHtSkById(HtSkVo htSkVo);

    @Delete(" delete from ht_fkb where id=#{skxh} " )
    int deleteHtSkById(HtSkVo htSkVo);

    @Update(" update ht_ht  set flag='0'  where ht_sno=#{htsno} " )
    int deleteContractByHtsno(String htsno);

    List<HtDhmxVo> queryDhmxByHtsno(String htsno);
    
    @Select(" select nbzkbl from ht_htfyl where htfylb=#{cplxid}  ")
    Double selectNbzkblByCplxid(String cplxid);
    
    List<HtCb> queryCbByHtsno(String htsno);

    List<HtFyVo> queryFyByHtsno(String htsno);

    List<HtNf> queryNfByHtsno(String htsno);

    List<HtFkgd> queryFkgdByHtsno(String htsno);
    
    int updateContractByHtsno(HtxxVo htxxVo);

    HtJeqkVo queryJeqkByHtsno(String htsno);

    int updateJeqkByHtsno(HtJeqkVo htJeqkVo);

    int updateDhmxByCpbh(HtDhmxVo htDhmxVo);
    
    @Delete(" update  ht_cpgg  set flag='0' where  cpdh =#{cpdh} ")
    int deleteByCpdh(HtDhmxVo htDhmxVo);
    
    int addFkgdByHtsno(HtFkgd htFkgd);
    
    int updateFkgdByGdno(HtFkgd htFkgd);

    @Delete(" update ht_fkgdxm set flag='0' where  gd_no =#{gdNo} ")
    int delFkgdByGdno(HtFkgd htFkgd);

    @Select(" select * from ht_jzxx where  (flag is null or flag='')  and fw_sno=#{htsno} ")
    List<HtJzxx> selectJzxxByHtsno(String htsno);
    
    int addJzxxByHtsno(HtJzxx htJzxx);
    
    int updateByHtsnoAndLrsj(HtJzxx htJzxx);

    @Delete(" update  ht_jzxx set flag='0' where  fw_sno =#{fwSno} and lrsj=#{lrsj} ")
    int delByHtsnoAndLrsj(HtJzxx htJzxx);

    int addCbByHtsno(HtCb htcb);
    
    @Delete(" delete from ht_emccb where ht_sno=#{htSno} and lrsj=#{lrsj} ")
    int delCbByHtsno(HtCb htcb);
    
    int updateCbByHtsno(HtCb htcb);

    int addFyByHtsno(HtFyVo htFyVo);

    @Delete(" delete from ht_emccbfy where id=#{id} ")
    int delFyById(HtFyVo htFyVo);

    int updateFyById(HtFyVo htFyVo);
    
    int insertNf(HtNf htNf);
    
    int updateNfByFwsno(HtNf htNf);
    
    @Delete(" delete from ht_emcfwht where fw_sno=#{fwSno} ")
    int deleteNfByFwsno(HtNf htNf);





}
