package com.kyowon.sms.wells.web.withdrawal.bilfnt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaNotReceivedCheckListDto.SearchAftnBilNrcvListReq;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaNotReceivedCheckListDto.SearchAftnBilNrcvListRes;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaNotReceivedCheckListDto.SearchAftnDpNcrtCheckListReq;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaNotReceivedCheckListDto.SearchAftnDpNcrtCheckListRes;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaNotReceivedCheckListDto.SearchAftnSlPerfCheckInqrRes;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaNotReceivedCheckListDto.SearchResultBundleErrorRes;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.mapper.WwdaNotReceivedCheckListMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 자동이체 미수신 체크 목록 관리 서비스
 * </pre>
 *
 * @author donghyun.yoo
 * @since 2023-02-07
 */
@Service
@RequiredArgsConstructor
public class WwdaNotReceivedCheckListService {

    private final WwdaNotReceivedCheckListMapper mapper;

    /**
     * 자동이체 미수신 체크 목록 / 페이징
     * @param req
     * @param pageInfo 페이징
     * @return PagingResult<SearchAftnBilNrcvListRes>
     */
    public PagingResult<SearchAftnBilNrcvListRes> getAftnBilNrcvListPages(
        SearchAftnBilNrcvListReq req, PageInfo pageInfo
    ) {
        return mapper.selectAftnBilNrcvListPages(req, pageInfo);

    }

    /**
     * 자동이체 미수신 체크 엑셀다운로드
     * @param req
     * @return List<SearchAftnBilNrcvListRes>
     */
    public List<SearchAftnBilNrcvListRes> getAftnBilNrcvListExcels(SearchAftnBilNrcvListReq req) {
        return mapper.selectAftnBilNrcvListPages(req);
    }

    /**
     * 자동이체 입금 미생성 체크 목록 / 페이징
     * @param req
     * @param pageInfo 페이징
     * @return PagingResult<SearchAftnDpNcrtCheckListRes>
     */
    public PagingResult<SearchAftnDpNcrtCheckListRes> getAftnDpNcrtCheckListPages(
        SearchAftnDpNcrtCheckListReq req,
        PageInfo pageInfo
    ) {
        return mapper.selectAftnDpNcrtCheckListPages(req, pageInfo);
    }

    /**
     * 자동이체 입금 미생성 체크 엑셀다운로드
     * @param req
     * @return List<SearchAftnDpNcrtCheckListRes>
     */
    public List<SearchAftnDpNcrtCheckListRes> getAftnDpNcrtCheckListExcels(
        SearchAftnDpNcrtCheckListReq req
    ) {
        return mapper.selectAftnDpNcrtCheckListPages(req);
    }

    /**
     * 자동이체 매출실적 체크 목록 조회 / 페이징
     * @param bilYm 기준년월
     * @param pageInfo 페이징
     * @return PagingResult<SearchAftnSlPerfCheckInqrRes>
     */
    public PagingResult<SearchAftnSlPerfCheckInqrRes> getAftnSlPerfCheckInqrPages(
        String bilYm,
        PageInfo pageInfo
    ) {
        return mapper.selectAftnSlPerfCheckInqrPages(bilYm, pageInfo);
    }

    /**
     * 자동이체 매출실적 체크 목록 엑셀다운로드
     * @param bilYm 기준년월
     * @return List<SearchAftnSlPerfCheckInqrRes>
     */
    public List<SearchAftnSlPerfCheckInqrRes> getAftnSlPerfCheckInqrExcels(String bilYm) {
        return mapper.selectAftnSlPerfCheckInqrPages(bilYm);
    }

    /**
     * 자동이체 결과 묶음 오류 조회 / 페이징
     * @param dpTpCd 자동이체 체크
     * @param pageInfo 페이징
     * @return PagingResult<SearchResultBundleErrorRes>
     */
    public PagingResult<SearchResultBundleErrorRes> getAftnRsBndlErrInqrPages(
        String dpTpCd,
        PageInfo pageInfo
    ) {
        return mapper.selectAftnRsBndlErrInqrPages(dpTpCd, pageInfo);
    }

    /**
     * 자동이체 결과 묶음 오류 엑셀다운로드
     * @param dpTpCd 자동이체 체크
     * @return List<SearchResultBundleErrorRes>
     */
    public List<SearchResultBundleErrorRes> getAftnRsBndlErrInqrExcels(String dpTpCd) {
        return mapper.selectAftnRsBndlErrInqrPages(dpTpCd);
    }

}
