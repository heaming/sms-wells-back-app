package com.kyowon.sms.wells.web.service.visit.service;

import com.kyowon.sflex.common.message.dvo.SmsSendReqDvo;
import com.kyowon.sflex.common.message.service.SmsMessageService;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbHealthCareSmsDvo;
import com.kyowon.sms.wells.web.service.visit.mapper.WsnbHealthCareSmsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * <pre>
 * W-SV-S-0038 건강케어 알림톡 발송 , 설치 후 다음날 발송 안마의자, 웰스팜, 매트리스
 * </pre>
 *
 * @author gs.piit122 김동엽
 * @since 2023-01-13
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsnbHealthCareSmsService {

    private final WsnbHealthCareSmsMapper mapper;
    private final SmsMessageService smsMessageService;

    /**
     * 안마의자, 웰스팜, 매트리스 등 설치 후 건강케어 고객에게 알림톡으로 발송한다.
     *
     * @return 변경 개수
     */
    public int sendHealthCareSms() throws Exception {
        final AtomicInteger updateCount = new AtomicInteger();
        List<WsnbHealthCareSmsDvo> rows = mapper.selectHealthCareSms();
        String destinfo = "";
        String subject = "";
        StringBuilder contents = null;
        final String callback = "1588-4113";
        for (WsnbHealthCareSmsDvo row : rows) {
            String yn = row.getPifThpOfrAgYn();
            if (row.getPifThpOfrAgYn() == null)
                yn = "N";
            if (yn.equalsIgnoreCase("Y")) {
                subject = "[웰스] Wells 건강케어서비스 가입 신청 완료 안내";
                contents = new StringBuilder();
                contents.append("안녕하세요. ").append(row.getCstFnm()).append(" 고객님!");
                contents.append("고객님께서 신청하신 Wells 건강케어서비스 신청이 완료 되었습니다. \n");
                contents.append("본 서비스는 영업일 기준 2일 후부터 이용 가능합니다. \n");
                contents.append("\n");
                contents.append("▶ 신청서 확인 \n");
                contents.append("☞ Wells 건강케어서비스 신청 상세내역 바로가기 (URL 링크 클릭) \n");
                contents.append("http://kiwi-m.kyowon.co.kr/KIWI-M/upload_file/upload_file/healthcare/");
                contents.append(row.getCntrNo()).append(".jpg \n");
                contents.append("\n");
                contents.append("▶ 서비스 안내 \n");
                contents.append("☞ Wells 건강케어서비스 상세 안내 (URL 링크 클릭) \n");
                contents.append("https://m.kyowonwells.com:4440/voucher/index.html \n");
                contents.append("※ 링크 클릭 시 데이터가 소모 됩니다. \n");
                contents.append("※ 본 서비스는 계약하신 상품 종류에 따라 무상으로 제공되는 기간이 다를 수 있습니다. \n");
                contents.append("※ 본 서비스 무료 이용 기간에 대한 상세한 내용은 Wells 홈페이지 또는 고객센터를 통해 확인 부탁드립니다 \n");
                contents.append("※ 본 서비스는 회사의 사정으로 중단 또는 대체될 수 있습니다. \n");
                contents.append("\n");
                contents.append("문의: Wells고객센터 (").append(callback).append(") \n");
            } else {
                subject = "[웰스] Wells 건강케어서비스 가입 안내";
                contents = new StringBuilder();
                contents.append("안녕하세요. ").append(row.getCstFnm()).append(" 고객님! \n");
                contents.append("고객님의 소중한 건강을 지켜드리는 Wells건강케어서비스를 이용해보세요! \n");
                contents.append("Wells 건강케어서비스는 ").append(row.getCstFnm()).append("님께 무상으로 제공되는 서비스입니다. \n");
                contents.append("서비스 이용에 동의하시고 Wells 건강케어 서비스를 이용해보세요. \n");
                contents.append("\n");
                contents.append("▶ Wells 건강케어서비스 이용 동의 (URL 링크 클릭) \n");
                contents.append("http://kiwi-m.kyowon.co.kr/KIWI-W/nosession_health_care_cubigcc.do?CSMR_YR=");
                contents.append(row.getCntrNo()).append(" \n");
                contents.append("\n");
                contents.append("▶ Wells 건강케어서비스 상세 안내 (URL 링크 클릭) \n");
                contents.append("https://m.kyowonwells.com:4440/voucher/index.html \n");
                contents.append("※ 링크 클릭 시 데이터가 소모됩니다. \n");
                contents.append("※ 본 서비스는 이용 동의 완료 후 영업일 기준 2일 후부터 이용 가능합니다. \n");
                contents.append("※ 본 서비스는 계약하신 상품 종류에 따라 무상으로 제공되는 기간이 다를 수 있습니다. \n");
                contents.append("※ 본 서비스 무료 이용 기간에 대한 상세한 내용은 Wells 홈페이지 또는 고객센터를 통해 확인 부탁드립니다 \n");
                contents.append("※ 본 서비스는 회사의 사정으로 중단 또는 대체될 수 있습니다. \n");
                contents.append("감사합니다. \n");
                contents.append("\n");
                contents.append("문의: Wells고객센터 (").append(callback).append(") \n");
            }
            updateCount.addAndGet(
                smsMessageService.sendMessage(
                    SmsSendReqDvo.builder()
                        .subject(subject)
                        .msgContent(contents.toString())
                        .destInfo(row.getCstNm().concat("^").concat(row.getCphonIdvTno()))
                        .callback(callback.replaceAll("-", ""))
                        .build()
                )
            );
        }
        return updateCount.get();
    }

}
