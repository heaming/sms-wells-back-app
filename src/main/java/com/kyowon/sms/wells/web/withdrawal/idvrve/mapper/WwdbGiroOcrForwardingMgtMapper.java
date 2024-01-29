package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroOcrForwardingMgtDto;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroOcrForwardingMgtDto.*;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbGiroOcrForwardingMgtDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbGiroOcrForwardingPrintDeleteDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbGiroOcrForwardingPrintDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbGiroOcrForwardingPrintSeqDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwdbGiroOcrForwardingMgtMapper {

    /**
     * 지로OCR발송관리 목록 조회 / 페이징
     * @param dto
     * @param pageInfo
     * @return
     */
    PagingResult<SearchRes> selectGiroOcrForwardings(SearchReq dto, PageInfo pageInfo);

    /**
     * 지로OCR발송관리 목록 엑셀 다운로드
     * @param dto
     * @return
     */
    List<SearchRes> selectGiroOcrForwardings(SearchReq dto);

    /**
     * 지로OCR발송관리 대상 조회
     * @param cntr
     * @return
     */
    List<SearchObjectRes> selectGiroOcrForwardingObjects(WwdbGiroOcrForwardingMgtDto.SearchCntrReq cntr);

    /**
     * 지로OCR발송관리 대상 계약정보 조회
     * @param cntrNo
     * @param cntrSn
     * @return
     */
    SearchGiroCntractRes selectGiroOcrForwardingObjectContractInfo(
        String cntrNo, String cntrSn
    );

    /**
     * 지로OCR발송관리 등록
     * @param dvo
     * @return
     */
    int insertGiroOcrForwardings(WwdbGiroOcrForwardingMgtDvo dvo);

    /**
     * 지로OCR발송관리 수정
     * @param dvo
     * @return
     */
    int updateGiroOcrForwardings(WwdbGiroOcrForwardingMgtDvo dvo);

    /**
     * 지로OCR발송관리 삭제
     * @param dvo
     * @return
     */
    int deleteGiroOcrForwardings(WwdbGiroOcrForwardingMgtDvo dvo);

    /**
     * 지로OCR발송관리 출력 조회 / 페이징
     * @param dto
     * @param pageInfo
     * @return
     */
    PagingResult<SearchPrintRes> selectGiroOcrForwardingPrints(SearchPrintReq dto, PageInfo pageInfo);

    /**
     * 지로OCR발송관리 출력 엑셀 다운로드
     * @param dto
     * @return
     */
    List<SearchPrintRes> selectGiroOcrForwardingPrints(SearchPrintReq dto);

    /**
     * 지로OCR발송관리 출력 상세 등록
     * @param dvo
     * @return
     */
    int insertGiroOcrForwardingDetailPrints(WwdbGiroOcrForwardingPrintDvo dvo);

    /**
     * 지로OCR발송관리 출력 헤더 등록
     * @param dvo
     * @return
     */
    int insertGiroOcrForwardingPrints(WwdbGiroOcrForwardingPrintDvo dvo);

    /**
     * 지로OCR발송관리 출력 생성 날짜 및 시퀀스 조회
     * @param dto
     * @return
     */
    WwdbGiroOcrForwardingPrintSeqDvo selectGiroOcrForwardingPrintInfo(SavePrintReq dto);

    /**
     * 지로OCR발송관리 출력 삭제
     * @param dvo
     * @return
     * @throws Exception
     */
    int deleteGiroOcrForwardingPrints(WwdbGiroOcrForwardingPrintDeleteDvo dvo) throws Exception;

    /**
     * 지로OCR출력관리 출력일자 생성
     * @param dvo
     * @return
     * @throws Exception
     */
    int updateGiroPrintDate(WwdbGiroOcrForwardingPrintDvo dvo) throws Exception;

    /**
     * 지로OCR출력관리 출력내역 생성
     * @param dvo
     * @return
     * @throws Exception
     */
    int insertGiroPrintDate(WwdbGiroOcrForwardingPrintDvo dvo) throws Exception;

    /**
     * 지로OCR출력관리 출력내역 생성
     * @param dvo
     * @return
     * @throws Exception
     */
    List<WwdbGiroOcrForwardingMgtDto.SearchPrintCreateRes> selectGiroPrintCreate(WwdbGiroOcrForwardingPrintDvo dvo);

}
