package com.kyowon.sms.wells.web.deduction.redf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaAwRedfEtAmtDto.SearchRedfEtRes;
import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaAwRedfEtAmtDto.SearchRedfIzPerfPsRes;
import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaAwRedfEtAmtDto.SearchRedfIzReq;
import com.kyowon.sms.wells.web.deduction.redf.dto.WdeaAwRedfEtAmtDto.SearchRedfIzRes;
import com.kyowon.sms.wells.web.deduction.redf.dvo.WdeaAwRedfEtAmtDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WdeaAwRedfEtAmtMapper {

    /*  수당되물림 예상액 - 판매리스트 내역 목록 조회 */
    PagingResult<SearchRedfIzRes> selectAwRedfIzAmts(SearchRedfIzReq dto, PageInfo pageInfo);

    /* 수당되물림 예상액 - 판매리스트 내역 목록 엑셀다운로드 */
    List<SearchRedfIzRes> selectAwRedfIzAmts(SearchRedfIzReq dto);

    /* 수당되물림 예상액 - 실적현황 목록 엑셀다운로드*/
    List<SearchRedfIzPerfPsRes> selectAwRedfIzPerfPsAmt(SearchRedfIzReq dto);

    /* 되물림예상금액내역 삭제 */
    int deleteRedfEtAmtIz();

    /* 되물림예상계약기본 삭제 */
    int deleteRedfEtCntrBas();

    /* 되물림예상금액내역(temp) 등록 */
    int insertRedfEtAmtIz(WdeaAwRedfEtAmtDvo dvo);

    /* 되물림예상계약기본(temp) 등록 */
    int insertRedfEtCntrBas(WdeaAwRedfEtAmtDvo dvo);

    /*수당되물림 예상액 - 되물림 예상액 목록 조회*/
    List<SearchRedfEtRes> selectAwRedfEtAmts();
}
