package com.kyowon.sms.wells.web.withdrawal.pchssl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.pchssl.dto.WwdcSalesControlDto.SearchSalesControlReq;
import com.kyowon.sms.wells.web.withdrawal.pchssl.dto.WwdcSalesControlDto.SearchSalesControlRes;
import com.kyowon.sms.wells.web.withdrawal.pchssl.dvo.WwdcSalesControlDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

@Mapper
public interface WwdcSalesControlMapper {

    /**
     * 매출조정 조회
     * @param dto
     * @return PagingResult
     */
    PagingResult<SearchSalesControlRes> selectSalesControl(SearchSalesControlReq req, PageInfo pageInfo);

    /**
     * 매출조정 엑셀다운로드
     * @param dto
     * @return List
     */
    List<SearchSalesControlRes> selectSalesControl(SearchSalesControlReq req);

    /**
     * 매출조정 엑셀업로드 validation
     * @param dto
     * @return List
     */
    int selectSalesControlCount(WwdcSalesControlDvo req);

    /**
     * 매출조정 추가
     * @param dto
     * @return List
     */
    int insertSalesControl(WwdcSalesControlDvo req);

    /**
     * 매출조정 이력 추가
     * @param dto
     * @return List
     */
    int insertSalesControlHistory(WwdcSalesControlDvo req);

    /**
     * 매출조정 수정
     * @param dto
     * @return List
     */
    int updateSalesControl(WwdcSalesControlDvo req);

    /**
     * 매출조정이력 수정 
     * @param dto
     * @return List
     */
    int updateSalesControlHistory(WwdcSalesControlDvo req);

    /**
     * 매출확정 데이터 수정( 매출확정T - 조정금액 )
     * @param dto
     * @return List
     */
    int updateSalesConfirm(WwdcSalesControlDvo req);

    /**
     * 매출확정 데이터 삭제 ( 매출확정T - 조정금액 )
     * @param dto
     * @return List
     */
    int deleteSalesControl(WwdcSalesControlDvo req);

}
