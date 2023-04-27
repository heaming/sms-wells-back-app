package com.kyowon.sms.wells.web.closing.standard.service;

import com.kyowon.sms.wells.web.closing.standard.converter.WdcyCloseHourBulkRegConverter;
import com.kyowon.sms.wells.web.closing.standard.dto.WdcyCloseHourBulkRegDto.CreateReq;
import com.kyowon.sms.wells.web.closing.standard.dvo.WdcyCloseHourBulkRegDvo;
import com.kyowon.sms.wells.web.closing.standard.mapper.WdcyCloseHourBulkRegMapper;
import com.sds.sflex.system.config.validation.BizAssert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

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
            if (hourBulkCount > 0) {
                BizAssert.isFalse(false, "MSG_TXT_COUNT_GREA_THAN_PRE_REGI_DEA_INFO_EXIST_PLE_WORK_AFTER_DELE");
            }

            int diffDay = this.DateConut(dto.crtDt(), dto.clDt());

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
                        String clDt = this.AddDate(dto.clDt(), 0, 1, 0);
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
                            String clDt = this.AddDate(dto.clDt(), 0, 1, 0);
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
                            String clDt = this.AddDate(dto.clDt(), 0, 1, 0);
                            dvo.setPerfDt(clDt.substring(0, 6) + "01");
                        }
                        count += mapper.insertCloseHour(dvo);

                        dvo.setClBizTpCd("12");

                        count += mapper.insertCloseHour(dvo);
                    }
                }

                addDay = this.AddDate(addDay, 0, 0, 1);
            }

            // 막날 구하기
            String endDt = this.getLastDateOfMonth(dto.clDt());

            diffDay = this.DateConut(dto.clDt(), endDt);
            addDay = dto.clDt();

            for (int i = 0; i < diffDay; i++) {

                addDay = this.AddDate(addDay, 0, 0, 1);

                dvo.setClBizTpCd("11");
                dvo.setStrtdt(addDay); // 생성일자 +1
                dvo.setStrtHh(dto.rentalRcpClNxdTmForm());   // 마감 시작일자
                dvo.setEnddt(addDay);   // 생성일자 +1
                dvo.setEndHh(dto.rentalRcpClNxdTmTo());  // 마감 종료일자
                dvo.setDtaDlYn("N");

                if ("1".equals(dto.spayRcpClDdPerfDtDvVal())) {
                    dvo.setPerfDt(dto.clDt());
                } else {
                    String clDt = this.AddDate(dto.clDt(), 0, 1, 0);
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

    private int DateConut(String crtDt, String clDt) {

        long diffDay = 0L;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date startDate = sdf.parse(crtDt);
            Date endDate = sdf.parse(clDt);

            //두날짜 사이의 시간 차이(ms)를 하루 동안의 ms(24시*60분*60초*1000밀리초) 로 나눈다.
            diffDay = (startDate.getTime() - endDate.getTime()) / (24 * 60 * 60 * 1000);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return Long.valueOf(Optional.ofNullable(diffDay * -1).orElse(0L)).intValue();
    }

    private String AddDate(String strDate, int year, int month, int day) throws Exception {

        SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");

        Calendar cal = Calendar.getInstance();

        Date dt = dtFormat.parse(strDate);

        cal.setTime(dt);

        cal.add(Calendar.YEAR, year);
        cal.add(Calendar.MONTH, month);
        cal.add(Calendar.DATE, day);

        return dtFormat.format(cal.getTime());
    }

}
