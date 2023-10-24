package com.kyowon.sms.wells.web.service.stock.dvo;

/**
 * <pre>
 * W-SV-U-0138M01 창고조직 관리
 * W-SV-U-0175P01 창고조직 등록
 * </pre>
 *
 * @author songTaeSung
 * @since 2022.12.08
 */
import com.sds.sflex.system.config.annotation.DBDecField;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsnaWarehouseOrganizationDvo {

    private String apyYm; /* 적용년월 */
    private String wareNo; /* 창고번호 */
    private String wareDvCd; /* 창고구분코드 */
    private String wareDtlDvCd; /* 창고상세구분코드 */
    private String wareLocaraCd; /* 창고지역코드 */
    private Long wareLocaraSn; /* 창고지역일련번호 */
    private String hgrWareNo; /* 상위창고번호 */
    private String wareNm; /* 창고명 */
    private String wareIchrNo; /* 창고담당번호 */
    private String wareMngtPrtnrNo; /* 창고관리파트너번호 */
    private String coCd; /* 회사코드 */
    private String ogTpCd; /* 조직유형코드 */
    private String didyDvCd; /* 직배구분코드 */
    private String sortDvVal; /* 정렬구분값 */
    @DBDecField
    private String wareAdrId; /* 창고주소ID */
    private String bldCd; /* 빌딩코드 */
    private String wareUseYn; /* 창고사용여부 */
    private String adrUseYn; /* 주소사용여부 */
    private String apyStrtdt; /* 적용시작일자 */
    private String apyEnddt; /* 적용종료일자 */
    private String dtaDlYn; /* 데이터삭제여부 */
    private String rmkCn; /* 비고내용 */
    private String baseYm; /* 기준년월 */
    String orglhgrWareNo; /* 기존상위창고번호 */
    String fstRgstDt; /* 최초등록일 */
    String fstRgstDtm; /* 최초등록일시 */
    String fstRgstUsrId; /* 최초등록사용자ID */
    String fstRgstPrgId; /* 최초등록프로그램ID */
    String fstRgstDeptId; /* 최초등록부서ID */
    String fnlMdfcDtm; /* 최종수정일시 */
    String fnlMdfcUsrId; /* 최종수정사용자ID */
    String fnlMdfcPrgId; /* 최종수정프로그램ID */
    String fnlMdfcDeptId; /* 최종수정부서ID */

    String prtnrNo; /*파트너번호*/
    String prtnrKnm; /*파트너명*/
    String hgrWareNm; /*상위창고명*/
    String rnadr; /* 도로명주소 */
    String rdadr; /* 도로명상세주소 */
    String newAdrZip; /*신주소우편번호*/
    String bldCdNm; /* 빌딩코드명 */
    String ogId; /* 조직ID */
    String ogCd; /* 조직코드 */
    String ogNm; /* 조직명 */
    String dgr1LevlOgCd; /* 1레벨조직코드 */
    String dgr1LevlOgId; /* 1레벨조직ID */
    String dgr1LevlOgNm; /* 1레벨조직명 */
    String dgr1LevlOgCdNm; /* 1레벨조직코드명 */
    String dgr2LevlOgCd; /* 2레벨조직코드 */
    String dgr2LevlOgId; /* 2레벨조직ID */
    String dgr2LevlOgNm; /* 2레벨조직명 */
    String dgr2LevlOgCdNm; /* 2레벨조직코드명 */

}
