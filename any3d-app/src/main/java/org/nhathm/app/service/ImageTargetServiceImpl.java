package org.nhathm.app.service;

import com.alibaba.cola.dto.Response;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.nhathm.api.ImageTargetService;
import org.nhathm.app.executor.ImageTargetAddCmdExe;
import org.nhathm.dto.command.ImageTargetAddCmd;
import org.springframework.stereotype.Service;

import java.io.IOException;


@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ImageTargetServiceImpl implements ImageTargetService {

    ImageTargetAddCmdExe imageTargetAddCmdExe;

    @Override
    public Response addImageTarget(ImageTargetAddCmd cmd) throws IOException {
        return imageTargetAddCmdExe.execute(cmd);
    }
}
