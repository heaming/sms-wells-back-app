package com.kyowon.sms.wells.web.closing.payment.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.payment.dvo.WdcaBusinessAnticipationAmtDvo;

@Mapper
public interface WdcaBusinessAnticipationAmtMapper {
    int insertBusinessBasic(WdcaBusinessAnticipationAmtDvo dvo);

    int insertBusinessProcess(WdcaBusinessAnticipationAmtDvo dvo);

    String selectSapProductDivisionCode(WdcaBusinessAnticipationAmtDvo dvo);

    Map<String, String> selectContract(WdcaBusinessAnticipationAmtDvo dvo);

    String selectCustomerId(WdcaBusinessAnticipationAmtDvo dvo);

    int insertEtcProcess(WdcaBusinessAnticipationAmtDvo dvo);

    int updateBusinessBasic(WdcaBusinessAnticipationAmtDvo dvo);

    int updateEtcBasic(WdcaBusinessAnticipationAmtDvo dvo);
}
