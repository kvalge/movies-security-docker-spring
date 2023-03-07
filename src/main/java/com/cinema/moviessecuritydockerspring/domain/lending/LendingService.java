package com.cinema.moviessecuritydockerspring.domain.lending;

import com.cinema.moviessecuritydockerspring.domain.movie.Movie;
import com.cinema.moviessecuritydockerspring.domain.movie.MovieRepository;
import com.cinema.moviessecuritydockerspring.domain.user.User;
import com.cinema.moviessecuritydockerspring.domain.user.UserRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LendingService {

    @Resource
    private LendingMapper lendingMapper;

    @Resource
    private LendingRepository lendingRepository;

    @Resource
    private UserRepository userRepository;

    @Resource
    private MovieRepository movieRepository;

    public void addLending(LendingRequest request) {
        Lending lending = lendingMapper.toEntity(request);

        Lending newLending = new Lending();
        User user = userRepository.findByUsername(lending.getUser().getUsername());
        newLending.setUser(user);

        Movie movie = movieRepository.findByName(lending.getMovie().getName());
        newLending.setMovie(movie);

        LocalDate lendingDate = lending.getLendingDate();
        newLending.setLendingDate(lendingDate);

        newLending.setDueDate(lendingDate.plusDays(2));

        lendingRepository.save(newLending);
    }
}
