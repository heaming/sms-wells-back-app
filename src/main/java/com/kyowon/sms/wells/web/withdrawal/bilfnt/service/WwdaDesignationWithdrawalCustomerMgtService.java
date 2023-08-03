package com.kyowon.sms.wells.web.withdrawal.bilfnt.service;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.withdrawal.bilfnt.converter.WwdaDesignationWithdrawalCustomerMgtConverter;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaDesignationWithdrawalCustomerMgtDto.*;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dvo.WwdaAutomaticFntOjYnConfDvo;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dvo.WwdaDesignationWithdrawalCustomerMgtDvo;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dvo.WwdaDesignationWithdrawalDeleteDvo;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.mapper.WwdaDesignationWithdrawalCustomerMgtMapper;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 자동이체 지정 출금 고객 관리 서비스
 * </pre>
 *
 * @author donghyun.yoo
 * @since 2023-01-30
 */
@Service
@RequiredArgsConstructor
public class WwdaDesignationWithdrawalCustomerMgtService {

    private final WwdaDesignationWithdrawalCustomerMgtMapper mapper;
    private final WwdaDesignationWithdrawalCustomerMgtConverter converter;

    /** 자동이체 지정 출금 고객 조회
     * @param pageInfo
     *
     * @param SearchAutoFntDsnWdrwCstReq
     * @return PagingResult<SearchAutoFntDsnWdrwCstRes>
     */
    public PagingResult<SearchAutoFntDsnWdrwCstRes> getAftnDsnWdrwCstInqrPages(
        SearchAutoFntDsnWdrwCstReq req,
        PageInfo pageInfo
    ) {

        return mapper.selectAftnDsnWdrwCstInqrPages(req, pageInfo); // 자동이체 지정 출금 고객 조회
    }

    /** 자동이체 지정 출금 고객 엑셀다운로드
     *
     * @param req
     * @return
     */
    public List<SearchAutoFntDsnWdrwCstRes> getAftnDsnWdrwCstInqrExcels(
        SearchAutoFntDsnWdrwCstReq req
    ) {
        return mapper.selectAftnDsnWdrwCstInqrPages(req);
    }

    /** 자동이체 지정 출금 고객 저장
     *
     * @param req
     * @return
     */
    @Transactional
    public int saveAutoFntDsnWdrwCst(
        List<SaveReq> req
    ) {
        int processCount = 0;

        for (SaveReq dto : req) {
            String[] index = {dto.cntr()};
            WwdaDesignationWithdrawalCustomerMgtDvo dvo = converter
                .mapSaveReqToWwdaDesignationWithdrawalCustomerMgtDvo(dto);

            WwdaAutomaticFntOjYnConfDvo afyDvo = mapper.selectAutomaticFntOjYnConf(dvo); // 자동이체 대상 여부 확인

            BizAssert.isFalse(Objects.isNull(afyDvo), "MSG_ALT_CHK_CNTR_NO", index);
            BizAssert.isFalse(
                !afyDvo.getSellTpCd().equals(dvo.getSellTpCd()) || !afyDvo.getCstKnm().equals(dvo.getCstKnm()),
                "MSG_ALT_PD_CST_NM_DSCRPN"
            );

            BizAssert.isFalse(
                !afyDvo.getDpTpCd().equals("0102"),
                "MSG_ALT_NOT_AFTN_OJ_CST", index
            );
            BizAssert.isFalse(!"Y".equals(afyDvo.getFnitAprRsCd()), "MSG_ALT_NOT_AC_FNT_APR", index);

            int igCount = mapper.selectItgWdrwRgstCstCk(dvo); // 통합청구 등록 고객 확인
            BizAssert.isFalse(igCount > 0, "MSG_ALT_ITG_WDRW_RGST_CST", index);

            //            WwdaAutomaticFntOjYnConfDvo bilVo = mapper.selectBilFntAkDtl(dvo);// 청구이체요청상세 조회
            //            if (!Objects.isNull(bilVo)) {
            //                dvo.setBilNo(bilVo.getBilNo());
            //                dvo.setBilDtlSn(bilVo.getBilDtlSn());
            //            }

            switch (dto.rowState()) {
                case CommConst.ROW_STATE_CREATED -> {
                    int checkCount = mapper.selectAcFntDsnWdrwBasByPk(dvo); // 기존 데이터가 삭제된 것인지 조회
                    if (checkCount > 0) {
                        processCount += mapper.updateAcFntDsnWdrwBasByPk(dvo); // 계좌이체지정출금기본 삭제된 데이터 'N'으로 변경 후 처리
                        processCount += mapper.updateAutoFntDsnWdrwRelByPk(dvo); // 계좌이체지정출금관계 삭제된 데이터 'N'으로 변경 후 처리
                    } else {
                        int count = mapper.selectAcFntDsnWdrwBasCt(dvo); // 계좌 이체 지정 출금 기본 건수 조회
                        BizAssert.isFalse(count > 0, "MSG_ALT_LINE_ALREADY_RGST_CST", index);

                        processCount += mapper.insertAutoFntDsnWdrwCstBas(dvo); // 계좌이체지정출금기본 저장
                        if ("1".equals(dto.fntYn())) { // 신규이면서 이체구분이 '이체' 이면
                            CheckBillingFundTransferAsk check = mapper.selectBilFntAkCt(dvo); // 청구이체요청상세 , 청구이체요청기본 데이터 존재 여부
                            if (ObjectUtils.isNotEmpty(check)) {
                                processCount += mapper.insertAutoFntDsnWdrwRel(dvo); // 계좌이체지정출금관계 저장
                            }
                        }
                    }
                    processCount += mapper.insertAutoFntDsnWdrwCstHist(dvo); // 계좌이체지정출금이력 저장
                }
                case CommConst.ROW_STATE_UPDATED -> {
                    processCount += mapper.updateAutoFntDsnWdrwCst(dvo); // 계좌이체지정출금기본 수정
                    processCount += mapper.insertAutoFntDsnWdrwCstHist(dvo); // 계좌이체지정출금이력 추가
                }
                // 계좌이체지정출금관계 삭제
                default -> {
                    if (!"1".equals(dto.fntYn())) {
                        //                                                processCount = mapper.deleteDesignationWithdrawalRel(dvo);
                    }
                }
            }

        }

        return processCount;
    }

    /**
     *
     * @param req
     * @return
     */
    @Transactional
    public int deleteAutoFntDsnWdrwCst(
        List<RemoveReq> req
    ) {
        int processCount = 0;

        for (RemoveReq dto : req) {
            int result = 0;
            WwdaDesignationWithdrawalDeleteDvo dvo = converter
                .mapRemoveReqToWwdaDesignationWithdrawalDeleteDvo(dto);
            WwdaAutomaticFntOjYnConfDvo bilVo = mapper.selectBilFntAkDtl(dvo);// 청구이체요청상세 조회
            if (bilVo != null) {
                dvo.setBilNo(bilVo.getBilNo());
                dvo.setBilDtlSn(bilVo.getBilDtlSn());
            }
            result += mapper.deleteAutoFntDsnWdrwCst(dvo); // 계좌이체지정출금기본 삭제
            result += mapper.deleteAutoFntDsnWdrwRel(dvo); // 계좌이체지정출금관계 삭제
            result += mapper.deleteAutoFntDsnWdrwCstHist(dvo); // 계좌이체지정출금이력 추가
            BizAssert.isTrue(result > 0, "MSG_ALT_SVE_ERR");
            processCount += result;

        }
        return processCount;
    }

}
