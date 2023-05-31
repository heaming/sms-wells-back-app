package com.kyowon.sms.wells.web.withdrawal.pchssl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.pchssl.dto.WwdcSalesConfirmDto.SearchSalesConfirmReq;
import com.kyowon.sms.wells.web.withdrawal.pchssl.dto.WwdcSalesConfirmDto.SearchSalesConfirmRes;
import com.kyowon.sms.wells.web.withdrawal.pchssl.dvo.WwdcSalesConfirmDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwdcSalesConfirmMapper {

    /**
     * 매출확정관리
     * @param dto
     * @return PagingResult
     */
    PagingResult<SearchSalesConfirmRes> selectSalesConfirm(SearchSalesConfirmReq req, PageInfo pageInfo);

    /**
     * 매출확정관리 엑셀다운로드
     * @param dto
     * @return List
     */
    List<SearchSalesConfirmRes> selectSalesConfirm(SearchSalesConfirmReq req);

    /**
     * 매출확정관리 인식상태변경
     * @param dto
     * @return List
     */
    int updateSalesConfirm(WwdcSalesConfirmDvo dvo);
}
