package com.kyowon.sms.wells.web.service.interfaces.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.interfaces.dvo.WsnbServiceWorkInterfaceDvo;

/**
 * TODO: API 스펙 확인 후 수정 필요
 * <pre>
 * W-SV-I-0009 타시스템(kyowonwells, CubigCC, kmembers)에서 설치/AS/BS/홈케어 서비스 작업 오더 API
 * </pre>
 *
 * @author hyewon.kim 김혜원
 * @since 2023.02.03
 */
@Mapper
public interface WsnbServiceWorkInterfaceMapper {

    WsnbServiceWorkInterfaceDvo selectAsInstallationTarget(WsnbServiceWorkInterfaceDvo dvo);

}
