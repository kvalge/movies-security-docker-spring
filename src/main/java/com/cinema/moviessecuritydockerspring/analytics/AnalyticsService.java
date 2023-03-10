package com.cinema.moviessecuritydockerspring.analytics;

import com.cinema.moviessecuritydockerspring.domain.category.Category;
import com.cinema.moviessecuritydockerspring.domain.category.CategoryRepository;
import com.cinema.moviessecuritydockerspring.domain.movie.Movie;
import com.cinema.moviessecuritydockerspring.domain.movie.MovieRepository;
import com.cinema.moviessecuritydockerspring.domain.rental.Rental;
import com.cinema.moviessecuritydockerspring.domain.rental.RentalRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnalyticsService {

    @Resource
    private CategoryRepository categoryRepository;

    @Resource
    private MovieRepository movieRepository;

    @Resource
    private RentalRepository rentalRepository;

    /**
     * Returns the list of categories (genres) with statistics of:
     * number of movies in category,
     * share of movies in the category of all movies,
     * number of movies rented in category,
     * share of rentals in the category of all rentals,
     * average rating of movies in category.
     */
    public List<AnalyticsCategoryResponse> getCategoryAnalytics() {
        List<Category> categories = categoryRepository.findAll();
        List<Movie> movies = movieRepository.findAll();
        List<Rental> rentals = rentalRepository.findAll();

        List<AnalyticsCategoryResponse> responses = new ArrayList<>();

        int moviesInTotal = movies.size();
        int rentalsInTotal = rentals.size();

        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(2);

        for (Category category : categories) {
            AnalyticsCategoryResponse response = new AnalyticsCategoryResponse();

            response.setCategoryName(category.getName());

            int moviesInCategory = 0;
            for (Movie movie : movies) {
                if (movie.getCategory().getName().equals(category.getName())) {
                    moviesInCategory++;
                }
            }
            response.setMoviesInCategory(moviesInCategory);

            Float shareOfMovies = (float) moviesInCategory / moviesInTotal;
            response.setShareOfMovies(Float.valueOf(decimalFormat.format(shareOfMovies)));

            int rentalsInCategory = 0;
            int sumOfMovieRatingsInCategory = 0;
            int ratedMoviesInCategory = 0;
            for (Rental rental : rentals) {
                if (rental.getMovie().getCategory().getName().equals(category.getName())) {
                    rentalsInCategory++;

                    Integer rating = rental.getRating();
                    if (rating == null) {
                        continue;
                    }
                    sumOfMovieRatingsInCategory += rating;
                    ratedMoviesInCategory++;
                }

                if (sumOfMovieRatingsInCategory == 0 || ratedMoviesInCategory == 0) {
                    continue;
                }
                Float avRatingInCategory = (float) (sumOfMovieRatingsInCategory / ratedMoviesInCategory);
                response.setAvRatingInCategory(Float.valueOf(decimalFormat.format(avRatingInCategory)));
            }

            response.setRentalsInCategory(rentalsInCategory);

            Float shareOfRentals = (float) rentalsInCategory / rentalsInTotal;
            response.setShareOfRentals(Float.valueOf(decimalFormat.format(shareOfRentals)));

            responses.add(response);
        }

        return responses;
    }
}
