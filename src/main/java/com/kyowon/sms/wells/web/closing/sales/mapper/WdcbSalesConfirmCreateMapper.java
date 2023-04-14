package com.kyowon.sms.wells.web.closing.sales.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.closing.sales.dvo.WdcbSalesConfirmCreateDvo;

/**
 * <pre>
 * 매출확정생성 서비스 맵퍼
 * </pre>
 *
 * @author WOO SEUNGMIN
 * @since 2023-04-13
 */
@Mapper
public interface WdcbSalesConfirmCreateMapper {
    /**
     * @param 인서트 테이블(TB_CBCL_SL_CNFM_BAS)
     * @return insert 결과
     */
    int insertSalesConfirm(WdcbSalesConfirmCreateDvo dvo);
}
