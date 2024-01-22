package com.kyowon.sms.wells.web.fee.aggregate.service;

import static com.kyowon.sms.common.web.fee.standard.constant.FeFeeConst.DEFAULT_FEE_TCNT_DIVIDE_CODE;
import static com.kyowon.sms.common.web.fee.standard.constant.FeFeeConst.FeePerfAgrgPrtcDiviceCode.PARTNER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.common.web.fee.aggregate.service.ZfeaPerfAgrgPrtcHistService;
import com.kyowon.sms.common.web.fee.calculation.mapper.ZfeaRedfAdsbPerfMapper;
import com.kyowon.sms.wells.web.fee.aggregate.mapper.WfeaRedemptionPerfMapper;
import com.sds.sflex.common.common.dvo.CodeDetailDvo;
import com.sds.sflex.common.common.service.CodeService;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

/**
 * <pre>
 * Wells 사업부 되물림 실적 생성
 * </pre>
 *
 * @author jingun jung
 * @since 2023-10-08
 */
@Service
@RequiredArgsConstructor
public class WfeaRedemptionPerfService {
    private final ZfeaPerfAgrgPrtcHistService perfAgrgPrtcHistService;
    private final WfeaRedemptionPerfMapper mapper;

    private final ZfeaRedfAdsbPerfMapper zRedfAdsbmapper;

    private final CodeService codeService;

    /**
     * Wells 사업부 되물림 실적 생성
     *
     * @param baseYm 기준년월
     */
    @SneakyThrows
    @Transactional
    public void aggregateRedemptionOfFeePerformance(
        String baseYm, String ogTpCd, String perfAgrgCrtDvCd, String cntrPerfCrtDvCd
    ) {

        /* 순주문월마감 테이블에 되물림 데이터 재생성 가능한지 체크 */
        /* 확정데이터가 있으면 확정되어 집계 불가 오류 발생 */
        CodeDetailDvo code = codeService.getCodeDetails("OG_TP_CD").stream()
            .filter(item -> ogTpCd.equals(item.getCodeValidityValue())).findFirst().orElse(null);
        BizAssert.isTrue(
            zRedfAdsbmapper.selectRedfAdsbConfirmStatus(baseYm, ogTpCd, perfAgrgCrtDvCd) == 0,
            "MSG_ALT_CANT_AGRG_BY_CONFIRM", new String[] {baseYm, code.getCodeName()}
        );

        /* 되물림상태코드 생성 */
        zRedfAdsbmapper.insertRedfAdsbStatus(baseYm, ogTpCd, perfAgrgCrtDvCd);

        String perfAgrgPrtcId = perfAgrgPrtcHistService
            .savePerfAgrgPrtcHistory(baseYm, PARTNER.getCode(), perfAgrgCrtDvCd, DEFAULT_FEE_TCNT_DIVIDE_CODE);
        Integer feeAgrgPrtcSn = 0;

        try {
            /* 기생성 데이터 삭제 */
            feeAgrgPrtcSn = perfAgrgPrtcHistService.savePerfAgrgPrtcDtlHistory(perfAgrgPrtcId, "W00301");
            Integer deleteCount = zRedfAdsbmapper
                .deleteCommonRedfAdsbPerfData("TB_FEAM_NTORP_PERF_MM_CL", baseYm, ogTpCd, perfAgrgCrtDvCd);
            deleteCount += zRedfAdsbmapper
                .deleteCommonRedfAdsbPerfData("TB_FEAM_NTORP_CNTR_MM_CL", baseYm, ogTpCd, perfAgrgCrtDvCd);
            deleteCount += zRedfAdsbmapper
                .deleteCommonRedfAdsbPerfData("TB_FEAM_PRTNR_PERF_MM_CL", baseYm, ogTpCd, perfAgrgCrtDvCd);
            perfAgrgPrtcHistService.editPerfAgrgPrtcDtlHistory(perfAgrgPrtcId, feeAgrgPrtcSn, deleteCount);

            List<String> perfAtcCds = new ArrayList<>();
            /* 조직유형별 실적 생성 */
            switch (ogTpCd) {
                case "W01": /* P조직 */
                    perfAtcCds.addAll(
                        Arrays.asList("W00P00029", "W00P00030", "W01P00001", "W00P00028", "W01P00003", "W01P00004", "W01P00009", "W01P00029")
                    );
                    break;
                case "W02": /* M조직 */
                    perfAtcCds.addAll(
                        Arrays.asList(
                            "W00P00003", "W00P00004", "W00P00005", "W00P00006", "W00P00007", "W00P00008", "W00P00009",
                            "W00P00010", "W00P00011", "W00P00012", "W00P00013", "W00P00014", "W00P00015", "W00P00016",
                            "W00P00017", "W00P00018", "W00P00019", "W00P00028", "W00P00029", "W00P00030", "W00P00031",
                            "W00P00032", "W00P00033", "W00P00034", "W00P00035", "W00P00036", "W00P00037", "W00P00043",
                            "W00P00057", "W00P00058", "W00P00059", "W00P00068", "W00P00069", "W00P00072", "W00P00074",
                            "W00P00077", "W00P00078", "W00P00080", "W00P00081", "W00P00082", "W00P00087", "W00P00088",
                            "W02P00002", "W02P00023", "W02P00026", "W02P00027", "W02P00112", "W02P00113"
                        )
                    );
                    break;
                case "W03": /* 홈마스터 */
                    perfAtcCds.addAll(
                        Arrays.asList(
                            "W00P00005", "W00P00006", "W00P00007", "W00P00008", "W00P00009", "W00P00010", "W00P00011",
                            "W00P00012", "W00P00013", "W00P00014", "W00P00015", "W00P00016", "W00P00017", "W00P00018",
                            "W00P00019", "W00P00031", "W00P00032", "W00P00033", "W00P00034", "W00P00036", "W03P00002",
                            "W03P00023", "W03P00026", "W03P00027", "W03P00122", "W03P00123", "W03P00126"
                        )
                    );
                    break;
                case "W04": /* B2B */
                    perfAtcCds.addAll(Arrays.asList("W04P00001")); // 기본수수료
                    break;
                case "W05": /* 총판 */
                    perfAtcCds.addAll(Arrays.asList("W05P00001")); // 기본수수료
                    break;
            }

            /* 계약별 되물림 내역 생성 */
            /* 파트너 개인실적 계약 데이터 생성 */
            Integer insertCount = 0;
            feeAgrgPrtcSn = perfAgrgPrtcHistService.savePerfAgrgPrtcDtlHistory(perfAgrgPrtcId, "W00302");
            insertCount += mapper
                .insertContractDataFromNtorp(baseYm, ogTpCd, cntrPerfCrtDvCd, perfAgrgCrtDvCd, perfAtcCds);

            if ("W01".equals(ogTpCd)) {
                /* 개인 라이프 상조 취소되물림 계약 데이터 생성 */
                insertCount += mapper
                    .insertIndivContractDataFromLife(baseYm, ogTpCd, "W01P00010", cntrPerfCrtDvCd, perfAgrgCrtDvCd);
                /* 조직 라이프 상조 취소되물림 계약 데이터 생성 */
                insertCount += mapper
                    .insertOrgContractDataFromLife(baseYm, ogTpCd, "W01P00034", cntrPerfCrtDvCd, perfAgrgCrtDvCd);
                perfAtcCds.addAll(Arrays.asList("W01P00010", "W01P00034"));
            } else if ("W02".equals(ogTpCd)) {
                /* 개인 라이프 상조 취소되물림 계약 데이터 생성 */
                insertCount += mapper
                    .insertIndivContractDataFromLife(baseYm, ogTpCd, "W02P00136", cntrPerfCrtDvCd, perfAgrgCrtDvCd);
                /* 조직 라이프 상조 취소되물림 계약 데이터 생성 */
                insertCount += mapper
                    .insertOrgContractDataFromLife(baseYm, ogTpCd, "W02P00137", cntrPerfCrtDvCd, perfAgrgCrtDvCd);
                perfAtcCds.addAll(Arrays.asList("W02P00136", "W02P00137"));
            }
            perfAgrgPrtcHistService.editPerfAgrgPrtcDtlHistory(perfAgrgPrtcId, feeAgrgPrtcSn, insertCount);

            /* 파트너 개인실적 합계 생성 */
            feeAgrgPrtcSn = perfAgrgPrtcHistService.savePerfAgrgPrtcDtlHistory(perfAgrgPrtcId, "W00303");
            insertCount = zRedfAdsbmapper
                .insertSumOfCntrDataToPrtnrForIndiv(baseYm, ogTpCd, perfAgrgCrtDvCd, perfAtcCds);
            perfAgrgPrtcHistService.editPerfAgrgPrtcDtlHistory(perfAgrgPrtcId, feeAgrgPrtcSn, insertCount);

            /* 조직 합계 생성 */
            feeAgrgPrtcSn = perfAgrgPrtcHistService.savePerfAgrgPrtcDtlHistory(perfAgrgPrtcId, "W00303");
            insertCount = zRedfAdsbmapper
                .insertSumOfCntrDataToPrtnrForOg(baseYm, ogTpCd, perfAgrgCrtDvCd, perfAtcCds, Arrays.asList("2"));
            perfAgrgPrtcHistService.editPerfAgrgPrtcDtlHistory(perfAgrgPrtcId, feeAgrgPrtcSn, insertCount);

            /**
             *  P조직 실활동인원수 되물림실적 생성
             *
             *  실적항목코드 : W01P00020
             *  사용할 실적항목코드 : W01P00009 (해당되물림 실적 집계 후 실행)
             *  ASIS소스 : SELECT COUNT(DISTINCT AKDCDE) AS PERF_VAL
             *                  , MAX(A.AKWDAY) AS AKWDAY, MAX(A.AKWTIM) AS AKWTIM, MAX(A.AKWCDE) AS AKWCDE, MAX(A.AKWPGM) AS AKWPGM
             *               FROM IFMIG.AK1700P@DL_TMIGKW1_BIPLMIG A
             *              WHERE AKDFLG = '0' AND AKDGUB = 2  AND AKDRNK = 15  AND TOTSUD >= 2500000
             *              GROUP BY A.AKDDTY, A.AKDDTM, AKDBON
             */
            if ("W01".equals(ogTpCd)) {
                feeAgrgPrtcSn = perfAgrgPrtcHistService.savePerfAgrgPrtcDtlHistory(perfAgrgPrtcId, "W01301");
                insertCount = mapper.insertActiPplNForPorganization(baseYm, ogTpCd, perfAgrgCrtDvCd);
                perfAgrgPrtcHistService.editPerfAgrgPrtcDtlHistory(perfAgrgPrtcId, feeAgrgPrtcSn, insertCount);
            }
        } catch (Exception e) {
            String errrorMessage = "";
            if (e.getCause() != null) {
                errrorMessage = e.getCause().getMessage();
            } else if (e.getMessage() != null) {
                errrorMessage = e.getMessage();
            }
            perfAgrgPrtcHistService.savePerfAgrgPrtcErr(perfAgrgPrtcId, feeAgrgPrtcSn, errrorMessage);
            throw new Exception(e);
        }

        perfAgrgPrtcHistService.editPerfAgrgPrtcHistory(perfAgrgPrtcId);
    }
}
