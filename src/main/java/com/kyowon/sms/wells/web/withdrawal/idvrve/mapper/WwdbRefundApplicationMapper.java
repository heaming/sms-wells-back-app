package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.RefundBasic;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.RefundDetail;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchBankRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchCardRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundApplicationReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundApplicationRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundContractDetailReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundContractDetailRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundPossibleAmountReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundPossibleAmountRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbRefundApplicationBasicDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbRefundApplicationDetailDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbRefundApplicationDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbRefundApplicationInfoDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwdbRefundApplicationMapper {

    PagingResult<SearchRefundApplicationRes> selectRefundApplication(
        SearchRefundApplicationReq req,
        PageInfo pageInfo
    );

    List<SearchRefundApplicationRes> selectRefundApplication(
        SearchRefundApplicationReq req
    );

    PagingResult<SearchRefundContractDetailRes> selectRefundContractDetail(
        SearchRefundContractDetailReq req,
        PageInfo pageInfo
    );

    List<SearchRefundContractDetailRes> selectRefundContractDetail(
        SearchRefundContractDetailReq req
    );

    SearchRefundPossibleAmountRes selectRefundPossibleAmount(
        SearchRefundPossibleAmountReq req
    );

    List<SearchCardRes> selectRefundApplicationCard();

    List<SearchBankRes> selectRefundApplicationBank();

    //    int insertRefundApplicationDetail(WwdbRefundApplicationDvo vo);
    int insertRefundApplicationDetail(WwdbRefundApplicationDetailDvo details);

    //    int insertRefundApplicationDetailHistory(WwdbRefundApplicationDvo vo);
    int insertRefundApplicationDetailHistory(WwdbRefundApplicationDetailDvo details);

    int insertRefundApplication(WwdbRefundApplicationDvo vo);

    int insertRefundApplicationHistory(WwdbRefundApplicationDvo vo);

    RefundBasic selectRefundApplicationInfo(String rfndRcpNo);

    List<RefundDetail> selectRefundApplicationDetailInfo(String rfndRcpNo);

    int updateRefundApplicationDetail(WwdbRefundApplicationDetailDvo details);

    int insertRefundApplicationHistory(WwdbRefundApplicationInfoDvo vo);

    int updateRefundApplication(WwdbRefundApplicationBasicDvo vo);

    int insertRefundApplicationHistory(String rfndRcpNo);

    int deleteRefundApplication(String rfndRcpNo);
}
