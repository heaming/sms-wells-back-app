package com.kyowon.sms.wells.web.service.orgcode.dto;

import io.swagger.annotations.ApiModel;

public class WsndHumanResourcesDto {
    // *********************************************************
    // Request Dto
    // *********************************************************
    // 인사기본정보 Search Request Dto
    @ApiModel(value = "WsndHumanResourcesDto-SearchReq")
    public record SearchReq(
        String mngrDvCd,
        String deptCd,
        String cnrCd,
        String searchText
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 인사기본정보 Search Result Dto
    @ApiModel(value = "WsndHumanResourcesDto-SearchRes")
    public record SearchRes(
        String empId,
        String empNm,
        String pstnDvCd,
        String rolDvCd,
        String entcoDt,
        String rsgnDt,
        String cnrCd,
        String cnrNm,
        String brchCd,
        String brchNm
    ) {}

    // 총국 Search Result Dto
    @ApiModel(value = "WsndHumanResourcesDto-SearchDepartmentRes")
    public record SearchDepartmentRes(
        String deptCd,
        String deptNm
    ) {}

    // 소속센터 Search Result Dto
    @ApiModel(value = "WsndHumanResourcesDto-SearchCenterRes")
    public record SearchCenterRes(
        String cnrCd,
        String cnrNm
    ) {}
}
