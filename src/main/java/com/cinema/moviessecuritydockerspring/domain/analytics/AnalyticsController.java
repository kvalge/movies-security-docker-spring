package com.cinema.moviessecuritydockerspring.domain.analytics;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    @Resource
    private AnalyticsService analyticsService;


}
