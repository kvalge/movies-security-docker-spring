package com.cinema.moviessecuritydockerspring.domain.price;

import com.cinema.moviessecuritydockerspring.analytics.AnalyticsMovieResponse;
import com.cinema.moviessecuritydockerspring.analytics.AnalyticsService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class PriceService {

    public static final String PRICE_6 = "6.00";
    public static final String PRICE_3 = "3.00";
    public static final String PRICE_FREE = "Free";
    public static final String YEAR2015 = "2015";

    @Resource
    private AnalyticsService analyticsService;

    /**
     * Sets movie price according to the number of rentals and the release year.
     */
    public PriceResponse setPrice(String movieName) throws ParseException {
        List<AnalyticsMovieResponse> movieAnalytics = analyticsService.getMovieAnalytics();

        PriceResponse priceResponse = new PriceResponse();

        String price = "";

        for (AnalyticsMovieResponse response : movieAnalytics) {
            boolean equalsToMovie = response.getMovieName().equals(movieName);
            String releaseYear = response.getReleaseYear();
            Integer movieRentals = response.getMovieRentals();

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy", Locale.ENGLISH);
            Date year = formatter.parse(releaseYear);

            if (equalsToMovie) {
                if ((movieRentals < 5 || movieRentals >= 15) && year.after(Date.from(Instant.parse(YEAR2015)))) {
                    price = PRICE_6;
                } else if (movieRentals < 15 && year.after(Date.from(Instant.parse(YEAR2015)))) {
                    price = PRICE_3;
                } else {
                    price = PRICE_FREE;
                }
            }
        }
        priceResponse.setPrice(price);

        return priceResponse;
    }
}
