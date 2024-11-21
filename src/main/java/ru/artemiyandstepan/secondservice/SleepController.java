package ru.artemiyandstepan.secondservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequestMapping("/sleep")
public class SleepController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/{time}")
    public String sleep(@PathVariable long time) throws InterruptedException {
        Thread.sleep(Duration.ofSeconds(time).toMillis());
        return "Sleep for " + time + " seconds completed. Application port is " + port;
    }
}
