package org.nhathm.controller;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.nhathm.APIConstant;
import org.nhathm.api.VuforiaService;
import org.nhathm.dto.clientobject.VuforiaKeyCO;
import org.nhathm.dto.command.VuforiaAddKeyCmd;
import org.nhathm.dto.command.VuforiaUpdateKeyCmd;
import org.nhathm.dto.query.VuforiaFindKeyQry;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RequestMapping(APIConstant.WEB_API_PATH + "/sdk/vuforia")
@RestController
public class VuforiaController {

    private final VuforiaService vuforiaService;

    @GetMapping("/key")
    public SingleResponse<VuforiaKeyCO> findKey(@RequestBody VuforiaFindKeyQry qry) {
        return vuforiaService.findKey(qry);
    }

    @PostMapping("/key")
    public Response addKey(@RequestBody VuforiaAddKeyCmd cmd) {
        return vuforiaService.addKey(cmd);
    }

    @PutMapping("/key")
    public Response updateKey(@RequestBody VuforiaUpdateKeyCmd cmd) {
        return vuforiaService.updateKey(cmd);
    }
}
