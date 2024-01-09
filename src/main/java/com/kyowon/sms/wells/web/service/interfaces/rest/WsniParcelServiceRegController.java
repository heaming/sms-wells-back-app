package com.kyowon.sms.wells.web.service.interfaces.rest;

import static com.kyowon.sms.wells.web.service.interfaces.service.WsniParcelServiceRegService.TMP_PRC_SRV_REG_CUST_ID;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.validation.Valid;
import com.kyowon.sms.wells.web.service.visit.service.WsnbWorkOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.kyowon.sms.wells.web.service.visit.dvo.WsnbWorkOrderDvo;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.webclient.ivo.EaiWrapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.kyowon.sms.wells.web.service.interfaces.converter.WsniParcelServiceConverter;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniParcelServiceRegDto;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniParcelServiceRegDvo;
import com.kyowon.sms.wells.web.service.interfaces.ivo.EAI_OCJM1_WSVO1003.response.AddressCleansingResultData;
import com.kyowon.sms.wells.web.service.interfaces.ivo.EAI_OCJM1_WSVO1003.response.PcsvAddressCleansingResIvo;
import com.kyowon.sms.wells.web.service.interfaces.service.WsniParcelServiceRegService;
import com.sds.sflex.system.config.annotation.InterfaceController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;


/**
* OCJM1_CSVO1001 - CJ택배 인증키 생성, OCJM1_CSVO1002 - CJ택배 반품 집하 지시
* */
@Api(tags = "CJ택배 집하지시6 REST API")
@InterfaceController
@RequestMapping(SnServiceConst.REST_INTERFACE_URL_V1 + "/parcel-service")
@RequiredArgsConstructor
public class WsniParcelServiceRegController {

    private final WsniParcelServiceRegService parcelServiceRegService;

    private final WsnbWorkOrderService workOrderService;

    private final WsniParcelServiceConverter parcelServiceConverter;

    private static final Logger logger = LoggerFactory.getLogger(WsniParcelServiceRegController.class);

    @ApiOperation(value = "집하지시 오더저장", notes = "집하지시 오더를 생성/취소한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "reqdvCd", value = "요청구분코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "custNo", value = "계약번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "cntrSn", value = "계약차수", paramType = "query", required = true),
        @ApiImplicitParam(name = "sendrAddr", value = "발송주소", paramType = "query", required = true),
        @ApiImplicitParam(name = "sendrAddrDtl", value = "발송주소상세", paramType = "query", required = true),
        @ApiImplicitParam(name = "sendrtelNo1", value = "발송전번1", paramType = "query", required = false),
        @ApiImplicitParam(name = "sendrtelNo2", value = "발송전번2", paramType = "query", required = false),
        @ApiImplicitParam(name = "sendrtelNo3", value = "발송전번3", paramType = "query", required = false),
        @ApiImplicitParam(name = "sendrcellNo1", value = "발송핸폰1", paramType = "query", required = true),
        @ApiImplicitParam(name = "sendrcellNo2", value = "발송핸폰2", paramType = "query", required = true),
        @ApiImplicitParam(name = "sendrcellNo3", value = "발송핸폰3", paramType = "query", required = true),
        @ApiImplicitParam(name = "sendrZipNo", value = "발송우편번호", paramType = "query", required = true),
        @ApiImplicitParam(name = "gdsCd", value = "제품코드", paramType = "query", required = true),
        @ApiImplicitParam(name = "gdsNm", value = "상품명", paramType = "query", required = false),
        @ApiImplicitParam(name = "gdsQty", value = "상품수량", paramType = "query", required = true),
        @ApiImplicitParam(name = "sendrNm", value = "발송자명", paramType = "query", required = true),
        @ApiImplicitParam(name = "recpId", value = "접수자ID", paramType = "query", required = true),
        @ApiImplicitParam(name = "recpOgTpCd", value = "접수자조직유형", paramType = "query", required = true),
    })
    @PostMapping("/regist")
    public EaiWrapper saveParcelServiceRegistration(
        @Valid
        @RequestBody
        EaiWrapper<WsniParcelServiceRegDto.RegistParcelServiceReq> reqEaiWrapper
    ) throws Exception {

        EaiWrapper<PcsvAddressCleansingResIvo> resEaiWrapper = reqEaiWrapper.newResInstance();

        WsniParcelServiceRegDto.RegistParcelServiceReq dto = reqEaiWrapper.getBody();
        WsniParcelServiceRegDvo save_data = parcelServiceConverter.mapRgstPrclServiceReqDtoToRgstPrclServiceReqDvo(dto);

        logger.debug("요기 시작했음");
        // STEP 01. 원송장번호 조회
        List<WsniParcelServiceRegDto.SearchOriginInvoiceRes> OriginInvoicelist = parcelServiceRegService
            .getOriginInvoiceNum(dto);
        logger.debug("요기 시작했음1");
        if (OriginInvoicelist == null || OriginInvoicelist.size() <= 0) {
            throw new Exception(">> CJ예약(등록/취소) API 오류 - 원운송장번호를 고객번호로 찾을수 없습니다.");
        } else {
            WsniParcelServiceRegDto.SearchOriginInvoiceRes invc_no_data = OriginInvoicelist.get(0);

            LocalDate date = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String strToday = date.format(formatter);
            save_data.setCustId(TMP_PRC_SRV_REG_CUST_ID);
            save_data.setOriinvcNo(invc_no_data.oriinvcNo());
            save_data.setReqdvCd(dto.reqdvCd());
            save_data.setMpckKey("R"+invc_no_data.oriinvcNo()+"_"+strToday);
            save_data.setRcptDv("02");
            if (dto.reqdvCd().equals("02")) {

                save_data.setSendrAddr(invc_no_data.sendrAddr());
                save_data.setSendrAddrDtl(invc_no_data.sendrAddrDtl());
                save_data.setSendrtelNo1(invc_no_data.sendrtelNo1());
                save_data.setSendrtelNo2(invc_no_data.sendrtelNo2());
                save_data.setSendrtelNo3(invc_no_data.sendrtelNo3());
                save_data.setSendrcellNo1(invc_no_data.sendrcellNo1());
                save_data.setSendrcellNo2(invc_no_data.sendrcellNo2());
                save_data.setSendrcellNo3(invc_no_data.sendrcellNo3());
                save_data.setSendrZipNo(invc_no_data.sendrZipNo());
                save_data.setSendrNm(invc_no_data.sendrNm());
                save_data.setGdsCd(invc_no_data.gdsCd());
                save_data.setGdsNm(invc_no_data.gdsNm());
                save_data.setGdsQty(invc_no_data.gdsQty());
                save_data.setRcptYmd(invc_no_data.rcptYmd());
            } else {
                save_data.setSendrAddr(dto.sendrAddr());
                save_data.setSendrAddrDtl(dto.sendrAddrDtl());
                save_data.setSendrtelNo1(dto.sendrtelNo1());
                save_data.setSendrtelNo2(dto.sendrtelNo2());
                save_data.setSendrtelNo3(dto.sendrtelNo3());
                save_data.setSendrcellNo1(dto.sendrcellNo1());
                save_data.setSendrcellNo2(dto.sendrcellNo2());
                save_data.setSendrcellNo3(dto.sendrcellNo3());
                save_data.setSendrZipNo(dto.sendrZipNo());
                save_data.setSendrNm(dto.sendrNm());
                save_data.setGdsCd(dto.gdsCd());
                save_data.setGdsNm(dto.gdsNm());
                save_data.setGdsQty(dto.gdsQty());
                save_data.setRcptYmd(strToday);
            }
            if (!invc_no_data.sendAvailYn().isEmpty() && invc_no_data.sendAvailYn().equals("N")) {
                if (dto.reqdvCd().equals("01")) {
                    throw new Exception(">> CJ예약(등록) API 오류 - 이전 예약등록 존재");
                } else if (dto.reqdvCd().equals("02")) {
                    throw new Exception(">> CJ예약(취소) API 오류 - 예약등록 없이 취소 불가 또는 취소등록 이력존재");
                }
            }

            //받는분 주소 유효성
            if (save_data.getSendrAddr() == null || save_data.getSendrAddr().length() <= 0) {
                throw new Exception(">> CJ예약(등록/취소) API 오류 - 주소값 공백");
            }

            //우편번호 유효성
            if (save_data.getSendrZipNo() == null || save_data.getSendrZipNo().length() <= 0) {
                throw new Exception(">> CJ예약(등록/취소)  API 오류 - 우편번호값 공백");
            }

            if (dto.reqdvCd().equals("02")) {

                DateTimeFormatter formatter_hist = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
                String strHist = date.format(formatter);
                WsnbWorkOrderDvo dvo = new WsnbWorkOrderDvo();
                dvo.setCntrNo(dto.custNo());
                dvo.setCntrSn(dto.cntrSn());
                dvo.setApyDtm(strHist);
                dvo.setInChnlDvCd("1");
                dvo.setSvBizHclsfCd("3");
                dvo.setMtrStatCd("3");
                dvo.setSvBizDclsfCd("3460");
                dvo.setUrgtDvCd("2");
                dvo.setAsIstOjNo(invc_no_data.rtnAsIstOjNo());
                dvo.setSmsFwYn("N");
                dvo.setUserId(dto.recpId());
                dvo.setRcpOgTpCd(dto.recpOgTpCd());

                // 다건 작업 오더 호출(PR_KIWI_WRK_CREATE_V2M)
                workOrderService.saveWorkOrders(dvo);

            }

            // STEP 02. 토큰발급
            String tokennum = parcelServiceRegService.getParcelServiceCertKey();

            WsniParcelServiceRegDto.SearchAddressCleansingReq req_param = new WsniParcelServiceRegDto.SearchAddressCleansingReq(
                tokennum, dto.sendrAddr()
            );

            // STEP 03. 보내는분 주소정제
            AddressCleansingResultData senderdata = parcelServiceRegService.selectAddressCleansing(req_param);
            if(senderdata != null){
                save_data.setSendrAddr(senderdata.getCLSFADDR());
            }else{
                save_data.setSendrAddr(dto.sendrAddr());
            }

            save_data.setTokkenNum(tokennum);
            save_data.setCustNo(dto.custNo()); // 고객사용번호 -> 기업고객이 관리하는 주문번호/ 영수번호 등 내부 관리번호 취소 시에 취소처리 기준(중복되면 취소 불가능)
            save_data.setReqdvCd(dto.reqdvCd()); // 요청구분코드(01: 요청, 02: 취소)
            save_data.setRcptYmd(strToday); // 접수일자 -> YYYYMMDD
            save_data.setGdsCd(dto.gdsCd());
            save_data.setGdsNm(dto.gdsNm());
            save_data.setGdsQty(dto.gdsQty());

            PcsvAddressCleansingResIvo result = parcelServiceRegService.saveParcelServiceInterface(save_data);

            if (result != null) {
                save_data.setResultCd(result.getRESULTCD());
                save_data.setResultDetail(result.getRESULTDETAIL());
                parcelServiceRegService.saveParcelServiceData(save_data);

                resEaiWrapper.setBody(result);
            }else{
                PcsvAddressCleansingResIvo err_result = new PcsvAddressCleansingResIvo();
                err_result.setRESULTCD("E998");
                err_result.setRESULTCD("반품집하지시 등록 오류");

                resEaiWrapper.setBody(err_result);
            }

            return resEaiWrapper;

        }

    }
}
