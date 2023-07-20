package com.kyowon.sms.wells.web.service.interfaces.mapper;

import java.util.List;

import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniAsSidingChangeInterfaceDvo;
import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncAsInterfaceDto.*;
import com.kyowon.sms.wells.web.service.allocate.dvo.*;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniCustomerInformationDvo;
import com.sds.sflex.system.config.datasource.PageInfo;

/**
 *
 * <pre>
 * W-SV-I-0021 Wells 인터페이스
 * </pre>
 *
 * @author gs.piit122 김동엽
 * @since 2022-12-28
 */
@Mapper
public interface WsncWellsAsInterfaceMapper {

    /**
     * 고객서비스수행내역, 계약주소지기본 테이블을 기준으로 고객정보 조회
     *
     * @programId : W-SV-I-0021
     * @param req : 조회파라메터
     * @return 조회결과
     */
    List<WsncAsInterfaceCustInfoDvo> selectCustomerInformations(SearchCustInfoReq req);

    /**
    * 고객서비스AS설치대상내역, 고객서비스수행배정내역, 고객서비스수행내역, 계약주소지기본 테이블을 기준으로 고객정보 조회
    *
    * @param req : 조회파라메터
    * @return 조회결과
    */
    List<WsncAsInterfaceRecInfoDvo> selectReceiptInformations(SearchRecInfoReq req);

    /**
    * Wells 인터페이스 맞춤가이드 사용중인 제품 조회
    *
    * @programId : W-SV-I-0004
    * @param req : 조회파라메터
    * @return 조회결과
    */
    List<WsncAsInterfaceUsingPdutDvo> selectUsingProducts(SearchUsingProductsReq req);

    /**
     * Wells 인터페이스 맞춤가이드 서비스 이력 조회
     *
     * @programId : W-SV-I-0001
     * @param req : 조회파라메터
     * @return 조회결과
     */
    List<WsncAsInterfaceServHistDvo> selectServiceHistorys(SearchServiceHistoryReq req, PageInfo pageInfo);

    /**
     * Wells 인터페이스 맞춤가이드 서비스 내용 조회
     *
     * @programId : W-SV-I-0003
     * @param req : 조회파라메터
     * @return 조회결과
     */
    List<WsncAsInterfaceServContDvo> selectServiceContents(SearchServiceContentsReq req);

    /**
     * 고객센터 인터페이스 특이사항 조회
     *
     * @programId : W-SV-I-0035
     * @param req : 조회파라메터
     * @return 조회결과
     */
    WsniCustomerInformationDvo selectCustomerInformation(SearchCustomerInformationReq req);

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
    WsniAsSidingChangeInterfaceDvo selectAsSidingChanges(SearchAsSidingChangeReq req);

}
