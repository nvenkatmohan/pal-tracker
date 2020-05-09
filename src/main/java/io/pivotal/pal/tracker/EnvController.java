package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {

    final String PORT_PROP_NAME = "PORT";
    final String MEMORY_LIMIT_PROP_NAME = "MEMORY_LIMIT";
    final String CF_INSTANCE_INDEX_PROP_NAME = "CF_INSTANCE_INDEX";
    final String CF_INSTANCE_ADDR_PROP_NAME = "CF_INSTANCE_ADDR";


    private String port;
    private String memoryLimit;
    private String cfInstanceIndex;
    private String cfInstanceAddr;

    public EnvController(@Value("${port:NOT SET}") String port,
                         @Value("${memory.limit:NOT SET}") String memoryLimit,
                         @Value("${cf.instance.index:NOT SET}") String cfInstanceIndex,
                         @Value("${cf.instance.addr:NOT SET}") String cfInstanceAddr) {
        this.port = port;
        this.memoryLimit = memoryLimit;
        this.cfInstanceIndex = cfInstanceIndex;
        this.cfInstanceAddr = cfInstanceAddr;
    }

    @GetMapping("/env")
    public Map<String, String> getEnv() {

        Map<String,String> envMap = new HashMap<String,String>();

        envMap.put(PORT_PROP_NAME,this.port);
        envMap.put(MEMORY_LIMIT_PROP_NAME,this.memoryLimit);
        envMap.put(CF_INSTANCE_INDEX_PROP_NAME,this.cfInstanceIndex);
        envMap.put(CF_INSTANCE_ADDR_PROP_NAME,this.cfInstanceAddr);

        return envMap;
    }
}
