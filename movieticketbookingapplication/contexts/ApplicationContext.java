package com.mycompany.movieticketbookingapplication.contexts;

import com.mycompany.authlib.users.factory.AuthenticatableUserFactory;
import com.mycompany.movieticketbookingapplication.models.users.userFactories.AdminFactory;
import com.mycompany.movieticketbookingapplication.models.users.userFactories.CustomerFactory;
import com.mycompany.movieticketbookingapplication.repositories.IBookingRepository;
import com.mycompany.movieticketbookingapplication.repositories.IMovieRepository;
import com.mycompany.movieticketbookingapplication.repositories.IShowRepository;
import com.mycompany.movieticketbookingapplication.repositories.ITheatreRepository;
import com.mycompany.movieticketbookingapplication.repositories.IUserRepository;
import com.mycompany.movieticketbookingapplication.repositories.InMemoryRepository;

public class ApplicationContext {

    private static ApplicationContext instance = new ApplicationContext();
    
    private final IUserRepository userRepository;
    private final IBookingRepository bookingRepository;
    private final IMovieRepository movieRepository;
    private final IShowRepository showRepository;
    private final ITheatreRepository theatreRepository;
    private final AuthenticatableUserFactory customerFactory;
    private final AuthenticatableUserFactory adminFactory;
    private final SessionContext sessionContext;
    
    private ApplicationContext() {
        this.userRepository = InMemoryRepository.getInMemoryRepository();
        this.bookingRepository = InMemoryRepository.getInMemoryRepository();
        this.movieRepository = InMemoryRepository.getInMemoryRepository();
        this.showRepository = InMemoryRepository.getInMemoryRepository();
        this.theatreRepository = InMemoryRepository.getInMemoryRepository();
        this.customerFactory = new CustomerFactory();
        this.adminFactory = new AdminFactory();
        this.sessionContext = new SessionContext();
    }
    
    public static ApplicationContext getInstance() {
        return instance;
    }
    
    public IUserRepository getUserRepository() {
        return userRepository;
    }
    
    public IBookingRepository getBookingRepository() {
        return bookingRepository;
    }
    
    public IMovieRepository getMovieRepository() {
        return movieRepository;
    }
    
    public IShowRepository getShowRepository() {
        return showRepository;
    }
    
    public ITheatreRepository getTheatreRepository() {
        return theatreRepository;
    }

    public AuthenticatableUserFactory getCustomerFactory() {
        return customerFactory;
    }
    
    public AuthenticatableUserFactory getAdminFactory() {
        return adminFactory;
    }
    
    public SessionContext getSessionContext() {
        return sessionContext;
    }
}