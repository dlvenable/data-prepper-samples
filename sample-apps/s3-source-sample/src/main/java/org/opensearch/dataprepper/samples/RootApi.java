package org.opensearch.dataprepper.samples;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootApi {
    @GetMapping("/")
    void root() { }
}
