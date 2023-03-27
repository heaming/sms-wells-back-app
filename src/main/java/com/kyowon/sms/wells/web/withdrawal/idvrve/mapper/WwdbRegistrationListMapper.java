package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRegistrationListDto.SearchDetailReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRegistrationListDto.SearchDetailRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRegistrationListDto.SearchElectronicReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRegistrationListDto.SearchElectronicRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRegistrationListDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRegistrationListDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbRegistrationListDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbRegistrationSubListDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwdbRegistrationListMapper {

    PagingResult<SearchRes> selectRegistrationPages(SearchReq dto, PageInfo pageInfo);

    List<SearchRes> selectRegistrationPages(SearchReq dto);

    PagingResult<SearchDetailRes> selectRegistrationElectronics(SearchDetailReq dto, PageInfo pageInfo);

    List<SearchDetailRes> selectRegistrationElectronics(SearchDetailReq dto);

    int insertRegistrationMainElectronics(WwdbRegistrationListDvo dvo);

    int updateRegistrationMainElectronics(WwdbRegistrationListDvo dvo);

    int insertRegistrationSubElectronics(WwdbRegistrationSubListDvo dvo);

    int updateRegistrationSubElectronics(WwdbRegistrationSubListDvo dvo);

    int deleteRegistrationSubElectronics(WwdbRegistrationListDvo dvo);

    String selectRegistrationPk();

    PagingResult<SearchElectronicRes> selectRegistrationElectronicDetails(SearchElectronicReq dto, PageInfo pageInfo);

    List<SearchElectronicRes> selectRegistrationElectronicDetails(SearchElectronicReq dto);

}
