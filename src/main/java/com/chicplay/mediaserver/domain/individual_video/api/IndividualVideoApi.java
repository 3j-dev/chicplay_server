package com.chicplay.mediaserver.domain.individual_video.api;

import com.chicplay.mediaserver.domain.individual_video.domain.TextMemoState;
import com.chicplay.mediaserver.domain.individual_video.dto.TextMemoStateSaveRequest;
import com.chicplay.mediaserver.domain.individual_video.application.IndividualVideoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/individuals-videos")
public class IndividualVideoApi {

    private final IndividualVideoService individualVideoService;

    @PostMapping("/text-memo-state")
    public void saveTextMemoState(@RequestBody @Valid final TextMemoStateSaveRequest dto){

        individualVideoService.saveTextMemoState(dto);
    }

    @PostMapping("/text-memo-states")
    public void saveTextMemoStates(@RequestBody @Valid final List<TextMemoStateSaveRequest> textMemoStates) {

        individualVideoService.saveTextMemoStates(textMemoStates);

    }

}
