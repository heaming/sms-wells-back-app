package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.*;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.*;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaContractRegService {
    private final WctaContractRegMapper mapper;
    private final WctaContractRegStep1Mapper step1Mapper;
    private final WctaContractRegStep2Mapper step2Mapper;
    private final WctaContractRegStep3Mapper step3Mapper;
    private final WctaContractRegStep4Mapper step4Mapper;

    public boolean isNewCntr(String curCntrPrgsStatCd, String cntrPrgsStatCd) {
        return Integer.valueOf(curCntrPrgsStatCd) < Integer.valueOf(cntrPrgsStatCd);
    }

    public String getBryyMmdd(String cstNo) {
        return mapper.selectBryyMmdd(cstNo);
    }

    public String getRveDvCd(String sellTpCd) {
        return switch (sellTpCd) {
            case "3" -> "04";
            case "6" -> "05";
            default -> "03";
        };
    }

    public List<WctaContractDtlDvo> selectProductInfos(String cntrNo) {
        return step2Mapper.selectContractDtlWithPdInfo(cntrNo);
    }

    @Transactional
    public int updateCntrPrgsStatCd(String cntrNo, String cntrPrgsStatCd) {
        return mapper.updateCntrPrgsStatCd(cntrNo, cntrPrgsStatCd);
    }

    public WctaContractCstRelDvo selectCntrtInfoByCstNo(String cstNo) {
        return step1Mapper.selectCntrtInfoByCstNo(cstNo);
    }

    public WctaContractBasDvo selectContractBas(String cntrNo) {
        return mapper.selectContractBas(cntrNo);
    };

    public List<WctaContractDtlDvo> selectContractDtl(String cntrNo) {
        return mapper.selectContractDtl(cntrNo);
    };

    public List<WctaContractCstRelDvo> selectContractCstRel(String cntrNo) {
        return mapper.selectContractCstRel(cntrNo);
    };

    public List<WctaContractPrtnrRelDvo> selectContractPrtnrRel(String cntrNo) {
        return mapper.selectContractPrtnrRel(cntrNo);
    }

    List<WctaContractPrcCmptIzDvo> selectContractPrcCmptIz(String cntrNo, int cntrSn) {
        return mapper.selectContractPrcCmptIz(cntrNo, cntrSn);
    }

    List<WctaContractPdRelDvo> selectContractPdRel(String cntrNo, int cntrSn) {
        return mapper.selectContractPdRel(cntrNo, cntrSn);
    }

    WctaContractWellsDtlDvo selectContractWellsDtl(String cntrNo, int cntrSn) {
        return mapper.selectContractWellsDtl(cntrNo, cntrSn);
    }

    public List<WctaContractAdrpcBasDvo> selectContractAdrpcBas(String cntrNo) {
        return mapper.selectContractAdrpcBas(cntrNo);
    };

    public WctaContractAdrRelDvo selectContractAdrRel(String cntrNo, int cntrSn) {
        return mapper.selectContractAdrRel(cntrNo, cntrSn);
    };

    public List<WctaContractStlmRelDvo> selectContractStlmRel(String cntrNo, int cntrSn) {
        return mapper.selectContractStlmRel(cntrNo, cntrSn);
    }

    @Transactional
    public void removeStep1Data(String cntrNo) {
        step1Mapper.deleteCntrPrtnrRelStep1(cntrNo);
        step1Mapper.deleteCntrCstRelStep1(cntrNo);
        step1Mapper.deleteCntrRelStep1(cntrNo);
    }

    @Transactional
    public void removeStep2Data(String cntrNo) {
        step2Mapper.deleteCntrDtlStep2(cntrNo);
        step2Mapper.deleteContractDetailHistory(cntrNo);
        step2Mapper.deleteCntrHsmtrlDtlStep2(cntrNo);
        step2Mapper.deleteCntrHsmtrDchHistory(cntrNo);
        step2Mapper.deleteCntrPdRelStep2(cntrNo);
        step2Mapper.deleteCntrPmotIzStep2(cntrNo);
        step2Mapper.deleteFgptRcpIzStep2(cntrNo);
        step2Mapper.deleteCntrPrcCmptIzStep2(cntrNo);
        step2Mapper.deleteCntrPrccchHistory(cntrNo);
        step2Mapper.deleteCntrWellsDtlStep2(cntrNo);
    }

    @Transactional
    public void removeStep3Data(String cntrNo) {
        step3Mapper.deleteCntrStlmBasStep3(cntrNo);
        step3Mapper.deleteCntrStlmRelsStep3(cntrNo);
        step3Mapper.deleteCntrAdrpcBasStep3(cntrNo);
        step3Mapper.deleteCntrAdrRelsStep3(cntrNo);
    }

    @Transactional
    public void removeStep4Data(String cntrNo) {
        step4Mapper.updateCntrBasStep4(cntrNo, "");
    }
}
