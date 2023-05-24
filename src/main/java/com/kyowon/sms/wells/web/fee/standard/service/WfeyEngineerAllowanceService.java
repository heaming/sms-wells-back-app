package com.kyowon.sms.wells.web.fee.standard.service;

import com.kyowon.sms.wells.web.fee.standard.dto.WfeyEngineerAllowanceDto.*;
import com.kyowon.sms.wells.web.fee.standard.mapper.WfeyEngineerAllowanceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * <pre>
 * 엔지니어 단가 서비스
 * </pre>
 *
 * @author jingun jung
 * @since 2023.05.23
 */
@Service
@RequiredArgsConstructor
public class WfeyEngineerAllowanceService {

    private final WfeyEngineerAllowanceMapper engineerAllowanceMapper;

    /**
     * 엔지니어 수당 단가 목록 조회
     * @param req
     * @return
     */
    public List<SearchAllowanceUnitPriceRes> getEngineerAwUprcs(SearchAllowanceUnitPriceReq req) {
        return engineerAllowanceMapper.selectEngienerAwUprcs(req);
    }

    /**
     * 엔지니어 수당 단가 신규추가
     * @param req
     * @return
     */
    public Integer createEngineerAwUprcs(CreateAllowanceUnitPriceReq req) {
        return engineerAllowanceMapper.insertEnginnerAwUprcs(req);
    }

    /**
     * 엔지니어 수당 단가 수정
     * @param req
     * @return
     */
    public Integer editEngineerAwUprcs(EditAllowanceUnitPriceReq req) {
        Integer response = engineerAllowanceMapper.updateEnginnerAwUprcs(req);

        if(response > 0 && req.dsbBaseSn() > 1) {
            engineerAllowanceMapper.updatePriorEnginnerAwUprcs(req.pdGrpCd(), req.svTpCd(), req.siteAwAtcCd(), req.rglvlDvCd(), req.dsbBaseSn()-1, getStringDate(req.apyStrtdt(), -1));
        }

        return response;
    }

    /**
     * 엔지니어 수당 단가 삭제
     * @param req
     * @return
     */
    public Integer removeEngineerAwUprcs(RemoveAllowanceUnitPriceReq req) {
                Integer response = engineerAllowanceMapper.deleteEnginnerAwUprcs(req);

        if(response > 0 && req.dsbBaseSn() > 1) {
            engineerAllowanceMapper.updatePriorEnginnerAwUprcs(req.pdGrpCd(), req.svTpCd(), req.siteAwAtcCd(), req.rglvlDvCd(), req.dsbBaseSn()-1, "99991231");
        }

        return response;
    }

    /**
     * 문자열 년월일(yyyymmdd)의 일수만큼의 년월이 조회
     * @param date
     * @param differDays
     * @return
     */
    private String getStringDate(String date, int differDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(date.substring(0, 2)), Integer.parseInt(date.substring(2, 6))-1, Integer.parseInt(date.substring(6, 8)));
        calendar.add(Calendar.DATE, differDays);
		calendar.add(Calendar.DATE, -1);
		return new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
    }

}
