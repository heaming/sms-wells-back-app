package com.kyowon.sms.wells.web.withdrawal.idvrve.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchBankRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchCardRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundApplicationReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundApplicationRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundContractDetailReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundContractDetailRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundPossibleAmountReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbRefundApplicationDto.SearchRefundPossibleAmountRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbRefundApplicationDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbRefundApplicationDvo.saveList;
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
    int insertRefundApplicationDetail(saveList saveList);

    //    int insertRefundApplicationDetailHistory(WwdbRefundApplicationDvo vo);
    int insertRefundApplicationDetailHistory(saveList saveList);

    int insertRefundApplication(WwdbRefundApplicationDvo vo);

    int insertRefundApplicationHistory(WwdbRefundApplicationDvo vo);
}
