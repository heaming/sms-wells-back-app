package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sflex.common.message.dvo.EmailSendReqDvo;
import com.kyowon.sflex.common.message.service.EmailService;
import com.kyowon.sms.wells.web.contract.ordermgmt.converter.WctaSpectxBlkPrntConverter;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaSpectxBlkPrntDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaSpectxBlkPrntDto.SaveReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaSpectxBlkPrntDto.SearchCntrRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaSpectxBlkPrntDto.SearchReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaSpectxBlkPrntDto.SearchRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaSpectxBlkPrntDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaSpectxBlkPrntMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WctaSpectxBlkPrntService {
    private final WctaSpectxBlkPrntMapper mapper;
    private final WctaSpectxBlkPrntConverter converter;
    private final EmailService emailService;

    public List<SearchRes> getSpectxBlkPrnts(SearchReq dto) {
        return mapper.selectSpectxBlkPrnts(dto);
    }

    public List<SearchRes> getSpectxBlkPrntsExcelDwonload(SearchReq dto) {
        return mapper.selectSpectxBlkPrnts(dto);
    }

    public SearchCntrRes getTradeSpcshCst(String cntrNo, String cntrSn) {
        return mapper.selectTradeSpcshCst(cntrNo, cntrSn);
    }

    @Transactional
    public String saveSpectxBlkPrntsGrpNo() {
        WctaSpectxBlkPrntDvo dvo = new WctaSpectxBlkPrntDvo();
        int res = mapper.insertSsctSpectxIsBas(dvo);
        return dvo.getSpectxGrpNo();
    }

    @Transactional
    public int saveSpectxBlkPrnts(List<SaveReq> dtos) {
        int res = 0;
        String spectxGrpNoCheck = "";
        for (SaveReq dto : dtos) {
            WctaSpectxBlkPrntDvo dvo = converter.mapSaveReqToWctaSpectxBlkPrntDvo(dto);

            switch (dto.rowState()) {
                case CommConst.ROW_STATE_CREATED -> {
                    if (dvo.getSpectxGrpNo() != spectxGrpNoCheck) {
                        spectxGrpNoCheck = dvo.getSpectxGrpNo();
                        mapper.updateSsctSpectxIsBasFirst(dvo);
                        mapper.insertSsctSpectxIsChHist(dvo.getSpectxGrpNo());
                    }
                    int result = mapper.insertSsctSpectxIsDtl(dvo);
                    mapper.insertSsctSpectxPblHist(dvo.getSpectxGrpNo(), dvo.getCntrNo(), dvo.getCntrSn());
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
                }
                case CommConst.ROW_STATE_UPDATED -> {
                    if (dvo.getSpectxGrpNo() != spectxGrpNoCheck) {
                        spectxGrpNoCheck = dvo.getSpectxGrpNo();
                        mapper.insertSsctSpectxIsChHist(dvo.getSpectxGrpNo());
                        mapper.updateSsctSpectxIsBas(dvo);
                    }
                    mapper.insertSsctSpectxPblHist(dvo.getSpectxGrpNo(), dvo.getCntrNo(), dvo.getCntrSn());
                    int result = mapper.updateSsctSpectxIsDtl(dvo);
                    BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
                }
            }
            ;
        }
        return res;
    }

    @Transactional
    public int removeSpectxBlkPrnts(List<SaveReq> dtos) {
        int res = 0;
        String spectxGrpNoCheck = "";
        for (SaveReq dto : dtos) {
            WctaSpectxBlkPrntDvo dvo = converter.mapSaveReqToWctaSpectxBlkPrntDvo(dto);
            if (dvo.getSpectxGrpNo() != spectxGrpNoCheck) {
                spectxGrpNoCheck = dvo.getSpectxGrpNo();
                mapper.insertSsctSpectxIsChHist(dvo.getSpectxGrpNo());
                mapper.deleteSsctSpectxIsBas(dvo.getSpectxGrpNo());
                mapper.insertSsctSpectxIsChHist(dvo.getSpectxGrpNo());
            }
            mapper.insertSsctSpectxPblHist(dvo.getSpectxGrpNo(), dvo.getCntrNo(), dvo.getCntrSn());
            mapper.deleteSsctSpectxIsDtl(dvo.getSpectxGrpNo(), dvo.getCntrNo(), dvo.getCntrSn());
            mapper.insertSsctSpectxPblHist(dvo.getSpectxGrpNo(), dvo.getCntrNo(), dvo.getCntrSn());
        }
        return res;
    }

    public List<WctaSpectxBlkPrntDto.SpectxFwRes> getTradeSpcshFwInqrs(WctaSpectxBlkPrntDto.SpectxFwReq dto) {
        return mapper.selectTradeSpcshFwInqrs(dto);
    }

    public int saveTradeSpcshFws(List<WctaSpectxBlkPrntDto.SaveTradeSpcshFwReq> dtos) throws Exception {
        int res = 0;
        for (WctaSpectxBlkPrntDto.SaveTradeSpcshFwReq dto : dtos) {
            WctaSpectxBlkPrntDvo dvo = converter.mapSaveTradeSpcshFwReqToWctaSpectxBlkPrntDvo(dto);

            String sysDate = DateUtil.getNowString();
            String decStr = "";
            decStr = dvo.getSlClYm() + "01/" + dvo.getSlClYm() + "/" + dvo.getDpTpCd() + "// " + dvo.getCstNm() + "/"
                + dvo.getSpectxGrpNo();
            String encStr = Base64.encodeBase64String(decStr.getBytes());
            encStr = encStr.replaceAll("/", "slash");
            String perdStr = sysDate.substring(0, 4) + "년 "
                + sysDate.substring(4, 6) + "월";
            String pdfUrl = "http://wellsorder.kyowon.co.kr/specView/" + encStr;

            String title = "WELLS 거래명세서 안내메일";
            String content = "<!DOCTYPE html>";
            content += "<html>";
            content += "<head>";
            content += "    <title>WELLS 거래명세서 안내메일</title>";
            content += "    <meta charset='UTF-8' />";
            content += "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">";
            content += "    <meta name='viewport' content='width=device-width, initial-scale=1, maximum-scale=1'>";
            content += "</head>";
            content += "<body>";
            content += "<div style=\"width:100%; font-size: 14px;margin: 0;padding: 0;box-sizing: border-box; font-family: '나눔고딕', nanumgothic, '맑은 고딕', Malgun Gothic, Dotum, '돋움', Arial, '굴림', Gulim;line-height: normal;background: rgb(255, 255, 255);\">";
            content += "	<div style=\"max-width: 796px;margin: 0;padding: 0;width: 100%; _width:796px;height: auto;position: relative;overflow: hidden;border: 8px solid #004d79;\">";
            content += "        <table >";
            content += "            <colgroup>";
            content += "                <col width=\"40%\">";
            content += "                <col width=\"60%\">";
            content += "            </colgroup>";
            content += "            <tbody>";
            content += "                <tr>";
            content += "                 <td style=\"padding: 15px 20px;\"><img src=\"http://wellsorder.kyowon.co.kr/resources/mail/resources/image/wells-logo.png\" alt=\"자연을 담은 Wells\"></td>";
            content += "                    <td style=\"padding: 15px 20px; text-align: right\">";
            content += "                        <span>";
            content += "                            <a href=\"http://www.kyowonwells.com/\" target=\"_blink\">";
            content += "                                <img style=\"border: 0\" src=\"http://wellsorder.kyowon.co.kr/resources/mail/resources/image/icon-home.png\" alt=\"home바로가기\"></a>";
            content += "                        </span>";
            content += "                        <span>";
            content += "                            <a href=\"http://story.kakao.com/ch/kyowonwells\" target=\"_blink\">";
            content += "                                <img style=\"border: 0\" src=\"http://wellsorder.kyowon.co.kr/resources/mail/resources/image/icon-kakaostory.png\" alt=\"카카오스토리바로가기\"></a>";
            content += "                        </span>";
            content += "                        <span style=\"display: inline-block;margin: 0 0px;width: 35px;\">";
            content += "                            <a href=\"http://blog.naver.com/kyowon_wells\" target=\"_blink\">";
            content += "                                <img style=\"border: 0\" src=\"http://wellsorder.kyowon.co.kr/resources/mail/resources/image/icon-blog.png\" alt=\"블로그바로가기\"></a>";
            content += "                        </span>";
            content += "                        <span style=\"display: inline-block;margin: 0 0px;width: 35px;\">";
            content += "                            <a href=\"https://www.youtube.com/channel/UCGmCXue8o7pY6fJh4K8GvQg\" target=\"_blink\">";
            content += "                                <img style=\"border: 0\" src=\"http://wellsorder.kyowon.co.kr/resources/mail/resources/image/icon-youtube.png\" alt=\"유투브바로가기\"></a>";
            content += "                        </span>";
            content += "                    </td>";
            content += "                </tr>";
            content += "            </tbody>";
            content += "        </table>";
            content += "        <div style=\"position: relative;overflow: hidden;padding: 20px 20px;text-align: center;\">";
            content += "            <div style=\"margin: 25px auto;text-align: center;max-width: 350px;\">";
            content += "                <div style=\"font-size: 22px;color: #555;font-weight: bold;\">거래명세서 안내메일입니다.</div>";
            content += "                <div style=\"font-size: 16px;margin-top: 25px;color: #444;line-height: 1.4;\">";
            content += "                    <p style=\"display: block;line-height: 1.6;margin: 0;padding: 0;\">안녕하세요? 고객님</p>";
            content += "                    <p style=\"display: block;line-height: 1.6;margin: 0;padding: 0;\"> wells상품을 구매해주셔서 감사합니다! <p>";
            content += "                    <p style=\"display: block;line-height: 1.6;margin: 0;padding: 0;\">"
                + perdStr
                + " 거래명세서 안내해 드리겠습니다.</p>";
            content += "                </div>";
            content += "            </div>";
            content += "            <div align=\"center\">";
            content += "                <a style=\"text-decoration: none\" href=\"" + pdfUrl + "\">";
            content += "                    <div style=\"display: block;vertical-align: middle;text-align: center;padding: 20px 20px;max-width: 500px;font-size: 32px;font-weight: bold;line-height: 1.33;width: 100%;  _width:500px; color: #fff;background-color: #004d79;\">거래명세서 확인하기</div>";
            content += "                </a>";
            content += "            </div>";
            content += "        </div>";
            content += "        <div style=\"padding: 0;margin: 0;position: relative;overflow: hidden;background: #515056;display: block; margin-bottom: -3px; _height: 262px \">";
            content += "            <div style=\"padding: 0;margin: 0;text-align: center;float: left;width: 33.33333333333333%;_height: 262px\">";
            content += "                <a style=\"padding: 0;margin: 0; color: #333;text-decoration: none;cursor: pointer;\" href=\"http://www.kyowonwells.com/WellsSpecial/Farm\" target=\"_blink\">";
            content += "                    <img style=\"width: 100%;height: auto;padding: 0;margin: 0; border: 0\" src=\"http://wellsorder.kyowon.co.kr/resources/mail/resources/image/banner031.jpg\" alt=\"웰스팜 공유렌탈\">";
            content += "                </a>";
            content += "            </div>";
            content += "            <div style=\"padding: 0;margin: 0;text-align: center;float: left;width: 33.33333333333333%;_height: 262px\">";
            content += "                    <img style=\"width: 100%;height: auto;padding: 0;margin: 0;\" src=\"http://wellsorder.kyowon.co.kr/resources/mail/resources/image/banner02.jpg\" alt=\"고객센터1588-4113\">";
            content += "            </div>";
            content += "            <div style=\"padding: 0;margin: 0;text-align: center;float: left;width: 33.33333333333333%;_height: 262px\">";
            content += "                <a style=\"padding: 0;margin: 0; color: #333;text-decoration: none;cursor: pointer;\" href=\"http://www.kyowonwells.com/Customer/Consulting/B2C?prodNm=WN652&pdType\" target=\"_blink\">";
            content += "                    <img style=\"width: 100%;height: auto;padding: 0;margin: 0; border: 0\" src=\"http://wellsorder.kyowon.co.kr/resources/mail/resources/image/banner011.jpg\" alt=\"웰스 제품 구매문의\">";
            content += "                </a>";
            content += "            </div>";
            content += "        </div>";
            content += "        <div style=\"text-align: center;background: #acafb4;position: relative;overflow: hidden;padding: 25px 20px 35px 20px;\">";
            content += "            <div style=\"text-align: center;color: #fff;font-size: 13px;line-height: 1.43;\">";
            content += "                <div><img src=\"http://wellsorder.kyowon.co.kr/resources/mail/resources/image/kyowon-ci.png\" alt='교원'></div>";
            content += "                <div style=\"margin-top: 5px;font-size: 13px;color: #fff;\">";
            content += "                    본 메일은 발신전용으로 회신되지 않으며, 문의사항은 고객센터 1588-4113을 이용해 주세요.<br />";
            content += "                    서울시 중구 을지로 51(을지로2가 6번지) ㈜교원프라퍼티 대표이사 장평순 | 사업자등록번호 101-81-39767";
            content += "                </div>";
            content += "                <div style=\"color: #fff;font-size: 11px;line-height: 1.43;\">COPYRIGHT(C) KYOWON, ALL RIGHTS RESERVED.</div>";
            content += "            </div>";
            content += "        </div>";
            content += "    </div>";
            content += "</body>";
            content += "</html>";
            String receiveEmail = dvo.getEmadr();

            EmailSendReqDvo sendDvo = EmailSendReqDvo.builder()
                .title(title)
                .content(content)
                .receiveUsers(List.of(EmailSendReqDvo.ReceiveUser.fromEmail(receiveEmail)))
                .build();

            String emailUid = emailService.sendEmail(sendDvo);

            mapper.insertSsctSpectxPblHistSend(dvo.getSpectxGrpNo(), dvo.getCntrNo(), dvo.getCntrSn());
            res += mapper.updateSsctSpectxIsDtlChSn(dvo.getSpectxGrpNo(), dvo.getCntrNo(), dvo.getCntrSn());
        }

        return res;
    }
}
