package com.kyowon.sms.wells.web.bond.consultation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindBaseYmRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindCounselHistoryReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindCounselHistoryRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindCounselRegistrationReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindCounselRegistrationRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindCustomerDetailReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindCustomerDetailRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindUnusualArticlesReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.FindUnusualArticlesRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.SearchReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.SearchRes;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.SearchUserInfoReq;
import com.kyowon.sms.wells.web.bond.consultation.dto.WbncCustomerDto.SearchUserInfoRes;
import com.kyowon.sms.wells.web.bond.consultation.dvo.WbncCustomerDvo;
import com.kyowon.sms.wells.web.bond.consultation.dvo.WbncPetitionCreateDvo;

/**
 * <pre>
 * 채권상담 고객리스트 맵퍼 
 * wbnc-customer.xml
 * </pre>
 *
 * @author sunghun.choi
 * @since 2023-02-10
 */
@Mapper
public interface WbncCustomerMapper {
    FindBaseYmRes selectBaseYm();

    List<SearchRes> selectCustomers(SearchReq dto);

    FindRes selectCustomerDetail(FindReq dto);

    List<FindCustomerDetailRes> selectCustomerDetails(FindCustomerDetailReq dto);

    FindUnusualArticlesRes selectUnusualArticles(FindUnusualArticlesReq dto);

    List<FindCounselHistoryRes> selectCounselHistorys(FindCounselHistoryReq dto);

    int mergeUnuitmCn(WbncCustomerDvo dvo);

    int insertCounsel(WbncCustomerDvo dvo);

    int insertPromise(WbncCustomerDvo dvo);

    List<SearchUserInfoRes> selectUserInfo(SearchUserInfoReq dto);

    FindCounselRegistrationRes selectCounselRegistration(FindCounselRegistrationReq dto);

    WbncPetitionCreateDvo selectPetitionType(String cntrDtlNo, String baseYm);

    int selectPetitionType1(String cntrDtlNo);

    int selectPetitionType2(String cntrDtlNo);

    int selectPetitionType3(String cntrDtlNo);

    int selectPetitionType4(String cntrDtlNo);

    int selectPetitionType5(String cntrDtlNo);

    int selectPetitionType6(String cntrDtlNo);

    int selectPetitionType7(String cntrDtlNo);
}
