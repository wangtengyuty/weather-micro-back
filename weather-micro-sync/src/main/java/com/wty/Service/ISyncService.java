package com.wty.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * @Author wangtengyu
 * @Create 2018-04-23-9:05
 */

public interface ISyncService {

    void syncDataByCityKey() throws IOException, JAXBException;
}
