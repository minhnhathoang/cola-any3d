package org.nhathm.api;

import com.alibaba.cola.dto.Response;
import org.nhathm.dto.command.ImageTargetAddCmd;

import java.io.IOException;

public interface ImageTargetService {

    Response addImageTarget(ImageTargetAddCmd cmd) throws IOException;
}
