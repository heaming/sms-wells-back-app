package com.kyowon.sms.wells.web.closing.standard.service;

import com.kyowon.sms.wells.web.closing.standard.converter.WdcyCloseHourBulkRegConverter;
import com.kyowon.sms.wells.web.closing.standard.dto.WdcyCloseHourBulkRegDto.CreateReq;
import com.kyowon.sms.wells.web.closing.standard.dvo.WdcyCloseHourBulkRegDvo;
import com.kyowon.sms.wells.web.closing.standard.mapper.WdcyCloseHourBulkRegMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.validation.BizAssert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

@RequiredArgsConstructor
@Service
public class WdcyCloseHourBulkRegService {

    private final WdcyCloseHourBulkRegMapper mapper;
    private final WdcyCloseHourBulkRegConverter converter;

    @Transactional
    public int createCloseHour(CreateReq dto) {

        int count = 0;
        try {

            WdcyCloseHourBulkRegDvo dvo = converter.mapCreateReqToWdcyCloseHourBulkRegDvo(dto);
            int hourBulkCount = mapper.selectHourBulkCount(dto);
            BizAssert.isTrue(hourBulkCount <= 0, "MSG_TXT_COUNT_GREA_THAN_PRE_REGI_DEA_INFO_EXIST_PLE_WORK_AFTER_DELE");

            int diffDay = DateUtil.getDays(dto.crtDt(), dto.clDt());

            Calendar cal = Calendar.getInstance();
            String addDay = dto.crtDt();

            for (int i = 0; i <= diffDay; i++) {

                if (0 == i) {

                    dvo.setClBizTpCd("11");
                    dvo.setStrtdt(dto.crtDt());       // 생성일자
                    dvo.setStrtHh(dto.crtDtTmFrom()); // 생성 시작일자
                    dvo.setEnddt(dto.crtDt());        // 생성일자
                    dvo.setEndHh(dto.crtDtTmTo());    //생정 종료일자
                    dvo.setDtaDlYn("N");

                    if ("1".equals(dto.crtDtPerfDtDvVal())) {
                        dvo.setPerfDt(dto.clDt());
                    } else {
                        String clDt = DateUtil.addMonths(dto.clDt(), 1);
                        dvo.setPerfDt(clDt.substring(0, 6) + "01");
                    }
                    count += mapper.insertCloseHour(dvo);

                    dvo.setClBizTpCd("12");

                    count += mapper.insertCloseHour(dvo);


                } else {

                    if (i >= diffDay) {

                        dvo.setClBizTpCd("11");
                        dvo.setStrtdt(addDay); // 마감 일자
                        dvo.setStrtHh(dto.rentalRcpClNxdTmForm());   // 마감 시작일자
                        dvo.setEnddt(addDay);   // 마감 일자
                        dvo.setEndHh(dto.rentalRcpClNxdTmTo());  // 마감 종료일자
                        dvo.setDtaDlYn("N");

                        if ("1".equals(dto.rentalRcpClDdPerfDtDvVal())) {
                            dvo.setPerfDt(dto.clDt());
                        } else {
                            String clDt = DateUtil.addMonths(dto.clDt(), 1);
                            dvo.setPerfDt(clDt.substring(0, 6) + "01");
                        }
                        count += mapper.insertCloseHour(dvo);

                        dvo.setClBizTpCd("12");

                        count += mapper.insertCloseHour(dvo);

                    } else {

                        dvo.setClBizTpCd("11");
                        dvo.setStrtdt(addDay); // 생성일자 +1
                        dvo.setStrtHh(dto.crtDtTmFrom());   // 마감 시작일자
                        dvo.setEnddt(addDay);   // 생성일자 +1
                        dvo.setEndHh(dto.crtDtTmTo());  // 마감 종료일자
                        dvo.setDtaDlYn("N");

                        if ("1".equals(dto.ddClPerfDtDvVal())) {
                            dvo.setPerfDt(dto.clDt());
                        } else {
                            String clDt = DateUtil.addMonths(dto.clDt(), 1);
                            dvo.setPerfDt(clDt.substring(0, 6) + "01");
                        }
                        count += mapper.insertCloseHour(dvo);

                        dvo.setClBizTpCd("12");

                        count += mapper.insertCloseHour(dvo);
                    }
                }

                addDay = DateUtil.addDays(addDay, 1);
            }

            // 막날 구하기
            String endDt = this.getLastDateOfMonth(dto.clDt());

            diffDay = DateUtil.getDays(dto.clDt(), endDt);
            addDay = dto.clDt();

            for (int i = 0; i < diffDay; i++) {

                addDay = DateUtil.addDays(addDay, 1);

                dvo.setClBizTpCd("11");
                dvo.setStrtdt(addDay); // 생성일자 +1
                dvo.setStrtHh(dto.rentalRcpClNxdTmForm());   // 마감 시작일자
                dvo.setEnddt(addDay);   // 생성일자 +1
                dvo.setEndHh(dto.rentalRcpClNxdTmTo());  // 마감 종료일자
                dvo.setDtaDlYn("N");

                if ("1".equals(dto.spayRcpClDdPerfDtDvVal())) {
                    dvo.setPerfDt(dto.clDt());
                } else {
                    String clDt = DateUtil.addMonths(dto.clDt(), 1);
                    dvo.setPerfDt(clDt.substring(0, 6) + "01");
                }

                count += mapper.insertCloseHour(dvo);

                dvo.setClBizTpCd("12");

                count += mapper.insertCloseHour(dvo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    private String getLastDateOfMonth(String yyyyMMdd) {
        String year = yyyyMMdd.substring(0, 4);
        String month = yyyyMMdd.substring(4, 6);

        Calendar cal = Calendar.getInstance();
        cal.set(Integer.parseInt(year), Integer.parseInt(month) - 1, 1);

        String lastDay = year + month + Integer.toString(cal.getActualMaximum(Calendar.DAY_OF_MONTH));

        return lastDay;
    }
}
