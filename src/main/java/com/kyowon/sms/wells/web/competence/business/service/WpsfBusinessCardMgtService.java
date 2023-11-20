package com.kyowon.sms.wells.web.competence.business.service;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sflex.common.message.dvo.SmsSendReqDvo;
import com.kyowon.sflex.common.message.service.SmsMessageService;
import com.kyowon.sflex.common.system.service.UrlShortenerService;
import com.kyowon.sms.common.web.deduction.zcommon.constant.DeDeductionConst;
import com.kyowon.sms.wells.web.competence.business.converter.WpsfBusinessCarMgtConverter;
import com.kyowon.sms.wells.web.competence.business.dto.WpsfBusinessCardMgtDto;
import com.kyowon.sms.wells.web.competence.business.dvo.WpsfPartnerContactBaseDvo;
import com.kyowon.sms.wells.web.competence.business.dvo.WpsfPartnerCustomerContactBaseDvo;
import com.kyowon.sms.wells.web.competence.business.mapper.WpsfBusinessCardMgtMapper;
import com.sds.sflex.common.docs.service.AttachFileService;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.core.util.IDGenUtil;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WpsfBusinessCardMgtService {
    private final WpsfBusinessCardMgtMapper mapper;
    private final WpsfBusinessCarMgtConverter converter;
    private final AttachFileService attachFileService;
    private final UrlShortenerService shorterService;
    private final SmsMessageService smsMessageService;
    private final static String groupId = "ATG_PSF_BCD_IMG";

    public PagingResult<WpsfBusinessCardMgtDto.SearchRes> getPartnerCustomerContactBasePages(
        WpsfBusinessCardMgtDto.SearchReq dto, PageInfo pageInfo
    ) {
        PagingResult<WpsfPartnerCustomerContactBaseDvo> dvos = mapper
            .selectPartnerCustomerContactBasePages(dto, pageInfo);
        PageInfo newPage = dvos.getPageInfo();
        PagingResult<WpsfBusinessCardMgtDto.SearchRes> res = null;

        if (dvos.getList().size() > 0) {
            for (WpsfPartnerCustomerContactBaseDvo dvo : dvos) {
                String base64Image = attachFileService.convertAttachFileToBase64(dvo.getFileUid());
                dvo.setRealFpath(base64Image);
            }
        }

        res = converter.mapWpsfPartnerCustomerContactBaseDvoToSearchRes(dvos);
        res.setPageInfo(newPage);
        return res;

    }

    public List<WpsfBusinessCardMgtDto.SearchRes> getPartnerCustomerContactBase(
        WpsfBusinessCardMgtDto.SearchReq dto
    ) {
        List<WpsfBusinessCardMgtDto.SearchRes> res = null;
        List<WpsfPartnerCustomerContactBaseDvo> dvos = mapper.selectPartnerCustomerContactBasePages(dto);
        if (null != dvos) {
            for (WpsfPartnerCustomerContactBaseDvo dvo : dvos) {
                String base64Image = attachFileService.convertAttachFileToBase64(dvo.getFileUid());
                dvo.setRealFpath(base64Image);
            }
        }
        res = converter.mapWpsfPartnerCustomerContactBaseDvoListToSearchRes(dvos);
        return res;
    }

    /**
     * 명함첩 연락처 저장,수정
     *
     * @param dto
     * @return processCount
     */
    @Transactional
    public int editReqPartnerCustomerContactBase(WpsfBusinessCardMgtDto.EditReq dto) throws Exception {
        int processCount = 0;

        WpsfPartnerCustomerContactBaseDvo dvo = converter.mapSaveReq(dto);
        String fileId = IDGenUtil.getUUID("PSF");
        dvo.setDtaDlYn(DeDeductionConst.DELETE_N);
        if (dto.ctplcSn() == null) {
            int ctplcSn = mapper.selectMaxCtplcSn();
            dvo.setCtplcSn(ctplcSn);
            dvo.setCtplcImgFileId(fileId);
            processCount = mapper.insertPartnerCustomerContactBase(dvo);
        } else {
            processCount = mapper.updatePartnerCustomerContactBase(dvo);
        }
        if (CollectionUtils.isNotEmpty(dto.attachFiles1())) {
            attachFileService.saveAttachFiles(groupId, dvo.getCtplcImgFileId(), dto.attachFiles1());
            //BizAssert.isTrue(count > 0, "MSG_ALT_SVE_ERR");
        }
        return processCount;

    }

    /**
     * 명함첩 연락처 삭제
     *
     * @param ctplcSn
     * @return processCount
     */
    @Transactional
    public int removePartnerCustomerContactBase(String ctplcSn) {
        int processCount = 0;
        WpsfPartnerCustomerContactBaseDvo dvo = new WpsfPartnerCustomerContactBaseDvo();
        dvo.setCtplcSn(Integer.parseInt(ctplcSn));
        dvo.setDtaDlYn(DeDeductionConst.DELETE_Y);
        processCount = mapper.updatePartnerCustomerContactBaseDtaDlyn(dvo);
        return processCount;

    }

    /**
     * 내명함등록,수정
     *
     * @param dto
     * @return processCount
     */
    @Transactional
    public int savePartnerContactBase(WpsfBusinessCardMgtDto.EditPartnerReq dto) throws Exception {
        int processCount = 0;
        WpsfPartnerContactBaseDvo dvo = converter.mapEditPartnerReq(dto);
        String fileId = IDGenUtil.getUUID("PSF");
        int cnt = mapper.selectCountPartnerContactBase();
        dvo.setDtaDlYn(DeDeductionConst.DELETE_N);
        if (cnt == 0) {
            dvo.setCtplcImgFileId(fileId);
            processCount = mapper.insertPartnerContactBase(dvo);
        } else {
            processCount = mapper.updatePartnerContactBase(dvo);
        }

        if (Objects.nonNull(dto.attachFiles())) {
            attachFileService.saveAttachFile(groupId, dvo.getCtplcImgFileId(), dto.attachFiles());
        }
        /*
        BufferedImage image2 = ImageIO.read(new File("C:/Users/user001/Desktop/wells_logo.png"));

        int width = 960;
        int height = 543;

        //BufferedImage mergedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        BufferedImage mergedImage = ImageIO.read(new File("C:/Users/user001/Desktop/template.png")); // 배경 파일 불러오기
        Graphics2D graphics = (Graphics2D)mergedImage.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.drawImage(image2, 50, 250, null);
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("NanumGothic", Font.BOLD, 18));
        graphics.drawString("유지성", 200, 100); // 문자열 삽입
        graphics.setFont(new Font("NanumGothic", Font.PLAIN, 15));
        graphics.drawString("서부촐괄단/지점장", 200, 120); // 문자열 삽입
        graphics.setFont(new Font("NanumGothic", Font.BOLD, 18));
        graphics.drawString("교원 KYOWON", 200, 140); // 문자열 삽입
        graphics.setFont(new Font("NanumGothic", Font.PLAIN, 15));
        graphics.drawString("Mobile.010-8921-1782", 200, 160); // 문자열 삽입

        ImageIO.write(mergedImage, "png", new File("C:/Users/user001/Desktop/mergedImage.png"));
        */
        return processCount;
    }

    /**
     *  내명함 조회
     * @param dto
     * @return
     */
    public WpsfBusinessCardMgtDto.SearchPartnerRes getPartnerContactBase(WpsfBusinessCardMgtDto.SearchReq dto) {
        WpsfBusinessCardMgtDto.SearchPartnerRes res = null;
        WpsfPartnerContactBaseDvo dvo = mapper.selectPartnerContactBase(dto);
        if (null != dvo) {
            String base64Image = attachFileService.convertAttachFileToBase64(dvo.getFileUid());
            dvo.setRealFpath(base64Image);
            res = converter.mapWWpsfPartnerContactBaseDvoToSearchPreviewRes(dvo);
        } else {
            res = mapper.selectPartnerInfo();
        }
        return res;
    }

    /**
     *  내명함 발송
     * @param dtos
     * @return
     * @throws Exception
     */
    public int sendPartnerContactBase(List<WpsfBusinessCardMgtDto.SendReq> dtos) throws Exception {
        UserSessionDvo userSession = SFLEXContextHolder.getContext().getUserSession();
        int result = 0;
        // 메시지 발송

        String smsUrl = "/anonymous/login?deviceCheck=Y&redirectUrl=";
        String redirectUrl = "/#/competence/wwpsf-business-card-preview?ogTpCd=" + userSession.getOgTpCd()
            + "&prtnrNo=" + userSession.getEmployeeIDNumber();

        //URL 인코딩 후 URL Short처리
        String vacUrl = smsUrl + URLEncoder.encode(redirectUrl);
        String preview = shorterService.getShortedUrl(vacUrl);

        for (WpsfBusinessCardMgtDto.SendReq dto : dtos) {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("fnm", dto.fnm());
            paramMap.put("prtnrKnm", userSession.getUserName());
            paramMap.put("hp", dto.cellphone());
            paramMap.put("preview", preview);
            SmsSendReqDvo sendReqDvo = SmsSendReqDvo.withTemplateId()
                .templateId("TMP_PSF_CTPLC_1")
                .templateParamMap(paramMap)
                .destInfo(dto.fnm() + "^" + dto.phone())
                .build();
            result = smsMessageService.sendMessage(sendReqDvo);
        }

        return result;
    }

}
