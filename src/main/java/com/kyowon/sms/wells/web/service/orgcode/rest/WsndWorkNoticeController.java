package com.kyowon.sms.wells.web.service.orgcode.rest;

import static com.kyowon.sms.wells.web.service.orgcode.dto.WsndWorkNoticeDto.*;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.bind.annotation.*;

import com.kyowon.sms.wells.web.service.orgcode.service.WsndWorkNoticeService;
import com.kyowon.sms.wells.web.zcommon.constants.SnServiceConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "[WSND] 작업 공지사항")
@RequiredArgsConstructor
@RestController
@RequestMapping(SnServiceConst.REST_URL_V1 + "/work-notices")
public class WsndWorkNoticeController {
    private final WsndWorkNoticeService wsndWorkNoticeService;

    @ApiOperation(value = "작업 공지사항 조회", notes = "조회조건에 따른 작업 공지사항 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "stRgstDt", value = "시작 등록일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "edRgstDt", value = "종료 등록일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "mngrDvCd", value = "관리구분", paramType = "query", required = false),
        @ApiImplicitParam(name = "ntccnTitNm", value = "제목", paramType = "query", required = false),
    })
    @GetMapping
    public List<SearchRes> getWorkNotices(SearchReq dto) {
        return wsndWorkNoticeService.getWorkNotices(dto);
    }

    @ApiOperation(value = "작업 공지사항 조회(Paging)", notes = "조회조건에 따른 작업 공지사항 페이징 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "stRgstDt", value = "시작 등록일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "edRgstDt", value = "종료 등록일자", paramType = "query", required = true),
        @ApiImplicitParam(name = "mngrDvCd", value = "관리구분", paramType = "query", required = false),
        @ApiImplicitParam(name = "ntccnTitNm", value = "제목", paramType = "query", required = false),
    })
    @GetMapping("/paging")
    public PagingResult<SearchRes> getWorkNoticePages(
        SearchReq dto,
        @Valid
        PageInfo pageInfo
    ) {
        return wsndWorkNoticeService.getWorkNoticePages(dto, pageInfo);
    }

    @ApiOperation(value = "작업 공지사항 상세 조회", notes = "조회조건에 따른 작업 공지사항 상세 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "mngtYm", value = "관리년월", paramType = "query", required = true),
        @ApiImplicitParam(name = "ntcId", value = "공지ID", paramType = "query", required = true),
        @ApiImplicitParam(name = "ntcSn", value = "공지일련번호", paramType = "query", required = true),
    })
    @GetMapping("/detail")
    public FindRes getWorkNoticeDetail(FindReq dto) {
        return wsndWorkNoticeService.getWorkNoticeDetail(dto);
    }

    @ApiOperation(value = "상품코드 조회", notes = "상품그룹코드에 따른 상품코드 조회")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "pdGrpCd", value = "상품그룹코드", paramType = "path", required = true),
    })
    @GetMapping("/products/{pdGrpCd}")
    public List<SearchProductRes> getProductsByProductGroup(
        @PathVariable
        String pdGrpCd
    ) {
        return wsndWorkNoticeService.getProductsByProductGroup(pdGrpCd);
    }

    @ApiOperation(value = "작업 공지사항 등록", notes = "입력정보에 따른 작업 공지사항 등록")
    @PostMapping
    public SaveResponse createWorkNotice(
        @RequestBody
        @Valid
        @NotEmpty
        CreateReq dto
    ) {
        return SaveResponse.builder()
            .processCount(wsndWorkNoticeService.createWorkNotice(dto))
            .build();
    }

    @ApiOperation(value = "작업 공지사항 수정", notes = "입력정보에 따른 작업 공지사항 수정")
    @PutMapping
    public SaveResponse editWorkNotice(
        @RequestBody
        @Valid
        @NotEmpty
        EditReq dto
    ) {
        return SaveResponse.builder()
            .processCount(wsndWorkNoticeService.editWorkNotice(dto))
            .build();
    }
}
