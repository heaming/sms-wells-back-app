package com.kyowon.sms.wells.web.service.allocate.service;

import com.kyowon.sms.wells.web.service.allocate.dto.WsncCapsuleSubscriptionCustomerDto;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncRegularBfsvcAsnDto;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncCapsuleSubscriptionCustomerDvo;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncCapsuleSubscriptionCustomerMapper;

import java.util.List;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbCustomerRglrBfsvcDlDto;
import com.kyowon.sms.wells.web.service.visit.dto.WsnbIndividualVisitPrdDto;
import com.kyowon.sms.wells.web.service.visit.service.WsnbCustomerRglrBfsvcDlService;
import com.kyowon.sms.wells.web.service.visit.service.WsnbIndividualVisitPrdService;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 *
 *
 * W-SV-S-0049 홈카페 캡슐 정기구매 고객 스케쥴 인서트/업데이트
 *
 *
 * @author gs.piit122 김동엽
 * @since 2023-04-13
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsncCapsuleSubscriptionCustomerService {

    private final WsncCapsuleSubscriptionCustomerMapper mapper;

    private final WsnbIndividualVisitPrdService service1;
    private final WsnbCustomerRglrBfsvcDlService service2;

    private final WsncRegularBfsvcAsnService service3;

    public int saveCapsuleSubscriptionCustomer(WsncCapsuleSubscriptionCustomerDto.SearchReq req) throws Exception {

        int updateCount = 0;

        List<WsncCapsuleSubscriptionCustomerDvo> res = mapper.selectCapsuleRglrPrchsCsts(req.baseYmd());
        for (WsncCapsuleSubscriptionCustomerDvo row : res) {

            if (StringUtil.isEmpty(row.getCancDt())) { /* 주기표 강제 생성 */

                updateCount += mapper.deleteBfsvcPrd(row.getCntrNo(), row.getCntrSn());

                /*@TODO 2. 방문주기 재생성(SP_LC_SERVICEVISIT_482_LST_I06)*/
                updateCount += service1.processVisitPeriodRegen(
                    new WsnbIndividualVisitPrdDto.SearchProcessReq(
                        row.getCntrNo(),
                        row.getCntrSn(),
                        req.baseYmd(),
                        "",
                        "",
                        "",
                        "",
                        ""
                    )
                );

                /*@TODO 3. 고객 정기BS 삭제(SP_LC_SERVICEVISIT_482_LST_I07)*/
                updateCount += service2.removeRglrBfsvcDl(
                    new WsnbCustomerRglrBfsvcDlDto.SaveReq(
                        row.getCstSvAsnNo(),
                        row.getAsnOjYm()
                    )
                );

                /*@TODO 고객 정기BS 배정(SP_LC_SERVICEVISIT_482_LST_I03)*/
                updateCount += service3.processRegularBfsvcAsn(
                    new WsncRegularBfsvcAsnDto.SaveProcessReq(
                        row.getAsnOjYm(),
                        SFLEXContextHolder.getContext().getUserSession().getUserId(),
                        row.getCntrNo(),
                        row.getCntrSn()
                    )
                );

                updateCount += mapper.updateIstDt(row.getCntrNo(), row.getCntrSn());

            } else {

                updateCount += mapper.updateCancelDate(row.getCntrNo(), row.getCntrSn(), row.getCancDt());
                updateCount += mapper.deleteSchd(row.getCntrNo());

                /*@TODO 3. 고객 정기BS 삭제(SP_LC_SERVICEVISIT_482_LST_I07)*/
                service2.removeRglrBfsvcDl(
                    new WsnbCustomerRglrBfsvcDlDto.SaveReq(
                        row.getCstSvAsnNo(),
                        row.getAsnOjYm()
                    )
                );
            }

        }

        return updateCount;
    }

}
