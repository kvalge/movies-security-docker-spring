package com.cinema.moviessecuritydockerspring.analytics;

import com.cinema.moviessecuritydockerspring.domain.category.Category;
import com.cinema.moviessecuritydockerspring.domain.category.CategoryRepository;
import com.cinema.moviessecuritydockerspring.domain.movie.Movie;
import com.cinema.moviessecuritydockerspring.domain.movie.MovieRepository;
import com.cinema.moviessecuritydockerspring.domain.moviedetails.MovieDetails;
import com.cinema.moviessecuritydockerspring.domain.moviedetails.MovieDetailsRepository;
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

    @Resource
    private MovieDetailsRepository movieDetailsRepository;

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

    /**
     * Returns the list of movies with statistics of:
     * number of rentals of the movie,
     * share of rentals of the movie of all rentals,
     * average rating of movie.
     */
    public List<AnalyticsMovieResponse> getMovieAnalytics() {
        List<Movie> movies = movieRepository.findAll();
        List<Rental> rentals = rentalRepository.findAll();

        List<AnalyticsMovieResponse> responses = new ArrayList<>();

        int rentalsInTotal = rentals.size();

        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(2);

        for (Movie movie : movies) {
            AnalyticsMovieResponse response = new AnalyticsMovieResponse();

            response.setMovieName(movie.getName());
            response.setCategoryName(movie.getCategory().getName());

            MovieDetails details = movieDetailsRepository.findByMovieName(movie.getName());
            response.setReleaseYear(details.getYear());

            Integer totalMovieRentals = 0;
            int sumOfMovieRatings = 0;
            int nrOfRates = 0;
            for (Rental rental : rentals) {
                if (rental.getMovie().getName().equals(movie.getName())) {
                    totalMovieRentals++;

                    if (rental.getRating() == null) {
                        continue;
                    }
                    sumOfMovieRatings += rental.getRating();
                    nrOfRates++;
                }
            }
            response.setMovieRentals(totalMovieRentals);

            Float shareOfMovieRentals = (float) totalMovieRentals / rentalsInTotal;
            response.setShareOfRentals(Float.valueOf(decimalFormat.format(shareOfMovieRentals)));

            Float avMovieRating = (float) sumOfMovieRatings / nrOfRates;
            response.setAvMovieRating(avMovieRating);

            responses.add(response);
        }
        return responses;
    }

    /**
     * Returns the list of years with statistics of:
     * number of rentals in that year,
     * share of rentals of that year of all rentals,
     * average rating of movies of that year.
     */
    public List<AnalyticsYearResponse> getYearAnalytics() {
        List<MovieDetails> details = movieDetailsRepository.findAll();
        List<Rental> rentals = rentalRepository.findAll();

        int rentalsInTotal = rentals.size();

        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(2);

        List<AnalyticsYearResponse> responses = new ArrayList<>();

        for (MovieDetails d : details) {
            AnalyticsYearResponse response = new AnalyticsYearResponse();
            response.setReleaseYear(d.getYear());

            int sumOfMovieRentals = 0;
            int sumOfMovieRatings = 0;
            int nrOfRates = 0;
            for (Rental rental : rentals) {
                String rentedMovie = rental.getMovie().getName();
                String year = movieDetailsRepository.findByMovieName(rentedMovie).getYear();
                if (d.getYear().equals(year)) {
                    sumOfMovieRentals++;

                    if (rental.getRating() == null) {
                        continue;
                    }
                    sumOfMovieRatings += rental.getRating();
                    nrOfRates++;
                }
                response.setMovieRentals(sumOfMovieRentals);

                Float shareOfRentals = (float) sumOfMovieRentals / rentalsInTotal;
                response.setShareOfRentals(shareOfRentals);

                Float avMovieRating = (float) sumOfMovieRatings / nrOfRates;
                response.setAvMovieRating(avMovieRating);
            }
            responses.add(response);
        }
        return responses;
    }
}
