package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroDepositMgtDto.SearchContractDetailRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroDepositMgtDto.SearchDepositRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroDepositMgtDto.SearchDepositSettingRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroDepositMgtDto.SearchDtlStateRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroDepositMgtDto.SearchErrosRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroDepositMgtDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbGiroDepositMgtDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbGiroDepositErrorSaveDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbGiroDepositSaveDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbGiroDepositSaveInfoDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwdbGiroDepositMgtMapper {

    /* 지로 입금 조회 */
    PagingResult<SearchRes> selectGiroDepositMgt(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectGiroDepositMgt(SearchReq dto);

    //    /* 지로 입금 등록 - 지로입금원장내역 */
    //    int inertGiroDeposit(WwwdbGiroDepositSaveDvo dvo);

    /* 지로 입금 등록 - 지로입금원장내역 */
    int inertGiroDeposit(WwdbGiroDepositSaveDvo dvo);

    /* 지로 입금 등록 - 지로입금내역 */
    int inertGiroDepositItemization(WwdbGiroDepositSaveInfoDvo dvo);

    /* 지로 입금 등록 - 통합내역 */
    int inertIntegrationItemization(WwdbGiroDepositSaveInfoDvo dvo);

    /* 지로 입금 등록 - 통합내역 히스토리 */
    int inertIntegrationItemizationHistory(WwdbGiroDepositSaveInfoDvo dvo);

    /* 지로 입금 등록 조회 */
    List<WwdbGiroDepositSaveInfoDvo> selectGiroDepositItemizationInfo();

    /* 지로 입금 삭제 - 지로입금원장내역 삭제 */
    int deleteGiroDeposit();

    /* 지로 지로입금내역 조회*/
    List<SearchDepositRes> selectGiroDepositList(SearchReq dto);

    /* 지로 묶음기본 조회*/
    int selectGiroDepositCount(Map<String, Object> dto);

    /* 지로 계약상세 조회*/
    SearchContractDetailRes selectContractDetail(Map<String, Object> dto);

    /* 지로 입금 등록 - 오류건 수정*/
    int updateGiroDepositItemization(Map<String, Object> dto);

    /* 지로 입금 설정 합계 금액 조회*/
    int selectGiroDepositSettingAmount(Map<String, Object> dto);

    /* 지로 입금 설정 금액 및 고객 조회*/
    List<SearchDepositSettingRes> selectGiroDepositSettingList(Map<String, Object> dto);

    /* 지로 입금 에러 조회 */
    PagingResult<SearchErrosRes> selectBillingDocumentErrors(SearchReq dto, PageInfo pageInfo);

    /* 지로 입금 엑셀 다운로드 */
    List<SearchErrosRes> selectBillingDocumentErrors(SearchReq dto);

    /* 지로 입금 에러 저장 */
    int updateBillingDocumentErrors(WwdbGiroDepositErrorSaveDvo dvo) throws Exception;

    SearchDtlStateRes selectDtlState(WwdbGiroDepositSaveInfoDvo dto);
}
