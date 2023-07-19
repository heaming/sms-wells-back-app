package com.kyowon.sms.wells.web.service.interfaces.service;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncRegularBfsvcAsnDto;
import com.kyowon.sms.wells.web.service.allocate.service.WsncRegularBfsvcAsnService;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniSidingServiceChangesDto.SaveReq;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniSidingServiceChangesDto.SaveRes;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniSidingServiceChangesDvo;
import com.kyowon.sms.wells.web.service.interfaces.mapper.WsniSidingServiceChangesMapper;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbCustomerRglrBfsvcDlDto;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbIndividualVisitPrdDto;
import com.kyowon.sms.wells.web.service.visit.service.WsnbCustomerRglrBfsvcDlService;
import com.kyowon.sms.wells.web.service.visit.service.WsnbIndividualVisitPrdService;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsniSidingServiceChangesService {

    private final WsniSidingServiceChangesMapper mapper;

    private final WsnbIndividualVisitPrdService service1;
    private final WsnbCustomerRglrBfsvcDlService service2;
    private final WsncRegularBfsvcAsnService service3;

    /**
     * <pre>
     * PR_KIWI_FARM_CHANGE
     * </pre>
     *
     * @author gs.piit122
     * @since 2023.07.18
     */
    public void SidingChange(SaveReq req) throws Exception {

        mapper.insertSdingAsAkHist(req);

        if ("3".equals(req.mtrProcsStatCd()))

            mapper.deleteSdingAskAk(req);

        else {

            if (mapper.selectSidingAkCount(req).intValue() > 0)
                mapper.updateSidingAk(req);
            else
                mapper.insertSidingAk(req);
        }

        WsniSidingServiceChangesDvo dvo = mapper.selectCustomer(req);

        //IF(P_REQ_GB = '1' AND P_DATA_STUS != '3') THEN
        if ("1".equals(req.asAkDvCd()) && !"3".equals(req.mtrProcsStatCd())) {

            /*방문주기 재생성(SP_LC_SERVICEVISIT_482_LST_I06)*/
            service1.processVisitPeriodRegen(
                new WsnbIndividualVisitPrdDto.SearchProcessReq(
                    req.cntrNo(),
                    req.cntrSn(),
                    req.akChdt(),
                    "",
                    "",
                    "",
                    "",
                    ""
                )
            );

            if (mapper.selectBsTarget(req) > 0) {
                /*고객 정기BS 삭제(SP_LC_SERVICEVISIT_482_LST_I07)*/
                service2.removeRglrBfsvcDl(
                    new WsnbCustomerRglrBfsvcDlDto.SaveReq(
                        "",//row.getCstSvAsnNo(),
                        ""//row.getAsnOjYm()
                    )
                );

                /*고객 정기BS 배정(SP_LC_SERVICEVISIT_482_LST_I03)*/
                service3.processRegularBfsvcAsn(
                    new WsncRegularBfsvcAsnDto.SaveProcessReq(
                        "",//row.getAsnOjYm(),
                        "",//SFLEXContextHolder.getContext().getUserSession().getUserId(),
                        req.cntrNo(),
                        req.cntrSn()
                    )
                );
            }
        }

        if ("1".equals(req.asAkDvCd())) {
            //UPDATE TB_SVPD_CST_SV_RGBSPR_IZ -- 모종 주기표에 업데이트
            //UPDATE TB_SVPD_CST_SV_RGBSPR_IZ -- 디바이스 주기표에 업데이트
        }

    }

    /**
     * <pre>
     * W-SV-I-0025 모종 상품/서비스 변경 처리
     * </pre>
     *
     * @author gs.piit122
     * @since 2023.07.18
     */
    public SaveRes saveSidingProductChange(SaveReq req) throws Exception {
        //   LC_ASREGN_API_I02_T
        //   {
        //       CALL PR_KIWI_FARM_CHANGE
        SidingChange(req);
        //
        //   LC_ASREGN_API_I03_T
        //   {
        //       if (P_DATA_STUS.equals("3")) {
        //
        //           LC_ASREGN_API_I05
        //           -- INSERT LD3300H(정기배송　주문변경　관리History)
        //
        //           LC_ASREGN_API_D01
        //           -- DELETE LD3200P(정기배송　주문변경　관리)
        //
        //           return true;
        //
        //       } else {
        //
        //           int LD3200_CNT = LC_ASREGN_API_S09
        //           -- COUNT(*) LD3200P(정기배송　주문변경　관리)
        //
        //           if (LD3200_CNT == 0) {
        //
        //               ArrayList chk1 = LC_ASREGN_API_S08
        //               -- SELECT LC0200P(일련번호　채번（고객코드，일자，단순）)
        //
        //			if (chk1 != null) {
        //
        //			if (SEQ.equals("1")) {
        //				LC_ASREGN_API_I03
        //				-- INSERT LC0200P(일련번호　채번（고객코드，일자，단순）)
        //			} else {
        //				LC_ASREGN_API_U04
        //				-- UPDATE LC0200P(일련번호　채번（고객코드，일자，단순）)
        //			}
        //
        //			LC_ASREGN_API_I04
        //			-- INSERT LD3200P(정기배송　주문변경　관리)
        //
        //			LC_ASREGN_API_I05
        //			-- INSERT LD3300H(정기배송　주문변경　관리History)
        //
        //
        //			return true;
        //
        //			} else {
        //				throw new Exception(">> LC_ASREGN_API_I03_T : data insert failed <<");
        //			}
        //
        //		} else {
        //
        //			LC_ASREGN_API_U05
        //			-- UPDATE LD3200P(정기배송　주문변경　관리)
        //
        //			LC_ASREGN_API_I05
        //			-- INSERT LD3300H(정기배송　주문변경　관리History)
        //
        //			return true;
        //
        //		}
        //	}

        return null;
    }
}
