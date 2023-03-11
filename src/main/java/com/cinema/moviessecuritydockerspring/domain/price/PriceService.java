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
    public static final String PRICE_2 = "2.00";
    public static final String PRICE_FREE = "Free";
    public static final String YEAR2020 = "2020";
    public static final String YEAR2005 = "2005";

    @Resource
    private AnalyticsService analyticsService;

    /**
     * Sets movie price according to the number of rentals and the release year.
     * If the release year is after 2020, the price is 6.00.
     * if the release year is after 2005 and the number of rentals is more than or equal to 490, the price is 3.00.
     * if the release year is after 2005 and the number of rentals is less than 490, the price is 2.00.
     * All the rest of movies are for free.
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
                if (year.after(Date.from(Instant.parse(YEAR2020)))) {
                    price = PRICE_6;
                } else if (movieRentals >= 490 && year.after(Date.from(Instant.parse(YEAR2005)))) {
                    price = PRICE_3;
                } else if (movieRentals < 490 && year.after(Date.from(Instant.parse(YEAR2005)))) {
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
