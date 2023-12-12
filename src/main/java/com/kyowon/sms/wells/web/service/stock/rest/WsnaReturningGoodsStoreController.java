package com.kyowon.sms.wells.web.service.stock.rest;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaReturningGoodsStoreDto.*;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.stock.service.WsnaReturningGoodsStoreService;
import com.kyowon.sms.wells.web.service.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0108M01 반품입고 관리 Controller
 * </pre>
 *
 * @author SongTaeSung
 * @since 2023.05.04
 */
@Api(tags = "[WSNA] 반품입고 관리")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/returning-goods-store")
public class WsnaReturningGoodsStoreController {

    private final WsnaReturningGoodsStoreService service;

    @ApiOperation(value = "반품입고 관리 조회", notes = "조회조건에 해당하는 반품입고 데이터를 조회한다.")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "itmPdCd", value = "품목출고번호", paramType = "query", example = "212"),
        @ApiImplicitParam(name = "itmKndCd", value = "품목종류코드", paramType = "query", example = "200005"),
        @ApiImplicitParam(name = "stFnlVstFshDtFrom", value = "처리일자From", paramType = "query", example = "20230109"),
        @ApiImplicitParam(name = "edFnlVstFshDtTo", value = "처리일자To", paramType = "query", example = "20230109")
    })
    @GetMapping
    public List<SearchRes> getReturningGoodsStores(
        SearchReq dto
    ) {
        return service.getReturningGoodsStores(dto);
    }

    @ApiOperation(value = "반품입고 저장", notes = "창고장이나 매니저/엔지니어가 반품입고를받아 물류센터로 반품출고요청을 한다.")
    @PostMapping
    public SaveResponse saveReturningGoodsStores(
        @Valid
        @RequestBody
        @NotEmpty
        List<SaveReq> dtos

    ) throws Exception {
        return SaveResponse.builder().processCount(this.service.saveReturningGoodsStores(dtos)).build();
    }

    @ApiOperation(value = "반품입고 확인일자 반품처리유형코드 저장", notes = "반품입고 확인일자 반품처리유형코드 저장을 한다.")
    @PostMapping("/confirmation-type")
    public SaveResponse saveReturningGoodsStoreConfirmations(
        @Valid
        @RequestBody
        @NotEmpty
        List<SaveConfirmationReq> dtos

    ) throws Exception {
        return SaveResponse.builder().processCount(this.service.saveReturningGoodsStoreConfirmations(dtos)).build();
    }

    @ApiOperation(value = "반품입고 관리 엑셀다운로드", notes = "검색조건을 받아 엑셀다운로드용 반품입고 관리를 조회한다.")
    @GetMapping("/excel-download")
    public List<SearchRes> getReturningGoodsStoresExcelDownload(
        SearchReq dto
    ) {
        return service.getReturningGoodsStores(dto);
    }

    @ApiOperation(value = "반품입고 관리 로그인 사용자 창고조회", notes = "로그인한 사용자의 창고를 조회한다.")
    @GetMapping("/login-warehouse")
    public List<SearchWareRes> getReturningGoodsStoresLoginWarehouse(
        @RequestParam
        String prtnrNo
    ) {
        return service.getReturningGoodsStoresLoginWarehouse(prtnrNo);
    }

}
