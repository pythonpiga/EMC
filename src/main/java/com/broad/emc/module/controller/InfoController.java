package com.broad.emc.module.controller;

import com.broad.emc.module.entity.*;
import com.broad.emc.module.service.InfoService;
import com.broad.emc.common.util.RedisUtil;
import com.broad.emc.common.util.StringUtil;
import com.broad.emc.module.vo.CustomerVo;
import com.broad.emc.module.vo.ReturnData;
import com.broad.emc.module.vo.RsbmVo;
import com.broad.emc.module.vo.YwyVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/emc/info")
public class InfoController {
    
    @Resource
    private InfoService infoService;

   @Resource
   private RedisUtil redisUtil;
    
    private int i=1000;

    /**
     * 获取公司部门
     *
     * @param
     */
    @RequestMapping("/getRsbm")
    public ReturnData getRsbm() {
        ReturnData ret=ReturnData.getSuccessData();

        try {
            TreeNode tree = (TreeNode) redisUtil.get("RSZZJG");
            //如果缓存为空，则重新从数据写入redis
            if (tree == null || "".equals(tree)) {
                try {
                    List<TreeNode> nodes = new ArrayList<>();

                    //总公司
                    RsbmVo zgsRsbm = infoService.getZgs("A");
                    zgsRsbm.setBmmc(zgsRsbm.getBmmc() + "                ");
                    //根节点
                    Collections.addAll(nodes, TreeNode.builder().id(1).pid(0).bmmc(zgsRsbm.getBmmc()).build()
                    );
                    //获取各子公司及部门节点
                    setNodes(nodes, zgsRsbm, i, 1);

                    TreeNode root = nodes.stream().filter(node -> Objects.equals(node.getPid(), 0)).findFirst().orElse(null);

                    root.setChildren(getChildren(root, nodes));

                    redisUtil.set("RSZZJG", root);

                    ret.setData(root);
                    return ret;
                } catch (Exception e) {
                    log.error("初始化人事组织架构数据异常，{}", e);
                    return ReturnData.getExceptionData(e.getMessage());

                }
            }
            ret.setData(tree);
        }catch (Exception e){
            log.error("获取人事组织架构数据失败，{}", e);
            return ReturnData.getExceptionData(e.getMessage());
        }
        
        
        return ret;
        
        
        
       
    }


    /**
     * 获取子公司及子公司各部门节点
     * @param nodes tree节点
     * @param rsbmVo 父级部门对象
     * @param id    当前级别部门id 以自增i作为id
     * @param pid   当前级别的pid=父级的id 
     */
    public void setNodes(List<TreeNode> nodes,RsbmVo rsbmVo,int id,int pid){
        //根据父级部门对象的strid查询下级部门
        List<RsbmVo> fatherNode=infoService.getFgs(rsbmVo.getStrId());
        
        for(RsbmVo child: fatherNode){
            i=++i;
            child.setId(++i);
            Collections.addAll(nodes, TreeNode.builder().id( child.getId() ).pid( pid ).bmmc(child.getBmmc()).build());
            //遍历下一级
            setNodes(nodes,child, i,child.getId());
        }
        
    }


    /**
     * 递归获取根节点的子节点信息
     *
     * @param root  根节点
     * @param nodes 要遍历的节点
     * @return
     */
    private List<TreeNode> getChildren(TreeNode root, List<TreeNode> nodes) {
        List<TreeNode> childrenNodes = nodes.stream()
                // 使用stream流过滤出数据的父级id信息等于根节点的id信息的数据
                .filter(node -> Objects.equals(node.getPid(), root.getId()))
                .map(childNode -> {
                    // 同时使用map方法对每个过滤后的数据进行处理，递归找到其对应的子数据
                    childNode.setChildren(getChildren(childNode, nodes));
                    return childNode;
                })
                .collect(Collectors.toList());
        return childrenNodes;
    }


    /**
     * 初始化人事组织架构数据到Redis中
     */
    @PostConstruct
    public void initRsbmData(){
        log.info("============初始化人事组织架构数据到Redis，开始============");
        try {
            List<TreeNode> nodes = new ArrayList<>();

            //总公司
            RsbmVo zgsRsbm=infoService.getZgs("A");
            zgsRsbm.setBmmc(zgsRsbm.getBmmc()+"                ");
            //根节点
            Collections.addAll(nodes, TreeNode.builder().id(1).pid(0).bmmc(zgsRsbm.getBmmc()).build()
            );
            //获取各子公司及部门节点
            setNodes(nodes,zgsRsbm,i,1);
            
            TreeNode root = nodes.stream().filter(node -> Objects.equals(node.getPid(), 0)).findFirst().orElse(null);
            
            root.setChildren(getChildren(root, nodes));
            
            redisUtil.set("RSZZJG",root);
        }catch (Exception e){
            log.error("初始化人事组织架构数据异常，{}",e);
        }
        log.info("============初始化人事组织架构数据到Redis，结束============");
    }

    /**
     * 推送人事组织架构数据到Redis中 定时任务
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void initRsbmDataTask(){
        log.info("============初始化人事组织架构数据到Redis定时任务，开始============");
        try {
            List<TreeNode> nodes = new ArrayList<>();

            //总公司
            RsbmVo zgsRsbm=infoService.getZgs("A");
            zgsRsbm.setBmmc(zgsRsbm.getBmmc()+"                ");
            //根节点
            Collections.addAll(nodes, TreeNode.builder().id(1).pid(0).bmmc(zgsRsbm.getBmmc()).build()
            );
            //获取各子公司及部门节点
            setNodes(nodes,zgsRsbm,i,1);

            TreeNode root = nodes.stream().filter(node -> Objects.equals(node.getPid(), 0)).findFirst().orElse(null);

            root.setChildren(getChildren(root, nodes));

            redisUtil.set("RSZZJG",root);
        }catch (Exception e){
            log.error("初始化人事组织架构数据定时任务异常，{}",e);
        }
        log.info("============初始化人事组织架构数据到Redis定时任务，结束============");
    }




    /**
     * 获取公司部门
     *
     * @param 
     */
    @RequestMapping("/getGsbm")
    public ReturnData getGsbm() {
        ReturnData ret=ReturnData.getSuccessData();
        List<Gsbm> gsbms=infoService.queryGsbm();
        ret.setData(gsbms);
        return ret;
    }

    /**
     * 获取事务所
     *
     * @param
     */
    @RequestMapping("/getSws")
    public ReturnData getSws() {
        ReturnData ret=ReturnData.getSuccessData();
        List<Sws> swss=infoService.querySws();
        ret.setData(swss);
        return ret;
    }

    /**
     * 根据事务所名称获取事务所id
     *
     * @param
     */
    @RequestMapping("/getSwsId")
    public ReturnData getSwsId(@RequestParam String swsmc) {
        ReturnData ret=ReturnData.getSuccessData();
        Sws sws=infoService.getSwsId(swsmc);
        ret.setData(sws);
        return ret;
    }

    /**
     * 获取业务员/客户经理
     *
     * @param
     */
    @RequestMapping("/getYwy")
    public ReturnData getYwy(@RequestParam String id) {
        ReturnData ret=ReturnData.getSuccessData();
        if (StringUtil.isEmpty(id)) {
            return ReturnData.getFailData("缺少参数");
        }
        List<YwyVo> ywys=infoService.queryYwy(id);
        ret.setData(ywys);
        return ret;
    }


    /**
     * 根据业务员/客户经理姓名获取对应的id/dkxh
     *
     * @param
     */
    @RequestMapping("/getYwyId")
    public ReturnData getYwyId(@RequestParam String xm) {
        ReturnData ret=ReturnData.getSuccessData();
        if (StringUtil.isEmpty(xm)) {
            return ReturnData.getFailData("缺少参数");
        }
        String result=infoService.queryYwyId(xm);
        ret.setData(result);
        return ret;
    }

    /**
     * 获取销售类别
     *
     * @param
     */
    @RequestMapping("/getSaletype")
    public ReturnData getSaletype() {
        ReturnData ret=ReturnData.getSuccessData();
        List<Xslb> saletypes=infoService.querySaletype();
        ret.setData(saletypes);
        return ret;
    }

    /**
     * 获取运营部门
     *
     * @param
     */
    @RequestMapping("/getYybm")
    public ReturnData getYybm() {
        ReturnData ret=ReturnData.getSuccessData();
        List<Sws> swsList=infoService.queryYybm();
        ret.setData(swsList);
        return ret;
    }

    /**
     * 获取结算单位
     *
     * @param
     */
    @RequestMapping("/getJsdw")
    public ReturnData getJsdw() {
        ReturnData ret=ReturnData.getSuccessData();
        List<Jsdw> jsdwList=infoService.queryJsdw();
        ret.setData(jsdwList);
        return ret;
    }

    /**
     * 获取合同类别
     *
     * @param
     */
    @RequestMapping("/getHtlb")
    public ReturnData getHtlb() {
        ReturnData ret=ReturnData.getSuccessData();
        List<Htlb> htlbList=infoService.queryHtlb();
        ret.setData(htlbList);
        return ret;
    }

    /**
     * 获取产品类型
     *
     * @param
     */
    @RequestMapping("/getCplx")
    public ReturnData getCplx() {
        ReturnData ret=ReturnData.getSuccessData();
        List<Cplx> cplxList=infoService.queryCplx();
        ret.setData(cplxList);
        return ret;
    }

    /**
     * 获取产品项目编号
     *
     * @param
     */
    @RequestMapping("/getCpxm")
    public ReturnData getCpxm() {
        ReturnData ret=ReturnData.getSuccessData();
        List<Xmlb> cpxmList=infoService.queryCpxm();
        ret.setData(cpxmList);
        return ret;
    }

    /**
     * 获取成本类别
     *
     * @param
     */
    @RequestMapping("/getCblb")
    public ReturnData getCblb() {
        ReturnData ret=ReturnData.getSuccessData();
        List<Cblb> cblbList=infoService.queryCblb();
        ret.setData(cblbList);
        return ret;
    }

    /**
     * 编码获取客户
     *
     * @param
     */
    @RequestMapping("/getCustomer")
    public ReturnData getCustomer(@RequestBody CustomerVo customerVo) {
        ReturnData ret=ReturnData.getSuccessData();
        if (customerVo == null) {
            return ReturnData.getFailData("缺少参数");
        }
        try{
            List<CustomerVo> customerVoList=infoService.getCustomer(customerVo);
            ret.setData(customerVoList);
        }catch (Exception e){
            log.error("合同收款信息删除异常，异常：{}", e);
            return ReturnData.getExceptionData("删除合同收款信息，异常");
        }

        return ret;

    }


//    @GetMapping("/oneFather")
//    public TreeNode listOne() {
//        List<TreeNode> nodes = new ArrayList<>();
//        // 封装数据：模拟数据数据
//        // 注意：从数据库中查询到的数据没有childrenNode属性，那我们可以使用BeanUtils和mapstruct进行对象转换
//        // id可自定义 为避免重复可从10000开始自增
//        Collections.addAll(nodes,
//                //根节点
//                TreeNode.builder().id(1).pid(0).bmmc("xxxx公司").build(),
//                //子节点1
//                TreeNode.builder().id(2).pid(1).bmmc("总裁办").build(),
//                //1.1
//                TreeNode.builder().id(3).pid(2).bmmc("美术组").build(),
//                //1.2
//                TreeNode.builder().id(4).pid(2).bmmc("信息化部").build(),
//                //1.2.1
//                TreeNode.builder().id(5).pid(4).bmmc("软件开发课").build(),
//                //子节点2
//                TreeNode.builder().id(6).pid(1).bmmc("人力部").build(),
//                //子节点3
//                TreeNode.builder().id(7).pid(1).bmmc("财经部").build()
//
//                );
//        // 获取树节点
//        TreeNode root = nodes.stream().filter(node -> Objects.equals(node.getPid(), 0)).findFirst().orElse(null);
//        if (root == null) {
//            return null;
//        }
//        root.setChildren(getChildren(root, nodes));
//        return root;
//    }
//
//
//
//   


    /**
     * 获取用户信息
     * @param account
     * @return
     */
    @RequestMapping("/getUser")
    public ReturnData getUser(String account){
        ReturnData res=ReturnData.getSuccessData();
        Rsjx usr=infoService.findUser(account);
        res.setData(usr);
        return  res;
    }

    /**
     * 获取用户权限
     * @param account
     * @return
     */
    @RequestMapping("/getUserAuth")
    public ReturnData getUserAuth(String account){
        ReturnData res=ReturnData.getSuccessData();
        String auth=infoService.findUserAuth(account);
        res.setData(auth);
        return  res;
    }
    

}
