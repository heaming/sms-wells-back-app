package com.kyowon.sms.wells.web.contract.salesstatus.service;

import com.kyowon.sms.wells.web.contract.salesstatus.converter.WcteSecProductConverter;
import com.kyowon.sms.wells.web.contract.salesstatus.dvo.WcteConfirmValidationDvo;
import com.kyowon.sms.wells.web.contract.salesstatus.dvo.WcteInvoiceProcessIzDvo;
import com.kyowon.sms.wells.web.contract.salesstatus.dvo.WctePdOstrIzDvo;
import com.kyowon.sms.wells.web.contract.salesstatus.mapper.WcteSecProductMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kyowon.sms.wells.web.contract.salesstatus.dto.WcteSecProductDto.*;

@Service
@RequiredArgsConstructor
public class WcteSecProductService {

    private final WcteSecProductMapper mapper;
    private final WcteSecProductConverter converter;

    public PagingResult<SearchReservationRes> getReservationPages(SearchReservationReq dto, PageInfo pageInfo) {
        return mapper.selectReservationPages(dto, pageInfo);
    }

    public List<SearchReservationRes> getReservationsForExcelDownload(SearchReservationReq dto) {
        return mapper.selectReservationPages(dto);
    }

    public PagingResult<SearchConfirmRes> getConfirmPages(SearchConfirmReq dto, PageInfo pageInfo) {
        return mapper.selectConfirms(dto, pageInfo);
    }

    /**
     * 삼성전자 확정일 목록 조회
     * @param dto SearchConfirmReq
     * @return 확정일 리스트
     */
    public List<SearchConfirmRes> getConfirms(SearchConfirmReq dto) {
        return mapper.selectConfirms(dto);
    }

    /**
     * 확정일 단건 생성
     * @param dto 확정일 생성 요청 객체
     * @return 업데이트 건수
     */
    public int createConfirm(CreateConfirmReq dto) {
        WcteConfirmValidationDvo validationObject = mapper.selectConfirmValidation(dto.cntrNo(), dto.cntrSn()).orElseThrow(
            () -> new BizException("해당 계약 번호를 가진 계약은 존재하지 않습니다.")
        );

        if (!StringUtils.isEmpty(validationObject.getCanDt())) {
            throw new BizException("취소된 고객입니다!");
        }
        if ("Y".equals(validationObject.getCntrCnfmYn())) {
            throw new BizException("매출확정된 고객입니다!");
        }
        if (!StringUtils.isEmpty(validationObject.getIstDt())) {
            throw new BizException("이미 설치가 되었습니다!");
        }

        /* TODO: 확인 필요, MSG */
        if (!"Y".equals(validationObject.getSecPdYn())) {
            throw new BizException("삼성제품이 아닙니다!");
        }

        if ("Y".equals(validationObject.getOstrRgstYn())) {
            throw new BizException("제품 출고 요청이 이미 되었습니다!");
        }

        /* 계약 상세에 배송예정일자 업데이트 */
        int processCount = mapper.updateShippingDueDate(dto.cntrNo(), dto.cntrSn(), dto.sppFshDt());
        BizAssert.isTrue(processCount == 1, "MSG_ALT_SVE_ERR" ) ;

        /* 계약 상세 변경 이력 생성 */
        processCount = mapper.insertContractDetailChangeHistory(dto.cntrNo(), dto.cntrSn());
        BizAssert.isTrue(processCount == 1, "MSG_ALT_SVE_ERR" );

        /* 매출 조정 자료 생성: 제품고유번호출고내역 생성 */
        WctePdOstrIzDvo productOutOfStorageIz = converter.mapCreateConfirmReqToWcteProdu1ctOutOfStorageIz(dto);
        productOutOfStorageIz.setSellTpCd(validationObject.getSellTpCd());
        processCount = mapper.insertProductOutOfStorageIz(productOutOfStorageIz);
        BizAssert.isTrue(processCount == 1, "MSG_ALT_SVE_ERR" ) ;

        /* 삼성API 테이블 기록: 배송업체송장처리내역 생성 */
        WcteInvoiceProcessIzDvo invoiceProcessIz = converter.mapCreateConfirmReqToWcteInvoiceProcessIz(dto);
        processCount = mapper.insertInvoiceProcessIz(invoiceProcessIz);
        BizAssert.isTrue(processCount == 1, "MSG_ALT_SVE_ERR" ) ;

        /*배송업체송장처리이력 생성*/
        processCount = mapper.insertInvoiceProcessHistory(mapper.selectInvoiceProcessIzPk(invoiceProcessIz));
        BizAssert.isTrue(processCount == 1, "MSG_ALT_SVE_ERR" );

        return processCount;
    }

    public int createConfirms(List<CreateConfirmReq> list) {
        int processCount = 0;
        for (CreateConfirmReq req : list) {
            processCount += createConfirm(req);
        }
        return processCount;
    }

    /**
     * 삼성전자 미설치 페이지 조회 서비스
     *
     * @param dto 조회조건
     * @param pageInfo 페이지 정보
     * @return 삼성전자  미설치 정보 list
     */
    public PagingResult<SearchNotInstalledRes> getNotInstalledPages(SearchNotInstalledReq dto, PageInfo pageInfo) {
        return mapper.selectNotInstalledIzs(dto, pageInfo);
    }
}
