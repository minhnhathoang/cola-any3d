package org.nhathm.object;

import lombok.RequiredArgsConstructor;
import org.nhathm.constant.APIConstant;
import org.nhathm.object.api.ObjectService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author <a href="mailto:nhathm.uet@outlook.com">nhathm</a>
 */
@RequiredArgsConstructor
@RequestMapping(APIConstant.WEB_API_PATH + "/objects")
@RestController
public class ObjectController {

    private final ObjectService objectService;

    @PostMapping("/upload")
    public void upload(
            @RequestPart("file") MultipartFile object,
            @RequestPart("projectId") String projectId) {

    }

    @GetMapping("/download")
    public void download() {

    }

    @DeleteMapping("/delete")
    public void delete() {

    }
}
