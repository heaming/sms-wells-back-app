package com.kyowon.sms.wells.web.service.visit.service;

import com.kyowon.sms.wells.web.service.visit.converter.WsnbCallingLogSaveConverter;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.visit.dto.WsnbCallingLogSaveDto.CreateReq;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbCallingLogDvo;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbCallingLogSaveMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * W-SV-S-0013 PR_KIWI_WRK_CREATE_V2M 호출 로그 저장
 * </pre>
 *
 * @author yeonghwa.cheon
 * @since 2023.02.08
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsnbCallingLogSaveService {
    private WsnbCallingLogSaveMapper mapper;
    private WsnbCallingLogSaveConverter converter;

    /**
     * PR_KIWI_WRK_CREATE_V2M 호출 로그 저장
     * @param dto : [{  asIstOjNo : AS설치대상번호, histChDtm : 이력변경일시, cntrNo : 계약번호, cntrSn : 계약일련번호,
     *          cntrCst_no : 계약고객번호, inChnlDvCd : 입력채널구분코드, svBizHclsfCd : 서비스업무대분류코드,
     *          svBizDclsfCd : 서비스업무세분류코드, rcpSvBizDclsfCd : 접수서비스업무세분류코드, rcpdt : 접수일자,
     *          rcpHh : 접수시간, urgtYn : 긴급여부, vstRqdt : 방문요청일자, vstAkHh : 방문요청시간,
     *          cnslTpHclsfCd : 상담유형대분류코드, cnslTpMclsfCd : 상담유형중분류코드, cnslTpLclsfCd : 상담유형소분류코드,
     *          asRefriDvCd : AS유무상구분코드, bfsvcRefriDvCd : BS유무상구분코드, smsFwYn : SMS발송여부, dpDvCd : 입금구분코드,
     *          svEtAmt : 서비스예상금액, svCnrOgId : 서비스센터조직ID, mrtStatCd : 자료상태코드, pdCd : 상품코드,
     *          pdGdCd : 상품등급코드, pdUswyCd : 상품용도코드, cstSvAsnNo : 고객서비스배정번호, cnslMoCn : 상담메모내용,
     *          cnslDtlpTpCd : 상담세부유형코드, asAkDvCd1 : AS요청구분코드1, asAkDvCd2 : AS요청구분코드2,
     *          istllKnm : 설치자한글명, adrDvCd : 주소구분코드, istAdr : 설치주소 }]
     */
    public int createCallingLog(CreateReq dto) throws Exception {
        int processCount = 0;

        WsnbCallingLogDvo dvo = converter.mapCreateReqToWsnbCallingLogDvo(dto);
        int result = mapper.insertCallingLog(dvo);
        processCount += result;

        return processCount;
    }
}
