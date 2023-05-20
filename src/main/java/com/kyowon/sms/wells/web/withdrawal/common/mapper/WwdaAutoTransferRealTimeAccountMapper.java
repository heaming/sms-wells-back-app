package com.kyowon.sms.wells.web.withdrawal.common.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.withdrawal.common.dvo.WwdaAnAccountEffectivenessResDvo;
import com.kyowon.sms.wells.web.withdrawal.common.dvo.WwdaAutoTransferRealTimeAccountCheckDvo;
import com.kyowon.sms.wells.web.withdrawal.common.dvo.WwdaRealTimeAnAccountChangeResultDvo;

/**
 * <pre>
 * Class Description
 * </pre>
 *
 * @author donghyun.yoo
 * @since 2023-05-18
 */
@Mapper
public interface WwdaAutoTransferRealTimeAccountMapper {

    String selectBankCode(String bnkCd);

    String selectEntrepreneurNo(String copnDvDrmVal);

    String selectAcFntRtmAkAprAskSerialMax();

    int insertExtxMaterialIz(WwdaAutoTransferRealTimeAccountCheckDvo saveDvo);

    int insertExtxMaterialHis(WwdaAutoTransferRealTimeAccountCheckDvo saveDvo);

    WwdaAutoTransferRealTimeAccountCheckDvo selectReceiveResultCheck(String rqdt, String akSn);

    int insertResultConfirmationProcessingHis(String rqdt, String akSn);

    int updateResultConfirmationProcessingIz(String rqdt, String akSn);

    String selectAcFntImpsCdNmCheck(String acFntImpsCd);

    /** 실시간 계좌변경 송신전문 조회
     * 
     * @param reqParam
     * @return 
     */
    WwdaRealTimeAnAccountChangeResultDvo selectRealTimeAnAccountChange(Map<String, Object> reqParam);

    /** 계좌유효성검사 계좌이체실시간요청승인내역 
     * 
     * @param receiveDvo
     * @return
     */
    int updateAcEftnAcFntRtmAkAprIz(WwdaAnAccountEffectivenessResDvo receiveDvo);

    /** 계좌유효성검사 계좌이체실시간요청승인이력
     * 
     * @param receiveDvo
     * @return
     */
    int insertAcEftnAcFntRtmAkAprHist(WwdaAnAccountEffectivenessResDvo receiveDvo);
}
