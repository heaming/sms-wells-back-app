package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import java.util.List;

import com.kyowon.sms.common.web.withdrawal.zcommon.dvo.ZwdzWithdrawalReceiveAskDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillDepositMgtDto;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbBillDepositContractDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbBillDepositSlipProcessingDvo;
import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillDepositMgtDto.SearchDetailReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillDepositMgtDto.SearchDetailRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillDepositMgtDto.SearchElectronicReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillDepositMgtDto.SearchElectronicRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillDepositMgtDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbBillDepositMgtDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbBillDepositMgtDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbBillDepositMgtSubDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwdbBillDepositMgtMapper {

    PagingResult<SearchRes> selectRegistrationPages(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectRegistrationPages(SearchReq dto);

    PagingResult<SearchDetailRes> selectRegistrationElectronics(SearchDetailReq dto, PageInfo pageInfo);

    List<SearchDetailRes> selectRegistrationElectronics(SearchDetailReq dto);

    int insertRegistrationMainElectronics(WwdbBillDepositMgtDvo dvo);

    int updateRegistrationMainElectronics(WwdbBillDepositMgtDvo dvo);

    int insertRegistrationSubElectronics(WwdbBillDepositMgtSubDvo dvo);

    int updateRegistrationSubElectronics(WwdbBillDepositMgtSubDvo dvo);

    int deleteRegistrationSubElectronics(WwdbBillDepositMgtDvo dvo);

    WwdbBillDepositMgtDto.SearchItgNoRes selectRegistrationPk();

    PagingResult<SearchElectronicRes> selectRegistrationElectronicDetails(SearchElectronicReq dto, PageInfo pageInfo);

    List<SearchElectronicRes> selectRegistrationElectronicDetails(SearchElectronicReq dto);

    //어음입금 계약 조회
    int insertBillDepositContracts(WwdbBillDepositContractDvo dvo) throws Exception;

    List<WwdbBillDepositSlipProcessingDvo> selectSlipProcessings(WwdbBillDepositMgtDto.SearchSlipReq dto);

    int insertSlipProcessings(WwdbBillDepositSlipProcessingDvo dvo);

    List<WwdbBillDepositSlipProcessingDvo> selectReplacementSlipProcessing(WwdbBillDepositMgtDto.SearchSlipReq dto);

    //전표 등록 업데이트
    int updateSlipRegistration(WwdbBillDepositMgtDvo dvo) throws Exception;

    // 어음 신규등록 팝업 - 전자어음 엑셀 업로드 계약 상세번호 존재 체크
//    int selectValidationCntr(String cntr);
}
