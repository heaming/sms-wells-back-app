package com.kyowon.sms.wells.web.service.allocate.service;

import com.kyowon.sms.wells.web.service.allocate.dvo.WsncCapsuleSubscriptionCustomerDvo;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncCapsuleSubscriptionCustomerMapper;

import java.util.List;

import com.sds.sflex.common.utils.StringUtil;
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

    public int saveCapsuleSubscriptionCustomer() {

        int updateCount = 0;

        List<WsncCapsuleSubscriptionCustomerDvo> res = mapper.selectCapsuleRglrPrchsCsts();
        for (WsncCapsuleSubscriptionCustomerDvo row : res) {

            /*1. 고객마스터(LC_ALLOCATE_AC201TB) 인서트 또는 업데이트*/
            // AS-IS에서는 DB2의 데이터를 오라클로 옮기는 merge into를 진행하는데..
            // TO-BE에서는 필요없는 행위로고 판단됨.

            /*당월 BS 배정*/
            if (StringUtil.isEmpty(row.getAc201CancDt())) { /* 주기표 강제 생성 */

                /*무결성 제약 조건 오류 방지*/
                updateCount += mapper.deleteBfsvcPrd(row.getCntrNo(), row.getAc201CsmrSeq());

                /*@TODO 2. 스케쥴을 생성한다.*/
                //SP_LC_SERVICEVISIT_482_LST_I06 -> 이진성프로 작업 예정

                /*@TODO 3. 배정을 삭제한다. */
                //SP_LC_SERVICEVISIT_482_LST_I07 -> 이진성프로 작업 예정

                /*@TODO 사전방문 BS 생성*/
                //SP_LC_SERVICEVISIT_482_LST_I03 -> 이진성프로 작업 예정

                updateCount += mapper.updateIstDt(row.getCntrNo(), row.getAc201CsmrSeq());

            }
            /*취소일자가 있다면  취소된걸로 인지*/
            else {

                /*취소일자 업데이트*/
                updateCount += mapper.updateCancelDate(row.getCntrNo(), row.getAc201CsmrSeq(), row.getAc201CancDt());
                /*1. 스케쥴을 삭제 한다.*/
                updateCount += mapper.deleteSchd(row.getCntrNo());

                /*@TODO 3. 배정을 삭제한다. */
                //SP_LC_SERVICEVISIT_482_LST_I07 -> 이진성프로 작업 예정
            }

        }

        return updateCount;
    }

}
