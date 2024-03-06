package com.broad.emc.module.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

    import java.io.Serializable;
import java.util.Date;

/**
 * 员工信息表
 *
 * @author tkh
 * @email 1743962435@qq.com
 * @date 2023-12-26 14:52:42
 */
@Data
@TableName("ding_rs_jx")
public class Rsjx implements Serializable {
    private static final long serialVersionUID = 1L;

            /**
         * $column.comments
         */
                @TableId(type = IdType.AUTO)
            private String dingUserid;
            /**
         * $column.comments
         */
            private String bmmc;
            /**
         * $column.comments
         */
            private String zwmc;
            /**
         * $column.comments
         */
            private String xm;
            /**
         * $column.comments
         */
            private String dkxh;
            /**
         * $column.comments
         */
            private String sfzg;
            /**
         * $column.comments
         */
            private String vcMobile;
            /**
         * $column.comments
         */
            private String eMail;
            /**
         * $column.comments
         */
            private String vcTelOffice;
            /**
         * $column.comments
         */
            private String vcMobileSpare;
            /**
         * $column.comments
         */
            private String bmid;
            /**
         * $column.comments
         */
            private String strId;
            /**
         * $column.comments
         */
            private String strUpid;
            /**
         * $column.comments
         */
            private String deptName;
            /**
         * $column.comments
         */
            private Long deptDing;
            /**
         * $column.comments
         */
            private String upAct;
            /**
         * $column.comments
         */
            private String userid;
            /**
         * $column.comments
         */
            private String unionid;
            /**
         * $column.comments
         */
            private String name;
            /**
         * $column.comments
         */
            private String avatar;
            /**
         * $column.comments
         */
            private String stateCode;
            /**
         * $column.comments
         */
            private String managerUserid;
            /**
         * $column.comments
         */
            private String mobile;
            /**
         * $column.comments
         */
            private Boolean hideMobile;
            /**
         * $column.comments
         */
            private String telephone;
            /**
         * $column.comments
         */
            private String jobNumber;
            /**
         * $column.comments
         */
            private String title;
            /**
         * $column.comments
         */
            private String email;
            /**
         * $column.comments
         */
            private String orgEmail;
            /**
         * $column.comments
         */
            private String workPlace;
            /**
         * $column.comments
         */
            private String remark;
            /**
         * $column.comments
         */
            private Boolean exclusiveAccount;
            /**
         * $column.comments
         */
            private String deptIdList;
            /**
         * $column.comments
         */
            private String deptOrderList;
            /**
         * $column.comments
         */
            private String extension;
            /**
         * $column.comments
         */
            private Long hiredDate;
            /**
         * $column.comments
         */
            private Boolean active;
            /**
         * $column.comments
         */
            private Boolean realAuthed;
            /**
         * $column.comments
         */
            private Boolean senior;
            /**
         * $column.comments
         */
            private Boolean admin;
            /**
         * $column.comments
         */
            private Boolean boss;
            /**
         * $column.comments
         */
            private String leaderInDept;
            /**
         * $column.comments
         */
            private String roleList;
            /**
         * $column.comments
         */
            private String unionEmpExt;
            /**
         * $column.comments
         */
            private String deptTitleList;
            /**
         * $column.comments
         */
            private Boolean seniorMode;
            /**
         * $column.comments
         */
            private String language;
            /**
         * $column.comments
         */
            private String forceUpdateFields;
            /**
         * $column.comments
         */
            private String orgEmailType;
            /**
         * $column.comments
         */
            private String loginEmail;
            /**
         * $column.comments
         */
            private Date dtLog;
    
}
