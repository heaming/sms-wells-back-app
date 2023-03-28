package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import java.util.List;

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

    String selectRegistrationPk();

    PagingResult<SearchElectronicRes> selectRegistrationElectronicDetails(SearchElectronicReq dto, PageInfo pageInfo);

    List<SearchElectronicRes> selectRegistrationElectronicDetails(SearchElectronicReq dto);

}
