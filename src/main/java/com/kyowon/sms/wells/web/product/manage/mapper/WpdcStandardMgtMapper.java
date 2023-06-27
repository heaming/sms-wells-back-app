package com.kyowon.sms.wells.web.product.manage.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.product.manage.dto.WpdcStandardMgtDto;

/**
 * <pre>
 * 상품(공통) - 상품운영관리 - 기준상품
 * wpdc-standard-mgt.xml
 * </pre>
 *
 * @author jintae.choi
 * @since 2023-06-30
 */
@Mapper
public interface WpdcStandardMgtMapper {

    WpdcStandardMgtDto.SaleRecognitionClassification selectSaleRecognClassName(String slRcogClsfCd);
}
