package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.*;

@Mapper
public interface WctaContractRegStep2Mapper {

    List<WctaContractRegStep2Dvo.PdClsfDvo> selectPdClsfs();

    List<WctaContractRegStep2Dvo.PdDvo> selectProducts(String avlChnlId, String pdFilter);

    List<WctaContractDtlDvo> selectContractDtlWithPdInfo(String cntrNo);

    int insertCntrDtlStep2(WctaContractDtlDvo dvo);

    int insertCntrHsmtrlDtlStep2(WctaContractHsmtrlDtlDvo dvo);

    int insertCntrPdRelStep2(@Param("item")
    WctaContractPdRelDvo dvo);

    int insertCntrPmotIzStep2(@Param("item")
    WctaContractPmotIzDvo dvo);

    int insertFgptRcpIzStep2(@Param("item")
    WctaFgptRcpIzDvo dvo);

    int insertCntrPrcCmptIzStep2(@Param("item")
    WctaContractPrcCmptIzDvo dvo);

    List<WctaContractRegStep2Dvo.PdDetailDvo> selectProductSuscMms(
        String pdCd, String sellTpCd, String sellChnlCd, String lrnnLvGrpDvCd
    );

    List<WctaContractRegStep2Dvo.PdDetailDvo> selectProductLrnnLvs(String pdCd);

    List<WctaContractRegStep2Dvo.PdDetailDvo> selectProductStrtLvs(String pdCd);

    WctaContractRegStep2Dvo.PdAmtDvo selectProductPrices(WctaContractDto.SearchPdAmtReq dto);

    List<WctaContractRegStep2Dvo.PdSvcDvo> selectProductdServiceInfo(String pdCd);

    int deleteCntrDtlStep2(String cntrNo);

    int deleteContractDetailHistory(String cntrNo);

    int deleteCntrHsmtrlDtlStep2(String cntrNo);

    int deleteCntrHsmtrDchHistory(String cntrNo);

    int deleteCntrPdRelStep2(String cntrNo);

    int deleteCntrPmotIzStep2(String cntrNo);

    int deleteFgptRcpIzStep2(String cntrNo);

    int deleteCntrPrcCmptIzStep2(String cntrNo);

    int deleteCntrPrccchHistory(String cntrNo);

}
