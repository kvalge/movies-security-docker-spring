package com.cinema.moviessecuritydockerspring.domain.price;

import com.cinema.moviessecuritydockerspring.analytics.AnalyticsMovieResponse;
import com.cinema.moviessecuritydockerspring.analytics.AnalyticsService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;

@Service
public class PriceService {

    public static final String PRICE_6 = "6.00";
    public static final String PRICE_3 = "3.00";
    public static final String PRICE_2 = "2.00";
    public static final String PRICE_FREE = "Free";
    public static final int YEAR_SUBTRACT_5 = 5;
    public static final int YEAR_SUBTRACT_9 = 9;

    @Resource
    private AnalyticsService analyticsService;

    /**
     * Sets movie price according to the number of rentals and the release year.
     * If the release year is less than 5 years from the current year, the price is 6.00.
     * if the release year is less than 9 year from the current year and the number of rentals is more than or equal to 490, the price is 3.00.
     * if the release year is less than 9 year from the current year and the number of rentals is less than 490, the price is 2.00.
     * All the rest of movies are for free.
     */
    public PriceResponse setPrice(String movieName) {
        List<AnalyticsMovieResponse> movieAnalytics = analyticsService.getMovieAnalytics();

        PriceResponse priceResponse = new PriceResponse();

        String price = "";

        for (AnalyticsMovieResponse response : movieAnalytics) {
            boolean equalsToMovie = response.getMovieName().equals(movieName);
            String releaseYear = response.getReleaseYear();
            Integer movieRentals = response.getMovieRentals();

            Year year = Year.parse(releaseYear);
            Year currentYear = Year.now();
            Year minus5Years = currentYear.minusYears(YEAR_SUBTRACT_5);
            Year minus9Years = currentYear.minusYears(YEAR_SUBTRACT_9);

            if (equalsToMovie) {
                if (year.isAfter(minus5Years)) {
                    price = PRICE_6;
                } else if (year.isAfter(minus9Years) && movieRentals >= 490) {
                    price = PRICE_3;
                } else if (year.isAfter(minus9Years) && movieRentals < 490) {
                    price = PRICE_2;
                } else {
                    price = PRICE_FREE;
                }
            }
        }
        priceResponse.setPrice(price);

        return priceResponse;
    }
}