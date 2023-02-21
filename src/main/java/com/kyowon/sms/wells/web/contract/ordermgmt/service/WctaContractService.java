package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.ordermgmt.converter.WctaContractConverter;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaContractDto.*;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaCntrAprAkDvCdDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaCntrAprBaseBasDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaSpaySlamtInqrDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaContractMapper;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctaContractService {

    private final WctaContractMapper mapper;
    private final WctaContractConverter converter;

    public List<SearchRes> getApprovalAskDivides(String standardDt) {
        return mapper.selectApprovalAskDivides(standardDt);
    }

    public List<SearchConfirmAprPsicAksRes> getConfirmAprPsicAks(String cntrNo) {
        return mapper.selectConfirmAprPsicAks(cntrNo);
    }

    public List<SearchConfirmAprPsicPrchssRes> getConfirmAprPsicPrchss(String cntrNo) {
        return mapper.selectConfirmAprPsicPrchss(cntrNo);
    }

    public PagingResult<SearchConfirmApprovalBaseRes> getConfirmApprovalBasePages(
        SearchConfirmApprovalBaseReq dto,
        PageInfo pageInfo
    ) {
        return mapper.selectConfirmApprovalBases(dto, pageInfo);
    }

    public List<SearchConfirmApprovalBaseRes> getConfirmApprovalBasesExcelDownload(
        SearchConfirmApprovalBaseReq dto
    ) {
        return mapper.selectConfirmApprovalBases(dto);
    }

    @Transactional
    public int removeApprovalAskDivides(List<RemoveReq> dtos) {
        int processCount = 0;
        Iterator<RemoveReq> iterator = dtos.iterator();
        while (iterator.hasNext()) {
            RemoveReq dto = iterator.next();
            WctaCntrAprAkDvCdDvo dvo = converter.mapRemoveReqToWctaCntrAprAkDvCdDvo(dto);
            int result = mapper.deleteApprovalAskDivides(dvo);
            BizAssert.isTrue(result == 1, "MSG_ALT_DEL_ERR");
            processCount += result;
        }
        return processCount;
    }

    @Transactional
    public int saveConfirmApprovalBases(List<SaveConfirmApprovalBaseReq> dtos) {
        int processCount = 0;
        Iterator<SaveConfirmApprovalBaseReq> iterator = dtos.iterator();
        while (iterator.hasNext()) {
            SaveConfirmApprovalBaseReq dto = iterator.next();
            WctaCntrAprBaseBasDvo dvo = converter.mapSaveReqpToWctaCntrAprBaseBasDvo(dto);
            dvo.setCheckType("A");
            int checkCount = mapper.selectCountConfirmApprovalBases(dvo);
            BizAssert.isTrue(checkCount <= 0, "MSG_ALT_DUPLICATE_ROW_EXISTS");
            processCount += switch (dto.rowState()) {
                case CommConst.ROW_STATE_CREATED -> mapper.insertConfirmApprovalBases(dvo);
                case CommConst.ROW_STATE_UPDATED -> {
                    int result = mapper.updateConfirmApprovalBases(dvo);
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
                    yield result;
                }
                default -> throw new BizException("MSG_ALT_UNHANDLE_ROWSTATE");
            };
        }
        return processCount;
    }

    @Transactional
    public int removeConfirmApprovalBases(List<RemoveConfirmApprovalBaseReq> dtos) {
        int processCount = 0;
        Iterator<RemoveConfirmApprovalBaseReq> iterator = dtos.iterator();
        while (iterator.hasNext()) {
            RemoveConfirmApprovalBaseReq dto = iterator.next();
            WctaCntrAprBaseBasDvo dvo = converter.mapRemoveReqpToWctaCntrAprBaseBasDvo(dto);
            dvo.setCheckType("U");
            int checkCount = mapper.selectCountConfirmApprovalBases(dvo);
            String[] param = {dvo.getCntrAprAkDvCdNm()};
            BizAssert.isTrue(checkCount > 0, "MSG_ALT_PIC_NO_DEL_OBJ", param);
            int result = mapper.deleteConfirmApprovalBases(dvo);
            BizAssert.isTrue(result == 1, "MSG_ALT_DEL_ERR");
            processCount += result;
        }
        return processCount;
    }

    public List<SearchSpaySlamtInqrRes> getSpaySlamtInqr(SearchSpaySlamtInqrReq dto) {
        WctaSpaySlamtInqrDvo dvo = converter.mapSearchSpaySlamtInqrReqToWctaSpaySlamtInqrDvo(dto);

        //GUBN : W-WEB(홈케어）H-KSS(홈케어）, K-KSS(일반상품)
        if (dvo.getPdGubn().equals("W")) {
            if (StringUtil.isNull(dvo.getPdCd()) || StringUtil.isNull(dvo.getDscGubn())
                || StringUtil.isNull(dvo.getVlDtm())) {
                return null;
            }
        }

        //상품구분/상품코드／접수일/할인구분 필수 체크
        if (StringUtil.isNull(dvo.getPdGubn()) || StringUtil.isNull(dvo.getPdCd()) || StringUtil.isNull(dvo.getVlDtm())
            || StringUtil.isNull(dvo.getDscGubn())) {
            return null;
        }

        //상품구분 (홈케어/일반상품) 필수 체크
        if (!(dvo.getPdGubn().equals("W") || dvo.getPdGubn().equals("K") || dvo.getPdGubn().equals("H"))) {
            return null;
        }

        //쿠폰은 웹에서만 등록 가능
        if (dvo.getDscGubn().equals("C") || dvo.getDscGubn().equals("D")) {
            if (!dvo.getPdGubn().equals("W")) {
                return null;
            }
        }

        //쿠폰일 경우 ETC3-ETC4는　필수조건
        if (dvo.getPdGubn().equals("W") && (dvo.getDscGubn().equals("C") || dvo.getDscGubn().equals("D"))) {
            if (StringUtil.isNull(dvo.getDscType())) {
                return null;
            }
        }

        //상품코드별 필수 체크
        if (Arrays.asList(new String[] {"3720", "3730", "3810", "3820", "3830", "3840"}).contains(dvo.getPdCd())) {
            if (StringUtil.isNull(dvo.getPdClsfId())) {
                return null;
            }
        }

        //홈케어　상품구분１，２필수체크위한　변수
        if ((dvo.getPdGubn().equals("W") || dvo.getPdGubn().equals("H")) && (!dvo.getPdCd().equals("3710"))) {
            dvo.setPdClsfId("Y");
        }

        if (dvo.getPdCd().equals("3710")) {
            dvo.setPdClsfId("");
        }

        //홈케어 용도구분 강제세팅
        if (Arrays.asList(new String[] {"3720", "3730", "3810", "3820", "3830", "3840", "3710"})
            .contains(dvo.getPdCd())) {
            if (StringUtil.isNull(dvo.getUseGubn())) {
                dvo.setUseGubn("0");
            }
        }

        return mapper.selectSpaySlamtInqr(dvo);
    }
}
