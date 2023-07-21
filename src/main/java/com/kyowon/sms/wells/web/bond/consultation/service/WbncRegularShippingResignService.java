package com.kyowon.sms.wells.web.bond.consultation.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.bond.consultation.converter.WbncRegularShippingResignConverter;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncRegularShippingResignDto.SaveConfirmReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncRegularShippingResignDto.SearchReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncRegularShippingResignDto.SearchRes;
import com.kyowon.sms.wells.web.bond.consultation.dvo.WbncAuthorityResignIzDvo;
import com.kyowon.sms.wells.web.bond.consultation.mapper.WbncRegularShippingResignMapper;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbContractDtlStatCdChDvo;
import com.kyowon.sms.wells.web.contract.changeorder.service.WctbContractDtlStatCdChService;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WbncRegularShippingResignService {
    private final WbncRegularShippingResignMapper mapper;
    private final WbncRegularShippingResignConverter converter;
    private final WctbContractDtlStatCdChService wctbContractDtlStatCdChService;

    public List<SearchRes> getRegularShippingResigns(SearchReq dto) {
        return mapper.selectRegularShippingResigns(dto);
    }

    @Transactional
    public int editRegularShippingResignFinalConfirms(List<SaveConfirmReq> dtos) throws Exception {
        int processCount = 0;

        for (SaveConfirmReq dto : dtos) {
            WbncAuthorityResignIzDvo dvo = this.converter.mapSaveConfirmReqToAuthorityResignIzDvo(dto);
            // 정기배송해지 최종확정
            int result = this.mapper.updateRegularShippingResignFinalConfirm(dvo);
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");

            // 직권해지 된 계약건에 대하여 계약상태를 변경처리
            WctbContractDtlStatCdChDvo wctbContractDtlStatCdChDvo = new WctbContractDtlStatCdChDvo();
            wctbContractDtlStatCdChDvo.setCntrNo(dvo.getCntrNo());
            wctbContractDtlStatCdChDvo.setCntrSn(String.valueOf(dvo.getCntrSn()));
            wctbContractDtlStatCdChDvo.setCntrDtlStatCd("302");
            try {
                wctbContractDtlStatCdChService.editContractDtlStatCdCh(wctbContractDtlStatCdChDvo);
            } catch (Exception e) {
                throw new BizException("계약 상세 상태 변경 실패");
            }
        }
        return processCount;
    }
}
