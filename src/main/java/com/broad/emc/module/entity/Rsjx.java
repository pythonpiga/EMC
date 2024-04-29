package com.broad.emc.module.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 人事
 *
 * @author tkh
 * @email 1743962435@qq.com
 * @date 2024-04-24 11:22:34
 */
@Data
@TableName("rs_jx")
public class Rsjx implements Serializable {
        private static final long serialVersionUID = 1L;

        /**
         * $column.comments
         */
        @TableId(type = IdType.AUTO)
        private String id;
        /**
         * $column.comments
         */
        private String xm;
        /**
         * $column.comments
         */
        private String xb;
        /**
         * $column.comments
         */
        private String bm;
        /**
         * $column.comments
         */
        private String gz;
        /**
         * $column.comments
         */
        private String zw;
        /**
         * $column.comments
         */
        private Date pyrq;
        /**
         * $column.comments
         */
        private String jb;
        /**
         * $column.comments
         */
        private String zy;
        /**
         * $column.comments
         */
        private String xl;
        /**
         * $column.comments
         */
        private Float pjcs;
        /**
         * $column.comments
         */
        private String dkxh;
        /**
         * $column.comments
         */
        private Date bdrq;
        /**
         * $column.comments
         */
        private String bdyy;
        /**
         * $column.comments
         */
        private Float sdf;
        /**
         * $column.comments
         */
        private String sfzhm;
        /**
         * $column.comments
         */
        private Float dkbz;
        /**
         * $column.comments
         */
        private Date sr;
        /**
         * $column.comments
         */
        private String bz;
        /**
         * $column.comments
         */
        private Float htnx;
        /**
         * $column.comments
         */
        private String htbz;
        /**
         * $column.comments
         */
        private String zh1;
        /**
         * $column.comments
         */
        private String hzhm;
        /**
         * $column.comments
         */
        private Float zcbz;
        /**
         * $column.comments
         */
        private Date zfjj;
        /**
         * $column.comments
         */
        private String bzlb;
        /**
         * $column.comments
         */
        private String jg;
        /**
         * $column.comments
         */
        private String mz;
        /**
         * $column.comments
         */
        private Float jhbz;
        /**
         * $column.comments
         */
        private String grah;
        /**
         * $column.comments
         */
        private String txdz;
        /**
         * $column.comments
         */
        private String dh;
        /**
         * $column.comments
         */
        private String byxx;
        /**
         * $column.comments
         */
        private Date bysj;
        /**
         * $column.comments
         */
        private String ygzdw;
        /**
         * $column.comments
         */
        private Date lzyy;
        /**
         * $column.comments
         */
        private Date zftcrq;
        /**
         * $column.comments
         */
        private String email;
        /**
         * $column.comments
         */
        private Date htqdrq;
        /**
         * $column.comments
         */
        private String fw;
        /**
         * $column.comments
         */
        private String zhbz;
        /**
         * $column.comments
         */
        private String jlr;
        /**
         * $column.comments
         */
        private Date jlrq;
        /**
         * $column.comments
         */
        private String yzbm;
        /**
         * $column.comments
         */
        private String sybz;
        /**
         * $column.comments
         */
        private Date zzrq;
        /**
         * $column.comments
         */
        private String ywyId;
        /**
         * $column.comments
         */
        private String text;
        /**
         * $column.comments
         */
        private String hf;
        /**
         * $column.comments
         */
        private String jjtcsm;
        /**
         * $column.comments
         */
        private String pcsmc;
        /**
         * $column.comments
         */
        private String pcsdh;
        /**
         * $column.comments
         */
        private String srnl;
        /**
         * $column.comments
         */
        private String rybm;
        /**
         * $column.comments
         */
        private String byReason;
        /**
         * $column.comments
         */
        private String zgId;
        /**
         * $column.comments
         */
        private String flbz;
        /**
         * $column.comments
         */
        private String zgra;
        /**
         * $column.comments
         */
        private String zh;
        /**
         * $column.comments
         */
        private String gy;
        /**
         * $column.comments
         */
        private String wlkh;
        /**
         * $column.comments
         */
        private String enname;
        /**
         * $column.comments
         */
        private String bloodType;
        /**
         * $column.comments
         */
        private String stature;
        /**
         * $column.comments
         */
        private String duty;
        /**
         * $column.comments
         */
        private String weight;
        /**
         * $column.comments
         */
        private String photoflag;
        /**
         * $column.comments
         */
        private String ryzp;
        /**
         * $column.comments
         */
        private String insuranceBz;
        /**
         * $column.comments
         */
        private Date houseDate;
        /**
         * $column.comments
         */
        private String transbz;
        /**
         * $column.comments
         */
        private BigDecimal jsJzfw;
        /**
         * $column.comments
         */
        private String houseBz;
        /**
         * $column.comments
         */
        private String zwId;
        /**
         * $column.comments
         */
        private String yjjbz;
        /**
         * $column.comments
         */
        private String fhbz;
        /**
         * $column.comments
         */
        private String xnqcbz;
        /**
         * $column.comments
         */
        private BigDecimal yjjzhxs;
        /**
         * $column.comments
         */
        private BigDecimal fhxs;
        /**
         * $column.comments
         */
        private String newBm;
        /**
         * $column.comments
         */
        private String shbz;
        /**
         * $column.comments
         */
        private String shr;
        /**
         * $column.comments
         */
        private Date shsj;
        /**
         * $column.comments
         */
        private String cw;
        /**
         * $column.comments
         */
        private String oldZw;
        /**
         * $column.comments
         */
        private String rqdm;
        /**
         * $column.comments
         */
        private String fflx;
        /**
         * $column.comments
         */
        private String salaryAreaid;
        /**
         * $column.comments
         */
        private String zd;
        /**
         * $column.comments
         */
        private BigDecimal monthSalary;
        /**
         * $column.comments
         */
        private String illcode;
        /**
         * $column.comments
         */
        private String yhgz;
        /**
         * $column.comments
         */
        private String pyzt;
        /**
         * $column.comments
         */
        private String vcBmnew;
        /**
         * $column.comments
         */
        private String zwbz;
        /**
         * $column.comments
         */
        private String zwbzold;
        /**
         * $column.comments
         */
        private String sbbz;
        /**
         * $column.comments
         */
        private String ybbz;
        /**
         * $column.comments
         */
        private String meno;
        /**
         * $column.comments
         */
        private String sh;
        /**
         * $column.comments
         */
        private Date fflxSj;
        /**
         * $column.comments
         */
        private String operator;
        /**
         * $column.comments
         */
        private String gzbz;
        /**
         * $column.comments
         */
        private Date sgrq;
        /**
         * $column.comments
         */
        private Float dkbzBak;
        /**
         * $column.comments
         */
        private String gggrf;
        /**
         * $column.comments
         */
        private String khh;
        /**
         * $column.comments
         */
        private String lzsxbz;
        /**
         * $column.comments
         */
        private String dkddbz;
        /**
         * $column.comments
         */
        private String gzhzbm;
        /**
         * $column.comments
         */
        private String zzmmbz;
        /**
         * $column.comments
         */
        private String yjsbz;
        /**
         * $column.comments
         */
        private Date dateWork;
        /**
         * $column.comments
         */
        private String syqx;
        /**
         * $column.comments
         */
        private String dsznbz;
        /**
         * $column.comments
         */
        private Date yzzrq;
        /**
         * $column.comments
         */
        private String bjgzFlag;
        /**
         * $column.comments
         */
        private String bxbz;
        /**
         * $column.comments
         */
        private BigDecimal cyjt;
        /**
         * $column.comments
         */
        private String jbfFlag;
        /**
         * $column.comments
         */
        private String tranFlag;
        /**
         * $column.comments
         */
        private Date dtLog;

}
