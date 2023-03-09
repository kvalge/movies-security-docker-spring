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
     * Returns the list of categories (genres) with statistics about:
     * number of movies in category,
     * share of movies in the category of all movies,
     * how many movies are rented in category,
     * share of rentals in the category of all rentals,
     * average rating of movies in category.
     */
    public List<AnalyticsCategoryResponse> getCategoryAnalytics() {
        List<Category> categories = categoryRepository.findAll();
        List<Movie> movies = movieRepository.findAll();
        List<Rental> rentals = rentalRepository.findAll();

        List<AnalyticsCategoryResponse> responses = new ArrayList<>();

        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(1);

        for (Category category : categories) {
            AnalyticsCategoryResponse response = new AnalyticsCategoryResponse();

            response.setCategoryName(category.getName());

            int moviesInTotal = 0;
            int categoryMovieCount = 0;
            for (Movie movie : movies) {
                moviesInTotal++;
                if (movie.getCategory().getName().equals(category.getName())) {
                    categoryMovieCount++;
                }
            }
            response.setMoviesInCategory(categoryMovieCount);

            Float shareOfMovies = (float) categoryMovieCount / moviesInTotal;
            response.setShareOfMovies(Float.valueOf(decimalFormat.format(shareOfMovies)));

            int rentalsInTotal = 0;
            int categoryRentalCount = 0;
            int categoryRentalRatings = 0;
            int categoryRatingsCount = 0;
            for (Rental rental : rentals) {
                rentalsInTotal++;
                if (rental.getMovie().getCategory().getName().equals(category.getName())) {
                    categoryRentalCount++;

                    Integer rating = rental.getRating();
                    if (rating == null) {
                        continue;
                    }
                    categoryRentalRatings += rating;
                    categoryRatingsCount ++;
                }
                response.setRentalsInCategory(categoryRentalCount);
                if (categoryRentalRatings == 0 || categoryRatingsCount ==  0){
                    continue;
                }
                Float avRatingInCategory = (float) categoryMovieCount / moviesInTotal;
                response.setAvRatingInCategory(Float.valueOf(decimalFormat.format(avRatingInCategory)));
            }

            Float shareOfRentals = (float)categoryRentalCount / rentalsInTotal;
            response.setShareOfRentals(Float.valueOf(decimalFormat.format(shareOfRentals)));

            responses.add(response);
        }

        return responses;
    }
}
