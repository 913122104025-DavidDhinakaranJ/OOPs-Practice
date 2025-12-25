package com.mycompany.movieticketbookingapplication.repositories;

import com.mycompany.authlib.users.AuthenticatableUser;
import com.mycompany.movieticketbookingapplication.enums.Genre;
import com.mycompany.movieticketbookingapplication.enums.Language;
import com.mycompany.movieticketbookingapplication.enums.Rating;
import com.mycompany.movieticketbookingapplication.models.Booking;
import com.mycompany.movieticketbookingapplication.models.Movie;
import com.mycompany.movieticketbookingapplication.models.Show;
import com.mycompany.movieticketbookingapplication.models.Theatre;
import com.mycompany.movieticketbookingapplication.models.users.Admin;
import com.mycompany.movieticketbookingapplication.models.users.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryRepository implements IBookingRepository, IUserRepository, IMovieRepository, IShowRepository, ITheatreRepository {
    private static InMemoryRepository instance;
    
    private final Map<String, User> users;
    private final List<Booking> bookings;
    private final List<Movie> movies;
    private final List<Show> shows;
    private final List<Theatre> theatres;
    
    private InMemoryRepository() {
        this.users = new HashMap<>();
        this.bookings = new ArrayList<>();
        this.movies = new ArrayList<>();
        this.shows = new ArrayList<>();
        this.theatres = new ArrayList<>();
        
        Admin admin = new Admin("admin", "Admin@1234");
        users.put(admin.getUsername(), admin);
    }

    public static InMemoryRepository getInMemoryRepository() {
        if(instance == null) {
            instance = new InMemoryRepository();
        }
        return instance;
    }

    @Override
    public void saveBooking(Booking booking) {
        bookings.add(booking);
    }

    @Override
    public void saveUser(AuthenticatableUser user) {
        users.put(user.getUsername(), (User) user);
    }

    @Override
    public AuthenticatableUser findUser(String username) {
        return users.get(username);
    }

    @Override
    public boolean isUsernameTaken(String username) {
        return users.containsKey(username);
    }

    @Override
    public List<Movie> getAllMovies() {
        return new ArrayList<>(movies);
    }

    @Override
    public List<Movie> getMovies(String title) {
        List<Movie> movieList = new ArrayList<>();
        for(Movie movie : movies) {
            if(movie.getTitle().toLowerCase().contains(title.toLowerCase())) {
                movieList.add(movie);
            }
        }
        return movieList;
    }


    @Override
    public List<Movie> getMovies(Genre genre) {
        List<Movie> movieList = new ArrayList<>();
        for(Movie movie : movies) {
            if(movie.getGenres().contains(genre)) {
                movieList.add(movie);
            }
        }
        return movieList;
    }

    @Override
    public List<Movie> getMovies(Language language) {
        List<Movie> movieList = new ArrayList<>();
        for(Movie movie : movies) {
            if(movie.getLanguages().contains(language)) {
                movieList.add(movie);
            }
        }
        return movieList;
    }

    @Override
    public List<Movie> getMovies(Rating rating) {
        List<Movie> movieList = new ArrayList<>();
        for(Movie movie : movies) {
            if(movie.getRating().equals(rating)) {
                movieList.add(movie);
            }
        }
        return movieList;
    }

    @Override
    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    @Override
    public void deleteMovie(Movie movie) {
        movies.remove(movie);
    }

    @Override
    public void addShow(Show show) {
        shows.add(show);
    }

    @Override
    public void deleteShow(Show show) {
        shows.remove(show);
    }

    @Override
    public List<Show> getShows(Movie movie) {
        List<Show> showList = new ArrayList<>();
        for(Show show : shows) {
            if(show.getMovie().equals(movie)) {
                showList.add(show);
            }
        }
        return showList;
    }

    @Override
    public List<Show> getAllShows() {
        return new ArrayList<>(shows);
    }

    @Override
    public void addTheatre(Theatre theatre) {
        theatres.add(theatre);
    }

    @Override
    public void deleteTheatre(Theatre theatre) {
        theatres.remove(theatre);
    }

    @Override
    public List<Theatre> getAllTheatres() {
        return new ArrayList<>(theatres);
    }
    
}