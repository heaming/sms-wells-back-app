package com.kyowon.sms.wells.web.closing.standard.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.closing.standard.dvo.WdcyBusinessCloseHhCheckDvo;
import com.kyowon.sms.wells.web.closing.standard.mapper.WdcyBusinessCloseHhCheckMapper;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 영업마감통제 서비스
 * </pre>
 *
 * @author gs.piit183
 * @since 2023-02-06
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WdcyBusinessCloseHhCheckService {

    private final WdcyBusinessCloseHhCheckMapper mapper;

    /**
     * 일자 + 담당자번호 + 마감업무유형코드로 처리가능여부와 실적일자를 리턴
     * @param clDt 일자
     * @param clPsicNo 담당자번호
     * @param clBizTpCd 마감업무유형코드
     * @return EBusinessCloseHhCheckDvo procsPsbYn:처리가능 여부, perfDt:실적 일자
     * @throws BizException 조회 결과가 없는 경우 Exception 처리
     */
    public WdcyBusinessCloseHhCheckDvo getBusinessCloseHhCheck(String clDt, String clPsicNo, String clBizTpCd)
        throws BizException {
        HashMap<String, String> searchParam = new HashMap<>();
        searchParam.put("clDt", clDt);
        searchParam.put("clPsicNo", clPsicNo);
        searchParam.put("clBizTpCd", clBizTpCd);

        WdcyBusinessCloseHhCheckDvo WdcyBusinessCloseHhCheckDvo = mapper.selectBusinessCloseHh(searchParam);
        if (Objects.isNull(WdcyBusinessCloseHhCheckDvo)) {

            //입력 값으로 조회 검색 결과가 없는 경우 담당자번호 전체('0')로 변경 후 다시 검색
            HashMap<String, String> cloneParam = new HashMap<>(searchParam);
            cloneParam.put("clPsicNo", "0");
            WdcyBusinessCloseHhCheckDvo = mapper.selectBusinessCloseHh(cloneParam);
        }
        Optional<WdcyBusinessCloseHhCheckDvo> optionalRes = Optional.ofNullable(WdcyBusinessCloseHhCheckDvo);

        //조회 결과 없는 경우 에러 발생
        WdcyBusinessCloseHhCheckDvo = optionalRes.orElseThrow(() -> new BizException("MSG_ALT_CL_HH_UNRG"));
        if (closeHourConfirmation()) {
            WdcyBusinessCloseHhCheckDvo.setProcsPsbYn("Y");
            return WdcyBusinessCloseHhCheckDvo;
        } else {
            WdcyBusinessCloseHhCheckDvo.setProcsPsbYn("N");
            WdcyBusinessCloseHhCheckDvo.setPerfDt("00010101"); // 2곳 이상 사용하는 경우 상수로 변경
            return WdcyBusinessCloseHhCheckDvo;
        }
    }

    /**
     * 영업마감 시간 정보를 받아서 현재 시간이 마감 시간에 포함 되는지 여부를 리턴
     * @return boolean(true:포함, false:미포함)
     */
    protected boolean closeHourConfirmation() {

        // DB에서 데이터 확인을 하지 않아 형식에 대한 처리를 임의로 구성 데이터 구성 후 추가 작업
        LocalDateTime curDate = LocalDateTime.now();
        LocalDateTime stDate = LocalDateTime.of(2023, 2, 1, 11, 0, 0, 0);
        LocalDateTime edDate = LocalDateTime.of(2023, 2, 12, 11, 0, 0, 0);
        return (curDate.isAfter(stDate) || curDate.isEqual(stDate))
            && (curDate.isBefore(edDate) || curDate.isEqual(edDate));
    }

}
