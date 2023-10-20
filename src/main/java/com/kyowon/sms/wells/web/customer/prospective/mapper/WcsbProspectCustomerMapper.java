package com.kyowon.sms.wells.web.customer.prospective.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kyowon.sms.wells.web.customer.prospective.dvo.WcsbProspectCustomerBasDvo;
import com.kyowon.sms.wells.web.customer.prospective.dvo.WcsbProspectCustomerCnslBasDvo;

/**
 * <pre>
 * 웰스홈페이지 신규접수 I/F Mapper
 * </pre>
 *
 * @author jeongeon.kim
 * @since 2023-06-26
 */
@Mapper
public interface WcsbProspectCustomerMapper {

    /**
    * 가망고객 기본 insert
    * @param dvo 가망고객 기본 등록 정보
    * @return int 등록 건수
    */
    int insertPspcCstBas(@Param("dvo")
    WcsbProspectCustomerBasDvo dvo);

    /**
    * 가망고객 변경 이력 insert
    * @param pspcCstId 가망고객ID
    * @return int 등록 건수
    */
    int insertPspcCstChHist(String pspcCstId);

    /**
    * 가망고객상담기본 insert
    * @param dvo 가망고객상담기본 등록 정보
    * @return int 등록 건수
    */
    int insertPspcCstCnslBas(@Param("dvo")
    WcsbProspectCustomerCnslBasDvo dvo);

    /**
    * 가망고객상담변경이력 insert
    * @param pspcCstCnslId 가망고객상담ID
    * @param pspcCstId 가망고객ID (둘 중 한가지는 필수)
    * @return int 등록 건수
    */
    int insertPspcCstCnslChHist(String pspcCstCnslId, String pspcCstId);
}
