package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.ordermgmt.converter.WctaSpectxBlkPrntConverter;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaSpectxBlkPrntDto.SaveReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaSpectxBlkPrntDto.SearchCntrRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaSpectxBlkPrntDto.SearchReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaSpectxBlkPrntDto.SearchRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaSpectxBlkPrntDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaSpectxBlkPrntMapper;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WctaSpectxBlkPrntService {
    private final WctaSpectxBlkPrntMapper mapper;
    private final WctaSpectxBlkPrntConverter converter;

    public List<SearchRes> getSpectxBlkPrnts(SearchReq dto) {
        return mapper.selectSpectxBlkPrnts(dto);
    }

    public List<SearchRes> getSpectxBlkPrntsExcelDwonload(SearchReq dto) {
        return mapper.selectSpectxBlkPrnts(dto);
    }

    public SearchCntrRes getTradeSpcshCst(String cntrNo, String cntrSn) {
        return mapper.selectTradeSpcshCst(cntrNo, cntrSn);
    }

    @Transactional
    public String saveSpectxBlkPrntsGrpNo() {
        WctaSpectxBlkPrntDvo dvo = new WctaSpectxBlkPrntDvo();
        int res = mapper.insertSsctSpectxIsBas(dvo);
        return dvo.getSpectxGrpNo();
    }

    @Transactional
    public int saveSpectxBlkPrnts(List<SaveReq> dtos) {
        int res = 0;
        String spectxGrpNoCheck = "";
        for (SaveReq dto : dtos) {
            WctaSpectxBlkPrntDvo dvo = converter.mapSaveReqToWctaSpectxBlkPrntDvo(dto);

            switch (dto.rowState()) {
                case CommConst.ROW_STATE_CREATED -> {
                    if (dvo.getSpectxGrpNo() != spectxGrpNoCheck) {
                        spectxGrpNoCheck = dvo.getSpectxGrpNo();
                        mapper.updateSsctSpectxIsBasFirst(dvo);
                        mapper.insertSsctSpectxIsChHist(dvo.getSpectxGrpNo());
                    }
                    int result = mapper.insertSsctSpectxIsDtl(dvo);
                    mapper.insertSsctSpectxPblHist(dvo.getSpectxGrpNo(), dvo.getCntrNo(), dvo.getCntrSn());
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
                }
                case CommConst.ROW_STATE_UPDATED -> {
                    if (dvo.getSpectxGrpNo() != spectxGrpNoCheck) {
                        spectxGrpNoCheck = dvo.getSpectxGrpNo();
                        mapper.insertSsctSpectxIsChHist(dvo.getSpectxGrpNo());
                        mapper.updateSsctSpectxIsBas(dvo);
                    }
                    mapper.insertSsctSpectxPblHist(dvo.getSpectxGrpNo(), dvo.getCntrNo(), dvo.getCntrSn());
                    int result = mapper.updateSsctSpectxIsDtl(dvo);
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
                }
            };
        }
        return res;
    }
    @Transactional
    public int removeSpectxBlkPrnts(List<SaveReq> dtos) {
        int res = 0;
        String spectxGrpNoCheck = "";
        for (SaveReq dto : dtos) {
            WctaSpectxBlkPrntDvo dvo = converter.mapSaveReqToWctaSpectxBlkPrntDvo(dto);
            if (dvo.getSpectxGrpNo() != spectxGrpNoCheck) {
                spectxGrpNoCheck = dvo.getSpectxGrpNo();
                mapper.insertSsctSpectxIsChHist(dvo.getSpectxGrpNo());
                mapper.deleteSsctSpectxIsBas(dvo.getSpectxGrpNo());
                mapper.insertSsctSpectxIsChHist(dvo.getSpectxGrpNo());
            }
            mapper.insertSsctSpectxPblHist(dvo.getSpectxGrpNo(), dvo.getCntrNo(), dvo.getCntrSn());
            mapper.deleteSsctSpectxIsDtl(dvo.getSpectxGrpNo(), dvo.getCntrNo(), dvo.getCntrSn());
            mapper.insertSsctSpectxPblHist(dvo.getSpectxGrpNo(), dvo.getCntrNo(), dvo.getCntrSn());
        }
        return res;
    }
}
