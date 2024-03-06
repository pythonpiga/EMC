package com.broad.emc.module.service;

import com.broad.emc.module.entity.*;
import com.broad.emc.module.vo.CustomerVo;
import com.broad.emc.module.vo.HtywyVo;
import com.broad.emc.module.vo.RsbmVo;
import com.broad.emc.module.vo.YwyVo;


import java.util.List;


public interface InfoService {

    /**
     * 查询公司部门主体
     * @return
     */
    List<Gsbm> queryGsbm();

    /**
     * 查询事务所
     * @return
     */
    List<Sws> querySws();

    /**
     * 根据事务所名称获取事务所id
     * @param swsmc
     * @return
     */
    Sws getSwsId(String swsmc);
    
    /**
     * 查询业务员/签单人员/项目经理
     * @return
     */
    List<YwyVo>  queryYwy(String id);

    /**
     * 根据业务员/客户经理姓名获取对应的id/dkxh
     *
     * @param
     */
    String queryYwyId(String xm);

    /**
     * 根据业务员/签单人员/项目经理 姓名查询所在事务所
     * @return
     */
    HtywyVo getSwsByKhjl(String khjl);

    /**
     * 查询销售类别
     * @return
     */
    List<Xslb>  querySaletype( );

    /**
     * 查询运营部门
     * @return
     */
    List<Sws>  queryYybm( );

    /**
     * 查询结算单位
     * @return
     */
    List<Jsdw> queryJsdw( );

    /**
     * 查询合同类别
     * @return
     */
    List<Htlb>  queryHtlb( );

    /**
     * 查询产品类型
     * @return
     */
    List<Cplx>  queryCplx( );

    /**
     * 查询产品项目编号
     * @return
     */
    List<Xmlb>  queryCpxm();

    /**
     * 查询成本类别
     * @return
     */
    List<Cblb>  queryCblb();

    /**
     * 查询用友客户信息
     * @return
     */
    List<CustomerVo>  getCustomer(CustomerVo customerVo);


    /**
     * 查询公司部门主体
     * @return
     */
    RsbmVo getZgs(String strId);

    List<RsbmVo> getFgs(String strUpid);

    /**
     * 根据账号（打卡序号）查找用户信息
     * @param account
     * @return
     */
    Rsjx findUser(String account);

}
