package com.cinema.moviessecuritydockerspring.analytics;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    @Resource
    private AnalyticsService analyticsService;

    @GetMapping("/category")
    public List<AnalyticsCategoryResponse> getCategoryAnalytics() {
        return analyticsService.getCategoryAnalytics();
    }

    @GetMapping("/movie")
    public List<AnalyticsMovieResponse> getMovieAnalytics() {
        return analyticsService.getMovieAnalytics();
    }

    @GetMapping("/year")
    public List<AnalyticsYearResponse> getYearAnalytics() {
        return analyticsService.getYearAnalytics();
    }
}
