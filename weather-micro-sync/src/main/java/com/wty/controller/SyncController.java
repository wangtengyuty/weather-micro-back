package com.wty.controller;

import com.wty.Service.ISyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * @Author wangtengyu
 * @Create 2018-05-07-15:05
 */
@RestController
public class SyncController {

    @Autowired
    private ISyncService iSyncService;

    @GetMapping("/sync")
    public void sync() throws IOException, JAXBException {
        iSyncService.syncDataByCityKey();
    }
}
