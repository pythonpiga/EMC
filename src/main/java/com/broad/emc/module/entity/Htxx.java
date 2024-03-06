package com.broad.emc.module.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 合同
 *
 * @author tkh
 * @email 1743962435@qq.com
 * @date 2023-09-16 19:38:02
 */
@Data
@TableName("ht_ht")
public class Htxx implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * $column.comments
	 */
	@TableId(type = IdType.AUTO)
	private Integer htSno;
	/**
	 * $column.comments
	 */
	private Integer swsId;
	/**
	 * $column.comments
	 */
	private Integer xmbId;
	/**
	 * $column.comments
	 */
	private String ywyId;
	/**
	 * $column.comments
	 */
	private Integer parentHtid;
	/**
	 * $column.comments
	 */
	private String parentHtbh;
	/**
	 * $column.comments
	 */
	private String htbh;
	/**
	 * $column.comments
	 */
	private String htmc;
	/**
	 * $column.comments
	 */
	private String yyxmbh;
	/**
	 * $column.comments
	 */
	private String xmbh;
	/**
	 * $column.comments
	 */
	private String xmmc;
	/**
	 * $column.comments
	 */
	private String jjrqdm;
	/**
	 * $column.comments
	 */
	private String schtzl;
	/**
	 * $column.comments
	 */
	private String xmdx;
	/**
	 * $column.comments
	 */
	private String htlb;
	/**
	 * $column.comments
	 */
	private String saletype;
	/**
	 * $column.comments
	 */
	private String yymsdh;
	/**
	 * $column.comments
	 */
	private String sfnhzbht;
	/**
	 * $column.comments
	 */
	private String yjftlb;
	/**
	 * $column.comments
	 */
	private String pyzt;
	/**
	 * $column.comments
	 */
	private String qddd;
	/**
	 * $column.comments
	 */
	private String ajdd;
	/**
	 * $column.comments
	 */
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date qdrq;
	/**
	 * $column.comments
	 */
	private String yhjc;
	/**
	 * $column.comments
	 */
	private String yhqc;
	/**
	 * $column.comments
	 */
	private String zgsdm;
	/**
	 * $column.comments
	 */
	private String zgsmc;
	/**
	 * $column.comments
	 */
	private String khlb;
	/**
	 * $column.comments
	 */
	private String yhdz;
	/**
	 * $column.comments
	 */
	private String yhfdwtr;
	/**
	 * $column.comments
	 */
	private String yhwtdlr;
	/**
	 * $column.comments
	 */
	private String yhlxr;
	/**
	 * $column.comments
	 */
	private String yhlxdh;
	/**
	 * $column.comments
	 */
	private String yhcz;
	/**
	 * $column.comments
	 */
	private String yhyb;
	/**
	 * $column.comments
	 */
	private String yhsh;
	/**
	 * $column.comments
	 */
	private String yhkhh;
	/**
	 * $column.comments
	 */
	private String yhhh;
	/**
	 * $column.comments
	 */
	private String yhzh;
	/**
	 * $column.comments
	 */
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date jhsj;
	/**
	 * $column.comments
	 */
	private String ysfs;
	/**
	 * $column.comments
	 */
	private String dzfs;
	/**
	 * $column.comments
	 */
	private BigDecimal dtbj;
	/**
	 * $column.comments
	 */
	private BigDecimal zjbj;
	/**
	 * $column.comments
	 */
	private BigDecimal zjje;
	/**
	 * $column.comments
	 */
	private BigDecimal zjnbzkje;
	/**
	 * $column.comments
	 */
	private BigDecimal fydcpje;
	/**
	 * $column.comments
	 */
	private BigDecimal rygf;
	/**
	 * $column.comments
	 */
	private BigDecimal ryje;
	/**
	 * $column.comments
	 */
	private BigDecimal glf;
	/**
	 * $column.comments
	 */
	private BigDecimal tsf;
	/**
	 * $column.comments
	 */
	private BigDecimal jjf;
	/**
	 * $column.comments
	 */
	private BigDecimal qttcje;
	/**
	 * $column.comments
	 */
	private BigDecimal tcf;
	/**
	 * $column.comments
	 */
	private BigDecimal qtnbzkje;
	/**
	 * $column.comments
	 */
	private BigDecimal bctje;
	/**
	 * $column.comments
	 */
	private BigDecimal snjje;
	/**
	 * $column.comments
	 */
	private BigDecimal jhqje;
	/**
	 * $column.comments
	 */
	private BigDecimal fjje;
	/**
	 * $column.comments
	 */
	private BigDecimal nyfwf;
	/**
	 * $column.comments
	 */
	private BigDecimal sbtzje;
	/**
	 * $column.comments
	 */
	private BigDecimal nyfwftzje;
	/**
	 * $column.comments
	 */
	private BigDecimal jngzje;
	/**
	 * $column.comments
	 */
	private BigDecimal jgje;
	/**
	 * $column.comments
	 */
	private Integer nyfwqsnd;
	/**
	 * $column.comments
	 */
	private Integer nyfwhtnx;
	/**
	 * $column.comments
	 */
	private BigDecimal qtxff;
	/**
	 * $column.comments
	 */
	private BigDecimal qtewfy;
	/**
	 * $column.comments
	 */
	private BigDecimal tzyf;
	/**
	 * $column.comments
	 */
	private String zjmc;
	/**
	 * $column.comments
	 */
	private BigDecimal zjts;
	/**
	 * $column.comments
	 */
	private Integer zjzfh;
	/**
	 * $column.comments
	 */
	private BigDecimal rysl;
	/**
	 * $column.comments
	 */
	private BigDecimal htzje;
	/**
	 * $column.comments
	 */
	private BigDecimal cpzy;
	/**
	 * $column.comments
	 */
	private BigDecimal httcje;
	/**
	 * $column.comments
	 */
	private BigDecimal htbtje;
	/**
	 * $column.comments
	 */
	private BigDecimal htbtbl;
	/**
	 * $column.comments
	 */
	private BigDecimal ywytcAdd;
	/**
	 * $column.comments
	 */
	private String fhbz;
	/**
	 * $column.comments
	 */
	private String tsbz;
	/**
	 * $column.comments
	 */
	private BigDecimal cdhpje;
	/**
	 * $column.comments
	 */
	private BigDecimal wkbl;
	/**
	 * $column.comments
	 */
	private BigDecimal sjf;
	/**
	 * $column.comments
	 */
	private BigDecimal ajf;
	/**
	 * $column.comments
	 */
	private BigDecimal jsfwf;
	/**
	 * $column.comments
	 */
	private BigDecimal wbgcf;
	/**
	 * $column.comments
	 */
	private BigDecimal gclr;
	/**
	 * $column.comments
	 */
	private BigDecimal jncj;
	/**
	 * $column.comments
	 */
	private BigDecimal qtjl;
	/**
	 * $column.comments
	 */
	private String zjfhdj;
	/**
	 * $column.comments
	 */
	private String sgjtcbz;
	/**
	 * $column.comments
	 */
	private String httcbz;
	/**
	 * $column.comments
	 */
	private String ggjtcbz;
	/**
	 * $column.comments
	 */
	private String zbytcbz;
	/**
	 * $column.comments
	 */
	private String tbjbz;
	/**
	 * $column.comments
	 */
	private String cqjlytbz;
	/**
	 * $column.comments
	 */
	private String htbz;
	/**
	 * $column.comments
	 */
	private String fpdy;
	/**
	 * $column.comments
	 */
	private String htlrr;
	/**
	 * $column.comments
	 */
	private Date htlrsj;
	/**
	 * $column.comments
	 */
	private String zqjbz;
	/**
	 * $column.comments
	 */
	private Date sfrq;
	/**
	 * $column.comments
	 */
	private BigDecimal sfje;
	/**
	 * $column.comments
	 */
	private BigDecimal yfbl;
	/**
	 * $column.comments
	 */
	private BigDecimal yfje;
	/**
	 * $column.comments
	 */
	private String qyxz;
	/**
	 * $column.comments
	 */
	private String yhlb;
	/**
	 * $column.comments
	 */
	private String xmlb;
	/**
	 * $column.comments
	 */
	private String zdxmbz;
	/**
	 * $column.comments
	 */
	private String jzwlb;
	/**
	 * $column.comments
	 */
	private String dc;
	/**
	 * $column.comments
	 */
	private BigDecimal zgd;
	/**
	 * $column.comments
	 */
	private BigDecimal cg;
	/**
	 * $column.comments
	 */
	private BigDecimal zmj;
	/**
	 * $column.comments
	 */
	private BigDecimal symj;
	/**
	 * $column.comments
	 */
	private BigDecimal bgmj;
	/**
	 * $column.comments
	 */
	private BigDecimal qtmj;
	/**
	 * $column.comments
	 */
	private BigDecimal jzmj;
	/**
	 * $column.comments
	 */
	private BigDecimal ktmj;
	/**
	 * $column.comments
	 */
	private BigDecimal sjtzze;
	/**
	 * $column.comments
	 */
	private BigDecimal sjlrl;
	/**
	 * $column.comments
	 */
	private BigDecimal sjtzhbq;
	/**
	 * $column.comments
	 */
	private Date tzhbrq;
	/**
	 * $column.comments
	 */
	private BigDecimal htztze;
	/**
	 * $column.comments
	 */
	private BigDecimal htkhtze;
	/**
	 * $column.comments
	 */
	private BigDecimal htydtze;
	/**
	 * $column.comments
	 */
	private String jjfs;
	/**
	 * $column.comments
	 */
	private String sktcfs;
	/**
	 * $column.comments
	 */
	private String zjtcfs;
	/**
	 * $column.comments
	 */
	private BigDecimal lfh;
	/**
	 * $column.comments
	 */
	private BigDecimal rfh;
	/**
	 * $column.comments
	 */
	private BigDecimal wsrsl;
	/**
	 * $column.comments
	 */
	private String dd;
	/**
	 * $column.comments
	 */
	private Date dhsj;
	/**
	 * $column.comments
	 */
	private String jfwz;
	/**
	 * $column.comments
	 */
	private BigDecimal jfmj;
	/**
	 * $column.comments
	 */
	private String jfzy;
	/**
	 * $column.comments
	 */
	private String glbz;
	/**
	 * $column.comments
	 */
	private String kjbz;
	/**
	 * $column.comments
	 */
	private String jfbz;
	/**
	 * $column.comments
	 */
	private String ydwz;
	/**
	 * $column.comments
	 */
	private String yhphcd;
	/**
	 * $column.comments
	 */
	private String czysz;
	/**
	 * $column.comments
	 */
	private String jfyxbz;
	/**
	 * $column.comments
	 */
	private String yhtd;
	/**
	 * $column.comments
	 */
	private BigDecimal gymj;
	/**
	 * $column.comments
	 */
	private String htzyxd;
	/**
	 * $column.comments
	 */
	private BigDecimal dzxff;
	/**
	 * $column.comments
	 */
	private BigDecimal yfxff;
	/**
	 * $column.comments
	 */
	private BigDecimal sjxff;
	/**
	 * $column.comments
	 */
	private String znxmylbh;
	/**
	 * $column.comments
	 */
	private String znhbxm;
	/**
	 * $column.comments
	 */
	private String znxmxx01;
	/**
	 * $column.comments
	 */
	private String znxmxx02;
	/**
	 * $column.comments
	 */
	private String znxmxx03;
	/**
	 * $column.comments
	 */
	private String znxmxx04;
	/**
	 * $column.comments
	 */
	private String znxmxx05;
	/**
	 * $column.comments
	 */
	private String zdxm;
	/**
	 * $column.comments
	 */
	private String thxmbz;
	/**
	 * $column.comments
	 */
	private String xqhtbz;
	/**
	 * $column.comments
	 */
	private String yxhtbz;
	/**
	 * $column.comments
	 */
	private String ywyxht;
	/**
	 * $column.comments
	 */
	private String gskhdm;
	/**
	 * $column.comments
	 */
	private String htxj;
	/**
	 * $column.comments
	 */
	private String htycqk;
	/**
	 * $column.comments
	 */
	private String htbffkbz;
	/**
	 * $column.comments
	 */
	private String ckhtbz;
	/**
	 * $column.comments
	 */
	private String hfhtbz;
	/**
	 * $column.comments
	 */
	private String xdhtbz;
	/**
	 * $column.comments
	 */
	private String jnfxhtbz;
	/**
	 * $column.comments
	 */
	private String qdfs;
	/**
	 * $column.comments
	 */
	private String ishaverec;
	/**
	 * $column.comments
	 */
	private String islxxy30days;
	/**
	 * $column.comments
	 */
	private BigDecimal hfhtje;
	/**
	 * $column.comments
	 */
	private String skywy;
	/**
	 * $column.comments
	 */
	private Date fprq;
	/**
	 * $column.comments
	 */
	private String fpdybz;
	/**
	 * $column.comments
	 */
	private BigDecimal xse;
	/**
	 * $column.comments
	 */
	private String zqjgg;
	/**
	 * $column.comments
	 */
	private Date fk01date;
	/**
	 * $column.comments
	 */
	private Date fk03date;
	/**
	 * $column.comments
	 */
	private Date fk09date;
	/**
	 * $column.comments
	 */
	private Date fk10date;
	/**
	 * $column.comments
	 */
	private Date fk01dateNyzj;
	/**
	 * $column.comments
	 */
	private Date fk03dateNyzj;
	/**
	 * $column.comments
	 */
	private Date fk09dateNyzj;
	/**
	 * $column.comments
	 */
	private Date fk10dateNyzj;
	/**
	 * $column.comments
	 */
	private String tpzdbz;
	/**
	 * $column.comments
	 */
	private String rzhtbz;
	/**
	 * $column.comments
	 */
	private String zjbz;
	/**
	 * $column.comments
	 */
	private Date wkrq;
	/**
	 * $column.comments
	 */
	private BigDecimal zbj;
	/**
	 * $column.comments
	 */
	private BigDecimal htyse;
	/**
	 * $column.comments
	 */
	private BigDecimal ryyse;
	/**
	 * $column.comments
	 */
	private BigDecimal zjyse;
	/**
	 * $column.comments
	 */
	private Integer htqdf;
	/**
	 * $column.comments
	 */
	private String htgsbm;
	/**
	 * $column.comments
	 */
	private String htgsgs;
	/**
	 * $column.comments
	 */
	private String tcjsdwdh;
	/**
	 * $column.comments
	 */
	private String hzbz;
	/**
	 * $column.comments
	 */
	private String zghzbz;
	/**
	 * $column.comments
	 */
	private Integer hzrs;
	/**
	 * $column.comments
	 */
	private BigDecimal htyxcbje;
	/**
	 * $column.comments
	 */
	private BigDecimal htyxcbje1;
	/**
	 * $column.comments
	 */
	private BigDecimal xmbkzcbd;
	/**
	 * $column.comments
	 */
	private BigDecimal swskzcbd;
	/**
	 * $column.comments
	 */
	private BigDecimal htbtcbje;
	/**
	 * $column.comments
	 */
	private BigDecimal htbtcbje1;
	/**
	 * $column.comments
	 */
	private BigDecimal zjhte;
	/**
	 * $column.comments
	 */
	private BigDecimal fzjhte;
	/**
	 * $column.comments
	 */
	private BigDecimal nfwxyf;
	/**
	 * $column.comments
	 */
	private BigDecimal fwxye;
	/**
	 * $column.comments
	 */
	private String khdm;
	/**
	 * $column.comments
	 */
	private String khbh;
	/**
	 * $column.comments
	 */
	private String khmc;
	/**
	 * $column.comments
	 */
	private BigDecimal htbjbj;
	/**
	 * $column.comments
	 */
	private BigDecimal qtbjbj;
	/**
	 * $column.comments
	 */
	private BigDecimal zjxfl;
	/**
	 * $column.comments
	 */
	private BigDecimal qtxfl;
	/**
	 * $column.comments
	 */
	private BigDecimal zxfl;
	/**
	 * $column.comments
	 */
	private BigDecimal spxfje;
	/**
	 * $column.comments
	 */
	private Integer yqzjts;
	/**
	 * $column.comments
	 */
	private BigDecimal tcbl;
	/**
	 * $column.comments
	 */
	private BigDecimal zgtcbl;
	/**
	 * $column.comments
	 */
	private BigDecimal htbd;
	/**
	 * $column.comments
	 */
	private Date scfhrq;
	/**
	 * $column.comments
	 */
	private String zfbz;
	/**
	 * $column.comments
	 */
	private String sfydhqr;
	/**
	 * $column.comments
	 */
	private BigDecimal htzjeWb;
	/**
	 * $column.comments
	 */
	private BigDecimal fprmbje;
	/**
	 * $column.comments
	 */
	private BigDecimal fpwbje;
	/**
	 * $column.comments
	 */
	private BigDecimal fphl;
	/**
	 * $column.comments
	 */
	private BigDecimal gdhl;
	/**
	 * $column.comments
	 */
	private BigDecimal gdhlOy;
	/**
	 * $column.comments
	 */
	private BigDecimal gdhlMy;
	/**
	 * $column.comments
	 */
	private BigDecimal jzhl;
	/**
	 * $column.comments
	 */
	private BigDecimal jzhlOy;
	/**
	 * $column.comments
	 */
	private BigDecimal jzhlMy;
	/**
	 * $column.comments
	 */
	private BigDecimal yf;
	/**
	 * $column.comments
	 */
	private BigDecimal bxf;
	/**
	 * $column.comments
	 */
	private BigDecimal yj;
	/**
	 * $column.comments
	 */
	private String wbbz;
	/**
	 * $column.comments
	 */
	private String zgtcbz;
	/**
	 * $column.comments
	 */
	private String htztbz;
	/**
	 * $column.comments
	 */
	private String eshtbz;
	/**
	 * $column.comments
	 */
	private String cxhtbz;
	/**
	 * $column.comments
	 */
	private String tzhtbz;
	/**
	 * $column.comments
	 */
	private String tzqkbz;
	/**
	 * $column.comments
	 */
	private String zjktcbz;
	/**
	 * $column.comments
	 */
	private String qdbz;
	/**
	 * $column.comments
	 */
	private String bsjtyjbz;
	/**
	 * $column.comments
	 */
	private String jlsyjbz;
	/**
	 * $column.comments
	 */
	private String bsyjbz;
	/**
	 * $column.comments
	 */
	private String bscpzydwyjbz;
	/**
	 * $column.comments
	 */
	private String bssrbz;
	/**
	 * $column.comments
	 */
	private String jsshzbz;
	/**
	 * $column.comments
	 */
	private String lzryhtbz;
	/**
	 * $column.comments
	 */
	private String rwhtbz;
	/**
	 * $column.comments
	 */
	private String jtnbhzbz;
	/**
	 * $column.comments
	 */
	private BigDecimal lzqhtfkbl;
	/**
	 * $column.comments
	 */
	private BigDecimal lzqhtktcbl;
	/**
	 * $column.comments
	 */
	private BigDecimal xfbhbl;
	/**
	 * $column.comments
	 */
	private BigDecimal gfbhbl;
	/**
	 * $column.comments
	 */
	private BigDecimal bjbl;
	/**
	 * $column.comments
	 */
	private String yyzt;
	/**
	 * $column.comments
	 */
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date yyqsrq;
	/**
	 * $column.comments
	 */
	private String wgnszt;
	/**
	 * $column.comments
	 */
	private String xgr;
	/**
	 * $column.comments
	 */
	private Date xgsj;
	/**
	 * $column.comments
	 */
	private String htshbz;
	/**
	 * $column.comments
	 */
	private String htshr;
	/**
	 * $column.comments
	 */
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date htshsj;
	/**
	 * $column.comments
	 */
	private String htfhr;
	/**
	 * $column.comments
	 */
	private String fhycqk;
	/**
	 * $column.comments
	 */
	private String ycjjqk;
	/**
	 * $column.comments
	 */
	private String fbhtbz;
	/**
	 * $column.comments
	 */
	private String skzrr;
	/**
	 * $column.comments
	 */
	private String skzrrkh;
	/**
	 * $column.comments
	 */
	private String outtoufbz;
	/**
	 * $column.comments
	 */
	private String scorderbz;
	/**
	 * $column.comments
	 */
	private String yjjhbz;
	/**
	 * $column.comments
	 */
	private Date yjqssj;
	/**
	 * $column.comments
	 */
	private String yjqsr;
	/**
	 * $column.comments
	 */
	private String tsjxbz;
	/**
	 * $column.comments
	 */
	private String sort;
	/**
	 * $column.comments
	 */
	private String dhxmnr;
	/**
	 * $column.comments
	 */
	private String bz;
	/**
	 * $column.comments
	 */
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date sbdhrq;
	/**
	 * $column.comments
	 */
	private String sbsybz;
	/**
	 * $column.comments
	 */
	private BigDecimal yhmyd;
	/**
	 * $column.comments
	 */
	private BigDecimal htgce;
	/**
	 * $column.comments
	 */
	private BigDecimal psyggce;
	/**
	 * $column.comments
	 */
	private String ylkqcpbz;
	/**
	 * $column.comments
	 */
	private Date ylkqshrq;
	/**
	 * $column.comments
	 */
	private String ylkqshr;
	/**
	 * $column.comments
	 */
	private String sbkpsltyy;
	/**
	 * $column.comments
	 */
	private BigDecimal htcgj;
	/**
	 * $column.comments
	 */
	private BigDecimal gczbj;
	/**
	 * $column.comments
	 */
	private BigDecimal htjd;
	/**
	 * $column.comments
	 */
	private String drbz;
	/**
	 * $column.comments
	 */
	private BigDecimal ydcgbl;
	/**
	 * $column.comments
	 */
	private String zyjhtlrbz;
	/**
	 * $column.comments
	 */
	private String zgdh;
	/**
	 * $column.comments
	 */
	private Date zgtcjzrq;
	/**
	 * $column.comments
	 */
	private BigDecimal jznhzbXmjlrl;
	/**
	 * $column.comments
	 */
	private BigDecimal ktnhzbXmjlrl;
	/**
	 * $column.comments
	 */
	private BigDecimal xmjlrl;
	/**
	 * $column.comments
	 */
	private String xqnddy5year;
	/**
	 * $column.comments
	 */
	private String ddtsbz;
	
	private String flag;

}
