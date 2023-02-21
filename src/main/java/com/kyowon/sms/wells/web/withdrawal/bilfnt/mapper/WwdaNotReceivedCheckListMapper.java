package com.kyowon.sms.wells.web.withdrawal.bilfnt.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaNotReceivedCheckListDto.SearchAftnBilNrcvListReq;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaNotReceivedCheckListDto.SearchAftnBilNrcvListRes;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaNotReceivedCheckListDto.SearchAftnDpNcrtCheckListReq;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaNotReceivedCheckListDto.SearchAftnDpNcrtCheckListRes;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaNotReceivedCheckListDto.SearchAftnSlPerfCheckInqrRes;
import com.kyowon.sms.wells.web.withdrawal.bilfnt.dto.WwdaNotReceivedCheckListDto.SearchResultBundleErrorRes;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwdaNotReceivedCheckListMapper {

    /** 자동이체 미수신 체크 목록
     * @param pageInfo 
     * 
     * @param SearchAftnBilNrcvListReq
     * @return PagingResult<SearchAftnBilNrcvListRes>
     */
    PagingResult<SearchAftnBilNrcvListRes> selectAftnBilNrcvListPages(SearchAftnBilNrcvListReq req, PageInfo pageInfo);

    /** 자동이체 입금 미생성 체크 목록
     * 
     * @param req
     * @param pageInfo 
     * @return
     */
    PagingResult<SearchAftnDpNcrtCheckListRes> selectAftnDpNcrtCheckListPages(
        SearchAftnDpNcrtCheckListReq req, PageInfo pageInfo
    );

    /** 자동이체 매출실적 체크 목록 조회
     * 
     * @param bilYm
     * @param pageInfo
     * @return
     */
    PagingResult<SearchAftnSlPerfCheckInqrRes> selectAftnSlPerfCheckInqrPages(
        String bilYm, PageInfo pageInfo
    );

    /** 자동이체 결과 묶음 오류 조회
     * 
     * @param mpyMthdTpCd
     * @param pageInfo
     * @return
     */
    PagingResult<SearchResultBundleErrorRes> selectAftnRsBndlErrInqrPages(String mpyMthdTpCd, PageInfo pageInfo);

}
