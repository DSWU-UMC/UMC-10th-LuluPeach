package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.exception.code.StoreErrorCode;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.mission.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionService {
    
    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    public static String singleParameter(String singleParameter) {
        return singleParameter;
    }

    @Transactional(readOnly = true)
    public MissionResDTO.MyMissionListDTO getMyMissions(Long memberId) {
        // memberId로 MemberMission를 찾아냄.(리스트로 반환)
        List<MemberMission> memberMissions = memberMissionRepository.findByMemberId(memberId);

        
        List<MissionResDTO.MyMissionDTO> missionDTOs = memberMissions.stream()
                .map(MissionConverter::toMyMissionDTO)
                .collect(Collectors.toList());
        
        return MissionResDTO.MyMissionListDTO.builder()
                .missions(missionDTOs)
                .build();
    }

    public MissionResDTO.CompleteMissionResultDTO completeMission(Long memberMissionId) {
        MemberMission memberMission = memberMissionRepository.findById(memberMissionId)
                .orElseThrow(() -> new IllegalArgumentException("미션을 찾을 수 없습니다"));
        
        memberMission.complete();
        memberMissionRepository.save(memberMission);
        
        return MissionConverter.toCompleteMissionResultDTO(memberMission);
    }

    public MissionResDTO.GetMission getMissions(Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new MissionException(StoreErrorCode.NOT_FOUND));

        List<MissionResDTO.MissionInfo> missions = missionRepository.findAllByStoreId(storeId)
                .stream()
                .map(mission -> new MissionResDTO.MissionInfo(
                        mission.getId(),
                        mission.getDeadline(),
                        mission.getConditional(),
                        mission.getPoint()
                ))
                .toList();

        return new MissionResDTO.GetMission(store.getId(), missions);
    }

    // 가게 내 미션 조회
    public static MissionResDTO.GetMission toGetMission(
            Mission mission
    ){
        return MissionResDTO.GetMission.builder()
                .conditional(mission.getConditional())
                .point(mission.getPoint())
                .missionId(mission.getId())
                .build();
    }

}
