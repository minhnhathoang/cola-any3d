package org.nhathm.controller;

import lombok.RequiredArgsConstructor;
import org.nhathm.APIConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RequestMapping(APIConstant.WEB_API_PATH + "/holograms")
@RestController
public class HologramController {

}
