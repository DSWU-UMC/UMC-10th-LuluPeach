package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
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

    public static String singleParameter(String singleParameter) {
        return singleParameter;
    }

    @Transactional(readOnly = true)
    public MissionResDTO.MyMissionListDTO getMyMissions(Long memberId) {
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
}
