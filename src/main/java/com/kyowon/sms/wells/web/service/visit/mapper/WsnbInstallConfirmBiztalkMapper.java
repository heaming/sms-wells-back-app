package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dvo.WsnbInstallConfirmDvo;

@Mapper
public interface WsnbInstallConfirmBiztalkMapper {

    List<WsnbInstallConfirmDvo> selectInstallConfirms();

}
