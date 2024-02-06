package com.kyowon.sms.wells.web.service.stock.mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaPcsvOutOfStorageSaveDvo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WsnaPcsvOutOfStorageSaveMapper {

    String selectNewLgstOstrAkNo();

    /**
     * TB_SVPD_CST_SV_WK_RS_IZ 고객서비스작업결과내역
     * @param vo
     * @return
     */
    int selectExistSvpdCstSvWkRsIz(WsnaPcsvOutOfStorageSaveDvo vo);

    /**
     * TB_OGBS_MM_PRTNR_IZ 월파트너내역
     * 작업엔지니어 정보 단건 조회
     * @param vo
     * @return
     */
    WsnaPcsvOutOfStorageSaveDvo selectEngineerOgbsMmPrtnrIz(WsnaPcsvOutOfStorageSaveDvo vo);

    /**
     * TB_SVPD_AS_TP_CD   AS유형코드
     * 위치현상원인수당조회
     * @param vo
     * @return
     */
    WsnaPcsvOutOfStorageSaveDvo selectAsCodeSvpdCstSvasIstOjIz(WsnaPcsvOutOfStorageSaveDvo vo);

    /**
     * TB_SVPD_CST_SVAS_IST_OJ_IZ   고객서비스AS설치대상내역
     * TB_SVPD_CST_SVAS_IST_ASN_IZ  고객서비스AS설치배정내역
     * 반품요청오더가 미처리된게 있다면 수불 없이 완료 처리를 한다
     * @param vo
     * @return
     */
    WsnaPcsvOutOfStorageSaveDvo selectReturningSvpdCstSvasIstOjIz(WsnaPcsvOutOfStorageSaveDvo vo);

    /**
     * TB_SVPD_CST_SVAS_IST_ASN_IZ   고객서비스AS설치배정내역
     * @param vo
     */
    void updateSvpdCstSvasIstAsnIz(WsnaPcsvOutOfStorageSaveDvo vo);

    /**
     * TB_SVPD_CST_SV_WK_RS_IZ 고객서비스작업결과내역
     * @param vo
     */
    void insertSvpdCstSvWkRsIz(WsnaPcsvOutOfStorageSaveDvo vo);

    /**
     * TB_SVST_SV_WK_OSTR_IZ 서비스작업출고내역
     * @param vo
     */
    void insertSvstSvWkOstrIz(WsnaPcsvOutOfStorageSaveDvo vo);

    /**
     * TB_SVPD_CST_SV_EXCN_IZ 고객서비스수행내역
     * @param vo
     */
    void updateSvpdCstSvExcnIz(WsnaPcsvOutOfStorageSaveDvo vo);
}
