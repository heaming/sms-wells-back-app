package com.kyowon.sms.wells.web.service.visit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.visit.dvo.WsnbProductDvo;

@Mapper
public interface WsnbProductMapper {

    List<WsnbProductDvo> selectProducts();

    int updateAsInstallationAssign(WsnbProductDvo dvo);

    int updateAsInstallationObject(WsnbProductDvo dvo);

}
