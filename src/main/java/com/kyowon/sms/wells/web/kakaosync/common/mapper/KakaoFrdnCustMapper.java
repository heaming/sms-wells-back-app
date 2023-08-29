package com.kyowon.sms.wells.web.kakaosync.common.mapper;

import com.kyowon.sms.wells.web.customer.prospective.dvo.WcsbProspecCustomerDvo;
import com.kyowon.sms.wells.web.customer.prospective.dvo.WcsbTbSsopPspcCstDdlvHistDvo;
import com.kyowon.sms.wells.web.kakaosync.common.dvo.KakaoWcsbSyncDefaultDvo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface KakaoFrdnCustMapper {

    /**
     * 고객구분(신규/기존) 확인
     * 고객전화번호로 카운트하여 기존고객여부 확인
     * @param defaultdvo
     * @return
     */
    public int selectCusthnoExsn(KakaoWcsbSyncDefaultDvo defaultdvo);

    /**
     * 고객상세 파일에서 교원키 조회
     * @param dvo
     * @return
     */
    public String selectKwkey(WcsbProspecCustomerDvo dvo);

    /**
     * 기본정보 저장
     * @param dvo
     * @return
     */
    int insertProspectCustomer(@Param("dvo") WcsbProspecCustomerDvo dvo);

    /**
     * 기본정보 이력저장
     * @param pspcCstId, startDtm
     * @return
     */
    int insertProspectCustomerHistory(String pspcCstId, String startDtm);

    /**
     * 교사(배부이력) 저장
     * @param dvo
     * @return
     */
    int insertPspcCstDdlvHist(WcsbTbSsopPspcCstDdlvHistDvo dvo);

}
