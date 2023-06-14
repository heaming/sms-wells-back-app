package com.kyowon.sms.wells.web.closing.standard.service;

import com.kyowon.sms.wells.web.closing.standard.converter.WdcyCloseHourBulkRegConverter;
import com.kyowon.sms.wells.web.closing.standard.dto.WdcyCloseHourBulkRegDto.CreateReq;
import com.kyowon.sms.wells.web.closing.standard.dvo.WdcyCloseHourBulkRegDvo;
import com.kyowon.sms.wells.web.closing.standard.mapper.WdcyCloseHourBulkRegMapper;
import com.sds.sflex.common.utils.DateUtil;
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
        mapper.deleteHourBulk(dto);

        try {

            WdcyCloseHourBulkRegDvo dvo = converter.mapCreateReqToWdcyCloseHourBulkRegDvo(dto);

            int diffDay = DateUtil.getDays(dto.crtDt(), dto.clDt());

            String addDay = dto.crtDt();

            for (int i = 0; i <= diffDay; i++) {

                if (0 == i) {

                    // 생성일자
                    dvo.setStrtdt(dto.crtDt());       // 생성일자
                    dvo.setStrtHh(dto.crtDtTmFrom()); // 생성 시작일자
                    dvo.setEnddt(dto.crtDt());        // 생성일자
                    dvo.setEndHh(dto.crtDtTmTo());    //생정 종료일자
                    dvo.setClDt(dto.crtDt());
                    dvo.setDtaDlYn("N");

                    if ("1".equals(dto.crtDtPerfDtDvVal())) {
                        dvo.setPerfDt(dto.crtDt());
                    } else {
                        String clDt = DateUtil.addMonths(dto.clDt(), 1);
                        dvo.setPerfDt(clDt.substring(0, 6) + "01");
                    }

                    if ("11".equals(dvo.getClBizTpCd()) || "12".equals(dvo.getClBizTpCd())) {

                        dvo.setClBizTpCd("11");

                        count += mapper.insertCloseHour(dvo);

                        dvo.setClBizTpCd("12");

                        count += mapper.insertCloseHour(dvo);

                    } else if ("21".equals(dvo.getClBizTpCd()) || "22".equals(dvo.getClBizTpCd())) {

                        dvo.setClBizTpCd("21");

                        count += mapper.insertCloseHour(dvo);

                        dvo.setClBizTpCd("22");
                        count += mapper.insertCloseHour(dvo);

                    } else if ("30".equals(dvo.getClBizTpCd())) {

                        count += mapper.insertCloseHour(dvo);
                    }

                } else {

                    if (i >= diffDay) {

                        //마감일/실적일자
                        dvo.setStrtdt(dto.clDt()); // 마감 일자
                        dvo.setEnddt(dto.clDt());   // 마감 일자
                        dvo.setClDt(dto.clDt());
                        dvo.setDtaDlYn("N");

                        if ("11".equals(dvo.getClBizTpCd()) || "12".equals(dvo.getClBizTpCd())) {

                            dvo.setClBizTpCd("11");
                            dvo.setStrtHh(dto.spayRcpClDdTmFrom());   // (일시불)마감일/실적일자 시작일자
                            dvo.setEndHh(dto.spayRcpClDdTmTo());  // (일시불)마감일/실적일자 종료일자
                            if ("1".equals(dto.spayRcpClDdPerfDtDvVal())) {
                                dvo.setPerfDt(dto.clDt());
                            } else {
                                String clDt = DateUtil.addMonths(dto.clDt(), 1);
                                dvo.setPerfDt(clDt.substring(0, 6) + "01");
                            }
                            count += mapper.insertCloseHour(dvo);

                            dvo.setClBizTpCd("12");
                            dvo.setStrtHh(dto.rentalRcpClDdTmFrom());   // (렌탈)마감일/실적일자 시작일자
                            dvo.setEndHh(dto.rentalRcpClDdTmTo());  // (렌탈)마감일/실적일자 종료일자
                            if ("1".equals(dto.rentalRcpClDdPerfDtDvVal())) {
                                dvo.setPerfDt(dto.clDt());
                            } else {
                                String clDt = DateUtil.addMonths(dto.clDt(), 1);
                                dvo.setPerfDt(clDt.substring(0, 6) + "01");
                            }
                            count += mapper.insertCloseHour(dvo);

                        } else if ("12".equals(dvo.getClBizTpCd()) || "22".equals(dvo.getClBizTpCd())) {

                            dvo.setClBizTpCd("21");
                            dvo.setStrtHh(dto.spayRcpClDdTmFrom());   // (일시불)마감일/실적일자 시작일자
                            dvo.setEndHh(dto.spayRcpClDdTmTo());  // (일시불)마감일/실적일자 종료일자
                            if ("1".equals(dto.spayRcpClDdPerfDtDvVal())) {
                                dvo.setPerfDt(dto.clDt());
                            } else {
                                String clDt = DateUtil.addMonths(dto.clDt(), 1);
                                dvo.setPerfDt(clDt.substring(0, 6) + "01");
                            }
                            count += mapper.insertCloseHour(dvo);

                            dvo.setClBizTpCd("22");
                            dvo.setStrtHh(dto.rentalRcpClDdTmFrom());   // (렌탈)마감일/실적일자 시작일자
                            dvo.setEndHh(dto.rentalRcpClDdTmTo());  // (렌탈)마감일/실적일자 종료일자
                            if ("1".equals(dto.rentalRcpClDdPerfDtDvVal())) {
                                dvo.setPerfDt(dto.clDt());
                            } else {
                                String clDt = DateUtil.addMonths(dto.clDt(), 1);
                                dvo.setPerfDt(clDt.substring(0, 6) + "01");
                            }
                            count += mapper.insertCloseHour(dvo);

                        } else if ("30".equals(dvo.getClBizTpCd())) {

                            count += mapper.insertCloseHour(dvo);
                        }
                    } else {

                        // 마감일자
                        dvo.setStrtdt(addDay); // 생성일자 +1
                        dvo.setEnddt(addDay);   // 생성일자 +1
                        dvo.setClDt(addDay);
                        dvo.setDtaDlYn("N");
                        dvo.setStrtHh(dto.ddClDtTmFrom());   // 마감일자
                        dvo.setEndHh(dto.ddClDtTmTo());  // 마감일자
                        dvo.setClPrdCd("3");

                        if ("1".equals(dto.ddClPerfDtDvVal())) {
                            dvo.setPerfDt(addDay);
                        } else {
                            String clDt = DateUtil.addMonths(dto.clDt(), 1);
                            dvo.setPerfDt(clDt.substring(0, 6) + "01");
                        }

                        if ("11".equals(dvo.getClBizTpCd()) || "12".equals(dvo.getClBizTpCd())) {

                            dvo.setClBizTpCd("11");
                            count += mapper.insertCloseHour(dvo);

                            dvo.setClBizTpCd("12");

                            count += mapper.insertCloseHour(dvo);
                        } else if ("21".equals(dvo.getClBizTpCd()) || "22".equals(dvo.getClBizTpCd())) {


                            dvo.setClBizTpCd("21");

                            count += mapper.insertCloseHour(dvo);


                            dvo.setClBizTpCd("22");

                            count += mapper.insertCloseHour(dvo);

                        } else if ("30".equals(dvo.getClBizTpCd())) {
                            
                            count += mapper.insertCloseHour(dvo);
                        }

                    }
                }

                addDay = DateUtil.addDays(addDay, 1);
            }

            // 막날 구하기
            String endDt = this.getLastDateOfMonth(dto.clDt());

            diffDay = DateUtil.getDays(dto.clDt(), endDt);
            addDay = dto.clDt();

            // 마감익일~말일시간/실적일자
            for (int i = 0; i < diffDay; i++) {

                addDay = DateUtil.addDays(addDay, 1);


                if ("11".equals(dvo.getClBizTpCd()) || "12".equals(dvo.getClBizTpCd())) {
                    // 마감익일~말일시간/실적일자
                    dvo.setClBizTpCd("12");
                    dvo.setStrtdt(addDay); // (일시불)마감일로 부터 달에 막날까지
                    dvo.setStrtHh(dto.rentalRcpClNxdTmForm());   // (렌탈)마감익일~말일시간/실적일자
                    dvo.setEnddt(addDay);   // 마감일로 부터 달에 막날까지
                    dvo.setEndHh(dto.rentalRcpClNxdTmTo());  // (렌탈)마감익일~말일시간/실적일자
                    dvo.setEnddt(addDay);   // 마감일로 부터 달에 막날까지
                    dvo.setClDt(addDay);
                    dvo.setDtaDlYn("N");

                    if ("1".equals(dto.rentalRcpClNxdPerfDtDvVal())) {
                        dvo.setPerfDt(dto.clDt());
                    } else {
                        String clDt = DateUtil.addMonths(dto.clDt(), 1);
                        dvo.setPerfDt(clDt.substring(0, 6) + "01");
                    }

                    count += mapper.insertCloseHour(dvo);

                    dvo.setClBizTpCd("11");
                    dvo.setStrtHh(dto.spayRcpClNxdTmFrom());   // (일시불)마감익일~말일시간/실적일자
                    dvo.setEndHh(dto.spayRcpClNxdTmTo());  // (일시불)마감익일~말일시간/실적일자

                    if ("1".equals(dto.spayRcpClNxdPerfDtDvVal())) {
                        dvo.setPerfDt(dto.clDt());
                    } else {
                        String clDt = DateUtil.addMonths(dto.clDt(), 1);
                        dvo.setPerfDt(clDt.substring(0, 6) + "01");
                    }

                    count += mapper.insertCloseHour(dvo);

                } else if ("21".equals(dvo.getClBizTpCd()) || "22".equals(dvo.getClBizTpCd())) {

                    // 마감익일~말일시간/실적일자
                    dvo.setClBizTpCd("22");
                    dvo.setStrtdt(addDay); // (일시불)마감일로 부터 달에 막날까지
                    dvo.setStrtHh(dto.rentalRcpClNxdTmForm());   // (렌탈)마감익일~말일시간/실적일자
                    dvo.setEnddt(addDay);   // 마감일로 부터 달에 막날까지
                    dvo.setEndHh(dto.rentalRcpClNxdTmTo());  // (렌탈)마감익일~말일시간/실적일자
                    dvo.setEnddt(addDay);   // 마감일로 부터 달에 막날까지
                    dvo.setClDt(addDay);
                    dvo.setDtaDlYn("N");

                    if ("1".equals(dto.rentalRcpClNxdPerfDtDvVal())) {
                        dvo.setPerfDt(dto.clDt());
                    } else {
                        String clDt = DateUtil.addMonths(dto.clDt(), 1);
                        dvo.setPerfDt(clDt.substring(0, 6) + "01");
                    }

                    count += mapper.insertCloseHour(dvo);

                    dvo.setClBizTpCd("21");
                    dvo.setStrtHh(dto.spayRcpClNxdTmFrom());   // (일시불)마감익일~말일시간/실적일자
                    dvo.setEndHh(dto.spayRcpClNxdTmTo());  // (일시불)마감익일~말일시간/실적일자

                    if ("1".equals(dto.spayRcpClNxdPerfDtDvVal())) {
                        dvo.setPerfDt(dto.clDt());
                    } else {
                        String clDt = DateUtil.addMonths(dto.clDt(), 1);
                        dvo.setPerfDt(clDt.substring(0, 6) + "01");
                    }

                    count += mapper.insertCloseHour(dvo);
                } else if ("30".equals(dvo.getClBizTpCd())) {

                    count += mapper.insertCloseHour(dvo);
                }
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
