package com.kyowon.sms.wells.web.withdrawal.bilfnt.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.withdrawal.bilfnt.converter.WwdaDesignationWithdrawalCustomerMgtConverter;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaDesignationWithdrawalCustomerMgtDto.RemoveReq;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaDesignationWithdrawalCustomerMgtDto.SaveReq;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaDesignationWithdrawalCustomerMgtDto.SearchAutoFntDsnWdrwCstReq;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaDesignationWithdrawalCustomerMgtDto.SearchAutoFntDsnWdrwCstRes;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dvo.WwdaAutomaticFntOjYnConfDvo;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dvo.WwdaDesignationWithdrawalCustomerMgtDvo;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.mapper.WwdaDesignationWithdrawalCustomerMgtMapper;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;
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
            int index = Integer.parseInt(dto.dataRow()) + 1;
            WwdaDesignationWithdrawalCustomerMgtDvo dvo = converter
                .mapSaveReqToWwdaDesignationWithdrawalCustomerMgtDvo(dto);
            // SearchContractDetailInfRes res = mapper.selectContractDetailInf(dvo); // 필요한지 모르곘음
            WwdaAutomaticFntOjYnConfDvo afyDvo = mapper.selectAutomaticFntOjYnConf(dvo); // 자동이체 대상 여부 확인
            if (afyDvo == null) {
                throw new BizException("계약번호를 확인해 주시기 바랍니다.");
            }
            if (!afyDvo.getMpyMthdTpCd().equals("110") && !afyDvo.getMpyMthdTpCd().equals("120")) {
                throw new BizException(index + "번째 라인은 자동이체　대상고객이　아닙니다！");
            }
            if (!afyDvo.getFnitAprRsCd().equals("Y")) {
                throw new BizException(index + " 번째 라인은  자동이체　계좌승인　고객이　아닙니다！");
            }
            int igCount = mapper.selectItgWdrwRgstCstCk(dvo); // 통합청구 등록 고객 확인
            if (igCount > 0) {
                throw new BizException(index + " 번째 라인은  통합출금　등록　고객입니다！");
            }
            WwdaAutomaticFntOjYnConfDvo bilVo = mapper.selectBilFntAkDtl(dvo);// 청구이체요청상세 조회
            if (bilVo != null) {
                dvo.setBilNo(bilVo.getBilNo());
                dvo.setBilDtlSn(bilVo.getBilDtlSn());
            }
            switch (dto.rowState()) {
                case CommConst.ROW_STATE_CREATED -> {
                    int checkCount = mapper.selectAcFntDsnWdrwBasByPk(dvo); // 기존 데이터가 삭제된 것인지 조회
                    if (checkCount > 0) {
                        processCount += mapper.updateAcFntDsnWdrwBasByPk(dvo); // 계좌이체지정출금기본 삭제된 데이터 'N'으로 변경 후 처리
                        processCount += mapper.updateAutoFntDsnWdrwRelByPk(dvo); // 계좌이체지정출금관계 삭제된 데이터 'N'으로 변경 후 처리
                    } else {
                        int count = mapper.selectAcFntDsnWdrwBasCt(dvo); // 계좌 이체 지정 출금 기본 건수 조회
                        if (count > 0) {
                            throw new BizException(index + "번째 라인은 이미　등록된　고객입니다！");
                        }
                        processCount += mapper.insertAutoFntDsnWdrwCstBas(dvo); // 계좌이체지정출금기본 저장
                        if (dto.fntYn().equals("Y")) { // 신규이면서 이체구분이 'Y' 이면
                            int bilCount = mapper.selectBilFntAkCt(dvo); // 청구이체요청상세 , 청구이체요청기본 데이터 존재 여부
                            if (bilCount > 0) {
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
            WwdaDesignationWithdrawalCustomerMgtDvo dvo = converter
                .mapRemoveReqToWwdaDesignationWithdrawalCustomerMgtDvo(dto);
            WwdaAutomaticFntOjYnConfDvo bilVo = mapper.selectBilFntAkDtl(dvo);// 청구이체요청상세 조회
            if (bilVo != null) {
                dvo.setBilNo(bilVo.getBilNo());
                dvo.setBilDtlSn(bilVo.getBilDtlSn());
                dvo.setDtaDlYn("Y");
            }
            result += mapper.deleteAutoFntDsnWdrwCst(dvo); // 계좌이체지정출금기본 삭제
            result += mapper.deleteAutoFntDsnWdrwRel(dvo); // 계좌이체지정출금관계 삭제 
            result += mapper.insertAutoFntDsnWdrwCstHist(dvo); // 계좌이체지정출금이력 추가
            BizAssert.isTrue(result == 3, "MSG_ALT_SVE_ERR");
            processCount += result;

        }
        return processCount;
    }

}
