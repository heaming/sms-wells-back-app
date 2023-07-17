package com.kyowon.sms.wells.web.service.interfaces.service;

import com.kyowon.sms.wells.web.service.interfaces.converter.WsncWellsAsInterfaceConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncAsInterfaceDto.*;
import com.kyowon.sms.wells.web.service.interfaces.mapper.WsncWellsAsInterfaceMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 *
 * <pre>
 * W-SV-I-0021 Wells 인터페이스
 * </pre>
 *
 * @author gs.piit122 김동엽
 * @since 2022-12-28
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsncWellsAsInterfaceService {

    private final WsncWellsAsInterfaceMapper mapper;
    private final WsncWellsAsInterfaceConverter converter;

    /**
     * 고객서비스수행내역, 계약주소지기본 테이블을 기준으로 고객정보 조회
     *
     * @programId : W-SV-I-0021
     * @param req : 조회파라메터
     * @return 조회결과
     */
    public List<SearchCustInfoRes> getCustomerInformations(
        SearchCustInfoReq req
    ) {
        return converter.mapAllCustInfoDvoToRes(mapper.selectCustomerInformations(req));
    }

    /**
    * 고객서비스AS설치대상내역, 고객서비스수행배정내역, 고객서비스수행내역, 계약주소지기본 테이블을 기준으로 고객정보 조회
    *
    * @programId : W-SV-I-0022
    * @param req : 조회파라메터
    * @return 조회결과
    */
    public List<SearchRecInfoRes> getReceiptInformations(
        SearchRecInfoReq req
    ) {
        return converter.mapAllRecInfoDvoToRes(mapper.selectReceiptInformations(req));
    }

    /**
    * Wells 인터페이스 맞춤가이드 사용중인 제품 조회
    *
    * @programId : W-SV-I-0004
    * @param req : 조회파라메터
    * @return 조회결과
    */
    public List<SearchUsingProductsRes> getUsingProducts(
        SearchUsingProductsReq req
    ) {
        return converter.mapAllUsingProductDvoToRes(mapper.selectUsingProducts(req));
    }

    /**
    * Wells 인터페이스 맞춤가이드 서비스 이력 조회
    *
    * @programId : W-SV-I-0001
    * @param req : 조회파라메터
    * @return 조회결과
    */
    public PagingResult<SearchServiceHistoryRes> getServiceHistoryPages(
        SearchServiceHistoryReq req
    ) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageIndex(req.pageIndex());
        pageInfo.setPageSize(req.pageSize());
        return new PagingResult<>(
            converter.mapAllServiceHistoryDvoToRes(mapper.selectServiceHistorys(req, pageInfo)), pageInfo
        );
    }

    /**
    * Wells 인터페이스 맞춤가이드 서비스 내용 조회
    *
    * @programId : W-SV-I-0003
    * @param req : 조회파라메터
    * @return 조회결과
    */
    public List<SearchServiceContentsRes> getServiceContents(
        SearchServiceContentsReq req
    ) {
        return converter.mapAllServiceContentsDvoToRes(mapper.selectServiceContents(req));
    }

    /**
     * 고객센터 인터페이스 특이사항 조회
     *
     * @programId : W-SV-I-0035
     * @param req : 조회파라메터
     * @return 조회결과
     */
    public SearchCustomerInformationRes getCustomerInformation(SearchCustomerInformationReq req) {
        return converter.mapCustomerInformationDvoToRes(mapper.selectCustomerInformation(req));
    }

    /**
     * 인터페이스 맞춤가이드 관리자페이지 AS접수 모종변경 조회
     *
     * @programId : W-SV-I-0023 Wells
     * @param req : String rcpdtStrt, 접수일자시작
     *              String rcpdtEnd, 접수일자종료
     *              String pdCd, 상품코드
     *              String cntrNo, 계약번호
     *              String bcNo 바코드번호
     *
     * @return res: String tpCd, 유형코드
     *              String rcpdt, 접수일자
     *              String cntrNo, 계약번호
     *              String pdCd, 상품코드
     *              String pdNm, 상품명
     *              String bcNo, 바코드번호
     *              String wkTp, 작업유형
     *              String asRson, // A)
     *              String vstRqdt, 방문요청일자
     *              String wkExcnDt, 작업수행일자
     *              String wkPrgsStat, 작업진행상태
     *              String pextSding, 기존모종
     *              String chSding, 변경모종
     *              String apyDt 적용일자
     */
    public SearchAsSidingChangeRes getAsSidingChanges(SearchAsSidingChangeReq req) {
        return converter.mapAsSidingChangeDvoToRes(mapper.selectAsSidingChanges(req));
    }

}
