package com.kyowon.sms.wells.web.closing.standard.service;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.closing.standard.dto.WdcyBusinessCloseHhCheckDto.SearchRes;
import com.kyowon.sms.wells.web.closing.standard.dvo.WdcyBusinessCloseHhCheckDvo;
import com.kyowon.sms.wells.web.closing.standard.mapper.WdcyBusinessCloseHhCheckMapper;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 일마감통제확인 서비스
 * </pre>
 *
 * @author gs.piit183
 * @since 2023-02-06
 */
@Service
@RequiredArgsConstructor
public class WdcyBusinessCloseHhCheckService {

    private final WdcyBusinessCloseHhCheckMapper mapper;

    /**
     * 일자 + 담당자번호 + 마감업무유형코드로 처리가능여부와 실적일자를 리턴
     * @param clDt 일자
     * @param clPsicNo 담당자번호
     * @param clBizTpCd 마감업무유형코드
     * @return WdcyBusinessCloseHhCheckDto procsPsbYn:처리가능 여부, perfDt:실적 일자
     * @throws BizException 조회 결과가 없는 경우 Exception 처리
     */
    public SearchRes getBusinessCloseHhCheck(String clDt, String clPsicNo, String clBizTpCd)
        throws BizException {

        WdcyBusinessCloseHhCheckDvo search = mapper.selectBusinessCloseHh(clDt, clPsicNo, clBizTpCd);
        if (Objects.isNull(search)) {

            //입력 값으로 조회 검색 결과가 없는 경우 담당자번호 전체('0')로 변경 후 다시 검색
            WdcyBusinessCloseHhCheckDvo research = mapper.selectBusinessCloseHh(clDt, "0", clBizTpCd);
            if (Objects.isNull(research)) {
                throw new BizException("MSG_ALT_CL_HH_UNRG");
            } else {
                return mapper.selectCloseHourConfirm(research);
            }
        } else {
            return mapper.selectCloseHourConfirm(search);
        }
    }
}
