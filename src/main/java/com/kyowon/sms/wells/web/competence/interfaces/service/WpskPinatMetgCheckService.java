package com.kyowon.sms.wells.web.competence.interfaces.service;

import com.kyowon.sms.common.web.competence.meetings.dvo.ZpsaMcbyPrtnrMetgAgrgCrtDvo;
import com.kyowon.sms.common.web.competence.meetings.service.ZpsaMcbyPrtnrMetgAgrgCrtService;
import com.kyowon.sms.wells.web.competence.interfaces.converter.WpskPinatMetgCheckConverter;
import com.kyowon.sms.wells.web.competence.interfaces.dvo.WpskPinatMetgCheckDvo;
import com.kyowon.sms.wells.web.competence.interfaces.mapper.WpskPinatMetgCheckMapper;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.validation.BizAssert;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kyowon.sms.wells.web.competence.interfaces.dto.WpskPinatMetgCheckDto.CreateReq;
import static com.kyowon.sms.wells.web.competence.interfaces.dto.WpskPinatMetgCheckDto.CreateRes;

@Service
@RequiredArgsConstructor
public class WpskPinatMetgCheckService {

    private final WpskPinatMetgCheckMapper mapper;
    private final WpskPinatMetgCheckConverter converter;
    private final MessageResourceService messageResourceService;
    private final ZpsaMcbyPrtnrMetgAgrgCrtService mcbyPrtnrMetgAgrgCrtService;

    /**
     * 핀앳) 출결앱 미팅 체크 - 미팅참석 등록 및 월 미팅 집계 실행
     * @param req
     * @return SaveResponse
     */
    @Transactional
    public CreateRes createBeaconMeetingCheck(CreateReq req) {
        /* 언어 세션 임시 설정 */
        UserSessionDvo userSession = SFLEXContextHolder.getContext().getUserSession();
        userSession.setLangId(StringUtil.nvl(userSession.getLangId(), "ko"));

        WpskPinatMetgCheckDvo dvo = converter.mapToDvo(req);
        WpskPinatMetgCheckDvo res = new WpskPinatMetgCheckDvo();

        try {
            String[] prtnrList = null;
            if(StringUtils.isNotBlank(dvo.getPrtnrNos())){
                prtnrList = dvo.getPrtnrNos().replaceAll("\\s", "").split(",");
            }

            for (String prtnr : prtnrList) {
                WpskPinatMetgCheckDvo reqDvo = new WpskPinatMetgCheckDvo();
                reqDvo.setPrtnrNo(prtnr);
                reqDvo.setMetgPrscDt(dvo.getMetgPrscDt());

                /* 미팅 등록 */
                int resultCount = mapper.updateMeetingCheck(reqDvo);
                BizAssert.isTrue(resultCount == 1, messageResourceService.getMessage("MSG_ALT_SVE_ERR"));

                /* 월 미팅 집계 실행 */
                ZpsaMcbyPrtnrMetgAgrgCrtDvo mcbyDvo = new ZpsaMcbyPrtnrMetgAgrgCrtDvo();
                mcbyDvo.setOgTpCd(mapper.selectPrtnrOgTpCd(reqDvo)); /* 파트너 조직 유형 코드 조회 */
                mcbyDvo.setPrtnrNo(reqDvo.getPrtnrNo());
                mcbyDvo.setAgrgYm(reqDvo.getMetgPrscDt().substring(0, 6));
                mcbyPrtnrMetgAgrgCrtService.editMcbyPrtnrMetgAgrg(mcbyDvo);
            }
            res.setRspCd("Y");

            // [TO-DO] FEP 연동 추가 해야함

            return converter.dvoToCreateRes(res);
        } catch (Exception e) {
            res.setRspCd("N");
            res.setRspMsg(e.getMessage());
            return converter.dvoToCreateRes(res);
        }
    }
}
