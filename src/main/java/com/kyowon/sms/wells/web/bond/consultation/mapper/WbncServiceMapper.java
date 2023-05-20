package com.kyowon.sms.wells.web.bond.consultation.mapper;

import static com.kyowon.sms.wells.web.bond.consultation.dto.WbncServiceDto.FindRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WbncServiceMapper {
    List<FindRes> selectServices(
        String cntrNo, int cntrSn
    );
}
