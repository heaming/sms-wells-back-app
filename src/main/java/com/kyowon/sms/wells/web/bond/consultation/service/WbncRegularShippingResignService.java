package com.kyowon.sms.wells.web.bond.consultation.service;

import com.kyowon.sms.wells.web.bond.consultation.converter.WbncRegularShippingResignConverter;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncRegularShippingResignDto.SaveConfirmReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncRegularShippingResignDto.SearchReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncRegularShippingResignDto.SearchRes;
import com.kyowon.sms.wells.web.bond.consultation.dvo.WbncAuthorityResignIzDvo;
import com.kyowon.sms.wells.web.bond.consultation.mapper.WbncRegularShippingResignMapper;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbContractDtlStatCdChDvo;
import com.kyowon.sms.wells.web.contract.changeorder.service.WctbContractDtlStatCdChService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.WctzCntrDtlStatCd;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WbncRegularShippingResignService {
    private final WbncRegularShippingResignMapper mapper;
    private final WbncRegularShippingResignConverter converter;
    private final WctbContractDtlStatCdChService wctbContractDtlStatCdChService;

    /**
     * <pre>
     * 직권해지 (정기배송) 해지 조회
     * </pre>
     *
     * @param dto authRsgDt 직권해지일(필수)
     *            clctamDvCd 집금구분코드
     *            clctamPrtnrNo 집금담당자
     * @author sieun.bae
     * @since 2023-10-17
     */
    public List<SearchRes> getRegularShippingResigns(SearchReq dto) {
        return mapper.selectRegularShippingResigns(dto);
    }

    /**
     * <pre>
     * 직권해지 (정기배송) 해지 최종 확정
     * </pre>
     *
     * @param dtos baseYm 기준년월(필수)
     *             cntrNo 계약번호(필수)
     *             cntrSn 계약일련번호(필수)
     * @author sieun.bae
     * @since 2023-10-17
     */
    @Transactional
    public int editRegularShippingResignFinalConfirms(List<SaveConfirmReq> dtos) throws Exception {
        int processCount = 0;

        for (SaveConfirmReq dto : dtos) {
            WbncAuthorityResignIzDvo dvo = this.converter.mapSaveConfirmReqToAuthorityResignIzDvo(dto);
            // 정기배송 해지 최종 확정 시 계약해지처리내역 등록
            int result = this.mapper.insertRegularShippingResignsCancel(dvo);
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");

            // 직권 해지된 계약 건에 대하여 계약 상태를 '연체 해약(302)' 으로 변경
            WctbContractDtlStatCdChDvo wctbContractDtlStatCdChDvo = new WctbContractDtlStatCdChDvo();
            wctbContractDtlStatCdChDvo.setCntrNo(dvo.getCntrNo());
            wctbContractDtlStatCdChDvo.setCntrSn(String.valueOf(dvo.getCntrSn()));
            wctbContractDtlStatCdChDvo.setCntrDtlStatCd(WctzCntrDtlStatCd.CLTN_DLQ.getCode());

            try {
                wctbContractDtlStatCdChService.editContractDtlStatCdCh(wctbContractDtlStatCdChDvo);
            } catch (Exception e) {
                throw new BizException("계약 상세 상태 변경 실패");
            }
        }
        return processCount;
    }
}
