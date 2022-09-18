package com.chicplay.mediaserver.domain.video_space.application;

import com.chicplay.mediaserver.domain.account.application.AccountService;
import com.chicplay.mediaserver.domain.account.domain.Account;
import com.chicplay.mediaserver.domain.video_space.dao.VideoSpaceRepository;
import com.chicplay.mediaserver.domain.video_space.domain.VideoSpace;
import com.chicplay.mediaserver.domain.video_space.domain.VideoSpaceParticipant;
import com.chicplay.mediaserver.domain.video_space.dto.VideoSpaceSaveRequest;
import com.chicplay.mediaserver.domain.video_space.dto.VideoSpaceSaveResponse;
import com.chicplay.mediaserver.domain.video_space.exception.VideoSpaceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class VideoSpaceService {

    private final VideoSpaceRepository videoSpaceRepository;

    private final AccountService accountService;


    // video space save, 생성시 생성자에 대해서 participant 자동 생성
    public VideoSpaceSaveResponse save(VideoSpaceSaveRequest videoSpaceSaveRequest) {

        // account find
        Account account = accountService.findByEmail("jsb100800@naver.com");

        // video space 생성
        VideoSpace savedVideoSpace = videoSpaceRepository.save(videoSpaceSaveRequest.toEntity());

        // 생성자가 포함된 video space participant create, 연관 관계 매핑에 의해 생성된다.
        VideoSpaceParticipant videoSpaceParticipant = VideoSpaceParticipant.builder().videoSpace(savedVideoSpace).account(account).build();

        return VideoSpaceSaveResponse.builder().videoSpace(savedVideoSpace).build();
    }

    public VideoSpace findById(Long id) {

        // id를 통해 videoSpace get
        Optional<VideoSpace> videoSpace = videoSpaceRepository.findById(id);

        // not found exception
        videoSpace.orElseThrow(() -> new VideoSpaceNotFoundException(id.toString()));

        return videoSpace.get();
    }


}
