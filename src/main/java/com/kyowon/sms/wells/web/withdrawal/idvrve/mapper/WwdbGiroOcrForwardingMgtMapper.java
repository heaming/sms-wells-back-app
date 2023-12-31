package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import java.util.List;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroOcrForwardingMgtDto;
import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroOcrForwardingMgtDto.SavePrintReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroOcrForwardingMgtDto.SearchObjectRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroOcrForwardingMgtDto.SearchPrintReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroOcrForwardingMgtDto.SearchPrintRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroOcrForwardingMgtDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroOcrForwardingMgtDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbGiroOcrForwardingMgtDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbGiroOcrForwardingPrintDeleteDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbGiroOcrForwardingPrintDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbGiroOcrForwardingPrintSeqDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwdbGiroOcrForwardingMgtMapper {

    /* 지로OCR발송관리 목록 조회 */
    public PagingResult<SearchRes> selectGiroOcrForwardings(SearchReq dto, PageInfo pageInfo);

    public List<SearchRes> selectGiroOcrForwardings(SearchReq dto);

    /* 지로OCR발송관리 대상 조회 */
    public List<SearchObjectRes> selectGiroOcrForwardingObjects(WwdbGiroOcrForwardingMgtDto.SearchCntrReq cntr);

    /* 지로OCR발송관리 등록 */
    public int insertGiroOcrForwardings(WwdbGiroOcrForwardingMgtDvo dvo);

    /* 지로OCR발송관리 수정 */
    public int updateGiroOcrForwardings(WwdbGiroOcrForwardingMgtDvo dvo);

    /* 지로OCR발송관리 삭제 */
    public int deleteGiroOcrForwardings(WwdbGiroOcrForwardingMgtDvo dvo);

    /* 지로OCR발송관리 출력 조회 */
    PagingResult<SearchPrintRes> selectGiroOcrForwardingPrints(SearchPrintReq dto, PageInfo pageInfo);

    List<SearchPrintRes> selectGiroOcrForwardingPrints(SearchPrintReq dto);

    /* 지로OCR발송관리 출력 상세 등록 */
    public int insertGiroOcrForwardingDetailPrints(WwdbGiroOcrForwardingPrintDvo dvo);

    /* 지로OCR발송관리 출력 헤더 등록 */
    public int insertGiroOcrForwardingPrints(WwdbGiroOcrForwardingPrintDvo dvo);

    /* 지로OCR발송관리 Pk 조회
    public int selectGiroOcrPk();
     */

    /* 지로OCR발송관리 출력 생성 날짜 및 시퀀스 조회 */
    public WwdbGiroOcrForwardingPrintSeqDvo selectGiroOcrForwardingPrintInfo(SavePrintReq dto);

    /* 지로OCR발송관리 출력 삭제 */
    public int deleteGiroOcrForwardingPrints(WwdbGiroOcrForwardingPrintDeleteDvo dvo) throws Exception;

    /* 지로OCR출력관리 출력일자 생성 */
    public int updateGiroPrintDate(WwdbGiroOcrForwardingPrintDvo dvo) throws Exception;

    /* 지로OCR출력관리 출력내역 생성 */
    public int insertGiroPrintDate(WwdbGiroOcrForwardingPrintDvo dvo) throws Exception;

    public List<WwdbGiroOcrForwardingMgtDto.SearchPrintCreateRes> selectGiroPrintCreate(WwdbGiroOcrForwardingPrintDvo dvo);

}
